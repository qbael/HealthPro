'use client'

import React, { useEffect, useState } from 'react'
import SpecialtyCard from '@/components/clinics/controls/SpecialtyCard'
import { AddSpecialtyDialog } from '@/components/clinics/AddSpecialty'
import { Button } from '@/components/ui/button'
import { toast } from 'sonner'

interface ClinicSpecialty {
  id: string
  specialtyName: string
  imageUrl?: string
}

const ClinicSpecialtyPage = () => {
  const [specialtyItems, setSpecialtyItems] = useState<ClinicSpecialty[]>([])
  const [loading, setLoading] = useState(false)

  // 🧩 TRỰC TIẾP LẤY DANH SÁCH CHUYÊN KHOA (Gateway tự inject X-UserRole-Id)
  const fetchClinicSpecialties = async () => {
    try {
      setLoading(true)
      
      // 🔥 KHÔNG cần clinicId! Gateway tự inject X-UserRole-Id từ JWT
      const res = await fetch(`localhost:8081/api/v1/clinics/specialties`, {
        method: 'GET',
        credentials: 'include', // Chỉ cần gửi cookie JWT
      })

      if (!res.ok) {
        throw new Error(`HTTP ${res.status}: ${res.statusText}`)
      }

      const data = await res.json()
      
      if (data.statusCode === 200 && Array.isArray(data.data)) {
        setSpecialtyItems(data.data)
        toast.success('Tải danh sách chuyên khoa thành công')
      } else {
        setSpecialtyItems([])
        toast.info('Chưa có chuyên khoa nào')
      }
    } catch (err: any) {
      console.error('❌ Error:', err)
      toast.error(err.message || 'Không thể tải chuyên khoa')
    } finally {
      setLoading(false)
    }
  }

  // 🧩 THÊM CHUYÊN KHOA (Cũng không cần clinicId)
  const handleAddSpecialty = async (specialty: any) => {
    try {
      const res = await fetch(`localhost:8081/api/v1/clinics/specialties`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({ specialtyId: specialty.id }),
      })

      if (!res.ok) throw new Error(`HTTP ${res.status}`)
      
      toast.success(`Đã thêm: ${specialty.name}`)
      fetchClinicSpecialties() // Refresh
    } catch (err: any) {
      toast.error(err.message || 'Thêm thất bại')
    }
  }

  // 🧩 LOAD NGAY KHI MOUNT - CHỈ 1 CALL!
  useEffect(() => {
    fetchClinicSpecialties()
  }, [])

  return (
    <section>
      <div className="flex items-center justify-between mb-5">
        <h1 className="text-2xl sm:text-4xl font-bold">Chuyên khoa của phòng khám</h1>
        <AddSpecialtyDialog onSelect={handleAddSpecialty}>
          <Button size="lg" className="gap-2">
            <svg xmlns="http://www.w3.org/2000/svg" className="w-5 h-5" fill="none" viewBox="0 0 24 24" stroke="currentColor" strokeWidth={2}>
              <path strokeLinecap="round" strokeLinejoin="round" d="M12 4v16m8-8H4" />
            </svg>
            Thêm mới
          </Button>
        </AddSpecialtyDialog>
      </div>

      {loading ? (
        <p className="text-gray-500 text-center">Đang tải...</p>
      ) : (
        <div className="grid grid-cols-2 gap-3 sm:gap-4">
          {specialtyItems.length === 0 ? (
            <p className="text-gray-500 col-span-2 text-center">Chưa có chuyên khoa nào.</p>
          ) : (
            specialtyItems.map((s) => (
              <SpecialtyCard
                key={s.id}
                title={s.specialtyName}
                img={s.imageUrl || '/default-specialty.jpg'}
                id={s.id}
              />
            ))
          )}
        </div>
      )}
    </section>
  )
}

export default ClinicSpecialtyPage