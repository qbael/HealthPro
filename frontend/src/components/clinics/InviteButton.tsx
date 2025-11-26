"use client";

import { toast } from "sonner";
import api from "@/lib/axios";
import {router} from "next/client";
const InviteButton = ({ specialtyId, doctorId }: { specialtyId: string; doctorId: string }) => {
    const handleInvite = async () => {
        try {
            await api.post("v2/clinic-invitation", { specialtyId, doctorId });
            toast.success("Đã gửi lời mời");
            window.location.reload();
        } catch (err: any) {
            const msg = err.response?.data?.message || 'Không thể gửi lời mời.'
            toast.error(msg)
        }
    };

    return (
        <button
            className="w-full bg-cyan-400 hover:bg-cyan-500 text-white px-5 py-2 rounded-lg font-medium text-sm"
            onClick={handleInvite}
        >
            Mời vào chuyên khoa
        </button>
    );
};

export default InviteButton;