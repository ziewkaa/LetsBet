package pl.coderslab.letsbetnow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.letsbetnow.faker.FakerService;

import java.util.Random;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class LetsbetnowApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsbetnowApplication.class, args);

    }

}
