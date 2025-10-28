begin;

insert into role (name)
values ('ADMIN'),
       ('MODERATOR'),
       ('LOGISTIC'),
       ('COMMON');

insert into category (name)
values ('Tecnología'),
       ('Hogar'),
       ('Académico'),
       ('Personal'),
       ('Decoración'),
       ('Entretenimiento');


/* EMPLEADOS */
insert into _user (email, password, name, enabled, role)
values ('admin@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Brigido', true, 'ADMIN'),

    /* usuarios de logistica */
       ('alejandroLog@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Alejandro', true,
        'LOGISTIC'),
       ('isabelLog@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Isabel', true,
        'LOGISTIC'),
       ('mateoLog@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Mateo', true, 'LOGISTIC'),

    /* usuarios moderadores */
       ('sofiaMod@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Sofia', true,
        'MODERATOR'),
       ('diegoMod@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Diego', true,
        'MODERATOR'),
       ('valentinaMod@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Valentina', true,
        'MODERATOR'),
       ('sebastianMod@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Sebastian', true,
        'MODERATOR'),
       ('camilaMod@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Camila', true,
        'MODERATOR');


/* CARTERA DE LA APLICACION */
insert into wallet (money, _user)
values (0, 'admin@mail.com');


/* USUARIOS */

--- 1ro.
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('lara@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Lara', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'lara@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'lara@mail.com');

-- Productos para Lara
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (1, 'Lámpara de Mesa', 'Lámpara de mesa moderna', 'images-server/1.jpeg', 50.00, 12, false, 'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 1);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (2, 'Reloj Despertador', 'Reloj despertador digital con radio', 'images-server/2.jpeg', 25.99, 15, true,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 2);
INSERT INTO product_category (category, product)
VALUES ('Personal', 2);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (3, 'Set de Cuadernos', 'Set de 3 cuadernos profesionales', 'images-server/3.jpeg', 18.50, 25, false,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 3);
INSERT INTO product_category (category, product)
VALUES ('Personal', 3);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (4, 'Auriculares con Cable', 'Auriculares con sonido envolvente', 'images-server/4.jpeg', 29.99, 18, true,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 4);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 4);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (5, 'Maceta con Planta', 'Maceta decorativa con planta suculenta', 'images-server/5.jpeg', 22.75, 10, false,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 5);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 5);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (6, 'Libro de Novelas', 'Colección de 3 novelas bestseller', 'images-server/6.jpeg', 34.99, 12, true,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 6);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 6);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (7, 'Organizador de Joyas', 'Organizador de joyas con espejo', 'images-server/7.jpeg', 19.99, 20, false,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 7);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 7);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (8, 'Cargador Portátil', 'Power bank 10000mAh compacto', 'images-server/8.jpeg', 32.50, 14, true,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 8);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (9, 'Juego de Tazas', 'Set de 4 tazas de cerámica', 'images-server/9.jpeg', 27.99, 8, false, 'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 9);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 9);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (10, 'Puzle 500 Piezas', 'Puzle de paisaje para adultos', 'images-server/10.jpeg', 16.99, 16, true,
        'lara@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 10);

-- Usuario 2
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('carlos@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Carlos', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'carlos@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'carlos@mail.com');

-- Productos para Carlos
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (11, 'Teclado Mecánico', 'Teclado mecánico RGB para gaming', 'images-server/11.jpeg', 89.99, 8, true,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 11);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 11);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (12, 'Silla Ergonómica', 'Silla de oficina ergonómica', 'images-server/12.jpeg', 199.99, 5, false,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 12);
INSERT INTO product_category (category, product)
VALUES ('Personal', 12);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (13, 'Libro Programación', 'Libro de algoritmos y estructuras de datos', 'images-server/13.jpeg', 45.50, 15,
        true, 'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 13);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (14, 'Mouse Inalámbrico', 'Mouse ergonómico inalámbrico', 'images-server/14.jpeg', 35.99, 20, false,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 14);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (15, 'Lámpara LED', 'Lámpara LED de escritorio', 'images-server/15.jpeg', 29.99, 12, true, 'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 15);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 15);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (16, 'Cuaderno Profesional', 'Cuaderno de notas profesional', 'images-server/16.jpeg', 12.99, 30, false,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 16);
