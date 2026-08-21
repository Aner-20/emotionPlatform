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
    '$2a$10$IL_TUO_HASH_BCRYPT',
    1,
    2,
    CURRENT_TIMESTAMP
);