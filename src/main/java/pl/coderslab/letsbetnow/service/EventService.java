package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    Event findEventById(Long id);

    List<Event> findAllEventsByStartDate(LocalDate start);

    List<Event> findAllEvents();

    void saveEvent(Event event);

}
