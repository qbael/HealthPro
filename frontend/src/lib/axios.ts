import axios from "axios"

const api = axios.create({
    baseURL: process.env.NEXT_PUBLIC_API_URL,
    withCredentials: true,
    headers: {
        "Content-Type": "application/json",
    },
})
const api2 = axios.create({
    baseURL: process.env.NEXT_PUBLIC_API_URL2,
    withCredentials: true,
    headers: {
        "Content-Type": "application/json",
    },
})

export default api