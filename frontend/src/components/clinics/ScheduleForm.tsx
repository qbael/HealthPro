'use client'

import {zodResolver} from "@hookform/resolvers/zod"
import {useForm} from "react-hook-form"
import {z} from "zod"
import {Button} from "@/components/ui/button"
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage,} from "@/components/ui/form"
import {Checkbox} from "@/components/ui/checkbox"
import {toast} from 'sonner'
import api from '@/lib/axios'
import * as React from "react"
import {useEffect, useState} from "react"
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {useRouter} from 'next/navigation'
import {useAuth} from "@/contexts/AuthContext";

const daySchema = z.object({
    enabled: z.boolean(),
    fromTime: z.string().optional(),
    toTime: z.string().optional(),
    slotDuration: z.number().optional(),
});

const baseSchema = z.object({
    days: z.object({
        SUNDAY: daySchema,
        MONDAY: daySchema,
        TUESDAY: daySchema,
        WEDNESDAY: daySchema,
        THURSDAY: daySchema,
        FRIDAY: daySchema,
        SATURDAY: daySchema,
    })
}).superRefine((data, ctx) => {
    const toMinutes = (t: string) => {
        const [h, m] = t.split(":").map(Number);
        return h * 60 + m;
    }

    const hasEnabledDay = Object.values(data.days).some(day => day.enabled);
    if (!hasEnabledDay) {
        ctx.addIssue({
            path: ["days"],
            code: z.ZodIssueCode.custom,
            message: "Vui lòng chọn ít nhất một ngày trong tuần.",
        });
    }

    Object.entries(data.days).forEach(([dayKey, day]) => {
        if (day.enabled) {
            if (!day.fromTime) {
                ctx.addIssue({
                    path: ["days", dayKey, "fromTime"],
                    code: z.ZodIssueCode.custom,
                    message: "Vui lòng chọn giờ bắt đầu.",
                });
            }
            if (!day.toTime) {
                ctx.addIssue({
                    path: ["days", dayKey, "toTime"],
                    code: z.ZodIssueCode.custom,
                    message: "Vui lòng chọn giờ kết thúc.",
                });
            }
            if (!day.slotDuration) {
                ctx.addIssue({
                    path: ["days", dayKey, "slotDuration"],
                    code: z.ZodIssueCode.custom,
                    message: "Vui lòng chọn thời lượng ca.",
                });
            }

            if (day.fromTime && day.toTime) {
                const from = toMinutes(day.fromTime)
                const to = toMinutes(day.toTime)

                if (from >= to) {
                    ctx.addIssue({
                        path: ["days", dayKey, "fromTime"],
                        code: z.ZodIssueCode.custom,
                        message: "Giờ bắt đầu phải nhỏ hơn giờ kết thúc.",
                    })
                    ctx.addIssue({
                        path: ["days", dayKey, "toTime"],
                        code: z.ZodIssueCode.custom,
                        message: "Giờ kết thúc phải lớn hơn giờ bắt đầu.",
                    })
                }

                if (day.slotDuration) {
                    const duration = to - from
                    if (day.slotDuration > duration) {
                        ctx.addIssue({
                            path: ["days", dayKey, "slotDuration"],
                            code: z.ZodIssueCode.custom,
                            message: `Thời lượng ca (${day.slotDuration} phút) phải nhỏ hơn tổng thời gian (${duration} phút) ít nhất 5 phút.`,
                        })
                    }
                }
            }
        }
    });
});

