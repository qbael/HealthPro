export type SlotType = "DOCTOR" | "CLINIC"
    | 'GENERAL_STOMACH'           // Đặt khám dạ dày & đại tràng
    | 'ENDOSCOPY_STOMACH'         // Nội soi dạ dày không đau
    | 'ENDOSCOPY_COLON'           // Nội soi đại tràng không đau
    | 'ENDOSCOPY_BOTH'            // Nội soi dạ dày & đại tràng không đau
    | 'CANCER_SCREENING_STOMACH'  // Tầm soát ung thư dạ dày
    | 'CANCER_SCREENING_COLON'    // Tầm soát ung thư đại tràng
    | 'CANCER_SCREENING_BOTH'     // Tầm soát ung thư dạ dày & đại tràng
    | 'VACCINATION'               // Tiêm ngừa viêm gan
    | 'TESTING_PACKAGE'           // Gói xét nghiệm
    | 'AFTER_HOURS'               // Đặt khám ngoài giờ
    | 'BY_DOCTOR';

export interface ClinicSpecialty {
    id: string;
    clinicId: string;
    specialtyId: string;
    specialtyName: string;
    description?: string;
    icon?: string;
    bookingType: SlotType;
}