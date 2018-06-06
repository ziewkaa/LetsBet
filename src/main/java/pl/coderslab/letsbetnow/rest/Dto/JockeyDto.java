package pl.coderslab.letsbetnow.rest.Dto;

import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Horse;

import javax.persistence.*;
import java.util.List;

public class JockeyDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Double weight;

    private List<HorseDto> horses;

    private List<EventDto> events;

    public JockeyDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<HorseDto> getHorses() {
        return horses;
    }

    public void setHorses(List<HorseDto> horses) {
        this.horses = horses;
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }
}
