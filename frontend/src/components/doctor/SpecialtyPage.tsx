import React from 'react';
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog";
import {Button} from "@/components/ui/button";
import SpecialtyForm from "@/components/doctor/SpecialtyForm";
import {renderSpecialtyIcon} from "@/lib/icon-provider";

interface Specialty {
    specialtyName: string;
    id: string
}

interface SpecialtyPageProps {
    specialties: Specialty[]
}

const SpecialtyPage = ({ specialties } : SpecialtyPageProps) => {
    return (
        <main className='relative top-10 w-[90%] max-w-400 m-auto'>
            <div className='flex items-center justify-center gap-3 mb-5'>
                <h1 className="text-3xl md:text-4xl font-bold text-cyan-500 mb-3">Chuyên Khoa</h1>
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
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-6">
                    {specialties.map((specialty) => (
                        <div
                            key={specialty.id}
                            className="group bg-white rounded-2xl shadow-md hover:shadow-xl
                       transition-all duration-300 p-6 md:p-8
                       border border-gray-100 hover:border-cyan-300
                       transform hover:-translate-y-1
                       disabled:opacity-50 disabled:cursor-not-allowed"
                        >
                            <div className="flex items-center gap-4 md:gap-6">
                                <div className={`flex-shrink-0 group-hover:scale-110 transition-transform duration-300`}>
                                    {renderSpecialtyIcon(specialty.specialtyName)}
                                </div>

                                <h3 className="text-left text-lg md:text-xl font-semibold text-gray-800
                             group-hover:text-cyan-600 transition-colors">
                                    {specialty.specialtyName}
                                </h3>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div className='text-center'>Chưa Đăng Ký Chuyên Khoa</div>
            )}
        </main>
    );
};

export default SpecialtyPage;