package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Odds;

import java.util.List;

public interface OddsService {

//    List<Odds> findAllOddsByEvent(Event event);

    void saveOdds(Odds odds);

}
