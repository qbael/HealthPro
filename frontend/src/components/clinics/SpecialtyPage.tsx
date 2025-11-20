import SpecialtyCard from "@/components/clinics/SpecialtyCard";
import {Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger} from "@/components/ui/dialog";
import {Button} from "@/components/ui/button";
import SpecialtyForm from "@/components/clinics/SpecialtyForm";

interface ClinicSpecialties {
    clinicId: string
    id: string
    specialtyId: string
    name: string
}

interface SpecialtyPageProps {
    clinicSpecialties: ClinicSpecialties[]
}

const SpecialtyPage = ({clinicSpecialties}: SpecialtyPageProps) => {
    return (
        <main className='relative top-10 w-[90%] max-w-400 m-auto'>
            <div className='flex items-center justify-center gap-3 mb-7'>
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
                        {clinicSpecialties &&
                            <SpecialtyForm
                                specialties={clinicSpecialties}
                            />
                        }
                    </DialogContent>
                </Dialog>
            </div>

            {clinicSpecialties.length > 0 ? (
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-6">
                    {clinicSpecialties.map((specialty) => (
                        <SpecialtyCard key={specialty.id} specialty={specialty}/>
                    ))}
                </div>
            ) : (
                <div className='text-center'>Chưa Đăng Ký Chuyên Khoa</div>
            )}
        </main>
    );
};

export default SpecialtyPage;