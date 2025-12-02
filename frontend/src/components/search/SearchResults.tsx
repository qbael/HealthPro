// components/search/SearchResults.tsx
import { DoctorBookingCard } from "@/components/cards/DoctorBookingCard";
import { ClinicBookingCard } from "@/components/cards/ClinicBookingCard";

const SEARCH_API_URL = "http://localhost:4004/api/v2/search";

interface SearchResultsProps {
  searchParams: Record<string, string | undefined>; // ← ĐÃ ĐƯỢC await → không còn Promise
}

export default async function SearchResults({ searchParams }: SearchResultsProps) {
  const q = searchParams.q || "";
  const type = searchParams.type || "all";
  const specialty = searchParams.specialty || "";

  const queryParams = new URLSearchParams();
  if (q) queryParams.set("q", q);
  if (type !== "all") queryParams.set("type", type);
  if (specialty) queryParams.set("specialty", specialty);
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
      <div className="text-center py-20 bg-white rounded-lg">
        <h3 className="text-2xl font-medium text-gray-700 mb-2">
          Không tìm thấy kết quả cho "<span className="text-cyan-500">{q || "trống"}</span>"
        </h3>
        <p className="text-gray-500">
          Thử tìm: "đau đầu", "tiểu đường", "bệnh viện Chợ Rẫy", "da liễu"...
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
          <div className="space-y-6 max-w-5xl mx-auto">
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