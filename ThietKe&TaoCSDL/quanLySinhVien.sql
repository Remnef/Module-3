create database quanLySinhVien;
use quanLySinhVien;
create table class(
	classID int auto_increment not null primary key,
    className varchar(60) not null,
    startDate datetime not null,
    `status` bit
);

create table Student(
	studentID int not null auto_increment primary key,
    studentName varchar(30) not null,
    address varchar (50),
    Phone varchar(20),
    `status` bit,
    classID int not null,
    foreign key (classID) references class(classID)
);

create table `Subject`( 
	subID int not null auto_increment primary key,
    subName varchar(30) not null,
    credit Tinyint not null default 1 check (credit >= 1),
    `status` bit default 1
);

CREATE TABLE Mark
(
    MarkId    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    SubId     INT NOT NULL,
    StudentId INT NOT NULL,
    Mark      FLOAT   DEFAULT 0 CHECK ( Mark BETWEEN 0 AND 100),
    ExamTimes TINYINT DEFAULT 1,
    UNIQUE (SubId, StudentId),
    FOREIGN KEY (SubId) REFERENCES Subject (SubId),
    FOREIGN KEY (StudentId) REFERENCES Student (StudentId)
);

