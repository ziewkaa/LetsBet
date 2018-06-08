package pl.coderslab.letsbetnow.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "creditcards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private Long cardNumber;

    @Min(value = 1, message = "* Incorrect month")
    @Max(value = 12, message = "* Incorrect month")
    private Integer expiringMonth;

    @Min(value = 18, message = "* Incorrect year value (Requires min 18)")
    @Max(value = 25 )
    private Integer expiringYear;

    private Integer cvv;

    @OneToOne
    private User user;

    public CreditCard (){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpiringMonth() {
        return expiringMonth;
    }

    public void setExpiringMonth(Integer expiringMonth) {
        this.expiringMonth = expiringMonth;
    }

    public Integer getExpiringYear() {
        return expiringYear;
    }

    public void setExpiringYear(Integer expiringYear) {
        this.expiringYear = expiringYear;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
