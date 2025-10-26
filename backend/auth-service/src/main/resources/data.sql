-- Insert Roles
INSERT INTO roles (id, role_name)
VALUES ('550e8400-e29b-41d4-a716-446655440001', 'ADMIN'),
       ('550e8400-e29b-41d4-a716-446655440002', 'DOCTOR'),
       ('550e8400-e29b-41d4-a716-446655440003', 'PATIENT'),
       ('550e8400-e29b-41d4-a716-446655440004', 'CLINIC');

-- Insert Users
-- Admin Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440001', 'admin@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0901234567',
        '550e8400-e29b-41d4-a716-446655440001', true, '2024-01-15 08:00:00', '2024-01-15 08:00:00'),
       ('650e8400-e29b-41d4-a716-446655440002', 'admin.nguyen@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0901234568',
        '550e8400-e29b-41d4-a716-446655440001', true, '2024-01-20 09:00:00', '2024-01-20 09:00:00');

-- Doctor Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440011', 'bs.trannguyen@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345678',
        '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-01 10:00:00', '2024-02-01 10:00:00'),
       ('650e8400-e29b-41d4-a716-446655440012', 'bs.lethimai@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345679',
        '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-05 11:00:00', '2024-02-05 11:00:00'),
       ('650e8400-e29b-41d4-a716-446655440013', 'bs.phamvanminh@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345680',
        '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-10 08:30:00', '2024-02-10 08:30:00'),
       ('650e8400-e29b-41d4-a716-446655440014', 'bs.nguyenthihanh@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345681',
        '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-15 09:15:00', '2024-02-15 09:15:00'),
       ('650e8400-e29b-41d4-a716-446655440015', 'bs.hoangvantuan@stayhealthy.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0912345682',
        '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-20 10:30:00', '2024-02-20 10:30:00'),
       ('650e8400-e29b-41d4-a716-446655440016', 'mindang2@gmail.com',
        '$2a$10$xgeCIaFJApydu/l6.flcrOYnLl1n2fSOMi5DVzsCf8MsOzeJadH9W', '0901234568',
        '550e8400-e29b-41d4-a716-446655440002', true, '2024-02-20 10:30:00', '2024-02-20 10:30:00');

