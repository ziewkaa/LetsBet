package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.CreditCard;
import pl.coderslab.letsbetnow.model.User;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findByUser(User user);

}
