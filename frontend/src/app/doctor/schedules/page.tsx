import { createServerApi } from '@/lib/axiosServer'
import SchedulePage from "@/components/doctor/SchedulePage";

const Page = async () => {
    const api = await createServerApi()
    try {
        const res1 = await api.get('v3/doctor-schedule-template')
        const template = res1.data

        return (
            <SchedulePage initialTemplate={template} />
        )
    }
    catch (error) {
        console.error('Failed to fetch schedules:', error)
    }
};

export default Page;