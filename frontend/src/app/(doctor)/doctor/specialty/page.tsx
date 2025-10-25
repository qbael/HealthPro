import {createServerApi} from '@/lib/axiosServer'
import SpecialtyPage from "@/components/doctor/SpecialtyPage";

const Page = async () => {
    const api = await createServerApi()
    try {
        const res = await api.get('/v1/doctor-specialty')
        const specialties = res.data

        return (
            <SpecialtyPage specialties={specialties} />
        )
    }
    catch (error) {
        console.error('Failed to fetch specialties:', error)
    }
};

export default Page;