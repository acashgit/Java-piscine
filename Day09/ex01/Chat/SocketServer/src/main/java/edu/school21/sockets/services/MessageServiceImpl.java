package edu.school21.sockets.services;


import edu.school21.sockets.models.Message;
import edu.school21.sockets.repositories.MessageRepository;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class MessageServiceImpl implements MessageService{
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void saveMessage(String login, String text) {
        Message message = new Message(usersRepository.findByEmail(login).get(), text, LocalDateTime.now());
        messageRepository.save(message);
    }
}
