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
import pl.coderslab.letsbetnow.service.HorseService;
import pl.coderslab.letsbetnow.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/bet")
public class BetController {

    @Autowired
    private BetService betService;

    @Autowired
    private HorseService horseService;

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String bet(Model model) {

        model.addAttribute(new Bet());
        return "bet";
    }

    @PostMapping("/add")
    public String bet(@Valid Bet bet, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) {

        if (bindingResult.hasErrors()) {
            return "bet";
        }

        User user = userService.findUserByUsername(userDetails.getUsername());

        return "bet";

    }

    @PostMapping("/basket/add")
    public String addBetToBasket(@RequestParam Event event, @RequestParam Double oddValue, @RequestParam Horse horse, @RequestParam String betType,  Model model) {

        Bet bet = new Bet();
        bet.setHorse(horse);
        bet.setEvent(event);
        bet.setBetType(betType);
        bet.setOddValue(oddValue);
        model.addAttribute("bet", bet);
        return "bet";
    }

    @GetMapping("/basket/add")
    public String confirmBasket(@RequestParam Event event, @RequestParam Double oddValue, @RequestParam Horse horse, @RequestParam String betType,  Model model) {

        Bet bet = new Bet();
        bet.setHorse(horse);
        bet.setEvent(event);
        bet.setBetType(betType);
        bet.setOddValue(oddValue);
        model.addAttribute("bet", bet);

        return "bet";
    }

}
