package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private Integer id;
    private String name;
    private User host;
    private List<Message> messages;

    public Chatroom(Integer id, String name, User host, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.host = host;
        this.messages = messages;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Chat room " + name + ": id = " + id + "; host = " + host + "; messages: " + messages;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Chatroom room = (Chatroom) obj;
        return id == room.id && name.equals(room.name) && host.equals(room.host)
                && messages.equals(room.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, host, messages);
    }
}
