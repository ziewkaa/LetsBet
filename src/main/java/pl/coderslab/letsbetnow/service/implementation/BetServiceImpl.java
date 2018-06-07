package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.repository.BetRepository;
import pl.coderslab.letsbetnow.service.BetService;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Override
    public void saveBet(Bet bet) {

        betRepository.save(bet);
    }

    @Override
    public List<Bet> findAllByUser(User user) {
        return betRepository.findAllByUser(user);
    }

    @Override
    public List<Bet> findAllBetsByUserAndEventStatus(User user, String status) {
        return betRepository.findAllByUserAndEventStatus(user,status);
    }

    @Override
    public List<Bet> findAllBets() {
        return betRepository.findAll();
    }

    @Override
    public List<Bet> findAllBetsByEventId(Long id) {
        return betRepository.findAllByEventId(id);
    }

    @Override
    public List<Bet> findAllBetsByHorse(Horse horse) {
        return betRepository.findAllByHorse(horse);
    }

    @Override
    public List<Bet> findAllBetsByHorseId(Long id) {
        return betRepository.findAllByHorseId(id);
    }

    @Override
    public List<Bet> findAllBetsByEvent(Event event) {
        return betRepository.findAllByEvent(event);
    }
}
