package edu.school21.sockets.services;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface MessageService {
    void saveMessage(String login, String text);
}
