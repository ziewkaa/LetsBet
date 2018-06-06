package pl.coderslab.letsbetnow.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Odds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @OneToOne
    private EventsHorses eventsHorses;

    @OneToMany
    private List<Bet> bets;

    private double winValue;

    private double placeValue;

    private double showValue;

    public Odds() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventsHorses getEventsHorses() {
        return eventsHorses;
    }

    public void setEventsHorses(EventsHorses eventsHorses) {
        this.eventsHorses = eventsHorses;
    }

    public double getWinValue() {
        return winValue;
    }

    public void setWinValue(double winValue) {
        this.winValue = winValue;
    }

    public double getPlaceValue() {
        return placeValue;
    }

    public void setPlaceValue(double placeValue) {
        this.placeValue = placeValue;
    }

    public double getShowValue() {
        return showValue;
    }

    public void setShowValue(double showValue) {
        this.showValue = showValue;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
