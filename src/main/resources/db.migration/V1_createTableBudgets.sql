-- Budgets Table
CREATE TABLE IF NOT EXISTS budgets (
    id SERIAL PRIMARY KEY,
    total_budget DECIMAL(12, 2),
    flight_cost DECIMAL(12, 2) DEFAULT 0.00,
    accommodation_cost DECIMAL(12, 2) DEFAULT 0.00,
    activities_cost DECIMAL(12, 2) DEFAULT 0.00,
    transport_cost DECIMAL(12, 2) DEFAULT 0.00,
    other_cost DECIMAL(12, 2) DEFAULT 0.00,
    currency VARCHAR(10) NOT NULL DEFAULT 'USD'
    );