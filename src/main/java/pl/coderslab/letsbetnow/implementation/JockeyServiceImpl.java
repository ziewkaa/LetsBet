package pl.coderslab.letsbetnow.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Jockey;
import pl.coderslab.letsbetnow.repository.JockeyRepository;
import pl.coderslab.letsbetnow.service.JockeyService;

import java.util.List;

@Service
public class JockeyServiceImpl implements JockeyService {

    @Autowired
    private JockeyRepository jockeyRepository;

    @Override
    public void saveJockey(Jockey jockey) {
        jockeyRepository.save(jockey);
    }

    @Override
    public List<Jockey> findAllJockeys() {
        return jockeyRepository.findAll();
    }

    @Override
    public Jockey findJockeyById(Long id) {
        return jockeyRepository.findOneById(id);
    }
}
