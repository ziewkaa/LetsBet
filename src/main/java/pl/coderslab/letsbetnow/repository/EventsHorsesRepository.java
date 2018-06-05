package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;

import java.util.List;

public interface EventsHorsesRepository extends JpaRepository<EventsHorses, Long> {

    List<EventsHorses> findAllByEvent(Event event);

    List<EventsHorses> findAllByHorse(Horse horse);

    List<EventsHorses> findAllByEventStatus(String status);

}
