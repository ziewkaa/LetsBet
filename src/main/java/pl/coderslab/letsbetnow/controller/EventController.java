package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.EventsHorsesService;
import pl.coderslab.letsbetnow.service.HorseService;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    @Autowired
    private HorseService horseService;

    @GetMapping("/{id}")
    public String eventDetails(@PathVariable Long id, Model model){

        Event event = eventService.findEventById(id);
        List<EventsHorses> details = eventsHorsesService.findAllByEvent(event);
        model.addAttribute("event", event);
        model.addAttribute("details", details);

        if (event.getStatus().equals("Planned")) {
            return "plannedevent";
        }

        return "liveevent";
    }

}
