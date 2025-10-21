import { createServerApi2 } from '@/lib/axiosServer'
import SchedulePage from "@/components/doctor/SchedulePage";

const Page = async () => {
    const api = createServerApi2()
    try {
        const res = await api.get('/v1/doctor-available-slots?appointmentType=DOCTOR')
        const schedules = res.data
        console.log(schedules)
        return (
            <SchedulePage initialSchedules={schedules} />
        )
    }
    catch (error) {
        console.error('Failed to fetch schedules:', error)
    }
};

export default Page;