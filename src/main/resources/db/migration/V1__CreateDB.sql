
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL ,
    password VARCHAR(1024) NOT NULL,
    CONSTRAINT UK_username UNIQUE (username)
);

CREATE TABLE user_contacts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    value VARCHAR(500) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT FK_user_contacts FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT FK_user_roles FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT FK_role_roles FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS categories(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS categories_tree(
    category_id BIGINT NOT NULL,
    parent_category_id BIGINT NOT NULL,
    CONSTRAINT PK_category_parent PRIMARY KEY (category_id, parent_category_id),
    CONSTRAINT FK_category_tree FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_parent_tree FOREIGN KEY (parent_category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS producers(
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        title VARCHAR(100),
                                        country VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
#     photos
    price DOUBLE NOT NULL,
    category_id BIGINT NOT NULL,
    producer_id BIGINT NOT NULL,
    CONSTRAINT FK_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT FK_producer FOREIGN KEY (producer_id) REFERENCES producers(id)
);

CREATE TABLE IF NOT EXISTS attributes(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS products_attributes(
    product_id BIGINT NOT NULL,
    attribute_id BIGINT NOT NULL,
    value VARCHAR(150),
    CONSTRAINT PK_primary PRIMARY KEY (product_id, attribute_id),
    CONSTRAINT FK_attribute_products FOREIGN KEY (attribute_id) REFERENCES attributes(id)
);

CREATE TABLE IF NOT EXISTS stocks(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tittle VARCHAR(150)
);

CREATE TABLE IF NOT EXISTS stocks_contacts(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    value VARCHAR(500) NOT NULL,
    stock_id BIGINT NOT NULL,
    CONSTRAINT FK_stock_contacts FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

CREATE TABLE IF NOT EXISTS stock_products(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    stock_id BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    last_update DATETIME NOT NULL,
    CONSTRAINT FK_product_stock FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT FK_stock_products FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

CREATE TABLE IF NOT EXISTS carts(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    last_update DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT FK_user_carts FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS cart_products(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    CONSTRAINT FK_cart FOREIGN KEY (cart_id) REFERENCES carts(id),
    CONSTRAINT FK_product_cart FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE IF NOT EXISTS deliveries(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    address VARCHAR(400) NOT NULL
);

CREATE TABLE IF NOT EXISTS payments(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    sum DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS order_details(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_date Date NOT NULL,
    user_id BIGINT NOT NULL,
    delivery_id BIGINT NOT NULL,
    payment_id BIGINT NOT NULL,
    sum DOUBLE NOT NULL,
    CONSTRAINT UK_delivery UNIQUE (delivery_id),
    CONSTRAINT UK_payment UNIQUE (payment_id),
    CONSTRAINT FK_delivery FOREIGN KEY (delivery_id) REFERENCES deliveries(id),
    CONSTRAINT FK_payment FOREIGN KEY (payment_id) REFERENCES payments(id),
    CONSTRAINT FK_user_orders FOREIGN KEY (user_id) REFERENCES users(id)
);

