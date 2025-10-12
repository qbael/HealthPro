"use client";
import React, { useState } from 'react';
import { ChevronLeft, ChevronRight } from "lucide-react";
import DoctorCard from "@/components/layout/DoctorCard";
import { DoctorType } from "@/types/doctor-types";

interface DoctorCarouselProps {
    doctors: DoctorType[];
}

const DoctorCarousel = ({ doctors }: DoctorCarouselProps) => {
    const [doctorIndex, setDoctorIndex] = useState(0);
    const visibleDoctors = 4;

    const totalSlides = Math.ceil(doctors.length / visibleDoctors);
    const currentSlide = Math.floor(doctorIndex / visibleDoctors);

    const nextDoctors = () => {
        if (doctorIndex < doctors.length - visibleDoctors) {
            setDoctorIndex(doctorIndex + 4);
        }
    };

    const prevDoctors = () => {
        if (doctorIndex > 0) {
            setDoctorIndex(doctorIndex - 4);
        }
    };

    const goToSlide = (slideIndex: number) => {
        setDoctorIndex(slideIndex * visibleDoctors);
    };

    return (
        <div className="max-w-7xl mx-auto mb-12">
            <div className="flex justify-between items-center mb-6 px-5">
                <div>
                    <h2 className="text-2xl font-bold text-gray-900 mb-1">Đặt khám bác sĩ</h2>
                    <p className="text-gray-600">Phiếu khám kèm số thứ tự và thời gian của bạn được xác nhận.</p>
                </div>
                <button
                    className="bg-blue-500 hover:bg-blue-700 text-sm font-bold text-white px-3 mt-7 py-1 rounded-lg flex items-center gap-2 transition-colors">
                    Xem thêm <ChevronRight size={18}/>
                </button>
            </div>

            <div className="relative">
                <button
                    onClick={prevDoctors}
                    disabled={doctorIndex === 0}
                    className="absolute left-0 top-1/2 -translate-y-1/2 -translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                >
                    <ChevronLeft size={24}/>
                </button>

                <div className="overflow-hidden">
                    <div
                        className="flex mx-1 gap-5 transition-transform duration-300"
                        style={{transform: `translateX(-${doctorIndex * (100 / visibleDoctors)}%)`}}
                    >
                        {doctors.map((doctor) => (
                            <DoctorCard key={doctor.id} {...doctor} />
                        ))}
                    </div>
                </div>

                <button
                    onClick={nextDoctors}
                    disabled={doctorIndex >= doctors.length - visibleDoctors}
                    className="absolute right-0 top-1/2 -translate-y-1/2 translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                >
                    <ChevronRight size={24}/>
                </button>
            </div>

            {totalSlides > 1 && (
                <div className="flex justify-center gap-2 mt-6">
                    {Array.from({ length: totalSlides }).map((_, index) => (
                        <button
                            key={index}
                            onClick={() => goToSlide(index)}
                            className={`h-2 rounded-full transition-all duration-300 hover:cursor-pointer ${
                                index === currentSlide
                                    ? 'w-8 bg-blue-500'
                                    : 'w-2 bg-gray-300 hover:bg-gray-400'
                            }`}
                            aria-label={`Go to slide ${index + 1}`}
                        />
                    ))}
                </div>
            )}
        </div>
    );
};

export default DoctorCarousel;