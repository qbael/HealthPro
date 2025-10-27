import {SCHEDULE_API_URL} from "@/lib/utils";
import {Calendar} from "@/components/calendar/Calendar";

export default async function DoctorAppointmentCalendar({params}: {params: Promise<{id: string }>}) {
    const {id} = await params;

    const res = await fetch(`${SCHEDULE_API_URL}/clinic-specialty/available-dates/${id}`, {
        method: "GET",
        cache: 'no-store',
    });

    const data = await res.json();
    const availableDates: string[] = data.data

    return (
        <>
            <Calendar id={id} type={"CLINIC"} availableDates={availableDates} />
        </>
    );
}
