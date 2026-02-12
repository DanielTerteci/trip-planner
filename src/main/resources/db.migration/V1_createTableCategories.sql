-- Categories Table
CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    popularity INT NOT NULL CHECK (popularity >= 1 AND popularity <= 5)
    );