import React, {useState} from "react";
import {ChevronDown} from "lucide-react";
import Link from "next/link";

const DropDownMenu = () => {
    const [isOpen, setIsOpen] = useState(false);

    return (
        <div className="relative inline-block text-left"
             onMouseLeave={() => setIsOpen(false)}
             onMouseEnter={() => setIsOpen(true)}
        >
            <Link href={"/booking"}>
                <button
                    className="inline-flex justify-center w-full rounded-md px-4 py-2 bg-white text-sm font-bold
                     text-gray-700 hover:bg-blue-100 cursor-pointer focus:outline-none transition"
                >
                    Đặt lịch khám
                    <ChevronDown
                        className={`ml-1 h-5 w-5 transform transition-transform duration-300 ${
                            isOpen ? "rotate-180" : "rotate-0"
                        }`}
                    />
                </button>
            </Link>
            <div
                className={`absolute right-0 w-60 shadow-2xl shadow-black rounded-md bg-gray-100 transform transition-all duration-300 origin-top-right
                 ${isOpen ? "opacity-100 scale-100 translate-y-0" : "opacity-0 scale-95 -translate-y-2 pointer-events-none"}`}
            >
                <div className="">
                    <Link
                        href="/booking/doctors"
                        className="block rounded-md shadow-md shadow-gray-200 py-3 px-3 m-3 text-sm font-bold text-gray-700 hover:bg-blue-100 hover:text-blue-600"
                    >
                        Đặt khám bác sĩ
                    </Link>
                    <Link
                        href="/booking/clinics"
                        className="block rounded-md shadow-md shadow-gray-200 py-3 px-3 m-3 text-sm font-bold text-gray-700 hover:bg-blue-100 hover:text-blue-600"
                    >
                         Đặt khám phòng khám
                    </Link>
                    <div className={"bg-gray-200 font-medium rounded-b-md p-3 text-sm"}>
                        Liên hệ: 1900 28 28
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DropDownMenu;
