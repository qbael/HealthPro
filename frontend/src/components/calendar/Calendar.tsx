"use client";
import React, {useEffect, useRef, useState} from "react";
import {TimeSlotType} from "@/types/calendar-types";
import {SCHEDULE_API_URL} from "@/lib/utils";
import {ChevronLeft, ChevronRight} from "lucide-react";
import {useAuth} from "@/contexts/AuthContext";
import {useRouter} from "next/navigation";
import api from "@/lib/axios";
import {SlotButton} from "@/components/calendar/SlotButton";
import ModalConfirmAppointment from "@/components/calendar/ModalConfirmAppointment";
import ModalDeleteSlot from "@/components/calendar/ModalDeleteSlot";

interface CalendarProps {
    id: string;
    type: 'DOCTOR' | 'CLINIC';
    slotClickEventType:  'MAKE_APPOINTMENT' | 'DELETE_DOCTOR_SLOT' | 'DELETE_CLINIC_SLOT' | 'SHOW_DOCTOR_APPOINTMENT' | 'SHOW_CLINIC_APPOINTMENT';
    availableDates: string[];
}

export const Calendar = ({id, type, slotClickEventType, availableDates}: CalendarProps) => {
    if (availableDates.length === 0) {
        return (
            <div
                className="text-center py-12 w-full h-screen flex justify-center items-center text-gray-500">
                Không có ngày khám khả dụng.
            </div>
        );
    }
    const [currentDate, setCurrentDate] = useState(new Date());
    const [selectedDate, setSelectedDate] = useState<Date | null>(null);
    const [availableSlots, setAvailableSlots] = useState<TimeSlotType[]>([]);
    const [dates, setDates] = useState<string[]>(availableDates);
    const pageType = useRef<'DOCTOR' | 'CLINIC'>(type);
    const bottomRef = useRef<HTMLDivElement | null>(null);
    const [loading, setLoading] = useState(false);
    const [selectedSlot, setSelectedSlot] = useState<TimeSlotType | null>(null);
    const {user} = useAuth();
    const router = useRouter();
    const daysOfWeek = ['CN', 'Hai', 'Ba', 'Tư', 'Năm', 'Sáu', 'Bảy'];
    const monthNames = [
        'THÁNG 1', 'THÁNG 2', 'THÁNG 3', 'THÁNG 4', 'THÁNG 5', 'THÁNG 6',
        'THÁNG 7', 'THÁNG 8', 'THÁNG 9', 'THÁNG 10', 'THÁNG 11', 'THÁNG 12'
    ];

    useEffect(() => {
        bottomRef.current?.scrollIntoView({behavior: "smooth"});
    }, [availableSlots]);


    useEffect(() => {
        window.scrollTo({top: 0, behavior: 'smooth'});
    }, []);

    useEffect(() => {
        if (!selectedDate) return
        setAvailableSlots([])
        fetchAvailableSlots(selectedDate)
    }, [selectedDate]);

    const fetchAvailableSlots = async (date: Date) => {
        setLoading(true);
        const dateStr = new Intl.DateTimeFormat("en-CA").format(date);
        let url = '';
        if (pageType.current === 'DOCTOR') {
            url = `${SCHEDULE_API_URL}/doctor/available-slots/${id}`;
        } else if (pageType.current === 'CLINIC') {
            url = `${SCHEDULE_API_URL}/clinic-specialty/available-slots/${id}`;
        }
        url += `?date=${dateStr}`;
        try {
            const response = await fetch(url, {
                method: 'GET',
            });
            if (!response.ok) {
                throw new Error('Failed to fetch available slots');
            }
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
        return dates.some(date => date === dateStr);
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

    const getTimeSlots = () => {
        if (!selectedDate) return [];

        return availableSlots.map(slot => ({
            id: slot.id,
            appointmentDate: slot.appointmentDate,
            startTime: slot.startTime.substring(0, 5),
            endTime: slot.endTime.substring(0, 5),
        }));
    };

    const groupSlotsByPeriod = () => {
        const timeSlots = getTimeSlots();
        const morning: TimeSlotType[] = [];
        const afternoon: TimeSlotType[] = [];
        const evening: TimeSlotType[] = [];

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

    const days = getDaysInMonth();
    const {morning, afternoon, evening} = groupSlotsByPeriod();

    const handleSlotClick = async (slot: TimeSlotType) => {
        if (!user) {
            router.push("/login");
            return
        }
        switch (slotClickEventType) {
            case 'MAKE_APPOINTMENT':
                await handleMakeAppointment(slot);
                break;
            case 'DELETE_DOCTOR_SLOT':
                handleSlotDelete(slot);
                break;
        }
    }

    const handleMakeAppointment = async (slot: TimeSlotType) => {
        try {
            const res = await api.get('v1/profile/is-fully-registered')
            const body = res.data
            if (!body.isFullyRegistered) {
                router.push("/profile");
            } else {
                setSelectedSlot(slot)
            }
        } catch (error) {
            console.error('Failed to fetch profile:', error)
        }
    }

    const handleSlotDelete = (slot: TimeSlotType) => {
        setSelectedSlot(slot)
    }

    const onCloseModal = () => {
        setSelectedSlot(null)
        router.refresh()
    }

    return (
        <div className="relative max-w-3xl mx-auto p-4 bg-white">
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

                        return (
                            <button
                                key={date.toISOString()}
                                onClick={() => handleDateSelect(date)}
                                disabled={isPast || !hasSlots || loading}
                                className={`aspect-square p-2 rounded-lg text-lg font-medium transition-all
                                          ${isSelected ? 'bg-cyan-400 text-white scale-105' : ''}
                                          ${!isSelected && hasSlots && !isPast ? 'hover:bg-cyan-50 text-gray-700' : ''}
                                          ${isPast || !hasSlots ? 'text-gray-300 cursor-not-allowed' : 'cursor-pointer'}
                                          ${isTodayDate && !isSelected ? 'border-2 border-cyan-400 text-red-500' : ''}
                                          ${isSundayDate && !isPast && hasSlots ? 'text-red-500' : ''}
                                          ${loading ? 'opacity-60 pointer-events-none' : ''}`}
                            >
                                {date.getDate()}
                            </button>
                        );
                    })}
                </div>
            </div>

            {selectedDate && availableSlots?.length > 0 && (
                <div className="mt-6 border-t pt-6">
                    <div className="flex items-center justify-between mb-4">
                        <div className="h-1 flex-1 bg-cyan-400"/>
                        <span className="px-4 font-semibold text-gray-700 hover:cursor-pointer"
                              onClick={() => setSelectedDate(null)}>
                            Đóng
                        </span>
                    </div>

                    {morning.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi sáng</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {morning.map((slot) => (
                                    <SlotButton slot={slot} handleSlotClick={handleSlotClick} loading={loading}
                                                key={slot.id}/>
                                ))}
                            </div>
                        </div>
                    )}

                    {afternoon.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi chiều</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {afternoon.map((slot) => (
                                    <SlotButton slot={slot} handleSlotClick={handleSlotClick} loading={loading}
                                                key={slot.id}/>
                                ))}
                            </div>
                        </div>
                    )}

                    {evening.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi tối</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {evening.map((slot) => (
                                    <SlotButton slot={slot} handleSlotClick={handleSlotClick} loading={loading}
                                                key={slot.id}/>
                                ))}
                            </div>
                        </div>
                    )}

                    <p className="text-orange-400 text-sm mt-6" ref={bottomRef}>
                        Tất cả thời gian theo múi giờ Việt Nam GMT +7
                    </p>
                </div>
            )}

            {selectedSlot && slotClickEventType === 'MAKE_APPOINTMENT' && (
                <ModalConfirmAppointment
                    onClose={() => onCloseModal()}
                    slot={selectedSlot}
                    patientId={user.userRoleId}
                    appointmentType={pageType.current}
                    doctorId={pageType.current === 'DOCTOR' ? id : null}
                    clinicSpecialtyId={pageType.current === 'CLINIC' ? id : null}
                />
            )}

            {selectedSlot && slotClickEventType === 'DELETE_DOCTOR_SLOT' && (
                <ModalDeleteSlot
                    slot={selectedSlot}
                    onClose={() => onCloseModal()}
                />
            )}

        </div>
    );
};
