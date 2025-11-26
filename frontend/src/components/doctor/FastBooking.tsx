"use client"
import React, {useEffect, useRef, useState} from 'react';
import {ChevronLeft, ChevronRight} from 'lucide-react';
import {TimeSlotType} from "@/types/calendar-types";
import {SCHEDULE_API_URL} from "@/lib/utils";
import {SlotButton} from "@/components/calendar/SlotButton";
import api from "@/lib/axios";
import {useAuth} from "@/contexts/AuthContext";
import {useRouter} from "next/navigation";
import ModalConfirmAppointment from "@/components/calendar/ModalConfirmAppointment";


interface DateDisplay {
    dayOfWeek: string;
    day: string;
    month: string;
}

interface FastBookingProps {
    id: string;
    dates: { [date: string]: number };
}

const FastBooking = ({id, dates}: FastBookingProps) => {

    const [availabilityData, setAvailabilityData] = useState<{ [date: string]: number }>(dates);
    const [selectedDate, setSelectedDate] = useState<string | null>(null);
    const [availableSlots, setAvailableSlots] = useState<TimeSlotType[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const scrollContainerRef = useRef<HTMLDivElement>(null);
    const [selectedSlot, setSelectedSlot] = useState<TimeSlotType | null>(null);
    const {user} = useAuth();
    const router = useRouter();

    const formatDate = (dateStr: string): DateDisplay => {
        const date = new Date(dateStr);
        const days = ['CN', 'Th 2', 'Th 3', 'Th 4', 'Th 5', 'Th 6', 'Th 7'];
        const dayOfWeek = days[date.getDay()];
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        return { dayOfWeek, day, month };
    };

    useEffect(() => {
        if (!selectedDate) return
        setAvailableSlots([])
        const dateObj = new Date(selectedDate);
        fetchAvailableSlots(dateObj);
    }, [selectedDate]);

    const fetchAvailableSlots = async (date: Date) => {
        setLoading(true);
        const dateStr = new Intl.DateTimeFormat("en-CA").format(date);
        let url = `${SCHEDULE_API_URL}/doctor/available-slots/${id}`;
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

    const {morning, afternoon, evening} = groupSlotsByPeriod();

    const scroll = (direction: 'left' | 'right'): void => {
        if (scrollContainerRef.current) {
            const scrollAmount = 200;
            scrollContainerRef.current.scrollBy({
                left: direction === 'left' ? -scrollAmount : scrollAmount,
                behavior: 'smooth'
            });
        }
    };

    const handleSlotClick = async (slot: TimeSlotType) => {
        if (!user){
            router.push("/login");
            return
        }
        try {
            const res = await api.get('v1/profile/is-fully-registered')
            const body = res.data
            if (!body.isFullyRegistered){
                router.push("/profile");
            } else {
                setSelectedSlot(slot)
            }
        }
        catch (error) {
            console.error('Failed to fetch profile:', error)
        }
    }

    return (
        <div className="w-full mx-auto p-6 bg-white">
            <h2 className="text-2xl font-bold mb-6">Đặt khám nhanh</h2>

            <div className="relative mb-8">
                <button
                    onClick={() => scroll('left')}
                    className="absolute left-0 top-1/2 -translate-y-1/2 z-10 bg-white rounded-full shadow-lg p-2 hover:bg-gray-50 transition-colors"
                    aria-label="Cuộn sang trái"
                >
                    <ChevronLeft className="w-6 h-6" />
                </button>

                <div
                    ref={scrollContainerRef}
                    className="flex gap-4 overflow-x-auto scrollbar-hide px-12 scroll-smooth"
                    style={{ scrollbarWidth: 'none', msOverflowStyle: 'none' }}
                >
                    {Object.keys(availabilityData).map((dateStr) => {
                        const { dayOfWeek, day, month } = formatDate(dateStr);
                        // const isFull = isDateFull(dateStr);
                        const isSelected = selectedDate === dateStr;
                        const availableSlots = availabilityData[dateStr];

                        return (
                            <button
                                key={dateStr}
                                onClick={() => setSelectedDate(dateStr)}
                                className={`flex-shrink-0 w-40 p-4 rounded-lg border-2 transition-all ${
                                    isSelected ? 'border-blue-500 bg-blue-50' : 'border-gray-200 hover:border-blue-300 bg-white'
                                }`}
                                aria-label={`Chọn ngày ${day}/${month}`}
                                aria-pressed={isSelected}
                            >
                                <div className="text-center">
                                    <div className="font-semibold text-lg mb-1">
                                        {dayOfWeek}, {day}-{month}
                                    </div>
                                    <div
                                        className={'text-sm font-medium text-green-500'}
                                    >
                                        {availableSlots} khung giờ
                                    </div>
                                </div>
                            </button>
                        );
                    })}
                </div>

                <button
                    onClick={() => scroll('right')}
                    className="absolute right-0 top-1/2 -translate-y-1/2 z-10 bg-white rounded-full shadow-lg p-2 hover:bg-gray-50 transition-colors"
                    aria-label="Cuộn sang phải"
                >
                    <ChevronRight className="w-6 h-6" />
                </button>
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
                                    <SlotButton slot={slot} handleSlotClick={handleSlotClick} loading={loading} key={slot.id}/>
                                ))}
                            </div>
                        </div>
                    )}

                    {afternoon.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi chiều</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {afternoon.map((slot) => (
                                    <SlotButton slot={slot} handleSlotClick={handleSlotClick} loading={loading} key={slot.id}/>
                                ))}
                            </div>
                        </div>
                    )}

                    {evening.length > 0 && (
                        <div className="mb-6">
                            <h3 className="text-3xl font-bold mb-4 text-gray-800">Buổi tối</h3>
                            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-5 gap-3">
                                {evening.map((slot) => (
                                    <SlotButton slot={slot} handleSlotClick={handleSlotClick} loading={loading} key={slot.id}/>
                                ))}
                            </div>
                        </div>
                    )}
                </div>
            )}
            {selectedSlot && (
                <ModalConfirmAppointment
                    onClose={() => setSelectedSlot(null)}
                    slot={selectedSlot}
                    userId={user.id}
                    appointmentType={'DOCTOR'}
                    doctorId={id}
                    clinicSpecialtyId={null}
                />
            )}
        </div>
    );
};

export default FastBooking;