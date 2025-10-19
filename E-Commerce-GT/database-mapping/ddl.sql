
drop  database if exists e_commerce_gt;

create database e_commerce_gt;

create table role(
    name varchar(50) primary key
);

create table _user (
    id serial primary key,
    email varchar(50) unique not null ,
    password text not null ,
    name varchar(50) not null ,
    enabled boolean not null default true,
    role varchar(50) not null,

    constraint fk_role_in_user
                   foreign key (role)
                   references role(name)
                   on delete cascade
                   on update cascade

);

create table wallet(
    id serial primary key ,
    money float not null ,
    _user varchar(50) unique not null ,

    constraint fk_user_in_wallet
                   foreign key (_user)
                   references _user(email)
                   on delete cascade
                   on update cascade
);

create table notification (
    id serial primary key ,
    sender varchar(50) not null ,
    context text not null
);

create table notification_sent(
    id serial primary key ,
    notification int not null ,
    _user varchar(50) not null ,

    constraint fk_notification_in_notification_sent
                              foreign key (notification)
                              references notification(id)
                              on delete cascade
                              on update cascade ,

    constraint fk_user_in_notification_sent
                              foreign key (_user)
                              references _user(email)
                              on delete cascade
                              on update cascade

);

create table credit_card(
    id serial primary key ,
    number varchar(20) not null ,
    _user varchar(50) not null ,

    constraint fk_user_in_credit_card
                        foreign key (_user)
                        references _user(email)
                        on delete  cascade
                        on update  cascade,

    constraint unique_number_and_user
                        unique (number, _user)
);

create table sanction (
    id serial primary key ,
    reason text not null ,
    is_reviewed boolean not null ,
    created_at timestamp default current_timestamp,
    _user varchar(50) not null ,

    constraint fk_user_in_sanction
                      foreign key (_user)
                      references _user(email)
                      on delete cascade
                      on update cascade
);

create table product(
    id serial primary key ,
    name varchar(50) not null ,
    description text not null ,
    image_url text not null ,
    price float not null ,
    stock int not null ,
    is_new boolean not null ,
    is_approved boolean not null default false,
    _user varchar(50) not null ,

    constraint fk_user_in_product
                    foreign key (_user)
                    references _user(email)
                    on delete cascade
                    on update cascade
);

create table comment(
    id serial primary key ,
    content text not null ,
    created_at timestamp default current_timestamp,
    product int not null ,
    _user varchar(50) not null ,

    constraint fk_product_int_comment
                    foreign key (product)
                    references product(id)
                    on delete cascade
                    on update cascade ,

    constraint fk_user_in_comment
                    foreign key (_user)
                    references _user(email)
                    on delete restrict
                    on update cascade
);

create table qualification(
    id serial primary key ,
    starts int not null ,
    product int not null ,
    _user varchar(50) not null ,

    constraint fk_product_in_qualification
                          foreign key (product)
                          references product(id)
                          on delete cascade
                          on update cascade ,

    constraint fk_user_in_qualification
                          foreign key (_user)
                          references _user(email)
                          on delete restrict
                          on update cascade
);

create table category(
    name varchar(50) primary key
);

create table product_category(
    id serial primary key ,
    category varchar(50) not null ,
    product int not null ,

    constraint fk_category_in_product_category
                             foreign key (category)
                             references category(name)
                             on delete cascade
                             on update cascade ,

    constraint fk_product_in_product_category
                             foreign key (product)
                             references product(id)
                             on delete cascade
                             on update cascade
);

create table shopping_cart (
    id serial primary key ,
    status boolean not null default true,
    _user varchar(50) not null ,

    constraint fk_user_in_shopping_cart
                           foreign key (_user)
                           references _user(email)
                           on delete cascade
                           on update cascade
);

create table purchase_detail (
    id serial primary key ,
    amount int not null ,
    product int not null ,
    shopping_cart int not null ,

    constraint fk_product_in_purchase_detail
                             foreign key (product)
                             references product(id)
                             on delete cascade
                             on update cascade ,

    constraint fk_shopping_cart_in_purchase_detail
                             foreign key (shopping_cart)
                             references shopping_cart(id)
                             on delete  cascade
                             on update  cascade,

    constraint unique_product_shopping_cart
                             unique (shopping_cart, product)
);

create table package(

    id serial primary key ,
    is_delivered boolean default false not null ,
    departure_date  timestamp default current_timestamp not null ,
    delivery_date   timestamp default (current_timestamp + interval '5 days') not null ,
    shopping_cart int not null ,

    constraint fk_shopping_cart_in_package
                    foreign key (shopping_cart)
                    references shopping_cart(id)
                    on delete cascade
                    on update cascade
);
