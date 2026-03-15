use spjain;

INSERT INTO Authors (FirstName, LastName, Email)
VALUES 
('John', 'Doe', 'john.doe@example.com'),
('Jane', 'Smith', 'jane.smith@example.com'),
('Bob', 'Johnson', 'bob.johnson@example.com');

INSERT INTO Books (Title, AuthorID, PublicationDate)
VALUES 
('The Great Book', 1, '2020-01-01'),
('Another Great Book', 1, '2021-01-01'),
('A Book by Jane', 2, '2020-06-01'),
('A Book by Bob', 3, '2022-01-01');

INSERT INTO Reviews (BookID, ReviewerName, ReviewText, Rating)
VALUES 
(1, 'Alice', 'This book is amazing!', 5),
(1, 'Charlie', 'This book is okay.', 3),
(2, 'David', 'I loved this book!', 5),
(3, 'Eve', 'This book is great.', 4),
(4, 'Frank', 'This book is terrible.', 1);
