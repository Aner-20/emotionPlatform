CREATE TABLE note_emotions (

    note_id BIGINT NOT NULL,

    emotion_id BIGINT NOT NULL,

    score DECIMAL(5,2),


    PRIMARY KEY(note_id, emotion_id),


    CONSTRAINT fk_note_emotions_note
        FOREIGN KEY(note_id)
        REFERENCES notes(id),
        ON DELETE CASCADE

    CONSTRAINT fk_note_emotions_emotion
        FOREIGN KEY(emotion_id)
        REFERENCES emotions(id)
        ON DELETE CASCADE  
);