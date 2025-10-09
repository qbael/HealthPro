'use client'

import React from "react"
import { usePathname } from "next/navigation"
import SideBar from "@/components/Sidebar"
import ImageSection from "@/components/ImageSection"
import { healthyNav } from "@/lib/navItem"

export default function BookingLayout({
  children,
}: {
  children: React.ReactNode
}) {
  const pathname = usePathname()
  // const isRootBooking = pathname === "/booking"

  return (
    <div className="flex flex-col min-h-screen">
      <ImageSection />

      <SideBar navItems={healthyNav} />

      <main className="flex-1 p-4">{children}</main>
    </div>
  )
}