package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.History;
import pl.coderslab.letsbetnow.model.Horse;

public interface HistoryRepository extends JpaRepository<History, Long> {

    History findByHorse(Horse horse);

}
