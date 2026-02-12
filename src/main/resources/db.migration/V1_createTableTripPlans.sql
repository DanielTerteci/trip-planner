-- Trip Plans Table
CREATE TABLE IF NOT EXISTS trip_plan (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users_table(id) ON DELETE CASCADE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    budget_id BIGINT REFERENCES budgets(id) ON DELETE SET NULL,
    status VARCHAR(50) DEFAULT 'DRAFT',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
    );