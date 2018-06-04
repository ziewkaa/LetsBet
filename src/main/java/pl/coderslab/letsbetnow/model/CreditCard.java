package pl.coderslab.letsbetnow.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "creditcards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private Long number;

    @Min(value = 1, message = "* Incorrect month")
    @Max(value = 12, message = "* Incorrect month")
    private int expiringMonth;

    @Min(18)
    @Max(value = 25, message = "* Incorrect year")
    private int expiringYear;

    private int cvv;

    @ManyToOne
    private User user;

    public CreditCard (){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public int getExpiringMonth() {
        return expiringMonth;
    }

    public void setExpiringMonth(int expiringMonth) {
        this.expiringMonth = expiringMonth;
    }

    public int getExpiringYear() {
        return expiringYear;
    }

    public void setExpiringYear(int expiringYear) {
        this.expiringYear = expiringYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
