import SearchBarWithFilters from "@/components/search/SearchBarWithFilters";
import SearchResults from "@/components/search/SearchResults";

export default async function SearchPage({
  searchParams,
}: {
  searchParams: Promise<Record<string, string | undefined>>;
}) {
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