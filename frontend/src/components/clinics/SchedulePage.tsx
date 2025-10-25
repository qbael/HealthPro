'use client'

import {Button} from '@/components/ui/button'
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger,} from "@/components/ui/dialog"
import ScheduleForm from "@/components/clinics/ScheduleForm"

const SchedulePage = ({ initialTemplate, clinicSpecialtyId } : any) => {
    const days = [
        { label: "CN", color: "text-red-500" },
        { label: "Hai" },
        { label: "Ba" },
        { label: "Tư" },
        { label: "Năm" },
        { label: "Sáu" },
        { label: "Bảy", color: "text-orange-500" },
    ]

    return (
        <main>
            <section className='relative top-5 mx-auto w-[90%] max-w-[900px]'>
                <div className='flex justify-center gap-3'>
                    <h1 className='text-blue-400 text-2xl text-center font-bold mb-7'>Tháng 10-2025</h1>
                    <Dialog>
                        <DialogTrigger asChild>
                            <Button className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                                Đăng Ký Lịch Làm
                            </Button>
                        </DialogTrigger>
                        <DialogContent className="max-w-md">
                            <DialogHeader>
                                <DialogTitle>Đăng Ký Lịch Làm</DialogTitle>
                            </DialogHeader>
                            <ScheduleForm
                                template={initialTemplate}
                                clinicSpecialtyId={clinicSpecialtyId}
                            />
                        </DialogContent>
                    </Dialog>
                </div>

                <div className="grid grid-cols-7 text-center bg-white">
                    {days.map((day, i) => (
                        <div
                            key={i}
                            className={`py-4 font-semibold border border-gray-200 bg-gray-50 ${day.color || "text-gray-700"}`}
                        >
                            {day.label}
                        </div>
                    ))}
                </div>
            </section>
        </main>
    );
};

export default SchedulePage;