-- Patient Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440021', 'nguyen.vanan@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456789',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-01 14:00:00', '2024-03-01 14:00:00'),
       ('650e8400-e29b-41d4-a716-446655440022', 'tran.thihong@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456790',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-05 15:00:00', '2024-03-05 15:00:00'),
       ('650e8400-e29b-41d4-a716-446655440023', 'le.vanbinh@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456791',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-10 16:00:00', '2024-03-10 16:00:00'),
       ('650e8400-e29b-41d4-a716-446655440024', 'pham.thilan@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456792',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-15 10:30:00', '2024-03-15 10:30:00'),
       ('650e8400-e29b-41d4-a716-446655440025', 'hoang.vannam@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456793',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-20 11:00:00', '2024-03-20 11:00:00'),
       ('650e8400-e29b-41d4-a716-446655440026', 'do.thiphuong@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456794',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-25 13:00:00', '2024-03-25 13:00:00'),
       ('650e8400-e29b-41d4-a716-446655440027', 'vu.vanhung@gmail.com',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0923456795',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-28 14:30:00', '2024-03-28 14:30:00'),
       ('650e8400-e29b-41d4-a716-446655440028', 'mindang1@gmail.com',
        '$2a$10$H2rqXoAONygpzLmOaqiVAeioiR.fbj29FMBVREw2P9NIwP5NE23XS', '0901234567',
        '550e8400-e29b-41d4-a716-446655440003', true, '2024-03-28 14:30:00', '2024-03-28 14:30:00');

-- Clinic Users
INSERT INTO users (id, email, password, phone_number, role_id, is_active, created_at, updated_at)
VALUES ('650e8400-e29b-41d4-a716-446655440031', 'contact@benhvienchobray.vn',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567890',
        '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-10 08:00:00', '2024-01-10 08:00:00'),
       ('650e8400-e29b-41d4-a716-446655440032', 'info@phongkhamhoanghoa.vn',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567891',
        '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-12 09:00:00', '2024-01-12 09:00:00'),
       ('650e8400-e29b-41d4-a716-446655440033', 'contact@medicalcenter.vn',
        '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', '0934567892',
        '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-15 10:00:00', '2024-01-15 10:00:00'),
       ('650e8400-e29b-41d4-a716-446655440034', 'mindang3@gmail.com',
        '$2a$10$MpneJ8u0kFTx2RU.NQ0pc.S3HWprajaFgDRlQCiCNt1D.OWZKfAau', '0901234569',
        '550e8400-e29b-41d4-a716-446655440004', true, '2024-01-15 10:00:00', '2024-01-15 10:00:00');

-- Insert Admins
INSERT INTO admins (id, user_id, full_name, gender, address)
VALUES ('750e8400-e29b-41d4-a716-446655440001', '650e8400-e29b-41d4-a716-446655440001', 'Trần Văn Quản', true,
        '123 Nguyễn Huệ, Quận 1, TP.HCM'),
       ('750e8400-e29b-41d4-a716-446655440002', '650e8400-e29b-41d4-a716-446655440002', 'Nguyễn Thị Lan', false,
        '456 Lê Lợi, Quận 3, TP.HCM');

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
        true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://example.com/avatars/doctor5.jpg');

-- Insert Patients
INSERT INTO patients (id, user_id, full_name, date_of_birth, gender, medical_notes)
VALUES ('750e8400-e29b-41d4-a716-446655440021', '650e8400-e29b-41d4-a716-446655440021', 'Nguyễn Văn An', '1985-03-15',
        true, 'Tiền sử tăng huyết áp. Đang dùng thuốc hạ áp đều đặn. Cần theo dõi chỉ số huyết áp định kỳ.'),
       ('750e8400-e29b-41d4-a716-446655440022', '650e8400-e29b-41d4-a716-446655440022', 'Trần Thị Hồng', '1990-07-22',
        false, 'Dị ứng với Penicillin. Tiền sử sinh mổ. Đang trong giai đoạn theo dõi sau sinh.'),
       ('750e8400-e29b-41d4-a716-446655440023', '650e8400-e29b-41d4-a716-446655440023', 'Lê Văn Bình', '1978-11-30',
        true, 'Tiểu đường type 2. Đang điều trị bằng Metformin 500mg x 2 lần/ngày. Cần kiểm soát chế độ ăn.'),
       ('750e8400-e29b-41d4-a716-446655440024', '650e8400-e29b-41d4-a716-446655440024', 'Phạm Thị Lan', '1995-05-18',
        false, 'Không có tiền sử bệnh lý đặc biệt. Khám sức khỏe định kỳ.'),
       ('750e8400-e29b-41d4-a716-446655440025', '650e8400-e29b-41d4-a716-446655440025', 'Hoàng Văn Nam', '1982-09-10',
        true,
        'Viêm gan B mạn tính. Đang theo dõi và điều trị tại Bệnh viện Bệnh Nhiệt đới. Cần xét nghiệm chức năng gan 3 tháng/lần.'),
       ('750e8400-e29b-41d4-a716-446655440026', '650e8400-e29b-41d4-a716-446655440026', 'Đỗ Thị Phượng', '1988-12-05',
        false, 'Hen phế quản. Luôn mang theo bình xịt Ventolin. Tránh tiếp xúc với khói bụi và phấn hoa.'),
       ('750e8400-e29b-41d4-a716-446655440027', '650e8400-e29b-41d4-a716-446655440027', 'Vũ Văn Hùng', '2000-04-25',
        true, 'Sinh viên năm 3 Đại học Y. Không có tiền sử bệnh lý. Khám sức khỏe để xin visa du học.'),
       ('750e8400-e29b-41d4-a716-446655440028', '650e8400-e29b-41d4-a716-446655440028', 'Trần Minh Đăng 1', '2005-05-19',
        true, 'Sinh viên năm 3 Đại học Y. Không có tiền sử bệnh lý. Khám sức khỏe để xin visa du học.');

-- Insert Clinics
INSERT INTO clinics (id, user_id, clinic_name, address, description, weekday_open_hour, weekday_close_hour, weekend_open_hour,  weekend_close_hour, logo_url, avatar_url)
VALUES ('750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy',
        '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        'Bệnh viện đa khoa hạng đặc biệt, là một trong những bệnh viện lớn nhất khu vực phía Nam. Cung cấp đầy đủ các dịch vụ y tế từ khám chữa bệnh đến phẫu thuật chuyên sâu.',
        '07:00', '21:00', '07:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440032', '650e8400-e29b-41d4-a716-446655440032', 'Phòng khám Đa khoa Hoàng Hoa',
        '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM',
        'Phòng khám đa khoa uy tín với đội ngũ bác sĩ giàu kinh nghiệm. Chuyên khám và điều trị nội khoa, ngoại khoa, sản phụ khoa và nhi khoa.',
        '08:00', '20:00', '08:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760282444/jwzn0b5e3amdapletedu.jpg', 'https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440033', '650e8400-e29b-41d4-a716-446655440033', 'Trung tâm Y tế Medical Center',
        '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440034', '650e8400-e29b-41d4-a716-446655440034', 'Trung tâm Y tế Trần Minh Đăng 3',
        '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop');