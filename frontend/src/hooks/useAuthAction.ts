'use client'

import { useContext } from 'react'
import { AuthContext } from '@/contexts/AuthContext'
import api from '@/lib/axios'
import { toast } from "sonner"
import { useRouter } from "next/navigation"

export const useAuthAction = () => {
    const { user, loading, dispatch } = useContext(AuthContext)
    const router = useRouter()

    const signup = async (email: string, phoneNumber: string,
                          password: string, role: string) => {
        try {
            dispatch({ type: 'LOADING', payload: true })
            const res = await api.post('v1/auth/signup', {
                email: email,
                phoneNumber: phoneNumber,
                password: password,
                role: role
            })

            toast.success('Đăng ký thành công.')
            dispatch({ type: 'LOGIN', payload: res.data })
            router.replace('/profile')

        }
        catch (err: any) {
            console.error(err)
            dispatch({ type: 'LOGOUT' })
            toast.error('Đăng ký thất bại. Vui lòng thử lại.')
        }
    }

    const login = async (email: string, password: string) => {
        try {
            dispatch({ type: 'LOADING', payload: true })

            const res = await api.post('v1/auth/login', {
                email: email,
                password: password,
            })

            toast.success('Đăng nhập thành công.')
            dispatch({ type: 'LOGIN', payload: res.data })
            router.replace('/')

        }
        catch (err: any) {
            console.error(err)
            dispatch({ type: 'LOGOUT' })
            toast.error('Đăng nhập thất bại. Vui lòng thử lại.')
        }
    }

    const logout = async () => {
        try {
            await api.post('v1/auth/logout')
            toast.success('Hẹn gặp lại.')
            dispatch({ type: 'LOGOUT' })
            router.replace('/')
        }
        catch (err: any) {
            console.error(err.response?.data || err.message)
        }
    }

     return { user, loading, signup, login, logout }
}