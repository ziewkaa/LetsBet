package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EventService {

    Event findEventById(Long id);

    Event findEventByEventHorsesList(List<EventsHorses> eventsHorses);

    List<Event> findAllEventsByStartDate(LocalDate start);

    List<Event> findAllEventsByEndTimeBefore(LocalTime end);

    List<Event> findAllEvents();

    List<Event> findAllEventsByStatusAndStartDate(String status, LocalDate startDate);

    List<Event> findAllEventsByStatusAndEndTime(String status, LocalDate startDate);

    void saveEvent(Event event);

    List<Event> findAllEventsByStatus(String status);

    Event findEventByIdAndStartDate(Long id, LocalDate startDate);

    public void setRandomEventLive();

    List<Event> findAllEventsByUserBets(User user);

}
