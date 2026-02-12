-- Insert Categories
INSERT INTO categories (name, description, popularity) VALUES
                                                           ('Beach', 'Relaxing beach destinations with sun and sand', 5),
                                                           ('Adventure', 'Thrilling outdoor activities and experiences', 5),
                                                           ('Cultural', 'Rich history, museums, and cultural sites', 4),
                                                           ('Luxury', 'Premium experiences and high-end accommodations', 4),
                                                           ('Budget', 'Affordable travel without compromising experiences', 3),
                                                           ('Family', 'Family-friendly destinations and activities', 5),
                                                           ('Romantic', 'Perfect for couples and honeymoons', 4),
                                                           ('Nature', 'Natural wonders, parks, and wildlife', 5);

-- Insert Destinations
INSERT INTO destinations (name, country, city, region, description, category_id, average_rating, review_count, is_active) VALUES
                                                                                                                              ('Eiffel Tower Area', 'France', 'Paris', 'Île-de-France', 'Iconic area featuring the Eiffel Tower and surrounding attractions', 3, 4.8, 15234, true),
                                                                                                                              ('Santorini Island', 'Greece', 'Santorini', 'South Aegean', 'Beautiful Greek island with white buildings and blue domes', 1, 4.9, 8765, true),
                                                                                                                              ('Grand Canyon', 'United States', 'Arizona', 'Arizona', 'Massive natural canyon with stunning views', 8, 4.7, 12456, true),
                                                                                                                              ('Tokyo', 'Japan', 'Tokyo', 'Kanto', 'Vibrant capital city with modern and traditional attractions', 3, 4.6, 9876, true),
                                                                                                                              ('Machu Picchu', 'Peru', 'Cusco', 'Cusco Region', 'Ancient Incan citadel in the Andes Mountains', 2, 4.9, 11234, true),
                                                                                                                              ('Venice', 'Italy', 'Venice', 'Veneto', 'Romantic waterways and historic architecture', 7, 4.7, 10987, true),
                                                                                                                              ('Great Barrier Reef', 'Australia', 'Queensland', 'Queensland', 'World''s largest coral reef system', 2, 4.8, 8543, true),
                                                                                                                              ('Dubai', 'UAE', 'Dubai', 'Dubai', 'Luxury shopping and modern architecture', 4, 4.5, 7654, true),
                                                                                                                              ('Bali', 'Indonesia', 'Bali', 'Bali', 'Tropical paradise with beautiful beaches and temples', 1, 4.8, 13456, true),
                                                                                                                              ('Swiss Alps', 'Switzerland', 'Various', 'Alps', 'Stunning mountain range perfect for skiing and hiking', 2, 4.9, 9234, true),
                                                                                                                              ('New York City', 'United States', 'New York', 'New York', 'The city that never sleeps with iconic landmarks', 3, 4.7, 18765, true),
                                                                                                                              ('Barcelona', 'Spain', 'Barcelona', 'Catalonia', 'Vibrant city with unique architecture and beaches', 3, 4.8, 11234, true),
                                                                                                                              ('Maldives', 'Maldives', 'Malé', 'Maldives', 'Tropical paradise with overwater bungalows', 1, 4.9, 7890, true),
                                                                                                                              ('Rome', 'Italy', 'Rome', 'Lazio', 'Ancient city with rich history and culture', 3, 4.8, 16543, true),
                                                                                                                              ('Iceland', 'Iceland', 'Reykjavik', 'Capital Region', 'Land of fire and ice with stunning natural beauty', 8, 4.8, 6789, true);

-- Insert Attractions
INSERT INTO attractions (name, destination_id, description, latitude, longitude, entry_fee, type, image_url, website, opening_hours, rating) VALUES
-- Paris Attractions
('Louvre Museum', 1, 'World''s largest art museum and historic monument', 48.8606, 2.3376, 17.00, 'MUSEUM', 'louvre.jpg', 'www.louvre.fr', '9:00 AM - 6:00 PM', 4.8),
('Arc de Triomphe', 1, 'Iconic triumphal arch honoring French soldiers', 48.8738, 2.2950, 13.00, 'LANDMARK', 'arc.jpg', 'www.arc-de-triomphe.com', '10:00 AM - 10:00 PM', 4.7),
('Notre-Dame Cathedral', 1, 'Medieval Catholic cathedral', 48.8530, 2.3499, 0.00, 'RELIGIOUS', 'notredame.jpg', null, 'Currently closed', 4.6),

