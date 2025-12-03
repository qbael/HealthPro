import api from '@/lib/axios'
import {createContext, useReducer, useEffect, useContext} from 'react'
import type {ReactNode} from 'react'
import {useRouter} from "next/navigation";

type AuthState = {
    user: null | {
        id: string;
        email: string;
        userRoleId: string;
        role: 'PARENT' | 'DOCTOR' | 'ADMIN';
    };
    loading: boolean;
}
type AuthAction =
    | { type: 'LOGIN'; payload: { id: string; email: string; userRoleId: string; role: 'PARENT' | 'DOCTOR' | 'ADMIN'; } }
    | { type: 'LOGOUT' }
    | { type: 'LOADING'; payload: boolean }

export const AuthContext = createContext<any>(null)

const initialState: AuthState = {user: null, loading: false}

export const authReducer = (state: AuthState, action: AuthAction): AuthState => {
    switch (action.type) {
        case 'LOGIN':
            return {
                ...state, user: action.payload, loading: false
            }

        case 'LOGOUT':
            return {
                ...state, user: null, loading: false
            }
        case 'LOADING':
            return {...state, loading: action.payload}

        default:
            return state
    }
}

type AuthProviderProps = { children: ReactNode }

export const AuthContextProvider = ({children}: AuthProviderProps) => {
    const [state, dispatch] = useReducer(authReducer, initialState)
    const router= useRouter()

    useEffect(() => {
        const checkUser = async () => {
            try {
                const res = await api.get(`v1/auth/current`)
                dispatch({type: 'LOGIN', payload: res.data})
                router.refresh()
            } catch (err: any) {
                if (err.response?.status === 401) {
                    dispatch({type: 'LOGOUT'})
                } else {
                    console.error('Server error:', err)
                }
            }
        }

        checkUser()
    }, [])

    return (
        <AuthContext.Provider value={{user: state.user, loading: state.loading, dispatch}}>
            {children}
        </AuthContext.Provider>
    )
}

export const useAuth = () => {
    const context = useContext(AuthContext)
    if (!context)
        throw new Error('useAuth must be used within AuthContextProvider')
    return context
}