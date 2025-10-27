import {createServerApi} from "@/lib/axiosServer";
import DoctorSpecialtyCard from "@/components/clinics/DoctorSpecialtyCard";
import {Pagination} from "@/components/ui/Pagination";
import React from "react";
import ClinicInvitation from "@/components/doctor/ClinicInvitation";

interface PageProps {
    params: { id: string };
    searchParams: Record<string, string>;
}

const Page = async ({ searchParams }: PageProps) => {
    const api = await createServerApi()

    const { page, limit, sortBy, sortDir } = searchParams || {};

    const queryParams = new URLSearchParams({
        ...(page && { page }),
        ...(limit && { limit }),
        ...(sortBy && { sortBy }),
        ...(sortDir && { sortDir }),
    }).toString();

    const res = await api
        .get(`v1/clinic-invitation${queryParams ? `?${queryParams}` : ""}`)

    const invitations = res?.data?.data?.content;
    const totalPages = res?.data?.data?.totalPages;
    const currentPage = res?.data?.data?.pageable?.pageNumber;
    console.log(invitations)

    return (
        <main>
            <section className='relative top-5 mx-auto w-[90%] max-w-[900px]'>
                <h1 className='text-blue-400 text-2xl text-center font-bold mb-7'>Lời Mời Chuyên Khoa</h1>

                <div className="flex flex-wrap flex-col w-full items-center">
                    {invitations?.length ? (
                        invitations.map((invitation: any) => (
                            <ClinicInvitation key={invitation.id} invitation={invitation}/>
                        ))
                    ) : (
                        <p className="text-center text-gray-500">Không có lời mời.</p>
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