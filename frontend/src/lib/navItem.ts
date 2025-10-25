import { IoMedicalOutline, IoBusinessOutline, IoMedkitOutline, IoFlaskOutline  } from "react-icons/io5";

type NavItem1 = {
  label: string
  href: string
  icon: React.ComponentType
}
type NavItem= {
  label: string
  href: string
  icon: string
}

export const healthyNav: NavItem1[] = [
    { label: 'Đặt khám bác sĩ', href: '/booking/doctors', icon: IoMedicalOutline },
    { label: 'Đặt khám phòng khám', href: '/booking/clinics', icon: IoMedkitOutline },
]

export const doctorNav: NavItem[] = [
    { label: 'Lời Mời', href: '/notification', icon: 'Bell' },
]

export const clinicNav: NavItem[] = [
    { label: 'Hồ Sơ', href: '/profile', icon: 'House' },
    { label: 'Lịch Làm', href: '/schedule', icon: 'Calendar' },
    { label: 'Chuyên Khoa', href: '/specialty', icon: 'BriefcaseMedical' },
]

export const specialtiesNav: NavItem[] = [
    { label: 'Thêm chuyên khoa', href: '/specialty/add', icon: 'PersonStanding' },
    { label: 'Mời bác sĩ', href: '/specialty/invite', icon: 'Calendar' },
    
]