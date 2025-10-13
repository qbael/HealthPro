"use client"

import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"

import { Button } from "@/components/ui/button"
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import {useEffect, useState} from "react";

const baseSchema = z.object({
    role: z
        .string(),

    email: z
        .string()
        .min(1, { message: "Vui lòng nhập email."})
        .email({ message: "Email không hợp lệ"}),

    fullName: z.string().min(1, {
        message: "Vui lòng nhập họ tên.",
    }),

    phoneNumber: z
        .string()
        .trim()
        .refine(
            (value) => /^(\+84|0)(3|5|7|8|9)\d{8}$/.test(value),
            { message: "Số điện thoại không hợp lệ." }
        ),

    address: z.string().min(1, {
        message: "Vui lòng nhập địa chỉ.",
    }),
})

interface UserProfile {
    id: string;
    doctorId: string
    role: string;
    email: string;
    phoneNumber: string;
    isActive: boolean;
    fullName: string;
    address: string
}

interface ProfileFormProps {
    user: UserProfile;
}
const ProfileForm = ({ user } : ProfileFormProps) => {
    const [loading, setLoading] = useState(false)

    useEffect(() => {

    }, []);

    const form = useForm<z.infer<typeof baseSchema>>({
        resolver: zodResolver(baseSchema),
        defaultValues: {
            role: user.role,
            email: user.email,
            fullName: user.fullName || "",
            phoneNumber: user.phoneNumber,
            address:  user.address || "",
        },
    })

    const onSubmit = async (values: z.infer<typeof baseSchema>) => {
        console.log(values)
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <FormField
                    control={form.control}
                    name="role"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Vai Trò</FormLabel>
                            <FormControl>
                                <Input {...field} disabled={true}/>
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="email"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Email</FormLabel>
                            <FormControl>
                                <Input placeholder="smartschoolbus@gmail.com" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="fullName"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Họ Tên</FormLabel>
                            <FormControl>
                                <Input placeholder="Trần Minh Đăng" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="phoneNumber"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Số Điện Thoại</FormLabel>
                            <FormControl>
                                <Input placeholder="090-123-4567" {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                {user?.role === 'PATIENT' ? null
                : (
                    <FormField
                        control={form.control}
                        name="address"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Địa Chỉ</FormLabel>
                                <FormControl>
                                    <Input placeholder="123 An Dương Vương" {...field} />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    )}

                <Button type="submit" disabled={loading} className='w-full bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    {loading ? "Đang cập nhật..." : "Cập nhật"}
                </Button>
            </form>
        </Form>
    );
};

export default ProfileForm;