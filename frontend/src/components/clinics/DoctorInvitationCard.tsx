'use client'

import Image from "next/image";
import {MapPin} from "lucide-react";
import api from "@/lib/axios";
import {toast} from "sonner";

const DoctorInvitationCard = ({ invitation } : any) => {
    const handleInvitation = async () => {
        try {
            await api.put(`v2/clinic-invitation/${invitation.id}`, {
                status: 'CANCELLED'
            })
            toast.success('Hủy thành công')
            setTimeout(() => {
                window.location.reload()
            }, 1000)
        }
        catch (error: any) {
            console.error(error)
        }
    }

    return (
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-5 mb-3 max-w-3xl">
            <div className="flex gap-4 items-center">
                <Image
                    src={invitation.avatarUrl}
                    alt={invitation.fullName}
                    width={100}
                    height={100}
                    className="w-30 h-30 rounded-lg object-cover"
                />
                <div className="flex-1">
                    <h3 className="text-cyan-500 font-semibold text-lg mb-1">
                        {invitation.fullName}
                    </h3>
                    <p className="text-sm text-gray-700 mb-1">
                        <span className="font-medium">Chuyên trị:</span> {invitation.specialtyName}
                    </p>
                    <p className="text-sm font-medium text-gray-600 mb-2">{invitation.bio}</p>
                    <div className="flex items-start gap-2 text-sm text-gray-600">
                        <MapPin className="w-4 h-4 mt-0.5 flex-shrink-0" />
                        <span>{invitation.address}</span>
                    </div>
                </div>
            </div>

            <div className="flex flex-1 gap-2 mt-4 h-full">
                <div className="w-full text-white text-center bg-cyan-500 px-3 py-2 rounded-lg font-medium text-sm">
                    Trạng thái: {
                        invitation.status === 'PENDING' ? 'Đang xử lý' :
                        invitation.status === 'ACCEPTED' ? 'Đồng ý' :
                        invitation.status === 'REJECTED' ? 'Từ chối' :
                        invitation.status === 'CANCELLED' ? 'Đã huỷ' :
                        'Không xác định'
                    }
                </div>
                {invitation.status === 'PENDING' && (
                    <button
                        className="w-full bg-red-400 hover:bg-red-500 hover: cursor-pointer text-white px-5 py-2 rounded-lg font-medium text-sm"
                        onClick={handleInvitation}
                    >
                        Hủy
                    </button>
                )}
            </div>
        </div>
    );
};

export default DoctorInvitationCard;