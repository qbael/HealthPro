'use client'

import { cn } from "@/lib/utils"
import Link from "next/link"
import { usePathname } from "next/navigation"

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
        "w-full mb-5 bg-white border-t border-gray-200",
        "flex justify-center items-center gap-8 pt-5"
      )}
    >
      {navItems.map(({ label, href, icon: Icon }) => (
        <Link
          href={href}
          key={label}
          className={cn(
            "flex justify-center items-center w-70 gap-2 font-medium px-4 py-2 rounded-full border transition-all duration-300",
            pathname === href && "text-white bg-gradient-to-r from-[#00b5f1] to-[#00e0ff]"
          )}
        >
          {Icon && <Icon className="w-8 h-8" />}
          <span>{label}</span>
        </Link>
      ))}
    </nav>
  )
}

export default SideBar
