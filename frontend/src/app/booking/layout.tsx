'use client'

import React from "react"
import SideBar from "@/components/Sidebar"
import ImageSection from "@/components/ImageSection"
import { healthyNav } from "@/lib/navItem"

export default function BookingLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <div>
            <ImageSection />
            <SideBar navItems={healthyNav} />
  
      <main className="flex-1 p-4">{children}</main>
    </div>
  )
}
