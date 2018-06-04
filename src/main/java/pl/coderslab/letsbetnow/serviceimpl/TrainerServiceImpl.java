package pl.coderslab.letsbetnow.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Trainer;
import pl.coderslab.letsbetnow.repository.TrainerRepository;
import pl.coderslab.letsbetnow.service.TrainerService;

import java.util.List;

@Service
public class TrainerServiceImpl  implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public List<Trainer> findAllTrainers() {
        return trainerRepository.findAll();
    }

    @Override
    public void saveTrainer(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Override
    public Trainer findTrainerById(Long id) {
        return trainerRepository.findOneById(id);
    }
}
