CREATE TABLE note_emotions (

    id BIGSERIAL PRIMARY KEY,

    note_id BIGINT NOT NULL,

    emotion_id BIGINT NOT NULL,

    score DOUBLE PRECISION,


    CONSTRAINT uq_note_emotion UNIQUE(note_id, emotion_id),


    CONSTRAINT fk_note_emotions_note
        FOREIGN KEY(note_id)
        REFERENCES notes(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_note_emotions_emotion
        FOREIGN KEY(emotion_id)
        REFERENCES emotions(id)
        ON DELETE CASCADE  
);