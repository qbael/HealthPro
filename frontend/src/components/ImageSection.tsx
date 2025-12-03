'use client'
import Image from "next/image";
import {Input} from "@/components/ui/input";
import { usePathname, useRouter } from "next/navigation"
import { useState } from "react";

const ImageSection = () => {
    const pathname = usePathname()
    const router = useRouter()
    const [searchQuery, setSearchQuery] = useState("")

    const placeholderMap: Record<string, string> = {
        "/booking/doctor": "Tìm kiếm bác sĩ",
        "/booking/clinics": "Tìm kiếm phòng khám",
        "/booking/hospitals": "Tìm kiếm bệnh viện",
        "/booking/services": "Tìm kiếm dịch vụ y tế",
    }

    const placeholder = Object.entries(placeholderMap).find(([key]) => pathname.includes(key))?.[1] || "Triệu chứng, bác sĩ, bệnh viện..."

    const handleSearch = () => {
        if (searchQuery.trim()) {
            // Chuyển hướng đến trang search với query string
            router.push(`/search?q=${encodeURIComponent(searchQuery.trim())}&type=all`)
        }
    }

    const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
        if (e.key === 'Enter') {
            handleSearch()
        }
    }

    return (
        <section className='relative w-full h-[500px]'>
            <Image
                src='/landing.jpg'
                fill
                style={{objectFit: 'cover'}}
                alt='landing'
            />
            <div
                className='absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex flex-col items-center gap-2 text-center w-[50%]'>
                <h1 className='text-4xl text-black font-bold'>Ứng dụng đặt khám</h1>
                <p className='text-md text-black w-[90%]'>Đặt khám với hơn 1000 bác sĩ đã kết nối chính thức với
                    HealthPro để có số thứ tự và khung giờ khám trước</p>
                <Input
                    className="bg-white"
                    placeholder={placeholder}
                    value={searchQuery}
                    onChange={(e) => setSearchQuery(e.target.value)}
                    onKeyDown={handleKeyDown}
                />
            </div>
        </section>
    );
}

export default ImageSection;