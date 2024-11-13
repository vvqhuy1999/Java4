-- Create database  
CREATE DATABASE PolyOE;  
GO  
/*
USE master;  
GO  
ALTER DATABASE PolyOE SET SINGLE_USER WITH ROLLBACK IMMEDIATE;  
GO 
DROP DATABASE PolyOE;  
*/
USE PolyOE;  
GO  

-- Create User table  
CREATE TABLE [Users] (  
    Id varchar(10) PRIMARY KEY,  
    Password VARCHAR(50) NOT NULL,  
    Email VARCHAR(100) NOT NULL unique,  
    Fullname VARCHAR(100) NOT NULL,  
    Admin BIT NOT NULL  
);  

-- Create Video table  
CREATE TABLE Videos (  
    Id varchar(10) PRIMARY KEY,  
    Title VARCHAR(100) NOT NULL,  
    Poster VARCHAR(255) NOT NULL,  
    Views INT,  
    Description VARCHAR(MAX),  
    Active BIT  
);  

-- Create Favorite table  
CREATE TABLE Favorites (  
    Id INT IDENTITY(1,1) PRIMARY KEY,  
    UserId varchar(10) NOT NULL,  
    VideoId varchar(10) NOT NULL,  
    LikeDate DATE  
);  

-- Create Share table  
CREATE TABLE Shares (  
    Id INT IDENTITY(1,1) PRIMARY KEY,  
    UserId varchar(10) NOT NULL,  
    VideoId varchar(10) NOT NULL,  
    Emails VARCHAR(100) NOT NULL,  
    ShareDate DATE  
);  

-- Insert Users  
INSERT INTO [Users] (Id, Password, Email, Fullname, Admin) VALUES  
('US001', '123456', 'admin@gmail.com', 'Administrator', 1),  
('US002', '123456', 'user1@gmail.com', 'John Doe', 0),  
('US003', '123456', 'user2@gmail.com', 'Jane Smith', 0),  
('US004', '123456', 'user3@gmail.com', 'Robert Johnson', 0);  

-- Insert Videos  
INSERT INTO Videos (Id, Title, Poster, Views, Description, Active) VALUES  
('VD001', 'Girls Like You - Maroon 5 ft. Cardi B', 'https://img.youtube.com/vi/fwK7ggA3-bU/maxresdefault.jpg', 0, 'Music video by Maroon 5 performing Girls Like You ft. Cardi B', 1),  
('VD002', 'Sugar - Maroon 5', 'https://img.youtube.com/vi/SlPhMPnQ58k/maxresdefault.jpg', 0, 'Music video by Maroon 5 performing Sugar', 1),  
('VD003', 'One More Night - Maroon 5', 'https://img.youtube.com/vi/WA4iX5D9Z64/maxresdefault.jpg', 0, 'Music video by Maroon 5 performing One More Night', 1),  
('VD004', 'Memories - Maroon 5', 'https://img.youtube.com/vi/_Z5-P9v3F8w/maxresdefault.jpg', 0, 'Music video by Maroon 5 performing Memories', 1),  
('VD005', 'Baby - Justin Bieber ft. Ludacris', 'https://img.youtube.com/vi/kffacxfA7G4/maxresdefault.jpg', 0, 'Music video by Justin Bieber performing Baby ft. Ludacris', 1),  
('VD006', 'Moves Like Jagger - Maroon 5 ft. Christina Aguilera', 'https://img.youtube.com/vi/Ys7-6_t7OEQ/maxresdefault.jpg', 0, 'Music video by Maroon 5 performing Moves Like Jagger ft. Christina Aguilera', 1);  

-- Insert Favorites (Sample data)  
INSERT INTO Favorites (UserId, VideoId, LikeDate) VALUES  
( 'US002', 'VD001', '2023-01-15'),  
( 'US002', 'VD002', '2023-01-16'),  
( 'US003', 'VD001', '2023-01-17'),  
('US003', 'VD003', '2023-01-18'),  
( 'US004', 'VD004', '2023-01-19'),  
('US004', 'VD005', '2023-01-20');  

-- Insert Shares (Sample data)  
INSERT INTO Shares ( UserId, VideoId, Emails, ShareDate) VALUES  
( 'US002', 'VD001', 'friend1@gmail.com', '2023-01-15'),  
( 'US002', 'VD002', 'friend2@gmail.com', '2023-01-16'),  
( 'US003', 'VD003', 'friend3@gmail.com', '2023-01-17'),  
( 'US003', 'VD004', 'friend4@gmail.com', '2023-01-18'),  
( 'US004', 'VD005', 'friend5@gmail.com', '2023-01-19'),  
( 'US004', 'VD006', 'friend6@gmail.com', '2023-01-20'); 


-- Add constraints  
-- Unique constraint for User email  
ALTER TABLE [Users]  
ADD CONSTRAINT UC_User_Email UNIQUE (Email);  

-- Unique constraint for Favorite  

ALTER TABLE Favorites  
ADD CONSTRAINT UC_Favorite UNIQUE (UserId, VideoId);  


-- Foreign key constraints for Favorite table  
ALTER TABLE Favorites  
ADD CONSTRAINT FK_Favorite_User FOREIGN KEY (UserId) REFERENCES [Users](Id);  

ALTER TABLE Favorites  
ADD CONSTRAINT FK_Favorite_Video FOREIGN KEY (VideoId) REFERENCES Videos(Id);  

-- Unique constraint for Shares 
ALTER TABLE Shares  
ADD CONSTRAINT UC_Share UNIQUE (UserId, VideoId); 

-- Foreign key constraints for Share table  
ALTER TABLE Shares  
ADD CONSTRAINT FK_Share_User FOREIGN KEY (UserId) REFERENCES [Users](Id);  

ALTER TABLE Shares  
ADD CONSTRAINT FK_Share_Video FOREIGN KEY (VideoId) REFERENCES Videos(Id);


select * from Users
select * from Videos
select * from Favorites
select * from Shares