import {CLINICS_API_URL} from "@/lib/utils";
import {ResponseWithPaginationType} from "@/types/response";
import {Pagination} from "@/components/ui/Pagination";
import React from "react";
import {ClinicsType} from "@/types/clinics-types";
import {ClinicBookingCard} from "@/components/ClinicBookingCard";

export default async function ClinicListingPage({searchParams} : {searchParams: Promise<{page?: number; limit?: number; sortBy?: string; sortDir?: string} >}) {
    const {page = 0, limit = 10, sortBy = 'id', sortDir = 'acs'} = await searchParams;
    const res = await fetch(`${CLINICS_API_URL}?page=${page}&limit=${limit}&sortBy=${sortBy}&sortDir=${sortDir}`, {
        method: "GET",
        cache: 'no-store'
    });

    if (!res.ok) {
        throw new Error('Failed to fetch doctors');
    }

    const body: ResponseWithPaginationType<ClinicsType> = await res.json();
    const clinics = body.data.content;
    const totalPages = body.data.totalPages;
    const currentPage = body.data.pageable.pageNumber;

    return (
        <div className={'min-h-screen mt-15 bg-gray-50 py-6 px-40 items-center'}>
            <div className="grid grid-cols-1 lg:grid-cols-2 items-center w-full gap-4">
                {clinics.map(clinic => (
                    <ClinicBookingCard key={clinic.id} clinic={clinic}/>
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