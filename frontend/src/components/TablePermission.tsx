  'use client';

  import { useState, useEffect, useCallback } from 'react';
  import { Lock, Unlock, Filter, Search, Loader2 } from 'lucide-react';
  import { toast } from 'sonner';
  import api from '@/lib/axios';

  interface User {
    id: string;
    email: string;
    phoneNumber: string;
    role: { roleName: string };
    isActive: boolean;
    createdAt: string;
  }

  interface TablePermissionProps {
    initialData: {
      content: User[];
      totalElements: number;
      totalPages: number;
      number: number;
    };
  }

  const ROLES = ['ALL', 'DOCTOR', 'PATIENT', 'CLINIC'] as const;

export default function TablePermission({ initialData }: TablePermissionProps) {
  const [users, setUsers] = useState<User[]>(initialData.content);
  const [totalPages, setTotalPages] = useState(initialData.totalPages);
  const [currentPage, setCurrentPage] = useState(0);
  const [filterRole, setFilterRole] = useState<(typeof ROLES)[number]>('ALL');
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(false);
  const [actionLoading, setActionLoading] = useState<string | null>(null);

  const fetchUsers = useCallback(async (page: number, role?: string, search?: string) => {
    setLoading(true);
    try {
      const response = await api.get('v1/admins/users', { 
        params: {
          page: page.toString(),
          limit: 10,
          ...(role && role !== 'ALL' && { role }),
          ...(search && { search }),
        },
        headers: {
          'X-User-Role': 'ADMIN', 
        },
      });

      const data = response.data;
      setUsers(data.data.content);
      setTotalPages(data.data.totalPages);
    } catch (error: any) {
      console.error('Fetch error:', error);
      toast.error(error.response?.data?.message || 'Lỗi khi tải dữ liệu');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchUsers(currentPage, filterRole === 'ALL' ? undefined : filterRole, searchTerm);
  }, [currentPage, filterRole, searchTerm, fetchUsers]);

  useEffect(() => {
    setUsers(initialData.content);
    setTotalPages(initialData.totalPages);
  }, [initialData]);

  const toggleLock = async (userId: string, isActive: boolean) => {
    setActionLoading(userId);
    try {
      await api.post(`v1/admins/users/${userId}/${isActive ? 'lock' : 'unlock'}`, {}, {  
        headers: {
          'X-User-Role': 'ADMIN', 
        },
      });

      setUsers(prev =>
        prev.map(u => u.id === userId ? { ...u, isActive: !isActive } : u)
      );

      toast.success(`Tài khoản đã được ${isActive ? 'khóa' : 'mở khóa'} thành công!`);
    } catch (error: any) {
      toast.error(error.response?.data?.message || 'Lỗi khi cập nhật trạng thái');
    } finally {
      setActionLoading(null);
    }
  };

    const formatDate = (dateString: string) => {
      const date = new Date(dateString);
      return date.toLocaleDateString('vi-VN', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      });
    };

    const filteredUsers = users.filter(user =>
      (filterRole === 'ALL' || user.role.roleName === filterRole) &&
      user.email.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const maxPageAfterFilter = Math.ceil(filteredUsers.length / 10);
    const effectiveTotalPages = filterRole === 'ALL' && !searchTerm ? totalPages : Math.max(1, maxPageAfterFilter);

    return (
    <div className="w-full max-w-6xl mx-auto bg-white rounded-lg shadow-sm border border-gray-200 p-6">
      <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4 mb-6">
        <h1 className="text-xl font-semibold text-gray-800">Quản lý tài khoản</h1>

        <div className="flex flex-col sm:flex-row gap-3 w-full md:w-auto">
      
          <div className="relative flex-1">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
            <input
              type="text"
              placeholder="Tìm theo email hoặc số điện thoại"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="w-full pl-9 pr-4 py-2 rounded-lg border border-gray-200 bg-white text-gray-800 text-sm focus:outline-none focus:border-cyan-500 focus:ring-1 focus:ring-cyan-500"
            />
          </div>

          <div className="relative flex-1">
            <Filter className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
            <select
              value={filterRole}
              onChange={(e) => setFilterRole(e.target.value as typeof ROLES[number])}
              className="w-full pl-9 pr-10 py-2 rounded-lg border border-gray-200 bg-white text-gray-800 text-sm appearance-none focus:outline-none focus:border-cyan-500 focus:ring-1 focus:ring-cyan-500"
            >
              {ROLES.map((role) => (
                <option key={role} value={role}>
                  {role === 'ALL' ? 'Tất cả vai trò' : role}
                </option>
              ))}
            </select>
            <div className="pointer-events-none absolute right-3 top-1/2 -translate-y-1/2">
              <svg className="w-4 h-4 text-gray-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M19 9l-7 7-7-7" />
              </svg>
            </div>
          </div>
        </div>
      </div>

      {loading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="w-6 h-6 animate-spin text-cyan-500" />
        </div>
      ) : (
        <>
     
          <div className="flex flex-col gap-3">
            {filteredUsers.length > 0 ? (
              filteredUsers.map((user) => (
                <div
                  key={user.id}
                  className="group relative flex items-center gap-4 p-4 rounded-lg border border-gray-200 bg-white hover:bg-gray-50 transition-all duration-300"
                >
              
                  <div className="flex-1 flex items-start gap-4">
                    <div className="w-12 h-12 rounded-full bg-gray-100 flex items-center justify-center text-gray-600 font-medium text-sm">
                      {user.email[0].toUpperCase()}
                    </div>

                    <div>
                      <div className="flex items-center gap-2 mb-1">
                        <h3 className="font-medium text-gray-800">{user.email}</h3>

                        <span
                          className={`px-2 py-1 rounded-full text-xs font-medium ${
                            user.role.roleName === 'DOCTOR'
                              ? 'bg-amber-100 text-amber-600'
                              : user.role.roleName === 'PATIENT'
                              ? 'bg-green-100 text-green-600'
                              : user.role.roleName === 'CLINIC'
                              ? 'bg-purple-100 text-purple-600'
                              : 'bg-gray-100 text-gray-600'
                          }`}
                        >
                          {user.role.roleName}
                        </span>

                      
                        <span
                          className={`px-2 py-1 rounded-full text-xs font-medium ${
                            user.isActive ? 'bg-green-100 text-green-600' : 'bg-red-100 text-red-600'
                          }`}
                        >
                          {user.isActive ? 'Hoạt động' : 'Đã khóa'}
                        </span>
                      </div>

                      <div className="flex flex-wrap gap-3 text-xs text-gray-600">
                        <span>{user.phoneNumber}</span>
                        <span>•</span>
                        <span>Tham gia: {formatDate(user.createdAt)}</span>
                      </div>
                    </div>
                  </div>

                  
                  <div className="pr-4 opacity-0 group-hover:opacity-100 transition-opacity">
                    <button
                      onClick={() => toggleLock(user.id, user.isActive)}
                      disabled={actionLoading === user.id}
                      className={`flex items-center gap-1.5 px-3 py-1.5 rounded-lg text-xs font-medium transition-all ${
                        user.isActive
                          ? 'bg-red-100 text-red-600 hover:bg-red-500 hover:text-white'
                          : 'bg-green-100 text-green-600 hover:bg-green-500 hover:text-white'
                      } disabled:opacity-50`}
                    >
                      {actionLoading === user.id ? (
                        <Loader2 className="w-3 h-3 animate-spin" />
                      ) : user.isActive ? (
                        'Khóa'
                      ) : (
                        'Mở khóa'
                      )}
                    </button>
                  </div>

                
                  <div className="absolute bottom-0 left-0 w-full h-[1px] bg-cyan-500 transform scale-x-0 group-hover:scale-x-100 transition-transform duration-300 origin-left" />
                </div>
              ))
            ) : (
              <div className="flex flex-col items-center justify-center py-12 text-gray-600">
                <p className="text-lg font-medium">Không tìm thấy tài khoản</p>
                <p className="text-sm mt-1">Thử thay đổi bộ lọc hoặc tìm kiếm</p>
              </div>
            )}
          </div>

          {effectiveTotalPages > 1 && (
            <div className="flex justify-center gap-2 mt-8">
              {Array.from({ length: effectiveTotalPages }, (_, i) => (
                <button
                  key={i}
                  onClick={() => setCurrentPage(i)}
                  disabled={loading}
                  className={`w-10 h-10 rounded-lg text-sm font-medium transition-all flex items-center justify-center ${
                    currentPage === i
                      ? 'bg-cyan-500 text-white'
                      : 'bg-gray-100 text-gray-600 hover:bg-gray-200'
                  } disabled:opacity-50`}
                >
                  {i + 1}
                </button>
              ))}
            </div>
          )}
        </>
      )}
    </div>
  );
}