package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.service.EventService;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public String eventDetails(@PathVariable Long id, Model model){

        Event event = eventService.findEventById(id);
        model.addAttribute("event", event);

        return "event";
    }

}
