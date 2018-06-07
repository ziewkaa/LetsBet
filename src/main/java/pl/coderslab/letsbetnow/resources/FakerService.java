package pl.coderslab.letsbetnow.resources;

import com.github.javafaker.Faker;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.*;
import pl.coderslab.letsbetnow.repository.EventsHorsesRepository;
import pl.coderslab.letsbetnow.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class FakerService {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private HorseService horseService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private JockeyService jockeyService;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    @Autowired
    private EventsHorsesRepository eventsHorsesRepository;

    @Autowired
    private OddsService oddsService;

    public void getTrainers() {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            Trainer trainer = new Trainer();
            trainer.setFirstName(faker.name().firstName());
            trainer.setLastName(faker.name().lastName());
            trainerService.saveTrainer(trainer);
        }
    }

    public void getJockeys() {

        Faker faker = new Faker();

        for (int i = 0; i < 60; i++) {
            Jockey jockey = new Jockey();
            jockey.setFirstName(faker.name().firstName());
            jockey.setLastName(faker.name().lastName());
            jockey.setWeight(faker.number().randomDouble(2,50,64));
            jockeyService.saveJockey(jockey);
        }
    }

    public void getHorses() {

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 90; i++) {
            Horse horse = new Horse();
            String name = faker.lordOfTheRings().character();

            while (horseService.findHorseByName(name) != null) {
                name = faker.gameOfThrones().character();
            }

            horse.setName(name);
            horse.setAge(faker.number().numberBetween(3,6));
            horse.setJockey(jockeyService.findJockeyById((long)random.nextInt(60)+1));
            horse.setTrainer(trainerService.findTrainerById((long) random.nextInt(30)+1));
            horseService.saveHorse(horse);
        }
    }

    public void getStatisticsForPastEvents(){

        List<Event> events = eventService.findAllEvents();
        for (Event event : events) {
            if (event.getStatus().equals("Approved") && LocalDate.now().isAfter(event.getStartDate())) {
                List<EventsHorses> eventsHorses = eventsHorsesService.findAllByEvent(event);
                List<Integer> positions = new ArrayList<>();
                Random randomPosition = new Random();
                for (EventsHorses eh : eventsHorses) {
                    int position = randomPosition.nextInt(6)+1;
                    while (positions.contains(position)) {
                        position = randomPosition.nextInt(6) + 1;
                    }
                    positions.add(position);
                    eh.setPosition(position);
                    eventsHorsesService.saveEventsHorses(eh);
                }
            }
        }
    }

    public void getHorsesHistory() {

        for (Horse horse : horseService.findAllHorses()) {
            History history = new History();
            history.setHorse(horse);

            int wins = 0;
            int places = 0;
            int shows = 0;

            List<EventsHorses> eventsHorses = eventsHorsesService.findAllByHorse(horse);
            for (EventsHorses eh : eventsHorses) {

                if (eh.getPosition() == 1) {
                    wins++;
                } else if (eh.getPosition() == 2) {
                    places++;
                } else if (eh.getPosition() == 3) {
                    shows++;
                }
            }

            history.setWins(wins);
            history.setShows(shows);
            history.setPlaces(places);
            historyService.saveHistory(history);
            horse.setHistory(history);
            horseService.saveHorse(horse);
        }
    }

    public void getEvents() {

        for (int i = 0; i < 80; i++) {
            Random random = new Random();
            Event event = new Event();
            List<String> racecourses = new ArrayList<>(Arrays.asList("Ascot", "Hamilton Park", "Hexham", "Kempton Park", "Leicester", "Newbury", "Newcastle", "Perth", "Warwick", "Windsor"));
            List<Integer> distances = new ArrayList<>(Arrays.asList(1400, 1600, 1800, 2000, 2200, 2500));
            List<Integer> minutes = new ArrayList<>(Arrays.asList(1, 3, 5, 10));
            LocalTime currentTime = LocalTime.now().plusMinutes(minutes.get(random.nextInt(4)));
            event.setStartTime(currentTime);
            if (i < 40) {
                event.setStartDate(LocalDate.now().plusDays(random.nextInt(5)));
                event.setStatus("Planned");
            } else {
                event.setStartDate(LocalDate.now().minusDays(random.nextInt(5)));
                event.setStatus("Approved");
            }
            event.setEndTime(event.getStartTime().plusMinutes(3));
            event.setRacecourse(racecourses.get(random.nextInt(racecourses.size())));
            event.setDistance(distances.get(random.nextInt(distances.size())));
            eventService.saveEvent(event);
        }
    }

    public void getEventsHorses() {

        List<Event> events = eventService.findAllEvents();
        Random random = new Random();

        for (Event event : events) {
            List<Horse> horses = new ArrayList<>();

            while (horses.size() < 6) {
                Horse horse = horseService.findHorseById((long) random.nextInt(89)+1);

                while (horses.contains(horse)) {
                    horse = horseService.findHorseById((long) random.nextInt(89)+1);
                }
                horses.add(horse);
            }
            for (Horse horse : horses){
                EventsHorses eventsHorses = new EventsHorses();
                eventsHorses.setEvent(event);
                eventsHorses.setHorse(horse);
                eventsHorses.setPosition(0);
                eventsHorsesService.saveEventsHorses(eventsHorses);
            }
        }
    }

    public void getOdds() {

        List<EventsHorses> horses = eventsHorsesService.findAllEventsAndHorses();
        double baseWin = 1;
        double basePlace = 2;
        double baseShow = 3;

        for (EventsHorses eventsHorses : horses) {

            Odds odds = new Odds();
            Random randomOdd = new Random();
            odds.setWinValue(generateOdds(baseWin, randomOdd));
            odds.setPlaceValue(generateOdds(basePlace, randomOdd));
            odds.setShowValue(generateOdds(baseShow, randomOdd));
            odds.setEventsHorses(eventsHorses);
            oddsService.saveOdds(odds);
            eventsHorses.setOdds(odds);
            eventsHorsesService.saveEventsHorses(eventsHorses);
        }
    }

    public double generateOdds(double base, Random randomWin) {
        double start = base + randomWin.nextInt(10);
        double end = (double) randomWin.nextInt(100) / 100;
        double result = start + end;
        double finalResult = Math.round(result * 100.0) / 100.0;
        return finalResult;
    }

}
