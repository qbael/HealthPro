"use client";
import React, {useState, useEffect} from 'react';
import {ChevronLeft, ChevronRight} from 'lucide-react';
import {DoctorAvailableSlot, TimeSlot} from "@/types/calendar-types";
import {SCHEDULE_API_URL} from "@/lib/utils";

interface AppointmentCalendarProps {
    doctorId: string;
    clinicSpecialtyId?: string;
    appointmentType?: 'AT_CLINIC' | 'AT_HOME' | 'ONLINE';
    onSlotSelect?: (date: string, startTime: string, endTime: string) => void;
}

const AppointmentCalendar: React.FC<AppointmentCalendarProps> = ({
                                                                     doctorId,
                                                                     clinicSpecialtyId,
                                                                     appointmentType = 'AT_CLINIC',
                                                                     onSlotSelect,
                                                                 }) => {
    const [currentDate, setCurrentDate] = useState(new Date());
    const [selectedDate, setSelectedDate] = useState<Date | null>(null);
    const [availableSlots, setAvailableSlots] = useState<DoctorAvailableSlot[]>([]);
    const [loading, setLoading] = useState(false);

    const daysOfWeek = ['CN', 'Hai', 'Ba', 'Tư', 'Năm', 'Sáu', 'Bảy'];
    const monthNames = [
        'THÁNG 1', 'THÁNG 2', 'THÁNG 3', 'THÁNG 4', 'THÁNG 5', 'THÁNG 6',
        'THÁNG 7', 'THÁNG 8', 'THÁNG 9', 'THÁNG 10', 'THÁNG 11', 'THÁNG 12'
    ];

    useEffect(() => {
        fetchAvailableSlots();
    }, [doctorId, clinicSpecialtyId, appointmentType]);

    const fetchAvailableSlots = async () => {
        setLoading(true);
        try {
            const response = await fetch(`${SCHEDULE_API_URL}/doctor-available-slots/750e8400-e29b-41d4-a716-446655440011`, {
                method: 'GET',
            });
            const data = await response.json();
            setAvailableSlots(data.data);
        } catch (error) {
            console.error('Error fetching available slots:', error);
        } finally {
            setLoading(false);
        }
    };

    const getDaysInMonth = () => {
        const year = currentDate.getFullYear();
        const month = currentDate.getMonth();
        const firstDay = new Date(year, month, 1);
        const lastDay = new Date(year, month + 1, 0);
        const daysInMonth = lastDay.getDate();
        const startingDayOfWeek = firstDay.getDay();

        const days: (Date | null)[] = [];

        for (let i = 0; i < startingDayOfWeek; i++) {
            days.push(null);
        }

        for (let day = 1; day <= daysInMonth; day++) {
            days.push(new Date(year, month, day));
        }

        return days;
    };

    const hasAvailableSlots = (date: Date): boolean => {
        const dateStr = new Intl.DateTimeFormat("en-CA").format(date);
        return availableSlots.some(slot => slot.appointmentDate === dateStr);
    };

    const isToday = (date: Date): boolean => {
        const today = new Date();
        return date.toDateString() === today.toDateString();
    };

    const isSunday = (date: Date): boolean => {
        return date.getDay() === 0;
    };

    const isPastDate = (date: Date): boolean => {
        const today = new Date();
        today.setHours(0, 0, 0, 0);
        return date < today;
    };

    const handlePreviousMonth = () => {
        setCurrentDate(new Date(currentDate.getFullYear(), currentDate.getMonth() - 1));
        setSelectedDate(null);
    };

    const handleNextMonth = () => {
        setCurrentDate(new Date(currentDate.getFullYear(), currentDate.getMonth() + 1));
        setSelectedDate(null);
    };

    const handleDateSelect = (date: Date) => {
        if (isPastDate(date) || !hasAvailableSlots(date) || loading) return;
        setSelectedDate(date);
    };

    const getTimeSlots = (): TimeSlot[] => {
        if (!selectedDate) return [];

        const dateStr = new Intl.DateTimeFormat("en-CA").format(selectedDate);
        const slots = availableSlots.filter(slot => slot.appointmentDate === dateStr);

        return slots.map(slot => ({
            startTime: slot.startTime.substring(0, 5),
            endTime: slot.endTime.substring(0, 5),
            available: true,
        }));
    };

    const groupSlotsByPeriod = () => {
        const timeSlots = getTimeSlots();
        const morning: TimeSlot[] = [];
        const afternoon: TimeSlot[] = [];
        const evening: TimeSlot[] = [];

        timeSlots.forEach(slot => {
            const hour = parseInt(slot.startTime.split(':')[0]);
            if (hour < 12) {
                morning.push(slot);
            } else if (hour < 17) {
                afternoon.push(slot);
            } else {
                evening.push(slot);
            }
        });

        return {morning, afternoon, evening};
    };

    const handleSlotClick = (slot: TimeSlot) => {
        if (!selectedDate || !onSlotSelect || loading) return;
        const dateStr = selectedDate.toISOString().split('T')[0];
        onSlotSelect(dateStr, slot.startTime, slot.endTime);
    };

    const days = getDaysInMonth();
    const {morning, afternoon, evening} = groupSlotsByPeriod();

    return (
        <div className="relative max-w-4xl mx-auto p-4 bg-white">
            {loading && (
                <div className="text-center py-12 w-full h-screen flex justify-center items-center">
                    <div
                        className="inline-block animate-spin rounded-full h-30 w-30 border-4 border-cyan-400 border-t-transparent">
                    </div>
                </div>
            )}

            <div className="bg-cyan-400 text-white text-center py-4 rounded-t-lg">
                <h2 className="text-2xl font-semibold">Vui lòng chọn ngày khám</h2>
            </div>

            <div className="flex items-center justify-center gap-4 py-6 border-b">
                <button
                    onClick={handlePreviousMonth}
                    disabled={loading}
                    className={`p-2 hover:bg-gray-100 rounded-full transition-colors disabled:opacity-50 disabled:cursor-not-allowed`}
                    aria-label="Previous month"
                >
                    <ChevronLeft className="w-6 h-6 text-gray-500"/>
                </button>
                <h3 className="text-2xl font-semibold text-cyan-400">
                    {monthNames[currentDate.getMonth()]}-{currentDate.getFullYear()}
                </h3>
                <button
                    onClick={handleNextMonth}
                    disabled={loading}
                    className={`p-2 hover:bg-gray-100 rounded-full transition-colors disabled:opacity-50 disabled:cursor-not-allowed`}
                    aria-label="Next month"
                >
                    <ChevronRight className="w-6 h-6 text-gray-500"/>
                </button>
            </div>

            <div className="py-4">
                <div className="grid grid-cols-7 gap-2 mb-2">
                    {daysOfWeek.map((day, index) => (
                        <div
                            key={day}
                            className={`text-center font-semibold py-2 ${
                                index === 0 ? 'text-orange-500' : index === 6 ? 'text-orange-400' : 'text-gray-700'
                            }`}
                        >
                            {day}
                        </div>
                    ))}
                </div>

                <div className="grid grid-cols-7 gap-2">
                    {days.map((date, index) => {
                        if (!date) {
                            return <div key={`empty-${index}`} className="aspect-square"/>;
                        }

                        const isSelected = selectedDate?.toDateString() === date.toDateString();
                        const hasSlots = hasAvailableSlots(date);
                        const isPast = isPastDate(date);
                        const isTodayDate = isToday(date);
                        const isSundayDate = isSunday(date);
                        console.log(date, hasSlots);

                        return (
                            <button
                                key={date.toISOString()}
                                onClick={() => handleDateSelect(date)}
                                disabled={isPast || !hasSlots || loading}
                                className={`aspect-square p-2 rounded-lg text-lg font-medium transition-all
                                          ${isSelected ? 'bg-cyan-400 text-white scale-105' : ''}
                                          ${!isSelected && hasSlots && !isPast ? 'hover:bg-cyan-50 text-gray-700' : ''}
                                          ${isPast || !hasSlots ? 'text-gray-300 cursor-not-allowed' : 'cursor-pointer'}
                                          ${isTodayDate && !isSelected ? 'border-2 border-cyan-400' : ''}
                                          ${isSundayDate && !isPast && hasSlots ? 'text-red-500' : ''}
                                          ${loading ? 'opacity-60 pointer-events-none' : ''}`}
                            >
                                {date.getDate()}
                            </button>
                        );
                    })}
                </div>
            </div>

            {selectedDate && (
                <div className="mt-6 border-t pt-6">
                    <div className="flex items-center justify-between mb-4">
                        <div className="h-1 flex-1 bg-cyan-400"/>
                        <span className="px-4 font-semibold text-gray-700">Đóng</span>
                    </div>

                    {morning.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi sáng</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {morning.map((slot, index) => (
                                    <button
                                        key={`morning-${index}`}
                                        onClick={() => handleSlotClick(slot)}
                                        disabled={loading}
                                        className="border-2 border-cyan-400 text-gray-700 py-3 px-4 rounded-lg
                                 hover:bg-cyan-400 hover:text-white transition-all font-medium text-base disabled:opacity-50 disabled:cursor-not-allowed"
                                    >
                                        {slot.startTime} - {slot.endTime}
                                    </button>
                                ))}
                            </div>
                        </div>
                    )}

                    {afternoon.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi chiều</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {afternoon.map((slot, index) => (
                                    <button
                                        key={`afternoon-${index}`}
                                        onClick={() => handleSlotClick(slot)}
                                        disabled={loading}
                                        className="border-2 border-cyan-400 text-gray-700 py-3 px-4 rounded-lg
                                 hover:bg-cyan-400 hover:text-white transition-all font-medium text-base disabled:opacity-50 disabled:cursor-not-allowed"
                                    >
                                        {slot.startTime} - {slot.endTime}
                                    </button>
                                ))}
                            </div>
                        </div>
                    )}

                    {evening.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi tối</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {evening.map((slot, index) => (
                                    <button
                                        key={`evening-${index}`}
                                        onClick={() => handleSlotClick(slot)}
                                        disabled={loading}
                                        className="border-2 border-cyan-400 text-gray-700 py-3 px-4 rounded-lg
                                 hover:bg-cyan-400 hover:text-white transition-all font-medium text-base disabled:opacity-50 disabled:cursor-not-allowed"
                                    >
                                        {slot.startTime} - {slot.endTime}
                                    </button>
                                ))}
                            </div>
                        </div>
                    )}

                    <p className="text-orange-400 text-sm mt-6">
                        Tất cả thời gian theo múi giờ Việt Nam GMT +7
                    </p>
                </div>
            )}
        </div>
    );
};

export default AppointmentCalendar;