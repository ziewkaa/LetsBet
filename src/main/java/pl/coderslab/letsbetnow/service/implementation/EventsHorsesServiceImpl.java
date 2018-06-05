package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;
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
    public List<EventsHorses> findAllHorsesByEvent(Event event) {
        return eventsHorsesRepository.findAllByEvent(event);
    }

    @Override
    public List<EventsHorses> findAllEventsByHorse(Horse horse) {
        return eventsHorsesRepository.findAllByHorse(horse);
    }

    @Override
    public List<EventsHorses> findAllEventsAndHorses() {
        return eventsHorsesRepository.findAll();
    }

    @Override
    public List<EventsHorses> findAllEventsByEventStatus(String status) {
        return eventsHorsesRepository.findAllByEventStatus(status);
    }


}
