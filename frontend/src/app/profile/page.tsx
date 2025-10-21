import { createServerApi } from '@/lib/axiosServer'
import ProfileForm from "@/components/ProfileForm";

const Page = async () => {
    const api = createServerApi()
    try {
        const res = await api.get('/v1/profile')
        const user = res.data

        return (
            <main className="flex justify-center">
                <div className="my-10 w-[90%] max-w-[640px]">
                    <ProfileForm user={user} />
                </div>
            </main>
        )
    }
    catch (error) {
        console.error('Failed to fetch profile:', error)
    }
}

export default Page;