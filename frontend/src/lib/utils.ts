import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export const DOCTORS_API_URL = "http://localhost:8081/api/v1/doctors"
export const CLINICS_API_URL = "http://localhost:8081/api/v1/clinics"
// export const SPECIALTIES_API_URL = "http://localhost:8081/api/v1/specialties"