-- Santorini Attractions
('Red Beach', 2, 'Unique red sand beach with volcanic cliffs', 36.3489, 25.3949, 0.00, 'BEACH', 'red-beach.jpg', null, '24 hours', 4.6),
('Oia Village', 2, 'Picturesque cliff-side village with sunset views', 36.4618, 25.3753, 0.00, 'LANDMARK', 'oia.jpg', null, '24 hours', 4.9),
('Akrotiri Archaeological Site', 2, 'Ancient Minoan Bronze Age settlement', 36.3565, 25.4038, 12.00, 'HISTORICAL', 'akrotiri.jpg', null, '8:00 AM - 8:00 PM', 4.5),

-- Grand Canyon Attractions
('South Rim', 3, 'Most popular viewing area with visitor center', 36.0544, -112.1401, 35.00, 'NATURE', 'south-rim.jpg', 'www.nps.gov/grca', '24 hours', 4.8),
('Skywalk', 3, 'Glass bridge extending over the canyon', 36.0124, -113.8109, 70.00, 'ENTERTAINMENT', 'skywalk.jpg', 'www.grandcanyonwest.com', '8:00 AM - 7:00 PM', 4.5),
('Bright Angel Trail', 3, 'Popular hiking trail into the canyon', 36.0544, -112.1401, 0.00, 'NATURE', 'bright-angel.jpg', null, '24 hours', 4.7),

-- Tokyo Attractions
('Senso-ji Temple', 4, 'Ancient Buddhist temple in Asakusa', 35.7148, 139.7967, 0.00, 'RELIGIOUS', 'sensoji.jpg', 'www.senso-ji.jp', '6:00 AM - 5:00 PM', 4.7),
('Shibuya Crossing', 4, 'Famous pedestrian scramble intersection', 35.6595, 139.7004, 0.00, 'LANDMARK', 'shibuya.jpg', null, '24 hours', 4.6),
('Tokyo Skytree', 4, 'Tallest structure in Japan', 35.7101, 139.8107, 25.00, 'ENTERTAINMENT', 'skytree.jpg', 'www.tokyo-skytree.jp', '8:00 AM - 10:00 PM', 4.7),
('Meiji Shrine', 4, 'Shinto shrine dedicated to Emperor Meiji', 35.6764, 139.6993, 0.00, 'RELIGIOUS', 'meiji.jpg', null, 'Sunrise - Sunset', 4.8),

-- Machu Picchu Attractions
('Inca Trail', 5, 'Historic hiking trail to Machu Picchu', -13.1631, -72.5450, 500.00, 'NATURE', 'inca-trail.jpg', null, 'Varies by permit', 4.9),
('Temple of the Sun', 5, 'Ancient Incan temple ruins', -13.1633, -72.5450, 0.00, 'HISTORICAL', 'temple-sun.jpg', null, '6:00 AM - 5:00 PM', 4.8),
('Huayna Picchu', 5, 'Mountain overlooking Machu Picchu', -13.1545, -72.5447, 75.00, 'NATURE', 'huayna.jpg', null, '7:00 AM - 1:00 PM', 4.9),

-- Venice Attractions
('St. Mark''s Basilica', 6, 'Ornate cathedral with Byzantine architecture', 45.4345, 12.3397, 5.00, 'RELIGIOUS', 'stmarks.jpg', null, '9:30 AM - 5:00 PM', 4.8),
('Grand Canal', 6, 'Main waterway through Venice', 45.4408, 12.3155, 0.00, 'LANDMARK', 'grand-canal.jpg', null, '24 hours', 4.7),
('Doge''s Palace', 6, 'Gothic palace and former residence of Doge', 45.4338, 12.3403, 25.00, 'HISTORICAL', 'doges.jpg', null, '9:00 AM - 7:00 PM', 4.6),

-- Great Barrier Reef Attractions
('Reef Snorkeling', 7, 'Snorkeling tours in crystal clear waters', -18.2871, 147.6992, 120.00, 'NATURE', 'snorkel.jpg', null, '7:00 AM - 5:00 PM', 4.9),
('Whitehaven Beach', 7, 'Pristine white sand beach', -20.2817, 149.0392, 0.00, 'BEACH', 'whitehaven.jpg', null, '24 hours', 4.8),
('Heart Reef', 7, 'Natural heart-shaped coral formation', -19.2900, 149.2300, 250.00, 'NATURE', 'heart-reef.jpg', null, 'Daylight hours', 4.7),

