package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.History;
import pl.coderslab.letsbetnow.model.Horse;

public interface HistoryService {

    History findResultsByHorse(Horse horse);

    void saveHistory(History history);
}
