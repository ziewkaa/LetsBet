package pl.coderslab.letsbetnow.service;


import pl.coderslab.letsbetnow.model.CreditCard;
import pl.coderslab.letsbetnow.model.User;

public interface CreditCardService {

    void saveCreditCard(CreditCard creditCard);

    void deleteCardById(Long id);

    CreditCard findCardById(Long id);

    CreditCard findByUser(User user);
}
