package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.rest.Dto.EventsHorsesDto;
import pl.coderslab.letsbetnow.service.EventsHorsesService;
import pl.coderslab.letsbetnow.service.HorseService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsHorsesDtoService {

    @Autowired
    private HorseService horseService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    public List<EventsHorsesDto> getEventsHorsesByEventId(Long id) {
        List<EventsHorses> eventsHorses = eventsHorsesService.findAllByEventId(id);
        List<EventsHorsesDto> ehdList = new ArrayList<>();
        for (EventsHorses eh : eventsHorses) {
            EventsHorsesDto eventsHorsesDto = getEventsHorses(eh);
            ehdList.add(eventsHorsesDto);
        }
        return ehdList;
    }

    public List<EventsHorsesDto> getEventsHorsesByHorseId(Long id) {
        List<EventsHorses> eventsHorses = eventsHorsesService.findAllByHorseId(id);
        List<EventsHorsesDto> ehdList = new ArrayList<>();
        for (EventsHorses eh : eventsHorses) {
            EventsHorsesDto eventsHorsesDto = getEventsHorses(eh);
            ehdList.add(eventsHorsesDto);
        }
        return ehdList;
    }

    public EventsHorsesDto getEventsHorses(EventsHorses eventsHorses) {

        EventsHorsesDto eventsHorsesDto = new EventsHorsesDto();
        eventsHorsesDto.setId(eventsHorses.getId());
        eventsHorsesDto.setEvent(eventsHorses.getEvent().getId());
        eventsHorsesDto.setHorse(eventsHorses.getHorse().getId());
        eventsHorsesDto.setPosition(eventsHorses.getPosition());
        eventsHorsesDto.setWinOdds(eventsHorses.getOdds().getWinValue());
        eventsHorsesDto.setPlaceOdds(eventsHorses.getOdds().getPlaceValue());
        eventsHorsesDto.setShowOdds(eventsHorses.getOdds().getShowValue());

        return eventsHorsesDto;
    }

}
