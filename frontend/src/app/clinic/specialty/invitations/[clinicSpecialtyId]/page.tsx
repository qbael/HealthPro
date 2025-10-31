import {createServerApi} from "@/lib/axiosServer";
import DoctorInvitationCard from "@/components/clinics/DoctorInvitationCard"
import {Pagination} from "@/components/ui/Pagination";
import React from "react";

const Page = async ({ params, searchParams } : any) => {
    const { clinicSpecialtyId } = await params
    const api = await createServerApi()

    const { page, limit, sortBy, sortDir } = searchParams || {};

    const queryParams = new URLSearchParams({
        ...(page && { page }),
        ...(limit && { limit }),
        ...(sortBy && { sortBy }),
        ...(sortDir && { sortDir }),
    }).toString();

    const res1 = await api
        .get(`v2/clinic-invitation/${clinicSpecialtyId}${queryParams ? `?${queryParams}` : ""}`)

    const invitations = res1?.data?.data?.content;
    const totalPages = res1?.data?.data?.totalPages;
    const currentPage = res1?.data?.data?.pageable?.pageNumber;

    console.log(invitations)

    return (
        <main>
            <section className='relative top-5 mx-auto w-[90%] max-w-[900px]'>
                <h1 className='text-blue-400 text-2xl text-center font-bold mb-7'>Lời Mời Đã Gửi: {invitations[0]?.specialtyName}</h1>

                <div className="flex flex-wrap flex-col w-full items-center">
                    {invitations?.length ? (
                        invitations.map((invitation: any) => (
                            <DoctorInvitationCard key={invitation.id} invitation={invitation} />
                        ))
                    ) : (
                        <p className="text-gray-500">Không có lời mời nào.</p>
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