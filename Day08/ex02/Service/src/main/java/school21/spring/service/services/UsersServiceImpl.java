package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.repositories.UsersRepositoryJdbcImpl;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcTemplate") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        String password;
        if (optionalUser.isPresent()) {
            password = usersRepository.findPasswordById(optionalUser.get().getId());
        } else {
            password = passwordGenerate();
            usersRepository.saveByEmail(email, password);
        }
        return password;
    }

    private String passwordGenerate(){
        int len = (int)(Math.random() * 12);
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < len; i++)
            password.append((char) (Math.random() * 95 + 32));
        return password.toString();
    }
}
