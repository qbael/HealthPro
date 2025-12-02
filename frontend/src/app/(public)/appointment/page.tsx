import React from 'react';
import {createServerApi} from "@/lib/axiosServer";
import PatientAppointment from "@/components/calendar/PatientAppointment";

const Page = async () => {
    const api = await createServerApi()
    const res = await api.get('v3/appointments/patient')
    const appointments = res.data

    return (
        <PatientAppointment appointments={appointments}/>
    );
};

export default Page;