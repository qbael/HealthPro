-- Insert Doctor Schedule Templates (Lịch làm việc mẫu của bác sĩ theo tuần)
-- BS. Trần Văn Nguyên - Làm việc Thứ 2, 4, 6
INSERT INTO doctor_schedule_templates (id, doctor_id, day_of_week, from_time, to_time, slot_duration, is_active,
                                       updated_at)
VALUES ('d50e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440011', 'MONDAY', '08:00:00',
        '17:00:00', 30, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440011', 'WEDNESDAY', '08:00:00',
        '17:00:00', 30, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440011', 'FRIDAY', '08:00:00',
        '17:00:00', 30, true, '2024-03-01 10:00:00');

-- BS. Lê Thị Mai - Làm việc Thứ 2, 3, 5
INSERT INTO doctor_schedule_templates (id, doctor_id, day_of_week, from_time, to_time, slot_duration, is_active,
                                       updated_at)
VALUES ('d50e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440012', 'MONDAY', '09:00:00',
        '16:00:00', 45, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440012', 'TUESDAY', '09:00:00',
        '16:00:00', 45, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440012', 'THURSDAY', '09:00:00',
        '16:00:00', 45, true, '2024-03-01 10:00:00');

-- BS. Phạm Văn Minh - Làm việc Thứ 3, 4, 6, 7
INSERT INTO doctor_schedule_templates (id, doctor_id, day_of_week, from_time, to_time, slot_duration, is_active,
                                       updated_at)
VALUES ('d50e8400-e29b-41d4-a716-446655440007', '750e8400-e29b-41d4-a716-446655440013', 'TUESDAY', '08:00:00',
        '12:00:00', 20, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440008', '750e8400-e29b-41d4-a716-446655440013', 'WEDNESDAY', '13:00:00',
        '17:00:00', 20, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440009', '750e8400-e29b-41d4-a716-446655440013', 'FRIDAY', '08:00:00',
        '17:00:00', 20, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440010', '750e8400-e29b-41d4-a716-446655440013', 'SATURDAY', '08:00:00',
        '12:00:00', 20, true, '2024-03-01 10:00:00');

-- BS. Nguyễn Thị Hạnh - Làm việc Thứ 2, 4, 5, 7
INSERT INTO doctor_schedule_templates (id, doctor_id, day_of_week, from_time, to_time, slot_duration, is_active,
                                       updated_at)
VALUES ('d50e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440014', 'MONDAY', '13:00:00',
        '18:00:00', 30, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440014', 'WEDNESDAY', '09:00:00',
        '18:00:00', 30, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440014', 'THURSDAY', '09:00:00',
        '18:00:00', 30, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440014', 'SATURDAY', '09:00:00',
        '15:00:00', 30, true, '2024-03-01 10:00:00');

-- BS. Hoàng Văn Tuấn - Làm việc Thứ 3, 5, 6
INSERT INTO doctor_schedule_templates (id, doctor_id, day_of_week, from_time, to_time, slot_duration, is_active,
                                       updated_at)
VALUES ('d50e8400-e29b-41d4-a716-446655440015', '750e8400-e29b-41d4-a716-446655440015', 'TUESDAY', '08:00:00',
        '16:00:00', 60, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440016', '750e8400-e29b-41d4-a716-446655440015', 'THURSDAY', '08:00:00',
        '16:00:00', 60, true, '2024-03-01 10:00:00'),
       ('d50e8400-e29b-41d4-a716-446655440017', '750e8400-e29b-41d4-a716-446655440015', 'FRIDAY', '08:00:00',
        '16:00:00', 60, true, '2024-03-01 10:00:00');

-- Insert Clinic Specialty Schedule Templates (Lịch làm việc của chuyên khoa tại phòng khám)
-- Bệnh viện Chợ Rẫy - Nội tổng quát
INSERT INTO clinic_specialty_schedule_templates (id, clinic_specialty_id, day_of_week, from_time, to_time,
                                                 slot_duration, is_active, updated_at)
VALUES ('e50e8400-e29b-41d4-a716-446655440001', 'a50e8400-e29b-41d4-a716-446655440001', 'MONDAY', '07:00:00',
        '17:00:00', 30, true, '2024-03-01 08:00:00'),
       ('e50e8400-e29b-41d4-a716-446655440002', 'a50e8400-e29b-41d4-a716-446655440001', 'WEDNESDAY', '07:00:00',
        '17:00:00', 30, true, '2024-03-01 08:00:00'),
       ('e50e8400-e29b-41d4-a716-446655440003', 'a50e8400-e29b-41d4-a716-446655440001', 'FRIDAY', '07:00:00',
        '17:00:00', 30, true, '2024-03-01 08:00:00');

