package pl.coderslab.letsbetnow.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isScheduled = true;

    private String racecourse;

    private int distance;

    @OneToMany
    private List<Bet> bets;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER)
    private List<Horse> horses;

//    @OneToMany(mappedBy = "event")
//    private List<Odds> winOdds;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean schedules) {
        isScheduled = schedules;
    }

    public String getRacecourse() {
        return racecourse;
    }

    public void setRacecourse(String racecourse) {
        this.racecourse = racecourse;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void setHorses(List<Horse> horses) {
        this.horses = horses;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


}
