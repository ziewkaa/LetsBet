package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {

}
