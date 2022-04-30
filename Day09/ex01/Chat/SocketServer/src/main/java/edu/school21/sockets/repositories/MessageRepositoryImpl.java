package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Component
public class MessageRepositoryImpl implements MessageRepository{
    public JdbcTemplate jdbcTemplate;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    public MessageRepositoryImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Message> messageRowMapper = (rs, rowNum) ->
            new Message(usersRepository.findByEmail(rs.getString("sender")).orElse(null),
                    rs.getString("text"), rs.getTimestamp("time").toLocalDateTime());

    @Override
    public List<Message> findAll() {
        return jdbcTemplate.query("SELECT * FROM messages", messageRowMapper);
    }

    @Override
    public void save(Message entity) {
            jdbcTemplate.update("INSERT INTO messages (sender, text, time) values (?, ?, ?)", entity.getSender().getEmail(), entity.getText(), Timestamp.valueOf(entity.getTime()));
    }
}
