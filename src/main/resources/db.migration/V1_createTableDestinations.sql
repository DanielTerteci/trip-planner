-- Destinations Table
CREATE TABLE IF NOT EXISTS destinations (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    region VARCHAR(100),
    description TEXT,
    category_id BIGINT REFERENCES categories(id) ON DELETE SET NULL,
    average_rating DECIMAL(3, 2),
    review_count INT DEFAULT 0,
    is_active BOOLEAN DEFAULT TRUE
    );