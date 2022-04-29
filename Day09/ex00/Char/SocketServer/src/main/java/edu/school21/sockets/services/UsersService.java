package edu.school21.sockets.services;

import org.springframework.stereotype.Component;

@Component
public interface UsersService {
    boolean signUp(String email, String password);
}
