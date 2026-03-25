CREATE TABLE results (
    id BIGSERIAL PRIMARY KEY,
    participant_id BIGINT NOT NULL UNIQUE,
    rank_position INT NOT NULL,
    result_time DOUBLE PRECISION NOT NULL,
    medal VARCHAR(50),
    validated BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_results_participant
        FOREIGN KEY (participant_id)
        REFERENCES participants(id)
        ON DELETE CASCADE
);