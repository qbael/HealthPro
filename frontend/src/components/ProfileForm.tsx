"use client"

import {zodResolver} from "@hookform/resolvers/zod"
import {useForm} from "react-hook-form"
import {z} from "zod"
import {Popover, PopoverContent, PopoverTrigger,} from "@/components/ui/popover"
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue,} from "@/components/ui/select"
import {Button} from "@/components/ui/button"
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage,} from "@/components/ui/form"
import {Input} from "@/components/ui/input"
import * as React from "react";
import {useState} from "react";
import api from "@/lib/axios";
import {toast} from "sonner";
import {Textarea} from "@/components/ui/textarea"
import {Calendar} from "@/components/ui/calendar"
import {cn} from '@/lib/utils'
import {CalendarIcon} from "lucide-react"
import {format} from "date-fns"
import {vi} from "date-fns/locale"
import Image from 'next/image'
import {Loading} from '@/components/ui/Loading'

const baseSchema = z.object({
    role: z
        .string(),

    email: z
        .string()
        .min(1, { message: "Vui lòng nhập email."})
        .email({ message: "Email không hợp lệ"}),

    phoneNumber: z
        .string()
        .trim()
        .refine(
            (value) => /^(\+84|0)(3|5|7|8|9)\d{8}$/.test(value),
            { message: "Số điện thoại không hợp lệ." }
        )
})

const patientSchema = baseSchema.extend({
    fullName: z.string().min(1, {
        message: "Vui lòng nhập họ tên.",
    }),

    gender: z.boolean({
        message: "Vui lòng nhập giới tính.",
    }),

    dateOfBirth:
        z.date({ message: "Ngày sinh không hợp lệ." })
})

const doctorSchema = baseSchema.extend({
    fullName: z.string().min(1, {
        message: "Vui lòng nhập họ tên.",
    }),

    bio: z.string().min(1, {
        message: "Vui lòng nhập tiểu sử.",
    }),

    gender: z.boolean({
        message: "Vui lòng nhập giới tính.",
    }),

    address: z.string().min(1, {
        message: "Vui lòng nhập địa chỉ.",
    }),

    avatarFile: z
        .any()
        .refine((files) => !files || files.length === 1, "Vui lòng chọn avatar.")
        .optional(),

    avatarUrl: z.string().optional()
})

const isEarlier = (open: string, close: string) => {
    const [openH, openM] = open.split(":").map(Number);
    const [closeH, closeM] = close.split(":").map(Number);
    return openH * 60 + openM < closeH * 60 + closeM;
}

const clinicSchema = baseSchema.extend({
    clinicName: z.string().min(1, {
        message: "Vui lòng nhập tên phòng khám.",
    }),

    address: z.string().min(1, {
        message: "Vui lòng nhập địa chỉ.",
    }),

    avatarFile: z
        .any()
        .refine((files) => !files || files.length === 1, "Vui lòng chọn avatar.")
        .optional(),
    avatarUrl: z.string().optional(),

    logoFile: z
        .any()
        .refine((files) => !files || files.length === 1, "Vui lòng chọn logo.")
        .optional(),
    logoUrl: z.string().optional(),

    description: z.string().min(1, {
        message: "Vui lòng nhập chú thích.",
    }),

    weekdayOpenHour: z.string().min(1, {
        message: "Vui lòng chọn giờ bắt đầu trong tuần.",
    }),

    weekdayCloseHour: z.string().min(1, {
        message: "Vui lòng chọn giờ kết thúc trong tuần.",
    }),

    weekendOpenHour: z.string().min(1, {
        message: "Vui lòng chọn ngày bắt đầu cuối tuần.",
    }),

    weekendCloseHour: z.string().min(1, {
        message: "Vui lòng chọn ngày kết thúc cuối tuần.",
    })
})
.superRefine((data, ctx) => {
    const toMinutes = (t: string) => {
        const [h, m] = t.split(":").map(Number);
        return h * 60 + m;
    }

    if (data.weekdayOpenHour && data.weekdayCloseHour) {
        if (toMinutes(data.weekdayOpenHour) >= toMinutes(data.weekdayCloseHour)) {
            ctx.addIssue({
                path: ["weekdayOpenHour"],
                code: z.ZodIssueCode.custom,
                message: "Giờ bắt đầu phải nhỏ hơn giờ kết thúc.",
            });
            ctx.addIssue({
                path: ["weekdayCloseHour"],
                code: z.ZodIssueCode.custom,
                message: "Giờ kết thúc phải lớn hơn giờ bắt đầu.",
            });
        }
    }

    if (data.weekendOpenHour && data.weekendCloseHour) {
        if (toMinutes(data.weekendOpenHour) >= toMinutes(data.weekendCloseHour)) {
            ctx.addIssue({
                path: ["weekendOpenHour"],
                code: z.ZodIssueCode.custom,
                message: "Giờ bắt đầu phải nhỏ hơn giờ kết thúc.",
            });
            ctx.addIssue({
                path: ["weekendCloseHour"],
                code: z.ZodIssueCode.custom,
                message: "Giờ kết thúc phải lớn hơn giờ bắt đầu.",
            });
        }
    }
})

