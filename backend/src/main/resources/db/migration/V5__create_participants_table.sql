CREATE TABLE participants (
    id BIGSERIAL PRIMARY KEY,
    athlete_id BIGINT NOT NULL,
    event_id BIGINT NOT NULL,
    status VARCHAR(50) NOT NULL,
    registered_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_participants_athlete
        FOREIGN KEY (athlete_id)
        REFERENCES athletes(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_participants_event
        FOREIGN KEY (event_id)
        REFERENCES events(id)
        ON DELETE CASCADE,
    CONSTRAINT uk_athlete_event UNIQUE (athlete_id, event_id)
);