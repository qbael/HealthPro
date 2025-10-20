import { Button } from '@/components/ui/button'
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"
import ScheduleForm from "@/components/doctor/ScheduleForm";

const Page = () => {
    const days = [
        { label: "CN", color: "text-red-500" },
        { label: "Hai" },
        { label: "Ba" },
        { label: "Tư" },
        { label: "Năm" },
        { label: "Sáu" },
        { label: "Bảy", color: "text-orange-500" },
    ]

    const fetchSchedule = async () => {
        return
    }

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
                                fetchSchedule={fetchSchedule}
                            />
                        </DialogContent>
                    </Dialog>
                </div>
                <div>
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

                    <div className="grid grid-cols-7 text-center bg-white">
                        {days.map((day, i) => (
                            <div
                                key={i}
                                className={`py-6 font-semibold border border-gray-200 border-t-0 bg-white ${day.color || "text-gray-700"}`}
                            >
                                <span className='p-2 rounded-md hover:cursor-pointer hover:text-white hover:bg-blue-400'>{day.label}</span>

                            </div>
                        ))}
                    </div>

                    <div className='flex gap-1 items-center mt-5'>
                        <div className="bg-blue-500 w-[90%] h-[1px]"></div>
                        <div className="text-right font-semibold py-2 px-4 text-sm">
                            Đóng
                        </div>
                    </div>
                </div>
            </section>

            <section className='relative top-10 mx-auto w-[90%] max-w-[800px]'>
                <div>
                    <h1 className='text-4xl font-semibold mb-5'>Buổi sáng</h1>
                    <div className='grid grid-cols-5 gap-3'>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center
                        hover:cursor-pointer hover:text-white hover:bg-blue-400'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                    </div>
                </div>

                <div>
                    <h1 className='text-4xl font-semibold mt-10 mb-5'>Buổi chiều</h1>
                    <div className='grid grid-cols-5 gap-3'>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                        <div className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                            06:00 - 07:00
                        </div>
                    </div>
                </div>
            </section>
        </main>
    );
};

export default Page;