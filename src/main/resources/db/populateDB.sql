DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- password
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');
-- admin
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES ('ROLE_USER', 100000);
INSERT INTO user_roles (role, user_id) VALUES ('ROLE_ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-30 10:00:00', 'Завтрак', 500, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-30 13:00:00', 'Обед', 1000, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-30 20:00:00', 'Ужин', 500, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-31 10:00:00', 'Завтрак', 500, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-31 13:00:00', 'Обед', 1000, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-05-31 20:00:00', 'Ужин', 510, 100000);
INSERT INTO meals (datetime, description, calories, user_id) VALUES ('2015-06-01 14:00:00', 'Админ-Ланч', 2001, 100001);
