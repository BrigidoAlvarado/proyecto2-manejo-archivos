insert into role (name) values ('ADMIN'),
                               ('MODERATOR'),
                               ('LOGISTIC'),
                               ('COMMON');

insert into category (name) values ('Tecnología'),
                                   ('Hogar'),
                                   ('Académico'),
                                   ('Personal'),
                                   ('Decoración'),
                                   ('Entretenimiento');

insert into _user ( email, password, name, enabled, role) values ('admin@mail.com', '$2a$10$YIEet3q9dyYCDOSUxHd8yOIbFBpt9Diquy2i3OCaANy0zs3SZXO.K', 'admin', false, 'ADMIN')