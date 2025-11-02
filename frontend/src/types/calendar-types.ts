export interface TimeSlotType {
    id: string;
    appointmentDate: string;
    startTime: string;
    endTime: string;
}

export interface AppointmentData {
    patientId: string;
    patientName: string;
    patientEmail: string | null;
    patientPhone: string | null;
    doctorId?: string | null;
    doctorName?: string | null;
    clinicId?: string | null;
    clinicName?: string | null;
    address: string;
    phone: string;
    clinicSpecialtyId?: string | null;
    specialtyName?: string | null;
}