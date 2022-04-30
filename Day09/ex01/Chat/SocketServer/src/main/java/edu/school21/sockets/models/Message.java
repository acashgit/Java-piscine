package edu.school21.sockets.models;

import java.time.LocalDateTime;

public class Message {
    private User sender;
    private String text;
    private LocalDateTime time;

    public Message(User sender, String text, LocalDateTime time){
        this.sender = sender;
        this.text = text;
        this.time = time;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
