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
import { useAuthAction } from "@/hooks/useAuthAction";
 
const formSchema = z.object({
    email: z
        .string()
        .min(1, { message: "Vui lòng nhập email."})
        .email({ message: "Email không hợp lệ"}),

    phoneNumber: z
        .string()
        .min(1, { message: "Vui lòng nhập số điện thoại." })
        .regex(/^0\d{9}$/, { message: "Số điện thoại phải có đúng 10 chữ số và bắt đầu bằng 0." }),

    password: z
      .string()    
      .min(8, { message: "Mật khẩu phải ít nhất 8 ký tự." }),

    confPassword: z.string().min(1, { message: "Vui lòng nhập lại mật khẩu." }),
    }).refine((data) => data.password === data.confPassword, {
    path: ["confPassword"],
    message: "Mật khẩu không khớp",
})

type SignupFormProps = {
    role: string
}

const SignupForm = ({ role }: SignupFormProps) => {
    const { signup, loading } = useAuthAction()

    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
        email: "",
        phoneNumber: "",
        password: "",
        confPassword: "",
        }
    })

    const onSubmit = async (values: z.infer<typeof formSchema>) => {
        await signup(values.email, values.phoneNumber, values.password, role)
    }

    return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        <FormField
            control={form.control}
            name="email"
            render={({ field }) => (
                <FormItem>
                <FormLabel>Email</FormLabel>
                <FormControl>
                    <Input placeholder="healthpro@gmail.com" {...field} />
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

        <FormField
            control={form.control}
            name="password"
            render={({ field }) => (
                <FormItem>
                <FormLabel>Mật khẩu</FormLabel>
                <FormControl>
                    <Input placeholder="••••••••" {...field} type="password"/>
                </FormControl>
                <FormMessage />
                </FormItem>
            )}
        />

        <FormField
            control={form.control}
            name="confPassword"
            render={({ field }) => (
                <FormItem>
                <FormLabel>Nhập lại mật khẩu</FormLabel>
                <FormControl>
                    <Input placeholder="••••••••" {...field} type="password"/>
                </FormControl>
                <FormMessage />
                </FormItem>
            )}
        />
        <Button type="submit" disabled={loading} className='w-full bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
            {loading ? "Đang đăng ký..." : "Đăng ký"}
        </Button>
      </form>
    </Form>
    )
}

export default SignupForm