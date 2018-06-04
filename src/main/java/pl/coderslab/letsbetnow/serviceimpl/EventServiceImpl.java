package pl.coderslab.letsbetnow.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.repository.EventRepository;
import pl.coderslab.letsbetnow.service.EventService;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event findEventById(Long id) {
        return eventRepository.findOneById(id);
    }

    @Override
    public List<Event> findAllEventsByStartDate(LocalDate start) {
        return eventRepository.findAllByStartDate(start);
    }

    @Override
    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
}
