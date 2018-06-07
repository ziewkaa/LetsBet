package pl.coderslab.letsbetnow.rest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.rest.Dto.HorseDto;
import pl.coderslab.letsbetnow.rest.DtoService.BetDtoService;
import pl.coderslab.letsbetnow.rest.DtoService.HorseDtoService;

import java.util.List;

@RestController
@RequestMapping("/api/horses")
public class HorseControllerRest {

    @Autowired
    private HorseDtoService horseDtoService;

    @GetMapping
    public List<HorseDto> getAllHorses() {
        return horseDtoService.getAllHorses();
    }

    @GetMapping("/{id}")
    public HorseDto getHorseById(@PathVariable Long id) {
        return horseDtoService.getHorseById(id);
    }

    @GetMapping("/event/{id}")
    public List<HorseDto> getHorsesByEventId(@PathVariable Long id) {
        return horseDtoService.getHorsesByEventId(id);
    }

}