-- Bệnh viện Chợ Rẫy - Sản Phụ khoa
INSERT INTO clinic_specialty_schedule_templates (id, clinic_specialty_id, day_of_week, from_time, to_time,
                                                 slot_duration, is_active, updated_at)
VALUES ('e50e8400-e29b-41d4-a716-446655440004', 'a50e8400-e29b-41d4-a716-446655440002', 'MONDAY', '08:00:00',
        '16:00:00', 45, true, '2024-03-01 08:00:00'),
       ('e50e8400-e29b-41d4-a716-446655440005', 'a50e8400-e29b-41d4-a716-446655440002', 'TUESDAY', '08:00:00',
        '16:00:00', 45, true, '2024-03-01 08:00:00'),
       ('e50e8400-e29b-41d4-a716-446655440006', 'a50e8400-e29b-41d4-a716-446655440002', 'THURSDAY', '08:00:00',
        '16:00:00', 45, true, '2024-03-01 08:00:00');

-- Phòng khám Hoàng Hoa - Nhi khoa
INSERT INTO clinic_specialty_schedule_templates (id, clinic_specialty_id, day_of_week, from_time, to_time,
                                                 slot_duration, is_active, updated_at)
VALUES ('e50e8400-e29b-41d4-a716-446655440007', 'a50e8400-e29b-41d4-a716-446655440013', 'TUESDAY', '08:00:00',
        '17:00:00', 20, true, '2024-03-01 08:00:00'),
       ('e50e8400-e29b-41d4-a716-446655440008', 'a50e8400-e29b-41d4-a716-446655440013', 'FRIDAY', '08:00:00',
        '17:00:00', 20, true, '2024-03-01 08:00:00'),
       ('e50e8400-e29b-41d4-a716-446655440009', 'a50e8400-e29b-41d4-a716-446655440013', 'SATURDAY', '08:00:00',
        '12:00:00', 20, true, '2024-03-01 08:00:00');

-- Insert Doctor Available Slots (Các slot khám bệnh có sẵn của bác sĩ - future dates)
-- BS. Trần Văn Nguyên - Các slot trong tuần tới
INSERT INTO doctor_available_slots (id, doctor_id, clinic_specialty_id, appointment_date, start_time, end_time,
                                    appointment_type)