const ScheduleForm = ({ templates, clinicSpecialtyId, onClose } : any) => {
    const { user } = useAuth()
    const router = useRouter()

    const days = [
        { key: "SUNDAY", label: "Chủ Nhật" },
        { key: "MONDAY", label: "Thứ Hai" },
        { key: "TUESDAY", label: "Thứ Ba" },
        { key: "WEDNESDAY", label: "Thứ Tư" },
        { key: "THURSDAY", label: "Thứ Năm" },
        { key: "FRIDAY", label: "Thứ Sáu" },
        { key: "SATURDAY", label: "Thứ Bảy" },
    ] as const;

    const slotDurations = Array.from({ length: 12 }, (_, i) => {
        const value = (i + 1) * 5;
        return {
            value,
            label: `${value} phút`,
        };
    });

    const [hours, setHours] = useState<string[]>([]);

    const getClinicTime = async () => {
        const res = await api.get(`v1/clinics/time/${user?.userRoleId}`)
        const clinic = res.data

        const open = parseInt(clinic.fromTime.split(":")[0], 10)
        const close = parseInt(clinic.toTime.split(":")[0], 10)

        const generatedHours = Array.from({ length: (close - open) * 2 + 1 }, (_, i) => {
            const hour = open + Math.floor(i / 2)
            const minute = i % 2 === 0 ? "00" : "30"
            return `${hour.toString().padStart(2, "0")}:${minute}`
        })

        setHours(generatedHours)
    }

    useEffect(() => {
        if (user?.userRoleId)
            getClinicTime();
    }, [user])

    const getInitialValues = () => {
        const defaultDayValue = {
            enabled: false,
            fromTime: "",
            toTime: "",
            slotDuration: undefined,
        };

        if (!templates || !Array.isArray(templates) || templates.length === 0) {
            return {
                days: Object.fromEntries(
                    days.map(d => [d.key, { ...defaultDayValue }])
                ) as any
            };
        }

        // Nếu có templates array, chuyển thành object
        return {
            days: Object.fromEntries(
                days.map(d => {
                    const template = templates.find((t: any) => t.dayOfWeek === d.key);
                    return [
                        d.key,
                        template
                            ? {
                                enabled: true,
                                fromTime: template.fromTime || "",
                                toTime: template.toTime || "",
                                slotDuration: template.slotDuration || undefined,
                            }
                            : { ...defaultDayValue }
                    ];
                })
            ) as any
        };
    };

    const form = useForm<z.infer<typeof baseSchema>>({
        resolver: zodResolver(baseSchema),
        defaultValues: getInitialValues(),
    })

    useEffect(() => {
        form.reset(getInitialValues());
    }, [templates])

    const onSubmit = async (values: z.infer<typeof baseSchema>) => {
        try {
            const templatesArray = Object.entries(values.days)
                .filter(([_, day]) => day.enabled)
                .map(([dayKey, day]) => ({
                    dayOfWeek: dayKey,
                    fromTime: day.fromTime,
                    toTime: day.toTime,
                    slotDuration: day.slotDuration,
                }));

            if (templatesArray.length === 0) {
                toast.error("Vui lòng chọn ít nhất một ngày làm việc!");
                return;
            }

            console.log(templatesArray);

            await api.post(`v3/clinic-specialty-schedule-template/${clinicSpecialtyId}`, {
                templates: templatesArray
            })

            toast.success(
                !templates || templates.length === 0
                    ? 'Đăng ký thành công.'
                    : 'Chỉnh sửa thành công.'
            )
            onClose()
        }
        catch (err: any) {
            console.error(err)
            toast.error(
                !templates || templates.length === 0
                    ? 'Đăng ký thất bại.'
                    : 'Chỉnh sửa thất bại.'
            )
        }
    }

    const { isLoading, isSubmitting } = form.formState

    if (isLoading)
        return <p>Loading...</p>

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
                <div className="flex flex-col gap-5">
                    {days.map(d => (
                        <div key={d.key} className="p-3 border rounded-xl bg-gray-50">
                            <FormField
                                control={form.control}
                                name={`days.${d.key}.enabled`}
                                render={({ field }) => (
                                    <FormItem>
                                        <div className="flex items-center gap-3">
                                            <Checkbox
                                                id={d.key}
                                                checked={field.value}
                                                onCheckedChange={(checked) => field.onChange(!!checked)}
                                            />
                                            <FormLabel htmlFor={d.key} className="font-medium">{d.label}</FormLabel>
                                        </div>
                                    </FormItem>
                                )}
                            />

                            {form.watch(`days.${d.key}.enabled`) && (
                                <div className="mt-3 grid grid-cols-3 gap-4">
                                    <FormField
                                        control={form.control}
                                        name={`days.${d.key}.fromTime`}
                                        render={({ field }) => (
                                            <FormItem>
                                                <FormLabel>Giờ Bắt Đầu</FormLabel>
                                                <FormControl>
                                                    <Select
                                                        onValueChange={field.onChange}
                                                        value={field.value || undefined}
                                                    >
                                                        <SelectTrigger>
                                                            <SelectValue placeholder="Giờ Bắt Đầu" />
                                                        </SelectTrigger>
                                                        <SelectContent>
                                                            {hours?.map(hour => (
                                                                <SelectItem key={hour} value={hour}>
                                                                    {hour}
                                                                </SelectItem>
                                                            ))}
                                                        </SelectContent>
                                                    </Select>
                                                </FormControl>
                                                <FormMessage />
                                            </FormItem>
                                        )}
                                    />

                                    <FormField
                                        control={form.control}
                                        name={`days.${d.key}.toTime`}
                                        render={({ field }) => (
                                            <FormItem>
                                                <FormLabel>Giờ Kết Thúc</FormLabel>
                                                <FormControl>
                                                    <Select
                                                        onValueChange={field.onChange}
                                                        value={field.value || undefined}
                                                    >
                                                        <SelectTrigger>
                                                            <SelectValue placeholder="Giờ Kết Thúc" />
                                                        </SelectTrigger>
                                                        <SelectContent>
                                                            {hours?.map(hour => (
                                                                <SelectItem key={hour} value={hour}>
                                                                    {hour}
                                                                </SelectItem>
                                                            ))}
                                                        </SelectContent>
                                                    </Select>
                                                </FormControl>
                                                <FormMessage />
                                            </FormItem>
                                        )}
                                    />

                                    <FormField
                                        control={form.control}
                                        name={`days.${d.key}.slotDuration`}
                                        render={({ field }) => (
                                            <FormItem>
                                                <FormLabel>Thời Lượng Ca</FormLabel>
                                                <FormControl>
                                                    <Select
                                                        onValueChange={(val) => field.onChange(Number(val))}
                                                        value={field.value?.toString() || undefined}
                                                    >
                                                        <SelectTrigger>
                                                            <SelectValue placeholder="Thời Lượng Ca" />
                                                        </SelectTrigger>
                                                        <SelectContent>
                                                            {slotDurations.map(slot => (
                                                                <SelectItem key={slot.value} value={slot.value.toString()}>
                                                                    {slot.label}
                                                                </SelectItem>
                                                            ))}
                                                        </SelectContent>
                                                    </Select>
                                                </FormControl>
                                                <FormMessage />
                                            </FormItem>
                                        )}
                                    />
                                </div>
                            )}
                        </div>
                    ))}
                </div>

                <Button type="submit" disabled={isSubmitting} className='bg-blue-500 hover:bg-blue-600 text-white hover:cursor-pointer'>
                    {isSubmitting ? 'Đang xử lý...' :
                        (!templates || templates.length === 0)
                            ? 'Đăng ký'
                            : 'Chỉnh sửa'}
                </Button>
            </form>
        </Form>
    )
}

export default ScheduleForm