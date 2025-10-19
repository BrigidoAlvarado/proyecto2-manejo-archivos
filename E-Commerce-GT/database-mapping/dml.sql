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


/* ADMINISTRADOR DE LA APLICACION */
insert into _user ( email, password, name, enabled, role) values ('admin@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'admin', true, 'ADMIN'),
                                                                 ('mod@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'moderator', true, 'MODERATOR'),
                                                                 ('log@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'logistic', true, 'LOGISTIC');;;

/* CARTERA DE LA APLICACION */
insert into wallet ( money, _user) values (0, 'admin@mail.com');