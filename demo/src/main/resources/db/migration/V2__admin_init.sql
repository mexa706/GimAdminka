INSERT INTO profile ( name, surname, email,password,created_date, status, visible, role)
VALUES ('Adminjon', 'Adminov', 'admin5@gmail.com','123123123123',CURRENT_TIMESTAMP, 'ACTIVE', true,
        'ROLE_ADMIN') ON CONFLICT (id) DO NOTHING;
SELECT setval('profile_id_seq', max(id))
FROM profile;
