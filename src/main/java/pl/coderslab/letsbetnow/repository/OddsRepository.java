package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Odds;

import java.util.List;

public interface OddsRepository extends JpaRepository<Odds,Long> {

//    List<Odds> findAllByEvent(Event event);

}