-- Dubai Attractions
('Burj Khalifa', 8, 'Tallest building in the world', 25.1972, 55.2744, 40.00, 'LANDMARK', 'burj.jpg', 'www.burjkhalifa.ae', '8:30 AM - 11:00 PM', 4.8),
('Dubai Mall', 8, 'Largest shopping mall in the world', 25.1972, 55.2796, 0.00, 'SHOPPING', 'dubai-mall.jpg', 'www.thedubaimall.com', '10:00 AM - 12:00 AM', 4.6),
('Palm Jumeirah', 8, 'Artificial palm-shaped island', 25.1124, 55.1390, 0.00, 'LANDMARK', 'palm.jpg', null, '24 hours', 4.5),

-- Bali Attractions
('Tanah Lot Temple', 9, 'Ancient Hindu shrine on rock formation', -8.6211, 115.0868, 5.00, 'RELIGIOUS', 'tanah-lot.jpg', null, '7:00 AM - 7:00 PM', 4.7),
('Ubud Monkey Forest', 9, 'Sacred sanctuary with temples and monkeys', -8.5186, 115.2584, 10.00, 'NATURE', 'monkey-forest.jpg', null, '8:30 AM - 6:00 PM', 4.5),
('Tegallalang Rice Terraces', 9, 'Famous terraced rice paddies', -8.4300, 115.2800, 0.00, 'NATURE', 'rice-terraces.jpg', null, '8:00 AM - 6:00 PM', 4.8),

-- Swiss Alps Attractions
('Matterhorn', 10, 'Iconic pyramid-shaped mountain', 45.9763, 7.6586, 0.00, 'NATURE', 'matterhorn.jpg', null, '24 hours', 4.9),
('Jungfraujoch', 10, 'Top of Europe railway station', 46.5472, 7.9853, 220.00, 'NATURE', 'jungfraujoch.jpg', 'www.jungfrau.ch', '8:00 AM - 5:00 PM', 4.8),
('Interlaken', 10, 'Adventure sports hub between two lakes', 46.6863, 7.8632, 0.00, 'ENTERTAINMENT', 'interlaken.jpg', null, 'Varies', 4.7);

-- Insert Users (Password: "Password123!" hashed with BCrypt)
INSERT INTO users_table (email, password, first_name, last_name, subscription_plan, role, created_at) VALUES
                                                                                                          ('john.doe@example.com', '$2a$10$N8VWG9Kb3WPPbV.KzuLpmeYJKqBxBRMvRMXCl6LKI8YsVE0vL5jLS', 'John', 'Doe', 'BASIC', 'USER', CURRENT_TIMESTAMP),
                                                                                                          ('jane.smith@example.com', '$2a$10$N8VWG9Kb3WPPbV.KzuLpmeYJKqBxBRMvRMXCl6LKI8YsVE0vL5jLS', 'Jane', 'Smith', 'PRO', 'USER', CURRENT_TIMESTAMP),
                                                                                                          ('admin@tripplanner.com', '$2a$10$N8VWG9Kb3WPPbV.KzuLpmeYJKqBxBRMvRMXCl6LKI8YsVE0vL5jLS', 'Admin', 'User', 'PRO', 'ADMIN', CURRENT_TIMESTAMP),
                                                                                                          ('mike.johnson@example.com', '$2a$10$N8VWG9Kb3WPPbV.KzuLpmeYJKqBxBRMvRMXCl6LKI8YsVE0vL5jLS', 'Mike', 'Johnson', 'BASIC', 'USER', CURRENT_TIMESTAMP),
                                                                                                          ('sarah.williams@example.com', '$2a$10$N8VWG9Kb3WPPbV.KzuLpmeYJKqBxBRMvRMXCl6LKI8YsVE0vL5jLS', 'Sarah', 'Williams', 'PRO', 'USER', CURRENT_TIMESTAMP);

