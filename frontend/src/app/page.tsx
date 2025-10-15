import CarouselPage from "@/components/layout/Carousels";
import ImageSection from "@/components/ImageSection";

export default function Home() {
    return (
        <main className="flex flex-col items-center">
           <ImageSection />

            <section className="w-[85%] relative flex flex-col items-center justify-between gap-5">
                <CarouselPage/>
            </section>
        </main>
    );
}
