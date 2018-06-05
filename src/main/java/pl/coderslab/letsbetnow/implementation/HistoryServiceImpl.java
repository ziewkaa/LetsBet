package pl.coderslab.letsbetnow.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.History;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.repository.HistoryRepository;
import pl.coderslab.letsbetnow.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History findResultsByHorse(Horse horse) {
        return historyRepository.findByHorse(horse);
    }

    @Override
    public void saveHistory(History history) {
        historyRepository.save(history);
    }
}
