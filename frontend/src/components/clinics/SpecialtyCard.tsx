import React from 'react';
import {Button} from "@/components/ui/button"
import Link from "next/link";

const SpecialtyCard = ({ specialty } : any) => {

    return (
        <div className="flex flex-col gap-5 border border-gray-200 shadow-lg rounded-xl p-5 items-center justify-center bg-white cursor-pointer">
            <h3 className="text-xl font-semibold text-center">{specialty.specialtyName}</h3>
            <div className='flex items-center gap-3'>
                <Button className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    <Link href={`/clinic/specialty/doctors/${specialty.specialtyId}`}>Mời Bác Sĩ</Link>
                </Button>
                <Button className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    <Link href={`/clinic/specialty/schedules/${specialty.specialtyId}`}>Đăng Ký Lịch Làm</Link>
                </Button>
            </div>
        </div>
    );
};

export default SpecialtyCard;