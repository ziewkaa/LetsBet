package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.Trainer;

import java.util.List;

public interface HorseRepository extends JpaRepository<Horse, Long> {

    List<Horse> findAllByTrainer(Trainer trainer);

    Horse findByName(String name);

    Horse findOneById(Long id);

//    List<Horse> findAllByEvents(Event event);

//    List<Horse> findAllByEventsId(Long id);
}
