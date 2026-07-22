CREATE TABLE users (

    id BIGSERIAL PRIMARY KEY,

    first_name VARCHAR(50),

    last_name VARCHAR(50),

    email VARCHAR(150) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    role_id BIGINT NOT NULL,

    department_id BIGINT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    CONSTRAINT fk_users_role
        FOREIGN KEY (role_id)
        REFERENCES roles(id),


    CONSTRAINT fk_users_department
        FOREIGN KEY (department_id)
        REFERENCES departments(id)

);