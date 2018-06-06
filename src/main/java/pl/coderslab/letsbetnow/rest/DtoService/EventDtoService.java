package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.rest.Dto.EventDto;
import pl.coderslab.letsbetnow.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventDtoService {

    @Autowired
    private EventService eventService;

    @Autowired
    private BetDtoService betDtoService;

    public List<EventDto> getAllEvents() {

        List<Event> events = eventService.findAllEvents();
        List<EventDto> eventDtos = new ArrayList<>();

        for (Event e : events) {
            EventDto eventDto = getEventById(e.getId());
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }

    public EventDto getEventById(Long id) {

        EventDto eventDto = new EventDto();
        Event event = eventService.findEventById(id);
        eventDto.setId(event.getId());
        eventDto.setStatus(event.getStatus());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setEndTime(event.getEndTime());
        eventDto.setBets(betDtoService.getAllBets(event.getBets()));
        return eventDto;

    }

}
