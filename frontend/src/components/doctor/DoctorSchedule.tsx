"use client";

import React, { useState } from "react";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";

dayjs.extend(customParseFormat);

interface Slot {
    id: string;
    doctorId: string;
    clinicSpecialtyId: string;
    startTime: string;
    endTime: string;
    appointmentType: string;
}

interface SlotDay {
    appointmentDate: string;
    slots: Slot[];
}

interface Props {
    schedule: SlotDay[];
}

const DoctorSchedule = ({ schedule }: Props) => {
    const days = [
        { label: "CN", color: "text-red-500" },
        { label: "Hai" },
        { label: "Ba" },
        { label: "Tư" },
        { label: "Năm" },
        { label: "Sáu" },
        { label: "Bảy", color: "text-orange-500" },
    ]

    const [selectedDate, setSelectedDate] = useState<string | null>(null);

    const selectedSlots =
        schedule.find((s) => s.appointmentDate === selectedDate)?.slots || [];

    const morningSlots = selectedSlots.filter(
        (s) => dayjs(s.startTime, "HH:mm:ss").hour() < 12
    );

    const afternoonSlots = selectedSlots.filter(
        (s) => dayjs(s.startTime, "HH:mm:ss").hour() >= 12
    );


    return schedule && schedule.length > 0 ? (
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
                {schedule.map(s => (
                    <div
                        key={s.appointmentDate}
                        onClick={() => setSelectedDate(s.appointmentDate)}
                        className={`py-6 font-semibold border border-gray-200 border-t-0 bg-white text-gray-700`}
                    >
                        <span className='p-2 rounded-md hover:cursor-pointer hover:text-white hover:bg-blue-400'>
                            {dayjs(s.appointmentDate).format("DD/MM/YYYY")}
                        </span>

                    </div>
                ))}
            </div>
            <div className='flex gap-1 items-center mt-5'>
                <div className="bg-blue-500 w-[90%] h-[1px]"></div>
                <div className=" hover:cursor-pointer text-right font-semibold py-2 px-4 text-sm" onClick={() => setSelectedDate(null)}>
                    Đóng
                </div>
            </div>
            {selectedDate ? (
                <section className='relative top-10 mx-auto w-[90%] max-w-[800px]'>
                    <div>
                        <h1 className='text-4xl font-semibold mb-5'>Buổi sáng</h1>
                        <div className='grid grid-cols-5 gap-3'>
                            {morningSlots.map((s) => (
                                <div
                                    key={s.id}
                                    className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center
                                hover:cursor-pointer hover:text-white hover:bg-blue-400'>
                                    {dayjs(s.startTime, "HH:mm:ss").format("HH:mm")} -{" "}
                                    {dayjs(s.endTime, "HH:mm:ss").format("HH:mm")}
                                </div>
                            ))}
                        </div>
                    </div>

                    <div>
                        <h1 className='text-4xl font-semibold mt-10 mb-5'>Buổi chiều</h1>
                        {afternoonSlots.length > 0 ? (
                            <div className='grid grid-cols-5 gap-3'>
                                {afternoonSlots.map((s) => (
                                    <div
                                        key={s.id}
                                        className='border border-blue-300 rounded-xs p-3 font-semibold text-xl text-center'>
                                        {dayjs(s.startTime, "HH:mm:ss").format("HH:mm")} -{" "}
                                        {dayjs(s.endTime, "HH:mm:ss").format("HH:mm")}
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <p className="text-gray-500 text-sm">Không có lịch buổi chiều</p>
                        )}
                    </div>
                </section>
            ) : (
                <p className="text-gray-500">Chọn ngày để xem lịch làm</p>
            )}
        </div>
    ) : (
        <div className='text-center'>Chưa đăng ký lịch làm</div>
    )
};

export default DoctorSchedule;