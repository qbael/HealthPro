'use client'
import Image from 'next/image'
import { Button } from '@/components/ui/button'
import DropDownMenu from "@/components/ui/DropDownMenu";
import Link from "next/link";

const Header = () => {
    return (
        <header className='fixed top-0 left-0 flex items-center justify-between w-full h-16 px-3 bg-white shadow-md z-50'>
            <div className='flex items-center'>
                <Image
                    src='logo.svg'
                    alt='logo'
                    width={100}
                    height={100}
                    priority
                />
            </div>
            <div className='flex items-center gap-1'>
                <DropDownMenu/>
                <div className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                 text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'>
                    Liên hệ
                </div>
                <div className='hover:cursor-pointer'>
                    Đặt khám phòng khám
                </div>
                <Button variant='outline' className=' hover:cursor-pointer hover:bg-blue-500 hover:text-white'>
                    <Link href='/login'>
                        Đăng nhập
                    </Link>
                </Button>
            </div>
        </header>
    )
}

export default Header