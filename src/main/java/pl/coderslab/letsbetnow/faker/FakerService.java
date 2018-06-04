package pl.coderslab.letsbetnow.faker;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.*;
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

    public void getEvents() {

        for (int i = 0; i < 10; i++) {

            Random random = new Random();
            Event event = new Event();

            List<String> racecourses = new ArrayList<>(Arrays.asList("Ascot","Hamilton Park","Hexham", "Kempton Park", "Leicester", "Newbury", "Newcastle", "Perth", "Warwick", "Windsor"));
            List<Integer> distances = new ArrayList<>(Arrays.asList(1400, 1600, 1800, 2000, 2200, 2500));
            event.setStartTime(LocalTime.now().plusMinutes(random.nextInt(15)+1));
            event.setStartDate(LocalDate.now());
            event.setEndTime(event.getStartTime().plusMinutes(5));
            event.setScheduled(true);
            event.setRacecourse(racecourses.get(random.nextInt(racecourses.size())));
            event.setDistance(distances.get(random.nextInt(distances.size())));
            eventService.saveEvent(event);

        }

    }

    public void getHorses() {

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 60; i++) {
            Horse horse = new Horse();
            String name = faker.lordOfTheRings().character();

            while (horseService.findHorseByName(name) != null) {
                name = faker.gameOfThrones().character();
            }

            horse.setName(name);
            horse.setAge(faker.number().numberBetween(3,6));
            horse.setJockey(jockeyService.findJockeyById((long) i+1));
            horse.setTrainer(trainerService.findTrainerById((long) random.nextInt(30)+1));

            horseService.saveHorse(horse);
        }
    }

    public void getHorsesForEvents(){

        Random randomEvent = new Random();
        Long counter = 1L;

        for (Horse horse : horseService.findAllHorses()) {
            if (horse.getEvent() == null) {
                Event event = eventService.findEventById(counter);
                horse.setEvent(event);
                horseService.saveHorse(horse);
                counter++;
                if (counter > 10) {
                    counter = 1L;
                }
            }
        }

    }

    public void getOdds() {

        List<Horse> horses = horseService.findAllHorses();
        double baseWin = 3;
        double basePlace = 2;
        double baseShow = 1;

        for (Horse horse : horses) {
                Odds odds = new Odds();
                Random randomOdd = new Random();
                odds.setWinValue(generateOdds(baseWin, randomOdd));
                odds.setPlaceValue(generateOdds(basePlace, randomOdd));
                odds.setShowValue(generateOdds(baseShow, randomOdd));
                odds.setHorse(horse);
                oddsService.saveOdds(odds);

                horse.setOdds(odds);
                horseService.saveHorse(horse);
        }
    }

    private double generateOdds(double base, Random randomWin) {
        double start = base + randomWin.nextInt(10);
        double end = (double) randomWin.nextInt(100) / 100;
        double result = start + end;
        double finalResult = Math.round(result * 100.0) / 100.0;
        return finalResult;
    }

    @Scheduled(cron = ("0 0/1 * 1/1 * ?"))
    public void generatePositions() {
        List<Event> events = new ArrayList<>();

    }

    public void getHorsesHistory() {

        for (Horse horse : horseService.findAllHorses()) {
            History history = new History();
            Random randomHistory = new Random();
            int winsHistory = randomHistory.nextInt(30)+1;
            int placeHistory = randomHistory.nextInt(30)+1;
            int showHistory = randomHistory.nextInt(30)+1;
            history.setWins(winsHistory);
            history.setPlaces(placeHistory);
            history.setShows(showHistory);
            history.setHorse(horse);
            historyService.saveHistory(history);
            horse.setHistory(history);
            horseService.saveHorse(horse);
        }
    }

}
