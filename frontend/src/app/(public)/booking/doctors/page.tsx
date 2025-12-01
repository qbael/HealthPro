import {DoctorType} from "@/types/doctor-types";
import {DOCTORS_API_URL} from "@/lib/utils";
import {ResponseWithPaginationType} from "@/types/response";
import {Pagination} from "@/components/ui/Pagination";
import React from "react";
import {DoctorBookingCard} from "@/components/cards/DoctorBookingCard";

export default async function DoctorListingPage({searchParams} : {searchParams: Promise<{page?: number; limit?: number; sortBy?: string; sortDir?: string} >}) {
    const {page = 0, limit = 10, sortBy = 'id', sortDir = 'acs'} = await searchParams;
    const res = await fetch(`${DOCTORS_API_URL}?page=${page}&limit=${limit}&sortBy=${sortBy}&sortDir=${sortDir}`, {
        method: "GET",
        cache: 'no-store'
    });

    let doctors: DoctorType[] = [];
    let totalPages = 0;
    let currentPage = 0;
    let body: ResponseWithPaginationType<DoctorType>;

    if (res.ok) {
        body = await res.json();
        totalPages = body.data.totalPages;
        currentPage = body.data.pageable.pageNumber;
        doctors = body.data.content;
    }

    return (
        <div className={'min-h-screen w-full bg-gray-50 py-6 px-100'}>
            <div className="flex flex-wrap flex-col w-full items-center">
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