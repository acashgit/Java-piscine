package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private DataSource dataSource;
    UserRepositories userRepositories;
    ChatRoomRepository chatRoomRepository;

    public MessagesRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
        this.chatRoomRepository = new ChatRoomRepositoryJdbcImpl(dataSource);
        this.userRepositories = new UserRepositoryJdbcImpl(dataSource);
    }
    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Message result = null;
        try{
            PreparedStatement query = dataSource.getConnection().prepareStatement("SELECT * FROM chat.messages WHERE id=?");
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()){
                if (resultSet.getTimestamp("time") != null)
                    result = new Message(resultSet.getLong("id"),
                            userRepositories.findById(resultSet.getLong("authorId")).orElse(null),
                            chatRoomRepository.findById(resultSet.getLong("chatroomId")).orElse(null),
                            resultSet.getString("text"),
                            resultSet.getTimestamp("time").toLocalDateTime());
                else
                    result = new Message(resultSet.getLong("id"),
                            userRepositories.findById(resultSet.getLong("authorId")).orElse(null),
                            chatRoomRepository.findById(resultSet.getLong("chatroomId")).orElse(null),
                            resultSet.getString("text"),
                            null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }

    @Override
    public void save(Message message) {
        ResultSet resultSet = null;
        try{
            if (userRepositories.findById(message.getUser().getId()).isPresent() &&
                    chatRoomRepository.findById(message.getChat().getId()).isPresent()){
                PreparedStatement query = dataSource.getConnection().prepareStatement("INSERT INTO chat.messages (authorid, chatroomid, text, time) VALUES (?, ?, ?, ?) RETURNING *");
                query.setLong(1, message.getUser().getId());
                query.setLong(2, message.getChat().getId());
                query.setString(3, message.getText());
                query.setTimestamp(4, Timestamp.valueOf(message.getTime()));
                resultSet = query.executeQuery();
                resultSet.next();
                message.setId(resultSet.getLong("id"));
            }
            else
                throw new NotSavedSubEntityException();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Message message) {
        try {
            PreparedStatement query = null;
            query = dataSource.getConnection().prepareStatement( "UPDATE chat.messages SET " + "authorId = ?, " + "chatroomId = ?, " +
                    "text = ?, " + "time = ? " + " WHERE id = ?");
            query.setLong(1, message.getUser().getId());
            query.setLong(2, message.getChat().getId());
            query.setString(3, message.getText());
            try {
                query.setTimestamp(4, Timestamp.valueOf(message.getTime()));
            }catch (NullPointerException e){
                query.setTimestamp(4, null);
            }
            query.setLong(5, message.getId());
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
