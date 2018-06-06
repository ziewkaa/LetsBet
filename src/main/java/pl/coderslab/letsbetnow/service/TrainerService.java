package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.Trainer;

import java.util.List;

public interface TrainerService {

    List<Trainer> findAllTrainers();

    void saveTrainer(Trainer trainer);

    Trainer findTrainerById(Long id);

    Trainer findOneByHorses(Horse horse);
}
