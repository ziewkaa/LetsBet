package pl.coderslab.letsbetnow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.EventsHorses;
import pl.coderslab.letsbetnow.service.EventService;
import pl.coderslab.letsbetnow.service.EventsHorsesService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


@Controller
public class ScheduleController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventsHorsesService eventsHorsesService;

    @Scheduled(cron = ("1/1 * * * * ?"))
    public void checkRaceTime() {

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

    @Scheduled(cron = ("0/30 * * * * ?"))
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


}
