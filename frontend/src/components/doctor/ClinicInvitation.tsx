import React from 'react';
import Image from "next/image";
import {Clock, MapPin, Star} from "lucide-react";
import Link from "next/link";

const ClinicInvitation = ({ invitation } : any) => {
    return (
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-4 mb-3 w-full">
            <div className="flex items-center gap-4">
                <Image
                    src={invitation.logoUrl}
                    alt={invitation.clinicName}
                    width={100}
                    height={100}
                    className="w-25 h-25 rounded-lg object-cover"
                />
                <div className="flex-1">
                    <h1 className="text-gray-800 font-semibold mb-1">
                        {invitation.clinicName}
                    </h1>
                    <div className="flex items-start gap-2 text-sm text-gray-600 mb-2">
                        <MapPin className="w-4 h-4 mt-0.5 flex-shrink-0"/>
                        <span>{invitation.address}</span>
                    </div>
                    <p className="text-sm text-gray-600 mb-2">chuyen khoa</p>
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                        <Clock className="w-4 h-4"/>
                        <span>T2-T6: {invitation.weekdayOpenHour} - {invitation.weekdayCloseHour} | T7-CN:{' '}
                            {invitation.weekendOpenHour} - {invitation.weekendCloseHour}
                        </span>
                    </div>
                    <div className="flex items-center gap-1 mt-2">
                        {[...Array(5)].map((_, i) => (
                            <Star key={i} className="w-4 h-4 fill-amber-400 text-amber-400"/>
                        ))}
                    </div>
                </div>
            </div>
            <div className="flex flex-col gap-2 mt-4 h-full">
                <Link href={`/doctor/invitations/${invitation.clinicId}`}>
                    <button className="w-full bg-white hover:bg-gray-50 hover:cursor-pointer text-cyan-500 border border-cyan-500 px-5 py-2 rounded-lg font-medium text-sm">
                        Xem chi tiết
                    </button>
                </Link>

                <div className='flex flex-1 gap-5 items-center'>
                    <button className="w-full bg-cyan-400 hover:bg-cyan-500 hover: cursor-pointer text-white px-5 py-2 rounded-lg font-medium text-sm">
                        Đồng ý
                    </button>
                    <button className="w-full bg-red-400 hover:bg-red-500 hover: cursor-pointer text-white px-5 py-2 rounded-lg font-medium text-sm">
                        Từ chối
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ClinicInvitation;