import React, {useEffect, useState} from 'react';
import ProfileForm from "@/components/ProfileForm";
import api from '@/lib/axios'
const Page = () => {
    const [user, setUser] = useState(null)

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const res = await api.get('v1/profile')
                setUser(res.data)
            } catch (err) {
                console.error('Failed to fetch profile:', err)
            }
        }

        fetchProfile()
    }, [])

    if (!user) return <p>Loading...</p>

    return (
        <main className="flex justify-center">
            <div className='w-[90%] max-w-[700px] mt-30'>
                <ProfileForm user={user}/>
            </div>
        </main>
    );
};

export default Page;