'use client'

import React, {useState} from 'react';
import {
    AlertCircle,
    Building2,
    Calendar,
    CheckCircle,
    Clock,
    MapPin,
    Phone,
    Stethoscope,
    X,
    XCircle
} from 'lucide-react';
import ConfirmDialog from "@/components/ConfirmDialog";
import api from "@/lib/axios";
import {useRouter} from "next/navigation";
import {toast} from "sonner";

const PatientAppointment = ({ appointments } : any) => {
    const [confirmDialog, setConfirmDialog] = useState({ isOpen: false, appointmentId: null as string | null });
    const router = useRouter()

    const getStatusColor = (status: any) => {
        switch (status) {
            case 'COMPLETED': return 'bg-green-100 text-green-800 border-green-200';
            case 'SCHEDULED': return 'bg-blue-100 text-blue-800 border-blue-200';
            case 'CANCELLED': return 'bg-red-100 text-red-800 border-red-200';
            case 'NO_SHOW': return 'bg-gray-100 text-gray-800 border-gray-200';
            default: return 'bg-gray-100 text-gray-800 border-gray-200';
        }
    };

    const getStatusIcon = (status: any) => {
        switch (status) {
            case 'COMPLETED': return <CheckCircle className="w-4 h-4" />;
            case 'CANCELLED': return <XCircle className="w-4 h-4" />;
            default: return <AlertCircle className="w-4 h-4" />;
        }
    };

    const getStatusText = (status: any) => {
        switch (status) {
            case 'COMPLETED': return 'Đã hoàn thành';
            case 'SCHEDULED': return 'Đã đặt lịch';
            case 'CANCELLED': return 'Đã hủy';
            case 'NO_SHOW': return 'Không đến';
            default: return status;
        }
    };

    const formatDate = (date: any) => {
        return new Date(date).toLocaleDateString('vi-VN', {
            weekday: 'long',
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    };

    const handleCancelAppointment = async (appointmentId: any) => {
        if (!appointmentId)
            return

        try {
            await api.put(`v3/appointments/${appointmentId}?status=CANCELLED`)
            toast.success('Hủy thành công.')
            router.refresh()
        }
        catch (err: any) {
            console.error(err)
            toast.error('Hủy thất bại')
        }
    };

    const canCancel = (apt: any) => {
        return apt.status === 'SCHEDULED' && new Date(apt.appointmentDate) > new Date();
    };

    return (
        <div className="max-w-4xl mx-auto p-6">
            <div className="mb-6">
                <h2 className="text-2xl font-bold text-gray-900 mb-2">Lịch Hẹn Của Tôi</h2>
                <p className="text-gray-600">Quản lý các cuộc hẹn khám bệnh</p>
            </div>

            <ConfirmDialog
                isOpen={confirmDialog.isOpen}
                onClose={() => setConfirmDialog({ isOpen: false, appointmentId: null })}
                onConfirm={() => handleCancelAppointment(confirmDialog.appointmentId)}
                title="Xác nhận hủy lịch hẹn"
                message="Bạn có chắc chắn muốn hủy lịch hẹn này không? Hành động này không thể hoàn tác."
                confirmText="Hủy lịch hẹn"
                cancelText="Không"
                type="danger"
            />

            <div className="space-y-4">
                {appointments.map((apt: any) => (
                    <div key={apt.id} className="bg-white rounded-lg shadow-md border border-gray-200 overflow-hidden hover:shadow-lg transition-shadow">
                        <div className="p-6">
                            <div className="flex items-start justify-between mb-4">
                                <div className="flex-1">
                                    <div className="flex items-center gap-2 mb-2">
                                        <Calendar className="w-5 h-5 text-blue-600" />
                                        <span className="text-lg font-semibold text-gray-900">
                                          {formatDate(apt.appointmentDate)}
                                        </span>
                                    </div>
                                    <div className="flex items-center gap-2 text-gray-600">
                                        <Clock className="w-4 h-4" />
                                        <span>{apt.startTime.slice(0, 5)} - {apt.endTime.slice(0, 5)}</span>
                                    </div>
                                </div>
                                <span className={`px-3 py-1 rounded-full text-xs font-medium border flex items-center gap-1 ${getStatusColor(apt.status)}`}>
                                    {getStatusIcon(apt.status)}
                                    {getStatusText(apt.status)}
                                </span>
                            </div>

                            <div className="grid md:grid-cols-2 gap-4 mb-4">
                                <div className="space-y-3">
                                    <div className="flex items-start gap-2">
                                        <Stethoscope className="w-4 h-4 text-gray-500 mt-1" />
                                        <div>
                                            <p className="text-sm text-gray-500">Bác sĩ</p>
                                            <p className="font-medium text-gray-900">{apt.doctorName}</p>
                                        </div>
                                    </div>
                                    <div className="flex items-start gap-2">
                                        <Building2 className="w-4 h-4 text-gray-500 mt-1" />
                                        <div>
                                            <p className="text-sm text-gray-500">Chuyên khoa</p>
                                            <p className="font-medium text-gray-900">{apt.specialtyName}</p>
                                        </div>
                                    </div>
                                </div>

                                <div className="space-y-3">
                                    <div className="flex items-start gap-2">
                                        <MapPin className="w-4 h-4 text-gray-500 mt-1" />
                                        <div>
                                            <p className="text-sm text-gray-500">Phòng khám</p>
                                            <p className="font-medium text-gray-900">{apt.clinicName}</p>
                                            <p className="text-sm text-gray-600">{apt.address}</p>
                                        </div>
                                    </div>
                                    <div className="flex items-start gap-2">
                                        <Phone className="w-4 h-4 text-gray-500 mt-1" />
                                        <div>
                                            <p className="text-sm text-gray-500">Điện thoại</p>
                                            <p className="font-medium text-gray-900">{apt.phone}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            {apt.notes && (
                                <div className="bg-blue-50 rounded-md p-3 border border-blue-100 mb-4">
                                    <p className="text-sm text-gray-700">
                                        <span className="font-medium">Ghi chú:</span> {apt.notes}
                                    </p>
                                </div>
                            )}

                            {canCancel(apt) && (
                                <div className="pt-4 border-t">
                                    <button
                                        onClick={() => setConfirmDialog({ isOpen: true, appointmentId: apt.id })}
                                        className="w-full md:w-auto px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded-lg font-medium transition-colors flex items-center justify-center gap-2"
                                    >
                                        <X className="w-4 h-4" />
                                        Hủy lịch hẹn
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default PatientAppointment