import React from "react";
import {Clock, MapPin, Star} from "lucide-react";
import {ClinicsType} from "@/types/clinic-types";
import Image from "next/image";
import Link from "next/link";

interface ClinicBookingCardProps {
    clinic: ClinicsType;
}

export const ClinicBookingCard = ({clinic}: ClinicBookingCardProps) => {
    return (
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-4 mb-3">
            <div className="flex items-start gap-4">
                <Image
                    src={clinic.logoUrl}
                    alt={clinic.clinicName}
                    width={100}
                    height={100}
                    className="w-25 h-25 rounded-lg object-cover flex-shrink-0"
                />
                <div className="flex-1 min-w-0">
                    <h1 className="text-gray-800 font-semibold mb-1">
                        {clinic.clinicName}
                    </h1>
                    <div className="flex items-start gap-2 text-sm text-gray-600 mb-2">
                        <MapPin className="w-4 h-4 mt-0.5 flex-shrink-0"/>
                        <span className="line-clamp-1">{clinic.address}</span>
                    </div>
                    <p className="text-sm text-gray-600 mb-2 line-clamp-2 min-h-[2.5rem]">
                        {clinic.description}
                    </p>
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                        <Clock className="w-4 h-4 flex-shrink-0"/>
                        <span className="line-clamp-1">
                            T2-T6: {clinic.weekdayOpenHour} - {clinic.weekdayCloseHour} | T7-CN:{' '}
                            {clinic.weekendOpenHour} - {clinic.weekendCloseHour}
                        </span>
                    </div>
                    <div className="flex items-center gap-1 mt-2">
                        {[...Array(5)].map((_, i) => (
                            <Star key={i} className="w-4 h-4 fill-amber-400 text-amber-400"/>
                        ))}
                    </div>
                </div>
            </div>

            <div className="flex flex-col gap-2 mt-4">
                <Link href={`/clinics/${clinic.id}`}>
                    <button className="w-full bg-white hover:bg-gray-50 text-cyan-500 border border-cyan-500 px-5 py-2 rounded-lg font-medium text-sm transition-colors">
                        Xem chi tiết
                    </button>
                </Link>

                <Link href={`/clinics/${clinic.id}/specialties`}>
                    <button className="w-full bg-cyan-400 hover:bg-cyan-500 text-white px-5 py-2 rounded-lg font-medium text-sm transition-colors">
                        Đặt khám ngay
                    </button>
                </Link>
            </div>
        </div>
    );
};