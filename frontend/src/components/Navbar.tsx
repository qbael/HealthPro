'use client'
import Image from 'next/image'
import { Button } from '@/components/ui/button'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import { UserCog } from "lucide-react";
const Header = () => {
    return (
        <header className='fixed top-0 left-0 flex items-center justify-between w-full h-16 px-3 bg-white shadow-md'>
            <div className='flex items-center'>
                <Image 
                    src='/logo.svg'
                    alt='logo'
                    width={48}
                    height={48}
                />
            </div>
            <div className='flex items-center gap-5'>
                <div className='hover:cursor-pointer'>
                    Đặt khám bác sĩ
                </div>
                <div className='hover:cursor-pointer'>
                    Đặt khám phòng khám
                </div>
                <Button variant='outline' className=' hover:cursor-pointer hover:bg-blue-500 hover:text-white'>
                    Đăng nhập
                </Button>
            </div>
        </header>
    )
}

export default Header