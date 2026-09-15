CREATE TABLE IF NOT EXISTS public.companies (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zipcode VARCHAR(255),
    country VARCHAR(255)
);