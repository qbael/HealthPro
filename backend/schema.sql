DROP TABLE IF EXISTS clinic_specialty_schedule_templates CASCADE;
DROP TABLE IF EXISTS doctor_schedule_templates CASCADE;
DROP TABLE IF EXISTS appointments CASCADE;
DROP TABLE IF EXISTS doctor_available_slots CASCADE;
DROP TABLE IF EXISTS clinic_specialty_doctors CASCADE;
DROP TABLE IF EXISTS clinic_specialties CASCADE;
DROP TABLE IF EXISTS doctor_specialties CASCADE;
DROP TABLE IF EXISTS specialties CASCADE;
DROP TABLE IF EXISTS doctors CASCADE;
DROP TABLE IF EXISTS clinics CASCADE;
DROP TABLE IF EXISTS patients CASCADE;
DROP TABLE IF EXISTS admins CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;

CREATE TYPE appointment_type AS ENUM ('clinic', 'doctor');
CREATE TYPE appointment_status AS ENUM ('scheduled', 'completed', 'cancelled', 'no_show');
CREATE TYPE user_role AS ENUM ('admin', 'doctor', 'patient', 'clinic');
CREATE TYPE invitation_status AS ENUM ('pending', 'accepted', 'rejected', 'cancelled');
CREATE TYPE day_of_week AS ENUM ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday');

CREATE TABLE roles
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    role_name VARCHAR(20)
);

CREATE TABLE users
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email        VARCHAR(255) UNIQUE NOT NULL,
    password     VARCHAR(255)        NOT NULL,
    phone_number VARCHAR(10),
    role_id      UUID                NOT NULL REFERENCES roles (id),
    is_active    BOOLEAN          DEFAULT true,
    created_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE admins
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id   UUID UNIQUE  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    full_name VARCHAR(255) NOT NULL,
    gender    BOOLEAN,
    address   VARCHAR(255)
);

CREATE TABLE patients
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id       UUID UNIQUE  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    full_name     VARCHAR(255) NOT NULL,
    date_of_birth DATE,
    gender        BOOLEAN,
    medical_notes TEXT
);

CREATE TABLE clinics
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id     UUID UNIQUE  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    clinic_name VARCHAR(255) NOT NULL,
    address     VARCHAR(255),
    description TEXT,
    open_hour   VARCHAR(5)       DEFAULT '08:00',
    close_hour  VARCHAR(5)       DEFAULT '17:00',
    logo_url    VARCHAR(255)
);

CREATE TABLE doctors
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id    UUID UNIQUE  NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    full_name  VARCHAR(255) NOT NULL,
    bio        TEXT,
    gender     boolean,
    address    VARCHAR(200),
    avatar_url VARCHAR(255)
);

CREATE TABLE specialties
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE doctor_specialties
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    doctor_id    UUID NOT NULL REFERENCES doctors (id) ON DELETE CASCADE,
    specialty_id UUID NOT NULL REFERENCES specialties (id) ON DELETE CASCADE,
    UNIQUE (doctor_id, specialty_id)
);

CREATE TABLE clinic_specialties
(
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    clinic_id    UUID NOT NULL REFERENCES clinics (id) ON DELETE CASCADE,
    specialty_id UUID NOT NULL REFERENCES specialties (id) ON DELETE CASCADE,
    UNIQUE (clinic_id, specialty_id)
);

CREATE TABLE clinic_invitations
(
    id                  UUID PRIMARY KEY  DEFAULT gen_random_uuid(),
    clinic_specialty_id UUID REFERENCES clinic_specialties (id) ON DELETE CASCADE NOT NULL,
    doctor_id           UUID REFERENCES doctors (id) ON DELETE CASCADE            NOT NULL,
    status              invitation_status DEFAULT 'pending' CHECK (status IN ('pending', 'accepted', 'rejected', 'cancelled')),
    invited_at          TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
    responded_at        TIMESTAMP,
    title               TEXT,
    UNIQUE (clinic_specialty_id, doctor_id)
);

