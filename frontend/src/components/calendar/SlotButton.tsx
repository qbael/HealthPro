import {TimeSlotType} from "@/types/calendar-types";
import React from "react";

interface SlotButtonProps {
    slot: TimeSlotType;
    handleSlotClick: (slot: TimeSlotType) => void;
    loading: boolean;
}

export const SlotButton = ({slot, handleSlotClick, loading}: SlotButtonProps) => {
    return (
        <button
            onClick={() => handleSlotClick(slot)}
            disabled={loading}
            className="border-2 border-cyan-400 text-gray-700 py-3 px-4 rounded-lg
                                 hover:bg-cyan-400 hover:text-white transition-all font-medium text-base disabled:opacity-50 disabled:cursor-not-allowed"
        >
            {slot.startTime} - {slot.endTime}
        </button>
    );
}