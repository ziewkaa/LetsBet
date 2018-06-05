package pl.coderslab.letsbetnow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventControllerRest {

    @Autowired
    private EventService eventService;

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.findAllEvents();
    }

//    @GetMapping("/{id}")
//    public Event getEvent(@PathVariable Long id) {
//        return eventService.findEventById(id);
//    }

//    @GetMapping("/{id}/events")
//    public List<Event> getEventsByHorseId(@PathVariable Long id) {
//        return eventService.findAllEventsByHorseId(id);
//    }



}
