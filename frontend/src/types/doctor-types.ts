export type Specialty = {
    id: string;
    name: string;
};

export type Doctor = {
    id: string;
    userId: string,
    fullName: string,
    bio: string,
    gender: boolean,
    address: string,
    avatarUrl: string,
    doctorSpecialties: {
        id: string,
        specialty: Specialty
    }[]
}