'use client'

import { useRouter, useSearchParams } from 'next/navigation'
import SignupForm from '@/components/SignupForm'
import { Button } from '@/components/ui/button'
import Image from 'next/image'
import Link from 'next/link'

const page = () => {
    const searchParams = useSearchParams()
    const role = searchParams.get('role') || 'patient'
    const router = useRouter()

    return (
        <main className='flex justify-center'>
            <div className='relative top-30 w-1/4 border border-gray-300 shadow-sm rounded-2xl p-4 flex flex-col gap-5'>
                <div className='flex justify-center items-center gap-2'>
                    <Image 
                        src='/logo.svg'
                        alt='logo'
                        width={48}
                        height={48}
                    />
                    <h1 className='text-3xl font-bold italic text-[#0079CEFF]'>HealthPro</h1>
                </div>
                <div className='flex items-center justify-between gap-3'>
                    <Button 
                        variant={role === 'patient' ? 'default' : 'outline'}
                        onClick={() => router.push(`/signup?role=patient`)}   
                        className='flex-1'
                    >
                        Bệnh nhân
                    </Button>
                    <Button 
                        variant={role === 'doctor' ? 'default' : 'outline'}
                        onClick={() => router.push(`/signup?role=doctor`)}
                        className='flex-1' 
                    >
                        Bác sĩ
                    </Button>
                    <Button 
                        variant={role === 'clinic' ? 'default' : 'outline'}
                        onClick={() => router.push(`/signup?role=clinic`)}
                        className='flex-1' 
                    >
                        Phòng khám
                    </Button>
                </div>
                <SignupForm key={role} role={role} />
                <div className='flex gap-5'>
                    <Link href='/login' className='text-sm font-bold'>Chưa có tài khoản?</Link>
                </div>
            </div>
        </main>
    )
}

export default page