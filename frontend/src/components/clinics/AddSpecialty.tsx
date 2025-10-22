'use client'

import * as React from 'react'
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from '@/components/ui/dialog'
import { toast } from 'sonner'

interface Specialty {
  id: string
  name: string
  imageUrl?: string
}

export function AddSpecialtyDialog({
  children,
  onSelect,
}: {
  children: React.ReactNode
  onSelect: (specialty: Specialty) => void
}) {
  const [open, setOpen] = React.useState(false)
  const [specialties, setSpecialties] = React.useState<Specialty[]>([])
  const [loading, setLoading] = React.useState(false)

  React.useEffect(() => {
    if (open) {
      setLoading(true)
      fetch('/api/specialties')
        .then((res) => res.json())
        .then((data) => setSpecialties(data))
        .catch(() => toast.error('Không thể tải danh sách chuyên khoa'))
        .finally(() => setLoading(false))
    }
  }, [open])

  const handleSelect = (specialty: Specialty) => {
    onSelect(specialty)
    setOpen(false)
  }

  return (
    <Dialog open={open} onOpenChange={setOpen}>
      <DialogTrigger asChild>{children}</DialogTrigger>
      <DialogContent className="max-w-2xl max-h-[80vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>Chọn chuyên khoa để thêm</DialogTitle>
        </DialogHeader>

        {loading ? (
          <p className="text-center text-gray-500">Đang tải...</p>
        ) : (
          <div className="grid grid-cols-2 sm:grid-cols-3 gap-4 mt-4">
            {specialties.map((sp) => (
              <div
                key={sp.id}
                onClick={() => handleSelect(sp)}
                className="cursor-pointer border rounded-xl hover:border-primary hover:bg-primary/5 transition-all flex flex-col items-center justify-center p-4"
              >
                <img
                  src={sp.imageUrl || '/default-specialty.jpg'}
                  alt={sp.name}
                  className="w-20 h-20 object-cover rounded-full mb-3"
                />
                <p className="font-medium text-center">{sp.name}</p>
              </div>
            ))}
          </div>
        )}
      </DialogContent>
    </Dialog>
  )
}
