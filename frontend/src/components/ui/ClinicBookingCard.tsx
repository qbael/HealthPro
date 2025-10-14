import React from "react";
import {Clock, MapPin, Star} from "lucide-react";
import {ClinicsType} from "@/types/clinics-types";
import Image from "next/image";

interface ClinicBookingCardProps {
    clinic: ClinicsType;
}

export const ClinicBookingCard = ({clinic}: ClinicBookingCardProps) => {
    return (
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-4 mb-3">
            <div className="flex items-center gap-4">
                <Image
                    src={clinic.logoUrl}
                    alt={clinic.clinicName}
                    width={100}
                    height={100}
                    className="w-25 h-25 rounded-lg object-cover"
                />
                <div className="flex-1">
                    <h1 className="text-gray-800 font-semibold mb-1">
                        {clinic.clinicName}
                    </h1>
                    <div className="flex items-start gap-2 text-sm text-gray-600 mb-2">
                        <MapPin className="w-4 h-4 mt-0.5 flex-shrink-0"/>
                        <span>{clinic.address}</span>
                    </div>
                    <p className="text-sm text-gray-600 mb-2">{clinic.description}</p>
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                        <Clock className="w-4 h-4"/>
                        <span>T2-T6: {clinic.weekdayOpenHour} - {clinic.weekdayCloseHour} | T7-CN:{' '}
                            {clinic.weekendOpenHour} - {clinic.weekendCloseHour}
                        </span>
                    </div>
                    <div className="flex items-center gap-1 mt-2">
                        {[...Array(5)].map((_, i) => (
                            <Star key={i} className="w-4 h-4 fill-amber-400 text-amber-400"/>
                        ))}
                    </div>
                </div>
                <div className="flex flex-col gap-2">
                    <button
                        className="bg-white hover:bg-gray-50 text-cyan-500 border border-cyan-500 px-5 py-2 rounded-lg font-medium text-sm">
                        Xem chi tiết
                    </button>
                    <button
                        className="bg-cyan-400 hover:bg-cyan-500 text-white px-5 py-2 rounded-lg font-medium text-sm">
                        Đặt khám ngay
                    </button>
                </div>
            </div>
        </div>
    );
};