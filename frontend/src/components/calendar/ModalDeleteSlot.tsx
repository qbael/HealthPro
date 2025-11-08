import { useState } from "react";
import { TimeSlotType } from "@/types/calendar-types";
import {toast} from "sonner";
import api from "@/lib/axios";
import {router} from "next/client";
import {useRouter} from "next/navigation";

interface ModalDeleteSlotProps {
    slot: TimeSlotType;
    onClose: () => void;
}

export default function ModalDeleteSlot({slot, onClose}: ModalDeleteSlotProps) {
    const [isDeleting, setIsDeleting] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const handleDelete = async () => {
        try {
            setIsDeleting(true);
            setError(null);

            const res = await api.delete(`/v3/schedule/doctor/${slot.id}`);

            if (res.status === 200) {
                toast.success('Xóa khung giờ khám thành công!');
            }
        } catch (err) {
            setError("Không thể xóa khung giờ hẹn. Vui lòng thử lại.");
            console.error("Error deleting time slot:", err);
        } finally {
            setIsDeleting(false);
            setTimeout(() => {
                window.location.reload();
            }, 1000);
            onClose()
        }
    };


    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center">
            <div
                className="absolute inset-0 bg-black/50"
                onClick={onClose}
            />

            <div className="relative bg-white rounded-lg shadow-xl max-w-md w-full mx-4 p-6">
                <div className="mb-4">
                    <h2 className="text-xl font-semibold text-gray-900">
                        Xác nhận xóa
                    </h2>
                </div>

                <div className="mb-6">
                    <p className="text-gray-600 mb-3">
                        Bạn có chắc chắn muốn xóa khung giờ hẹn này không?
                    </p>
                    <div className="bg-gray-50 p-3 rounded-md">
                        <p className="text-sm text-gray-700">
                            <span className="font-medium">Ngày:</span> {slot.appointmentDate}
                        </p>
                        <p className="text-sm text-gray-700">
                            <span className="font-medium">Thời gian:</span> {slot.startTime} - {slot.endTime}
                        </p>
                    </div>
                </div>

                {error && (
                    <div className="mb-4 p-3 bg-red-50 border border-red-200 rounded-md">
                        <p className="text-sm text-red-600">{error}</p>
                    </div>
                )}

                <div className="flex gap-3 justify-end">
                    <button
                        onClick={onClose}
                        disabled={isDeleting}
                        className="px-4 py-2 text-gray-700 bg-gray-100 rounded-md hover:bg-gray-200 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                        Hủy
                    </button>
                    <button
                        onClick={handleDelete}
                        disabled={isDeleting}
                        className="px-4 py-2 text-white bg-red-600 rounded-md hover:bg-red-700 transition-colors disabled:opacity-50 disabled:cursor-not-allowed flex items-center gap-2"
                    >
                        {isDeleting && (
                            <svg className="animate-spin h-4 w-4 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                            </svg>
                        )}
                        {isDeleting ? "Đang xóa..." : "Xóa"}
                    </button>
                </div>
            </div>
        </div>
    );
}