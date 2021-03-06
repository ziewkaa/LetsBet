package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.letsbetnow.resources.FakerService;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute(new User());

        return "user/register";
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
            return "user/register";
        }

        userService.saveUser(user);

        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model){

        LocalDate date = LocalDate.now();
        List<Event> events = eventService.findAllEventsByStatusAndStartDate("Planned", date);
        List<Event> endedEvents = eventService.findAllEventsByStatusAndStartDate("Approved", date);
        List<Event> liveEvents = eventService.findAllEventsByStatusAndStartDate("Live", date);
        model.addAttribute("upcomingEvents", events);
        model.addAttribute("endedEvents", endedEvents);
        model.addAttribute("liveEvents", liveEvents);
        model.addAttribute("date", date );

        return "home";
    }

    @PostMapping("/home/next")
    public String nextDayEvents(@RequestParam String date, Model model){

        LocalDate newDate = LocalDate.parse(date);
        newDate = newDate.plusDays(1);
        List<Event> events = eventService.findAllEventsByStatusAndStartDate("Planned", newDate);
        model.addAttribute("upcomingEvents", events);
        model.addAttribute("date", newDate);

        return "home";
    }

    @PostMapping("/home/previous")
    public String previousDayEvents(@RequestParam String date,Model model){

        LocalDate newDate = LocalDate.parse(date);
        newDate = newDate.minusDays(1);
        List<Event> events = eventService.findAllEventsByStatusAndStartDate("Approved", newDate);
        model.addAttribute("endedEvents", events);
        model.addAttribute("date", newDate);

        return "home";
    }

}
