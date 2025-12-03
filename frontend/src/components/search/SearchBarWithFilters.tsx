'use client';

import { useRouter, useSearchParams } from "next/navigation";
import { useState } from "react";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";

export default function SearchBarWithFilters() {
  const router = useRouter();
  const searchParams = useSearchParams();

  const [query, setQuery] = useState(searchParams.get("q") || "");
  const [type, setType] = useState(searchParams.get("type") || "all");
  const [specialty, setSpecialty] = useState(searchParams.get("specialty") || "");

  const updateUrl = () => {
    const params = new URLSearchParams();
    if (query.trim()) params.set("q", query.trim());
    if (type !== "all") params.set("type", type);
    if (specialty && specialty !== "all") params.set("specialty", specialty);
      else params.delete("specialty");

    router.push(`/search?${params.toString()}`);
  };

  return (
    <div className="bg-white border-b shadow-sm">
      <div className="max-w-7xl mx-auto px-4 py-6">
        <div className="flex flex-col md:flex-row gap-4 items-center">
          <Input
            className="md:w-96 h-12 text-lg"
            placeholder="Tìm bác sĩ, bệnh viện, triệu chứng..."
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            onKeyDown={(e) => e.key === "Enter" && updateUrl()}
          />

          <Select value={type} onValueChange={setType}>
            <SelectTrigger className="w-48">
              <SelectValue placeholder="Tất cả" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">Tất cả</SelectItem>
              <SelectItem value="doctor">Chỉ bác sĩ</SelectItem>
              <SelectItem value="clinic">Chỉ phòng khám / bệnh viện</SelectItem>
            </SelectContent>
          </Select>

          <Select value={specialty} onValueChange={setSpecialty}>
            <SelectTrigger className="w-48">
              <SelectValue placeholder="Chọn chuyên khoa" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="all">Tất cả chuyên khoa</SelectItem>  {/* ← SỬA THÀNH "all" */}
              <SelectItem value="Thần kinh">Thần kinh</SelectItem>
              <SelectItem value="Da liễu">Da liễu</SelectItem>
              <SelectItem value="Sản - Phụ khoa">Sản - Phụ khoa</SelectItem>
              <SelectItem value="Nhi khoa">Nhi khoa</SelectItem>
              <SelectItem value="Tim mạch">Tim mạch</SelectItem>
              <SelectItem value="Tiêu hóa">Tiêu hóa</SelectItem>
            </SelectContent>
          </Select>

          <Button size="lg" onClick={updateUrl}>
            Tìm kiếm
          </Button>
        </div>
      </div>
    </div>
  );
}