package edu.school21.chat.app;


import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.repositories.*;

import java.sql.SQLException;
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
        try {
            System.out.println(messagesRepository.findById(scanner.nextLong()));
            System.out.println(userRepositories.findById(scanner.nextLong()));
            System.out.println(chatRoomRepository.findById(scanner.nextLong()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
