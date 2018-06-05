package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.ListUtils;
import pl.coderslab.letsbetnow.faker.FakerService;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private FakerService fakerService;


    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute(new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult bindingResult){

        User emailExists = userService.findUserByEmail(user.getEmail());
        User usernameExists = userService.findUserByUsername(user.getUsername());

        if (emailExists != null) {
            bindingResult.rejectValue("email", "error.user", "There already exists an account connected with this email");
        }

        if (usernameExists != null) {
            bindingResult.rejectValue("username", "error.user", "There already exists an account connected with this username");
        }

        if (bindingResult.hasErrors()){
            return "register";
        }

        userService.saveUser(user);

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model){

        List<Event> upcomingEvents = eventService.findAllEventsByStatusAndStartDate("Planned", LocalDate.now());
        model.addAttribute("upcomingEvents", upcomingEvents);

        List<Event> liveEvents = eventService.findAllEventsByStatusAndStartDate("Live", LocalDate.now());
        model.addAttribute("liveEvents", liveEvents);

        return "home";
    }

}
