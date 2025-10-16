"use client";

import {useAuth} from "@/contexts/AuthContext";
import Header from "@/components/Header";
import {clinicNav, doctorNav} from "@/lib/navItem"
import DashBoardSidebar from "@/components/DashBoardSidebar";

const ClientLayout = ({ children }: { children: React.ReactNode }) => {
    const { user } = useAuth();

    return (
        <>
            {
                user?.role === "DOCTOR" ? <DashBoardSidebar navItems={doctorNav} /> :
                user?.role === "CLINIC" ? <DashBoardSidebar navItems={clinicNav} /> :
                <Header />
            }
            <div className={user?.role === 'PATIENT' ? 'mt-16' : ''}>{children}</div>
        </>
    );
};

export default ClientLayout;

