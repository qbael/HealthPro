import React from 'react'
import {ChevronRight} from "lucide-react";

import {Doctor} from "@/types/doctor-types";

const DoctorCard = () => {
    return (
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
    )
}

export default DoctorCard