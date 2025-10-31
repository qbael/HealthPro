import React, {useEffect, useState} from 'react';
import {X, Calendar, Clock, User, Stethoscope, MapPin, Phone, FileText, AlertCircle} from 'lucide-react';
import {AppointmentData, TimeSlotType} from "@/types/calendar-types";
import api from "@/lib/axios";
import {toast} from "sonner";

interface ModalConfirmAppointmentProps {
    onClose: () => void;
    slot: TimeSlotType;
    userId: string;
    appointmentType: 'DOCTOR' | 'CLINIC';
    doctorId: string | null;
    clinicSpecialtyId: string | null;
}

const ModalConfirmAppointment = ({
                                     onClose,
                                     slot,
                                     userId,
                                     appointmentType,
                                     doctorId,
                                     clinicSpecialtyId,
                                 }: ModalConfirmAppointmentProps) => {
    const [notes, setNotes] = useState('');
    const [appointmentData, setAppointmentData] = useState<AppointmentData | null>(null);
    const [isLoading, setIsLoading] = useState(true);
    const [isConfirming, setIsConfirming] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const formatDate = (dateStr: string) => {
        const date = new Date(dateStr);
        const dayOfWeek = ['Chủ nhật', 'Thứ hai', 'Thứ ba', 'Thứ tư', 'Thứ năm', 'Thứ sáu', 'Thứ bảy'];
        return `${dayOfWeek[date.getDay()]}, ${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                setIsLoading(true);
                setError(null);

                const response = await api.post('/v3/appointments/appointment-data', {
                    "userId": userId,
                    "doctorId": doctorId,
                    "clinicSpecialtyId": clinicSpecialtyId
                });

                if (response.data.success) {
                    setAppointmentData(response.data.data);
                } else {
                    setError(response.data.message || 'Không thể tải thông tin đặt khám');
                }
            } catch (error: any) {
                console.error('Error fetching appointment data:', error);
                setError(error.response?.data?.message || 'Đã xảy ra lỗi khi tải thông tin. Vui lòng thử lại.');
            } finally {
                setIsLoading(false);
            }
        };

        fetchData();
    }, [userId, appointmentType, doctorId, clinicSpecialtyId]);

    const handleConfirm = async () => {
        if (!appointmentData) return;

        try {
            setIsConfirming(true);

            const bookingData = {
                ...appointmentData,
                slotId: slot.id,
                notes: notes.trim(),
                appointmentType: appointmentType,
            };

            const response = await api.post('/v3/appointments', bookingData);

            if (response.data.success) {
                toast.success('Đặt khám thành công!');
                onClose();
            }
        } catch (error: any) {
            console.error('Error booking appointment:', error);
            toast.error('Đã xảy ra lỗi khi đặt khám. Vui lòng thử lại.');
        } finally {
            setIsConfirming(false);
            setTimeout(() => {
                window.location.reload();
            }, 1500);
            onClose()
        }
    };

    if (!slot) return null;

    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/50">
            <div className="bg-white rounded-lg shadow-xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                <div className="sticky top-0 bg-cyan-400 text-white p-6 rounded-t-lg flex justify-between items-center">
                    <h2 className="text-2xl font-bold">Xác Nhận Thông Tin Đặt Khám</h2>
                    <button
                        onClick={onClose}
                        className="p-1 hover:bg-cyan-500 rounded-full transition-colors disabled:opacity-50"
                        disabled={isConfirming}
                    >
                        <X className="w-6 h-6"/>
                    </button>
                </div>

                <div className="p-6 space-y-6">
                    {isLoading ? (
                        <div className="flex flex-col items-center justify-center py-12">
                            <div className="animate-spin rounded-full h-12 w-12 border-4 border-cyan-400 border-t-transparent mb-4"/>
                            <p className="text-gray-600">Đang tải thông tin...</p>
                        </div>
                    ) : error ? (
                        <div className="bg-red-50 border-l-4 border-red-400 p-4 rounded">
                            <div className="flex items-center">
                                <AlertCircle className="w-5 h-5 text-red-400 mr-2"/>
                                <p className="text-red-700">{error}</p>
                            </div>
                        </div>
                    ) : appointmentData ? (
                        <>
                            <div className="bg-blue-50 rounded-lg p-4">
                                <h3 className="text-lg font-semibold text-gray-800 mb-3 flex items-center">
                                    <User className="w-5 h-5 mr-2 text-cyan-500"/>
                                    Thông Tin Bệnh Nhân
                                </h3>
                                <div className="space-y-2 text-gray-700">
                                    <p><span className="font-medium">Họ tên:</span> {appointmentData.patientName}</p>
                                    <p><span className="font-medium">Email:</span> {appointmentData.patientEmail}</p>
                                    <p><span className="font-medium">Số điện thoại:</span> {appointmentData.patientPhone}</p>
                                </div>
                            </div>

                            <div className="bg-green-50 rounded-lg p-4">
                                <h3 className="text-lg font-semibold text-gray-800 mb-3 flex items-center">
                                    <Stethoscope className="w-5 h-5 mr-2 text-green-500"/>
                                    Thông Tin {appointmentType === 'DOCTOR' ? 'Bác Sĩ' : 'Phòng Khám'}
                                </h3>
                                <div className="space-y-2 text-gray-700">
                                    {appointmentData.doctorName && (
                                        <p><span className="font-medium">Bác sĩ:</span> {appointmentData.doctorName}</p>
                                    )}
                                    {appointmentData.clinicName && (
                                        <p><span className="font-medium">Phòng khám:</span> {appointmentData.clinicName}</p>
                                    )}
                                    {appointmentData.specialtyName && (
                                        <p><span className="font-medium">Chuyên khoa:</span> {appointmentData.specialtyName}</p>
                                    )}
                                </div>
                            </div>

                            <div className="bg-orange-50 rounded-lg p-4">
                                <h3 className="text-lg font-semibold text-gray-800 mb-3 flex items-center">
                                    <MapPin className="w-5 h-5 mr-2 text-orange-500"/>
                                    Địa Điểm Khám
                                </h3>
                                <div className="space-y-2 text-gray-700">
                                    <p><span className="font-medium">Địa chỉ:</span> {appointmentData.address}</p>
                                    <p className="flex items-center">
                                        <Phone className="w-4 h-4 mr-2"/>
                                        <span className="font-medium">SĐT phòng khám:</span>
                                        <span className="ml-2">{appointmentData.phone}</span>
                                    </p>
                                </div>
                            </div>

                            <div className="bg-purple-50 rounded-lg p-4">
                                <h3 className="text-lg font-semibold text-gray-800 mb-3 flex items-center">
                                    <Calendar className="w-5 h-5 mr-2 text-purple-500"/>
                                    Thời Gian Khám
                                </h3>
                                <div className="space-y-2 text-gray-700">
                                    <p><span className="font-medium">Ngày khám:</span> {formatDate(slot.appointmentDate)}</p>
                                    <p className="flex items-center">
                                        <Clock className="w-4 h-4 mr-2"/>
                                        <span className="font-medium">Giờ khám:</span>
                                        <span className="ml-2 text-cyan-600 font-semibold">
                                            {slot.startTime} - {slot.endTime}
                                        </span>
                                    </p>
                                </div>
                            </div>

                            <div>
                                <label className="flex items-center text-gray-800 font-medium mb-2">
                                    <FileText className="w-5 h-5 mr-2 text-gray-500"/>
                                    Ghi Chú (Tùy chọn)
                                </label>
                                <textarea
                                    value={notes}
                                    onChange={(e) => setNotes(e.target.value)}
                                    placeholder="Nhập triệu chứng hoặc lý do khám bệnh..."
                                    disabled={isConfirming}
                                    className="w-full border-2 border-gray-300 rounded-lg p-3 focus:border-cyan-400 focus:outline-none resize-none disabled:bg-gray-100"
                                    rows={4}
                                    maxLength={1000}
                                />
                                <p className="text-sm text-gray-500 mt-1">{notes.length}/1000 ký tự</p>
                            </div>

                            <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4">
                                <p className="text-sm text-gray-700">
                                    <span className="font-semibold">Lưu ý:</span> Vui lòng kiểm tra kỹ thông tin trước khi xác nhận.
                                    Sau khi xác nhận, bạn có thể nhận được thông báo qua email hoặc số điện thoại đã đăng ký.
                                </p>
                            </div>
                        </>
                    ) : null}
                </div>

                <div className="sticky bottom-0 bg-gray-50 px-6 py-4 rounded-b-lg flex justify-end gap-3 border-t">
                    <button
                        onClick={onClose}
                        disabled={isConfirming}
                        className="px-6 py-2.5 border-2 border-gray-300 text-gray-700 rounded-lg font-medium hover:bg-gray-100 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                        Hủy
                    </button>
                    <button
                        onClick={handleConfirm}
                        disabled={isLoading || isConfirming || !appointmentData}
                        className="px-6 py-2.5 bg-cyan-400 text-white rounded-lg font-medium hover:bg-cyan-500 transition-colors disabled:opacity-50 disabled:cursor-not-allowed flex items-center"
                    >
                        {isConfirming ? (
                            <>
                                <div className="animate-spin rounded-full h-5 w-5 border-2 border-white border-t-transparent mr-2"/>
                                Đang xử lý...
                            </>
                        ) : (
                            'Xác Nhận Đặt Khám'
                        )}
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ModalConfirmAppointment;