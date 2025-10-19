import {
    Activity,
    Baby,
    Bone, Brain,
    Ear, Eye,
    Flower2,
    Heart,
    Hospital,
    LucideIcon, Microscope,
    PillBottle, Smile,
    Stethoscope, Syringe,
    User
} from "lucide-react";
import type { JSX } from "react";
import { createElement } from "react";

export interface SpecialtyIconMapping {
    keywords: string[];
    icon: LucideIcon;
    color: string;
}

const specialtyIconMappings: SpecialtyIconMapping[] = [
    {
        keywords: ['nội tổng quát', 'nội khoa', 'nội'],
        icon: Stethoscope,
        color: 'text-blue-500',
    },
    {
        keywords: ['sản', 'phụ khoa', 'sản phụ'],
        icon: Flower2,
        color: 'text-pink-500',
    },
    {
        keywords: ['nhi', 'nhi khoa', 'trẻ em'],
        icon: Baby,
        color: 'text-purple-500',
    },
    {
        keywords: ['da liễu', 'da', 'liễu'],
        icon: User,
        color: 'text-orange-500',
    },
    {
        keywords: ['ngoại', 'ngoại tổng hợp', 'phẫu thuật'],
        icon: Activity,
        color: 'text-red-500',
    },
    {
        keywords: ['tim mạch', 'tim', 'mạch máu'],
        icon: Heart,
        color: 'text-red-600',
    },
    {
        keywords: ['tiêu hóa', 'dạ dày', 'gan mật'],
        icon: Hospital,
        color: 'text-teal-500',
    },
    {
        keywords: ['hô hấp', 'phổi', 'hô'],
        icon: Activity,
        color: 'text-cyan-500',
    },
    {
        keywords: ['thận', 'tiết niệu', 'thận tiết niệu'],
        icon: PillBottle,
        color: 'text-indigo-500',
    },
    {
        keywords: ['chỉnh hình', 'xương khớp', 'cơ xương'],
        icon: Bone,
        color: 'text-gray-600',
    },
    {
        keywords: ['tai mũi họng', 'tai', 'mũi', 'họng'],
        icon: Ear,
        color: 'text-amber-500',
    },
    {
        keywords: ['mắt', 'nhãn khoa'],
        icon: Eye,
        color: 'text-emerald-500',
    },
    {
        keywords: ['răng hàm mặt', 'răng', 'nha khoa'],
        icon: Smile,
        color: 'text-blue-400',
    },
    {
        keywords: ['thần kinh', 'não', 'thần'],
        icon: Brain,
        color: 'text-violet-500',
    },
    {
        keywords: ['xét nghiệm', 'cận lâm sàng'],
        icon: Microscope,
        color: 'text-green-600',
    },
    {
        keywords: ['tiêm chủng', 'vắc xin', 'tiêm'],
        icon: Syringe,
        color: 'text-yellow-600',
    },
];

export const getSpecialtyIcon = (
    specialtyName: string
): { Icon: LucideIcon; color: string } => {
    const normalizedName = specialtyName.toLowerCase().trim();

    const mapping = specialtyIconMappings.find((item) =>
        item.keywords.some((keyword) => normalizedName.includes(keyword))
    );

    return {
        Icon: mapping?.icon || Stethoscope,
        color: mapping?.color || 'text-gray-500',
    };
};

export const renderSpecialtyIcon = (
    specialtyName: string,
    className: string = 'w-8 h-8'
): JSX.Element => {
    const { Icon, color } = getSpecialtyIcon(specialtyName);
    return createElement(Icon, { className: `${className} ${color}` });
};