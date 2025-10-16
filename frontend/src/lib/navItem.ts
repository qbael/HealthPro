import { IoMedicalOutline, IoBusinessOutline, IoMedkitOutline, IoFlaskOutline  } from "react-icons/io5";

type NavItem = {
  label: string
  href: string
  icon: string | React.ComponentType
}

export const healthyNav: NavItem[] = [
    { label: 'Đặt khám bác sĩ', href: '/booking/doctors', icon: IoMedicalOutline },
    { label: 'Đặt khám phòng khám', href: '/booking/clinics', icon: IoMedkitOutline },
]

export const doctorNav: NavItem[] = [
    { label: 'Hồ Sơ', href: '/profile', icon: 'User' },
    { label: 'Lời Mời', href: '/notification', icon: 'Bell' },
]

export const clinicNav: NavItem[] = [
    { label: 'Hồ Sơ', href: '/profile', icon: 'House' },
    { label: 'Lịch Làm', href: '/schedule', icon: 'Calendar' },
    { label: 'Chuyên Khoa', href: '/specialty', icon: 'BriefcaseMedical' },
]