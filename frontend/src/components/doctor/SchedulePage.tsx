'use client'

import {Button} from '@/components/ui/button'
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger,} from "@/components/ui/dialog"
import ScheduleForm from "@/components/doctor/ScheduleForm"
import DoctorSchedule from "@/components/doctor/DoctorSchedule";

const SchedulePage = ({ initialTemplate, initialSchedules } : any) => {
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
            <DoctorSchedule schedule={initialSchedules} />
        </main>
    );
};

export default SchedulePage;