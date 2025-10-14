"use client"

import {zodResolver} from "@hookform/resolvers/zod"
import {useForm} from "react-hook-form"
import {z} from "zod"
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue,} from "@/components/ui/select"
import {Button} from "@/components/ui/button"
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage,} from "@/components/ui/form"
import {Input} from "@/components/ui/input"
import {useState} from "react";
import api from "@/lib/axios";
import {useRouter} from "next/navigation";
import {toast} from "sonner";

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

    gender: z.boolean({
        message: "Vui lòng nhập giới tính.",
    }),

    // address: z.string().min(1, {
    //     message: "Vui lòng nhập địa chỉ.",
    // }),

    // dateOfBirth: z
    //     .string()
    //     .datetime({ offset: false, message: "Ngày sinh không hợp lệ." })
    //     .or(z.string().min(1, { message: "Vui lòng nhập ngày sinh." })),
    //
    // bio: z.string().min(1, {
    //     message: "Vui lòng nhập giới thiệu.",
    // }),
    //
    // avatarUrl: z.string().min(1, {
    //     message: "Vui lòng chọn avatar.",
    // }),
    //
    // logoUrl: z.string().min(1, {
    //     message: "Vui lòng chọn logo.",
    // }),
    //
    // description: z.string().min(1, {
    //     message: "Vui lòng nhập chú thích.",
    // }),
    //
    // weekdayOpenHour: z.string().min(1, {
    //     message: "Vui lòng chọn giờ bắt đầu trong tuần.",
    // }),
    //
    // weekdayCloseHour: z.string().min(1, {
    //     message: "Vui lòng chọn giờ kết thúc trong tuần.",
    // }),
    //
    // weekendOpenHour: z.string().min(1, {
    //     message: "Vui lòng chọn ngày bắt đầu cuối tuần.",
    // }),
    //
    // weekendCloseHour: z.string().min(1, {
    //     message: "Vui lòng chọn ngày kết thúc cuối tuần.",
    // })
})

interface UserProfile {
    id: string
    Id: string
    role: string
    email: string
    phoneNumber: string
    isActive: boolean
    fullName: string
    address: string
    gender: boolean
    dateOfBirth: string
    bio: string
    avatarUrl: string
    logoUrl: string
    description: string
    weekdayOpenHour: string
    weekdayCloseHour: string
    weekendOpenHour: string
    weekendCloseHour: string
}

interface ProfileFormProps {
    user: UserProfile;
}
const ProfileForm = ({ user } : ProfileFormProps) => {
    const [loading, setLoading] = useState(false)
    const router = useRouter()

    const form = useForm<z.infer<typeof baseSchema>>({
        resolver: zodResolver(baseSchema),
        defaultValues: {
            role: user.role,
            email: user.email,
            fullName: user.fullName || "",
            phoneNumber: user.phoneNumber,
            gender: user.gender || undefined,
            address:  user.address || "",
            dateOfBirth: user.dateOfBirth || "",
            bio: user.bio || "",
            avatarUrl: user.avatarUrl || "",
            logoUrl: user.logoUrl || "",
            description: user.description || "",
            weekdayOpenHour: user.weekdayOpenHour || "",
            weekdayCloseHour: user.weekdayCloseHour || "",
            weekendOpenHour: user.weekendOpenHour || "",
            weekendCloseHour: user.weekendCloseHour || "",
        },
    })

    const onSubmit = async (values: z.infer<typeof baseSchema>) => {
        try {
            setLoading(true)
            await api.put(`v1/profile/${user.role.toLowerCase()}/${user.id}`, values)
            setLoading(false)
            toast.success("Cập nhật thành công")
        }
        catch (err) {
            console.error('Failed to update profile:', err)
        }
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

                {user?.role === 'CLINIC' ? null
                    : (
                        <FormField
                            control={form.control}
                            name="gender"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Giới Tính</FormLabel>
                                    <FormControl>
                                        <Select
                                            onValueChange={(value) => field.onChange(value === "true")}
                                            value={field.value?.toString() ?? ""}
                                        >
                                            <SelectTrigger className="w-[180px]">
                                                <SelectValue placeholder="Giới tính" />
                                            </SelectTrigger>
                                            <SelectContent>
                                                <SelectItem value="true">Nam</SelectItem>
                                                <SelectItem value="false">Nữ</SelectItem>
                                            </SelectContent>
                                        </Select>
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