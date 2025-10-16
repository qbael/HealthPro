"use client";

import React from "react";
import Image from "next/image";
import Link from "next/link";
import { MapPin } from "lucide-react";
import { DoctorType } from "@/types/doctor-types";

interface DoctorBookingCardProps {
    doctor: DoctorType;
}

export const DoctorBookingCard = ({ doctor }: DoctorBookingCardProps) => {
    const specialties = doctor.doctorSpecialties
        .map(ds => ds.specialty.name)
        .join(" • ");

    return (
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-5 mb-3 max-w-3/4">
            <div className="flex gap-4 items-center">
                <Image
                    src={doctor.avatarUrl}
                    alt={doctor.fullName}
                    width={100}
                    height={100}
                    className="w-30 h-30 rounded-lg object-cover"
                />
                <div className="flex-1">
                    <h3 className="text-cyan-500 font-semibold text-lg mb-1">
                        {doctor.fullName}
                    </h3>
                    <p className="text-sm text-gray-700 mb-1">
                        <span className="font-medium">Chuyên trị:</span> {specialties}
                    </p>
                    <p className="text-sm font-medium text-gray-600 mb-2">{doctor.bio}</p>
                    <div className="flex items-start gap-2 text-sm text-gray-600">
                        <MapPin className="w-4 h-4 mt-0.5 flex-shrink-0" />
                        <span>{doctor.address}</span>
                    </div>
                </div>
            </div>

            <div className="flex flex-col gap-2 mt-4 h-full">
                <Link href={`/doctors/${doctor.id}`}>
                    <button className="w-full bg-white hover:bg-gray-50 hover:cursor-pointer text-cyan-500 border border-cyan-500 px-5 py-2 rounded-lg font-medium text-sm">
                        Xem chi tiết
                    </button>
                </Link>

                <Link href={''}>
                    <button className="w-full bg-cyan-400 hover:bg-cyan-500 hover: cursor-pointer text-white px-5 py-2 rounded-lg font-medium text-sm">
                        Đặt khám ngay
                    </button>
                </Link>
            </div>
        </div>
    );
};