-- Insert Reviews
INSERT INTO reviews (user_id, destination_id, rating, comment, created_at) VALUES
                                                                               (1, 1, 5, 'Absolutely breathtaking! The Eiffel Tower area is a must-see. So much history and culture!', CURRENT_TIMESTAMP),
                                                                               (2, 2, 5, 'Santorini is paradise on earth. The sunset in Oia is unforgettable. Highly recommend!', CURRENT_TIMESTAMP),
                                                                               (1, 3, 4, 'Grand Canyon is stunning but very crowded in summer. Go early morning for best experience.', CURRENT_TIMESTAMP),
                                                                               (2, 4, 5, 'Tokyo is an amazing city with so much to see! Perfect blend of modern and traditional.', CURRENT_TIMESTAMP),
                                                                               (1, 5, 5, 'Machu Picchu exceeded all expectations. Life-changing experience. Worth the trek!', CURRENT_TIMESTAMP),
                                                                               (3, 1, 4, 'Paris was lovely but expensive. Still worth it for the art and architecture.', CURRENT_TIMESTAMP),
                                                                               (4, 6, 5, 'Venice is magical! Take a gondola ride at sunset. Simply romantic!', CURRENT_TIMESTAMP),
                                                                               (5, 7, 5, 'Snorkeling in the Great Barrier Reef was incredible. So many colorful fish!', CURRENT_TIMESTAMP),
                                                                               (4, 8, 4, 'Dubai is impressive but can be overwhelming. The Burj Khalifa view is worth it.', CURRENT_TIMESTAMP),
                                                                               (5, 9, 5, 'Bali has everything - beaches, temples, culture, and friendly people. Perfect vacation!', CURRENT_TIMESTAMP),
                                                                               (1, 10, 5, 'Swiss Alps are breathtaking. Best skiing and hiking I''ve ever experienced!', CURRENT_TIMESTAMP),
                                                                               (2, 11, 4, 'NYC is fast-paced and exciting. So many things to do but can be tiring.', CURRENT_TIMESTAMP),
                                                                               (3, 12, 5, 'Barcelona has amazing architecture and food. Gaudi''s works are stunning!', CURRENT_TIMESTAMP),
                                                                               (4, 13, 5, 'Maldives is the definition of paradise. Crystal clear water and amazing resorts.', CURRENT_TIMESTAMP),
                                                                               (5, 14, 5, 'Rome is a living museum. History everywhere you look. Loved it!', CURRENT_TIMESTAMP);

-- Insert Sample Budgets
INSERT INTO budgets (total_budget, flight_cost, accommodation_cost, activities_cost, transport_cost, other_cost, currency) VALUES
                                                                                                                               (3000.00, 800.00, 1200.00, 600.00, 200.00, 200.00, 'USD'),
                                                                                                                               (5000.00, 1200.00, 2000.00, 1000.00, 500.00, 300.00, 'USD'),
                                                                                                                               (2000.00, 600.00, 800.00, 400.00, 100.00, 100.00, 'USD');

-- Insert Sample Trip Plans
INSERT INTO trip_plan (title, user_id, start_date, end_date, budget_id, status, notes, created_at) VALUES
                                                                                                       ('European Summer Adventure', 1, '2024-07-01', '2024-07-15', 1, 'PLANNED', 'Excited to explore Paris and Venice!', CURRENT_TIMESTAMP),
                                                                                                       ('Japanese Cultural Tour', 2, '2024-09-10', '2024-09-20', 2, 'DRAFT', 'Planning temples, food, and cherry blossoms', CURRENT_TIMESTAMP),
                                                                                                       ('Beach Getaway', 3, '2024-06-15', '2024-06-22', 3, 'BOOKED', 'Relaxing vacation in Bali', CURRENT_TIMESTAMP);

-- Insert Trip Destinations (Many-to-Many)
INSERT INTO trip_destinations (trip_plan_id, destination_id) VALUES
                                                                 (1, 1),  -- Paris
                                                                 (1, 6),  -- Venice
                                                                 (2, 4),  -- Tokyo
                                                                 (3, 9);  -- Bali

-- Insert Sample Trip Itineraries
INSERT INTO trip_itineraries (trip_plan_id, date, start_time, end_time, attraction_id, activity, notes, order_index) VALUES
                                                                                                                         (1, '2024-07-01', '09:00:00', '12:00:00', 1, 'Visit Louvre Museum', 'Book tickets in advance', 1),
                                                                                                                         (1, '2024-07-01', '14:00:00', '16:00:00', 2, 'Arc de Triomphe', 'Climb to the top for city views', 2),
                                                                                                                         (1, '2024-07-05', '10:00:00', '13:00:00', 13, 'Explore St. Mark''s Basilica', 'Dress modestly', 3),
                                                                                                                         (2, '2024-09-10', '08:00:00', '11:00:00', 7, 'Senso-ji Temple visit', 'Early morning to avoid crowds', 1),
                                                                                                                         (2, '2024-09-10', '15:00:00', '18:00:00', 8, 'Shibuya Crossing experience', 'Best time for photos', 2),
                                                                                                                         (3, '2024-06-15', '09:00:00', '12:00:00', 25, 'Tanah Lot Temple tour', 'Sunset viewing recommended', 1);