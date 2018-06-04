package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Event;
import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findOneById(Long id);

    List<Event> findAllByStartDate(LocalDate start);


}
