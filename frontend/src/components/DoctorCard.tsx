import Image from 'next/image'
import Link from 'next/link'
import React from 'react'

const DoctorCard = () => {
    return (
        <div className='flex flex-col items-center gap-2 border border-gray-300 rounded-2xl shadow-md p-3'>
            <div className="w-24 h-24 rounded-full overflow-hidden">
                <Image 
                    src='/doctor.jpg'
                    width={100}
                    height={100}
                    alt='doctor'
                    className='object-cover'
                />
            </div>
            <Link href='/'>Shine Clinic By TS.BS Trần Ngọc Ánh since 1987</Link>
            <p className='text-sm text-gray-700'>Nhi khoa</p>
            <p className='text-md'>3/1 Thành Thái, P. 14, Q.10, TP. HCM</p>
            <Link href='/' className='block w-full border-t border-gray-300'>
                Đặt lịch khám
            </Link>
        </div>
    )
}

export default DoctorCard