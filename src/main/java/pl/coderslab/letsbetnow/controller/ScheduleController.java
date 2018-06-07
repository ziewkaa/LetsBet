package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.letsbetnow.model.*;
import pl.coderslab.letsbetnow.resources.FakerService;
import pl.coderslab.letsbetnow.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Controller
public class ScheduleController {

    @Autowired
    private EventService eventService;

    @Autowired
    private FakerService fakerService;

    @Autowired
    private OddsService oddsService;

    @Autowired
    private BetService betService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    //event
    @Scheduled(cron = ("0/15 * * * * ?"))
    public void setRaceStatus() {

        List<Event> events = eventService.findAllEventsByStartDate(LocalDate.now());

        for (Event event : events) {

            if (event.getStartTime().getMinute() == LocalTime.now().getMinute()) {
                event.setStatus("Live");
                List<EventsHorses> eventsHorses = eventsHorsesService.findAllByEvent(event);


                int counter = 0;
                while(counter < eventsHorses.size()) {
                    for (EventsHorses horse : eventsHorses) {
                        horse.setPosition(counter+1);
                        eventsHorsesService.saveEventsHorses(horse);
                        counter++;
                    }
                }
            }

            if (event.getEndTime().getMinute() == LocalTime.now().getMinute()) {
                event.setStatus("Ended");
            }

            eventService.saveEvent(event);
        }

    }

    @Scheduled(cron = ("0/10 * * * * ?"))
    public void setPositions() {

        List<Event> events = eventService.findAllEventsByStatus("Live");

        //takes each event
        for (Event event : events) {
            // checks start time
            if (event.getEndTime().isAfter(LocalTime.now())) {

                //gets all details for  event
                List<EventsHorses> eventsHorses = eventsHorsesService.findAllByEvent(event);
                List<Integer> positions = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
                Collections.shuffle(positions);

                //sets random positions for event
                int counter = 0;
                while (counter < eventsHorses.size()) {
                    for (EventsHorses horse : eventsHorses) {
                        horse.setPosition(positions.get(counter));
                        eventsHorsesService.saveEventsHorses(horse);
                        counter++;
                    }
                }
            }
        }
    }

    @Scheduled(cron = ("0/15 * * * * ?"))
    public void setOdds() {

        List<Event> events = eventService.findAllEventsByStatus("Planned");

        for (Event event : events) {
            if (event.getStartTime().isAfter(LocalTime.now()) && event.getStartDate().isEqual(LocalDate.now())) {

                List<EventsHorses> eventsHorses = eventsHorsesService.findAllByEvent(event);

                for (EventsHorses eh : eventsHorses) {
                    List<Odds> odds = new ArrayList<>();
                    odds.add(eh.getOdds());

                    for (Odds odd : odds) {
                        Random randomOdd = new Random();
                        odd.setWinValue(fakerService.generateOdds(1, randomOdd));
                        odd.setPlaceValue(fakerService.generateOdds(2, randomOdd));
                        odd.setShowValue(fakerService.generateOdds(3, randomOdd));
                        oddsService.saveOdds(odd);
                        eh.setOdds(odd);
                        eventsHorsesService.saveEventsHorses(eh);
                    }
                }
            }
        }
    }

    @Scheduled(cron = ("0/10 * * * * ?"))
    public void checkLiveRaceBets() {

        List<Bet> bets = betService.findAllBetsByEventStatus("Live");
        for (Bet bet : bets) {
            bet.setStatus("Waiting");
            betService.saveBet(bet);
        }
    }

    @Scheduled(cron = ("0/20 * * * * ?"))
    public void checkEndedRaceBets() {

        List<Event> events = eventService.findAllEventsByStatus("Ended");
        for (Event event : events) {
            List<EventsHorses> eventsHorses = eventsHorsesService.findAllByEvent(event);
            List<Bet> bets = betService.findAllBetsByEvent(event);
            event.setStatus("Approved");
            eventService.saveEvent(event);
            for (Bet bet : bets) {
                checkBetsPrize(eventsHorses, bet);
                bet.setStatus("Approved");
                betService.saveBet(bet);
            }
        }
    }

    @Scheduled(cron = ("0/10 * * * * ?"))
    public void updateBetsOdds() {
        List<Bet> bets = betService.findAllBetsByEventStatus("Planned");
        for (Bet bet : bets) {
            List<EventsHorses> eventsHorses = eventsHorsesService.findAllByHorseAndEvent(bet.getHorse(), bet.getEvent());
            for (EventsHorses eh : eventsHorses){
                if (bet.getBetType().equals("Win") && !bet.getOddValue().equals(eh.getOdds().getWinValue())) {
                    bet.setOddValue(eh.getOdds().getWinValue());
                    betService.saveBet(bet);
                } else if (bet.getBetType().equals("Place") && !bet.getOddValue().equals(eh.getOdds().getWinValue())){
                    bet.setOddValue(eh.getOdds().getPlaceValue());
                    betService.saveBet(bet);
                } else {
                    bet.setOddValue(eh.getOdds().getShowValue());
                    betService.saveBet(bet);
                }
            }
        }
    }

    private void checkBetsPrize(List<EventsHorses> events, Bet bet) {

        for (EventsHorses eventsHorses : events) {
            if (bet.getBetType().equals("Win")) {
                if (eventsHorses.getHorse().getName().equals(bet.getHorse().getName())) {
                    if (eventsHorses.getPosition() == 1) {
                        calculatePrize(eventsHorses.getOdds().getWinValue(), bet);
                    }
                }
            } else if (bet.getBetType().equals("Place")) {
                if (eventsHorses.getHorse().equals(bet.getHorse())) {
                    if (eventsHorses.getPosition() == 2) {
                        calculatePrize(eventsHorses.getOdds().getPlaceValue(), bet);
                    }
                }
            } else if (bet.getBetType().equals("Show")) {
                if (eventsHorses.getHorse().equals(bet.getHorse())) {
                    if (eventsHorses.getPosition() == 3) {
                        calculatePrize(eventsHorses.getOdds().getShowValue(), bet);
                    }
                }
            }
        }
    }

    private void calculatePrize(double value, Bet bet) {
        User user = bet.getUser();
        BigDecimal funds = user.getFunds();
        double prize = bet.getBetValue() * value;
        funds = funds.add(BigDecimal.valueOf(prize));
        user.setFunds(funds);
        userService.saveUser(user);
    }

}
