package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.letsbetnow.model.*;
import pl.coderslab.letsbetnow.rest.Dto.BetDto;
import pl.coderslab.letsbetnow.rest.Dto.EventsHorsesDto;
import pl.coderslab.letsbetnow.rest.Dto.HorseDto;
import pl.coderslab.letsbetnow.service.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class HorseDtoService {

    @Autowired
    private JockeyService jockeyService;

    @Autowired
    private HorseService horseService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BetDtoService betDtoService;

    @Autowired
    private EventsHorsesDtoService eventsHorsesDtoService;

    public List<HorseDto> getAllHorses() {
        List<Horse> horses = horseService.findAllHorses();
        List<HorseDto> horseDtos = getHorses(horses);
        return horseDtos;
    }

    public List<HorseDto> getHorsesByEventId(Long id) {

//        Event event = eventService.findEventById(id);
        List<EventsHorsesDto> ehdList = eventsHorsesDtoService.getEventsHorsesByEventId(id);
        List<HorseDto> horseDtos = new ArrayList<>();

        for (EventsHorsesDto ehd : ehdList) {
            HorseDto horse = getHorseById(ehd.getHorse());
            horseDtos.add(horse);
        }
        return horseDtos;
    }

    public List<HorseDto> getHorses(List<Horse> horses){

        List<HorseDto> horseDtos = new ArrayList<>();
        for(Horse horse : horses) {
            HorseDto horseDto = getHorse(horse);
            horseDtos.add(horseDto);
        }
        return horseDtos;
    }

    public HorseDto getHorseById(Long id) {

        Horse horse = horseService.findHorseById(id);
        HorseDto horseDto = getHorse(horse);
        return horseDto;
    }

    public HorseDto getHorse(Horse horse) {

        HorseDto horseDto = new HorseDto();
        horseDto.setId(horse.getId());
        horseDto.setName(horse.getName());
        horseDto.setAge(horse.getAge());
        horseDto.setJockeyFirstName(horse.getJockey().getFirstName());
        horseDto.setJockeyLastName(horse.getJockey().getLastName());
        horseDto.setTrainerFirstName(horse.getTrainer().getFirstName());
        horseDto.setTrainerLastName(horse.getTrainer().getLastName());
        horseDto.setBets(betDtoService.getBetsByHorseId(horse.getId()));
        return horseDto;
    }


}
