package pl.coderslab.letsbetnow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.letsbetnow.faker.FakerService;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.UserService;

import java.time.LocalDate;

@Component
public class AppStartup implements ApplicationRunner {

    @Autowired
    private FakerService fakerService;

    @Autowired
    private UserService userService;

    @Override
    public void run (ApplicationArguments args) throws Exception {

        fakerService.getTrainers();
        fakerService.getJockeys();
        fakerService.getEvents();
        fakerService.getHorses();
        fakerService.getOdds();
        fakerService.getHorsesHistory();
        fakerService.getHorsesForEvents();
        userService.saveUser(testUser());

    }

    private User testUser(){
        User user = new User();
        user.setActive(true);
        user.setFirstName("Test");
        user.setUsername("test");
        user.setLastName("Test");
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setBirthDate(LocalDate.parse("1994-01-01"));
        return user;
    }
}
