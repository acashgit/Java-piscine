package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{

    private final UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public boolean signUp(String email, String password) {
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            return false;
        } else {
            password = passwordEncoder.encode(password);
            usersRepository.saveByEmail(email, password);
        }
        return true;
    }

    @Override
    public boolean singIn(String login, String password) {
        Optional<User> optionalUser = usersRepository.findByEmail(login);
        if (optionalUser.isPresent()){
            return optionalUser.filter(user -> passwordEncoder.matches(password, usersRepository.findPasswordById(user.getId()))).isPresent();
        }
        return false;
    }
}
