create table if not exists socks_income_history
(
    id serial primary key,
    color varchar(50) not null,
    cotton_Part varchar(50) not null,
    quantity integer not null,
    income_time timestamp default current_timestamp
);