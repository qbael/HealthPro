export type SpecialtyType = {
    id: string;
    name: string;
};

export type DoctorType = {
    id: string,
    userId: string,
    fullName: string,
    bio: string,
    gender: boolean,
    address: string,
    avatarUrl: string,
    doctorSpecialties: {
        id: string,
        specialty: SpecialtyType
    }[],
}