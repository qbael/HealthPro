import React from 'react'
import {ChevronRight} from "lucide-react";
import {DoctorType} from "@/types/doctor-types";
import Image from "next/image";
import Link from "next/link";

const DoctorCard = (doctor: DoctorType) => {
    const specialties = doctor.doctorSpecialties.map(ds => ds.specialty.name).join(' • ');
    return (
        <div key={doctor.id}
             className="min-w-[calc(25%-18px)] bg-white rounded-lg my-2 shadow-md p-6 hover:shadow-lg transition-shadow flex flex-col">
            <div className="flex flex-col items-center flex-1">
                <Image
                    src={doctor.avatarUrl}
                    alt={doctor.fullName}
                    width={96}
                    height={96}
                    className="w-24 h-24 rounded-full object-cover mb-4"
                />
                <div className="text-center px-6 flex-1">
                    <Link href={`/doctors/${doctor.id}`}>
                        <h3 className="font-semibold text-center text-gray-900 mb-2 hover:text-blue-500 hover:underline">{doctor.fullName}</h3>
                    </Link>
                    <span className="text-sm text-gray-600 block mb-1">{specialties}</span>
                    <p className="text-sm text-gray-500">{doctor.address}</p>
                </div>
            </div>
            <button
                className="w-full bg-white border font-medium border-gray-400 text-gray-700 py-2 rounded-lg
                hover:bg-gray-50 hover:text-blue-500 flex items-center justify-between px-4 mt-5 transition-colors">
                <span>Đặt lịch khám</span>
                <ChevronRight size={18}/>
            </button>
        </div>
    )
}

export default DoctorCard