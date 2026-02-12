-- Attractions Table
CREATE TABLE IF NOT EXISTS attractions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    destination_id BIGINT NOT NULL REFERENCES destinations(id) ON DELETE CASCADE,
    description TEXT,
    entry_fee DECIMAL(10, 2),
    type VARCHAR(50),
    website VARCHAR(255),
    opening_hours VARCHAR(255),
    rating DECIMAL(3, 2)
    );