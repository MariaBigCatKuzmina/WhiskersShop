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

INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_GUEST');

INSERT INTO users(username, password)
VALUES ('user_Ivan','userPassword1'),
       ('adm_Bob','admPass'),
       ('user_Alex','userPassword2');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1),
       (3, 2);