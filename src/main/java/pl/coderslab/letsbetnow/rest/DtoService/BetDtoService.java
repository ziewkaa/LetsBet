package pl.coderslab.letsbetnow.rest.DtoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Horse;
import pl.coderslab.letsbetnow.rest.Dto.BetDto;
import pl.coderslab.letsbetnow.service.BetService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetDtoService {

    @Autowired
    private BetService betService;

    public List<BetDto> getBetsByHorseId(Long id) {

        List<BetDto> betDtos = new ArrayList<>();
        List<Bet> bets = betService.findAllBetsByHorseId(id);
        for (Bet bet : bets) {
            BetDto bd = getBet(bet);
            betDtos.add(bd);
        }

        return betDtos;
    }

    public List<BetDto> getBetsByEventId(Long id) {

        List<BetDto> betDtos = new ArrayList<>();
        List<Bet> bets = betService.findAllBetsByEventId(id);
        for (Bet bet : bets) {
            BetDto bd = getBet(bet);
            betDtos.add(bd);
        }

        return betDtos;
    }

    public List<BetDto> getAllBets(List<Bet> bets) {

        List<BetDto> betDtos = new ArrayList<>();
        for (Bet bet : bets) {
            BetDto bd = getBet(bet);
            betDtos.add(bd);
        }
        return betDtos;
    }

    public BetDto getBet(Bet bet) {

        BetDto betDto = new BetDto();
        betDto.setId(bet.getId());
        betDto.setUserId(bet.getUser().getId());
        betDto.setRegistered(bet.getRegistered());
        betDto.setEventStartDate(bet.getEvent().getStartDate());
        betDto.setEventStartTime(bet.getEvent().getStartTime());
        betDto.setEventRacecourse(bet.getEvent().getRacecourse());
        betDto.setEventDistance(bet.getEvent().getDistance());
        betDto.setBetValue(bet.getBetValue());
        betDto.setBetType(bet.getBetType());
        betDto.setOddsValue(bet.getOddValue());
        return betDto;
    }



}
