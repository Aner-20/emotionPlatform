CREATE TABLE notes (

    id BIGSERIAL PRIMARY KEY,

    user_id BIGINT NOT NULL,

    text TEXT NOT NULL,

    is_private BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP,


    CONSTRAINT fk_notes_user
        FOREIGN KEY(user_id)
        REFERENCES users(id)

);