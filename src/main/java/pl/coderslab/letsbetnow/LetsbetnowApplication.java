package pl.coderslab.letsbetnow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class LetsbetnowApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsbetnowApplication.class, args);

    }

}
