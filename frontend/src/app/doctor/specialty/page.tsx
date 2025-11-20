import {createServerApi} from '@/lib/axiosServer'
import SpecialtyPage from "@/components/doctor/SpecialtyPage";

const Page = async () => {
    const api = await createServerApi()
    try {
        const res = await api.get('/v2/doctor-specialty')
        const clinicSpecialties = res.data

        return (
            <SpecialtyPage clinicSpecialties={clinicSpecialties} />
        )
    }
    catch (error) {
        console.error('Failed to fetch specialties:', error)
    }
};

export default Page;