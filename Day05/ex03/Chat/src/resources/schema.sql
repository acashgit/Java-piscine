DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE chat.Users (
                            id SERIAL PRIMARY KEY,
                            login VARCHAR(40) UNIQUE,
                            password VARCHAR(40)
);

CREATE TABLE chat.ChatRooms (
                                id SERIAL PRIMARY KEY,
                                name VARCHAR(40) NOT NULL ,
                                ownerId INT NOT NULL,
                                FOREIGN KEY (ownerId) REFERENCES chat.Users(id)
);

CREATE TABLE chat.Messages (
                               id SERIAL PRIMARY KEY,
                               authorId INT NOT NULL ,
                               chatRoomId INT NOT NULL ,
                               text TEXT NOT NULL ,
                               time TIMESTAMP,
                               FOREIGN KEY (authorId) REFERENCES chat.Users(id),
                               FOREIGN KEY (chatRoomId) REFERENCES chat.ChatRooms(id)
);