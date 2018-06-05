package pl.coderslab.letsbetnow.converter;

import org.springframework.core.convert.converter.Converter;
import com.github.javafaker.Team;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.service.EventService;

public class EventConverter implements Converter<String, Event> {

    @Autowired
    EventService eventService;

    @Override
    public Event convert(String s) {
        return eventService.findEventById(Long.parseLong(s));
    }
}
