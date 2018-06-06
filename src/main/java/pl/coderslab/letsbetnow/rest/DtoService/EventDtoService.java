package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.rest.Dto.EventDto;
import pl.coderslab.letsbetnow.rest.Dto.EventsHorsesDto;
import pl.coderslab.letsbetnow.rest.Dto.HorseDto;
import pl.coderslab.letsbetnow.service.EventService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventDtoService {

    @Autowired
    private EventService eventService;

    @Autowired
    private BetDtoService betDtoService;

    @Autowired
    private HorseDtoService horseDtoService;

    @Autowired
    private EventsHorsesDtoService eventsHorsesDtoService;

    public List<EventDto> getAllEvents() {
        List<Event> events = eventService.findAllEvents();
        List<EventDto> eventDtos = new ArrayList<>();

        for (Event e : events) {
            EventDto eventDto = getEvent(e);
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }

    public EventDto getEventById(Long id) {
        return getEvent(eventService.findEventById(id));
    }

    public List<EventDto> getEventsByHorseId(Long id) {
        List<EventsHorsesDto> ehdList = eventsHorsesDtoService.getEventsHorsesByHorseId(id);
        List<EventDto> events = new ArrayList<>();
        for (EventsHorsesDto ehd : ehdList) {
            EventDto event = getEventById(ehd.getEvent());
            events.add(event);
        }
        return events;
    }

    public List<EventDto> getEventsByDate(LocalDate date) {
        List<EventDto> eventDtos = new ArrayList<>();
        List<Event> events = eventService.findAllEventsByStartDate(date);
        for (Event event : events) {
            EventDto eventDto = getEvent(event);
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }

    private EventDto getEvent(Event event) {
        List<HorseDto> horses = horseDtoService.getHorsesByEventId(event.getId());
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setStatus(event.getStatus());
        eventDto.setStartDate(event.getStartDate());
        eventDto.setStartTime(event.getStartTime());
        eventDto.setEndTime(event.getEndTime());
        eventDto.setBets(betDtoService.getAllBets(event.getBets()));
        eventDto.setRacecourse(event.getRacecourse());
        eventDto.setDistance(event.getDistance());
        eventDto.setHorses(horses);
        return eventDto;
    }

}