VALUES
-- 2025-10-13 (Monday)
('f50e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-13', '08:00:00', '08:30:00', 'CLINIC'),
('f50e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-13', '08:30:00', '09:00:00', 'CLINIC'),
('f50e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-13', '09:00:00', '09:30:00', 'CLINIC'),
('f50e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-13', '14:00:00', '14:30:00', 'CLINIC'),
('f50e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-13', '14:30:00', '15:00:00', 'CLINIC'),
-- 2025-10-15 (Wednesday)
('f50e8400-e29b-41d4-a716-446655440006', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-15', '08:00:00', '08:30:00', 'CLINIC'),
('f50e8400-e29b-41d4-a716-446655440007', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-15', '10:00:00', '10:30:00', 'CLINIC'),
('f50e8400-e29b-41d4-a716-446655440008', '750e8400-e29b-41d4-a716-446655440011',
 'a50e8400-e29b-41d4-a716-446655440001', '2025-10-15', '15:00:00', '15:30:00', 'CLINIC');

-- BS. Lê Thị Mai - Các slot trong tuần tới
INSERT INTO doctor_available_slots (id, doctor_id, clinic_specialty_id, appointment_date, start_time, end_time,
                                    appointment_type)
VALUES ('f50e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440012',
        'a50e8400-e29b-41d4-a716-446655440002', '2025-10-13', '09:00:00', '09:45:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440012',
        'a50e8400-e29b-41d4-a716-446655440002', '2025-10-13', '10:00:00', '10:45:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440012',
        'a50e8400-e29b-41d4-a716-446655440002', '2025-10-14', '09:00:00', '09:45:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440012',
        'a50e8400-e29b-41d4-a716-446655440002', '2025-10-14', '14:00:00', '14:45:00', 'CLINIC');

-- BS. Phạm Văn Minh - Các slot trong tuần tới
INSERT INTO doctor_available_slots (id, doctor_id, clinic_specialty_id, appointment_date, start_time, end_time,
                                    appointment_type)
VALUES ('f50e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440013',
        'a50e8400-e29b-41d4-a716-446655440013', '2025-10-14', '08:00:00', '08:20:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440022', '750e8400-e29b-41d4-a716-446655440013',
        'a50e8400-e29b-41d4-a716-446655440013', '2025-10-14', '08:20:00', '08:40:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440023', '750e8400-e29b-41d4-a716-446655440013',
        'a50e8400-e29b-41d4-a716-446655440013', '2025-10-17', '08:00:00', '08:20:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440024', '750e8400-e29b-41d4-a716-446655440013',
        'a50e8400-e29b-41d4-a716-446655440013', '2025-10-17', '09:00:00', '09:20:00', 'CLINIC'),
       ('f50e8400-e29b-41d4-a716-446655440025', '750e8400-e29b-41d4-a716-446655440013',
        'a50e8400-e29b-41d4-a716-446655440013', '2025-10-18', '08:00:00', '08:20:00', 'CLINIC');

-- Insert Appointments (Các cuộc hẹn đã được tạo)
-- Cuộc hẹn đã hoàn thành
INSERT INTO appointments (id, patient_id, patient_name, patient_email, patient_phone, doctor_id, doctor_name, clinic_id,
                          clinic_name, address, clinic_phone, clinic_specialty_id, specialty_name, appointment_type,
                          appointment_date, start_time, end_time, status, notes, created_at, updated_at)
VALUES ('0c4b682a-db02-4bc5-817e-eaee5cdcc8e8', '750e8400-e29b-41d4-a716-446655440021', 'Nguyễn Văn An',
        'nguyen.vanan@gmail.com', '0923456789', '750e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
        '750e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy', '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        '0934567890', 'a50e8400-e29b-41d4-a716-446655440001', 'Nội tổng quát', 'CLINIC', '2024-09-15', '09:00:00',
        '09:30:00', 'COMPLETED', 'Tái khám kiểm tra huyết áp', '2024-09-10 14:00:00', '2024-09-15 10:00:00'),
       ('e8bc50dc-4509-4691-b8d3-4792f07d0133', '750e8400-e29b-41d4-a716-446655440022', 'Trần Thị Hồng',
        'tran.thihong@gmail.com', '0923456790', '750e8400-e29b-41d4-a716-446655440012', 'BS. Lê Thị Mai',
        '750e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy', '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        '0934567890', 'a50e8400-e29b-41d4-a716-446655440002', 'Sản - Phụ khoa', 'CLINIC', '2024-09-20',
        '10:00:00', '10:45:00', 'COMPLETED', 'Khám thai định kỳ 3 tháng', '2024-09-15 10:00:00',
        '2024-09-20 11:00:00');

-- Cuộc hẹn đã xác nhận (sắp tới)
INSERT INTO appointments (id, patient_id, patient_name, patient_email, patient_phone, doctor_id, doctor_name, clinic_id,
                          clinic_name, address, clinic_phone, clinic_specialty_id, specialty_name, appointment_type,
                          appointment_date, start_time, end_time, status, notes, created_at, updated_at)
VALUES ('6354270b-0590-456c-ad65-6aeafa682a50', '750e8400-e29b-41d4-a716-446655440023', 'Lê Văn Bình',
        'le.vanbinh@gmail.com', '0923456791', '750e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
        '750e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy', '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        '0934567890', 'a50e8400-e29b-41d4-a716-446655440001', 'Nội tổng quát', 'CLINIC', '2025-10-13', '08:30:00',
        '09:00:00', 'SCHEDULED', 'Khám kiểm tra đường huyết', '2025-10-05 09:00:00', '2025-10-05 09:00:00'),
       ('033bfaf8-eed7-4761-b68a-34fe6733585e', '750e8400-e29b-41d4-a716-446655440024', 'Phạm Thị Lan',
        'pham.thilan@gmail.com', '0923456792', '750e8400-e29b-41d4-a716-446655440013', 'BS. Phạm Văn Minh',
        '750e8400-e29b-41d4-a716-446655440032', 'Phòng khám Đa khoa Hoàng Hoa',
        '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM', '0934567891', 'a50e8400-e29b-41d4-a716-446655440013', 'Nhi khoa',
        'CLINIC', '2025-10-14', '08:20:00', '08:40:00', 'SCHEDULED', 'Khám sức khỏe định kỳ cho bé',
        '2025-10-06 10:30:00', '2025-10-06 10:30:00'),
       ('7303a9f5-efa3-46ff-bf82-f79221c10004', '750e8400-e29b-41d4-a716-446655440025', 'Hoàng Văn Nam',
        'hoang.vannam@gmail.com', '0923456793', '750e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên',
        '750e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy', '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        '0934567890', 'a50e8400-e29b-41d4-a716-446655440001', 'Nội tổng quát', 'CLINIC', '2025-10-15', '10:00:00',
        '10:30:00', 'SCHEDULED', 'Tái khám gan', '2025-10-06 14:00:00', '2025-10-06 14:00:00');

-- Cuộc hẹn đang chờ xác nhận
INSERT INTO appointments (id, patient_id, patient_name, patient_email, patient_phone, doctor_id, doctor_name, clinic_id,
                          clinic_name, address, clinic_phone, clinic_specialty_id, specialty_name, appointment_type,
                          appointment_date, start_time, end_time, status, notes, created_at, updated_at)
VALUES ('ba8b85ca-a2fd-45b7-a193-cee1187f1fd6', '750e8400-e29b-41d4-a716-446655440026', 'Đỗ Thị Phượng',
        'do.thiphuong@gmail.com', '0923456794', '750e8400-e29b-41d4-a716-446655440012', 'BS. Lê Thị Mai',
        '750e8400-e29b-41d4-a716-446655440031', 'Bệnh viện Chợ Rẫy', '201B Nguyễn Chí Thanh, Quận 5, TP.HCM',
        '0934567890', 'a50e8400-e29b-41d4-a716-446655440002', 'Sản - Phụ khoa', 'CLINIC', '2025-10-14',
        '14:00:00', '14:45:00', 'SCHEDULED', 'Khám tư vấn trước khi mang thai', '2025-10-06 16:00:00',
        '2025-10-06 16:00:00');

-- Cuộc hẹn đã hủy
INSERT INTO appointments (id, patient_id, patient_name, patient_email, patient_phone, doctor_id, doctor_name, clinic_id,
                          clinic_name, address, clinic_phone, clinic_specialty_id, specialty_name, appointment_type,
                          appointment_date, start_time, end_time, status, notes, created_at, updated_at)
VALUES ('a549d2b8-2634-41b9-b0cb-e63b8fe4a3a6', '750e8400-e29b-41d4-a716-446655440027', 'Vũ Văn Hùng',
        'vu.vanhung@gmail.com', '0923456795', '750e8400-e29b-41d4-a716-446655440013', 'BS. Phạm Văn Minh',
        '750e8400-e29b-41d4-a716-446655440032', 'Phòng khám Đa khoa Hoàng Hoa',
        '345 Hoàng Văn Thụ, Quận Tân Bình, TP.HCM', '0934567891', 'a50e8400-e29b-41d4-a716-446655440013', 'Nhi khoa',
        'CLINIC', '2025-10-17', '09:00:00', '09:20:00', 'CANCELLED', 'Bệnh nhân hủy do bận đột xuất',
        '2025-10-05 11:00:00', '2025-10-06 08:00:00');

-- Cuộc hẹn online (DOCTOR)
INSERT INTO appointments (id, patient_id, patient_name, patient_email, patient_phone, doctor_id, doctor_name, clinic_id,
                          clinic_name, address, clinic_phone, clinic_specialty_id, specialty_name, appointment_type,
                          appointment_date, start_time, end_time, status, notes, created_at, updated_at)
VALUES ('a2530d5b-0502-452c-88a8-fd02d2116868', '750e8400-e29b-41d4-a716-446655440021', 'Nguyễn Văn An',
        'nguyen.vanan@gmail.com', '0923456789', '750e8400-e29b-41d4-a716-446655440011', 'BS. Trần Văn Nguyên', NULL,
        'Tư vấn trực tuyến', 'Tư vấn qua video call', '0934567890', NULL, 'Nội tổng quát', 'DOCTOR',
        '2025-10-18', '19:00:00', '19:30:00', 'SCHEDULED', 'Tư vấn kết quả xét nghiệm online', '2025-10-06 13:00:00',
        '2025-10-06 13:00:00');

-- Note:
-- - AppointmentType: CLINIC, DOCTOR
-- - AppointmentStatus: SCHEDULED, SCHEDULED, COMPLETED, CANCELLED, NO_SHOW
-- - Dates are set to future dates (October 2025) for upcoming appointments
-- - slot_duration is in minutes (20, 30, 45, 60 minutes)
-- - Times use 24-hour format