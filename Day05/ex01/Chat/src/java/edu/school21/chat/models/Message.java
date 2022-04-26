package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Integer id;
    private User user;
    private Chatroom chat;
    private String text;
    private LocalDateTime time;

    public Message(Integer id, User user, Chatroom chat, String text, LocalDateTime time){
        this.id = id;
        this.user = user;
        this.chat = chat;
        this.text = text;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chatroom getChat() {
        return chat;
    }

    public void setChat(Chatroom chat) {
        this.chat = chat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message: <<" + text + ">> sent by " + user + " in chat " + chat.getName()
                + " at " + time;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Message message = (Message) obj;
        return id == message.id && user.equals(message.user) && chat.equals(message.chat)
                && text.equals(message.text) && time.equals(message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, chat, text, time);
    }
}
