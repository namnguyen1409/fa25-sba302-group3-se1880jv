-- =========================
-- ROLE & PERMISSION
-- =========================
INSERT INTO user_management.role (id, name, description, deleted, version, created_date)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'ROLE_ADMIN', 'System administrator', false, 1, NOW()),
    ('22222222-2222-2222-2222-222222222222', 'ROLE_STAFF', 'Clinic staff', false, 1, NOW()),
    ('33333333-3333-3333-3333-333333333333', 'ROLE_USER', 'Registered user', false, 1, NOW());

INSERT INTO user_management.permission (id, name, description, deleted, version, created_date)
VALUES
    ('44444444-4444-4444-4444-444444444444', 'USER_MANAGE', 'Manage users', false, 1, NOW()),
    ('55555555-5555-5555-5555-555555555555', 'ROLE_MANAGE', 'Manage roles', false, 1, NOW()),
    ('66666666-6666-6666-6666-666666666666', 'VIEW_REPORTS', 'View reports', false, 1, NOW());

INSERT INTO role_permissions (role_id, permissions_id)
VALUES
    ('11111111-1111-1111-1111-111111111111', '44444444-4444-4444-4444-444444444444'),
    ('11111111-1111-1111-1111-111111111111', '55555555-5555-5555-5555-555555555555'),
    ('11111111-1111-1111-1111-111111111111', '66666666-6666-6666-6666-666666666666'),
    ('22222222-2222-2222-2222-222222222222', '66666666-6666-6666-6666-666666666666');

-- =========================
-- USER
-- =========================
INSERT INTO user_management.users (id, username, email, password, active, locked, first_login, mfa_enabled, deleted, version, created_date)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'admin', 'admin@example.com', '$2a$10$Hq3HcI6uYzmpq4vQ6yZlzuZTtfFgxkaE5ED9vCJr8FGMI83SuUBbC', true, false, false, false, false, 1, NOW()), -- password = admin123
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'staff', 'staff@example.com', '$2a$10$Hq3HcI6uYzmpq4vQ6yZlzuZTtfFgxkaE5ED9vCJr8FGMI83SuUBbC', true, false, false, false, false, 1, NOW()),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'user1', 'user1@example.com', '$2a$10$Hq3HcI6uYzmpq4vQ6yZlzuZTtfFgxkaE5ED9vCJr8FGMI83SuUBbC', true, false, true, false, false, 1, NOW());

-- password hash là bcrypt("admin123")

INSERT INTO users_roles (user_id, roles_id)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', '33333333-3333-3333-3333-333333333333');

-- =========================
-- USER PROFILE + ADDRESS
-- =========================
INSERT INTO common.administrative_unit (id, code, name, level, deleted, version, created_date)
VALUES
    ('ad000001-0000-0000-0000-000000000001', '01', 'Hà Nội', 'PROVINCE', false, 1, NOW());

INSERT INTO common.address (id, city_name, district_name, ward_name, street, deleted, version, created_date)
VALUES
    ('addd0000-0000-0000-0000-000000000001', 'Hà Nội', 'Cầu Giấy', 'Dịch Vọng', '268 Cầu Giấy', false, 1, NOW());

INSERT INTO user_profile (id, user_id, full_name, date_of_birth, address_id, deleted, version, created_date)
VALUES
    ('faaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Admin User', '1990-01-01', 'addd0000-0000-0000-0000-000000000001', false, 1, NOW()),
    ('fbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Staff Member', '1995-02-02', NULL, false, 1, NOW()),
    ('fccccccc-cccc-cccc-cccc-cccccccccccc', 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'Test User', '2000-03-03', NULL, false, 1, NOW());

-- =========================
-- MFA CONFIG (sample)
-- =========================
INSERT INTO authentication.mfa_config (id, user_id, mfa_type, contact, secret, is_primary, revoked, public_key, deleted, version, created_date)
VALUES
    ('aaa11111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'EMAIL', 'admin@example.com', NULL, true, false, lo_from_bytea(0, decode('DEADBEEF','hex')), false, 1, NOW()),
    ('aaa22222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'TOTP', 'staff@example.com', 'JBSWY3DPEHPK3PXP', true, false, lo_from_bytea(0, decode('DEADBEEF','hex')), false, 1, NOW());

-- =========================
-- DEVICE SESSION
-- =========================
INSERT INTO user_management.device_session (id, user_id, device_id, device_name, ip_address, user_agent, trusted, remember_me, revoked, deleted, version, created_date)
VALUES
    ('d1111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'DEVICE-ADMIN-PC', 'Admin PC', '127.0.0.1', 'Mozilla/5.0', true, true, false, false, 1, NOW());

-- =========================
-- LOGIN ATTEMPT
-- =========================
INSERT INTO authentication.login_attempt (id, user_id, status, ip_address, user_agent, deleted, version, created_date)
VALUES
    ('bb111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'SUCCESS', '127.0.0.1', 'Mozilla/5.0', false, 1, NOW()),
    ('bb222222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'FAILURE', '127.0.0.1', 'Mozilla/5.0', false, 1, NOW());

-- =========================
-- OAUTH ACCOUNT SAMPLE
-- =========================
INSERT INTO authentication.oauth_account (id, user_id, provider, provider_user_id, name, email, avatar_url, deleted, version, created_date)
VALUES
    ('cc111111-1111-1111-1111-111111111111', 'cccccccc-cccc-cccc-cccc-cccccccccccc', 'GOOGLE', 'google-12345', 'Test User', 'user1@example.com', 'https://picsum.photos/100', false, 1, NOW());
