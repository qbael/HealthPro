import Image from "next/image";
import {Input} from "@/components/ui/input";
import CarouselPage from "@/components/layout/Carousels";

export default function Home() {
    return (
        <main className="flex flex-col items-center">

            <section className="w-[85%] relative flex flex-col items-center justify-between gap-5">
                <CarouselPage/>
            </section>
        </main>
    );
}
