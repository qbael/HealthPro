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

  // 🧩 1 CALL DUY NHẤT - GATEWAY TỰ XỬ LÝ!
  const fetchClinicSpecialties = async () => {
    try {
      setLoading(true)
      
      // 🔥 GATEWAY: localhost:4004 → CLINIC: localhost:8081
      //    TỰ inject X-UserRole-Id từ JWT cookie
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}v1/clinics/current/specialties`, {
        method: 'GET',
        credentials: 'include',  // Chỉ cần gửi JWT cookie
      })

      console.log('📡 URL:', `${process.env.NEXT_PUBLIC_API_URL}v1/clinics/current/specialties`)
      console.log('📡 Status:', res.status)

      if (!res.ok) throw new Error(`HTTP ${res.status}`)

      const data = await res.json()
      console.log('📡 Data:', data)
      
      if (data.statusCode === 200) {
        setSpecialtyItems(data.data || [])
        toast.success('Tải thành công')
      }
    } catch (err: any) {
      console.error('❌ Error:', err)
      toast.error(err.message)
    } finally {
      setLoading(false)
    }
  }

  // 🧩 THÊM - CŨNG 1 CALL
  const handleAddSpecialty = async (specialty: any) => {
    try {
      const res = await fetch(`${process.env.NEXT_PUBLIC_API_GATEWAY_URL}clinics/specialties`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({ specialtyId: specialty.id }),
      })

      if (!res.ok) throw new Error(`HTTP ${res.status}`)
      
      toast.success(`Đã thêm: ${specialty.name}`)
      fetchClinicSpecialties() // Refresh
    } catch (err: any) {
      toast.error(err.message)
    }
  }

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