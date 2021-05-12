create table "user" (
    ID serial primary key,
    name varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) unique not null,
    phone varchar(16) not null
);