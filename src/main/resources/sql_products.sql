-- insert suppliers data
INSERT INTO suppliers (id, name)
VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Amazon'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Lenovo'),
       ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'Apple')
ON CONFLICT (id) DO NOTHING;

INSERT INTO product_categories (name, department, description)
VALUES
    ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
    ('Laptop', 'Hardware', 'Portable power'),
    ('Smartphone', 'Hardware', 'Smart at your fingers');

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";



INSERT INTO categories (id, name, description)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'Tablet', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Laptop', 'Portable power'),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'Smartphone', 'Smart at your fingers');

INSERT INTO products (id, name, price, currency, description, category_id, supplier_id, image)
VALUES (uuid_generate_v4(), 'Amazon Fire', '49.9', 'USD',
        'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
        'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '/static/img/product_1.jpg');

-- insert products data
INSERT INTO products (id, name, price, currency, description, category_id, supplier_id, image)
VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Amazon Fire', '49.9', 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '/static/img/product_1.jpg'),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a15', 'Lenovo IdeaPad Mix 700', '479', 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', '/static/img/product_2.jpg'),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Amazon Fire HD 8', '89', 'USD', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', '/static/img/product_3.jpg'),
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a17', 'MacBook Pro 13" Touch Bar i5 2.0GHz 1TB SSD Space Grey, layout US', '2123', 'USD', '\nYour MacBook Pro comes with apps for most everything you want to do. Edit and share your photos and videos, create presentations, and enjoy music, books, movies, and more.', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'https://lcdn.altex.ro/resize/media/catalog/product/M/a/2bd48d28d1c32adea0e55139a4e6434a/MacBook_Pro_13in_Silver-1.jpg');

