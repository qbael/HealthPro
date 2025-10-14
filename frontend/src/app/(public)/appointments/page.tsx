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

    if (!doctorsRes.ok) {
        throw new Error('Failed to fetch doctors');
    }

    if (!clinicsRes.ok) {
        throw new Error('Failed to fetch clinics');
    }

    const doctorsData = await doctorsRes.json();
    const clinicsData = await clinicsRes.json();

    const doctors = doctorsData.data;
    const clinics = clinicsData.data;


    return (
        <div className="min-h-screen bg-gray-50 p-8 mt-12 font-medium text-gray-900">
            <DoctorCarousel doctors={doctors} />
            <ClinicCarousel clinics={clinics} />
        </div>
    );
}