INSERT INTO product_category (category, product)
VALUES ('Personal', 16);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (17, 'Auriculares Bluetooth', 'Auriculares con cancelación de ruido', 'images-server/17.jpeg', 79.99, 7, true,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 17);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 17);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (18, 'Maceta Decorativa', 'Maceta de cerámica para interiores', 'images-server/18.jpeg', 24.99, 18, false,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 18);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 18);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (19, 'Calculadora Científica', 'Calculadora para estudiantes', 'images-server/19.jpeg', 32.50, 25, true,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 19);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (20, 'Videojuego PS5', 'Videojuego de aventura para PS5', 'images-server/20.jpeg', 59.99, 6, true,
        'carlos@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 20);

-- Usuario 3
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('sofia@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Sofia', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'sofia@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'sofia@mail.com');

-- Productos para Sofia
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (21, 'Tablet Digital', 'Tablet para dibujo digital', 'images-server/21.jpeg', 129.99, 4, true, 'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 21);
INSERT INTO product_category (category, product)
VALUES ('Académico', 21);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (22, 'Set de Pinceles', 'Set completo de pinceles para acuarela', 'images-server/22.jpeg', 28.50, 22, false,
        'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 22);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 22);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (23, 'Organizador Escritorio', 'Organizador de madera para escritorio', 'images-server/23.jpeg', 19.99, 15, true,
        'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 23);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 23);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (24, 'Libro de Arte', 'Libro sobre técnicas de pintura', 'images-server/24.jpeg', 42.75, 8, false,
        'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 24);
INSERT INTO product_category (category, product)
VALUES ('Personal', 24);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (25, 'Reloj de Pared', 'Reloj decorativo vintage', 'images-server/25.jpeg', 67.99, 10, true, 'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 25);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 25);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (26, 'Tableta Gráfica', 'Tableta gráfica profesional', 'images-server/26.jpeg', 159.99, 3, true,
        'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 26);
INSERT INTO product_category (category, product)
VALUES ('Personal', 26);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (27, 'Juego de Mesa', 'Juego de estrategia familiar', 'images-server/27.jpeg', 34.99, 12, false,
        'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 27);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (28, 'Espejo Decorativo', 'Espejo para pared con marco dorado', 'images-server/28.jpeg', 89.50, 6, true,
        'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 28);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 28);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (29, 'Cámara Web', 'Cámara web HD para streaming', 'images-server/29.jpeg', 75.25, 9, false, 'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 29);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 29);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (30, 'Set Acuarelas', 'Set profesional de acuarelas', 'images-server/30.jpeg', 52.99, 7, true, 'sofia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 30);

-- Usuario 4
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('miguel@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Miguel', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'miguel@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'miguel@mail.com');

-- Productos para Miguel
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (31, 'Smartwatch', 'Reloj inteligente con GPS', 'images-server/31.jpeg', 199.99, 5, true, 'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 31);
INSERT INTO product_category (category, product)
VALUES ('Personal', 31);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (32, 'Mochila Laptop', 'Mochila antirrobo para laptop', 'images-server/32.jpeg', 49.99, 14, false,
        'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 32);
INSERT INTO product_category (category, product)
VALUES ('Académico', 32);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (33, 'Router WiFi', 'Router de doble banda 5G', 'images-server/33.jpeg', 79.50, 8, true, 'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 33);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (34, 'Libro Cocina', 'Libro de recetas internacionales', 'images-server/34.jpeg', 28.75, 20, false,
        'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 34);
INSERT INTO product_category (category, product)
VALUES ('Hogar', 34);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (35, 'Alfombra Oficina', 'Alfombra ergonómica para silla', 'images-server/35.jpeg', 35.99, 25, true,
        'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 35);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 35);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (36, 'Power Bank', 'Batería externa 20000mAh', 'images-server/36.jpeg', 45.99, 18, false, 'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 36);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (37, 'Soporte Laptop', 'Soporte ajustable para laptop', 'images-server/37.jpeg', 22.50, 30, true,
        'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 37);
INSERT INTO product_category (category, product)
VALUES ('Hogar', 37);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (38, 'Videojuego Nintendo', 'Juego de aventura para Switch', 'images-server/38.jpeg', 49.99, 9, true,
        'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 38);


INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (39, 'Lámpara Pie', 'Lámpara de pie moderna', 'images-server/39.jpeg', 89.99, 6, false, 'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 39);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 39);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (40, 'Curso Online', 'Acceso a curso de programación', 'images-server/40.jpeg', 99.00, 50, true,
        'miguel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 40);

-- Usuario 5
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('elena@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Elena', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'elena@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'elena@mail.com');

-- Productos para Elena
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (41, 'Kindle Paperwhite', 'Lector de libros electrónicos', 'images-server/41.jpeg', 139.99, 7, true,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 41);
INSERT INTO product_category (category, product)
VALUES ('Académico', 41);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (42, 'Velas Aromáticas', 'Set de velas con aromas relajantes', 'images-server/42.jpeg', 24.99, 35, false,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 42);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 42);
INSERT INTO product_category (category, product)
VALUES ('Personal', 42);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (43, 'Altavoz Bluetooth', 'Altavoz portátil resistente al agua', 'images-server/43.jpeg', 67.50, 11, true,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 43);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 43);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (44, 'Cojines Decorativos', 'Set de 4 cojines para sofá', 'images-server/44.jpeg', 45.99, 16, false,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 44);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 44);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (45, 'Libro Poesía', 'Colección de poemas contemporáneos', 'images-server/45.jpeg', 18.75, 28, true,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 45);
INSERT INTO product_category (category, product)
VALUES ('Personal', 45);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (46, 'Difusor Aromas', 'Difusor de aceites esenciales', 'images-server/46.jpeg', 32.99, 13, false,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 46);
INSERT INTO product_category (category, product)
VALUES ('Personal', 46);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (47, 'Tablet Stand', 'Soporte ajustable para tablet', 'images-server/47.jpeg', 19.50, 40, true,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 47);
INSERT INTO product_category (category, product)
VALUES ('Hogar', 47);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (48, 'Rompecabezas 1000pzs', 'Rompecabezas de paisaje montañoso', 'images-server/48.jpeg', 27.99, 9, false,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 48);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (49, 'Espejo Compacto', 'Espejo compacto con luz LED', 'images-server/49.jpeg', 15.99, 22, true,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 49);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (50, 'Cortina Blackout', 'Cortina opaca para dormitorio', 'images-server/50.jpeg', 52.75, 8, false,
        'elena@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 50);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 50);

-- Usuario 6
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('david@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'David', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'david@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'david@mail.com');

-- Productos para David
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (51, 'Monitor 24"', 'Monitor Full HD para oficina', 'images-server/51.jpeg', 189.99, 6, true, 'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 51);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (52, 'Mesa Computadora', 'Mesa ergonómica para computadora', 'images-server/52.jpeg', 120.50, 4, false,
        'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 52);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 52);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (53, 'Libro Finanzas', 'Libro sobre educación financiera', 'images-server/53.jpeg', 35.99, 15, true,
        'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 53);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (54, 'Teclado Inalámbrico', 'Teclado silencioso inalámbrico', 'images-server/54.jpeg', 45.75, 12, false,
        'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 54);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (55, 'Silla Gamer', 'Silla ergonómica para gaming', 'images-server/55.jpeg', 229.99, 3, true, 'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 55);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 55);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (56, 'Estantería Moderna', 'Estantería de diseño contemporáneo', 'images-server/56.jpeg', 89.99, 7, false,
        'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 56);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 56);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (57, 'Videojuego PC', 'Juego de estrategia para PC', 'images-server/57.jpeg', 39.99, 18, true, 'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 57);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (58, 'Lámpara Techo', 'Lámpara de techo LED moderna', 'images-server/58.jpeg', 67.50, 5, false,
        'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 58);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 58);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (59, 'Curso Inglés', 'Curso completo de inglés online', 'images-server/59.jpeg', 79.00, 100, true,
        'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 59);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (60, 'Webcam 4K', 'Cámara web 4K para streaming', 'images-server/60.jpeg', 95.99, 8, true, 'david@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 60);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 60);

-- Usuario 7
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('isabel@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Isabel', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'isabel@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'isabel@mail.com');

-- Productos para Isabel
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (61, 'Tablet Android', 'Tablet de 10 pulgadas Android', 'images-server/61.jpeg', 179.99, 9, true,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 61);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (62, 'Jarrón Cerámica', 'Jarrón decorativo de cerámica', 'images-server/62.jpeg', 42.99, 14, false,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Decoración', 62);
INSERT INTO product_category (category, product)
VALUES ('Hogar', 62);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (63, 'Libro Cocina Vegana', 'Libro de recetas veganas', 'images-server/63.jpeg', 29.50, 22, true,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 63);
INSERT INTO product_category (category, product)
VALUES ('Personal', 63);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (64, 'Auriculares Inalámbricos', 'Auriculares deportivos inalámbricos', 'images-server/64.jpeg', 65.99, 11,
        false, 'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 64);
INSERT INTO product_category (category, product)
VALUES ('Personal', 64);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (65, 'Set Té', 'Set completo de té con tetera', 'images-server/65.jpeg', 38.75, 8, true, 'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 65);
INSERT INTO product_category (category, product)
VALUES ('Personal', 65);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (66, 'Póster Arte', 'Póster de arte moderno enmarcado', 'images-server/66.jpeg', 24.99, 25, false,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Decoración', 66);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (67, 'Videojuego RPG', 'Juego de rol para consolas', 'images-server/67.jpeg', 54.99, 7, true, 'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 67);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (68, 'Organizador Cocina', 'Organizador de utensilios de cocina', 'images-server/68.jpeg', 31.50, 19, false,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 68);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (69, 'Libro Curso Fotografía', 'Curso de fotografía digital', 'images-server/69.jpeg', 89.00, 45, true,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 69);
INSERT INTO product_category (category, product)
VALUES ('Personal', 69);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (70, 'Reloj Smart', 'Reloj inteligente con monitor cardiaco', 'images-server/70.jpeg', 125.99, 6, true,
        'isabel@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 70);
INSERT INTO product_category (category, product)
VALUES ('Personal', 70);

-- Usuario 8
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('javier@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Javier', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'javier@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'javier@mail.com');

-- Productos para Javier
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (71, 'Disco Duro Externo', 'Disco duro 1TB USB 3.0', 'images-server/71.jpeg', 69.99, 13, true,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 71);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (72, 'Mesa Centro', 'Mesa de centro moderna', 'images-server/72.jpeg', 145.99, 3, false, 'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 72);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 72);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (73, 'Libro Historia', 'Libro de historia universal', 'images-server/73.jpeg', 32.75, 17, true,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 73);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (74, 'Ratón Gaming', 'Ratón para gaming profesional', 'images-server/74.jpeg', 52.99, 10, false,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 74);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 74);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (75, 'Lámpara Exterior', 'Lámpara solar para jardín', 'images-server/75.jpeg', 47.50, 8, true,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 75);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 75);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (76, 'Juego Cartas', 'Juego de cartas coleccionables', 'images-server/76.jpeg', 28.99, 30, false,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 76);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (77, 'Almohada Memory', 'Almohada con memory foam', 'images-server/77.jpeg', 39.99, 21, true, 'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 77);
INSERT INTO product_category (category, product)
VALUES ('Personal', 77);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (78, 'Libro Curso Marketing', 'Curso de marketing digital', 'images-server/78.jpeg', 120.00, 35, true,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 78);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (79, 'Cargador Múltiple', 'Cargador múltiple USB-C', 'images-server/79.jpeg', 34.99, 26, false,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 79);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (80, 'Figura Decorativa', 'Figura decorativa de resina', 'images-server/80.jpeg', 22.50, 15, true,
        'javier@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Decoración', 80);
INSERT INTO product_category (category, product)
VALUES ('Personal', 80);

-- Usuario 9
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('lucia@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Lucia', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'lucia@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'lucia@mail.com');

-- Productos para Lucia
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (81, 'Portátil Ultraligero', 'Laptop ultraligera 13 pulgadas', 'images-server/81.jpeg', 899.99, 2, true,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 81);
INSERT INTO product_category (category, product)
VALUES ('Académico', 81);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (82, 'Tapete Yoga', 'Tapete profesional para yoga', 'images-server/82.jpeg', 35.99, 16, false, 'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 82);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (83, 'Estuche Lápices', 'Estuche organizador para lápices', 'images-server/83.jpeg', 18.50, 28, true,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 83);
INSERT INTO product_category (category, product)
VALUES ('Personal', 83);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (84, 'Altavoz Inteligente', 'Altavoz con asistente virtual', 'images-server/84.jpeg', 89.99, 7, false,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 84);
INSERT INTO product_category (category, product)
VALUES ('Hogar', 84);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (85, 'Cortina Baño', 'Cortina para baño decorativa', 'images-server/85.jpeg', 27.99, 12, true, 'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 85);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 85);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (86, 'Juego Puzzle', 'Juego de puzzle para infantes', 'images-server/86.jpeg', 14.99, 50, false,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 86);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (87, 'Libro Autoayuda', 'Libro de desarrollo personal', 'images-server/87.jpeg', 21.75, 24, true,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 87);
INSERT INTO product_category (category, product)
VALUES ('Personal', 87);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (88, 'Fundas Cojín', 'Set de fundas para cojines', 'images-server/88.jpeg', 33.50, 9, false, 'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 88);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 88);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (89, 'Curso Piano', 'Curso de piano para principiantes', 'images-server/89.jpeg', 65.00, 60, true,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 89);
INSERT INTO product_category (category, product)
VALUES ('Personal', 89);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (90, 'Cámara Instantánea', 'Cámara fotográfica instantánea', 'images-server/90.jpeg', 75.99, 5, true,
        'lucia@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 90);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 90);

-- Usuario 10
INSERT INTO _user (email, password, name, enabled, role)
VALUES ('andres@mail.com', '$2a$10$7nRkgqcbS4SLrFGB2f8TpuD8bS103lw.INU/KbUJoA8U1KKhyWzqC', 'Andres', true, 'COMMON');

INSERT INTO wallet (money, _user)
VALUES (0, 'andres@mail.com');

INSERT INTO shopping_cart (status, _user)
VALUES (true, 'andres@mail.com');

-- Productos para Andres
INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (91, 'Impresora Láser', 'Impresora láser monocromática', 'images-server/91.jpeg', 149.99, 4, true,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 91);
INSERT INTO product_category (category, product)
VALUES ('Académico', 91);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (92, 'Mesa Noche', 'Mesa de noche con cajones', 'images-server/92.jpeg', 78.50, 6, false, 'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 92);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 92);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (93, 'Libro Negocios', 'Libro sobre emprendimiento digital', 'images-server/93.jpeg', 31.99, 18, true,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 93);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (94, 'Micrófono USB', 'Micrófono para streaming y podcasts', 'images-server/94.jpeg', 89.99, 8, false,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 94);
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 94);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (95, 'Lámpara Reading', 'Lámpara de lectura ajustable', 'images-server/95.jpeg', 42.75, 14, true,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Hogar', 95);
INSERT INTO product_category (category, product)
VALUES ('Decoración', 95);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (96, 'Videojuego Deporte', 'Juego de deportes para consola', 'images-server/96.jpeg', 49.99, 11, false,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Entretenimiento', 96);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (97, 'Mochila Viaje', 'Mochila para viajes con compartimentos', 'images-server/97.jpeg', 67.99, 9, true,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Personal', 97);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (98, 'Curso Diseño', 'Curso de diseño gráfico', 'images-server/98.jpeg', 95.00, 40, true, 'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Académico', 98);
INSERT INTO product_category (category, product)
VALUES ('Personal', 98);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (99, 'Router Mesh', 'Sistema de WiFi mesh para hogar', 'images-server/99.jpeg', 199.99, 3, false,
        'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Tecnología', 99);
INSERT INTO product_category (category, product)
VALUES ('Hogar', 99);

INSERT INTO product (id, name, description, image_url, price, stock, is_new, _user)
VALUES (100, 'Reloj Arena', 'Reloj de arena decorativo', 'images-server/100.jpeg', 28.50, 20, true, 'andres@mail.com');
INSERT INTO product_category (category, product)
VALUES ('Decoración', 100);
INSERT INTO product_category (category, product)
VALUES ('Personal', 100);

--- SINCRINIZAR ID'S
SELECT setval(
               pg_get_serial_sequence('product', 'id'),
               COALESCE((SELECT MAX(id) FROM product), 1),
               TRUE
       );

commit ;