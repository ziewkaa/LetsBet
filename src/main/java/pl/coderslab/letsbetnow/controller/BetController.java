package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.service.BetService;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.HorseService;
import pl.coderslab.letsbetnow.service.UserService;

import javax.validation.Valid;
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
    public String addToBasket(@RequestParam int event, @RequestParam Double oddValue, @RequestParam int horse, @RequestParam String betType,  Model model) {

        Bet bet = new Bet();
        bet.setHorse(horse);
        bet.setEvent(event);
        bet.setBetType(betType);
        bet.setOddValue(oddValue);
        Event eventToBet = eventService.findEventById((long) event);
        Horse horseToBet = horseService.findHorseById((long) horse);

        model.addAttribute("bet", bet);
        model.addAttribute("event", eventToBet);
        model.addAttribute("horse", horseToBet);
        return "bet";
    }


}
