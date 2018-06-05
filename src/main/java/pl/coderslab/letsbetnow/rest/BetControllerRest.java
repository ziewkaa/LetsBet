package pl.coderslab.letsbetnow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.service.BetService;

import java.util.List;

@RestController
@RequestMapping("/api/bets")
public class BetControllerRest {

    @Autowired
    private BetService betService;

    @GetMapping("/all")
    public List<Bet> getAllBets() {
        return betService.findAllBets();
    }

//    @GetMapping("/event/{id}/bets")
//    public List<Bet> getBetsByEventId(@PathVariable Long id) {
//        return betService.findAllBetsByEventId(id);
//    }

//    @GetMapping("/horse/{id}/bets")
//    public List<Bet> getBetsByHorseId(@PathVariable Long id) {
//        return betService.findAllBetsByHorseId(id);
//    }

}
