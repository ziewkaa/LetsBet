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

    @Scheduled(cron = ("0/5 * * * * ?"))
    public void setPositions() {

        List<Event> events = eventService.findAllEventsByStatus("Live");

        //takes each event
        for (Event event : events) {
            // checks start time
            if (event.getEndTime().isAfter(LocalTime.now())) {

                //gets all horses starting in event
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

    @Scheduled(cron = ("0/10 * * * * ?"))
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

    @Scheduled(cron = ("0/30 * * * * ?"))
    public void checkRaceBets() {

        List<EventsHorses> events = eventsHorsesService.findAllByEventStatus("Ended");
        List<Bet> bets = betService.findAllBets();

        for (EventsHorses event : events) {
            for (Bet bet : bets) {
                bet.setApproved(true);
                event.getEvent().setStatus("Approved");
                switch (bet.getBetType()) {
                    case "Win":
                        if (event.getEvent().equals(bet.getEvent())) {
                            if (event.getHorse().equals(bet.getHorse())) {
                                if (event.getPosition() == 1) {
                                    calculatePrize(bet);
                                }
                            }
                        }
                        break;

                    case "Place" :
                        if (event.getEvent().equals(bet.getEvent())) {
                            if (event.getHorse().equals(bet.getHorse())) {
                                if (event.getPosition() == 2) {
                                    calculatePrize(bet);
                                }
                            }
                        }
                        break;

                    case "Show":
                        if (event.getEvent().equals(bet.getEvent())) {
                            if (event.getHorse().equals(bet.getHorse())) {
                                if (event.getPosition() == 3) {
                                    calculatePrize(bet);
                                }
                            }
                        }
                        break;
                }
            }
        }
    }

    private void calculatePrize(Bet bet) {
        User user = bet.getUser();
        BigDecimal funds = user.getFunds();
        double prize = bet.getBetValue() * bet.getOddValue();
        funds = funds.add(BigDecimal.valueOf(prize));
        user.setFunds(funds);
        userService.saveUser(user);
    }

}
