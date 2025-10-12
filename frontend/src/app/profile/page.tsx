import React from 'react';
import ProfileForm from "@/components/ProfileForm";

const Page = () => {
    return (
        <main className="flex justify-center">
            <div className='w-[90%] max-w-[700px] mt-30'>
                <ProfileForm />
            </div>
        </main>
    );
};

export default Page;