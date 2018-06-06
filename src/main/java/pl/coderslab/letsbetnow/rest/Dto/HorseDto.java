package pl.coderslab.letsbetnow.rest.Dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.letsbetnow.model.*;
import pl.coderslab.letsbetnow.service.*;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class HorseDto {

    private Long id;

    private String name;

    private int age;

    private List<BetDto> bets = new ArrayList<>();

    private String jockeyFirstName;

    private String jockeyLastName;

    private String trainerFirstName;

    private String trainerLastName;

    public HorseDto() {

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

    public List<BetDto> getBets() {
        return bets;
    }

    public void setBets(List<BetDto> bets) {
        this.bets = bets;
    }

    public String getJockeyFirstName() {
        return jockeyFirstName;
    }

    public void setJockeyFirstName(String jockeyFirstName) {
        this.jockeyFirstName = jockeyFirstName;
    }

    public String getJockeyLastName() {
        return jockeyLastName;
    }

    public void setJockeyLastName(String jockeyLastName) {
        this.jockeyLastName = jockeyLastName;
    }

    public String getTrainerFirstName() {
        return trainerFirstName;
    }

    public void setTrainerFirstName(String trainerFirstName) {
        this.trainerFirstName = trainerFirstName;
    }

    public String getTrainerLastName() {
        return trainerLastName;
    }

    public void setTrainerLastName(String trainerLastName) {
        this.trainerLastName = trainerLastName;
    }
}
