import {Clock, Phone, ChevronLeft, ChevronRight} from 'lucide-react';
import {DOCTORS_API_URL} from "@/lib/utils";
import Image from "next/image";
import {DoctorType} from "@/types/doctor-types";

export default async function DoctorProfile({params}: { params: Promise<{ id: string }> }) {
    const {id} = await params;

    const res = await fetch(`${DOCTORS_API_URL}/${id}`, {
        method: "GET",
        cache: 'no-store'
    });

    const body = await res.json();

    const doctor: DoctorType = body.data;

    return (
        <div className="max-w-4xl mx-auto mt-20 bg-gray-50 min-h-screen">
            <div className="bg-white rounded-lg shadow-sm p-6 mb-4">
                <div className="flex items-start gap-4">
                    <Image
                        src={doctor.avatarUrl}
                        alt={doctor.fullName}
                        width={100}
                        height={100}
                        className="w-24 h-24 rounded-full object-cover"
                    />
                    <div className="flex-1">
                        <h1 className="text-xl font-bold text-gray-900 mb-2">{doctor.fullName}</h1>
                        <div className="flex items-center gap-2 text-blue-600 mb-2">
                            <span className="px-2 py-1 bg-blue-50 rounded text-sm font-medium">Bác sĩ</span>
                        </div>
                        <div className="space-y-1 text-sm">
                            <div>
                                <span className="text-gray-600">Chuyên khoa:</span>
                                <span className="text-blue-600 ml-1">
                                    {doctor.doctorSpecialties.map(specialty => specialty.specialty.name).join(' • ')}
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

                <div className="mt-4 bg-orange-50 border-l-4 border-orange-400 p-3 rounded">
                    <p className="text-sm text-orange-800">
                        <span className="font-semibold">Lưu ý</span><br/>
                        Nếu bệnh nhân bận việc không đến khám được vui lòng hủy lịch khám đã đặt và đặt lại ngày khác. Xin cám ơn!
                    </p>
                </div>
            </div>

            <div className="bg-white rounded-lg shadow-sm p-6 mb-4">
                <div className="flex items-center justify-between mb-4">
                    <h2 className="text-lg font-bold text-gray-900">Đặt khám nhanh</h2>
                    <div className="flex gap-2">
                        <button className="p-1 hover:bg-gray-100 rounded">
                            <ChevronLeft className="w-5 h-5"/>
                        </button>
                        <button className="p-1 hover:bg-gray-100 rounded">
                            <ChevronRight className="w-5 h-5"/>
                        </button>
                    </div>
                </div>

                <div className="flex gap-2 overflow-x-auto pb-4 mb-4">
                    {/*{availableDates.map((date, idx) => (*/}
                    {/*    <button*/}
                    {/*        key={idx}*/}
                    {/*        onClick={() => setSelectedDate(idx)}*/}
                    {/*        className={`flex-shrink-0 text-center p-3 rounded-lg border-2 transition-all ${*/}
                    {/*            selectedDate === idx*/}
                    {/*                ? 'border-blue-500 bg-blue-50'*/}
                    {/*                : 'border-gray-200 hover:border-blue-300'*/}
                    {/*        }`}*/}
                    {/*    >*/}
                    {/*        <div className="text-xs text-gray-600 mb-1">*/}
                    {/*            Th {date.day < 10 ? `0${date.day}` : date.day}/{date.month < 10 ? `0${date.month}` : date.month}*/}
                    {/*        </div>*/}
                    {/*        <div className="text-xs text-blue-600 font-medium">*/}
                    {/*            {date.slots} khung giờ*/}
                    {/*        </div>*/}
                    {/*    </button>*/}
                    {/*))}*/}
                </div>

                <div className="mb-4">
                    <div className="flex items-center gap-2 mb-3">
                        <Clock className="w-4 h-4 text-gray-600"/>
                        <span className="text-sm font-medium text-gray-700">Buổi chiều</span>
                    </div>
                    <div className="grid grid-cols-3 gap-2">
                        {/*{timeSlots.map((time, idx) => (*/}
                        {/*    <button*/}
                        {/*        key={idx}*/}
                        {/*        onClick={() => setSelectedTime(idx)}*/}
                        {/*        className={`p-3 text-sm rounded-lg border-2 transition-all ${*/}
                        {/*            selectedTime === idx*/}
                        {/*                ? 'border-blue-500 bg-blue-50 text-blue-700'*/}
                        {/*                : 'border-gray-200 hover:border-blue-300 text-gray-700'*/}
                        {/*        }`}*/}
                        {/*    >*/}
                        {/*        {time}*/}
                        {/*    </button>*/}
                        {/*))}*/}
                    </div>
                </div>
            </div>

            <div className="bg-white rounded-lg shadow-sm p-6 mb-4">
                <h2 className="text-lg font-bold text-gray-900 mb-3">Giới thiệu</h2>
                <p className="text-sm text-gray-700 leading-relaxed mb-3">{doctor.bio}</p>
                <button className="text-blue-600 text-sm mt-2 hover:underline">...Xem thêm</button>
            </div>

            <div className="bg-white rounded-lg shadow-sm p-6 mb-4">
                <h2 className="text-lg font-bold text-gray-900 mb-3">Chuyên khám</h2>
                <div className="grid grid-cols-2 gap-3">
                    {doctor.doctorSpecialties.map((specialty, idx) => (
                        <div key={idx} className="flex items-center gap-2 text-sm text-gray-700">
                            <h3 _ngcontent-serverapp-c40="" className="text-base">
                                <span _ngcontent-serverapp-c40=""
                                      className="flex items-center bg-slate-100 hover:bg-blue-500 hover:text-white transform transition rounded-full px-2 py-1">
                                    <svg _ngcontent-serverApp-c40="" width="18" height="18" viewBox="0 0 24 24"
                                         fill="none"
                                         className="flex-none mr-1">
                                    <path _ngcontent-serverApp-c40=""
                                          d="M12 22C17.5 22 22 17.5 22 12C22 6.5 17.5 2 12 2C6.5 2 2 6.5 2 12C2 17.5 6.5 22 12 22Z"
                                          stroke="currentColor" stroke-width="1.5"
                                          stroke-linecap="round" stroke-linejoin="round">
                                    </path>
                                    <path _ngcontent-serverApp-c40="" d="M7.75 11.9999L10.58 14.8299L16.25 9.16992"
                                          stroke="currentColor" stroke-width="1.5" stroke-linecap="round"
                                          stroke-linejoin="round">
                                    </path>
                                </svg>
                                    {specialty.specialty.name}
                                </span>
                            </h3>
                        </div>
                    ))}
                </div>
            </div>

            <div className="bg-white rounded-lg shadow-sm p-6 mb-4">
                {/*<h2 className="text-lg font-bold text-gray-900 mb-3">Địa chỉ</h2>*/}
                {/*<div className="bg-gradient-to-br from-blue-500 to-cyan-400 rounded-lg p-4 text-white">*/}
                {/*    <div className="flex items-start gap-2 mb-2">*/}
                {/*        <MapPin className="w-5 h-5 flex-shrink-0 mt-0.5"/>*/}
                {/*        <p className="text-sm">{doctor.address}</p>*/}
                {/*    </div>*/}
                {/*    <span className="inline-block px-3 py-1 bg-white/20 backdrop-blur-sm rounded-full text-xs">*/}
                {/*        Mở bản đồ*/}
                {/*    </span>*/}
                {/*</div>*/}

                <a _ngcontent-serverapp-c57="" target="_blank"
                   className="block relative overflow-hidden bg-primary text-white hover:text-white text-base rounded-2xl m-4 md:m-6 flex-1 bg-right-bottom"
                   href={`https://www.google.com/maps/search/?api=1&amp;query=${doctor.address}`}>
                    <img
                    _ngcontent-serverapp-c57="" src="/map.webp" width="700" height="460" alt="Map"
                    className="absolute bottom-0 right-0"/>
                    <div _ngcontent-serverapp-c57=""
                         className="relative bg-gradient-to-br from-blue-700 via-primary p-6 h-full"><h2
                        _ngcontent-serverapp-c57="" className="text-lg font-semibold text-white">Địa chỉ</h2>
                        <div _ngcontent-serverapp-c57="" className="mt-4 flex flex-col items-start"><h3
                            _ngcontent-serverapp-c57="" className="text-white">{doctor.address}</h3>
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

            <div className="bg-white border-t border-gray-200 p-4 sticky bottom-0">
                <div className="flex items-center justify-between max-w-4xl mx-auto">
                    <div className="text-sm">
                        <div className="text-gray-600">Hỗ trợ đặt khám</div>
                        <div className="font-bold text-gray-900 flex items-center gap-1">
                            <Phone className="w-4 h-4"/>
                            1800-2805
                        </div>
                    </div>
                    <button
                        className="bg-blue-600 hover:bg-blue-700 text-white px-8 py-3 rounded-lg font-medium transition-colors">
                        ĐẶT KHÁM NGAY
                    </button>
                </div>
            </div>
        </div>
    );
}
;
