package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.repository.EventsHorsesRepository;
import pl.coderslab.letsbetnow.service.EventsHorsesService;

import java.util.List;

@Service
public class EventsHorsesServiceImpl implements EventsHorsesService {

    @Autowired
    private EventsHorsesRepository eventsHorsesRepository;


    @Override
    public void saveEventsHorses(EventsHorses eventsHorses) {
        eventsHorsesRepository.save(eventsHorses);
    }

    @Override
    public List<EventsHorses> findAllByEvent(Event event) {
        return eventsHorsesRepository.findAllByEvent(event);
    }

    @Override
    public List<EventsHorses> findAllEventsAndHorses() {
        return eventsHorsesRepository.findAll();
    }

    @Override
    public List<EventsHorses> findAllByEventStatus(String status) {
        return eventsHorsesRepository.findAllByEventStatus(status);
    }

    @Override
    public List<EventsHorses> findAllByHorse(Horse horse) {
        return eventsHorsesRepository.findAllByHorse(horse);
    }

    @Override
    public List<EventsHorses> findAllByHorseAndEvent(Horse horse, Event event) {
        return eventsHorsesRepository.findAllByHorseAndEvent(horse,event);
    }

    @Override
    public List<EventsHorses> findAllByHorseId(Long id) {
        return eventsHorsesRepository.findAllByHorseId(id);
    }

    @Override
    public List<EventsHorses> findAllByEventId(Long id) {
        return eventsHorsesRepository.findAllByEventId(id);
    }

//    @Override
//    public List<EventsHorses> findAllLiveEventsByUser(User user) {
//        return eventsHorsesRepository.findAllB(user);
//    }


}
