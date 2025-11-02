import React from 'react';
import {SpecialtyType} from "@/types/doctor-types";
import {ResponseType} from "@/types/response";
import {renderSpecialtyIcon} from "@/lib/icon-provider";
import {CLINICS_API_URL} from "@/lib/utils";
import Link from "next/link";

export default async function ClinicSpecialtiesPage({params}: { params: Promise<{ id: string }> }) {

    const {id} = await params;

    const res = await fetch(`${CLINICS_API_URL}/${id}/specialties`, {
        method: "GET",
        cache: 'no-store'
    });

    if (!res.ok) {
        throw new Error('Failed to fetch clinic specialties');
    }

    const body: ResponseType<{ id: string, specialty: SpecialtyType }[]> = await res.json();

    const specialties = body.data;

    return (
        <div className="min-h-screen bg-gradient-to-b mt-16 from-cyan-50 to-blue-50 py-8 px-4">
            <div className="max-w-6xl mx-auto">
                <div className="text-center mb-8">
                    <h1 className="text-3xl md:text-4xl font-bold text-cyan-500 mb-3">
                        Các chuyên khoa tại phòng khám
                    </h1>
                    <p className="text-gray-600 text-base md:text-lg">
                        Đặt khám nhanh chóng, không phải chờ đợi với nhiều cơ sở y tế trên khắp các tỉnh thành
                    </p>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-6">
                    {specialties.map((specialty) => (
                        <Link href={`/calendar/clinic/${specialty.id}`} key={specialty.id}>
                            <button
                                key={specialty.id}
                                className="group bg-white w-full rounded-2xl shadow-md hover:shadow-xl
                                   transition-all duration-300 p-6 md:p-8
                                   border border-gray-100 hover:border-cyan-300
                                   transform hover:-translate-y-1
                                   disabled:opacity-50 disabled:cursor-not-allowed hover:cursor-pointer"
                            >
                                <div className="flex items-center gap-4 md:gap-6">
                                    <div className={`flex-shrink-0 group-hover:scale-110 transition-transform duration-300`}>
                                        {renderSpecialtyIcon(specialty.specialty.name)}
                                    </div>

                                    <h3 className="text-left text-lg md:text-xl font-semibold text-gray-800
                                        group-hover:text-cyan-600 transition-colors">
                                        {specialty.specialty.name}
                                    </h3>
                                </div>
                            </button>
                        </Link>
                    ))}
                </div>

                <div className="mt-12 text-center">
                    <p className="text-gray-500 text-sm">
                        Mọi thông tin đặt lịch sẽ được bảo mật tuyệt đối
                    </p>
                </div>
            </div>
        </div>
    );
};