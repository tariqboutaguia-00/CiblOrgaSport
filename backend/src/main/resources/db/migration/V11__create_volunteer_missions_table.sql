CREATE TABLE volunteer_missions (
    volunteer_id BIGINT NOT NULL,
    mission_id BIGINT NOT NULL,
    PRIMARY KEY (volunteer_id, mission_id),
    CONSTRAINT fk_volunteer_missions_volunteer
        FOREIGN KEY (volunteer_id)
        REFERENCES volunteers(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_volunteer_missions_mission
        FOREIGN KEY (mission_id)
        REFERENCES missions(id)
        ON DELETE CASCADE
);