package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;

import java.util.List;

public interface MessageRepository {
    List<Message> findAll();
    void save(Message entity);
}
