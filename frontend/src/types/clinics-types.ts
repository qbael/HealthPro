import {SpecialtyType} from "@/types/doctor-types";

export type ClinicsType = {
    id: string,
    userId: string,
    clinicName: string,
    address: string,
    description: string,
    weekdayOpenHour: string,
    weekdayCloseHour: string,
    weekendOpenHour: string,
    weekendCloseHour: string,
    logoUrl: string,
    avatarUrl: string
    clinicSpecialties: {
        id: string,
        specialty: SpecialtyType
    }[],
}