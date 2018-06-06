package pl.coderslab.letsbetnow.rest.Dto;

public class EventsHorsesDto {

    private Long id;

    private Long event;

    private Long horse;

    private int position;

    private double winOdds;

    private double placeOdds;

    private double showOdds;

    public EventsHorsesDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long getHorse() {
        return horse;
    }

    public void setHorse(Long horse) {
        this.horse = horse;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getWinOdds() {
        return winOdds;
    }

    public void setWinOdds(double winOdds) {
        this.winOdds = winOdds;
    }

    public double getPlaceOdds() {
        return placeOdds;
    }

    public void setPlaceOdds(double placeOdds) {
        this.placeOdds = placeOdds;
    }

    public double getShowOdds() {
        return showOdds;
    }

    public void setShowOdds(double showOdds) {
        this.showOdds = showOdds;
    }
}
