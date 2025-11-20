"use client"

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog";
import ScheduleForm from "@/components/clinics/ScheduleForm";
import {useRouter} from "next/navigation";

interface ScheduleDialogProps {
    templates: any;
    clinicSpecialtyId: string;
}

export default function ScheduleDialog({ templates, clinicSpecialtyId }: ScheduleDialogProps) {
    const [open, setOpen] = useState(false);
    const router = useRouter();

    return (
        <Dialog open={open} onOpenChange={() => setOpen(!open)}>
            <DialogTrigger asChild>
                <Button
                    className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'
                    onClick={() => setOpen(true)}
                >
                    Đăng Ký Lịch Làm
                </Button>
            </DialogTrigger>
            <DialogContent className="max-w-md max-h-[80vh] overflow-y-auto">
                <DialogHeader>
                    <DialogTitle>Đăng Ký Lịch Làm</DialogTitle>
                </DialogHeader>
                <ScheduleForm
                    templates={templates}
                    clinicSpecialtyId={clinicSpecialtyId}
                    onClose={() => {
                        setTimeout(() => {
                            window.location.reload();
                        }, 1000);
                        setOpen(false)
                    }}
                />
            </DialogContent>
        </Dialog>
    );
}
