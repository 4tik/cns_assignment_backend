drop table if exists users;
create table users(
    id  serial primary key not null,
    user_name  varchar(50)  UNIQUE,
    email      varchar(100) UNIQUE,
    password   varchar(250),
    created_on timestamp
);