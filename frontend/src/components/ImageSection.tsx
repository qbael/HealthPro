import Image from "next/image";
import {Input} from "@/components/ui/input";

const ImageSection = () => {
    return (
        <section className='relative w-full h-[500px]'>
            <Image 
                src='/landing.png'
                fill
                style={{ objectFit: 'cover' }}
                alt='landing'
            />
            <div className='absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex flex-col items-center gap-2 text-center w-[50%]'>
                <h1 className='text-4xl text-white font-bold'>Ứng dụng đặt khám</h1>
                <p className='text-md text-white w-[90%]'>Đặt khám với hơn 1000 bác sĩ đã kết nối chính thức với YouMed để có số thứ tự và khung giờ khám trước</p>
                <Input
                className="bg-white"
                placeholder="Triệu chứng, bác sĩ, bệnh viện..."
                />
            </div>
        </section>
    );
}

export default ImageSection;