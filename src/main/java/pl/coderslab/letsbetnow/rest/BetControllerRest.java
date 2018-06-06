package pl.coderslab.letsbetnow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.rest.Dto.BetDto;
import pl.coderslab.letsbetnow.rest.DtoService.BetDtoService;
import pl.coderslab.letsbetnow.service.BetService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bets")
public class BetControllerRest {

    @Autowired
    private BetService betService;

    @Autowired
    private BetDtoService betDtoService;

    @GetMapping
    public List<BetDto> getAllBets() {

        List<BetDto> bets = new ArrayList<>();
        for (Bet bet : betService.findAllBets()) {
            bets.add(betDtoService.getBet(bet));
        }
        return bets;
    }

    @GetMapping("/{id}")
    public List<BetDto> getBetById(@PathVariable Long id) {

        List<BetDto> bets = new ArrayList<>();
        for (Bet bet : betService.findAllBets()) {
            bets.add(betDtoService.getBet(bet));
        }
        return bets;
    }


}
