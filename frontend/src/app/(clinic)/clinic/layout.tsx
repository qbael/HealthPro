'use client'
import SideBar from '@/components/Sidebar1'
import { specialtiesNav } from "@/lib/navItem"
import { useState } from 'react';

interface ClinicLayoutProps {
    children: React.ReactNode; 
}

export default async function ClinicLayoutProps({children} : ClinicLayoutProps) {
    // const role
    const [open, setOpen] = useState(false)
    
    return (
        <div>
            <SideBar navItems={specialtiesNav} isOpen={open} onClose={() => setOpen(false)} />
        
                {open && (
                <div
                className="fixed inset-0 bg-transparent bg-opacity-50 z-30 md:hidden"
                onClick={() => setOpen(false)}
                />
            )}

            <button
                onClick={() => setOpen(!open)}
                className="fixed bottom-4 left-4 z-60 bg-gray-800 text-white p-3 rounded-full shadow-lg md:hidden"
            >
                <svg
                xmlns="http://www.w3.org/2000/svg"
                className="h-6 w-6"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                strokeWidth={2}
                >
                <path strokeLinecap="round" strokeLinejoin="round" d="M4 6h16M4 12h16M4 18h16" />
                </svg>
            </button>

            <main className="ml-0 md:ml-[16%] w-[calc(100%-16%)] min-h-screen mt-16 flex flex-col gap-6 bg-gray-50 p-4">
                {children}
            </main>
        </div>
    )
}