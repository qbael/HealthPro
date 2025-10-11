"use client"

import React, { useState } from 'react';
import { ChevronLeft, ChevronRight } from 'lucide-react';

interface Doctor {
    id: number;
    name: string;
    image: string;
    specialty: string;
    hospital: string;
    tags?: string[];
}

interface Hospital {
    id: number;
    name: string;
    image: string;
    address: string;
    schedule: {
        weekday: string;
        sunday?: string;
    };
}

const doctors: Doctor[] = [
    {
        id: 1,
        name: "TS. BS Trần Anh Tuấn",
        image: "https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop",
        specialty: "Nhi khoa",
        hospital: "Bệnh viện Nhi Đồng 1"
    },
    {
        id: 2,
        name: "BS. CK2 Huỳnh Phan Phúc Linh",
        image: "https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=200&h=200&fit=crop",
        specialty: "Cơ xương khớp",
        hospital: "Bệnh viện Chợ Rẫy"
    },
    {
        id: 3,
        name: "ThS. BS. CK2 Hoàng Thị Thu Huyền",
        image: "https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Ung bướu",
        tags: ["Sản phụ khoa"]
    },
    {
        id: 4,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 5,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 6,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 7,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 8,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 9,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 10,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 11,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 12,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    },
    {
        id: 13,
        name: "BS. CK2 Lý Thái Lộc",
        image: "https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop",
        specialty: "Sản phụ khoa",
        hospital: "Bệnh viện Hùng Vương",
        tags: ["Sản phụ khoa", "Vô sinh hiếm muộn"]
    }
];

const hospitals: Hospital[] = [
    {
        id: 1,
        name: "Bệnh viện Ung Bướu TP.HCM",
        image: "https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop",
        address: "47 Nguyễn Huy Lượng, Phường Bình Thạnh, TP. Hồ Chí Minh",
        schedule: {
            weekday: "7h30 - 16h30",
            sunday: "7h30 - 11h30"
        }
    },
    {
        id: 2,
        name: "Bệnh viện Quân Y 175",
        image: "https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop",
        address: "Số 786 Nguyễn Kiệm, Phường Hạnh Thông, TP. Hồ Chí Minh",
        schedule: {
            weekday: "7h - 16h30",
            sunday: "7h - 16h"
        }
    },
    {
        id: 3,
        name: "Bệnh viện Y Học Cổ Truyền TP.HCM",
        image: "https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop",
        address: "179 -187 Nam Kỳ Khởi Nghĩa, Phường Xuân Hoà, TP. Hồ Chí Minh",
        schedule: {
            weekday: "7h - 19h",
            sunday: "7h - 11h30"
        }
    },
    {
        id: 4,
        name: "Bệnh viện Da Khoa Thủ Đức",
        image: "https://images.unsplash.com/photo-1632833239869-a37e3a5806d2?w=400&h=250&fit=crop",
        address: "29 Phu Châu, Phường Tam Bình, TP. Hồ Chí Minh",
        schedule: {
            weekday: "7h - 16h30"
        }
    }
];

