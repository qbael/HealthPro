import {CLINICS_API_URL, DOCTORS_API_URL} from "@/lib/utils";
import DoctorCarousel from "@/components/layout/DoctorCarousel";
import ClinicCarousel from "@/components/layout/ClinicCarousel";

export default async function CarouselPage() {
    const res = Promise.all([
        fetch(`${DOCTORS_API_URL}`, {
            method: "GET",
            cache: 'no-store'
        }),
        fetch(`${CLINICS_API_URL}`, {
            method: "GET",
            cache: 'no-store'
        })
    ])
    const [doctorsRes, clinicsRes] = await res;
    let doctors = [];
    let clinics = [];

    if (doctorsRes.ok) {
        const doctorsData = await doctorsRes.json();
        doctors = doctorsData.data.content;
    }

    if (clinicsRes.ok) {
        const clinicsData = await clinicsRes.json();
        clinics = clinicsData.data.content;
    }


    return (
        <div className="min-h-screen bg-gray-50 p-8 mt-5 font-medium text-gray-900">
            <DoctorCarousel doctors={doctors} />
            <ClinicCarousel clinics={clinics} />
        </div>
    );
}