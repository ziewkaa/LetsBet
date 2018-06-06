package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;

import java.util.List;

public interface EventsHorsesService {

    void saveEventsHorses(EventsHorses eventsHorses);

    List<EventsHorses> findAllByEvent(Event event);

    List<EventsHorses> findAllEventsAndHorses();

    List<EventsHorses> findAllByEventStatus(String status);

    List<EventsHorses> findAllByHorse(Horse horse);
}