export default function DoctorBookingUI() {
    const [doctorIndex, setDoctorIndex] = useState(0);
    const [hospitalIndex, setHospitalIndex] = useState(0);

    const visibleDoctors = 4;
    const visibleHospitals = 3;

    const nextDoctors = () => {
        if (doctorIndex < doctors.length - visibleDoctors) {
            setDoctorIndex(doctorIndex + 3);
        }
    };

    const prevDoctors = () => {
        if (doctorIndex > 0) {
            setDoctorIndex(doctorIndex - 3);
        }
    };

    const nextHospitals = () => {
        if (hospitalIndex < hospitals.length - visibleHospitals) {
            setHospitalIndex(hospitalIndex + 1);
        }
    };

    const prevHospitals = () => {
        if (hospitalIndex > 0) {
            setHospitalIndex(hospitalIndex - 1);
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 p-8 mt-9">
            <div className="max-w-7xl mx-auto mb-12">
                <div className="flex justify-between items-center mb-6">
                    <div>
                        <h2 className="text-2xl font-bold text-gray-900 mb-1">Đặt khám bác sĩ</h2>
                        <p className="text-gray-600">Phiếu khám kèm số thứ tự và thời gian của bạn được xác nhận.</p>
                    </div>
                    <button className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-lg flex items-center gap-2 transition-colors">
                        Xem thêm <ChevronRight size={18} />
                    </button>
                </div>

                <div className="relative">
                    <button
                        onClick={prevDoctors}
                        disabled={doctorIndex === 0}
                        className="absolute left-0 top-1/2 -translate-y-1/2 -translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                    >
                        <ChevronLeft size={24} />
                    </button>

                    <div className="overflow-hidden">
                        <div
                            className="flex gap-5 transition-transform duration-300"
                            style={{ transform: `translateX(-${doctorIndex * (100 / visibleDoctors)}%)` }}
                        >
                            {doctors.map((doctor) => (
                                <div key={doctor.id} className="min-w-[calc(25%-18px)] bg-white rounded-lg my-2 shadow-md p-6 hover:shadow-lg transition-shadow">
                                    <div className="flex flex-col items-center">
                                        <img
                                            src={doctor.image}
                                            alt={doctor.name}
                                            className="w-24 h-24 rounded-full object-cover mb-4"
                                        />
                                        <h3 className="font-semibold text-center text-gray-900 mb-2">{doctor.name}</h3>
                                        <div className="text-center">
                                            <p className="text-sm text-gray-600">{doctor.specialty}</p>
                                            <p className="text-sm text-gray-500">{doctor.hospital}</p>
                                            {/*{doctor.tags && (*/}
                                            {/*    <div className="flex flex-wrap gap-1 justify-center mt-2">*/}
                                            {/*        {doctor.tags.map((tag, i) => (*/}
                                            {/*            <span key={i} className="text-xs text-gray-600">{tag}</span>*/}
                                            {/*        ))}*/}
                                            {/*    </div>*/}
                                            {/*)}*/}
                                        </div>
                                    </div>
                                    <button className="w-full bg-white border border-gray-300 text-gray-700 py-2 rounded-lg hover:bg-gray-50 flex items-center justify-between px-4 mt-5 transition-colors">
                                        <span>Đặt lịch khám</span>
                                        <ChevronRight size={18} />
                                    </button>
                                </div>
                            ))}
                        </div>
                    </div>

                    <button
                        onClick={nextDoctors}
                        disabled={doctorIndex >= doctors.length - visibleDoctors}
                        className="absolute right-0 top-1/2 -translate-y-1/2 translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                    >
                        <ChevronRight size={24} />
                    </button>
                </div>
            </div>

            <div className="max-w-7xl mx-auto">
                <div className="flex justify-between items-center mb-6">
                    <div>
                        <h2 className="text-2xl font-bold text-gray-900 mb-1">Đặt khám bệnh viện</h2>
                        <p className="text-gray-600">Đặt khám và thanh toán dễ có phiếu khám (thời gian, số thứ tự) trước khi đi khám các bệnh viện kết nối chính thức với YouMed.</p>
                    </div>
                    <button className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-lg flex items-center gap-2 transition-colors">
                        Xem thêm <ChevronRight size={18} />
                    </button>
                </div>

                <div className="relative">
                    <button
                        onClick={prevHospitals}
                        disabled={hospitalIndex === 0}
                        className="absolute left-0 top-1/2 -translate-y-1/2 -translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                    >
                        <ChevronLeft size={24} />
                    </button>

                    <div className="overflow-hidden">
                        <div
                            className="flex gap-6 transition-transform duration-300"
                            style={{ transform: `translateX(-${hospitalIndex * (100 / visibleHospitals)}%)` }}
                        >
                            {hospitals.map((hospital) => (
                                <div key={hospital.id} className="min-w-[calc(33.333%-16px)] bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow">
                                    <div className="relative h-48">
                                        <img
                                            src={hospital.image}
                                            alt={hospital.name}
                                            className="w-full h-full object-cover"
                                        />
                                        <div className="absolute bottom-4 left-4 bg-white rounded-full p-3 shadow-lg">
                                            <div className="w-12 h-12 bg-blue-100 rounded-full flex items-center justify-center">
                                                <span className="text-blue-600 font-bold text-xs">H</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="p-5">
                                        <h3 className="font-semibold text-gray-900 mb-2">{hospital.name}</h3>
                                        <p className="text-sm text-gray-600 mb-4">{hospital.address}</p>
                                        <div className="space-y-1 text-sm">
                                            <div className="flex justify-between">
                                                <span className="text-gray-600">Thứ 2 - Thứ 6:</span>
                                                <span className="text-gray-900">{hospital.schedule.weekday}</span>
                                            </div>
                                            {hospital.schedule.sunday && (
                                                <div className="flex justify-between">
                                                    <span className="text-gray-600">Thứ 7 - CN:</span>
                                                    <span className="text-gray-900">{hospital.schedule.sunday}</span>
                                                </div>
                                            )}
                                            {!hospital.schedule.sunday && (
                                                <div className="flex justify-between">
                                                    <span className="text-gray-600">Thứ 7:</span>
                                                    <span className="text-gray-900">{hospital.schedule.weekday.split(' - ')[0]} - 16h</span>
                                                </div>
                                            )}
                                            <div className="flex justify-between">
                                                <span className="text-gray-600">Chủ nhật:</span>
                                                <span className="text-gray-900">{hospital.schedule.sunday || '7h - 11h30'}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>

                    <button
                        onClick={nextHospitals}
                        disabled={hospitalIndex >= hospitals.length - visibleHospitals}
                        className="absolute right-0 top-1/2 -translate-y-1/2 translate-x-4 bg-white rounded-full p-2 shadow-lg z-10 disabled:opacity-50 disabled:cursor-not-allowed hover:bg-gray-50"
                    >
                        <ChevronRight size={24} />
                    </button>
                </div>
            </div>
        </div>
    );
}