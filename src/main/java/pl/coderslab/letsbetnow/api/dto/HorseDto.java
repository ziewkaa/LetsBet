package pl.coderslab.letsbetnow.api.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.boot.jackson.JsonComponent;
import pl.coderslab.letsbetnow.model.Event;
import pl.coderslab.letsbetnow.model.Jockey;
import pl.coderslab.letsbetnow.model.Trainer;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class HorseDto {

    private Long id;

    private String name;

    private int age;

    public HorseDto() {
    }
}
