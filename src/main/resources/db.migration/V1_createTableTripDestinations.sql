-- Trip Destinations Join Table (Many-to-Many)
CREATE TABLE IF NOT EXISTS trip_destinations (
    trip_plan_id BIGINT NOT NULL REFERENCES trip_plan(id) ON DELETE CASCADE,
    destination_id BIGINT NOT NULL REFERENCES destinations(id) ON DELETE CASCADE,
    PRIMARY KEY (trip_plan_id, destination_id)
    );