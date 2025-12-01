'use client'
import Image from 'next/image'
import {Button} from '@/components/ui/button'
import DropDownMenu from "@/components/DropDownMenu";
import Link from "next/link";
import {useAuth} from "@/contexts/AuthContext";
import {useAuthAction} from "@/hooks/useAuthAction"
import React from "react";

const Header = () => {
    const {user} = useAuth()
    const {logout} = useAuthAction()

    return (
        <header
            className='fixed top-0 left-0 flex items-center justify-between w-full h-16 px-3 bg-white shadow-md z-20'>
            <div className='flex items-center'>
                <Link href={"/"}>
                    <h1 className='text-3xl font-bold italic text-[#0079CEFF]'>HealthPro</h1>
                </Link>
            </div>
            <div className='flex items-center gap-1'>
                {
                    user?.role === 'PATIENT' || !user ? (
                        <>
                            <DropDownMenu/>
                            <div className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                            text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'>
                                Lịch hẹn
                            </div>
                        </>
                    ) : user?.role === 'DOCTOR' ? (
                        <>
                            <Link className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                        text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'
                                  href='/doctor/invitations'
                            >
                                Lời mời
                            </Link>
                            <Link className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                        text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'
                                  href='/doctor/specialty'
                            >
                                Chuyên khoa
                            </Link>
                            <Link className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                            text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'
                                  href='/doctor/schedules'
                            >
                                Lịch làm
                            </Link>
                        </>
                    ) : user?.role === 'CLINIC' ? (
                        <>
                            <Link className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                            text-gray-700 hover:bg-blue-100 cursor-pointer mr-2'
                                  href='/clinic/specialty'
                            >
                                Chuyên khoa
                            </Link>
                        </>
                    ) : null}

                {user ? (
                    <div className='flex gap-7'>
                        <Link href='/profile' className='hover:cursor-pointer inline-flex justify-center rounded-md px-4 py-2 bg-white text-sm font-bold
                                text-gray-700 hover:bg-blue-100 cursor-pointer'>
                            Hồ Sơ {
                            user?.role === 'PATIENT' ? <span className='ml-1'>(Bệnh Nhân)</span> :
                                user?.role === 'DOCTOR' ? <span className='ml-1'>(Bác Sĩ)</span> :
                                    user?.role === 'CLINIC' ? <span className='ml-1'>(Phòng Khám)</span> : null}
                        </Link>

                        <Button variant='destructive'
                                className=' hover:cursor-pointer hover:bg-red-500 hover:text-white' onClick={logout}>
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