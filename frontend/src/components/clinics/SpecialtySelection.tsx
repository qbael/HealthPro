import React from 'react';
import { AddSpecialtyDialog } from '@/components/clinics/AddSpecialty'
import { SPECIALTIES_API_URL } from '@/lib/utils';
import { toast } from 'sonner';

interface Specialty {
    id: string;
    name: string;
    imageUrl?: string;
}

// 💡 Hàm fetch data được chạy trên server
async function fetchAllSpecialties(): Promise<Specialty[]> {
    try {
        // Vẫn gọi trực tiếp 8081 (vì đang ở Server Side nên bỏ qua CORS)
        const res = await fetch(SPECIALTIES_API_URL, {
            method: 'GET',
            cache: 'no-store' // Hoặc 'force-cache' tùy nhu cầu
        });

        if (!res.ok) {
            console.error('Lỗi fetch Specialty:', res.status, await res.text());
            throw new Error(`Failed to fetch specialties: HTTP ${res.status}`);
        }

        const body = await res.json();
        
        if (body.statusCode === 200) {
            return body.data.map((item: any) => ({
                id: item.id,
                name: item.specialtyName, 
                imageUrl: item.imageUrl
            }));
        } else {
            throw new Error(body.message || 'Lỗi không xác định khi tải chuyên khoa');
        }
    } catch (error) {
        console.error('Error fetching specialties:', error);
        // Trả về mảng rỗng nếu lỗi
        return []; 
    }
}


export default async function SpecialtySelection({ onSelect }: { onSelect: (specialty: Specialty) => void }) {
    const specialties = await fetchAllSpecialties();

    if (specialties.length === 0) {
        // Có thể hiện thông báo lỗi ngay tại đây nếu bạn muốn
        // (Tuy nhiên, toast chỉ hoạt động trong client component, nên dùng console.error)
    }

    // 💡 Truyền dữ liệu tĩnh xuống cho Client Component
    return (
        <AddSpecialtyDialog
            specialties={specialties}
            onSelect={onSelect}
        >
            {/* ... children button */}
        </AddSpecialtyDialog>
    );
}