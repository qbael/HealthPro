import { createServerApi } from '@/lib/axiosServer'
import SchedulePage from "@/components/clinics/SchedulePage"

const Page = async ({ params } : any) => {
    const { id } = await params
    const api = await createServerApi()
    try {
        const res = await api.get(`v3/clinic-specialty-schedule-template/${id}`)
        const templates = res.data

        return (
            <SchedulePage initialTemplates={templates} clinicSpecialtyId={id}/>
        )
    }
    catch (error) {
        console.error('Failed to fetch schedules:', error)
    }
};

export default Page;