import {DoctorType} from "@/types/doctor-types";
import {DOCTORS_API_URL} from "@/lib/utils";
import {ResponseWithPaginationType} from "@/types/response";
import {Pagination} from "@/components/ui/Pagination";
import React from "react";
import {DoctorBookingCard} from "@/components/ui/DoctorBookingCard";

export default async function DoctorListingPage({searchParams} : {searchParams: Promise<{page?: number; limit?: number; sortBy?: string; sortDir?: string} >}) {
    const {page = 0, limit = 10, sortBy = 'id', sortDir = 'acs'} = await searchParams;
    const res = await fetch(`${DOCTORS_API_URL}?page=${page}&limit=${limit}&sortBy=${sortBy}&sortDir=${sortDir}`, {
        method: "GET",
        cache: 'no-store'
    });

    if (!res.ok) {
        throw new Error('Failed to fetch doctors');
    }
    const body: ResponseWithPaginationType<DoctorType> = await res.json();
    const doctors = body.data.content;
    const totalPages = body.data.totalPages;
    const currentPage = body.data.pageable.pageNumber;

    return (
        <div className={'min-h-screen mt-5 bg-gray-50 py-6 px-100'}>
            <div className="flex flex-wrap flex-col w-full gap-4">
                {doctors.map(doctor => (
                    <DoctorBookingCard key={doctor.id} doctor={doctor}/>
                ))}
            </div>
            <div className={'w-full justify-items-center'}>
                <Pagination
                    currentPage={currentPage}
                    totalPages={totalPages}
                />
            </div>
        </div>
    )
}