package pl.coderslab.letsbetnow.rest.Dto;

import pl.coderslab.letsbetnow.model.Bet;
import pl.coderslab.letsbetnow.model.Horse;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventDto {

    private Long id;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String status;

    private String racecourse;

    private int distance;

    private List<HorseDto> horses;

    private List<BetDto> bets;

    public EventDto() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRacecourse() {
        return racecourse;
    }

    public void setRacecourse(String racecourse) {
        this.racecourse = racecourse;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<HorseDto> getHorses() {
        return horses;
    }

    public void setHorses(List<HorseDto> horses) {
        this.horses = horses;
    }

    public List<BetDto> getBets() {
        return bets;
    }

    public void setBets(List<BetDto> bets) {
        this.bets = bets;
    }
}
