package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.User;

import java.util.List;

public interface BetService {

    void saveBet(Bet bet);

    List<Bet> findAllByUser(User user);
}
