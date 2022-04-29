package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("usersRepositoryJdbcTemplate") DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) ->
            new User(rs.getLong("id"), rs.getString("email"));

    private RowMapper<String> passwordRowMapper = (rs, rowNum) ->
            rs.getString("password");

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> userList = jdbcTemplate.query("SELECT * FROM users WHERE email = ?", userRowMapper, email);
        if (userList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(userList.get(0));
        }
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", userRowMapper, id).get(0);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users (email) values (?)", entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET email=? WHERE id=?", entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }

    @Override
    public String findPasswordById(Long id) {
        List<String> passwordList = jdbcTemplate.query("SELECT password FROM users WHERE id = ?", passwordRowMapper, id);
        if (passwordList.isEmpty()) {
            return "";
        } else {
            return passwordList.get(0);
        }
    }
    @Override
    public void saveByEmail(String email, String password) {
        jdbcTemplate.update("INSERT INTO users (email, password) VALUES (?, ?)", email, password);
    }
}
