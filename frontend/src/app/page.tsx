import { Button } from "@/components/ui/button";
import ImageSection from "@/components/ImageSection";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel"
import DoctorCard from "@/components/layout/DoctorCard";
export default function Home() {
  return (
    <main className="flex flex-col items-center">
      <ImageSection />

      <section className="w-[85%] relative top-16 flex flex-col items-center justify-between gap-5">
        <div className="flex flex-col gap-2 items-center">
          <h1 className="text-3xl font-bold">Đặt lịch khám trực tuyến</h1>
          <p className="text-md text-gray-700">Tìm Bác sĩ chính xác - Đặt lịch khám dễ dàng</p>
        </div>

        <div className="w-full flex justify-between">
          <div className="flex flex-col">
            <h2 className="text-xl font-bold">Đặt khám bác sĩ</h2>
            <p className="text-sm text-gray-800">Phiếu khám kèm số thứ tự và thời gian của bạn được xác nhận.</p>
          </div>
          <Button>Xem thêm</Button>
        </div>

        <div className="w-full">
          <Carousel>
            <CarouselContent>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
            </CarouselContent>
            <CarouselPrevious />
            <CarouselNext />
          </Carousel>
        </div>

        <div className="w-full flex justify-between">
          <div className="flex flex-col">
            <h2 className="text-xl font-bold">Đặt khám phòng khám</h2>
            <p className="text-sm text-gray-800">Đa dạng phòng khám với nhiều chuyên khoa khác nhau như Sản - Nhi, Tai Mũi họng, Da Liễu, Tiêu Hoá...</p>
          </div>
          <Button>Xem thêm</Button>
        </div>

        <div className="w-full">
          <Carousel>
            <CarouselContent>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
              <CarouselItem className="basis-1/4"><DoctorCard /></CarouselItem>
            </CarouselContent>
            <CarouselPrevious />
            <CarouselNext />
          </Carousel>
        </div>

      </section>
    </main>
  );
}
