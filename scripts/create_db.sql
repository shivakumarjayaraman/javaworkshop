create database if not exists spjain;
CREATE USER if not exists 'spjain'@'%' IDENTIFIED BY 'spjain';
grant all privileges on spjain.* to 'spjain'@'%';

use spjain;

CREATE TABLE Authors (
    AuthorID INT AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY (AuthorID)
);

CREATE TABLE Books (
    BookID INT AUTO_INCREMENT,
    Title VARCHAR(200) NOT NULL,
    AuthorID INT NOT NULL,
    PublicationDate DATE NOT NULL,
    PRIMARY KEY (BookID),
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID)
);

CREATE TABLE Reviews (
    ReviewID INT AUTO_INCREMENT,
    BookID INT NOT NULL,
    ReviewerName VARCHAR(100) NOT NULL,
    ReviewText TEXT NOT NULL,
    Rating INT NOT NULL CHECK (Rating BETWEEN 1 AND 5),
    PRIMARY KEY (ReviewID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

