package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepositories{
    private DataSource dataSource;

    public UserRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public Optional<User> findById(Long id) throws SQLException {
        User result = null;

        try{
            PreparedStatement query = dataSource.getConnection().prepareStatement("SELECT * FROM chat.users WHERE id=?");
            query.setLong(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()){
                result = new User(resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        null,null);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return Optional.ofNullable(result);
    }
}
