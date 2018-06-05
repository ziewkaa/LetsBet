package pl.coderslab.letsbetnow.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.service.HorseService;

import java.util.List;

@RestController
@RequestMapping("/api/horses")
public class HorseControllerRest {

    @Autowired
    private HorseService horseService;

    @GetMapping("/all")
    public List<Horse> getAllHorses() {
        return horseService.findAllHorses();
    }

    @GetMapping("/{id}")
    public Horse getHorse(@PathVariable Long id) {
        return horseService.findHorseById(id);
    }

//    @GetMapping("/{id}/horses")
//    public List<Horse> getHorsesByEventId(@PathVariable Long id) {
//        return horseService.findAllHorsesByEventId(id);
//    }

}
