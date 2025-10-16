'use client'
import Image from 'next/image'
import { Button } from '@/components/ui/button'
import DropDownMenu from "@/components/ui/DropDownMenu";
import Link from "next/link";
import {useAuth} from "@/contexts/AuthContext";
import {useAuthAction} from "@/hooks/useAuthAction"

const Header = () => {
    const { user } = useAuth()
    const { logout } = useAuthAction()
    return (
        <header className='fixed top-0 left-0 flex items-center justify-between w-full h-16 px-3 bg-white shadow-md z-10'>
            <div className='flex items-center'>
                <Link href={"/"}>
                    <Image
                        src='logo.svg'
                        alt='logo'
                        width={100}
                        height={100}
                        priority
                    />
                </Link>
            </div>
            <div className='flex items-center gap-1'>
                <DropDownMenu/>
                <div className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'>
                    Liên hệ
                </div>

                {user ? (
                    <div className='flex gap-7'>
                            <Link href='/profile' className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                                text-gray-700 hover:bg-blue-100 cursor-pointer' >
                                Hồ Sơ
                            </Link>

                        <Button variant='destructive' className=' hover:cursor-pointer hover:bg-red-500 hover:text-white' onClick={logout}>
                            Đăng xuất
                        </Button>
                    </div>

                ) : (
                    <Button variant='outline' className=' hover:cursor-pointer hover:bg-blue-500 hover:text-white'>
                        <Link href='/login'>
                            Đăng nhập
                        </Link>
                    </Button>
                )}

            </div>
        </header>
    )
}

export default Header