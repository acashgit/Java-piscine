package edu.school21.chat.app;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/");
        dataSource.setUsername("postgres");
        dataSource.setPassword("12345");

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        ChatRoomRepository chatRoomRepository = new ChatRoomRepositoryJdbcImpl(dataSource);
        UserRepositories userRepositories = new UserRepositoryJdbcImpl(dataSource);
        Scanner scanner = new Scanner(System.in);
        User creator = new User(2L, "user", "user", new ArrayList<>(), new ArrayList<>());
        Chatroom room = new Chatroom(3L, "room", creator, new ArrayList<>());
        Message message = new Message(null, creator, room, "Hello!", LocalDateTime.now());
        messagesRepository.save(message);
        System.out.println(message.getId());
    }
}
