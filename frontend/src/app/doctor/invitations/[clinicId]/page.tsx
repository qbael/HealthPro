import React from 'react';
import Image from "next/image";
import {Award, Calendar, Clock, MapPin, Star} from "lucide-react";
import {createServerApi} from "@/lib/axiosServer";

const Page = async ({ params } : any) => {
    const { clinicId } = await params;
    const api = await createServerApi()
    const res = await api.get(`v1/clinics/${clinicId}`)
    const clinic = res.data

    return (
        <main className="min-h-screen bg-gradient-to-br from-blue-50 mt-16 to-indigo-50">
            <div className="relative h-80 w-full overflow-hidden">
                <Image
                    src={clinic.avatarUrl}
                    alt={clinic.clinicName}
                    width={1920}
                    height={320}
                    className="w-full h-full object-cover"
                />
                <div className="absolute inset-0 bg-gradient-to-t from-black/60 to-transparent" />

                <div className="absolute bottom-6 left-8">
                    <div className="flex items-end gap-6">
                        {clinic.logoUrl && (
                            <div className="w-28 h-28 rounded-2xl border-4 border-white shadow-xl overflow-hidden bg-white">
                                <Image
                                    src={clinic.logoUrl}
                                    alt="Logo"
                                    width={112}
                                    height={112}
                                    className="w-full h-full object-cover"
                                />
                            </div>
                        )}
                        <div className="pb-2">
                            <h1 className="text-4xl font-bold text-white mb-2 drop-shadow-lg">
                                {clinic.clinicName}
                            </h1>
                            <div className="flex items-center gap-2 text-white/90">
                                <Star className="w-5 h-5 fill-yellow-400 text-yellow-400" />
                                <span className="font-semibold">4.8</span>
                                <span className="text-sm">(256 đánh giá)</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="max-w-7xl mx-auto px-6 py-8">
                <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                    <div className="lg:col-span-2 space-y-6">
                        <div className="bg-white rounded-2xl shadow-md p-8">
                            <h2 className="text-2xl font-bold text-gray-800 mb-4 flex items-center gap-2">
                                <Award className="w-6 h-6 text-blue-600" />
                                Giới Thiệu
                            </h2>
                            <p className="text-gray-600 leading-relaxed">
                                {clinic.description}
                            </p>
                        </div>

                        {clinic.clinicSpecialties && clinic.clinicSpecialties.length > 0 && (
                            <div className="bg-white rounded-2xl shadow-md p-8">
                                <h2 className="text-2xl font-bold text-gray-800 mb-4">
                                    Chuyên Khoa
                                </h2>
                                <div className="flex flex-wrap gap-3">
                                    {clinic.clinicSpecialties.map((specialty : any) => (
                                        <span
                                            key={specialty.id}
                                            className="px-4 py-2 bg-blue-100 text-blue-700 rounded-full text-sm font-medium hover:bg-blue-200 transition-colors cursor-pointer"
                                        >
                                            {specialty.specialty.name}
                                        </span>
                                    ))}
                                </div>
                            </div>
                        )}

                        <div className="bg-white rounded-2xl shadow-md p-8">
                            <h2 className="text-2xl font-bold text-gray-800 mb-6 flex items-center gap-2">
                                <Clock className="w-6 h-6 text-blue-600" />
                                Giờ Làm Việc
                            </h2>
                            <div className="space-y-4">
                                <div className="flex items-center justify-between p-4 bg-gray-50 rounded-xl">
                                    <div className="flex items-center gap-3">
                                        <Calendar className="w-5 h-5 text-gray-600" />
                                        <span className="font-semibold text-gray-800">Thứ 2 - Thứ 6</span>
                                    </div>
                                    <span className="text-blue-600 font-semibold">
                                        {clinic.weekdayOpenHour} - {clinic.weekdayCloseHour}
                                    </span>
                                </div>
                                <div className="flex items-center justify-between p-4 bg-gray-50 rounded-xl">
                                    <div className="flex items-center gap-3">
                                        <Calendar className="w-5 h-5 text-gray-600" />
                                        <span className="font-semibold text-gray-800">Thứ 7 - Chủ Nhật</span>
                                    </div>
                                    <span className="text-blue-600 font-semibold">
                                        {clinic.weekendOpenHour} - {clinic.weekendCloseHour}
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="space-y-6">
                        <div className="bg-white rounded-2xl shadow-md p-6">
                            <h3 className="text-xl font-bold text-gray-800 mb-4">
                                Thông Tin Liên Hệ
                            </h3>
                            <div className="space-y-4">
                                {clinic.address && (
                                    <div className="flex items-start gap-3">
                                        <MapPin className="w-5 h-5 text-blue-600 mt-1 flex-shrink-0" />
                                        <div>
                                            <p className="text-sm text-gray-500 mb-1">Địa chỉ</p>
                                            <p className="text-gray-800">{clinic.address}</p>
                                        </div>
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="bg-white rounded-2xl col-span-2 shadow-md p-6">
                        <a _ngcontent-serverapp-c57="" target="_blank"
                           className="block relative overflow-hidden bg-primary text-white hover:text-white text-base rounded-2xl m-4 md:m-6 flex-1 bg-right-bottom"
                           href={`https://www.google.com/maps/search/?api=1&amp;query=${clinic.address}`}>
                            <img
                                _ngcontent-serverapp-c57="" src="/map.webp" width="700" height="460" alt="Map"
                                className="absolute bottom-0 right-0"/>
                            <div _ngcontent-serverapp-c57=""
                                 className="relative bg-gradient-to-br from-blue-700 via-primary p-6 h-full"><h2
                                _ngcontent-serverapp-c57="" className="text-lg font-semibold text-white">Địa chỉ</h2>
                                <div _ngcontent-serverapp-c57="" className="mt-4 flex flex-col items-start"><h3
                                    _ngcontent-serverapp-c57="" className="text-white">{clinic.address}</h3>
                                    <div _ngcontent-serverapp-c57=""
                                         className="rounded-full px-2 py-1 bg-white text-primary text-sm font-semibold mt-6 flex items-center">
                                        <svg _ngcontent-serverApp-c57="" xmlns="http://www.w3.org/2000/svg" width="16"
                                             height="16" viewBox="0 0 512 512" className="mr-1">
                                            <path _ngcontent-serverApp-c57="" fill="currentColor"
                                                  d="M48.17 113.34A32 32 0 0032 141.24V438a32 32 0 0047 28.37c.43-.23.85-.47 1.26-.74l84.14-55.05a8 8 0 003.63-6.72V46.45a8 8 0 00-12.51-6.63zM212.36 39.31A8 8 0 00200 46v357.56a8 8 0 003.63 6.72l96 62.42A8 8 0 00312 466V108.67a8 8 0 00-3.64-6.73zM464.53 46.47a31.64 31.64 0 00-31.5-.88 12.07 12.07 0 00-1.25.74l-84.15 55a8 8 0 00-3.63 6.72v357.46a8 8 0 0012.52 6.63l107.07-73.46a32 32 0 0016.41-28v-296a32.76 32.76 0 00-15.47-28.21z"></path>
                                        </svg>
                                        Mở bản đồ
                                    </div>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </main>
    );
};

export default Page;