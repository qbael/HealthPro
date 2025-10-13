import React from 'react';
import ProfileForm from "@/components/ProfileForm";
import api from '@/lib/axios'

const Page = async () => {
    const res = await api.get('v1/users')
    const user = res.data

    return (
        <main className="flex justify-center">
            <div className='w-[90%] max-w-[700px] mt-30'>
                <ProfileForm user={user}/>
            </div>
        </main>
    );
};

export default Page;