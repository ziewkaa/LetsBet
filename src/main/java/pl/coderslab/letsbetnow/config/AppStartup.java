package pl.coderslab.letsbetnow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pl.coderslab.letsbetnow.faker.FakerService;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.UserService;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Order
@Component
public class AppStartup implements ApplicationRunner {

    @Autowired
    private FakerService fakerService;

    @Autowired
    private UserService userService;

    @Override
    public void run (ApplicationArguments args) throws Exception {

        userService.saveUser(testUser());
        fakerService.getTrainers();
        fakerService.getJockeys();
        fakerService.getHorses();
        fakerService.getHorsesHistory();
        fakerService.getEvents();
        fakerService.getEventsHorses();
        fakerService.getOdds();

    }

    private User testUser(){
        User user = new User();
        user.setActive(true);
        user.setFirstName("Jack");
        user.setLastName("Black");
        user.setUsername("jackblack");
        user.setEmail("jack@black.com");
        user.setPassword("jackblack");
        user.setBirthDate(LocalDate.parse("1994-01-01"));
        return user;
    }
}
