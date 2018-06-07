package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.Trainer;

import java.util.List;

public interface HorseRepository extends JpaRepository<Horse, Long> {

    List<Horse> findAllByTrainer(Trainer trainer);

    @Query("SELECT h FROM Horse h ORDER BY h.name")
    List<Horse> findAllOrderByName();

    Horse findByName(String name);

    Horse findOneById(Long id);


}
