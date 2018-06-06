package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findOneById(Long id);

    Event findEventByEventHorses(List<EventsHorses> eventsHorses);

    List<Event> findAllByStartDateOrderByStartTimeAsc(LocalDate start);

    List<Event> findAllByEndTimeBefore(LocalTime now);

    List<Event> findAllByStatusAndStartDateOrderByStartTimeAsc(String status, LocalDate startDate);

    List<Event> findAllByStatus(String status);

    Event findByIdAndStartDate(Long id, LocalDate startDate);

    List<Event> findAllByStatusAndEndTime(String status, LocalDate endTime);

//    @Query("SELECT e FROM Event e JOIN Horse h ON e.horses.id = h.id WHERE h.id = :id")
//    List<Event> findAllByHorseId(@Param("id") Long id);
}
