-- Inserimento o verifica della presenza dei dipartimenti base
INSERT INTO departments (id, name) VALUES (1, 'Help Desk') ON CONFLICT (id) DO NOTHING;
INSERT INTO departments (id, name) VALUES (2, 'Human Resources') ON CONFLICT (id) DO NOTHING;

-- Inserimento dell'utente amministratore (Password impostata su: admin123)
INSERT INTO users (
    first_name,
    last_name,
    email,
    password,
    role_id,
    department_id,
    created_at
)
VALUES (
    'Admin',
    'Test',
    'admin@emotionplatform.com',
    '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVym5pIacM89f.pL3y8tPOnC',
    1,
    (SELECT id FROM departments WHERE name = 'Human Resources' LIMIT 1),
    CURRENT_TIMESTAMP
)
ON CONFLICT (email) DO UPDATE 
SET password = EXCLUDED.password;