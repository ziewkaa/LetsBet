package pl.coderslab.letsbetnow.faker;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EventsHorsesService eventHorsesService ;

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

    public void getEvents() {

        for (int i = 0; i < 40; i++) {
            Random random = new Random();
            Event event = new Event();
            List<String> racecourses = new ArrayList<>(Arrays.asList("Ascot", "Hamilton Park", "Hexham", "Kempton Park", "Leicester", "Newbury", "Newcastle", "Perth", "Warwick", "Windsor"));
            List<Integer> distances = new ArrayList<>(Arrays.asList(1400, 1600, 1800, 2000, 2200, 2500));
            List<Integer> minutes = new ArrayList<>(Arrays.asList(2,5, 10, 15, 20, 25, 30, 35, 40, 45));
            LocalTime currentTime = LocalTime.now().plusMinutes(minutes.get(random.nextInt(10)));
//            LocalTime currentTime = LocalTime.now().plusMinutes(2);
            event.setStartTime(currentTime);
            event.setStatus("Planned");
            event.setStartDate(LocalDate.now().plusDays(random.nextInt(5)));
            event.setEndTime(event.getStartTime().plusMinutes(5));
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
                Horse horse = horseService.findHorseById((long) random.nextInt(90));

                while (horses.contains(horse)) {
                    horse = horseService.findHorseById((long) random.nextInt(90));
                }

                horses.add(horse);
            }

            for (Horse horse : horses){
                EventsHorses eventsHorses = new EventsHorses();
                eventsHorses.setEvent(event);
                eventsHorses.setHorse(horse);
                eventsHorses.setPosition(0);
                eventHorsesService.saveEventsHorses(eventsHorses);
            }
        }
    }

    public void getOdds() {

        List<EventsHorses> horses = eventHorsesService.findAllEventsAndHorses();
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
            eventHorsesService.saveEventsHorses(eventsHorses);
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
