import TablePermission from '@/components/TablePermission';
import api from '@/lib/axios';

const Page = async () => {
  try {
    const response = await api.get('v1/admins/users', {
      params: {
        page: 0,
        limit: 10,
      },
      headers: {
        'X-User-Role': 'ADMIN', 
      }
    });

    const result = response.data;  

    return (
      <div className="min-h-screen text-white p-6">
        <TablePermission initialData={result.data} />
      </div>
    );
  } catch (error: any) {
    return (
      <div className="flex items-center justify-center h-screen text-red-400">
        Lỗi {error.response?.status || 500}: Không có quyền truy cập
      </div>
    );
  }
};

export default Page;