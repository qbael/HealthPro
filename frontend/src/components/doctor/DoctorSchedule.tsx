"use client";

import React, { useState } from "react";
import dayjs from "dayjs";
import customParseFormat from "dayjs/plugin/customParseFormat";

dayjs.extend(customParseFormat);

interface Slot {
    id: string;
    doctorId: string;
    clinicSpecialtyId: string;
    startTime: string; // ISO string
    endTime: string;
    appointmentType: string;
}

interface SlotDay {
    appointmentDate: string; // e.g., "2025-10-23"
    slots: Slot[];
}

interface Props {
    schedule: SlotDay[];
}

const DoctorSchedule = ({ schedule }: Props) => {
    const [selectedDate, setSelectedDate] = useState<string | null>(null);

    const selectedSlots =
        schedule.find((s) => s.appointmentDate === selectedDate)?.slots || [];
    
    const morningSlots = selectedSlots.filter(
        (s) => dayjs(s.startTime, "HH:mm:ss").hour() < 12
    );

    const afternoonSlots = selectedSlots.filter(
        (s) => dayjs(s.startTime, "HH:mm:ss").hour() >= 12
    );

    return (
        <div>
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

            {/* Slot list */}
            {selectedDate ? (
                <div className="space-y-6">
                    {/* Morning */}
                    <div>
                        <h3 className="text-lg font-semibold text-gray-800 mb-2">
                            Buổi sáng
                        </h3>
                        {morningSlots.length > 0 ? (
                            <div className="flex gap-2 flex-wrap">
                                {morningSlots.map((s) => (
                                    <div
                                        key={s.id}
                                        className="px-3 py-2 bg-green-100 text-green-800 rounded-lg text-sm font-medium"
                                    >
                                        {dayjs(s.startTime, "HH:mm:ss").format("HH:mm")} -{" "}
                                        {dayjs(s.endTime, "HH:mm:ss").format("HH:mm")}
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <p className="text-gray-500 text-sm">Không có lịch buổi sáng</p>
                        )}
                    </div>

                    {/* Afternoon */}
                    <div>
                        <h3 className="text-lg font-semibold text-gray-800 mb-2">
                            Buổi chiều
                        </h3>
                        {afternoonSlots.length > 0 ? (
                            <div className="flex gap-2 flex-wrap">
                                {afternoonSlots.map((s) => (
                                    <div
                                        key={s.id}
                                        className="px-3 py-2 bg-yellow-100 text-yellow-800 rounded-lg text-sm font-medium"
                                    >
                                        {dayjs(s.startTime).format("HH:mm")} -{" "}
                                        {dayjs(s.endTime).format("HH:mm")}
                                    </div>
                                ))}
                            </div>
                        ) : (
                            <p className="text-gray-500 text-sm">Không có lịch buổi chiều</p>
                        )}
                    </div>
                </div>
            ) : (
                <p className="text-gray-500">Chọn ngày để xem lịch khám</p>
            )}
        </div>
    );
};

export default DoctorSchedule;