import React from 'react';
import LoginForm from "@/components/LoginForm";
import Link from "next/link";
import Image from "next/image";

const Page = () => {
    return (
        <main className='flex justify-center'>
            <div className='relative top-40 w-1/4 border border-gray-300 shadow-sm rounded-2xl p-4'>
                <div className='flex justify-center items-center gap-2 mb-3'>
                    <Image
                        src='/logo.svg'
                        alt='logo'
                        width={48}
                        height={48}
                    />
                    <h1 className='text-3xl font-bold italic text-[#0079CEFF]'>HealthPro</h1>
                </div>
                <LoginForm />
                <div className='flex gap-5 mt-5'>
                    <Link href='/signup' className='text-sm font-bold'>Chưa có tài khoản?</Link>
                </div>
            </div>
        </main>
    );
};

export default Page;