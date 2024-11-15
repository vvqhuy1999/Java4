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
    Id Nvarchar(10) PRIMARY KEY,  
    Password NVARCHAR(50) NOT NULL,  
    Email NVARCHAR(100) NOT NULL unique,  
    Fullname NVARCHAR(100) NOT NULL,  
    Admin BIT NOT NULL  
);  

-- Create Video table  
CREATE TABLE Videos (  
    Id Nvarchar(10) PRIMARY KEY,  
    Title NVARCHAR(100) NOT NULL,  
    Poster NVARCHAR(255) NOT NULL,
	Link NVARCHAR(255) NOT NULL,
    Views INT,  
    Description NVARCHAR(MAX),  
    Active BIT  
);  

-- Create Favorite table  
CREATE TABLE Favorites (  
    Id INT IDENTITY(1,1) PRIMARY KEY,  
    UserId Nvarchar(10) NOT NULL,  
    VideoId Nvarchar(10) NOT NULL,  
    LikeDate DATE  
);  

-- Create Share table  
CREATE TABLE Shares (  
    Id INT IDENTITY(1,1) PRIMARY KEY,  
    UserId Nvarchar(10) NOT NULL,  
    VideoId Nvarchar(10) NOT NULL,  
    Emails NVARCHAR(100) NOT NULL,  
    ShareDate DATE  
);  

-- Insert Users  
INSERT INTO [Users] (Id, Password, Email, Fullname, Admin) VALUES  
('US001', '123456', 'admin@gmail.com', 'Administrator', 1),  
('US002', '123456', 'user1@gmail.com', 'John Doe', 0),  
('US003', '123456', 'user2@gmail.com', 'Jane Smith', 0),  
('US004', '123456', 'user3@gmail.com', 'Robert Johnson', 0),
('US005', '123456', 'user4@gmail.com', 'Emily Davis', 0),  
('US006', '123456', 'user5@gmail.com', 'Michael Brown', 0),  
('US007', '123456', 'user6@gmail.com', 'Jessica Wilson', 0); 

-- Insert Videos  
INSERT INTO Videos (Id, Title, Poster, Link, Views, Description, Active) VALUES  
('VID001', 'Bruno Mars - The Lazy Song', 'https://img.youtube.com/vi/fLexgOxsZu0/maxresdefault.jpg', 'https://youtu.be/fLexgOxsZu0?list=RDfLexgOxsZu0', 0, N'Official music video for Bruno Mars - The Lazy Song', 1),  
('VID002', 'Wiz Khalifa - See You Again ft. Charlie Puth', 'https://img.youtube.com/vi/RgKAFK5djSk/maxresdefault.jpg', 'https://youtu.be/RgKAFK5djSk?list=RDfLexgOxsZu0', 0, N'Official music video for Wiz Khalifa - See You Again featuring Charlie Puth', 1),  
('VID003', 'Maroon 5 - Payphone ft. Wiz Khalifa', 'https://img.youtube.com/vi/KRaWnd3LJfs/maxresdefault.jpg', 'https://youtu.be/KRaWnd3LJfs?list=RDfLexgOxsZu0', 0, N'Official music video for Maroon 5 - Payphone featuring Wiz Khalifa', 1),  
('VID004', 'Maroon 5 - Girls Like You ft. Cardi B', 'https://img.youtube.com/vi/fwK7ggA3-bU/maxresdefault.jpg', 'https://youtu.be/fwK7ggA3-bU?list=RDfLexgOxsZu0', 0, N'Official music video for Maroon 5 - Girls Like You featuring Cardi B', 1),  
('VID005', 'Maroon 5 - Sugar', 'https://img.youtube.com/vi/SlPhMPnQ58k/maxresdefault.jpg', 'https://youtu.be/SlPhMPnQ58k?list=RDfLexgOxsZu0', 0, N'Official music video for Maroon 5 - Sugar', 1),  
('VID006', 'Maroon 5 - One More Night', 'https://img.youtube.com/vi/WA4iX5D9Z64/maxresdefault.jpg', 'https://youtu.be/WA4iX5D9Z64?list=RDfLexgOxsZu0', 0, N'Official music video for Maroon 5 - One More Night', 1),  
('VID007', 'Maroon 5 - Memories', 'https://img.youtube.com/vi/_Z5-P9v3F8w/maxresdefault.jpg', 'https://youtu.be/_Z5-P9v3F8w?list=RDfLexgOxsZu0', 0, 'Official music video for Maroon 5 - Memories', 1),  
('VID008', 'Justin Bieber - Baby ft. Ludacris', 'https://img.youtube.com/vi/kffacxfA7G4/maxresdefault.jpg', 'https://youtu.be/kffacxfA7G4?list=RDfLexgOxsZu0', 0, 'Official music video for Justin Bieber - Baby featuring Ludacris', 1),  
('VID009', 'Maroon 5 - Moves Like Jagger ft. Christina Aguilera', 'https://img.youtube.com/vi/Ys7-6_t7OEQ/maxresdefault.jpg', 'https://youtu.be/Ys7-6_t7OEQ?list=RDfLexgOxsZu0', 0, 'Official music video for Maroon 5 - Moves Like Jagger featuring Christina Aguilera', 1),
('VID010','Mark Ronson - Uptown Funk ft. Bruno Mars', 'https://img.youtube.com/vi/OPf0YbXqDm0/maxresdefault.jpg','https://youtu.be/OPf0YbXqDm0?list=RDMMfLexgOxsZu0',0,'Official music video for Uptown Funk by Mark Ronson featuring Bruno Mars.',1),
('VID011', 'HIEUTHUHAI - TRÌNH', 'https://img.youtube.com/vi/7kO_ALcwNAw/maxresdefault.jpg', 'https://youtu.be/7kO_ALcwNAw', 0, N'Official music video TRÌNH by HIEUTHUHAI, produced by Kewtiie', 1),  

