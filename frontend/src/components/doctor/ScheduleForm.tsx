'use client'

import {zodResolver} from "@hookform/resolvers/zod"
import {useForm} from "react-hook-form"
import {z} from "zod"
import {Button} from "@/components/ui/button"
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage,} from "@/components/ui/form"
import {Checkbox} from "@/components/ui/checkbox"
import {Label} from "@/components/ui/label"
import {toast} from 'sonner'
import api from '@/lib/axios'
import * as React from "react"
import {useEffect} from "react"
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";

const baseSchema = z.object({
    dayOfWeek: z
        .array(
            z.enum([
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday",
            ])
        )
        .nonempty({ message: "Vui lòng chọn ít nhất một ngày trong tuần." }),

    fromTime: z.string().min(1, {
        message: "Vui lòng chọn giờ bắt đầu.",
    }),

    toTime: z.string().min(1, {
        message: "Vui lòng chọn giờ kết thúc.",
    }),

    slotDuration: z
        .number({
            message: "Vui lòng chọn thời lượng ca.",
        })
        .positive({ message: "Thời lượng ca phải lớn hơn 0." })

})

.superRefine((data, ctx) => {
    const toMinutes = (t: string) => {
        const [h, m] = t.split(":").map(Number);
        return h * 60 + m;
    }

    if (data.fromTime && data.toTime) {
        if (toMinutes(data.fromTime) >= toMinutes(data.toTime)) {
            ctx.addIssue({
                path: ["fromTime"],
                code: z.ZodIssueCode.custom,
                message: "Giờ bắt đầu phải nhỏ hơn giờ kết thúc.",
            });
            ctx.addIssue({
                path: ["toTime"],
                code: z.ZodIssueCode.custom,
                message: "Giờ kết thúc phải lớn hơn giờ bắt đầu.",
            });
        }
    }
})

type ScheduleFormProps = {
    schedule?: {
        dayOfWeek: string[]
        fromTime: string
        toTime: string
        slotDuration: number
        doctor_id?: string
    }
    mode?: 'create' | 'update'
    // fetchSchedule: () => void
}

const ScheduleForm = ({ schedule, mode = 'create' }: ScheduleFormProps) => {
    const days = [
        { value: "Sunday", label: "Chủ Nhật" },
        { value: "Monday", label: "Thứ Hai" },
        { value: "Tuesday", label: "Thứ Ba" },
        { value: "Wednesday", label: "Thứ Tư" },
        { value: "Thursday", label: "Thứ Năm" },
        { value: "Friday", label: "Thứ Sáu" },
        { value: "Saturday", label: "Thứ Bảy" },
    ];

    const hours = Array.from({ length: 10 }, (_, i) => {
        const h = i + 8;
        return h.toString().padStart(2, "0") + ":00";
    })

    const slotDurations = Array.from({ length: 12 }, (_, i) => {
        const value = (i + 1) * 5;
        return {
            value,
            label: `${value} phút`,
        };
    });


    const form = useForm<z.infer<typeof baseSchema>>({
        resolver: zodResolver(baseSchema),
        defaultValues: schedule ?? {
            dayOfWeek: [],
            fromTime: "",
            toTime: "",
            slotDuration: undefined,
        },
    })

    useEffect(() => {
        if (schedule)
            form.reset(schedule)

        else
            form.reset({
                dayOfWeek: [],
                fromTime: "",
                toTime: "",
                slotDuration: undefined,
            })
    }, [schedule])

    const onSubmit = async (values: z.infer<typeof baseSchema>) => {
        try {
            if (mode == 'update' && schedule?.doctor_id) {
                const payload = { ... values }

                await api.put(`/api/users/${schedule?.doctor_id}`, {
                    ... values,
                    role: 'Driver'
                })
                fetchSchedule()
                toast.success('Cập nhật thành công.')
            }
            else {
                await api.post('/api/users', {
                    ...values, role: 'Driver'
                })
                fetchSchedule()
                toast.success('Tạo thành công.')
            }

            form.reset()

        }
        catch (err: any) {
            console.error(err)
            toast.error(mode === 'update' ? 'Cập nhật thất bại.' : 'Tạo thất bại.')
        }
    }

    const { isLoading } = form.formState

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="dayOfWeek"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Ngày Làm</FormLabel>
                            <FormControl>
                                <div className=" mt-2 grid grid-cols-4 gap-3">
                                    {days.map(d => (
                                        <div key={d.label} className="flex items-center gap-3">
                                            <Checkbox id={d.label} />
                                            <Label htmlFor={d.label} className='text-sm'>{d.label}</Label>
                                        </div>
                                    ))}
                                </div>
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <div className='flex justify-between items-center gap-7 w-full'>
                    <FormField
                        control={form.control}
                        name="fromTime"
                        render={({ field }) => (
                            <FormItem className='flex-1'>
                                <FormLabel>Giờ Bắt Đầu</FormLabel>
                                <FormControl>
                                    <Select
                                        onValueChange={field.onChange}
                                        value={field.value}
                                    >
                                        <SelectTrigger className="w-full">
                                            <SelectValue placeholder="Giờ Bắt Đầu" />
                                        </SelectTrigger>
                                        <SelectContent>
                                            {hours.map(hour => (
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
                        name="toTime"
                        render={({ field }) => (
                            <FormItem className='flex-1'>
                                <FormLabel>Giờ Kết Thúc</FormLabel>
                                <FormControl>
                                    <Select
                                        onValueChange={field.onChange}
                                        value={field.value}
                                    >
                                        <SelectTrigger className="w-full">
                                            <SelectValue placeholder="Giờ Kết Thúc" />
                                        </SelectTrigger>
                                        <SelectContent>
                                            {hours.map(hour => (
                                                <SelectItem key={hour} value={hour}>
                                                    {hour}
                                                </SelectItem>
                                            ))}
                                        </SelectContent>
                                    </Select>
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}></FormField>
                </div>

                <FormField
                    control={form.control}
                    name="slotDuration"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Thời Lượng Ca</FormLabel>
                            <FormControl>
                                <Select
                                    onValueChange={(val) => field.onChange(Number(val))}
                                    value={field.value?.toString()}
                                >
                                    <SelectTrigger className="w-[180px]">
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

                <Button type="submit" className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    {isLoading ? 'Đang xử lý...' : mode === 'update' ? 'Cập nhật' : 'Đăng ký'}
                </Button>
            </form>
        </Form>
    )
}

export default ScheduleForm