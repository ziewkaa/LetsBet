package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.service.TrainerService;

@Service
public class TrainerDtoService {

    @Autowired
    private TrainerService trainerService;

}
