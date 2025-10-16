'use client'
import React, {useEffect, useState} from 'react';
import ProfileForm from "@/components/ProfileForm";
import api from '@/lib/axios'
import {useAuth} from "@/contexts/AuthContext";

const Page = () => {
    const [user, setUser] = useState(null)
    const {user: currentUser} = useAuth()
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

    if (!user)
        return (
            <div className="text-center py-12 w-full h-screen flex justify-center items-center">
                <div
                    className="inline-block animate-spin rounded-full h-30 w-30 border-4 border-cyan-400 border-t-transparent">
                </div>
            </div>
        )

    return (
        <main className={`${currentUser?.role === 'PATIENT' ? 'mt-30' : 'ml-0 md:ml-[11%] my-5'} flex justify-center`}>
                <div className='w-[90%] max-w-[640px]'>
                <ProfileForm user={user}/>
            </div>
        </main>
    );
};

export default Page;