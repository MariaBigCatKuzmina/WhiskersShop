
INSERT INTO roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_GUEST');

INSERT INTO users(username, email, password)
VALUES ('user_Ivan','ivan@mail.ru','$2a$12$HZkqeOjS.81tJggFJlJrGerGfiIoNUWZBTnWcUJGuiB48htVqITHC'),
       ('adm_Bob','bob@gmail.com','$2a$12$Tf/k/GSvU0k211is4Of/b.Yxz7rksyVtqEsAirkz85MrOW6Y7Seza'),
       ('user_Alex','alex.mail@mail.ru','$2a$12$p5RP7Mc0nl3qtK5DpSRrU.JlC/xqyUiFvPJ/YhCmcidiXm5uGC8NW');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1),
       (3, 2);