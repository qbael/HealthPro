export interface AvailableSlotType {
    id: string;
    doctorId: string;
    clinicSpecialtyId?: string;
    appointmentDate: string;
    startTime: string;
    endTime: string;
    appointmentType: 'DOCTOR' | 'CLINIC';
}

export interface TimeSlotType {
    startTime: string;
    endTime: string;
    available: boolean;
}
