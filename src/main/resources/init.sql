drop schema if exists crypto_demo_test;
create schema if not exists crypto_demo_test;

drop table if exists crypto_demo_test.users;
create table if not exists crypto_demo_test.users
(
    id   serial unique,
    name varchar(20) not null unique,
    primary key (id),
    unique index idx_users_id_unique (id asc),
    unique index idx_users_name_unique (name asc)
);

drop table if exists crypto_demo_test.cryptos;
create table if not exists crypto_demo_test.cryptos
(
    id                 bigint unsigned   not null,
    name               varchar(20)       not null unique,
    name_id            varchar(20)       not null unique,
    symbol             varchar(5)        not null unique,
    btc_rank           smallint unsigned not null,
    price_usd          numeric(30, 2)    not null,
    percent_change_24h numeric(30, 2)    not null,
    percent_change_1h  numeric(30, 2)    not null,
    percent_change_7d  numeric(30, 2)    not null,
    market_cap_usd     numeric(30, 2)    not null,
    volume_24          numeric(30, 2)    not null,
    volume_24_native   numeric(30, 2)    not null,
    csupply            numeric(30, 2),
    price_btc          numeric(30, 2)    not null,
    tsupply            numeric(30, 2),
    msupply            numeric(30, 2),
    primary key (id),
    unique index idx_cryptos_id_unique (id asc),
    unique index idx_cryptos_name_unique (name asc),
    unique index idx_cryptos_name_id_unique (name_id asc),
    unique index idx_cryptos_symbol_unique (symbol asc)
);

drop table if exists crypto_demo_test.user_cryptos;
create table if not exists crypto_demo_test.user_cryptos
(
    id                serial unique,
    user_id           bigint unsigned not null,
    crypto_id         bigint unsigned not null,
    current_price     numeric         not null,
    first_time_reg    timestamp       not null,
    price_update_date timestamp,
    primary key (id),
    unique index idx_user_cryptos_id_unique (id asc),
    constraint fk_user_cryptos_user_id_users_id
        foreign key (user_id) references crypto_demo_test.users (id) on update cascade on delete cascade,
    constraint fk_user_cryptos_crypto_id_cryptos_id
        foreign key (crypto_id) references crypto_demo_test.cryptos (id) on update cascade on delete cascade
);