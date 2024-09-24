INSERT INTO profile ( name, surname, email,password,created_date, status, visible, role)
VALUES ('Trener', 'Trenerov', 'trener@gmail.com','JRSIpuUOBrlNMJrx2URi',CURRENT_TIMESTAMP, 'ACTIVE', true,
        'ROLE_TRENER') ON CONFLICT (id) DO NOTHING;
SELECT setval('profile_id_seq', max(id))
FROM profile;