('VID012', 'HIEUTHUHAI - Exit Sign ft. marzuz', 'https://img.youtube.com/vi/sJt_i0hOugA/maxresdefault.jpg', 'https://youtu.be/sJt_i0hOugA', 0, N'Official music video Exit Sign by HIEUTHUHAI featuring marzuz', 1),  

('VID013', N'Phải là yêu', 'https://img.youtube.com/vi/-veZZSIJ7hI/maxresdefault.jpg', 'https://youtu.be/-veZZSIJ7hI', 0, N'Official music video Phải là yêu', 1),  

('VID014', 'CUA', 'https://img.youtube.com/vi/P8qEusQiwsw/maxresdefault.jpg', 'https://www.youtube.com/watch?v=P8qEusQiwsw', 0, N'Official music video CUA', 1),  

('VID015', N'HURRYKNG, HIEUTHUHAI, MANBO - Hẹn Gặp Em Dưới Ánh Trăng', 'https://img.youtube.com/vi/dLmczwDCEZI/maxresdefault.jpg', 'https://youtu.be/dLmczwDCEZI', 0, N'Official music video Hẹn Gặp Em Dưới Ánh Trăng by HURRYKNG, HIEUTHUHAI, MANBO', 1);



-- Insert Favorites (Sample data)  
INSERT INTO Favorites (UserId, VideoId, LikeDate) VALUES  
('US001', 'VID001', '2024-02-07'),  
('US002', 'VID001', '2024-02-08'),  
('US003', 'VID002', '2024-02-09'),  
('US004', 'VID002', '2024-02-10'),  
('US005', 'VID003', '2024-02-11'),  
('US006', 'VID003', '2024-02-12'),  
('US007', 'VID004', '2024-02-13'),  
('US001', 'VID004', '2024-02-14'),  
('US002', 'VID005', '2024-02-15'),  
('US003', 'VID005', '2024-02-16'),  
('US004', 'VID006', '2024-02-17'),  
('US005', 'VID006', '2024-02-18'),  
('US006', 'VID007', '2024-02-19'),  
('US007', 'VID007', '2024-02-20'),  
('US001', 'VID008', '2024-02-21'),  
('US002', 'VID008', '2024-02-22'),  
('US003', 'VID009', '2024-02-23'),  -- Giữ lại cái này  
('US004', 'VID009', '2024-02-24'),  
('US005', 'VID010', '2024-02-25'),  
('US006', 'VID010', '2024-02-26'),  
('US007', 'VID011', '2024-02-27'),  
('US001', 'VID011', '2024-02-28'),  
('US002', 'VID012', '2024-03-01'),  
('US003', 'VID012', '2024-03-02'),  
('US004', 'VID013', '2024-03-03'),  
('US005', 'VID013', '2024-03-04'),  
('US006', 'VID014', '2024-03-05'),  
('US007', 'VID014', '2024-03-06'),  
('US001', 'VID015', '2024-03-07'),  
('US002', 'VID015', '2024-03-08'),  
('US003', 'VID001', '2024-03-09'),  
('US004', 'VID003', '2024-03-10'),  
('US005', 'VID004', '2024-03-11'),  
('US006', 'VID005', '2024-03-12'),  
('US007', 'VID006', '2024-03-13'),  
('US001', 'VID007', '2024-03-14'),  
('US003', 'VID008', '2024-03-15'),  
('US005', 'VID009', '2024-03-16'),  -- Đã thay đổi từ US003 thành US005  
('US004', 'VID010', '2024-03-17'),  
('US005', 'VID011', '2024-03-18'),  
('US006', 'VID012', '2024-03-19'),  
('US007', 'VID013', '2024-03-20'),  
('US001', 'VID014', '2024-03-21'),  
('US004', 'VID015', '2024-03-22');

