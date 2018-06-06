package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.Jockey;
import pl.coderslab.letsbetnow.rest.Dto.HorseDto;
import pl.coderslab.letsbetnow.rest.Dto.JockeyDto;
import pl.coderslab.letsbetnow.service.JockeyService;

@Service
public class JockeyDtoService {

    public JockeyDto getJockey(Jockey jockey) {

        JockeyDto jockeyDto = new JockeyDto();
        jockeyDto.setId(jockey.getId());
        jockeyDto.setFirstName(jockey.getFirstName());
        jockeyDto.setLastName(jockey.getLastName());
        jockeyDto.setWeight(jockey.getWeight());

        return jockeyDto;
    }

}
