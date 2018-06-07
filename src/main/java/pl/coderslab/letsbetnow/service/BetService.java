package pl.coderslab.letsbetnow.service;

import org.springframework.data.jpa.repository.Query;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;

import java.util.List;

public interface BetService {

    void saveBet(Bet bet);

    List<Bet> findAllByUser(User user);

    List<Bet> findAllBetsByUserAndEventStatus(User user, String status);

    List<Bet> findAllBets();

    List<Bet> findAllBetsByEventId(Long id);

    List<Bet> findAllBetsByHorse(Horse horse);

    List<Bet> findAllBetsByHorseId(Long id);

    List<Bet> findAllBetsByEvent(Event event);

    List<Bet> findAllBetsByEventStatus(String status);
}
