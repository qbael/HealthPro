'use client'

import {Button} from '@/components/ui/button'
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger,} from "@/components/ui/dialog"
import ScheduleForm from "@/components/doctor/ScheduleForm"
import api from "@/lib/axios";
import {useAuth} from "@/contexts/AuthContext";
import {useEffect, useState} from "react";
import {Calendar} from "@/components/calendar/Calendar";
import {useRouter} from "next/navigation";

const SchedulePage = ({initialTemplates}: any) => {
    const {user} = useAuth()
    const [initialSchedules, setInitialSchedules] = useState([])
    const [open, setOpen] = useState(false)
    const router = useRouter()

    useEffect(() => {
        if (!user) return
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
                <Dialog open={open} onOpenChange={() => setOpen(!open)}>
                    <DialogTrigger asChild>
                        <Button className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'
                                onClick={() => setOpen(!open)}
                        >
                            Đăng Ký Lịch Làm
                        </Button>
                    </DialogTrigger>
                    <DialogContent className="max-w-md max-h-[80vh] overflow-y-auto">
                        <DialogHeader>
                            <DialogTitle>Đăng Ký Lịch Làm</DialogTitle>
                        </DialogHeader>
                        <ScheduleForm
                            initialTemplates={initialTemplates}
                            onClose={() => {
                                setTimeout(() => {
                                    window.location.reload();
                                }, 1000);
                                setOpen(false)
                            }}
                        />
                    </DialogContent>
                </Dialog>
            </div>
            {user && initialSchedules && (
                <Calendar id={user.userRoleId} type={"DOCTOR"} slotClickEventType={'DELETE_DOCTOR_SLOT'}
                          availableDates={initialSchedules}/>
            )}
        </main>
    );
};

export default SchedulePage;