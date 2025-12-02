// app/search/page.tsx
import SearchBarWithFilters from "@/components/search/SearchBarWithFilters";
import SearchResults from "@/components/search/SearchResults";

export default async function SearchPage({
  searchParams, // ← ĐÚNG: là Promise, KHÔNG cần khai báo kiểu
}: {
  searchParams: Promise<Record<string, string | undefined>>;
}) {
  // PHẢI await để lấy được giá trị thật
  const params = await searchParams;

  return (
    <div className="min-h-screen bg-gray-100">
      <SearchBarWithFilters />
      <div className="max-w-7xl mx-auto px-4 py-8">
        <SearchResults searchParams={params} />
      </div>
    </div>
  );
}