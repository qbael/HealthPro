'use client'

import { cn } from "@/lib/utils"
import Link from "next/link"
import { usePathname } from "next/navigation"
import * as Icons from 'lucide-react'
import Image from "next/image";
import {Button} from "@/components/ui/button";
import {useAuthAction} from "@/hooks/useAuthAction";

interface NavItem {
    label: string
    href: string
    icon: string
}
interface SideBarProps {
    navItems: NavItem[]
    isOpen?: boolean
    onClose?: () => void
}

const DashBoardSidebar = ({ navItems, isOpen, onClose }: SideBarProps) => {
    const pathname = usePathname()
    const { logout } = useAuthAction()

    return (
        <nav
            className={cn(
                "fixed top-0 left-0 flex flex-col justify-between gap-3 w-[16%] h-full pt-4 border-r border-gray-200 shadow-md z-10 bg-white transition-transform overflow-y-auto",
                "md:translate-x-0",
                isOpen ? "translate-x-0" : "-translate-x-full md:translate-x-0"
            )}
        >
            <div>

            <div className='flex justify-center items-center mb-10 pb-5 border-b border-gray-200'>
                <Link href={"/"}>
                    <Image
                        src='logo.svg'
                        alt='logo'
                        width={120}
                        height={120}
                        priority
                    />
                </Link>
            </div>

            {navItems.map(({ label, href, icon }) => {
                const Icon = (Icons as any)[icon]
                return (
                    <Link
                        href={href}
                        key={label}
                        onClick={onClose}
                        className={cn(
                            'flex items-center md:justify-start justify-center text-gray-500 hover:bg-gray-200 p-4 my-3 rounded-sm',
                            pathname === href && 'text-black font-semibold bg-gray-200'
                        )}
                    >
                        {Icon ? <Icon className="w-6 h-6 shrink-0" /> : null}
                        <span className="text-sm ml-3 hidden md:inline">{label}</span>
                    </Link>
                )
            })}
            </div>
            <Button variant='destructive' className='hover:cursor-pointer hover:bg-red-500 hover:text-white mb-1' onClick={logout}>
                Đăng xuất
            </Button>
        </nav>
    )
}

export default DashBoardSidebar