-- Insert Shares (Sample data)  
-- Ensure each video has at least 2 shares  
INSERT INTO Shares (UserId, VideoId, Emails, ShareDate) VALUES  
('US001', 'VID001', 'friend13@gmail.com', '2024-02-07'),  
('US002', 'VID001', 'friend14@gmail.com', '2024-02-08'),  
('US003', 'VID002', 'friend15@gmail.com', '2024-02-09'),  
('US004', 'VID002', 'friend16@gmail.com', '2024-02-10'),  
('US005', 'VID003', 'friend17@gmail.com', '2024-02-11'),  
('US006', 'VID003', 'friend18@gmail.com', '2024-02-12'),  
('US007', 'VID004', 'friend19@gmail.com', '2024-02-13'),  
('US001', 'VID004', 'friend20@gmail.com', '2024-02-14'),  
('US002', 'VID005', 'friend21@gmail.com', '2024-02-15'),  
('US003', 'VID005', 'friend22@gmail.com', '2024-02-16'),  
('US004', 'VID006', 'friend23@gmail.com', '2024-02-17'),  
('US005', 'VID006', 'friend24@gmail.com', '2024-02-18'),  
('US006', 'VID007', 'friend25@gmail.com', '2024-02-19'),  
('US007', 'VID007', 'friend26@gmail.com', '2024-02-20'),  
('US001', 'VID008', 'friend27@gmail.com', '2024-02-21'),  
('US002', 'VID008', 'friend28@gmail.com', '2024-02-22'),  
('US003', 'VID009', 'friend29@gmail.com', '2024-02-23'),  
('US004', 'VID009', 'friend30@gmail.com', '2024-02-24'),  
('US005', 'VID010', 'friend31@gmail.com', '2024-02-25'),  -- Giữ lại cái này  
('US006', 'VID010', 'friend32@gmail.com', '2024-02-26'),  
('US007', 'VID011', 'friend33@gmail.com', '2024-02-27'),  
('US001', 'VID011', 'friend34@gmail.com', '2024-02-28'),  
('US002', 'VID012', 'friend35@gmail.com', '2024-03-01'),  
('US003', 'VID012', 'friend36@gmail.com', '2024-03-02'),  
('US004', 'VID013', 'friend37@gmail.com', '2024-03-03'),  
('US005', 'VID013', 'friend38@gmail.com', '2024-03-04'),  
('US006', 'VID014', 'friend39@gmail.com', '2024-03-05'),  
('US007', 'VID014', 'friend40@gmail.com', '2024-03-06'),  
('US001', 'VID015', 'friend41@gmail.com', '2024-03-07'),  
('US002', 'VID015', 'friend42@gmail.com', '2024-03-08'),  
('US003', 'VID001', 'friend43@gmail.com', '2024-03-09'),  
('US007', 'VID002', 'friend44@gmail.com', '2024-03-10'),  
('US001', 'VID003', 'friend45@gmail.com', '2024-03-11'),  
('US006', 'VID004', 'friend46@gmail.com', '2024-03-12'),  
('US007', 'VID005', 'friend47@gmail.com', '2024-03-13'),  
('US001', 'VID006', 'friend48@gmail.com', '2024-03-14'),  
('US002', 'VID007', 'friend49@gmail.com', '2024-03-15'),  
('US003', 'VID008', 'friend50@gmail.com', '2024-03-16'),  
('US002', 'VID009', 'friend51@gmail.com', '2024-03-17'),  
('US004', 'VID010', 'friend52@gmail.com', '2024-03-18'),  -- Thay đổi từ US005 thành US004  
('US003', 'VID011', 'friend53@gmail.com', '2024-03-19'),  -- Thay đổi từ US006 thành US003  
('US004', 'VID012', 'friend54@gmail.com', '2024-03-20'),  -- Thay đổi từ US007 thành US004  
('US002', 'VID013', 'friend55@gmail.com', '2024-03-21'),  -- Thay đổi từ US001 thành US002  
('US002', 'VID014', 'friend56@gmail.com', '2024-03-22'),  
('US003', 'VID015', 'friend57@gmail.com', '2024-03-23');


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
ADD CONSTRAINT FK_Favorite_Videos FOREIGN KEY (VideoId) REFERENCES Videos(Id);  

-- Unique constraint for Shares 
ALTER TABLE Shares  
ADD CONSTRAINT UC_Share UNIQUE (UserId, VideoId); 

-- Foreign key constraints for Share table  
ALTER TABLE Shares  
ADD CONSTRAINT FK_Share_User FOREIGN KEY (UserId) REFERENCES [Users](Id);  

ALTER TABLE Shares  
ADD CONSTRAINT FK_Share_Video FOREIGN KEY (VideoId) REFERENCES Videos(Id);


select * from Users;
select * from Favorites;
select * from Videos;
select * from Shares;