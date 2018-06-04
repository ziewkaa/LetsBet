package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Trainer;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer,Long> {

    Trainer findByLastName(String lastName);

    List<Trainer> findAll();

    Trainer findOneById(Long id);
}
