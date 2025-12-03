'use client'

import {useState} from "react";
import {Building2, Calendar, Clock, Phone} from 'lucide-react';
import ConfirmDialog from "@/components/ConfirmDialog";
import api from "@/lib/axios";
import {toast} from "sonner";
import {useRouter} from "next/navigation";

const DoctorAppointment = ({ appointments } : any) => {
    const [selectedDate, setSelectedDate] = useState('all');
    const [confirmDialog, setConfirmDialog] = useState({
        isOpen: false,
        appointmentId: null as string | null,
        newStatus: null as string | null
    });
    const router = useRouter()

    const dates: any = [...new Set(appointments.map((a: any) => a.appointmentDate))];

    const filteredAppointments = selectedDate === 'all'
        ? appointments
        : appointments.filter((a: any) => a.appointmentDate === selectedDate);

    const groupByTime = filteredAppointments.sort((a: any, b: any) =>
        a.startTime.localeCompare(b.startTime)
    );

    const getStatusText = (status: any) => {
        switch (status) {
            case 'COMPLETED': return 'Đã hoàn thành';
            case 'SCHEDULED': return 'Đã đặt lịch';
            case 'CANCELLED': return 'Đã hủy';
            case 'NO_SHOW': return 'Không đến';
            default: return status;
        }
    };

    const handleStatusChange = async (appointmentId: any, newStatus: any) => {
        try {
            await api.put(`v3/appointments/${appointmentId}?status=${newStatus}`)
            toast.success('Cập nhật thành công.')
            router.refresh()
        }
        catch (err: any) {
            console.error(err)
            toast.error('Cập nhật thất bại')
        }
    };

    const getConfirmMessage = (status: any) => {
        switch (status) {
            case 'COMPLETED': return 'Xác nhận đã hoàn thành cuộc hẹn này?';
            case 'CANCELLED': return 'Xác nhận hủy cuộc hẹn này?';
            case 'NO_SHOW': return 'Xác nhận bệnh nhân không đến?';
            default: return 'Xác nhận thay đổi trạng thái?';
        }
    };

    const getConfirmTitle = (status: any) => {
        switch (status) {
            case 'COMPLETED': return 'Hoàn thành cuộc hẹn';
            case 'CANCELLED': return 'Hủy cuộc hẹn';
            case 'NO_SHOW': return 'Đánh dấu không đến';
            default: return 'Xác nhận';
        }
    };

    return (
        <div className="max-w-6xl mx-auto p-6">
            <div className="mb-6">
                <h2 className="text-2xl font-bold text-gray-900 mb-2">Lịch Khám Bệnh</h2>
                <p className="text-gray-600">Quản lý lịch hẹn với bệnh nhân</p>
            </div>

            <ConfirmDialog
                isOpen={confirmDialog.isOpen}
                onClose={() => setConfirmDialog({ isOpen: false, appointmentId: null, newStatus: null })}
                onConfirm={() => handleStatusChange(confirmDialog.appointmentId, confirmDialog.newStatus)}
                title={getConfirmTitle(confirmDialog.newStatus)}
                message={getConfirmMessage(confirmDialog.newStatus)}
                confirmText="Xác nhận"
                cancelText="Hủy"
                type={confirmDialog.newStatus === 'CANCELLED' ? 'danger' : 'info'}
            />

            <div className="mb-6">
                <label className="block text-sm font-medium text-gray-700 mb-2">Lọc theo ngày</label>
                <select
                    value={selectedDate}
                    onChange={(e) => setSelectedDate(e.target.value)}
                    className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                >
                    <option value="all">Tất cả các ngày</option>
                    {dates.map((date: any) => (
                        <option key={date} value={date}>
                            {new Date(date).toLocaleDateString('vi-VN')}
                        </option>
                    ))}
                </select>
            </div>

            <div className="grid gap-4">
                {groupByTime.map((apt: any) => (
                    <div key={apt.id} className="bg-white rounded-lg shadow border border-gray-200 p-5 hover:shadow-md transition-shadow">
                        <div className="flex items-start justify-between mb-4">
                            <div className="flex gap-4 flex-1">
                                <div className="bg-blue-100 rounded-lg p-3 h-fit">
                                    <Clock className="w-6 h-6 text-blue-600" />
                                    <p className="text-xs font-medium text-blue-900 mt-1">{apt.startTime.slice(0, 5)}</p>
                                </div>

                                <div className="flex-1">
                                    <div className="flex items-center gap-3 mb-3">
                                        <h3 className="text-lg font-semibold text-gray-900">{apt.patientName}</h3>
                                        <span className="px-2 py-1 bg-purple-100 text-purple-800 text-xs rounded-full font-medium">
                      {apt.specialtyName}
                    </span>
                                    </div>

                                    <div className="grid md:grid-cols-3 gap-4 text-sm">
                                        <div className="flex items-center gap-2 text-gray-600">
                                            <Phone className="w-4 h-4" />
                                            <span>{apt.patientPhone}</span>
                                        </div>
                                        <div className="flex items-center gap-2 text-gray-600">
                                            <Building2 className="w-4 h-4" />
                                            <span>{apt.clinicName}</span>
                                        </div>
                                        <div className="flex items-center gap-2 text-gray-600">
                                            <Calendar className="w-4 h-4" />
                                            <span>{new Date(apt.appointmentDate).toLocaleDateString('vi-VN')}</span>
                                        </div>
                                    </div>

                                    {apt.notes && (
                                        <div className="mt-3 bg-gray-50 rounded p-2 text-sm text-gray-700">
                                            {apt.notes}
                                        </div>
                                    )}
                                </div>
                            </div>

                            <div className="text-right">
                <span className={`inline-block px-3 py-1 rounded-full text-xs font-medium ${
                    apt.status === 'COMPLETED' ? 'bg-green-100 text-green-800' :
                        apt.status === 'SCHEDULED' ? 'bg-blue-100 text-blue-800' :
                            apt.status === 'CANCELLED' ? 'bg-red-100 text-red-800' :
                                'bg-gray-100 text-gray-800'
                }`}>
                  {getStatusText(apt.status)}
                </span>
                                <p className="text-xs text-gray-500 mt-2">
                                    {apt.endTime}
                                </p>
                            </div>
                        </div>

                        {apt.status === 'SCHEDULED' && (
                            <div className="pt-4 border-t flex flex-wrap gap-2">
                                <button
                                    onClick={() => setConfirmDialog({
                                        isOpen: true,
                                        appointmentId: apt.id,
                                        newStatus: 'COMPLETED'
                                    })}
                                    className="px-4 py-2 bg-green-600 hover:bg-green-700 text-white rounded-lg text-sm font-medium transition-colors"
                                >
                                    Hoàn thành
                                </button>
                                <button
                                    onClick={() => setConfirmDialog({
                                        isOpen: true,
                                        appointmentId: apt.id,
                                        newStatus: 'NO_SHOW'
                                    })}
                                    className="px-4 py-2 bg-gray-600 hover:bg-gray-700 text-white rounded-lg text-sm font-medium transition-colors"
                                >
                                    Không đến
                                </button>
                                <button
                                    onClick={() => setConfirmDialog({
                                        isOpen: true,
                                        appointmentId: apt.id,
                                        newStatus: 'CANCELLED'
                                    })}
                                    className="px-4 py-2 bg-red-600 hover:bg-red-700 text-white rounded-lg text-sm font-medium transition-colors"
                                >
                                    Hủy lịch
                                </button>
                            </div>
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default DoctorAppointment