package pl.coderslab.letsbetnow.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.*;
import pl.coderslab.letsbetnow.service.implementation.config.CurrentUser;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    @Autowired
    private BetService betService;

    @GetMapping("/edit")
    public String userDetails(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());

        model.addAttribute(user);

        return "user/details";
    }

    @PostMapping("/edit")
    public String userDetails(@AuthenticationPrincipal UserDetails userDetails, @Valid User user, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "details";
        }

        User oldUser = userService.findUserByUsername(userDetails.getUsername());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        userService.saveUser(oldUser);

        return "user/details";
    }

    @PostMapping("/delete")
    public String userDelete(@RequestParam String username, HttpSession session){

        User user = userService.findUserByUsername(username);
        userService.deleteUser(user);
        session.invalidate();

        return "redirect:/logout";
    }

    @GetMapping("/card/add")
    public String addCard(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());

        model.addAttribute("user",user);

        return "user/wallet";
    }

    @GetMapping("/bets")
    public String userBets(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        List<Bet> betsActive = betService.findAllBetsByUserAndEventStatus(user, "Planned");
        List<Bet> betsApproved = betService.findAllBetsByUserAndEventStatus(user, "Approved");
        List<Bet> betsWaiting = betService.findAllBetsByUserAndEventStatus(user, "Live");
        model.addAttribute("betsActive",betsActive);
        model.addAttribute("betsApproved",betsApproved);
        model.addAttribute("betsWaiting",betsWaiting);

        return "user/bets";
    }

    @GetMapping("/live")
    public String userBetsLive(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        User user = userService.findUserByUsername(currentUser.getUsername());
        List<Event> events = eventService.findAllEventsByUserBets(user);
        model.addAttribute("events", events);

        return "user/liveevents";
    }

}
