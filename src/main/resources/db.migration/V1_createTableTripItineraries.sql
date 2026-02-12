-- Trip Itineraries Table
CREATE TABLE IF NOT EXISTS trip_itineraries (
    id SERIAL PRIMARY KEY,
    trip_plan_id BIGINT NOT NULL REFERENCES trip_plan(id) ON DELETE CASCADE,
    date DATE NOT NULL,
    start_time TIME,
    end_time TIME,
    attraction_id BIGINT REFERENCES attractions(id) ON DELETE SET NULL,
    activity VARCHAR(255) NOT NULL,
    notes TEXT,
    order_index INT
    );
