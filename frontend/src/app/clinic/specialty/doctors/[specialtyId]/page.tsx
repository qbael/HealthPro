import {createServerApi} from "@/lib/axiosServer";
import DoctorSpecialtyCard from "@/components/clinics/DoctorSpecialtyCard";
import {Pagination} from "@/components/ui/Pagination";
import React from "react";

interface PageProps {
    params: { id: string };
    searchParams: Record<string, string>;
}

const Page = async ({ params, searchParams }: PageProps) => {
    const api = await createServerApi()
    const { specialtyId } = params;

    const res = await api.get(`/v1/specialties/${specialtyId}`)
    const specialtyName = res.data.name

    const { page, limit, sortBy, sortDir } = searchParams || {};

    const queryParams = new URLSearchParams({
        ...(page && { page }),
        ...(limit && { limit }),
        ...(sortBy && { sortBy }),
        ...(sortDir && { sortDir }),
    }).toString();

    const res1 = await api
        .get(`v1/doctor-specialty/${specialtyId}${queryParams ? `?${queryParams}` : ""}`)
    console.log(res1)
    const doctors = res1?.data?.data?.content;
    const totalPages = res1?.data?.data?.totalPages;
    const currentPage = res1?.data?.data?.pageable?.pageNumber;
    console.log(doctors)
    console.log(totalPages)
    console.log(currentPage)
    return (
        <main>
            <section className='relative top-5 mx-auto w-[90%] max-w-[900px]'>
                <h1 className='text-blue-400 text-2xl text-center font-bold mb-7'>Bác Sĩ Theo Chuyên Khoa: {specialtyName}</h1>

                <div className="flex flex-wrap flex-col w-full items-center">
                    {doctors?.length ? (
                        doctors.map((doctor: any) => (
                            <DoctorSpecialtyCard key={doctor.id} doctor={doctor} specialtyId={specialtyId}/>
                        ))
                    ) : (
                        <p className="text-gray-500">Không có bác sĩ nào trong chuyên khoa này.</p>
                    )}
                </div>
                <div className={'w-full justify-items-center'}>
                    <Pagination
                        currentPage={currentPage}
                        totalPages={totalPages}
                    />
                </div>
            </section>
        </main>
    )
};

export default Page;