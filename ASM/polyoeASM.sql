create database PolyOEASM;
use PolyOEASM;
-- Create User table
CREATE TABLE Users (
    Id VARCHAR(255) PRIMARY KEY,
    Password VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL UNIQUE,
    Fullname NVARCHAR(255) NOT NULL,
    Admin BIT NOT NULL
);

-- Create Video table
CREATE TABLE Videos (
    Id VARCHAR(255) PRIMARY KEY,
    Title NVARCHAR(255) NOT NULL,
	Link varchar(255),
    Poster NVARCHAR(255),
    Views INT NOT NULL DEFAULT 0,
    Description NVARCHAR(MAX),
    Active BIT NOT NULL DEFAULT 1
);

-- Create Favorite table
CREATE TABLE Favorites (
    Id BIGINT PRIMARY KEY IDENTITY(1,1),
    UserId VARCHAR(255) NOT NULL,
    VideoId VARCHAR(255) NOT NULL,
    LikeDate DATE NOT NULL,
    CONSTRAINT UC_Favorite UNIQUE (UserId, VideoId),
    CONSTRAINT FK_Favorite_User FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_Favorite_Video FOREIGN KEY (VideoId) REFERENCES Videos(Id)
);

-- Create Share table
CREATE TABLE Shares(
    Id BIGINT PRIMARY KEY IDENTITY(1,1),
    UserId VARCHAR(255) NOT NULL,
    VideoId VARCHAR(255) NOT NULL,
    Emails VARCHAR(255) NOT NULL,
    ShareDate DATE NOT NULL,
    CONSTRAINT FK_Share_User FOREIGN KEY (UserId) REFERENCES Users(Id),
    CONSTRAINT FK_Share_Video FOREIGN KEY (VideoId) REFERENCES Videos(Id)
);
-- Thêm dữ liệu vào bảng User
INSERT INTO Users (Id, Password, Email, Fullname, Admin) VALUES
('user01', 'pass123', 'nam@gmail.com', N'Nguyễn Thành Nam', 1),
('user02', 'pass123', 'huong@gmail.com', N'Trần Thanh Hương', 0),
('user03', 'pass123', 'minh@gmail.com', N'Lê Công Minh', 0),
('user04', 'pass123', 'loan@gmail.com', N'Phạm Thị Loan', 0),
('user05', 'pass123', 'tuan@gmail.com', N'Hoàng Văn Tuấn', 1);
INSERT INTO Videos (Id, Title, Link, Poster, Views, Description, Active) VALUES
('video1', 'Believer', 'https://www.youtube.com/watch?v=7wtfhZwyrcc', 'https://i.ytimg.com/vi/7wtfhZwyrcc/hqdefault.jpg', 2847, 'Believer by Imagine Dragons', 1),
('video2', 'Shape of You', 'https://www.youtube.com/watch?v=JGwWNGJdvx8', 'https://i.ytimg.com/vi/JGwWNGJdvx8/hqdefault.jpg', 5544, 'Shape of You by Ed Sheeran', 1),
('video3', 'Despacito', 'https://www.youtube.com/watch?v=kJQP7kiw5Fk', 'https://i.ytimg.com/vi/kJQP7kiw5Fk/hqdefault.jpg', 7123, 'Despacito by Luis Fonsi, Daddy Yankee', 1),
('video4', 'One Dance', 'https://www.youtube.com/watch?v=Ns41dfuaKmQ', 'https://i.ytimg.com/vi/Ns41dfuaKmQ/hqdefault.jpg', 2342, 'One Dance by Drake, Wizkid, Kyla', 1),
('video5', 'Sorry', 'https://www.youtube.com/watch?v=fRh_vgS2dFE', 'https://i.ytimg.com/vi/fRh_vgS2dFE/hqdefault.jpg', 4567, 'Sorry by Justin Bieber', 1),
('video6', 'Hello', 'https://www.youtube.com/watch?v=YQHsXMglC9A', 'https://i.ytimg.com/vi/YQHsXMglC9A/hqdefault.jpg', 3456, 'Hello by Adele', 1),
('video7', 'Uptown Funk', 'https://www.youtube.com/watch?v=OPf0YbXqDm0', 'https://i.ytimg.com/vi/OPf0YbXqDm0/hqdefault.jpg', 3456, 'Uptown Funk by Mark Ronson ft. Bruno Mars', 1),
('video8', 'Gangnam Style', 'https://www.youtube.com/watch?v=9bZkp7q19f0', 'https://i.ytimg.com/vi/9bZkp7q19f0/hqdefault.jpg', 3456, 'Gangnam Style by Psy', 1),
('video9', 'Starboy', 'https://www.youtube.com/watch?v=34Na4j8AVgA', 'https://i.ytimg.com/vi/34Na4j8AVgA/hqdefault.jpg', 2345, 'Starboy by The Weeknd', 1),
('video10', 'See You Again', 'https://www.youtube.com/watch?v=RgKAFK5djSk', 'https://i.ytimg.com/vi/RgKAFK5djSk/hqdefault.jpg', 3456, 'See You Again by Wiz Khalifa ft. Charlie Puth', 1);