CREATE TABLE clinic_specialty_doctors
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    clinic_specialty_id UUID NOT NULL REFERENCES clinic_specialties (id) ON DELETE CASCADE,
    doctor_id           UUID NOT NULL REFERENCES doctors (id) ON DELETE CASCADE,
    assignment_count    INTEGER          DEFAULT 0,
    UNIQUE (clinic_specialty_id, doctor_id)
);

CREATE TABLE doctor_available_slots
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    doctor_id           UUID NOT NULL REFERENCES doctors (id) ON DELETE CASCADE,
    clinic_specialty_id UUID NOT NULL REFERENCES clinic_specialties (id) ON DELETE CASCADE,
    appointment_date    DATE NOT NULL,
    start_time          TIME NOT NULL,
    end_time            TIME NOT NULL,
    appointment_type    appointment_type
);

CREATE TABLE appointments
(
    id                  UUID PRIMARY KEY   DEFAULT gen_random_uuid(),
    patient_id          UUID             NOT NULL REFERENCES patients (id) ON DELETE CASCADE,
    doctor_id           UUID             NOT NULL REFERENCES doctors (id) ON DELETE CASCADE,
    clinic_specialty_id UUID REFERENCES clinic_specialties (id) ON DELETE CASCADE,
    appointment_type    appointment_type NOT NULL,
    appointment_date    DATE             NOT NULL,
    start_time          TIME             NOT NULL,
    end_time            TIME             NOT NULL,
    status              appointment_status DEFAULT 'scheduled',
    notes               TEXT,
    created_at          TIMESTAMP          DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE doctor_schedule_templates
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    doctor_id     UUID        NOT NULL REFERENCES doctors (id) ON DELETE CASCADE,
    day_of_week   day_of_week NOT NULL,
    from_time     TIME        NOT NULL,
    to_time       TIME        NOT NULL,
    slot_duration INTEGER     NOT NULL,
    is_active     BOOLEAN          DEFAULT true,
    updated_at    TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE clinic_specialty_schedule_templates
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    clinic_specialty_id UUID        NOT NULL REFERENCES clinic_specialties (id) ON DELETE CASCADE,
    day_of_week         day_of_week NOT NULL,
    from_time           TIME        NOT NULL,
    to_time             TIME        NOT NULL,
    slot_duration       INTEGER     NOT NULL,
    is_active           BOOLEAN          DEFAULT true,
    updated_at          TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

CREATE
    OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at
        = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$
    language 'plpgsql';

CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE
    ON users
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_doctor_schedule_updated_at
    BEFORE UPDATE
    ON doctor_schedule_templates
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_clinic_schedule_updated_at
    BEFORE UPDATE
    ON clinic_specialty_schedule_templates
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

COMMENT
    ON TABLE users IS 'Base authentication table for all user types';
COMMENT
    ON TABLE doctors IS 'Doctor profiles and information';
COMMENT
    ON TABLE patients IS 'Patient profiles and medical history';
COMMENT
    ON TABLE clinics IS 'Medical clinic information';
COMMENT
    ON TABLE appointments IS 'Patient appointment bookings';
COMMENT
    ON TABLE doctor_available_slots IS 'Doctor availability schedule';
COMMENT
    ON TABLE doctor_schedule_templates IS 'Weekly recurring schedule templates for doctors';
COMMENT
    ON TABLE clinic_specialty_schedule_templates IS 'Weekly recurring schedule templates for clinic specialties';
COMMENT
    ON COLUMN appointments.appointment_type IS 'Type of appointment: online, offline, or emergency';
COMMENT
    ON COLUMN appointments.status IS 'Current status of the appointment';
COMMENT
    ON COLUMN doctor_available_slots.appointment_type IS 'Type of time slot: regular, emergency, or break';

SELECT 'Database schema created successfully!' as message;