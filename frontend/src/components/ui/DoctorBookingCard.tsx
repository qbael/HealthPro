import React from "react";
import Image from "next/image";
import {MapPin} from "lucide-react";
import {DoctorType} from "@/types/doctor-types";

interface DoctorBookingCardProps {
    doctor: DoctorType;
}

export const DoctorBookingCard = ( {doctor}: DoctorBookingCardProps ) => {
    const specialties = doctor.doctorSpecialties
        .map(ds => ds.specialty.name)
        .join(' • ');

    return (
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-4 mb-3">
            <div className="flex gap-4">
                <Image
                    src={doctor.avatarUrl || '/api/placeholder/80/80'}
                    alt={doctor.fullName}
                    width={100}
                    height={100}
                    className="w-16 h-16 rounded-lg object-cover"
                />
                <div className="flex-1">
                    <h3 className="text-cyan-500 font-semibold text-lg mb-1">
                        {doctor.fullName}
                    </h3>
                    <p className="text-sm text-gray-700 mb-1">
                        <span className="font-medium">Chuyên trị:</span> {specialties}
                    </p>
                    <p className="text-sm text-gray-600 mb-2">{doctor.bio}</p>
                    <div className="flex items-start gap-2 text-sm text-gray-600">
                        <MapPin className="w-4 h-4 mt-0.5 flex-shrink-0" />
                        <span>{doctor.address}</span>
                    </div>
                </div>
                <button className="bg-cyan-400 hover:bg-cyan-500 text-white px-6 py-2 rounded-lg font-medium h-fit">
                    Đặt ngay
                </button>
            </div>
        </div>
    );
};