interface UserProfile {
    id: string
    Id: string
    role: string
    email: string
    phoneNumber: string
    isActive: boolean
    fullName: string
    clinicName: string
    address: string
    gender: boolean
    dateOfBirth: Date
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
    const [avatarPreview, setAvatarPreview] = useState<string | null>(user.avatarUrl || null)
    const [logoPreview, setLogoPreview] = useState<string | null>(user.logoUrl || null)

    const hours = Array.from({ length: 11 }, (_, i) => {
        const h = i + 8;
        return h.toString().padStart(2, "0") + ":00";
    })
    const schema = user.role === 'PATIENT' ? patientSchema :
                        user.role === 'DOCTOR' ? doctorSchema :
                        user.role === 'CLINIC' ? clinicSchema : null

    if (!schema) throw new Error('Unknown role')

    type FormValues = z.infer<typeof schema>

    const form = useForm<FormValues>({
        resolver: zodResolver(schema),
        defaultValues: {
            role: user.role === 'PATIENT' ? 'BỆNH NHÂN' :
                  user.role === 'DOCTOR' ? 'BÁC SĨ' :
                  user.role === 'CLINIC' ? 'PHÒNG KHÁM' : "KHÔNG CÓ"
            ,
            email: user.email,
            fullName: user.fullName || "",
            clinicName: user.clinicName || "",
            phoneNumber: user.phoneNumber,
            gender: user.gender || undefined,
            address:  user.address || "",
            dateOfBirth: user.dateOfBirth ? new Date(user.dateOfBirth) : undefined,
            bio: user.bio || "",
            avatarUrl: user.avatarUrl || "",
            logoUrl: user.logoUrl || "",
            description: user.description || "",
            weekdayOpenHour: user.weekdayOpenHour || "",
            weekdayCloseHour: user.weekdayCloseHour || "",
            weekendOpenHour: user.weekendOpenHour || "",
            weekendCloseHour: user.weekendCloseHour || "",
        },
        shouldUnregister: true
    })

    const uploadToCloudinary = async (file: File) => {
        const formData = new FormData()
        formData.append("file", file)
        formData.append("upload_preset", "clinics")
        const res = await fetch("https://api.cloudinary.com/v1_1/dwkjsecri/image/upload", {
            method: "POST",
            body: formData,
        })
        const data = await res.json()
        return data.secure_url
    }

