package pl.coderslab.letsbetnow.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "horses")
public class Horse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    private int age;

    @OneToOne
    private Odds odds;

    @OneToMany(mappedBy = "horse")
    private List<Bet> bets;

    @ManyToMany(mappedBy = "horse")
    private List<EventsHorses> eventsHorses;

    @ManyToOne
    private Jockey jockey;

    @ManyToOne
    private Trainer trainer;

    @OneToOne
    private History history;

    public Horse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Odds getOdds() {
        return odds;
    }

    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public Jockey getJockey() {
        return jockey;
    }

    public void setJockey(Jockey jockey) {
        this.jockey = jockey;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public List<EventsHorses> getEventsHorses() {
        return eventsHorses;
    }

    public void setEventsHorses(List<EventsHorses> eventsHorses) {
        this.eventsHorses = eventsHorses;
    }
}

