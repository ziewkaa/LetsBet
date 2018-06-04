package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Jockey;

import java.util.List;

public interface JockeyRepository extends JpaRepository<Jockey, Long> {

    List<Jockey> findAll();

    Jockey findOneById(Long id);
}
