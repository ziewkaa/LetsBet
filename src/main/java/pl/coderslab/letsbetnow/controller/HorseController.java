package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.model.History;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.service.EventsHorsesService;
import pl.coderslab.letsbetnow.service.HistoryService;
import pl.coderslab.letsbetnow.service.HorseService;

import java.util.List;

@Controller
@RequestMapping("/horse")
public class HorseController {

    @Autowired
    private HorseService horseService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    @GetMapping("/all")
    public String allHorses(Model model) {
        List<Horse> horses = horseService.findAllHorsesOrderByName();
        model.addAttribute("horses",horses);
        return "horses";
    }

    @GetMapping("/{id}")
    public String horseDetails(@PathVariable Long id, Model model) {
        Horse horse = horseService.findHorseById(id);
        List<EventsHorses> events = eventsHorsesService.findAllByHorse(horse);
        History history = historyService.findResultsByHorse(horse);
        model.addAttribute("horse",horse);
        model.addAttribute("events",events);
        model.addAttribute("history",history);
        return "horse";
    }

}
