// middleware.ts
import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'

export function middleware(req: NextRequest) {
    const role = req.cookies.get('role')?.value
    console.log(role)
    const path = req.nextUrl.pathname

    // Redirect root
    if (path === '/') {
        if (role === 'DOCTOR')
            return NextResponse.redirect(new URL('/doctor', req.url))
        if (role === 'PATIENT')
            return NextResponse.redirect(new URL('/patient', req.url))
    }

    // Protect doctor routes
    // if (path.startsWith('/doctor') && role !== 'DOCTOR') {
    //     return NextResponse.redirect(new URL('/patient', req.url))
    // }
    //
    // // Protect patient routes
    // if (path.startsWith('/patient') && role !== 'PATIENT') {
    //     return NextResponse.redirect(new URL('/doctor', req.url))
    // }

    return NextResponse.next()
}
