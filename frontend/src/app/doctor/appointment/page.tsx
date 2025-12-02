import React from 'react';
import DoctorAppointment from "@/components/doctor/DoctorAppointment";
import {createServerApi} from "@/lib/axiosServer";

const Page = async () => {
    const api = await createServerApi()
    const res = await api.get('v3/appointments/doctor')
    const appointments = res.data

    return (
        <DoctorAppointment appointments={appointments}/>
    );
};

export default Page;