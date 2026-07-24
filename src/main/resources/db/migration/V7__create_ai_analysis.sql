CREATE TABLE ai_analysis (

    id BIGSERIAL PRIMARY KEY,

    note_id BIGINT NOT NULL UNIQUE,

    mood_score INTEGER,

    summary TEXT,

    json_result JSONB,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,


    CONSTRAINT fk_ai_analysis_note
        FOREIGN KEY(note_id)
        REFERENCES notes(id)
        ON DELETE CASCADE
);