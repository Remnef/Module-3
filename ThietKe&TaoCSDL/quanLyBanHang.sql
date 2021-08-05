create table Product(
	pID int primary key,
    pName varchar(30),
    pPrice int
);

create table Customer(
	cID int primary key,
    cName varchar(30),
    cAge int
);

create table `Order`(
	oID int primary key,
    cID int,
    oDate datetime,
    oTotalPrice int,
    foreign key (cID) references `Customer` (cID)
);

create table `OrderDetail` (
	oID int,
    pID int,
    odQTY varchar(30),
    foreign key (oID) references `Order` (oID),
    foreign key (pID) references `Product` (pID)
);

