"use client";
import React, {useState} from 'react';
import {ChevronLeft, ChevronRight} from "lucide-react";
import {ClinicsType} from "@/types/clinics-types";
import {ClinicCard} from "@/components/ClinicCard";
import Link from "next/link";

interface ClinicCarouselProps {
    clinics: ClinicsType[];
}

const ClinicCarousel = ({clinics}: ClinicCarouselProps) => {
    const [clinicIndex, setClinicIndex] = useState(0);
    const visibleClinics = 3;

    const totalSlides = Math.ceil(clinics.length / visibleClinics);
    const currentSlide = Math.floor(clinicIndex / visibleClinics);

    const nextClinics = () => {
        if (clinicIndex < clinics.length - visibleClinics) {
            setClinicIndex(clinicIndex + 3);
        }
    };

    const prevClinics = () => {
        if (clinicIndex > 0) {
            setClinicIndex(clinicIndex - 3);
        }
    };

    const goToSlide = (slideIndex: number) => {
        setClinicIndex(slideIndex * visibleClinics);
    };

    return (
        <div className="max-w-7xl mx-auto">
            <div className="flex justify-between items-center mb-6 px-5">
                <div>
                    <h2 className="text-2xl font-bold text-gray-900 mb-1">Đặt khám bệnh viện</h2>
                    <p className="text-gray-600">Đặt khám và thanh toán dễ có phiếu khám (thời gian, số thứ tự) trước
                        khi đi khám các bệnh viện kết nối chính thức với YouMed.</p>
                </div>
                <Link href="/booking/clinics">
                    <button
                        className="bg-cyan-400 hover:bg-blue-700 text-sm font-bold text-white px-3 mt-7 py-1 rounded-lg flex items-center gap-2 hover:cursor-pointer transition-colors">
                        Xem thêm <ChevronRight size={18}/>
                    </button>
                </Link>
            </div>

            <div className="relative">
                <button
                    onClick={prevClinics}
                    disabled={clinicIndex === 0}
                    className="absolute left-0 top-1/2 -translate-y-1/2 -translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                >
                    <ChevronLeft size={24}/>
                </button>

                <div className="overflow-hidden">
                    <div
                        className="flex gap-6 transition-transform duration-300"
                        style={{transform: `translateX(-${clinicIndex * (100 / visibleClinics)}%)`}}
                    >
                        {clinics.map((clinic) => (
                            <ClinicCard key={clinic.id} {...clinic} />
                        ))}
                    </div>
                </div>

                <button
                    onClick={nextClinics}
                    disabled={clinicIndex >= clinics.length - visibleClinics}
                    className="absolute right-0 top-1/2 -translate-y-1/2 translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                >
                    <ChevronRight size={24}/>
                </button>
            </div>
            {totalSlides > 1 && (
                <div className="flex justify-center gap-2 mt-6">
                    {Array.from({length: totalSlides}).map((_, index) => (
                        <button
                            key={index}
                            onClick={() => goToSlide(index)}
                            className={`h-2 rounded-full transition-all duration-300 hover:cursor-pointer ${
                                index === currentSlide
                                    ? 'w-8 bg-cyan-400'
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

export default ClinicCarousel;