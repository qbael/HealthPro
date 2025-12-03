import React from 'react';
import {createServerApi} from "@/lib/axiosServer";
import ClinicAppointment from "@/components/clinics/ClinicAppointment";

const Page = async () => {
    const api = await createServerApi()
    const res = await api.get('v3/appointments/clinic')
    const appointments = res.data

    return (
        <ClinicAppointment appointments={appointments}/>
    );
};

export default Page;