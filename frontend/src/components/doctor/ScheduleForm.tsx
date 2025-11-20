'use client'

import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"
import { Button } from "@/components/ui/button"
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form"
import { Checkbox } from "@/components/ui/checkbox"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { toast } from "sonner"
import api from "@/lib/axios"
import { useAuth } from "@/contexts/AuthContext"
import { useRouter } from "next/navigation"
import { useEffect } from "react"

const daySchema = z.object({
    enabled: z.boolean(),
    fromTime: z.string().optional(),
    toTime: z.string().optional(),
    slotDuration: z.number().optional(),
})

const scheduleSchema = z.object({
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
        const [h, m] = t.split(":").map(Number)
        return h * 60 + m
    }

    const hasEnabledDay = Object.values(data.days).some(day => day.enabled)
    if (!hasEnabledDay) {
        ctx.addIssue({
            path: ["days"],
            code: z.ZodIssueCode.custom,
            message: "Vui lòng chọn ít nhất một ngày trong tuần.",
        })
    }

    Object.entries(data.days).forEach(([dayKey, day]) => {
        if (day.enabled) {
            if (!day.fromTime) {
                ctx.addIssue({
                    path: ["days", dayKey, "fromTime"],
                    code: z.ZodIssueCode.custom,
                    message: "Vui lòng chọn giờ bắt đầu.",
                })
            }
            if (!day.toTime) {
                ctx.addIssue({
                    path: ["days", dayKey, "toTime"],
                    code: z.ZodIssueCode.custom,
                    message: "Vui lòng chọn giờ kết thúc.",
                })
            }
            if (!day.slotDuration) {
                ctx.addIssue({
                    path: ["days", dayKey, "slotDuration"],
                    code: z.ZodIssueCode.custom,
                    message: "Vui lòng chọn thời lượng ca.",
                })
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
    })
})


type ScheduleFormType = z.infer<typeof scheduleSchema>

const days = [
    { key: "SUNDAY", label: "Chủ Nhật" },
    { key: "MONDAY", label: "Thứ Hai" },
    { key: "TUESDAY", label: "Thứ Ba" },
    { key: "WEDNESDAY", label: "Thứ Tư" },
    { key: "THURSDAY", label: "Thứ Năm" },
    { key: "FRIDAY", label: "Thứ Sáu" },
    { key: "SATURDAY", label: "Thứ Bảy" },
] as const

const hours = Array.from({ length: (23 - 6) * 2 + 1 }, (_, i) => {
    const hour = 6 + Math.floor(i / 2)
    const minute = i % 2 === 0 ? "00" : "30"
    return `${hour.toString().padStart(2, "0")}:${minute}`
})

const slotDurations = Array.from({ length: 12 }, (_, i) => {
    const value = (i + 1) * 5
    return { value, label: `${value} phút` }
})

export default function ScheduleForm({ initialTemplates, onClose }: any) {
    const { user } = useAuth()
    const router = useRouter()

    const getInitialValues = () => {
        const defaultDayValue = {
            enabled: false,
            fromTime: "",
            toTime: "",
            slotDuration: undefined,
        }

        if (!initialTemplates || !Array.isArray(initialTemplates) || initialTemplates.length === 0) {
            return {
                days: Object.fromEntries(
                    days.map(d => [d.key, { ...defaultDayValue }])
                ) as ScheduleFormType["days"]
            }
        }

        return {
            days: Object.fromEntries(
                days.map(d => {
                    const template = initialTemplates.find((t: any) => t.dayOfWeek === d.key)
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
                    ]
                })
            ) as ScheduleFormType["days"]
        }
    }

    const form = useForm<ScheduleFormType>({
        resolver: zodResolver(scheduleSchema),
        defaultValues: getInitialValues(),
    })

    useEffect(() => {
        form.reset(getInitialValues())
    }, [initialTemplates])

    const onSubmit = async (values: ScheduleFormType) => {
        try {
            const templatesArray = Object.entries(values.days)
                .filter(([_, day]) => day.enabled)
                .map(([dayKey, day]) => ({
                    dayOfWeek: dayKey,
                    fromTime: day.fromTime,
                    toTime: day.toTime,
                    slotDuration: day.slotDuration,
                }))

            if (templatesArray.length === 0) {
                toast.error("Vui lòng chọn ít nhất một ngày làm việc!")
                return
            }

            await api.post(`v3/doctor-schedule-template/${user.userRoleId}`, {
                templates: templatesArray,
            })

            toast.success(
                !initialTemplates || initialTemplates.length === 0
                    ? "Đăng ký lịch làm thành công!"
                    : "Chỉnh sửa lịch làm thành công!"
            )
            onClose()
        } catch (err) {
            console.error(err)
            toast.error(
                !initialTemplates || initialTemplates.length === 0
                    ? "Đăng ký thất bại!"
                    : "Chỉnh sửa thất bại!"
            )
        }
    }

    const { isSubmitting } = form.formState

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
                <div className="flex flex-col gap-5">
                    {days.map((d) => (
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
                                                            {hours.map(h => (
                                                                <SelectItem key={h} value={h}>{h}</SelectItem>
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
                                                            {hours.map(h => (
                                                                <SelectItem key={h} value={h}>{h}</SelectItem>
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
                                                            {slotDurations.map(s => (
                                                                <SelectItem key={s.value} value={s.value.toString()}>
                                                                    {s.label}
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

                <Button
                    type="submit"
                    disabled={isSubmitting}
                    className="bg-blue-500 hover:bg-blue-600 text-white"
                >
                    {isSubmitting ? "Đang xử lý..." :
                        (!initialTemplates || initialTemplates.length === 0)
                            ? "Đăng ký"
                            : "Chỉnh sửa"}
                </Button>
            </form>
        </Form>
    )
}