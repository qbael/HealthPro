'use client'

import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Checkbox} from "@/components/ui/checkbox";
import {Label} from "@/components/ui/label";
import {Button} from "@/components/ui/button";
import {useForm} from "react-hook-form";
import {z} from "zod"
import {zodResolver} from "@hookform/resolvers/zod";
import * as React from "react";
import {useEffect} from "react";
import api from "@/lib/axios";
import {toast} from "sonner";
import {useRouter} from "next/navigation";

const baseSchema = z.object({
    specialty: z
        .array(
            z.enum([
                "850e8400-e29b-41d4-a716-446655440001",
                "850e8400-e29b-41d4-a716-446655440002",
                "850e8400-e29b-41d4-a716-446655440003",
                "850e8400-e29b-41d4-a716-446655440004",
                "850e8400-e29b-41d4-a716-446655440005",
                "850e8400-e29b-41d4-a716-446655440006",
                "850e8400-e29b-41d4-a716-446655440007",
                "850e8400-e29b-41d4-a716-446655440008",
                "850e8400-e29b-41d4-a716-446655440009",
                "850e8400-e29b-41d4-a716-446655440010",
            ])
        )
        .nonempty({ message: "Vui lòng chọn ít nhất một chuyên khoa." }),
})

const Specialty = [
    { value: "850e8400-e29b-41d4-a716-446655440001", label: "Nội tổng quát" },
    { value: "850e8400-e29b-41d4-a716-446655440002", label: "Sản - Phụ khoa" },
    { value: "850e8400-e29b-41d4-a716-446655440003", label: "Nhi khoa" },
    { value: "850e8400-e29b-41d4-a716-446655440004", label: "Da liễu" },
    { value: "850e8400-e29b-41d4-a716-446655440005", label: "Ngoại tổng hợp" },
    { value: "850e8400-e29b-41d4-a716-446655440006", label: "Tim mạch" },
    { value: "850e8400-e29b-41d4-a716-446655440007", label: "Tiêu hóa" },
    { value: "850e8400-e29b-41d4-a716-446655440008", label: "Hô hấp" },
    { value: "850e8400-e29b-41d4-a716-446655440009", label: "Thần kinh" },
    { value: "850e8400-e29b-41d4-a716-446655440010", label: "Chỉnh hình" },
] as const;

const SpecialtyForm = ({ specialties } : any) => {
    const router = useRouter()

    const form = useForm<z.infer<typeof baseSchema>>({
        resolver: zodResolver(baseSchema),
        defaultValues: {
            specialty: specialties?.map((s: any) => s.specialtyId) ?? [],
        },
    })

    useEffect(() => {
        if (Array.isArray(specialties)) {
            form.reset({
                specialty: specialties.map((s: any) => s.specialtyId),
            });
        } else {
            form.reset({ specialty: [] });
        }
    }, [form, specialties]);

    const onSubmit = async (values: z.infer<typeof baseSchema>) => {
        console.log(values)
        try {
            await api.post(`v1/doctor-specialty`, values)

            toast.success(
                Object.values(specialties ?? {}).every(v => v === null)
                    ? 'Đăng ký thành công.'
                    : 'Chỉnh sửa thành công.'
            )
            router.refresh()
        }
        catch (err: any) {
            console.error(err)
            toast.error(
                Object.values(specialties ?? {}).every(v => v === null)
                    ? 'Đăng ký thất bại.'
                    : 'Chỉnh sửa thất bại.'
            )
        }
    }

    const { isLoading } = form.formState

    if (isLoading)
        return <p>Loading...</p>

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="specialty"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Ngày Làm</FormLabel>
                            <FormControl>
                                <div className=" mt-2 grid grid-cols-4 gap-5">
                                    {Specialty.map(d => (
                                        <div key={d.value} className="flex items-center gap-3">
                                            <Checkbox
                                                id={d.value}
                                                checked={field.value?.includes(d.value)}
                                                onCheckedChange={(check) => {
                                                    if (check)
                                                        field.onChange([... field.value, d.value])
                                                    else
                                                        field.onChange(field.value.filter(v => v != d.value))
                                                }}
                                            />
                                            <Label htmlFor={d.value} className='text-sm'>{d.label}</Label>
                                        </div>
                                    ))}
                                </div>
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <Button type="submit" className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    {isLoading ? 'Đang xử lý...' :
                        Object.values(specialties ?? {}).every(v => v === null)
                            ? 'Đăng ký'
                            : 'Chỉnh sửa'}
                </Button>
            </form>
        </Form>
    );
};

export default SpecialtyForm;