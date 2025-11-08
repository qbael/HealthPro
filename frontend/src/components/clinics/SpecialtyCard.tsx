'use client'

import {renderSpecialtyIcon} from "@/lib/icon-provider";
import Link from "next/link";
import {Button} from "@/components/ui/button"
import api from "@/lib/axios";
import {useAuth} from "@/contexts/AuthContext";
import {toast} from "sonner";
import {useRouter} from "next/navigation";

const SpecialtyCard = ({ specialty } : any) => {
    const { user } = useAuth()
    const router = useRouter()
    console.log(specialty)

    const checkClinicHasRegisteredTime = async () => {
        try {
            const res = await api.get(`/v2/clinics/${user?.userRoleId}`);
            const clinic = res.data.data;

            if (
                !clinic.weekdayOpenHour ||
                !clinic.weekdayCloseHour ||
                !clinic.weekendOpenHour ||
                !clinic.weekendCloseHour
            ) {
                toast.error("Phòng khám chưa đăng ký thời gian làm.");
                return;
            }

            router.push(`/clinic/specialty/schedules/${specialty.id}`);
        }
        catch (err) {
            toast.error("Không thể kiểm tra thời gian làm của phòng khám.");
            console.error(err);
        }
    }

    return (
        <div
            key={specialty.id}
            className="group bg-white rounded-2xl shadow-md hover:shadow-xl
                       transition-all duration-300 p-6 md:p-8
                       border border-gray-100 hover:border-cyan-300
                       transform hover:-translate-y-1
                       disabled:opacity-50 disabled:cursor-not-allowed"
        >
            <div className="flex items-center gap-4 md:gap-6">
                <div className={`flex-shrink-0 group-hover:scale-110 transition-transform duration-300`}>
                    {renderSpecialtyIcon(specialty.specialtyName)}
                </div>

                <h3 className="text-left text-lg md:text-xl font-semibold text-gray-800
                             group-hover:text-cyan-600 transition-colors">
                    {specialty.specialtyName}
                </h3>
            </div>
            <div className='flex items-center gap-3 mt-5'>
                <Button asChild className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    <Link href={`/clinic/specialty/doctors/${specialty.specialtyId}`}>Mời Bác Sĩ</Link>
                </Button>
                <Button asChild className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                    <Link href={`/clinic/specialty/invitations/${specialty.id}`}>Lời Mời Đã Gửi</Link>
                </Button>
                <Button className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'
                        onClick={checkClinicHasRegisteredTime}>
                    Đăng Ký Lịch Làm
                </Button>
            </div>
        </div>
    );
};

export default SpecialtyCard;