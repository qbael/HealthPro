import React from 'react';
import ProfileForm from "@/components/ProfileForm";
import api from '@/lib/axios'
const Page = async () => {
    const res = await api.get(`/api/v1/profile`)
    if (res.status === 204)
        router.push("/profile/create");
    else
        user = res.data

    return (
        <main className="flex justify-center">
            <div className='w-[90%] max-w-[700px] mt-30'>
                <ProfileForm user={user}/>
            </div>
        </main>
    );
};

export default Page;