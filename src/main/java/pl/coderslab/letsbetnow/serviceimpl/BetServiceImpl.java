package pl.coderslab.letsbetnow.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.repository.BetRepository;
import pl.coderslab.letsbetnow.service.BetService;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Override
    public void saveBet(Bet bet) {

        betRepository.save(bet);

    }
}
