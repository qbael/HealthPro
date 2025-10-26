import React from 'react';
import { Stethoscope, Clock3, PillBottle, FlaskConical} from 'lucide-react';
import ControlCard from '@/components/clinics/controls/ControlCard'

const controlItems = [
    { 'title': 'Tổng doanh thu', 'icon': <Stethoscope />, 'href': '' },
    { 'title': 'Phòng khám', 'icon': <Clock3 />, 'href': '' },
    { 'title': 'Dịch vụ', 'icon': <PillBottle />, 'href': '' },
    { 'title': 'Doanh thu dịch vụ', 'icon': <FlaskConical />, 'href': '' }
]

const page = () => {
    return (
        <section>
            <h1 className='text-2xl sm:text-4xl font-bold mb-5'>Báo cáo tổng quan</h1>
            <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-5 cursor-pointer">
                {controlItems.map((c) => (
                <ControlCard key={c.title} {...c} />
                ))}
            </div>
        </section>
    )
}

export default page;