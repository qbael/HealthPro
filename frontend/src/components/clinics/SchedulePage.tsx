import ScheduleDialog from "@/components/clinics/ScheduleDialog";
import { createServerApi } from "@/lib/axiosServer";
import { Calendar } from "@/components/calendar/Calendar";

const SchedulePage = async ({ initialTemplates, clinicSpecialtyId }: any) => {
    const api = await createServerApi();

    const res1 = await api.get(`v2/clinic-specialty/${clinicSpecialtyId}`);
    const res2 = await api.get(`v3/schedule/clinic-specialty/available-dates/${clinicSpecialtyId}`);
    const specialtyName = res1.data.specialty.name;
    const initialDates: string[] = res2.data.data;

    return (
        <main>
            <section className='relative top-5 mx-auto w-[90%] max-w-[900px]'>
                <div className='flex justify-center gap-3'>
                    <h1 className='text-blue-400 text-2xl text-center font-bold mb-7'>
                        Lịch Làm: {specialtyName}
                    </h1>

                    <ScheduleDialog
                        templates={initialTemplates}
                        clinicSpecialtyId={clinicSpecialtyId}
                    />
                </div>

                <Calendar
                    id={clinicSpecialtyId}
                    type="DOCTOR"
                    slotClickEventType="DELETE_CLINIC_SLOT"
                    availableDates={initialDates}
                />
            </section>
        </main>
    );
};

export default SchedulePage;
