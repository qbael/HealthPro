'use client'

import { useContext } from 'react'
import { AuthContext } from '@/contexts/AuthContext'
import api from '@/lib/axios'
import { toast } from "sonner"

export const useAuthAction = () => {
    const { user, loading, dispatch } = useContext(AuthContext)

    const signup = async (email: string, password: string, role: string) => {
        try {
            dispatch({ type: 'LOADING' })
            const res = await api.post('v1/auth/signup', {
                email: email,
                password: password,
                role: role
            })

            toast.success('Đăng ký thành công.')
            dispatch({ type: 'LOGIN', payload: res.data.user })

        }
        catch (err: any) {
            console.error(err)
            dispatch({ type: 'LOGOUT' })
        }
    }

    const login = async (email: string, password: string) => {
        try {
            dispatch({ type: 'LOADING' })

            const res = await api.post('v1/auth/login', {
                email: email,
                password: password,
            })

            toast.success('Đăng nhập thành công.')
            dispatch({ type: 'LOGIN', payload: res.data.user })

        }
        catch (err: any) {
            console.error(err)
            dispatch({ type: 'LOGOUT' })
        }
    }

    const logout = async () => {
        try {
            await api.post('/auth/logout')
            toast.success('Hẹn gặp lại.')
            dispatch({ type: 'LOGOUT' })
        }
        catch (err: any) {
            console.error(err.response?.data || err.message)
        }
    }

     return { user, loading, signup, login, logout }
}