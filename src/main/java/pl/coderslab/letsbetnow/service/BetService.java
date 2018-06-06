package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;

import java.util.List;

public interface BetService {

    void saveBet(Bet bet);

    List<Bet> findAllByUser(User user);

    List<Bet> findAllBets();

    List<Bet> findAllBetsByEventId(Long id);

    List<Bet> findAllBetsByHorse(Horse horse);

    List<Bet> findAllBetsByHorseId(Long id);

//    List<Bet> findAllBetsByHorseId(Long id);
}
