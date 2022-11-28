
CREATE TABLE categories
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255),
    description VARCHAR(1024)
);

CREATE TABLE producers
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(100),
    country VARCHAR(100)
);

CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255)   NOT NULL,
    description VARCHAR(1000),
    price       DECIMAL(12, 2) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    category_id BIGINT         NOT NULL,
    producer_id BIGINT,
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT FK_producer FOREIGN KEY (producer_id) REFERENCES producers (id)
);


insert into categories(title, description)
VALUES ('кошки', 'товары для кошек'),
       ('собаки', 'товары для собак');

INSERT INTO producers(title, country)
VALUES ('blitz', 'Italy'),
       ('blitz', 'Russia');

INSERT INTO products(title, description, price, category_id, producer_id)
VALUES ('Корм holistic sterilized', 'корм для стерилизованых кошек утка-рис', 3500, 1, 1),
       ('Корм kitten', 'корм для котят', 3900, 1, 1),
       ('Корм maine-coon', 'корм для кошек крупных пород', 4500, 1, 1);

