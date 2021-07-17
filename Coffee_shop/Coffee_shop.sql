drop database coffee_shop;
create database coffee_shop;
use coffee_shop;
create table `drink`(
	idDrink int unsigned primary key,
    nameDrink varchar(30),
    price int unsigned not null
);

create table `Menu`(
	idMenu int unsigned primary key,
    startDate datetime,
    endDate datetime,
    idDrink int unsigned,
    foreign key (idDrink) references `drink` (idDrink)
);

create table `GetOrder`(
	idOrder int unsigned primary key,
    idDrink int unsigned,
    Quantity int unsigned,
    total int unsigned,
    foreign key (idDrink) references `Menu` (idDrink)
);

create table `Client`(
	idClient int primary key,
    `Order` int unsigned,
    `FeedBack` varchar(255),
    foreign key (`Order`) references `GetOrder` (idOrder)
);

create table `OrderList`(
	idOrder int unsigned primary key,
    `Order` int unsigned,
    foreign key (`Order`) references `GetOrder` (`idOrder`)
);

create table `FeedBack`(
	idFeedBack int unsigned primary key,
    `Feedback` int,
    foreign key (`Feedback`) references `Client` (idClient)
);

create table `Admin`(
	idAdmin int primary key,
    getFeedBack int unsigned,
    getOrderList int unsigned,
    idMenu int,
    foreign key (getFeedBack) references `FeedBack` (idFeedBack),
    foreign key (getOrderList) references `OrderList` (idOrder)
); 

create table shop(
	idAdmin int,
    idClient int,
    foreign key (idAdmin) references `Admin` (idAdmin),
    foreign key (idClient) references `Client` (idClient)
);








