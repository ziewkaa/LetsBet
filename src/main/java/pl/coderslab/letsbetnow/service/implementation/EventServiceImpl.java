package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.repository.EventRepository;
import pl.coderslab.letsbetnow.service.EventService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findEventById(Long id) {
        return eventRepository.findOneById(id);
    }

    @Override
    public Event findEventByEventHorsesList(List<EventsHorses> eventsHorses) {
        return eventRepository.findEventByEventHorses(eventsHorses);
    }

    @Override
    public List<Event> findAllEventsByStartDate(LocalDate start) {
        return eventRepository.findAllByStartDateOrderByStartTimeAsc(start);
    }

    @Override
    public List<Event> findAllEventsByStatusAndStartDate(String status, LocalDate startDate) {
        return eventRepository.findAllByStatusAndStartDateOrderByStartTimeAsc(status ,startDate);
    }

    @Override
    public List<Event> findAllEventsByStatusAndEndTime(String status, LocalDate endTime) {
        return eventRepository.findAllByStatusAndEndTime(status,endTime);
    }

    @Override
    public List<Event> findAllEventsByEndTimeBefore(LocalTime end) {
        return eventRepository.findAllByEndTimeBefore(end);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public List<Event> findAllEventsByStatus(String status) {
        return eventRepository.findAllByStatus(status);
    }

    @Override
    public Event findEventByIdAndStartDate(Long id, LocalDate startDate) {
        return eventRepository.findByIdAndStartDate(id, startDate);
    }

    public void setRandomEventLive(){

        Random randomEvent = new Random();

        Event event = eventRepository.findByIdAndStartDate((long)randomEvent.nextInt(80)+1, LocalDate.now());
        while (event == null) {
            event = eventRepository.findByIdAndStartDate((long)randomEvent.nextInt(80)+1, LocalDate.now());
        }
        event.setStartTime(LocalTime.now());
        event.setStatus("Live");
        eventRepository.save(event);

    }

    @Override
    public List<Event> findAllEventsByUserBets(User user) {
        return eventRepository.findAllLiveEventsByUser(user);
    }


}
