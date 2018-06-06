package pl.coderslab.letsbetnow.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDateTime registered;

    private boolean approved = false;

    @ManyToOne
    private Horse horse;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    private Double betValue;

    private Double oddValue;

    private String betType;

    public Bet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Horse getHorse() {
        return horse;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBetValue() {
        return betValue;
    }

    public void setBetValue(Double betValue) {
        this.betValue = betValue;
    }

    public Double getOddValue() {
        return oddValue;
    }

    public void setOddValue(Double oddValue) {
        this.oddValue = oddValue;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }


    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
