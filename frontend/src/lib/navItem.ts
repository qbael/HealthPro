import { IoMedicalOutline, IoBusinessOutline, IoMedkitOutline, IoFlaskOutline  } from "react-icons/io5";


type NavItem = {
  label: string
  href: string
  icon: string
}

export const healthyNav: NavItem[] = [
    { label: 'Đặt khám bác sĩ', href: '/booking/doctors', icon: IoMedicalOutline },
    { label: 'Đặt khám phòng khám', href: '/booking/clinics', icon: IoMedkitOutline },
]