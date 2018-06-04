package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.service.HorseService;

@Controller
@RequestMapping("/horse")
public class HorseController {

    @Autowired
    private HorseService horseService;

    @GetMapping("/{id}")
    public String horseDetails(@PathVariable Long id, Model model) {
        Horse horse = horseService.findHorseById(id);
        model.addAttribute(horse);
        return "horse";
    }

}
