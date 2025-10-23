
interface SpecialtyCardProps {
    id: string
    title: string
    img: string
}

const SpecialtyCard = ({ id, title, img }: SpecialtyCardProps) => {
    return (
        <div className="relative group border border-gray-200 shadow-lg rounded-xl p-5 flex flex-col items-center max-w-[500px] justify-center gap-3 bg-white hover:shadow-xl hover:scale-[1.03] transition-all cursor-pointer">
            <img src={img} alt={title} className="w-24 h-24 object-contain mb-2" />
            <h3 className="text-lg font-semibold text-center">{title}</h3>
        </div>
    )
}

export default SpecialtyCard;