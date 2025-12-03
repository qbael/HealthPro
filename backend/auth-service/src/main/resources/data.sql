-- -- Insert Roles
-- INSERT INTO roles (id, role_name)
-- VALUES ('550e8400-e29b-41d4-a716-446655440001', 'ADMIN'),
--        ('550e8400-e29b-41d4-a716-446655440002', 'DOCTOR'),
--        ('550e8400-e29b-41d4-a716-446655440003', 'PATIENT'),
--        ('550e8400-e29b-41d4-a716-446655440004', 'CLINIC');
--
-- -- Insert Users
-- -- Admin Users
-- INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
-- VALUES ('650e8400-e29b-41d4-a716-446655440001', 'admin@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0901234567',
--         '550e8400-e29b-41d4-a716-446655440001', true, '2024-01-15 08:00:00', '2024-01-15 08:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440002', 'admin.nguyen@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0901234568',
--         '550e8400-e29b-41d4-a716-446655440001', true, '2024-01-20 09:00:00', '2024-01-20 09:00:00');
--
-- -- Doctor Users
-- INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
-- VALUES ('650e8400-e29b-41d4-a716-446655440011', 'bs.trannguyen@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345678',
--         '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-01 10:00:00', '2024-02-01 10:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440012', 'bs.lethimai@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345679',
--         '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-05 11:00:00', '2024-02-05 11:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440013', 'bs.phamvanminh@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345680',
--         '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-10 08:30:00', '2024-02-10 08:30:00'),
--        ('650e8400-e29b-41d4-a716-446655440014', 'bs.nguyenthihanh@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345681',
--         '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-15 09:15:00', '2024-02-15 09:15:00'),
--        ('650e8400-e29b-41d4-a716-446655440015', 'bs.hoangvantuan@stayhealthy.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345682',
--         '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-20 10:30:00', '2024-02-20 10:30:00'),
--        ('650e8400-e29b-41d4-a716-446655440016', 'mindang2@gmail.com',
--         '$2a$10$xgeCIaFJApydu/l6.flcrOYnLl1n2fSOMi5DVzsCf8MsOzeJadH9W', '0901234568',
--         '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-20 10:30:00', '2024-02-20 10:30:00');
--
-- -- Patient Users
-- INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
-- VALUES ('650e8400-e29b-41d4-a716-446655440021', 'nguyen.vanan@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456789',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-01 14:00:00', '2024-03-01 14:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440022', 'tran.thihong@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456790',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-05 15:00:00', '2024-03-05 15:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440023', 'le.vanbinh@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456791',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-10 16:00:00', '2024-03-10 16:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440024', 'pham.thilan@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456792',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-15 10:30:00', '2024-03-15 10:30:00'),
--        ('650e8400-e29b-41d4-a716-446655440025', 'hoang.vannam@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456793',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-20 11:00:00', '2024-03-20 11:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440026', 'do.thiphuong@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456794',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-25 13:00:00', '2024-03-25 13:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440027', 'vu.vanhung@gmail.com',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456795',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-28 14:30:00', '2024-03-28 14:30:00'),
--        ('650e8400-e29b-41d4-a716-446655440028', 'mindang1@gmail.com',
--         '$2a$10$H2rqXoAONygpzLmOaqiVAeioiR.fbj29FMBVREw2P9NIwP5NE23XS', '0901234567',
--         '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-28 14:30:00', '2024-03-28 14:30:00');
--
-- -- Clinic Users
-- INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
-- VALUES ('650e8400-e29b-41d4-a716-446655440031', 'contact@benhvienchobray.vn',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567890',
--         '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-10 08:00:00', '2024-01-10 08:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440032', 'info@phongkhamhoanghoa.vn',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567891',
--         '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-12 09:00:00', '2024-01-12 09:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440033', 'contact@medicalcenter.vn',
--         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567892',
--         '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
--        ('650e8400-e29b-41d4-a716-446655440034', 'mindang3@gmail.com',
--         '$2a$10$MpneJ8u0kFTx2RU.NQ0pc.S3HWprajaFgDRlQCiCNt1D.OWZKfAau', '0901234569',
--         '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-15 10:00:00', '2024-01-15 10:00:00');
--
-- -- Insert Admins
-- INSERT INTO admins (id, user_id, full_name, gender, address)
-- VALUES ('750e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440001', 'Trần Văn Quản', true,
--         '123 Nguyễn Huệ, Quận 1, TP.HCM'),
--        ('750e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440002', 'Nguyễn Thị Lan', false,
--         '456 Lê Lợi, Quận 3, TP.HCM');
--
-- -- Insert Doctors
-- INSERT INTO doctors (id, user_id, full_name, bio, gender, address, avatar_url)
-- VALUES ('750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
--         'Bác sĩ chuyên khoa Nội tổng quát với hơn 15 năm kinh nghiệm. Chuyên điều trị các bệnh về tim mạch, tiểu đường và cao huyết áp.',
--         true, '78 Pasteur, Quận 1, TP.HCM', 'https://example.com/avatars/doctor1.jpg'),
--        ('750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440012', 'BS. Lê Thị Mai',
--         'Bác sĩ chuyên khoa Sản - Phụ khoa, tốt nghiệp Đại học Y Hà Nội. Có kinh nghiệm 10 năm trong lĩnh vực chăm sóc sức khỏe sinh sản và điều trị vô sinh.',
--         false, '234 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', 'https://example.com/avatars/doctor2.jpg'),
--        ('750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440013', 'BS. Phạm Văn Minh',
--         'Bác sĩ chuyên khoa Nhi với 12 năm kinh nghiệm. Chuyên điều trị các bệnh về hô hấp, tiêu hóa và phát triển ở trẻ em.',
--         true, '567 Cách Mạng Tháng 8, Quận 10, TP.HCM', 'https://example.com/avatars/doctor3.jpg'),
--        ('750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440014', 'BS. Nguyễn Thị Hạnh',
--         'Bác sĩ chuyên khoa Da liễu, tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM. Chuyên điều trị mụn trứng cá, viêm da và các bệnh nấm da.',
--         false, '89 Võ Văn Tần, Quận 3, TP.HCM', 'https://example.com/avatars/doctor4.jpg'),
--        ('750e8400-e29b-41d4-a716-446655440015', '650e8400-e29b-41d4-a716-446655440015', 'BS. Hoàng Văn Tuấn',
--         'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
--         true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://example.com/avatars/doctor5.jpg'),
--        ('750e8400-e29b-41d4-a716-446655440016', '650e8400-e29b-41d4-a716-446655440016', 'BS. Trần Minh Đăng 2',
--         'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
--         true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://example.com/avatars/doctor5.jpg');
--
-- -- Insert Patients
-- INSERT INTO patients (id, user_id, full_name, date_of_birth, gender, medical_notes)
-- VALUES ('750e8400-e29b-41d4-a716-446655440021', '650e8400-e29b-41d4-a716-446655440021', 'Nguyễn Văn An', '1985-03-15',
--         true, 'Tiền sử tăng huyết áp. Đang dùng thuốc hạ áp đều đặn. Cần theo dõi chỉ số huyết áp định kỳ.'),
--        ('750e8400-e29b-41d4-a716-446655440022', '650e8400-e29b-41d4-a716-446655440022', 'Trần Thị Hồng', '1990-07-22',
--         false, 'Dị ứng với Penicillin. Tiền sử sinh mổ. Đang trong giai đoạn theo dõi sau sinh.'),
--        ('750e8400-e29b-41d4-a716-446655440023', '650e8400-e29b-41d4-a716-446655440023', 'Lê Văn Bình', '1978-11-30',
--         true, 'Tiểu đường type 2. Đang điều trị bằng Metformin 500mg x 2 lần/ngày. Cần kiểm soát chế độ ăn.'),
--        ('750e8400-e29b-41d4-a716-446655440024', '650e8400-e29b-41d4-a716-446655440024', 'Phạm Thị Lan', '1995-05-18',
--         false, 'Không có tiền sử bệnh lý đặc biệt. Khám sức khỏe định kỳ.'),
--        ('750e8400-e29b-41d4-a716-446655440025', '650e8400-e29b-41d4-a716-446655440025', 'Hoàng Văn Nam', '1982-09-10',
--         true,
--         'Viêm gan B mạn tính. Đang theo dõi và điều trị tại Bệnh viện Bệnh Nhiệt đới. Cần xét nghiệm chức năng gan 3 tháng/lần.'),
--        ('750e8400-e29b-41d4-a716-446655440026', '650e8400-e29b-41d4-a716-446655440026', 'Đỗ Thị Phượng', '1988-12-05',
--         false, 'Hen phế quản. Luôn mang theo bình xịt Ventolin. Tránh tiếp xúc với khói bụi và phấn hoa.'),
--        ('750e8400-e29b-41d4-a716-446655440027', '650e8400-e29b-41d4-a716-446655440027', 'Vũ Văn Hùng', '2000-04-25',
--         true, 'Sinh viên năm 3 Đại học Y. Không có tiền sử bệnh lý. Khám sức khỏe để xin visa du học.'),
--        ('750e8400-e29b-41d4-a716-446655440028', '650e8400-e29b-41d4-a716-446655440028', 'Trần Minh Đăng 1', '2005-05-19',
--         true, 'Sinh viên năm 3 Đại học Y. Không có tiền sử bệnh lý. Khám sức khỏe để xin visa du học.');
--
-- -- Insert Clinics
-- INSERT INTO clinics (id, user_id, clinic_name, address, description, weekday_open_hour, weekday_close_hour, weekend_open_hour,  weekend_close_hour, logo_url, avatar_url)
-- VALUES ('750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy',
--         '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
--         'Bệnh viện đa khoa hạng đặc biệt, là một trong những bệnh viện lớn nhất khu vực phía Nam. Cung cấp đầy đủ các dịch vụ y tế từ khám chữa bệnh đến phẫu thuật chuyên sâu.',
--         '07:00', '21:00', '07:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop'),
--        ('750e8400-e29b-41d4-a716-446655440032', '650e8400-e29b-41d4-a716-446655440032', 'Phòng khám Đa khoa Hoàng Hoa',
--         '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM',
--         'Phòng khám đa khoa uy tín với đội ngũ bác sĩ giàu kinh nghiệm. Chuyên khám và điều trị nội khoa, ngoại khoa, sản phụ khoa và nhi khoa.',
--         '08:00', '20:00', '08:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760282444/jwzn0b5e3amdapletedu.jpg', 'https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop'),
--        ('750e8400-e29b-41d4-a716-446655440033', '650e8400-e29b-41d4-a716-446655440033', 'Trung tâm Y tế Medical Center',
--         '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
--         'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
--         '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
--        ('750e8400-e29b-41d4-a716-446655440034', '650e8400-e29b-41d4-a716-446655440034', 'Trung tâm Y tế Trần Minh Đăng 3',
--         '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
--         'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
--         '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop');

-- Insert Roles
INSERT INTO roles (id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'ADMIN'),
       ('550e8400-e29b-41d4-a716-446655440002', 'DOCTOR'),
       ('550e8400-e29b-41d4-a716-446655440003', 'PATIENT'),
       ('550e8400-e29b-41d4-a716-446655440004', 'CLINIC');

-- Insert Users
-- Admin Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440001', 'admin@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0901234567', '550e8400-e29b-41d4-a716-446655440001', true, '2024-01-15 08:00:00', '2024-01-15 08:00:00'),
       ('650e8400-e29b-41d4-a716-446655440002', 'admin.nguyen@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0901234568','550e8400-e29b-41d4-a716-446655440001', true, '2024-01-20 09:00:00', '2024-01-20 09:00:00');

-- Doctor Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440011', 'bs.trannguyen@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345678','550e8400-e29b-41d4-a716-446655440002', true, '2024-02-01 10:00:00', '2024-02-01 10:00:00'),
       ('650e8400-e29b-41d4-a716-446655440012', 'bs.lethimai@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345679','550e8400-e29b-41d4-a716-446655440002', true, '2024-02-05 11:00:00', '2024-02-05 11:00:00'),
       ('650e8400-e29b-41d4-a716-446655440013', 'bs.phamvanminh@stayhealthy.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345680','550e8400-e29b-41d4-a716-446655440002', true, '2024-02-10 08:30:00', '2024-02-10 08:30:00'),
       ('650e8400-e29b-41d4-a716-446655440014', 'bs.nguyenthihanh@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345681','550e8400-e29b-41d4-a716-446655440002', true, '2024-02-15 09:15:00', '2024-02-15 09:15:00'),
       ('650e8400-e29b-41d4-a716-446655440015', 'bs.hoangvantuan@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345682','550e8400-e29b-41d4-a716-446655440002', true, '2024-02-20 10:30:00', '2024-02-20 10:30:00'),
       ('650e8400-e29b-41d4-a716-446655440016', 'mindang2@gmail.com','$2a$10$xgeCIaFJApydu/l6.flcrOYnLl1n2fSOMi5DVzsCf8MsOzeJadH9W', '0901234568','550e8400-e29b-41d4-a716-446655440002', true, '2024-02-20 10:30:00', '2024-02-20 10:30:00'),
        ('650e8400-e29b-41d4-a716-446655440017', 'bs.nguyenvana@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345683','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440018', 'bs.tranthingoc@stayhealthy.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345684','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440019', 'bs.phamminhduc@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345685','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440020', 'bs.hoangthikim@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345686', '550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440021', 'bs.levandung@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345687','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440022', 'bs.dangthilan@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345688','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440023', 'bs.vuthanhhai@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345689','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440024', 'bs.buithanhdat@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345690','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440025', 'bs.lythithuy@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345691','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440026', 'bs.tranvanmanh@stayhealthy.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345692','550e8400-e29b-41d4-a716-446655440002', true, NOW(), NOW());

-- Patient Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES  ('650e8400-e29b-41d4-a716-446655440027', 'nguyen.vanan@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456789','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-01 14:00:00', '2024-03-01 14:00:00'),
        ('650e8400-e29b-41d4-a716-446655440028', 'tran.thihong@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456790','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-05 15:00:00', '2024-03-05 15:00:00'),
        ('650e8400-e29b-41d4-a716-446655440029', 'le.vanbinh@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456791','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-10 16:00:00', '2024-03-10 16:00:00'),
        ('650e8400-e29b-41d4-a716-446655440030', 'pham.thilan@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456792','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-15 10:30:00', '2024-03-15 10:30:00'),
        ('650e8400-e29b-41d4-a716-446655440031', 'hoang.vannam@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456793','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-20 11:00:00', '2024-03-20 11:00:00'),
        ('650e8400-e29b-41d4-a716-446655440032', 'do.thiphuong@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456794','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-25 13:00:00', '2024-03-25 13:00:00'),
        ('650e8400-e29b-41d4-a716-446655440033', 'vu.vanhung@gmail.com','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456795','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-28 14:30:00', '2024-03-28 14:30:00'),
        ('650e8400-e29b-41d4-a716-446655440034', 'mindang1@gmail.com',               '$2a$10$H2rqXoAONygpzLmOaqiVAeioiR.fbj29FMBVREw2P9NIwP5NE23XS', '0901234567','550e8400-e29b-41d4-a716-446655440003', true, '2024-03-28 14:30:00', '2024-03-28 14:30:00'),
        ('650e8400-e29b-41d4-a716-446655440035', 'patient.tranvan2@gmail.com',      '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456796', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440036', 'patient.nguyenthi2@gmail.com',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456797', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440037', 'patient.levan2@gmail.com',         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456798', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440038', 'patient.phamthi2@gmail.com',       '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456799', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440039', 'patient.hoangvan2@gmail.com',      '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456800', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440040', 'patient.dothanh2@gmail.com',       '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456801', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440041', 'patient.vuthi2@gmail.com',         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456802', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440042', 'patient.buivan2@gmail.com',        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456803', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440043', 'patient.lythi2@gmail.com',         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456804', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440044', 'patient.tranminh2@gmail.com',      '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456805', '550e8400-e29b-41d4-a716-446655440003', true, NOW(), NOW());

-- Clinic Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440045', 'contact@benhvienchobray.vn','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567890','550e8400-e29b-41d4-a716-446655440004', true, '2024-01-10 08:00:00', '2024-01-10 08:00:00'),
       ('650e8400-e29b-41d4-a716-446655440046', 'info@phongkhamhoanghoa.vn','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567891','550e8400-e29b-41d4-a716-446655440004', true, '2024-01-12 09:00:00', '2024-01-12 09:00:00'),
       ('650e8400-e29b-41d4-a716-446655440047', 'contact@medicalcenter.vn','$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567892','550e8400-e29b-41d4-a716-446655440004', true, '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
       ('650e8400-e29b-41d4-a716-446655440048', 'mindang3@gmail.com','$2a$10$MpneJ8u0kFTx2RU.NQ0pc.S3HWprajaFgDRlQCiCNt1D.OWZKfAau', '0901234569','550e8400-e29b-41d4-a716-446655440004', true, '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
        ('650e8400-e29b-41d4-a716-446655440049', 'contact@phongkhamanhduong.vn',     '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567893', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440050', 'info@benhvienvietduc.vn',          '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567894', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440051', 'contact@tamanhhospital.vn',        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567895', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440052', 'info@phongkhamhue.vn',             '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567896', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440053', 'contact@meditecclinic.vn',         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567897', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440054', 'info@phongkhamthienan.vn',         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567898', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440055', 'contact@hongngochospital.vn',      '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567899', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440056', 'info@phongkhamfamily.vn',          '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567900', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440057', 'contact@vinmecsaigon.vn',          '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567901', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW()),
        ('650e8400-e29b-41d4-a716-446655440058', 'info@phongkhamhathanh.vn',         '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567902', '550e8400-e29b-41d4-a716-446655440004', true, NOW(), NOW());

-- Insert Admins
INSERT INTO admins (id, user_id, full_name, gender, address)
VALUES ('750e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440001', 'Trần Văn Quản', true,'123 Nguyễn Huệ, Quận 1, TP.HCM'),
       ('750e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440002', 'Nguyễn Thị Lan', false,'456 Lê Lợi, Quận 3, TP.HCM');

-- Insert Doctors
INSERT INTO doctors (id, user_id, full_name, bio, gender, address, avatar_url)
VALUES ('750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
        'Bác sĩ chuyên khoa Nội tổng quát với hơn 15 năm kinh nghiệm. Chuyên điều trị các bệnh về tim mạch, tiểu đường và cao huyết áp.',
        true, '78 Pasteur, Quận 1, TP.HCM', 'https://example.com/avatars/doctor1.jpg'),
       ('750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440012', 'BS. Lê Thị Mai',
        'Bác sĩ chuyên khoa Sản - Phụ khoa, tốt nghiệp Đại học Y Hà Nội. Có kinh nghiệm 10 năm trong lĩnh vực chăm sóc sức khỏe sinh sản và điều trị vô sinh.',
        false, '234 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', 'https://example.com/avatars/doctor2.jpg'),
       ('750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440013', 'BS. Phạm Văn Minh',
        'Bác sĩ chuyên khoa Nhi với 12 năm kinh nghiệm. Chuyên điều trị các bệnh về hô hấp, tiêu hóa và phát triển ở trẻ em.',
        true, '567 Cách Mạng Tháng 8, Quận 10, TP.HCM', 'https://example.com/avatars/doctor3.jpg'),
       ('750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440014', 'BS. Nguyễn Thị Hạnh',
        'Bác sĩ chuyên khoa Da liễu, tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM. Chuyên điều trị mụn trứng cá, viêm da và các bệnh nấm da.',
        false, '89 Võ Văn Tần, Quận 3, TP.HCM', 'https://example.com/avatars/doctor4.jpg'),
       ('750e8400-e29b-41d4-a716-446655440015', '650e8400-e29b-41d4-a716-446655440015', 'BS. Hoàng Văn Tuấn',
        'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
        true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://example.com/avatars/doctor5.jpg'),
       ('750e8400-e29b-41d4-a716-446655440016', '650e8400-e29b-41d4-a716-446655440016', 'BS. Trần Minh Đăng 2',
        'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
        true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://example.com/avatars/doctor5.jpg'),

       ('750e8400-e29b-41d4-a716-446655440017', '650e8400-e29b-41d4-a716-446655440017', 'BS. Nguyễn Văn A',
        'Bác sĩ chuyên khoa Tim mạch can thiệp với hơn 18 năm kinh nghiệm. Tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM, từng tu nghiệp tại Pháp về can thiệp mạch vành và đặt stent. Chuyên điều trị nhồi máu cơ tim, rối loạn nhịp tim và suy tim.',
        true, '180 Lê Lợi, Quận 1, TP.HCM',
        'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440018', '650e8400-e29b-41d4-a716-446655440018', 'BS. Trần Thị Ngọc Anh',
        'Bác sĩ chuyên khoa Sản phụ khoa, chuyên sâu về vô sinh – hiếm muộn và thai kỳ nguy cơ cao. Hơn 14 năm kinh nghiệm, thực hiện thành công hàng nghìn ca thụ tinh trong ống nghiệm (IVF) và hỗ trợ sinh sản. Từng công tác tại Bệnh viện Từ Dũ và Bệnh viện Hùng Vương.',
        false, '56 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440019', '650e8400-e29b-41d4-a716-446655440019', 'BS. Phạm Minh Đức',
        'Bác sĩ chuyên khoa Nhi với 15 năm kinh nghiệm, chuyên sâu về hô hấp trẻ em và chương trình tiêm chủng mở rộng. Nguyên Trưởng khoa Hô hấp – Bệnh viện Nhi Đồng 1, đã điều trị thành công hàng nghìn ca viêm phổi nặng và hen phế quản ở trẻ em.',
        true, '321 Lý Thường Kiệt, Quận 10, TP.HCM',
        'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440020', '650e8400-e29b-41d4-a716-446655440020', 'BS. Hoàng Thị Kim Liên',
        'Bác sĩ chuyên khoa Da liễu – Thẩm mỹ với hơn 12 năm kinh nghiệm. Chuyên trị mụn nội tiết, nám, sẹo rỗ và trẻ hóa da bằng laser Fractional CO2, HIFU, Thermage. Tốt nghiệp loại Xuất sắc Đại học Y Dược TP.HCM, tu nghiệp thẩm mỹ tại Hàn Quốc.',
        false, '89 Phạm Ngọc Thạch, Quận 3, TP.HCM',
        'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440021', '650e8400-e29b-41d4-a716-446655440021', 'BS. Lê Văn Dũng',
        'Bác sĩ chuyên khoa Ngoại Thần kinh, chuyên phẫu thuật cột sống và u não. Hơn 20 năm kinh nghiệm tại Bệnh viện Chợ Rẫy, thực hiện thành công hàng trăm ca vi phẫu u não, thoát vị đĩa đệm và cố định cột sống.',
        true, 'Bệnh viện Chợ Rẫy, Quận 5, TP.HCM',
        'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440022', '650e8400-e29b-41d4-a716-446655440022', 'BS. Đặng Thị Lan',
        'Bác sĩ chuyên khoa Tiêu hóa – Gan mật với 16 năm kinh nghiệm. Chuyên nội soi không đau, điều trị viêm loét dạ dày, trào ngược, viêm gan virus và xơ gan. Nguyên Phó trưởng khoa Tiêu hóa Bệnh viện Đại học Y Dược TP.HCM.',
        false, '123 Võ Văn Tần, Quận 3, TP.HCM',
        'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440023', '650e8400-e29b-41d4-a716-446655440023', 'BS. Vũ Thanh Hải',
        'Bác sĩ chuyên khoa Thần kinh, chuyên điều trị đột quỵ cấp và bệnh Parkinson. Hơn 18 năm kinh nghiệm tại Bệnh viện Nhân dân 115, là thành viên nhóm đáp ứng đột quỵ nhanh (Stroke Team) của bệnh viện.',
        true, 'Bệnh viện Nhân dân 115, Quận 10, TP.HCM',
        'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440024', '650e8400-e29b-41d4-a716-446655440024', 'BS. Bùi Thanh Đạt',
        'Bác sĩ chuyên khoa Chấn thương chỉnh hình – Thay khớp với hơn 22 năm kinh nghiệm. Chuyên thay khớp háng, khớp gối toàn phần và bán phần, phẫu thuật chỉnh hình cột sống. Từng tu nghiệp thay khớp tại Singapore và Thái Lan.',
        true, '256 Nguyễn Tri Phương, Quận 5, TP.HCM',
        'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440025', '650e8400-e29b-41d4-a716-446655440025', 'BS. Lý Thị Thùy Dung',
        'Bác sĩ chuyên khoa Nội tiết – Đái tháo đường và bệnh tuyến giáp. Hơn 15 năm kinh nghiệm, nguyên Trưởng khoa Nội tiết Bệnh viện Chợ Rẫy. Chuyên điều trị đái tháo đường type 1 & 2, biến chứng bàn chân đái tháo đường, Basedow và suy giáp.',
        false, '99 Trần Hưng Đạo, Quận 1, TP.HCM',
        'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=400&h=400&fit=crop&crop=face'),

       ('750e8400-e29b-41d4-a716-446655440026', '650e8400-e29b-41d4-a716-446655440026', 'BS. Trần Văn Mạnh',
        'Bác sĩ chuyên khoa Tai Mũi Họng với 17 năm kinh nghiệm. Chuyên nội soi mũi xoang chức năng (FESS), phẫu thuật amidan, vá màng nhĩ và điều trị viêm mũi dị ứng, điếc đột ngột. Nguyên Phó khoa TMH Bệnh viện Tai Mũi Họng TP.HCM.',
        true, 'Bệnh viện Tai Mũi Họng TP.HCM, Quận 5',
        'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop&crop=face');


-- Insert Patients
INSERT INTO patients (id, user_id, full_name, date_of_birth, gender, medical_notes)
VALUES ('750e8400-e29b-41d4-a716-446655440027', '650e8400-e29b-41d4-a716-446655440021', 'Nguyễn Văn An', '1985-03-15',
        true, 'Tiền sử tăng huyết áp. Đang dùng thuốc hạ áp đều đặn. Cần theo dõi chỉ số huyết áp định kỳ.'),
       ('750e8400-e29b-41d4-a716-446655440028', '650e8400-e29b-41d4-a716-446655440022', 'Trần Thị Hồng', '1990-07-22',
        false, 'Dị ứng với Penicillin. Tiền sử sinh mổ. Đang trong giai đoạn theo dõi sau sinh.'),
       ('750e8400-e29b-41d4-a716-446655440029', '650e8400-e29b-41d4-a716-446655440023', 'Lê Văn Bình', '1978-11-30',
        true, 'Tiểu đường type 2. Đang điều trị bằng Metformin 500mg x 2 lần/ngày. Cần kiểm soát chế độ ăn.'),
       ('750e8400-e29b-41d4-a716-446655440030', '650e8400-e29b-41d4-a716-446655440024', 'Phạm Thị Lan', '1995-05-18',
        false, 'Không có tiền sử bệnh lý đặc biệt. Khám sức khỏe định kỳ.'),
       ('750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440025', 'Hoàng Văn Nam', '1982-09-10',
        true,
        'Viêm gan B mạn tính. Đang theo dõi và điều trị tại Bệnh viện Bệnh Nhiệt đới. Cần xét nghiệm chức năng gan 3 tháng/lần.'),
       ('750e8400-e29b-41d4-a716-446655440032', '650e8400-e29b-41d4-a716-446655440026', 'Đỗ Thị Phượng', '1988-12-05',
        false, 'Hen phế quản. Luôn mang theo bình xịt Ventolin. Tránh tiếp xúc với khói bụi và phấn hoa.'),
       ('750e8400-e29b-41d4-a716-446655440033', '650e8400-e29b-41d4-a716-446655440027', 'Vũ Văn Hùng', '2000-04-25',
        true, 'Sinh viên năm 3 Đại học Y. Không có tiền sử bệnh lý. Khám sức khỏe để xin visa du học.'),
       ('750e8400-e29b-41d4-a716-446655440034', '650e8400-e29b-41d4-a716-446655440028', 'Trần Minh Đăng 1', '2005-05-19',
        true, 'Sinh viên năm 3 Đại học Y. Không có tiền sử bệnh lý. Khám sức khỏe để xin visa du học.'),
       ('750e8400-e29b-41d4-a716-446655440035', '650e8400-e29b-41d4-a716-446655440029', 'Trần Văn Bình', '1992-06-15', true,  'Tăng huyết áp độ 2, đang dùng Amlodipine 5mg.'),
       ('750e8400-e29b-41d4-a716-446655440036', '650e8400-e29b-41d4-a716-446655440030', 'Nguyễn Thị Mai', '1988-11-20', false, 'Tiểu đường thai kỳ lần 1.'),
       ('750e8400-e29b-41d4-a716-446655440037', '650e8400-e29b-41d4-a716-446655440031', 'Lê Văn Hoàng', '1975-03-10', true,  'Viêm gan B mạn, e antigen dương tính.'),
       ('750e8400-e29b-41d4-a716-446655440038', '650e8400-e29b-41d4-a716-446655440032', 'Phạm Thị Lan Anh', '1995-09-05', false, 'Dị ứng Penicillin nặng.'),
       ('750e8400-e29b-41d4-a716-446655440039', '650e8400-e29b-41d4-a716-446655440033', 'Hoàng Văn Nam', '1980-12-25', true,  'Hen phế quản trung bình.'),
       ('750e8400-e29b-41d4-a716-446655440040', '650e8400-e29b-41d4-a716-446655440034', 'Đỗ Thanh Tâm', '2002-07-18', false, 'Sinh viên, không bệnh lý nền.'),
       ('750e8400-e29b-41d4-a716-446655440041', '650e8400-e29b-41d4-a716-446655440035', 'Vũ Thị Hương', '1990-04-30', false, 'Mang thai tuần 12.'),
       ('750e8400-e29b-41d4-a716-446655440042', '650e8400-e29b-41d4-a716-446655440036', 'Bùi Văn Minh', '1985-01-08', true,  'Thoái hóa cột sống thắt lưng.'),
       ('750e8400-e29b-41d4-a716-446655440043', '650e8400-e29b-41d4-a716-446655440037', 'Lý Thị Ngọc', '1998-10-12', false, 'Viêm da cơ địa tái phát.'),
       ('750e8400-e29b-41d4-a716-446655440044', '650e8400-e29b-41d4-a716-446655440038', 'Trần Minh Tuấn', '1970-08-20', true,  'Suy giãn tĩnh mạch chi dưới độ 3.');

-- Insert Clinics
INSERT INTO clinics (id, user_id, clinic_name, address, description, weekday_open_hour, weekday_close_hour, weekend_open_hour,  weekend_close_hour, logo_url, avatar_url)
VALUES ('750e8400-e29b-41d4-a716-446655440045', '650e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy',
        '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        'Bệnh viện đa khoa hạng đặc biệt, là một trong những bệnh viện lớn nhất khu vực phía Nam. Cung cấp đầy đủ các dịch vụ y tế từ khám chữa bệnh đến phẫu thuật chuyên sâu.',
        '07:00', '21:00', '07:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440046', '650e8400-e29b-41d4-a716-446655440032', 'Phòng khám Đa khoa Hoàng Hoa',
        '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM',
        'Phòng khám đa khoa uy tín với đội ngũ bác sĩ giàu kinh nghiệm. Chuyên khám và điều trị nội khoa, ngoại khoa, sản phụ khoa và nhi khoa.',
        '08:00', '20:00', '08:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760282444/jwzn0b5e3amdapletedu.jpg', 'https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440047', '650e8400-e29b-41d4-a716-446655440033', 'Trung tâm Y tế Medical Center',
        '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440048', '650e8400-e29b-41d4-a716-446655440034', 'Trung tâm Y tế Trần Minh Đăng 3',
        '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg'),
       ('750e8400-e29b-41d4-a716-446655440049', '650e8400-e29b-41d4-a716-446655440041', 'Phòng khám Đa khoa Ánh Dương', '180 Nguyễn Thị Minh Khai, Q.3', 'Phòng khám đa khoa hiện đại.', '07:30','20:00','08:00','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440050', '650e8400-e29b-41d4-a716-446655440042', 'Bệnh viện Việt Đức cơ sở 2', '268 Nguyễn Trãi, Q.5', 'Chuyên sâu Ngoại khoa, Tim mạch.', '00:00','23:59','00:00','23:59', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440051', '650e8400-e29b-41d4-a716-446655440043', 'Phòng khám Tâm Anh', '12 Nguyễn Văn Trỗi, Phú Nhuận', 'Chuyên Sản phụ khoa và Nhi khoa.', '07:00','21:00','07:30','18:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440052', '650e8400-e29b-41d4-a716-446655440044', 'Phòng khám Huệ Meditec', '456 Lê Văn Sỹ, Q.3', 'Chuyên Da liễu – Thẩm mỹ.', '08:00','20:00','08:30','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440053', '650e8400-e29b-41d4-a716-446655440045', 'Phòng khám Thiên Ân', '78 Bis Kỳ Đồng, Q.3', 'Đa khoa gia đình, khám BHYT.', '07:30','19:30','08:00','12:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440054', '650e8400-e29b-41d4-a716-446655440046', 'Bệnh viện Hồng Ngọc', '55 Yên Ninh (cơ sở phía Nam)', 'Dịch vụ y tế cao cấp.', '07:00','20:00','07:00','20:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440055', '650e8400-e29b-41d4-a716-446655440047', 'Phòng khám Family Medical', 'Vista Verde, Q.2', 'Phòng khám quốc tế.', '08:00','20:00','08:00','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440056', '650e8400-e29b-41d4-a716-446655440048', 'Vinmec Saigon', 'Vincom Center, Q.1', 'Hệ thống Vinmec chuẩn quốc tế.', '07:00','21:00','07:00','21:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440057', '650e8400-e29b-41d4-a716-446655440049', 'Phòng khám Hà Thanh', '280A Điện Biên Phủ, Q.3', 'Chuyên Da liễu & Thẩm mỹ.', '08:30','19:30','09:00','16:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440058', '650e8400-e29b-41d4-a716-446655440050', 'Phòng khám Đa khoa Hạnh Phúc', '99 Trần Quốc Thảo, Q.3', 'Phòng khám gia đình thân thiện.', '07:30','20:30','08:00','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop')