export type ResponseType<T> = {
    status: number;
    success: boolean;
    message: string;
    data: T,
    timestamp: Date;
}

export type ResponseWithPaginationType<T> = {
    status: number;
    success: boolean;
    message: string;
    data: {
        content: T[],
        pageable: {
            pageNumber: number,
            pageSize: number,
            sort: {
                sorted: boolean,
                empty: boolean,
                unsorted: boolean
            },
            offset: number,
            paged: boolean,
            unpaged: boolean
        },
        last: boolean,
        totalElements: number,
        totalPages: number,
        first: boolean,
        size: number,
        number: number,
        sort: {
            sorted: boolean,
            empty: boolean,
            unsorted: boolean
        },
        numberOfElements: 5,
        empty: boolean
    },
    timestamp: Date;
}