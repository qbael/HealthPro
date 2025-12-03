// components/search/SearchResults.tsx
import { DoctorBookingCard } from "@/components/cards/DoctorBookingCard";
import { ClinicBookingCard } from "@/components/cards/ClinicBookingCard";

const SEARCH_API_URL = "http://localhost:4004/api/v2/search";

interface SearchResultsProps {
    searchParams: Record<string, string | undefined>;
}

export default async function SearchResults({ searchParams }: SearchResultsProps) {
    const q = searchParams.q || "";
    const type = searchParams.type || "all";
    const specialty = searchParams.specialty || "";

    const queryParams = new URLSearchParams();
    if (q) queryParams.set("q", q);
    if (type !== "all") queryParams.set("type", type);
    if (specialty && specialty !== "all") queryParams.set("specialty", specialty);
    queryParams.set("page", "0");
    queryParams.set("limit", "30");

    let doctors: any[] = [];
    let clinics: any[] = [];

    try {
        const res = await fetch(`${SEARCH_API_URL}?${queryParams.toString()}`, {
            cache: "no-store",
        });

        if (res.ok) {
            const json = await res.json();
            if (json.success) {
                doctors = json.data.doctors || [];
                clinics = json.data.clinics || [];
            }
        }
    } catch (error) {
        console.error("Search error:", error);
    }

    const totalResults = doctors.length + clinics.length;

    if (totalResults === 0) {
        return (
            <div className="text-center py-20 bg-white rounded-lg shadow-sm">
                <div className="mb-4">
                    <svg
                        className="w-24 h-24 mx-auto text-gray-300"
                        fill="none"
                        stroke="currentColor"
                        viewBox="0 0 24 24"
                    >
                        <path
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            strokeWidth={1.5}
                            d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"
                        />
                    </svg>
                </div>
                <h3 className="text-2xl font-semibold text-gray-800 mb-3">
                    Không tìm thấy kết quả
                </h3>
                <p className="text-gray-600 mb-2">
                    {q && `Không có kết quả cho từ khóa "${q}"`}
                    {specialty && !q && `Không có kết quả cho chuyên khoa "${specialty}"`}
                </p>
                <p className="text-sm text-gray-500 mt-4">
                    💡 Thử tìm: <span className="font-medium">"đau đầu"</span>, <span className="font-medium">"tiểu đường"</span>, <span className="font-medium">"bệnh viện Chợ Rẫy"</span>
                </p>
            </div>
        );
    }

    return (
        <div className="space-y-12">
            {doctors.length > 0 && (
                <section>
                    <h2 className="text-2xl font-bold text-gray-800 mb-6">
                        Bác sĩ ({doctors.length})
                    </h2>
                    {/* ✅ THAY ĐỔI: Grid 2 cột giống clinic */}
                    <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 max-w-7xl mx-auto">
                        {doctors.map((doctor) => (
                            <DoctorBookingCard key={doctor.id} doctor={doctor} />
                        ))}
                    </div>
                </section>
            )}

            {clinics.length > 0 && (
                <section>
                    <h2 className="text-2xl font-bold text-gray-800 mb-6">
                        Phòng khám & Bệnh viện ({clinics.length})
                    </h2>
                    <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 max-w-7xl mx-auto">
                        {clinics.map((clinic) => (
                            <ClinicBookingCard key={clinic.id} clinic={clinic} />
                        ))}
                    </div>
                </section>
            )}
        </div>
    );
}