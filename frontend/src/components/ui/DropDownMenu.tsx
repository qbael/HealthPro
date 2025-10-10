"use client"

import React, {useEffect, useState} from "react";
import {ChevronDown} from "lucide-react";

const DropDownMenu = () => {
    const [isOpen, setIsOpen] = useState(false);

    // const toggleDropdown = () => {
    //     setIsOpen(!isOpen);
    // };
    const [isMounted, setIsMounted] = useState(false);

    useEffect(() => {
        setIsMounted(true);
    }, []);

    if (!isMounted) return null;

    return (
        <div className="relative inline-block text-left"
             onMouseLeave={() => setIsOpen(false)}
             onMouseEnter={() => setIsOpen(true)}
        >
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

            <div
                className={`absolute right-0 w-50 shadow-2xl border-1 border-black rounded-md bg-white transform transition-all duration-300 origin-top-right font-bold
                 ${isOpen ? "opacity-100 scale-100 translate-y-0" : "opacity-0 scale-95 -translate-y-2 pointer-events-none"}`}
            >
                <div className="px-3 py-3 gap-4">
                    <a
                        href="#"
                        className="block rounded-md py-3 px-3 text-sm mb-2 text-gray-700 hover:bg-blue-100"
                    >
                        Đặt khám bác sĩ
                    </a>
                    <a
                        href="#"
                        className="block rounded-md py-3 px-3 text-sm text-gray-700 hover:bg-blue-100"
                    >
                         Đặt khám phòng khám
                    </a>
                </div>
            </div>
        </div>
    );
};

export default DropDownMenu;
