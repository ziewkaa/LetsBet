package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllByUser(User user);

    List<Bet> findAllByUserAndEventStatus(User user, String status);

    List<Bet> findAllByEventId(Long id);

    List<Bet> findAllByHorseId(Long id);

    List<Bet> findAllByHorse(Horse horse);

    List<Bet> findAllByEvent(Event event);
}
