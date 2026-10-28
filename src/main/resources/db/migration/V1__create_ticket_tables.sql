CREATE TABLE category
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
    );

CREATE TABLE  tickets
(
    id          UUID PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(50) NOT NULL,
    category_id BIGINT REFERENCES category (id)
);