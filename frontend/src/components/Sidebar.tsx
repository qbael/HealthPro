'use client'

import { cn } from "@/lib/utils"
import Link from "next/link"
import { usePathname } from "next/navigation"
// import * as Icons from 'lucide-react'

interface NavItem {
    label: string
    href: string
    icon: React.ComponentType
}

interface SideBarProps {
    navItems: NavItem[]
}

const SideBar = ({ navItems }: SideBarProps) => {
    const pathname = usePathname()

    return (
        <nav
            className={cn(
                "fixed left-0 right-0 bottom-0 flex flex-row items-center justify-around bg-white border-t border-gray-200 shadow-lg z-40 h-16 px-2 md:top-16 md:left-0 md:right-auto md:w-[250px] md:h-[calc(100vh-4rem)] md:flex-col md:justify-start md:gap-3 md:p-4 md:pt-6 md:pr-4 md:border-r md:border-t-0 md:shadow-md md:overflow-y-auto",
                "transition-all duration-300"
            )}
        >
            {navItems.map(({ label, href, icon: IconComponent }) => {
                return (
                    <Link   
                        href={href} 
                        key={label}
                        className={cn(
                            'flex flex-col md:flex-row md:items-center items-center justify-center text-gray-500 hover:bg-gray-100 p-2 md:p-3 rounded-lg md:rounded-sm w-16 md:w-auto h-full md:h-auto',
                            pathname === href && 'text-blue-600 font-semibold bg-blue-50 md:bg-gray-200'
                        )}
                    >
                        {Icon ? <Icon className="w-5 h-5 shrink-0 mb-1 md:mb-0 md:mr-2" /> : null}
                        <span className="text-xs md:text-sm md:ml-2 hidden md:inline">{label}</span>
                    </Link>
                )   
            })}
        </nav>
    )
}

export default SideBar