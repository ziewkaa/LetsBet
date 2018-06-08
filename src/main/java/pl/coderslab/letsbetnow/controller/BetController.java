package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.BetService;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.HorseService;
import pl.coderslab.letsbetnow.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/bet")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private HorseService horseService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @PostMapping("/add")
    public String bet(@ModelAttribute Bet bet, @AuthenticationPrincipal UserDetails userDetails) {

        User user = userService.findUserByUsername(userDetails.getUsername());
        BigDecimal funds = user.getFunds();
        funds = funds.subtract(BigDecimal.valueOf(bet.getBetValue()));
        user.setFunds(funds);
        userService.saveUser(user);
        bet.setRegistered(LocalDateTime.now());
        bet.setUser(user);
        betService.saveBet(bet);

        return "redirect:/user/bets";
    }

    @PostMapping("/basket/add")
    public String addToBasket(@RequestParam Long event, @RequestParam Double oddValue, @RequestParam Long horse, @RequestParam String betType, @AuthenticationPrincipal UserDetails userDetails, Model model) {

        Event eventToBet = eventService.findEventById(event);
        Horse horseToBet = horseService.findHorseById(horse);
        Bet bet = new Bet();
        bet.setHorse(horseToBet);
        bet.setEvent(eventToBet);
        bet.setBetType(betType);
        bet.setOddValue(oddValue);
        User user = userService.findUserByUsername(userDetails.getUsername());

        model.addAttribute("user", user);
        model.addAttribute("bet", bet);
        model.addAttribute("event", eventToBet);
        model.addAttribute("horse", horseToBet);
        return "user/bet";
    }


}
