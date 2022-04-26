package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> usersRooms;

    public User(Integer id, String login, String password, List<Chatroom> created, List<Chatroom> users){
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = created;
        this.usersRooms = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: id = " + id + "; login = " + getLogin() + "; password = " + getPassword() +
                "; created rooms = " + createdRooms + "; user's rooms = " + usersRooms;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id && login.equals(user.login) && password.equals(user.password)
                && createdRooms.equals(user.createdRooms) && usersRooms.equals(user.usersRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, usersRooms);
    }
}
