CREATE TABLE events (
    id BIGSERIAL PRIMARY KEY,
    competition_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    event_date DATE NOT NULL,
    start_time TIME NOT NULL,
    location VARCHAR(150) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_events_competition
        FOREIGN KEY (competition_id)
        REFERENCES competitions(id)
        ON DELETE CASCADE
);