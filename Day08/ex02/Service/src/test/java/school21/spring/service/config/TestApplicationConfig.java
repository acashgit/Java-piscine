package school21.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;
import school21.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "school21.spring.service")
public class TestApplicationConfig {
    private EmbeddedDatabase embeddedDatabase;
    @Bean
    public DataSource hsqlData(){
        try{
            embeddedDatabase =  new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
            Connection connection = embeddedDatabase.getConnection();
            PreparedStatement statement = connection.prepareStatement("create table if not exists users (id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, email VARCHAR(40), password VARCHAR(40))");
            statement.execute();
            statement = connection.prepareStatement("INSERT INTO users (email, password) VALUES ('biba', 'pas1')");
            statement.execute();
            return embeddedDatabase;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public UsersRepositoryJdbcImpl usersRepositoryJdbc(){
        return new UsersRepositoryJdbcImpl(hsqlData());
    }

    @Bean
    public UsersRepositoryJdbcTemplateImpl usersRepositoryJdbcTemplate(){
        return new UsersRepositoryJdbcTemplateImpl(hsqlData());
    }
}
