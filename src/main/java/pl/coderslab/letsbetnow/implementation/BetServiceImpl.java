package pl.coderslab.letsbetnow.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Bet;
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
}
