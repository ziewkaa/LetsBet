package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Odds;
import pl.coderslab.letsbetnow.repository.OddsRepository;
import pl.coderslab.letsbetnow.service.OddsService;

@Service
public class OddsServiceImpl implements OddsService {

    @Autowired
    private OddsRepository oddsRepository;

//    @Override
//    public List<Odds> findAllOddsByEvent(Event event) {
//        return oddsRepository.findAllByEvent(event);
//    }

    @Override
    public void saveOdds(Odds odds) {
        oddsRepository.save(odds);
    }
}