    const onSubmit = async (values: z.infer<typeof schema>) => {
        try {
            setLoading(true)

            const payload: any = { ...values }

            if ("avatarFile" in values && values.avatarFile?.[0]) {
                payload.avatarUrl = await uploadToCloudinary(values.avatarFile[0])
            }

            if ("logoFile" in values && values.logoFile?.[0]) {
                payload.logoUrl = await uploadToCloudinary(values.logoFile[0])
            }

            delete payload.avatarFile
            delete payload.logoFile

            await api.put(`v1/profile/${user.role.toLowerCase()}/${user.id}`, payload)
            toast.success("Cập nhật thành công")
        }

        catch (err: any) {
            const msg = err.response?.data?.message || 'Cập nhật thất bại. Vui lòng thử lại.'
            toast.error(msg)
        }
        finally{
            setLoading(false)
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
                                <Input {...field} readOnly/>
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

                {
                    user.role === 'CLINIC' ? (
                        <FormField
                            control={form.control}
                            name="clinicName"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Tên Phòng Khám</FormLabel>
                                    <FormControl>
                                        <Input placeholder="Phòng Khám HealthPro" {...field} />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                    ) : (
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
                    )
                }

                {user.role === 'PATIENT' && (
                        <FormField
                            control={form.control}
                            name="dateOfBirth"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Ngày Sinh</FormLabel>
                                    <FormControl>
                                        <Popover>
                                            <PopoverTrigger asChild>
                                                <FormControl>
                                                    <Button
                                                        variant={"outline"}
                                                        className={cn(
                                                            "w-[240px] pl-3 text-left font-normal",
                                                            !field.value && "text-muted-foreground"
                                                        )}
                                                    >
                                                        {field.value ? (
                                                            format(field.value, "PPP", { locale: vi })
                                                        ) : (
                                                            <span>Chọn ngày sinh</span>
                                                        )}
                                                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                                                    </Button>
                                                </FormControl>
                                            </PopoverTrigger>
                                            <PopoverContent className="w-auto p-0" align="start">
                                                <Calendar
                                                    mode="single"
                                                    selected={field.value}
                                                    onSelect={field.onChange}
                                                    disabled={(date) =>
                                                        date > new Date() || date < new Date("1900-01-01")
                                                    }
                                                    captionLayout="dropdown"
                                                />
                                            </PopoverContent>
                                        </Popover>
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                    )}

                {user.role === 'PATIENT' ? null
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

                {user.role === 'CLINIC' ? null
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

                {user.role === 'DOCTOR' && (
                        <FormField
                            control={form.control}
                            name="bio"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Tiểu Sử</FormLabel>
                                    <FormControl>
                                        <Textarea placeholder="Nhập tiểu sử..." {...field} />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                    )}

                {user.role === 'PATIENT' ? null
                    : (
                    <FormField
                        control={form.control}
                        name="avatarFile"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Ảnh Avatar</FormLabel>
                                <FormControl>
                                    <Input
                                        type='file'
                                        accept="image/*"
                                        onChange={(e) => {
                                            const files = e.target.files
                                            field.onChange(files)
                                            if (files && files[0]) {
                                                setAvatarPreview(URL.createObjectURL(files[0]))
                                            }
                                        }}
                                    />
                                </FormControl>
                                {avatarPreview && (
                                    <img
                                        src={avatarPreview}
                                        alt="Avatar Preview"
                                        width={200}
                                        height={200}
                                        className="mt-2 object-cover rounded"
                                    />
                                )}
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                    )}

                {user.role === 'CLINIC' && (
                    <>
                        <FormField
                            control={form.control}
                            name="logoFile"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Ảnh Logo</FormLabel>
                                    <FormControl>
                                        <Input
                                            type="file"
                                            accept="image/*"
                                            onChange={(e) => {
                                                const files = e.target.files
                                                field.onChange(files)
                                                if (files && files[0]) {
                                                    setLogoPreview(URL.createObjectURL(files[0]))
                                                }
                                            }}
                                        />
                                    </FormControl>
                                    {logoPreview && (
                                        <Image
                                            src={logoPreview}
                                            alt="Logo Preview"
                                            width={200}
                                            height={200}
                                            className="mt-2 object-cover rounded"
                                        />
                                    )}
                                    <FormMessage />
                                </FormItem>
                            )}
                        />

                        <FormField
                            control={form.control}
                            name="description"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Giới Thiệu</FormLabel>
                                    <FormControl>
                                        <Textarea placeholder="Nhập giới thiệu..." {...field} />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />

                        <div className='flex justify-between items-center gap-7 w-full'>
                            <FormField
                                control={form.control}
                                name="weekdayOpenHour"
                                render={({ field }) => (
                                    <FormItem className='flex-1'>
                                        <FormLabel>Giờ Bắt Đầu Trong Tuần</FormLabel>
                                        <FormControl>
                                            <Select
                                                onValueChange={field.onChange}
                                                value={field.value}
                                            >
                                                <SelectTrigger className="w-full">
                                                    <SelectValue placeholder="Giờ Bắt Đầu Trong Tuần" />
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
                                name="weekdayCloseHour"
                                render={({ field }) => (
                                    <FormItem className='flex-1'>
                                        <FormLabel>Giờ Kết Thúc Trong Tuần</FormLabel>
                                        <FormControl>
                                            <Select
                                                onValueChange={field.onChange}
                                                value={field.value}
                                            >
                                                <SelectTrigger className="w-full">
                                                    <SelectValue placeholder="Giờ Kết Thúc Trong Tuần" />
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
                        </div>

                        <div className='flex justify-between items-center gap-7 w-full'>
                            <FormField
                                control={form.control}
                                name="weekendOpenHour"
                                render={({ field }) => (
                                    <FormItem className='flex-1'>
                                        <FormLabel>Giờ Bắt Đầu Cuối Tuần</FormLabel>
                                        <FormControl>
                                            <Select
                                                onValueChange={field.onChange}
                                                value={field.value}
                                            >
                                                <SelectTrigger className="w-full">
                                                    <SelectValue placeholder="Giờ Bắt Đầu Trong Tuần" />
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
                                name="weekendCloseHour"
                                render={({ field }) => (
                                    <FormItem className='flex-1'>
                                        <FormLabel>Giờ Kết Thúc Cuối Tuần</FormLabel>
                                        <FormControl>
                                            <Select
                                                onValueChange={field.onChange}
                                                value={field.value}
                                            >
                                                <SelectTrigger className="w-full">
                                                    <SelectValue placeholder="Giờ Kết Thúc Cuối Tuần" />
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
                        </div>
                    </>
                )}

                {loading && <Loading />}
                <Button type="submit" disabled={loading} className='w-full bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    {loading ? "Đang cập nhật..." : "Cập nhật"}
                </Button>
            </form>
        </Form>
    );
};

export default ProfileForm;