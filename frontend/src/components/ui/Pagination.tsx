"use client";
import {useRouter, useSearchParams} from "next/navigation";

interface PaginationProps {
    currentPage: number;
    totalPages: number;
}

export const Pagination = ({ currentPage, totalPages }: PaginationProps) => {

    const router = useRouter();
    const searchParams = useSearchParams();

    const updateQuery = (key: string, value: number) => {
        const params = new URLSearchParams(searchParams);
        params.set(key, (value).toString());
        router.push(`?${params.toString()}`);
    };

    const getPageNumbers = () => {
        const pages = [];
        const showEllipsisStart = currentPage > 3;
        const showEllipsisEnd = currentPage < totalPages - 2;

        if (totalPages <= 4) {
            for (let i = 1; i <= totalPages; i++) {
                pages.push(i);
            }
        } else {
            pages.push(1);

            if (showEllipsisStart) {
                pages.push('...');
            }

            const start = Math.max(2, currentPage - 1);
            const end = Math.min(totalPages - 1, currentPage + 1);

            for (let i = start; i <= end; i++) {
                if (!pages.includes(i)) {
                    pages.push(i);
                }
            }

            if (showEllipsisEnd) {
                pages.push('...');
            }

            if (!pages.includes(totalPages)) {
                pages.push(totalPages);
            }
        }

        return pages;
    };

    const handlePageClick = (page: number) => {
        if (page.toString() !== '...' && page - 1 !== currentPage) {
            updateQuery('page', page - 1);
        }
    };

    const handlePrevious = () => {
        if (currentPage > 0) {
            updateQuery('page', currentPage - 1);
        }
    };

    const handleNext = () => {
        if (currentPage + 1 < totalPages) {
            updateQuery('page', currentPage + 1);
        }
    };

    const pageNumbers = getPageNumbers();

    return (
        <div className="flex items-center gap-2 p-4">

            <button
                onClick={handlePrevious}
                disabled={currentPage === 0}
                className={`w-12 h-12 flex items-center justify-center rounded-lg border transition-all
          ${currentPage === 0
                    ? 'bg-gray-100 text-gray-400 cursor-not-allowed border-gray-200'
                    : 'bg-white text-gray-600 hover:bg-gray-50 border-gray-300 cursor-pointer'
                }`}
            >
                <svg width="20" height="20" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"/>
                </svg>
            </button>

            {pageNumbers.map((page, index) => (
                <button
                    key={index}
                    onClick={() => handlePageClick(page as number)}
                    disabled={page === '...'}
                    className={`w-12 h-12 flex items-center justify-center rounded-lg font-medium transition-all
            ${page === currentPage + 1
                        ? 'bg-cyan-500 text-white shadow-md'
                        : page === '...'
                            ? 'bg-white text-gray-400 cursor-default border border-gray-200'
                            : 'bg-white text-cyan-500 hover:bg-cyan-50 border border-gray-200 cursor-pointer'
                    }`}
                >
                    {page}
                </button>
            ))}

            <button
                onClick={handleNext}
                disabled={currentPage + 1 === totalPages}
                className={`w-12 h-12 flex items-center justify-center rounded-lg border transition-all
          ${currentPage + 1 === totalPages
                    ? 'bg-gray-100 text-gray-400 cursor-not-allowed border-gray-200'
                    : 'bg-white text-gray-600 hover:bg-gray-50 border-gray-300 cursor-pointer'
                }`}
            >
                <svg width="20" height="20" viewBox="0 0 20 20" fill="currentColor">
                    <path d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"/>
                </svg>
            </button>
        </div>
    );
};