package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.Trainer;
import pl.coderslab.letsbetnow.repository.HorseRepository;
import pl.coderslab.letsbetnow.service.HorseService;

import java.util.Collections;
import java.util.List;

@Service
public class HorseServiceImpl implements HorseService {

    @Autowired
    private HorseRepository horseRepository;

    @Override
    public void saveHorse(Horse horse) {
        horseRepository.save(horse);
    }

    @Override
    public List<Horse> findAllHorses() {
        return horseRepository.findAll();
    }

    @Override
    public List<Horse> findAllHorsesOrderByName() {

        return horseRepository.findAllOrderByName();
    }

    @Override
    public List<Horse> findAllHorsesByTrainer(Trainer trainer) {
        return horseRepository.findAllByTrainer(trainer);
    }

    @Override
    public Horse findHorseByName(String name) {
        return horseRepository.findByName(name);
    }

    @Override
    public Horse findHorseById(Long id) {
        return horseRepository.findOneById(id);
    }

//    @Override
//    public List<Horse> findAllHorsesByEvents(Event event) {
//        return horseRepository.findAllByEvents(event);
//    }

//    @Override
//    public List<Horse> findAllHorsesByEventsId(Long id) {
//        return horseRepository.findAllByEventsId(id);
//    }
}
