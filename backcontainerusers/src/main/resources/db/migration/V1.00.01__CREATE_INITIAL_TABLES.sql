CREATE TABLE IF NOT EXISTS tb_user(
    id bigint(20) auto_increment primary key,
    name_user varchar(50) not null,
    birthday_date date not null,
    city varchar(50) not null,
    uf varchar(2) not null,
    description varchar(200),
    max_age Date not null,
    minimum_age Date not null,
    zodiac varchar(25) default null,
    intention varchar(50) not null,
    confirmed_acc TINYINT,
    disabled TINYINT,
    gender varchar(20),
    prefer_gender varchar(30),
    max_distance int not null,
    email varchar(255) unique not null,
    password varchar(255) not null,
    location_latitude DECIMAL(10,8),
    location_longitude DECIMAL(10,8),
    Country varchar(50)
);

