package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChatRoomRepositoryJdbcImpl implements ChatRoomRepository{
    private DataSource dataSource;

    public ChatRoomRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public Optional<Chatroom> findById(Long id) throws SQLException {
        Chatroom result = null;
        UserRepositories userRepositories = new UserRepositoryJdbcImpl(dataSource);
        try{
            PreparedStatement query = dataSource.getConnection().prepareStatement("SELECT * FROM chat.chatrooms WHERE id=?");
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()){
                result = new Chatroom(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        userRepositories.findById(resultSet.getLong("ownerId")).orElse(null),new ArrayList<>());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }
}
