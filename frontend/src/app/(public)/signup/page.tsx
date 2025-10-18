'use client'

import { useRouter, useSearchParams } from 'next/navigation'
import SignupForm from '@/components/SignupForm'
import { Button } from '@/components/ui/button'
import Image from 'next/image'
import Link from 'next/link'

const SignupPage = () => {
    const searchParams = useSearchParams()
    const role = searchParams.get('role') || 'PATIENT'
    const router = useRouter()

    return (
        <main className='flex justify-center'>
            <div className='relative top-5 w-1/4 border border-gray-300 shadow-sm rounded-2xl p-4 flex flex-col gap-5'>
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
                        variant='outline'
                        onClick={() => router.replace(`/signup?role=PATIENT`)}
                        className={`flex-1 ${role === 'PATIENT' ? 'bg-blue-500 text-white' : ''}`}
                    >
                        Bệnh nhân
                    </Button>
                    <Button 
                        variant='outline'
                        onClick={() => router.replace(`/signup?role=DOCTOR`)}
                        className={`flex-1 ${role === 'DOCTOR' ? 'bg-blue-500 text-white' : ''}`}
                    >
                        Bác sĩ
                    </Button>
                    <Button 
                        variant='outline'
                        onClick={() => router.replace(`/signup?role=CLINIC`)}
                        className={`flex-1 ${role === 'CLINIC' ? 'bg-blue-500 text-white' : ''}`}
                    >
                        Phòng khám
                    </Button>
                </div>
                <SignupForm key={role} role={role} />
                <div className='flex gap-5'>
                    <Link href='/login' className='text-sm font-bold'>Đã có tài khoản?</Link>
                </div>
            </div>
        </main>
    )
}

export default SignupPage