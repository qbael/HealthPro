export interface DoctorAvailableSlot {
    id: string;
    doctorId: string;
    clinicSpecialtyId?: string;
    appointmentDate: string;
    startTime: string;
    endTime: string;
    appointmentType: 'DOCTOR' | 'CLINIC';
}

export interface TimeSlot {
    startTime: string;
    endTime: string;
    available: boolean;
}
