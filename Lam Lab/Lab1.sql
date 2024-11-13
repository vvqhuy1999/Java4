create database PolyOEUser;

go
use PolyOEUser;
go

create table Users(
	Id nvarchar(20) primary key not null ,
	Password nvarchar(50) not null,
	Fullname nvarchar(50) not null,
	Email nvarchar(50) not null,
	Admin bit not null,
);
INSERT INTO Users (Id, Password, Fullname, Email, Admin) VALUES
('user1', 'password1', 'User One', 'user1@example.com', 1),
('user2', 'password2', 'User Two', 'user2@example.com', 0),
('user3', 'password3', 'User Three', 'user3@example.com', 1),
('user4', 'password4', 'User Four', 'user4@example.com', 0);



-- SELECT * FROM Users
-- WHERE CAST(SUBSTRING(id, 5, LEN(id) - 4) AS INT) BETWEEN 10 AND 15;



--select * from Users

INSERT INTO Users (id, fullname, email, password, admin) VALUES
('user6', 'Jane Smith', 'jane.smith@example.com', 'pass456', 1),
('user7', 'Alex Johnson', 'alex.johnson@example.com', 'securepass', 0),
('user8', 'Emily Davis', 'emily.davis@example.com', 'emily123', 1),
('user9', 'Michael Brown', 'michael.brown@example.com', 'mikepass', 0),
('user10', 'Olivia Wilson', 'olivia.wilson@example.com', 'olivia456', 1),
('user11', 'William Taylor', 'william.taylor@example.com', 'will123', 0),
('user12', 'Sophia Anderson', 'sophia.anderson@example.com', 'sophiapass', 1),
('user13', 'James Martin', 'james.martin@example.com', 'james456', 0),
('user14', 'Ava Thompson', 'ava.thompson@example.com', 'avapass', 1),
('user15', 'John Doe', 'john.doe@example.com', 'password123', 0);