package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;

import java.util.List;

public interface EventsHorsesRepository extends JpaRepository<EventsHorses, Long> {

    List<EventsHorses> findAllByEvent(Event event);

    List<EventsHorses> findAllByHorse(Horse horse);

    List<EventsHorses> findAllByHorseAndEvent(Horse horse, Event event);

    List<EventsHorses> findAllByEventStatus(String status);

    @Query("SELECT e FROM EventsHorses e WHERE e.horse.id = :id")
    List<EventsHorses> findAllByHorseId(@Param("id") Long id);

    @Query("SELECT e FROM EventsHorses e WHERE e.event.id = :id")
    List<EventsHorses> findAllByEventId(@Param("id") Long id);
}
