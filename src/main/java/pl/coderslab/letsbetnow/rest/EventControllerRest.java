package pl.coderslab.letsbetnow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.rest.Dto.BetDto;
import pl.coderslab.letsbetnow.rest.Dto.EventDto;
import pl.coderslab.letsbetnow.rest.DtoService.BetDtoService;
import pl.coderslab.letsbetnow.rest.DtoService.EventDtoService;
import pl.coderslab.letsbetnow.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventControllerRest {

    @Autowired
    private EventDtoService eventDtoService;

    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventDtoService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventDtoService.getEventById(id);
    }

}
