-- -- Insert Specialties
-- INSERT INTO specialties (id, name)
-- VALUES ('850e8400-e29b-41d4-a716-446655440001', 'Nội tổng quát'),
--        ('850e8400-e29b-41d4-a716-446655440002', 'Sản - Phụ khoa'),
--        ('850e8400-e29b-41d4-a716-446655440003', 'Nhi khoa'),
--        ('850e8400-e29b-41d4-a716-446655440004', 'Da liễu'),
--        ('850e8400-e29b-41d4-a716-446655440005', 'Ngoại tổng hợp'),
--        ('850e8400-e29b-41d4-a716-446655440006', 'Tim mạch'),
--        ('850e8400-e29b-41d4-a716-446655440007', 'Tiêu hóa'),
--        ('850e8400-e29b-41d4-a716-446655440008', 'Hô hấp'),
--        ('850e8400-e29b-41d4-a716-446655440009', 'Thần kinh'),
--        ('850e8400-e29b-41d4-a716-446655440010', 'Chỉnh hình');
--
-- -- Insert Doctors (matching user_ids from auth-service)
-- INSERT INTO doctors (
--     id, user_id, full_name, bio, gender, address, avatar_url, is_in_clinic_specialty
-- )
-- VALUES
--     ('750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440011',
--      'BS. Trần Văn Nguyên',
--      'Bác sĩ chuyên khoa Nội tổng quát với hơn 15 năm kinh nghiệm. Chuyên điều trị các bệnh về tim mạch, tiểu đường và cao huyết áp.',
--      true, '78 Pasteur, Quận 1, TP.HCM',
--      'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop',
--      false),
--
--     ('750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440012',
--      'BS. Lê Thị Mai',
--      'Bác sĩ chuyên khoa Sản - Phụ khoa, tốt nghiệp Đại học Y Hà Nội. Có kinh nghiệm 10 năm trong lĩnh vực chăm sóc sức khỏe sinh sản và điều trị vô sinh.',
--      false, '234 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM',
--      'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=200&h=200&fit=crop',
--      false),
--
--     ('750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440013',
--      'BS. Phạm Văn Minh',
--      'Bác sĩ chuyên khoa Nhi với 12 năm kinh nghiệm. Chuyên điều trị các bệnh về hô hấp, tiêu hóa và phát triển ở trẻ em.',
--      true, '567 Cách Mạng Tháng 8, Quận 10, TP.HCM',
--      'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=200&h=200&fit=crop',
--      false),
--
--     ('750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440014',
--      'BS. Nguyễn Thị Hạnh',
--      'Bác sĩ chuyên khoa Da liễu, tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM. Chuyên điều trị mụn trứng cá, viêm da và các bệnh nấm da.',
--      false, '89 Võ Văn Tần, Quận 3, TP.HCM',
--      'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop',
--      false),
--
--     ('750e8400-e29b-41d4-a716-446655440015', '650e8400-e29b-41d4-a716-446655440015',
--      'BS. Hoàng Văn Tuấn',
--      'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
--      true, '123 Trần Hưng Đạo, Quận 5, TP.HCM',
--      'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop',
--      false),
--
--     ('750e8400-e29b-41d4-a716-446655440016', '650e8400-e29b-41d4-a716-446655440016',
--      'BS. Trần Minh Đăng 2',
--      'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
--      true, '123 Trần Hưng Đạo, Quận 5, TP.HCM',
--      'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop',
--      false);
-- --     phần comment trước đó
-- -- INSERT INTO doctors (id, user_id, full_name, bio, gender, address, avatar_url)
-- -- VALUES ('750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
-- --         'Bác sĩ chuyên khoa Nội tổng quát với hơn 15 năm kinh nghiệm. Chuyên điều trị các bệnh về tim mạch, tiểu đường và cao huyết áp.',
-- --         true, '78 Pasteur, Quận 1, TP.HCM', 'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop'),
-- --        ('750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440012', 'BS. Lê Thị Mai',
-- --         'Bác sĩ chuyên khoa Sản - Phụ khoa, tốt nghiệp Đại học Y Hà Nội. Có kinh nghiệm 10 năm trong lĩnh vực chăm sóc sức khỏe sinh sản và điều trị vô sinh.',
-- --         false, '234 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', 'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=200&h=200&fit=crop'),
-- --        ('750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440013', 'BS. Phạm Văn Minh',
-- --         'Bác sĩ chuyên khoa Nhi với 12 năm kinh nghiệm. Chuyên điều trị các bệnh về hô hấp, tiêu hóa và phát triển ở trẻ em.',
-- --         true, '567 Cách Mạng Tháng 8, Quận 10, TP.HCM', 'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=200&h=200&fit=crop'),
-- --        ('750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440014', 'BS. Nguyễn Thị Hạnh',
-- --         'Bác sĩ chuyên khoa Da liễu, tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM. Chuyên điều trị mụn trứng cá, viêm da và các bệnh nấm da.',
-- --         false, '89 Võ Văn Tần, Quận 3, TP.HCM', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop'),
-- --        ('750e8400-e29b-41d4-a716-446655440015', '650e8400-e29b-41d4-a716-446655440015', 'BS. Hoàng Văn Tuấn',
-- --         'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
-- --         true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop'),
-- --        ('750e8400-e29b-41d4-a716-446655440016', '650e8400-e29b-41d4-a716-446655440016', 'BS. Trần Minh Đăng 2',
-- --         'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
-- --         true, '123 Trần Hưng Đạo, Quận 5, TP.HCM', 'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop');
--
-- -- Insert Clinics (matching user_ids from auth-service)
-- INSERT INTO clinics (id, user_id, clinic_name, address, description, weekday_open_hour, weekday_close_hour, weekend_open_hour,  weekend_close_hour, logo_url, avatar_url)
-- VALUES ('750e8400-e29b-41d4-a716-446655440031', '650e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy',
--         '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
--         'Bệnh viện đa khoa hạng đặc biệt, là một trong những bệnh viện lớn nhất khu vực phía Nam. Cung cấp đầy đủ các dịch vụ y tế từ khám chữa bệnh đến phẫu thuật chuyên sâu.',
--         '07:00', '21:00', '07:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop'),
--        ('750e8400-e29b-41d4-a716-446655440032', '650e8400-e29b-41d4-a716-446655440032',
--         'Phòng khám Đa khoa Hoàng Hoa', '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM',
--         'Phòng khám đa khoa uy tín với đội ngũ bác sĩ giàu kinh nghiệm. Chuyên khám và điều trị nội khoa, ngoại khoa, sản phụ khoa và nhi khoa.',
--         '08:00', '20:00', '08:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760282444/jwzn0b5e3amdapletedu.jpg', 'https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop'),
--        ('750e8400-e29b-41d4-a716-446655440033', '650e8400-e29b-41d4-a716-446655440033',
--         'Trung tâm Y tế Medical Center', '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
--         'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
--         '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
--        ('750e8400-e29b-41d4-a716-446655440034', '650e8400-e29b-41d4-a716-446655440034', 'Trung tâm Y tế Trần Minh Đăng 3',
--         '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
--         'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
--         '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop');
--
-- -- Insert Doctor Specialties (Bác sĩ và chuyên khoa của họ)
-- INSERT INTO doctor_specialties (id, doctor_id, specialty_id)
-- VALUES
-- -- BS. Trần Văn Nguyên - Nội tổng quát, Tim mạch
-- ('950e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440011',
--  '850e8400-e29b-41d4-a716-446655440001'),
-- ('950e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440011',
--  '850e8400-e29b-41d4-a716-446655440006'),
-- -- BS. Lê Thị Mai - Sản - Phụ khoa
-- ('950e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440012',
--  '850e8400-e29b-41d4-a716-446655440002'),
-- -- BS. Phạm Văn Minh - Nhi khoa, Hô hấp
-- ('950e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440013',
--  '850e8400-e29b-41d4-a716-446655440003'),
-- ('950e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440013',
--  '850e8400-e29b-41d4-a716-446655440008'),
-- -- BS. Nguyễn Thị Hạnh - Da liễu
-- ('950e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440014',
--  '850e8400-e29b-41d4-a716-446655440004'),
-- -- BS. Hoàng Văn Tuấn - Ngoại tổng hợp
-- ('950e8400-e29b-41d4-a716-446655440007', '750e8400-e29b-41d4-a716-446655440015',
--  '850e8400-e29b-41d4-a716-446655440005'),
-- -- BS. Trần Minh Đăng 2 - Ngoại tổng hợp
-- ('950e8400-e29b-41d4-a716-446655440008', '750e8400-e29b-41d4-a716-446655440016',
--  '850e8400-e29b-41d4-a716-446655440005'),
-- ('950e8400-e29b-41d4-a716-446655440009', '750e8400-e29b-41d4-a716-446655440016',
--  '850e8400-e29b-41d4-a716-446655440001');
--
-- -- Insert Clinic Specialties (Chuyên khoa mà phòng khám cung cấp)
-- INSERT INTO clinic_specialties (id, clinic_id, specialty_id)
-- VALUES
-- -- Bệnh viện Chợ Rẫy - Có đầy đủ các chuyên khoa
-- ('a50e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440031',
--  '850e8400-e29b-41d4-a716-446655440001'),
-- ('a50e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440031',
--  '850e8400-e29b-41d4-a716-446655440002'),
-- ('a50e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440031',
--  '850e8400-e29b-41d4-a716-446655440003'),
-- ('a50e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440031',
--  '850e8400-e29b-41d4-a716-446655440004'),
-- ('a50e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440031',
--  '850e8400-e29b-41d4-a716-446655440005'),
-- ('a50e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440031',
--  '850e8400-e29b-41d4-a716-446655440006'),
-- -- Phòng khám Hoàng Hoa - Đa khoa cơ bản
-- ('a50e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440032',
--  '850e8400-e29b-41d4-a716-446655440001'),
-- ('a50e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440032',
--  '850e8400-e29b-41d4-a716-446655440002'),
-- ('a50e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440032',
--  '850e8400-e29b-41d4-a716-446655440003'),
-- ('a50e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440032',
--  '850e8400-e29b-41d4-a716-446655440004'),
-- -- Medical Center - Chuyên khoa cao cấp
-- ('a50e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440033',
--  '850e8400-e29b-41d4-a716-446655440006'),
-- ('a50e8400-e29b-41d4-a716-446655440022', '750e8400-e29b-41d4-a716-446655440033',
--  '850e8400-e29b-41d4-a716-446655440007'),
-- ('a50e8400-e29b-41d4-a716-446655440023', '750e8400-e29b-41d4-a716-446655440033',
--  '850e8400-e29b-41d4-a716-446655440009'),
-- -- Bệnh viện Trần Minh Đăng 3 - Có đầy đủ các chuyên khoa
-- ('a50e8400-e29b-41d4-a716-446655440024', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440001'),
-- ('a50e8400-e29b-41d4-a716-446655440025', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440002'),
-- ('a50e8400-e29b-41d4-a716-446655440026', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440003'),
-- ('a50e8400-e29b-41d4-a716-446655440027', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440004'),
-- ('a50e8400-e29b-41d4-a716-446655440028', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440005'),
-- ('a50e8400-e29b-41d4-a716-446655440029', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440006'),
-- ('a50e8400-e29b-41d4-a716-446655440030', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440007'),
-- ('a50e8400-e29b-41d4-a716-446655440031', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440008'),
-- ('a50e8400-e29b-41d4-a716-446655440032', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440009'),
-- ('a50e8400-e29b-41d4-a716-446655440033', '750e8400-e29b-41d4-a716-446655440034',
--  '850e8400-e29b-41d4-a716-446655440010');
--
-- -- -- Insert Clinic Invitations (Lời mời từ phòng khám đến bác sĩ)
-- -- INSERT INTO clinic_invitations (id, clinic_specialty_id, doctor_id, status, invited_at, responded_at)
-- -- VALUES
-- -- -- Bệnh viện Chợ Rẫy mời BS. Trần Văn Nguyên - Nội tổng quát (đã chấp nhận)
-- -- ('b50e8400-e29b-41d4-a716-446655440001', 'a50e8400-e29b-41d4-a716-446655440001',
-- --  '750e8400-e29b-41d4-a716-446655440011', 'ACCEPTED', '2024-03-01 09:00:00', '2024-03-02 10:30:00'),
-- -- -- Bệnh viện Chợ Rẫy mời BS. Trần Văn Nguyên - Tim mạch (đã chấp nhận)
-- -- ('b50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440006',
-- --  '750e8400-e29b-41d4-a716-446655440011', 'ACCEPTED', '2024-03-01 09:15:00', '2024-03-02 10:30:00'),
-- -- -- Bệnh viện Chợ Rẫy mời BS. Lê Thị Mai - Sản Phụ khoa (đã chấp nhận)
-- -- ('b50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440002',
-- --  '750e8400-e29b-41d4-a716-446655440012', 'ACCEPTED', '2024-03-03 08:00:00', '2024-03-03 14:00:00'),
-- -- -- Phòng khám Hoàng Hoa mời BS. Phạm Văn Minh - Nhi khoa (đã chấp nhận)
-- -- ('b50e8400-e29b-41d4-a716-446655440004', 'a50e8400-e29b-41d4-a716-446655440013',
-- --  '750e8400-e29b-41d4-a716-446655440013', 'ACCEPTED', '2024-03-05 10:00:00', '2024-03-06 09:00:00'),
-- -- -- Phòng khám Hoàng Hoa mời BS. Nguyễn Thị Hạnh - Da liễu (đang chờ)
-- -- ('b50e8400-e29b-41d4-a716-446655440005', 'a50e8400-e29b-41d4-a716-446655440014',
-- --  '750e8400-e29b-41d4-a716-446655440014', 'PENDING', '2024-03-28 11:00:00', NULL),
-- -- -- Medical Center mời BS. Trần Văn Nguyên - Tim mạch (đang chờ)
-- -- ('b50e8400-e29b-41d4-a716-446655440006', 'a50e8400-e29b-41d4-a716-446655440021',
-- --  '750e8400-e29b-41d4-a716-446655440011', 'PENDING', '2024-03-27 15:00:00', NULL),
-- -- -- Bệnh viện Chợ Rẫy mời BS. Hoàng Văn Tuấn - Ngoại tổng hợp (bị từ chối)
-- -- ('b50e8400-e29b-41d4-a716-446655440007', 'a50e8400-e29b-41d4-a716-446655440005',
-- --  '750e8400-e29b-41d4-a716-446655440015', 'REJECTED', '2024-03-10 08:00:00', '2024-03-12 10:00:00'),
-- -- ('b50e8400-e29b-41d4-a716-446655440008', 'a50e8400-e29b-41d4-a716-446655440028',
-- --  '750e8400-e29b-41d4-a716-446655440016', 'PENDING', '2024-10-27 08:00:00', '2024-03-12 10:00:00');
--
-- -- Insert Clinic Specialty Doctors (Bác sĩ đã được phân công vào chuyên khoa của phòng khám)
-- INSERT INTO clinic_specialty_doctors (id, clinic_specialty_id, doctor_id)
-- VALUES
-- -- BS. Trần Văn Nguyên tại Bệnh viện Chợ Rẫy - Nội tổng quát
-- ('c50e8400-e29b-41d4-a716-446655440001', 'a50e8400-e29b-41d4-a716-446655440001',
--  '750e8400-e29b-41d4-a716-446655440011'),
-- -- BS. Trần Văn Nguyên tại Bệnh viện Chợ Rẫy - Tim mạch
-- ('c50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440006',
--  '750e8400-e29b-41d4-a716-446655440011'),
-- -- BS. Lê Thị Mai tại Bệnh viện Chợ Rẫy - Sản Phụ khoa
-- ('c50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440002',
--  '750e8400-e29b-41d4-a716-446655440012'),
-- -- BS. Lê Thị Mai tại Phòng khám Hoàng Hoa - Sản Phụ khoa
-- ('c50e8400-e29b-41d4-a716-446655440004', 'a50e8400-e29b-41d4-a716-446655440012',
--  '750e8400-e29b-41d4-a716-446655440012'),
-- -- BS. Phạm Văn Minh tại Phòng khám Hoàng Hoa - Nhi khoa
-- ('c50e8400-e29b-41d4-a716-446655440005', 'a50e8400-e29b-41d4-a716-446655440013',
--  '750e8400-e29b-41d4-a716-446655440013');
--
-- -- Note:
-- -- - Doctor user_ids match the ones from auth-service
-- -- - Clinic user_ids match the ones from auth-service
-- -- - InvitationStatus enum: PENDING, ACCEPTED, REJECTED
-- -- - assignment_count tracks how many times a doctor has been assigned to appointments

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
INSERT INTO doctors (
    id, user_id, full_name, bio, gender, address, avatar_url, is_in_clinic_specialty
)
VALUES
    ('750e8400-e29b-41d4-a716-446655440011', '650e8400-e29b-41d4-a716-446655440011',
     'BS. Trần Văn Nguyên',
     'Bác sĩ chuyên khoa Nội tổng quát với hơn 15 năm kinh nghiệm. Chuyên điều trị các bệnh về tim mạch, tiểu đường và cao huyết áp.',
     true, '78 Pasteur, Quận 1, TP.HCM',
     'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=200&h=200&fit=crop',
     false),

    ('750e8400-e29b-41d4-a716-446655440012', '650e8400-e29b-41d4-a716-446655440012',
     'BS. Lê Thị Mai',
     'Bác sĩ chuyên khoa Sản - Phụ khoa, tốt nghiệp Đại học Y Hà Nội. Có kinh nghiệm 10 năm trong lĩnh vực chăm sóc sức khỏe sinh sản và điều trị vô sinh.',
     false, '234 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM',
     'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=200&h=200&fit=crop',
     false),

    ('750e8400-e29b-41d4-a716-446655440013', '650e8400-e29b-41d4-a716-446655440013',
     'BS. Phạm Văn Minh',
     'Bác sĩ chuyên khoa Nhi với 12 năm kinh nghiệm. Chuyên điều trị các bệnh về hô hấp, tiêu hóa và phát triển ở trẻ em.',
     true, '567 Cách Mạng Tháng 8, Quận 10, TP.HCM',
     'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=200&h=200&fit=crop',
     false),

    ('750e8400-e29b-41d4-a716-446655440014', '650e8400-e29b-41d4-a716-446655440014',
     'BS. Nguyễn Thị Hạnh',
     'Bác sĩ chuyên khoa Da liễu, tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM. Chuyên điều trị mụn trứng cá, viêm da và các bệnh nấm da.',
     false, '89 Võ Văn Tần, Quận 3, TP.HCM',
     'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop',
     false),

    ('750e8400-e29b-41d4-a716-446655440015', '650e8400-e29b-41d4-a716-446655440015',
     'BS. Hoàng Văn Tuấn',
     'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
     true, '123 Trần Hưng Đạo, Quận 5, TP.HCM',
     'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop',
     false),

    ('750e8400-e29b-41d4-a716-446655440016', '650e8400-e29b-41d4-a716-446655440016',
     'BS. Trần Minh Đăng 2',
     'Bác sĩ chuyên khoa Ngoại tổng hợp với 20 năm kinh nghiệm. Chuyên phẫu thuật nội soi và các ca phẫu thuật về ổ bụng.',
     true, '123 Trần Hưng Đạo, Quận 5, TP.HCM',
     'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=200&h=200&fit=crop',
     false),
    ('750e8400-e29b-41d4-a716-446655440017', '650e8400-e29b-41d4-a716-446655440017', 'BS. Nguyễn Văn A',
    'Bác sĩ chuyên khoa Tim mạch can thiệp với hơn 18 năm kinh nghiệm. Tốt nghiệp loại Giỏi Đại học Y Dược TP.HCM, từng tu nghiệp tại Pháp về can thiệp mạch vành và đặt stent. Chuyên điều trị nhồi máu cơ tim, rối loạn nhịp tim và suy tim.', true, '180 Lê Lợi, Quận 1, TP.HCM',
    'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440018', '650e8400-e29b-41d4-a716-446655440018', 'BS. Trần Thị Ngọc Anh',
     'Bác sĩ chuyên khoa Sản phụ khoa, chuyên sâu về vô sinh – hiếm muộn và thai kỳ nguy cơ cao. Hơn 14 năm kinh nghiệm, thực hiện thành công hàng nghìn ca thụ tinh trong ống nghiệm (IVF) và hỗ trợ sinh sản. Từng công tác tại Bệnh viện Từ Dũ và Bệnh viện Hùng Vương.', false, '56 Nguyễn Thị Minh Khai, Q.3',
    'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440019', '650e8400-e29b-41d4-a716-446655440019', 'BS. Phạm Minh Đức',
     'Bác sĩ chuyên khoa Nhi với 15 năm kinh nghiệm, chuyên sâu về hô hấp trẻ em và chương trình tiêm chủng mở rộng. Nguyên Trưởng khoa Hô hấp – Bệnh viện Nhi Đồng 1, đã điều trị thành công hàng nghìn ca viêm phổi nặng và hen phế quản ở trẻ em.',
     true, '321 Lý Thường Kiệt, Q.10',
    'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440020', '650e8400-e29b-41d4-a716-446655440020', 'BS. Hoàng Thị Kim Liên',
     'Bác sĩ chuyên khoa Da liễu – Thẩm mỹ với hơn 12 năm kinh nghiệm. Chuyên trị mụn nội tiết, nám, sẹo rỗ và trẻ hóa da bằng laser Fractional CO2, HIFU, Thermage. Tốt nghiệp loại Xuất sắc Đại học Y Dược TP.HCM, tu nghiệp thẩm mỹ tại Hàn Quốc.',
     false, '89 Phạm Ngọc Thạch, Q.3',
    'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440021', '650e8400-e29b-41d4-a716-446655440021', 'BS. Lê Văn Dũng',
     'Bác sĩ chuyên khoa Ngoại Thần kinh, chuyên phẫu thuật cột sống và u não. Hơn 20 năm kinh nghiệm tại Bệnh viện Chợ Rẫy, thực hiện thành công hàng trăm ca vi phẫu u não, thoát vị đĩa đệm và cố định cột sống.',
     true, 'BV Chợ Rẫy, Q.5',
    'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440022', '650e8400-e29b-41d4-a716-446655440022', 'BS. Đặng Thị Lan',
     'Bác sĩ chuyên khoa Tiêu hóa – Gan mật với 16 năm kinh nghiệm. Chuyên nội soi không đau, điều trị viêm loét dạ dày, trào ngược, viêm gan virus và xơ gan. Nguyên Phó trưởng khoa Tiêu hóa Bệnh viện Đại học Y Dược TP.HCM.',
     false, '123 Võ Văn Tần, Q.3',
    'https://images.unsplash.com/photo-1559839734-2b71ea197ec2?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440023', '650e8400-e29b-41d4-a716-446655440023', 'BS. Vũ Thanh Hải',
     'Bác sĩ chuyên khoa Thần kinh, chuyên điều trị đột quỵ cấp và bệnh Parkinson. Hơn 18 năm kinh nghiệm tại Bệnh viện Nhân dân 115, là thành viên nhóm đáp ứng đột quỵ nhanh (Stroke Team) của bệnh viện.',
     true, 'BV Nhân dân 115',
    'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440024', '650e8400-e29b-41d4-a716-446655440024', 'BS. Bùi Thanh Đạt',
     'Bác sĩ chuyên khoa Chấn thương chỉnh hình – Thay khớp với hơn 22 năm kinh nghiệm. Chuyên thay khớp háng, khớp gối toàn phần và bán phần, phẫu thuật chỉnh hình cột sống. Từng tu nghiệp thay khớp tại Singapore và Thái Lan.',
     true, '256 Nguyễn Tri Phương, Q.5',
    'https://images.unsplash.com/photo-1612349317150-e413f6a5b16d?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440025', '650e8400-e29b-41d4-a716-446655440025', 'BS. Lý Thị Thùy Dung',
     'Bác sĩ chuyên khoa Nội tiết – Đái tháo đường và bệnh tuyến giáp. Hơn 15 năm kinh nghiệm, nguyên Trưởng khoa Nội tiết Bệnh viện Chợ Rẫy. Chuyên điều trị đái tháo đường type 1 & 2, biến chứng bàn chân đái tháo đường, Basedow và suy giáp.',
     false, '99 Trần Hưng Đạo, Q.1',
    'https://images.unsplash.com/photo-1594824476967-48c8b964273f?w=400&h=400&fit=crop&crop=face', false),

    ('750e8400-e29b-41d4-a716-446655440026', '650e8400-e29b-41d4-a716-446655440026', 'BS. Trần Văn Mạnh',
     'Bác sĩ chuyên khoa Tai Mũi Họng với 17 năm kinh nghiệm. Chuyên nội soi mũi xoang chức năng (FESS), phẫu thuật amidan, vá màng nhĩ và điều trị viêm mũi dị ứng, điếc đột ngột. Nguyên Phó khoa TMH Bệnh viện Tai Mũi Họng TP.HCM.',
     true, 'BV Tai Mũi Họng TP.HCM',
    'https://images.unsplash.com/photo-1622253692010-333f2da6031d?w=400&h=400&fit=crop&crop=face', false);

-- Insert Clinics (matching user_ids from auth-service)
INSERT INTO clinics (id, user_id, clinic_name, address, description, weekday_open_hour, weekday_close_hour, weekend_open_hour,  weekend_close_hour, logo_url, avatar_url)
VALUES ('750e8400-e29b-41d4-a716-446655440045', '650e8400-e29b-41d4-a716-446655440045', 'Bệnh viện Chợ Rẫy',
        '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        'Bệnh viện đa khoa hạng đặc biệt, là một trong những bệnh viện lớn nhất khu vực phía Nam. Cung cấp đầy đủ các dịch vụ y tế từ khám chữa bệnh đến phẫu thuật chuyên sâu.',
        '07:00', '21:00', '07:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1587351021759-3e566b6af7cc?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440046', '650e8400-e29b-41d4-a716-446655440046',
        'Phòng khám Đa khoa Hoàng Hoa', '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM',
        'Phòng khám đa khoa uy tín với đội ngũ bác sĩ giàu kinh nghiệm. Chuyên khám và điều trị nội khoa, ngoại khoa, sản phụ khoa và nhi khoa.',
        '08:00', '20:00', '08:00', '17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760282444/jwzn0b5e3amdapletedu.jpg', 'https://images.unsplash.com/photo-1538108149393-fbbd81895907?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440047', '650e8400-e29b-41d4-a716-446655440047',
        'Trung tâm Y tế Medical Center', '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
       ('750e8400-e29b-41d4-a716-446655440048', '650e8400-e29b-41d4-a716-446655440048', 'Trung tâm Y tế Trần Minh Đăng 3',
        '789 Nguyễn Thị Minh Khai, Quận 3, TP.HCM',
        'Trung tâm y tế hiện đại với trang thiết bị tiên tiến. Cung cấp dịch vụ khám chữa bệnh, xét nghiệm và chẩn đoán hình ảnh chất lượng cao.',
        '08:00', '18:00', '08:00', '18:00','https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440049', '650e8400-e29b-41d4-a716-446655440049', 'Phòng khám Đa khoa Ánh Dương', '180 Nguyễn Thị Minh Khai, Q.3', 'Phòng khám đa khoa hiện đại.', '07:30','20:00','08:00','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440050', '650e8400-e29b-41d4-a716-446655440050', 'Bệnh viện Việt Đức cơ sở 2', '268 Nguyễn Trãi, Q.5', 'Chuyên sâu Ngoại khoa, Tim mạch.', '00:00','23:59','00:00','23:59', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440051', '650e8400-e29b-41d4-a716-446655440051', 'Phòng khám Tâm Anh', '12 Nguyễn Văn Trỗi, Phú Nhuận', 'Chuyên Sản phụ khoa và Nhi khoa.', '07:00','21:00','07:30','18:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440052', '650e8400-e29b-41d4-a716-446655440052', 'Phòng khám Huệ Meditec', '456 Lê Văn Sỹ, Q.3', 'Chuyên Da liễu – Thẩm mỹ.', '08:00','20:00','08:30','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440053', '650e8400-e29b-41d4-a716-446655440053', 'Phòng khám Thiên Ân', '78 Bis Kỳ Đồng, Q.3', 'Đa khoa gia đình, khám BHYT.', '07:30','19:30','08:00','12:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440054', '650e8400-e29b-41d4-a716-446655440054', 'Bệnh viện Hồng Ngọc', '55 Yên Ninh (cơ sở phía Nam)', 'Dịch vụ y tế cao cấp.', '07:00','20:00','07:00','20:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440055', '650e8400-e29b-41d4-a716-446655440055', 'Phòng khám Family Medical', 'Vista Verde, Q.2', 'Phòng khám quốc tế.', '08:00','20:00','08:00','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440056', '650e8400-e29b-41d4-a716-446655440056', 'Vinmec Saigon', 'Vincom Center, Q.1', 'Hệ thống Vinmec chuẩn quốc tế.', '07:00','21:00','07:00','21:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440057', '650e8400-e29b-41d4-a716-446655440057', 'Phòng khám Hà Thanh', '280A Điện Biên Phủ, Q.3', 'Chuyên Da liễu & Thẩm mỹ.', '08:30','19:30','09:00','16:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop'),
        ('750e8400-e29b-41d4-a716-446655440058', '650e8400-e29b-41d4-a716-446655440058', 'Phòng khám Đa khoa Hạnh Phúc', '99 Trần Quốc Thảo, Q.3', 'Phòng khám gia đình thân thiện.', '07:30','20:30','08:00','17:00', 'https://res.cloudinary.com/dwkjsecri/image/upload/v1760283326/ggm2hmwvz358ww0ub7ek.jpg', 'https://images.unsplash.com/photo-1519494026892-80bbd2d6fd0d?w=400&h=250&fit=crop');

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
 '850e8400-e29b-41d4-a716-446655440005'),
('950e8400-e29b-41d4-a716-446655440009', '750e8400-e29b-41d4-a716-446655440016',
 '850e8400-e29b-41d4-a716-446655440001'),

('950e8400-e29b-41d4-a716-446655440010', '750e8400-e29b-41d4-a716-446655440017', '850e8400-e29b-41d4-a716-446655440006'),
('950e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440017', '850e8400-e29b-41d4-a716-446655440001'),

('950e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440018', '850e8400-e29b-41d4-a716-446655440002'),

('950e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440019', '850e8400-e29b-41d4-a716-446655440003'),
('950e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440019', '850e8400-e29b-41d4-a716-446655440008'),

('950e8400-e29b-41d4-a716-446655440015', '750e8400-e29b-41d4-a716-446655440020', '850e8400-e29b-41d4-a716-446655440004'),

('950e8400-e29b-41d4-a716-446655440016', '750e8400-e29b-41d4-a716-446655440021', '850e8400-e29b-41d4-a716-446655440009'),

('950e8400-e29b-41d4-a716-446655440017', '750e8400-e29b-41d4-a716-446655440022', '850e8400-e29b-41d4-a716-446655440007'),

('950e8400-e29b-41d4-a716-446655440018', '750e8400-e29b-41d4-a716-446655440023', '850e8400-e29b-41d4-a716-446655440009'),

('950e8400-e29b-41d4-a716-446655440019', '750e8400-e29b-41d4-a716-446655440024', '850e8400-e29b-41d4-a716-446655440010'),

('950e8400-e29b-41d4-a716-446655440020', '750e8400-e29b-41d4-a716-446655440025', '850e8400-e29b-41d4-a716-446655440001'),

('950e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440026', '850e8400-e29b-41d4-a716-446655440005');

-- Insert Clinic Specialties (Chuyên khoa mà phòng khám cung cấp)
INSERT INTO clinic_specialties (id, clinic_id, specialty_id)
VALUES
-- Bệnh viện Chợ Rẫy - Có đầy đủ các chuyên khoa
('a50e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440045',
 '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440045',
 '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440045',
 '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440045',
 '850e8400-e29b-41d4-a716-446655440004'),
('a50e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440045',
 '850e8400-e29b-41d4-a716-446655440005'),
('a50e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440045',
 '850e8400-e29b-41d4-a716-446655440006'),
-- Phòng khám Hoàng Hoa - Đa khoa cơ bản
('a50e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440046',
 '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440046',
 '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440046',
 '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440046',
 '850e8400-e29b-41d4-a716-446655440004'),
-- Medical Center - Chuyên khoa cao cấp
('a50e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440047',
 '850e8400-e29b-41d4-a716-446655440006'),
('a50e8400-e29b-41d4-a716-446655440022', '750e8400-e29b-41d4-a716-446655440047',
 '850e8400-e29b-41d4-a716-446655440007'),
('a50e8400-e29b-41d4-a716-446655440023', '750e8400-e29b-41d4-a716-446655440047',
 '850e8400-e29b-41d4-a716-446655440009'),
-- Bệnh viện Trần Minh Đăng 3 - Có đầy đủ các chuyên khoa
('a50e8400-e29b-41d4-a716-446655440024', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440025', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440026', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440027', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440004'),
('a50e8400-e29b-41d4-a716-446655440028', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440005'),
('a50e8400-e29b-41d4-a716-446655440029', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440006'),
('a50e8400-e29b-41d4-a716-446655440030', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440007'),
('a50e8400-e29b-41d4-a716-446655440031', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440008'),
('a50e8400-e29b-41d4-a716-446655440032', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440009'),
('a50e8400-e29b-41d4-a716-446655440033', '750e8400-e29b-41d4-a716-446655440048',
 '850e8400-e29b-41d4-a716-446655440010'),
-- Phòng khám Ánh Dương
('a50e8400-e29b-41d4-a716-446655440051', '750e8400-e29b-41d4-a716-446655440049', '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440052', '750e8400-e29b-41d4-a716-446655440049', '850e8400-e29b-41d4-a716-446655440006'),
('a50e8400-e29b-41d4-a716-446655440053', '750e8400-e29b-41d4-a716-446655440049', '850e8400-e29b-41d4-a716-446655440004'),

-- BV Việt Đức cơ sở 2
('a50e8400-e29b-41d4-a716-446655440054', '750e8400-e29b-41d4-a716-446655440050', '850e8400-e29b-41d4-a716-446655440005'),
('a50e8400-e29b-41d4-a716-446655440055', '750e8400-e29b-41d4-a716-446655440050', '850e8400-e29b-41d4-a716-446655440009'),
('a50e8400-e29b-41d4-a716-446655440056', '750e8400-e29b-41d4-a716-446655440050', '850e8400-e29b-41d4-a716-446655440010'),

-- Phòng khám Tâm Anh
('a50e8400-e29b-41d4-a716-446655440057', '750e8400-e29b-41d4-a716-446655440051', '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440058', '750e8400-e29b-41d4-a716-446655440051', '850e8400-e29b-41d4-a716-446655440003'),

-- Huệ Meditec
('a50e8400-e29b-41d4-a716-446655440059', '750e8400-e29b-41d4-a716-446655440052', '850e8400-e29b-41d4-a716-446655440004'),

-- Thiên Ân
('a50e8400-e29b-41d4-a716-446655440060', '750e8400-e29b-41d4-a716-446655440053', '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440061', '750e8400-e29b-41d4-a716-446655440053', '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440062', '750e8400-e29b-41d4-a716-446655440053', '850e8400-e29b-41d4-a716-446655440006'),

-- Hồng Ngọc
('a50e8400-e29b-41d4-a716-446655440063', '750e8400-e29b-41d4-a716-446655440054', '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440064', '750e8400-e29b-41d4-a716-446655440054', '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440065', '750e8400-e29b-41d4-a716-446655440054', '850e8400-e29b-41d4-a716-446655440004'),

-- Family Medical
('a50e8400-e29b-41d4-a716-446655440066', '750e8400-e29b-41d4-a716-446655440055', '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440067', '750e8400-e29b-41d4-a716-446655440055', '850e8400-e29b-41d4-a716-446655440003'),

-- Vinmec Saigon
('a50e8400-e29b-41d4-a716-446655440068', '750e8400-e29b-41d4-a716-446655440056', '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440069', '750e8400-e29b-41d4-a716-446655440056', '850e8400-e29b-41d4-a716-446655440002'),
('a50e8400-e29b-41d4-a716-446655440070', '750e8400-e29b-41d4-a716-446655440056', '850e8400-e29b-41d4-a716-446655440006'),
('a50e8400-e29b-41d4-a716-446655440071', '750e8400-e29b-41d4-a716-446655440056', '850e8400-e29b-41d4-a716-446655440004'),

-- Hà Thanh
('a50e8400-e29b-41d4-a716-446655440072', '750e8400-e29b-41d4-a716-446655440057', '850e8400-e29b-41d4-a716-446655440004'),

-- Hạnh Phúc
('a50e8400-e29b-41d4-a716-446655440073', '750e8400-e29b-41d4-a716-446655440058', '850e8400-e29b-41d4-a716-446655440001'),
('a50e8400-e29b-41d4-a716-446655440074', '750e8400-e29b-41d4-a716-446655440058', '850e8400-e29b-41d4-a716-446655440003'),
('a50e8400-e29b-41d4-a716-446655440075', '750e8400-e29b-41d4-a716-446655440058', '850e8400-e29b-41d4-a716-446655440007');

-- Insert Clinic Specialty Doctors (Bác sĩ đã được phân công vào chuyên khoa của phòng khám)
INSERT INTO clinic_specialty_doctors (id, clinic_specialty_id, doctor_id)
VALUES
-- BS. Trần Văn Nguyên tại Bệnh viện Chợ Rẫy - Nội tổng quát
('c50e8400-e29b-41d4-a716-446655440001', 'a50e8400-e29b-41d4-a716-446655440001',
 '750e8400-e29b-41d4-a716-446655440011'),
-- BS. Trần Văn Nguyên tại Bệnh viện Chợ Rẫy - Tim mạch
('c50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440006',
 '750e8400-e29b-41d4-a716-446655440011'),
-- BS. Lê Thị Mai tại Bệnh viện Chợ Rẫy - Sản Phụ khoa
('c50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440002',
 '750e8400-e29b-41d4-a716-446655440012'),
-- BS. Lê Thị Mai tại Phòng khám Hoàng Hoa - Sản Phụ khoa
('c50e8400-e29b-41d4-a716-446655440004', 'a50e8400-e29b-41d4-a716-446655440012',
 '750e8400-e29b-41d4-a716-446655440012'),
-- BS. Phạm Văn Minh tại Phòng khám Hoàng Hoa - Nhi khoa
('c50e8400-e29b-41d4-a716-446655440005', 'a50e8400-e29b-41d4-a716-446655440013',
 '750e8400-e29b-41d4-a716-446655440013'),
('c50e8400-e29b-41d4-a716-446655440101', 'a50e8400-e29b-41d4-a716-446655440052', '750e8400-e29b-41d4-a716-446655440017'),
('c50e8400-e29b-41d4-a716-446655440102', 'a50e8400-e29b-41d4-a716-446655440068', '750e8400-e29b-41d4-a716-446655440017'),

-- BS. Trần Thị Ngọc Anh vào Tâm Anh + Vinmec
('c50e8400-e29b-41d4-a716-446655440103', 'a50e8400-e29b-41d4-a716-446655440057', '750e8400-e29b-41d4-a716-446655440018'),
('c50e8400-e29b-41d4-a716-446655440104', 'a50e8400-e29b-41d4-a716-446655440069', '750e8400-e29b-41d4-a716-446655440018'),

-- BS. Phạm Minh Đức vào Tâm Anh + Hạnh Phúc (Nhi khoa)
('c50e8400-e29b-41d4-a716-446655440105', 'a50e8400-e29b-41d4-a716-446655440058', '750e8400-e29b-41d4-a716-446655440019'),
('c50e8400-e29b-41d4-a716-446655440106', 'a50e8400-e29b-41d4-a716-446655440074', '750e8400-e29b-41d4-a716-446655440019'),

-- BS. Hoàng Thị Kim Liên vào Huệ Meditec + Vinmec + Hồng Ngọc
('c50e8400-e29b-41d4-a716-446655440107', 'a50e8400-e29b-41d4-a716-446655440059', '750e8400-e29b-41d4-a716-446655440020'),
('c50e8400-e29b-41d4-a716-446655440108', 'a50e8400-e29b-41d4-a716-446655440071', '750e8400-e29b-41d4-a716-446655440020'),
('c50e8400-e29b-41d4-a716-446655440109', 'a50e8400-e29b-41d4-a716-446655440065', '750e8400-e29b-41d4-a716-446655440020'),

-- BS. Lê Văn Dũng vào BV Việt Đức (Thần kinh)
('c50e8400-e29b-41d4-a716-446655440110', 'a50e8400-e29b-41d4-a716-446655440055', '750e8400-e29b-41d4-a716-446655440021'),

-- BS. Bùi Thanh Đạt vào BV Việt Đức (Chỉnh hình)
('c50e8400-e29b-41d4-a716-446655440111', 'a50e8400-e29b-41d4-a716-446655440056', '750e8400-e29b-41d4-a716-446655440024'),

-- Một vài bác sĩ đang chờ mời (PENDING) để test UI
('c50e8400-e29b-41d4-a716-446655440112', 'a50e8400-e29b-41d4-a716-446655440073', '750e8400-e29b-41d4-a716-446655440022'),
('c50e8400-e29b-41d4-a716-446655440113', 'a50e8400-e29b-41d4-a716-446655440072', '750e8400-e29b-41d4-a716-446655440020');

-- Note:
-- - Doctor user_ids match the ones from auth-service
-- - Clinic user_ids match the ones from auth-service
-- - InvitationStatus enum: PENDING, ACCEPTED, REJECTED
-- - assignment_count tracks how many times a doctor has been assigned to appointments