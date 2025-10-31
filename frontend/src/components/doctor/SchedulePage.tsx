'use client'

import {Button} from '@/components/ui/button'
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger,} from "@/components/ui/dialog"
import ScheduleForm from "@/components/doctor/ScheduleForm"
import api from "@/lib/axios";
import {useAuth} from "@/contexts/AuthContext";
import {useEffect, useState} from "react";
import {Calendar} from "@/components/calendar/Calendar";

const SchedulePage = ({ initialTemplate } : any) => {
    const { user } = useAuth()
    const [initialSchedules, setInitialSchedules] = useState([])

    useEffect(() => {
        if (!user?.userRoleId) return

        const fetchSchedules = async () => {
            try {
                const res = await api.get(`/v3/schedule/doctor/available-dates/${user.userRoleId}`)
                setInitialSchedules(res.data.data)
            } catch (error) {
                console.error('Failed to fetch schedules:', error)
            }
        }

        fetchSchedules()
    }, [user])

    return (
        <main className='relative top-5 mx-auto w-[90%] max-w-[900px]'>
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
                        />
                    </DialogContent>
                </Dialog>
            </div>
            <Calendar id={user?.userRoleId} type={"DOCTOR"} availableDates={initialSchedules} />
        </main>
    );
};

export default SchedulePage;