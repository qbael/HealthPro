'use client'

import {useState} from "react";
import ConfirmDialog from "@/components/ConfirmDialog";
import {AlertCircle, CheckCircle, XCircle} from "lucide-react";
import {useRouter} from "next/navigation";
import api from "@/lib/axios";
import {toast} from "sonner";

const ClinicAppointment = ({ appointments }: any) => {
    const [filterStatus, setFilterStatus] = useState('all');
    const [filterSpecialty, setFilterSpecialty] = useState('all');
    const [confirmDialog, setConfirmDialog] = useState({
        isOpen: false,
        appointmentId: null as string | null,
        newStatus: null as string | null
    });
    const router = useRouter()

    const specialties = [...new Set(appointments.map((a: any) => a.specialtyName))];

    const filteredAppointments = appointments.filter((apt: any) => {
        const statusMatch = filterStatus === 'all' || apt.status === filterStatus;
        const specialtyMatch = filterSpecialty === 'all' || apt.specialtyName === filterSpecialty;
        return statusMatch && specialtyMatch;
    });

    const stats = {
        total: appointments.length,
        completed: appointments.filter((a: any) => a.status === 'COMPLETED').length,
        scheduled: appointments.filter((a: any) => a.status === 'SCHEDULED').length,
        cancelled: appointments.filter((a: any) => a.status === 'CANCELLED').length,
        noShow: appointments.filter((a: any) => a.status === 'NO_SHOW').length,
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
        <div className="max-w-7xl mx-auto p-6">
            <div className="mb-6">
                <h2 className="text-2xl font-bold text-gray-900 mb-2">Quản Lý Lịch Hẹn</h2>
                <p className="text-gray-600">{appointments[0]?.clinicName}</p>
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

            <div className="grid grid-cols-4 gap-4 mb-6">
                <div className="bg-white rounded-lg shadow p-4 border-l-4 border-blue-500">
                    <p className="text-sm text-gray-600">Tổng số</p>
                    <p className="text-2xl font-bold text-gray-900">{stats.total}</p>
                </div>
                <div className="bg-white rounded-lg shadow p-4 border-l-4 border-green-500">
                    <p className="text-sm text-gray-600">Hoàn thành</p>
                    <p className="text-2xl font-bold text-green-600">{stats.completed}</p>
                </div>
                <div className="bg-white rounded-lg shadow p-4 border-l-4 border-yellow-500">
                    <p className="text-sm text-gray-600">Đã đặt lịch</p>
                    <p className="text-2xl font-bold text-yellow-600">{stats.scheduled}</p>
                </div>
                <div className="bg-white rounded-lg shadow p-4 border-l-4 border-red-500">
                    <p className="text-sm text-gray-600">Đã hủy/Không đến</p>
                    <p className="text-2xl font-bold text-red-600">{stats.cancelled + stats.noShow}</p>
                </div>
            </div>

            <div className="bg-white rounded-lg shadow p-4 mb-6">
                <div className="flex gap-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-2">Trạng thái</label>
                        <select
                            value={filterStatus}
                            onChange={(e) => setFilterStatus(e.target.value)}
                            className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
                        >
                            <option value="all">Tất cả</option>
                            <option value="SCHEDULED">Đã đặt lịch</option>
                            <option value="COMPLETED">Hoàn thành</option>
                            <option value="CANCELLED">Đã hủy</option>
                            <option value="NO_SHOW">Không đến</option>
                        </select>
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-2">Chuyên khoa</label>
                        <select
                            value={filterSpecialty}
                            onChange={(e) => setFilterSpecialty(e.target.value)}
                            className="px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500"
                        >
                            <option value="all">Tất cả chuyên khoa</option>
                            {specialties.map((specialty: any) => (
                                <option key={specialty} value={specialty}>{specialty}</option>
                            ))}
                        </select>
                    </div>
                </div>
            </div>

            <div className="bg-white rounded-lg shadow overflow-hidden">
                <table className="min-w-full divide-y divide-gray-200">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Ngày/Giờ</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Bệnh nhân</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Bác sĩ</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Chuyên khoa</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Trạng thái</th>
                        <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Thao tác</th>
                    </tr>
                    </thead>
                    <tbody className="bg-white divide-y divide-gray-200">
                    {filteredAppointments.map((apt: any) => (
                        <tr key={apt.id} className="hover:bg-gray-50">
                            <td className="px-6 py-4 whitespace-nowrap">
                                <div className="text-sm font-medium text-gray-900">
                                    {new Date(apt.appointmentDate).toLocaleDateString('vi-VN')}
                                </div>
                                <div className="text-sm text-gray-500">{apt.startTime.slice(0, 5)}</div>
                            </td>
                            <td className="px-6 py-4">
                                <div className="text-sm font-medium text-gray-900">{apt.patientName}</div>
                                <div className="text-sm text-gray-500">{apt.patientPhone}</div>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap">
                                <div className="text-sm text-gray-900">{apt.doctorName}</div>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap">
                  <span className="px-2 py-1 text-xs font-medium bg-purple-100 text-purple-800 rounded-full">
                    {apt.specialtyName}
                  </span>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap">
                  <span className={`px-2 py-1 text-xs font-medium rounded-full ${
                      apt.status === 'COMPLETED' ? 'bg-green-100 text-green-800' :
                          apt.status === 'SCHEDULED' ? 'bg-blue-100 text-blue-800' :
                              apt.status === 'CANCELLED' ? 'bg-red-100 text-red-800' :
                                  'bg-gray-100 text-gray-800'
                  }`}>
                    {getStatusText(apt.status)}
                  </span>
                            </td>
                            <td className="px-6 py-4 whitespace-nowrap text-sm">
                                {apt.status === 'SCHEDULED' && (
                                    <div className="flex gap-1">
                                        <button
                                            onClick={() => setConfirmDialog({
                                                isOpen: true,
                                                appointmentId: apt.id,
                                                newStatus: 'COMPLETED'
                                            })}
                                            className="px-2 py-1 bg-green-600 hover:bg-green-700 text-white rounded text-xs font-medium"
                                            title="Hoàn thành"
                                        >
                                            <CheckCircle className="w-4 h-4" />
                                        </button>
                                        <button
                                            onClick={() => setConfirmDialog({
                                                isOpen: true,
                                                appointmentId: apt.id,
                                                newStatus: 'NO_SHOW'
                                            })}
                                            className="px-2 py-1 bg-gray-600 hover:bg-gray-700 text-white rounded text-xs font-medium"
                                            title="Không đến"
                                        >
                                            <AlertCircle className="w-4 h-4" />
                                        </button>
                                        <button
                                            onClick={() => setConfirmDialog({
                                                isOpen: true,
                                                appointmentId: apt.id,
                                                newStatus: 'CANCELLED'
                                            })}
                                            className="px-2 py-1 bg-red-600 hover:bg-red-700 text-white rounded text-xs font-medium"
                                            title="Hủy"
                                        >
                                            <XCircle className="w-4 h-4" />
                                        </button>
                                    </div>
                                )}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default ClinicAppointment

// // Demo component
// export default function AppointmentDemo() {
//     const [view, setView] = useState('patient');
//
//     const sampleAppointments = [
//         {
//             id: "e8bc51dc-4509-4691-b8d3-4792f07d0133",
//             patientId: "750e8400-e29b-41d4-a716-446655440028",
//             patientName: "Trần Minh Đăng",
//             patientEmail: "mindang1@gmail.com",
//             patientPhone: "0923456790",
//             doctorId: "750e8400-e29b-41d4-a716-446655440016",
//             doctorName: "BS. Trần Minh Đăng",
//             clinicId: "750e8400-e29b-41d4-a716-446655440034",
//             clinicName: "Trung tâm Y tế Trần Minh Đăng 3",
//             address: "789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM",
//             phone: "0934567890",
//             clinicSpecialtyId: "a50e8400-e29b-41d4-a716-446655440024",
//             specialtyName: "Sản - Phụ khoa",
//             appointmentType: "CLINIC",
//             appointmentDate: "2024-09-20",
//             startTime: "10:00:00",
//             endTime: "10:45:00",
//             status: "COMPLETED",
//             notes: "Khám thai định kỳ 3 tháng",
//             createdAt: "2024-09-15T10:00:00",
//             updatedAt: "2024-09-20T11:00:00",
//         }
//     ];
//
//     return (
//         <div className="min-h-screen bg-gray-50">
//             <div className="bg-white shadow-sm border-b">
//                 <div className="max-w-7xl mx-auto px-6 py-4">
//                     <div className="flex gap-4">
//                         <button
//                             onClick={() => setView('patient')}
//                             className={`px-4 py-2 rounded-lg font-medium transition-colors ${
//                                 view === 'patient'
//                                     ? 'bg-blue-600 text-white'
//                                     : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
//                             }`}
//                         >
//                             Giao diện Bệnh nhân
//                         </button>
//                         <button
//                             onClick={() => setView('doctor')}
//                             className={`px-4 py-2 rounded-lg font-medium transition-colors ${
//                                 view === 'doctor'
//                                     ? 'bg-blue-600 text-white'
//                                     : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
//                             }`}
//                         >
//                             Giao diện Bác sĩ
//                         </button>
//                         <button
//                             onClick={() => setView('clinic')}
//                             className={`px-4 py-2 rounded-lg font-medium transition-colors ${
//                                 view === 'clinic'
//                                     ? 'bg-blue-600 text-white'
//                                     : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
//                             }`}
//                         >
//                             Giao diện Phòng khám
//                         </button>
//                     </div>
//                 </div>
//             </div>
//
//             {view === 'patient' && <PatientAppointments appointments={sampleAppointments} />}
//             {view === 'doctor' && <DoctorAppointments appointments={sampleAppointments} />}
//             {view === 'clinic' && <ClinicAppointments appointments={sampleAppointments} />}
//         </div>
//     );
// }