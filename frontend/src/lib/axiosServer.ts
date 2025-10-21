import axios from 'axios';
import {cookies} from 'next/headers';

export function createServerApi() {
    const cookieStore = cookies();
    const cookieHeader = cookieStore.toString();

    return axios.create({
        baseURL: process.env.NEXT_PUBLIC_API_URL,
        withCredentials: true,
        headers: {
            'Content-Type': 'application/json',
            Cookie: cookieHeader,
        },
    });
}
export function createServerApi2() {
    const cookieStore = cookies();
    const cookieHeader = cookieStore.toString();

    return axios.create({
        baseURL: process.env.NEXT_PUBLIC_API_URL2,
        withCredentials: true,
        headers: {
            'Content-Type': 'application/json',
            Cookie: cookieHeader,
        },
    });
}
