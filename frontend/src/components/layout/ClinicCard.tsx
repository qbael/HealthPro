import React from "react";
import {ClinicsType} from "@/types/clinics-types";
import Image from "next/image";
import Link from "next/link";

export const ClinicCard = (clinic: ClinicsType) => {
    return (
        <div key={clinic.id}
             className="min-w-[calc(33.333%-16px)] bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow">
            <div className="relative h-48">
                <Image
                    src={clinic.avatarUrl}
                    alt={clinic.clinicName}
                    fill={true}
                    className="w-full h-full object-cover"
                />
                <div className="absolute bottom-4 left-4 bg-white rounded-full p-3 shadow-lg">
                    <div className="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center">
                        <Image
                            src={clinic.logoUrl}
                            alt={clinic.clinicName}
                            fill={true}
                            className="w-full h-full object-cover rounded-full p-1 shadow-lg"
                        />
                    </div>
                </div>
            </div>
            <div className="p-5">
                <Link href={`/clinics/${clinic.id}`}>
                    <h3 className="font-semibold text-gray-900 mb-2 hover:text-blue-500 hover:underline">{clinic.clinicName}</h3>
                </Link>
                <p className="text-sm text-gray-600 mb-4">{clinic.address}</p>
                <div className="space-y-1 text-sm">
                    <div className="flex justify-between">
                        <span className="text-gray-600">T2 - T6: </span>
                        <span className="text-gray-900">{clinic.weekdayOpenHour} - {clinic.weekdayCloseHour}</span>
                    </div>
                    <div className="flex justify-between">
                        <span className="text-gray-600">Thứ 7 - CN:</span>
                        <span className="text-gray-900">{clinic.weekendOpenHour} - {clinic.weekendCloseHour}</span>
                    </div>
                </div>
            </div>
        </div>
    );
};
