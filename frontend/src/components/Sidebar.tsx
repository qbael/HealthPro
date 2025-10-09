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
        "w-full bg-white shadow-sm border-t border-gray-200",
        "flex justify-center items-center gap-8 py-3"
      )}
    >
      {navItems.map(({ label, href, icon: Icon }) => (
        <Link
          href={href}
          key={label}
          className={cn(
            "flex items-center gap-2 text-gray-600 hover:text-blue-600 transition-colors font-medium",
            pathname === href && "text-blue-600 font-semibold border-b-2 border-blue-600 pb-1"
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
