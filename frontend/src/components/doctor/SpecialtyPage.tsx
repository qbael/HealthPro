import React from 'react';
import SpecialtyCard from "@/components/clinics/SpecialtyCard";
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog";
import {Button} from "@/components/ui/button";
import SpecialtyForm from "@/components/doctor/SpecialtyForm";

interface Specialty {
    id: string
    name: string
}

interface SpecialtyPageProps {
    specialties: Specialty[]
}

const SpecialtyPage = ({ specialties } : SpecialtyPageProps) => {
    return (
        <main className='relative top-10 w-[90%] max-w-400 m-auto'>
            <div className='flex gap-3 mb-5'>
                <h1 className='text-3xl font-bold'>Chuyên Khoa</h1>
                <Dialog>
                    <DialogTrigger asChild>
                        <Button className='bg-blue-500 hover:bg-blue-600 hover:cursor-pointer'>
                            Đăng Ký Chuyên Khoa
                        </Button>
                    </DialogTrigger>
                    <DialogContent className="max-w-md">
                        <DialogHeader>
                            <DialogTitle>Đăng Ký Chuyên Khoa</DialogTitle>
                        </DialogHeader>
                        <SpecialtyForm
                            specialties={specialties}
                        />
                    </DialogContent>
                </Dialog>
            </div>

            {specialties.length > 0 ? (
                <div className='grid grid-cols-4 gap-4'>
                    {specialties.map(s => (
                        <SpecialtyCard
                            key={s.id}
                            specialty={s}
                        />
                    ))}
                </div>
            ) : (
                <div>Chưa Đăng Ký Chuyên Khoa</div>
            )}
        </main>
    );
};

export default SpecialtyPage;