-- Insert Specialties
INSERT INTO specialties (id, name)
VALUES ('850e8400-e29b-41d4-a716-446655440001', 'Nội tổng quát'),
       ('850e8400-e29b-41d4-a716-446655440002', 'Sản - Phụ khoa'),
       ('850e8400-e29b-41d4-a716-446655440003', 'Nhi khoa'),
       ('850e8400-e29b-41d4-a716-446655440004', 'Da liễu'),
       ('850e8400-e29b-41d4-a716-446655440005', 'Ngoại tổng hợp'),
       ('850e8400-e29b-41d4-a716-446655440006', 'Tim mạch'),
       ('850e8400-e29b-41d4-a716-446655440007', 'Tiêu hóa'),
       ('850e8400-e29b-41d4-a716-446655440008', 'Hô hấp'),
       ('850e8400-e29b-41d4-a716-446655440009', 'Thần kinh'),
       ('850e8400-e29b-41d4-a716-446655440010', 'Chỉnh hình');

-- Insert Doctors (matching user_ids from auth-service)
INSERT INTO doctors (id, user_id, full_name, bio, gender, address, avatar_url)
VALUES ('750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
        'Bác sĩ chuyên khoa Nội tổng quát với hơn 15 năm kinh nghiệm. Chuyên điều trị các bệnh về tim mạch, tiểu đường và cao huyết áp.',
        true, '78 Pasteur, Quận 1, TP.HCM', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440012', 'BS. Lê Thị Mai',
        'Bác sĩ chuyên khoa Sản - Phụ khoa, tốt nghiệp Đại học Y Hà Nội. Có kinh nghiệm 10 năm trong lĩnh vực chăm sóc sức khỏe sinh sản và điều trị vô sinh.',
        false, '234 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', 'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=200&h=200&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440013', 'BS. Phạm Văn Minh',
        'Bác sĩ chuyên khoa Nhi với 12 năm kinh nghiệm. Chuyên điều trị các bệnh về hô hấp, tiêu hóa và phát triển ở trẻ em.',
        true, '567 Cách Mạng Tháng 8, Quận 10, TP.HCM', 'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=200&h=200&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440014', 'BS. Nguyễn Thị Hạnh',
        'Bác sĩ chuyên khoa Da liễu, tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM. Chuyên điều trị mụn trứng cá, viêm da và các bệnh nấm da.',
        false, '89 Võ Văn Tần, Quận 3, TP.HCM', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440015', '650e8400-e29b-41d4-a716-446655440015', 'BS. Hoàng Văn Tuấn',
        'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
        true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440016', '650e8400-e29b-41d4-a716-446655440016', 'BS. Trần Minh Đăng 2',
        'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
        true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop');

-- Insert Clinics (matching user_ids from auth-service)
INSERT INTO clinics (id, user_id, clinic_name, address, description, weekday_open_hour, weekday_close_hour, weekend_open_hour,  weekend_close_hour, logo_url, avatar_url)
VALUES ('750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy',
        '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        'Bệnh viện đa khoa hạng đặc biệt, là một trong những bệnh viện lớn nhất khu vực phía Nam. Cung cấp đầy đủ các dịch vụ y tế từ khám chữa bệnh đến phẫu thuật chuyên sâu.',
        '07:00', '21:00', '07:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440032', '650e8400-e29b-41d4-a716-446655440032',
        'Phòng khám Đa khoa Hoàng Hoa', '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM',
        'Phòng khám đa khoa uy tín với đội ngũ bác sĩ giàu kinh nghiệm. Chuyên khám và điều trị nội khoa, ngoại khoa, sản phụ khoa và nhi khoa.',
        '08:00', '20:00', '08:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760282444/jwzn0b5e3amdapletedu.jpg', 'https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440033', '650e8400-e29b-41d4-a716-446655440033',
        'Trung tâm Y tế Medical Center', '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440034', '650e8400-e29b-41d4-a716-446655440034', 'Trung tâm Y tế Trần Minh Đăng 3',
        '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop');

-- Insert Doctor Specialties (Bác sĩ và chuyên khoa của họ)
INSERT INTO doctor_specialties (id, doctor_id, specialty_id)
VALUES
-- BS. Trần Văn Nguyên - Nội tổng quát, Tim mạch
('950e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440011',
 '850e8400-e29b-41d4-a716-446655440001'),
('950e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440011',
 '850e8400-e29b-41d4-a716-446655440006'),
-- BS. Lê Thị Mai - Sản - Phụ khoa
('950e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440012',
 '850e8400-e29b-41d4-a716-446655440002'),
-- BS. Phạm Văn Minh - Nhi khoa, Hô hấp
('950e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440013',
 '850e8400-e29b-41d4-a716-446655440003'),
('950e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440013',
 '850e8400-e29b-41d4-a716-446655440008'),
-- BS. Nguyễn Thị Hạnh - Da liễu
('950e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440014',
 '850e8400-e29b-41d4-a716-446655440004'),
-- BS. Hoàng Văn Tuấn - Ngoại tổng hợp
('950e8400-e29b-41d4-a716-446655440007', '750e8400-e29b-41d4-a716-446655440015',
 '850e8400-e29b-41d4-a716-446655440005'),
    -- BS. Trần Minh Đăng 2 - Ngoại tổng hợp
('950e8400-e29b-41d4-a716-446655440008', '750e8400-e29b-41d4-a716-446655440016',
 '850e8400-e29b-41d4-a716-446655440005');

-- Insert Clinic Specialties (Chuyên khoa mà phòng khám cung cấp)
INSERT INTO clinic_specialties (id, clinic_id, specialty_id)
VALUES
-- Bệnh viện Chợ Rẫy - Có đầy đủ các chuyên khoa
('a50e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440031',
 '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440031',
 '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440031',
 '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440031',
 '850e8400-e29b-41d4-a716-446655440004'),
('a50e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440031',
 '850e8400-e29b-41d4-a716-446655440005'),
('a50e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440031',
 '850e8400-e29b-41d4-a716-446655440006'),
-- Phòng khám Hoàng Hoa - Đa khoa cơ bản
('a50e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440032',
 '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440032',
 '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440032',
 '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440032',
 '850e8400-e29b-41d4-a716-446655440004'),
-- Medical Center - Chuyên khoa cao cấp
('a50e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440033',
 '850e8400-e29b-41d4-a716-446655440006'),
('a50e8400-e29b-41d4-a716-446655440022', '750e8400-e29b-41d4-a716-446655440033',
 '850e8400-e29b-41d4-a716-446655440007'),
('a50e8400-e29b-41d4-a716-446655440023', '750e8400-e29b-41d4-a716-446655440033',
 '850e8400-e29b-41d4-a716-446655440009'),
-- Bệnh viện Trần Minh Đăng 3 - Có đầy đủ các chuyên khoa
('a50e8400-e29b-41d4-a716-446655440024', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440025', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440026', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440027', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440004'),
('a50e8400-e29b-41d4-a716-446655440028', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440005'),
('a50e8400-e29b-41d4-a716-446655440029', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440006'),
('a50e8400-e29b-41d4-a716-446655440030', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440007'),
('a50e8400-e29b-41d4-a716-446655440031', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440008'),
('a50e8400-e29b-41d4-a716-446655440032', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440009'),
('a50e8400-e29b-41d4-a716-446655440033', '750e8400-e29b-41d4-a716-446655440034',
 '850e8400-e29b-41d4-a716-446655440010');

-- Insert Clinic Invitations (Lời mời từ phòng khám đến bác sĩ)
INSERT INTO clinic_invitations (id, clinic_specialty_id, doctor_id, status, invited_at, responded_at)
VALUES
-- Bệnh viện Chợ Rẫy mời BS. Trần Văn Nguyên - Nội tổng quát (đã chấp nhận)
('b50e8400-e29b-41d4-a716-446655440001', 'a50e8400-e29b-41d4-a716-446655440001',
 '750e8400-e29b-41d4-a716-446655440011', 'ACCEPTED', '2024-03-01 09:00:00', '2024-03-02 10:30:00'),
-- Bệnh viện Chợ Rẫy mời BS. Trần Văn Nguyên - Tim mạch (đã chấp nhận)
('b50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440006',
 '750e8400-e29b-41d4-a716-446655440011', 'ACCEPTED', '2024-03-01 09:15:00', '2024-03-02 10:30:00'),
-- Bệnh viện Chợ Rẫy mời BS. Lê Thị Mai - Sản Phụ khoa (đã chấp nhận)
('b50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440002',
 '750e8400-e29b-41d4-a716-446655440012', 'ACCEPTED', '2024-03-03 08:00:00', '2024-03-03 14:00:00'),
-- Phòng khám Hoàng Hoa mời BS. Phạm Văn Minh - Nhi khoa (đã chấp nhận)
('b50e8400-e29b-41d4-a716-446655440004', 'a50e8400-e29b-41d4-a716-446655440013',
 '750e8400-e29b-41d4-a716-446655440013', 'ACCEPTED', '2024-03-05 10:00:00', '2024-03-06 09:00:00'),
-- Phòng khám Hoàng Hoa mời BS. Nguyễn Thị Hạnh - Da liễu (đang chờ)
('b50e8400-e29b-41d4-a716-446655440005', 'a50e8400-e29b-41d4-a716-446655440014',
 '750e8400-e29b-41d4-a716-446655440014', 'PENDING', '2024-03-28 11:00:00', NULL),
-- Medical Center mời BS. Trần Văn Nguyên - Tim mạch (đang chờ)
('b50e8400-e29b-41d4-a716-446655440006', 'a50e8400-e29b-41d4-a716-446655440021',
 '750e8400-e29b-41d4-a716-446655440011', 'PENDING', '2024-03-27 15:00:00', NULL),
-- Bệnh viện Chợ Rẫy mời BS. Hoàng Văn Tuấn - Ngoại tổng hợp (bị từ chối)
('b50e8400-e29b-41d4-a716-446655440007', 'a50e8400-e29b-41d4-a716-446655440005',
 '750e8400-e29b-41d4-a716-446655440015', 'REJECTED', '2024-03-10 08:00:00', '2024-03-12 10:00:00'),
('b50e8400-e29b-41d4-a716-446655440008', 'a50e8400-e29b-41d4-a716-446655440028',
 '750e8400-e29b-41d4-a716-446655440016', 'PENDING', '2024-10-27 08:00:00', '2024-03-12 10:00:00');

-- Insert Clinic Specialty Doctors (Bác sĩ đã được phân công vào chuyên khoa của phòng khám)
INSERT INTO clinic_specialty_doctors (id, clinic_specialty_id, doctor_id, assignment_count)
VALUES
-- BS. Trần Văn Nguyên tại Bệnh viện Chợ Rẫy - Nội tổng quát
('c50e8400-e29b-41d4-a716-446655440001', 'a50e8400-e29b-41d4-a716-446655440001',
 '750e8400-e29b-41d4-a716-446655440011', 15),
-- BS. Trần Văn Nguyên tại Bệnh viện Chợ Rẫy - Tim mạch
('c50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440006',
 '750e8400-e29b-41d4-a716-446655440011', 8),
-- BS. Lê Thị Mai tại Bệnh viện Chợ Rẫy - Sản Phụ khoa
('c50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440002',
 '750e8400-e29b-41d4-a716-446655440012', 12),
-- BS. Lê Thị Mai tại Phòng khám Hoàng Hoa - Sản Phụ khoa
('c50e8400-e29b-41d4-a716-446655440004', 'a50e8400-e29b-41d4-a716-446655440012',
 '750e8400-e29b-41d4-a716-446655440012', 5),
-- BS. Phạm Văn Minh tại Phòng khám Hoàng Hoa - Nhi khoa
('c50e8400-e29b-41d4-a716-446655440005', 'a50e8400-e29b-41d4-a716-446655440013',
 '750e8400-e29b-41d4-a716-446655440013', 10);

-- Note:
-- - Doctor user_ids match the ones from auth-service
-- - Clinic user_ids match the ones from auth-service
-- - InvitationStatus enum: PENDING, ACCEPTED, REJECTED
-- - assignment_count tracks how many times a doctor has been assigned to appointments