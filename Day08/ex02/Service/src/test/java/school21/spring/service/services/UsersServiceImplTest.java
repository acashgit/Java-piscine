package school21.spring.service.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;

public class UsersServiceImplTest {
    @Test
    void usersServicesTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        UsersService userService = context.getBean(UsersService.class);
        String password = userService.signUp("dasdagq@mail.com");
        Assertions.assertNotEquals(password, "");
    }
}
