create table currencies (
id BIGINT(19) UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
ticker VARCHAR(255) NOT NULL,
name VARCHAR(255) NOT NULL,
number_of_coins BIGINT(19),
market_cap BIGINT(19)
);

insert into currencies values (1, 'BTC', 'Bitcoin', 16770000, 189580000000);
insert into currencies values (2, 'ETH', 'Ethereum', 96710000, 69280000000);
insert into currencies values (3, 'XRP', 'Ripple', 38590000000, 64750000000);
insert into currencies values (4, 'BCH', 'BitcoinCash', 16670000, 69020000000);