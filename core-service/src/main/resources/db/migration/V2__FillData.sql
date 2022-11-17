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
VALUES ('user_Ivan','$2a$12$HZkqeOjS.81tJggFJlJrGerGfiIoNUWZBTnWcUJGuiB48htVqITHC'),
       ('adm_Bob','$2a$12$Tf/k/GSvU0k211is4Of/b.Yxz7rksyVtqEsAirkz85MrOW6Y7Seza'),
       ('user_Alex','$2a$12$p5RP7Mc0nl3qtK5DpSRrU.JlC/xqyUiFvPJ/YhCmcidiXm5uGC8NW');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1),
       (3, 2);