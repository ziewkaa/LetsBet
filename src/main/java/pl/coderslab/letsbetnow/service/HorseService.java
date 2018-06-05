package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.Trainer;

import java.util.List;

public interface HorseService {

    void saveHorse(Horse horse);

    List<Horse> findAllHorses();

    List<Horse> findAllHorsesByTrainer(Trainer trainer);

    Horse findHorseByName(String name);

    Horse findHorseById(Long id);

//    List<Horse> findAllHorsesByEvents(Event event);

//    List<Horse> findAllHorsesByEventsId(Long id);
}
