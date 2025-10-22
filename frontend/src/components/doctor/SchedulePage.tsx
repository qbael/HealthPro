'use client'

import { useState } from "react"
import { Button } from '@/components/ui/button'
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"
import ScheduleForm from "@/components/doctor/ScheduleForm"
import api from "@/lib/axios"
import DoctorSchedule from "@/components/doctor/DoctorSchedule";

const SchedulePage = ({ initialTemplate, initialSchedules } : any) => {
    const [schedules, setSchedules] = useState(initialSchedules)

    const fetchSchedule = async () => {
        const res = await api.get('v1/ok')
        setSchedules(res.data)
    }

    const sampleData = [
        {
            appointmentDate: "2025-10-23",
            slots: [
                {
                    id: "1",
                    doctorId: "abc",
                    clinicSpecialtyId: "xyz",
                    startTime: "2025-10-23T08:00:00",
                    endTime: "2025-10-23T09:00:00",
                    appointmentType: "OFFLINE",
                },
                {
                    id: "2",
                    doctorId: "abc",
                    clinicSpecialtyId: "xyz",
                    startTime: "2025-10-23T14:00:00",
                    endTime: "2025-10-23T15:00:00",
                    appointmentType: "ONLINE",
                },
            ],
        },
        {
            appointmentDate: "2025-10-24",
            slots: [
                {
                    id: "3",
                    doctorId: "abc",
                    clinicSpecialtyId: "xyz",
                    startTime: "2025-10-24T09:30:00",
                    endTime: "2025-10-24T10:30:00",
                    appointmentType: "OFFLINE",
                },
            ],
        },
    ];

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
                                fetchSchedule={fetchSchedule}
                            />
                        </DialogContent>
                    </Dialog>
                </div>


                {/*<div>*/}
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


                    {/*<div className="grid grid-cols-7 text-center bg-white">*/}
                    {/*    {days.map((day, i) => (*/}
                    {/*        <div*/}
                    {/*            key={i}*/}
                    {/*            className={`py-6 font-semibold border border-gray-200 border-t-0 bg-white ${day.color || "text-gray-700"}`}*/}
                    {/*        >*/}
                    {/*            <span className='p-2 rounded-md hover:cursor-pointer hover:text-white hover:bg-blue-400'>{day.label}</span>*/}

                    {/*        </div>*/}
                    {/*    ))}*/}
                    {/*</div>*/}

                <DoctorSchedule schedule={schedules} />
            </section>
        </main>
    );
};

export default SchedulePage;