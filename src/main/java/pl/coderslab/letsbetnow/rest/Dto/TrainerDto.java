package pl.coderslab.letsbetnow.rest.Dto;

import pl.coderslab.letsbetnow.model.Horse;

import javax.persistence.OneToMany;
import java.util.List;

public class TrainerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private List<HorseDto> horses;

    public TrainerDto() {
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

    public List<HorseDto> getHorses() {
        return horses;
    }

    public void setHorses(List<HorseDto> horses) {
        this.horses = horses;
    }
}
