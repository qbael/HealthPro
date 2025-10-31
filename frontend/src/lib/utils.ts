import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export const DOCTORS_API_URL = "http://localhost:4004/api/v2/doctors"
export const CLINICS_API_URL = "http://localhost:4004/api/v2/clinics"
export const SCHEDULE_API_URL = "http://localhost:4004/api/v3/schedule"