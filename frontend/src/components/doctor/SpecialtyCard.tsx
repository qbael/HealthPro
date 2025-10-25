const SpecialtyCard = ({ specialty } : any) => {
    return (
        <div className="border border-gray-200 shadow-lg rounded-xl p-5 flex flex-col items-center justify-center gap-3 bg-white hover:shadow-xl hover:scale-[1.03] transition-all cursor-pointer">
            <h3 className="text-lg font-semibold text-center">{specialty.specialtyName}</h3>
        </div>
    );
};

export default SpecialtyCard;