package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public Optional<Message> findById(Long id) throws SQLException {
        Message result = null;
        UserRepositories userRepositories = new UserRepositoryJdbcImpl(dataSource);
        ChatRoomRepository chatRoomRepository = new ChatRoomRepositoryJdbcImpl(dataSource);
        try{
            PreparedStatement query = dataSource.getConnection().prepareStatement("SELECT * FROM chat.messages WHERE id=?");
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()){
                result = new Message(resultSet.getInt("id"),
                        userRepositories.findById(resultSet.getLong("authorId")).orElse(null),
                        chatRoomRepository.findById(resultSet.getLong("chatroomId")).orElse(null),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("time").toLocalDateTime());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }
}
