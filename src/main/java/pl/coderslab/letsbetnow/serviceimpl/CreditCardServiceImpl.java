package pl.coderslab.letsbetnow.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.CreditCard;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.repository.CreditCardRepository;
import pl.coderslab.letsbetnow.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public void saveCreditCard(CreditCard creditCard) {
        creditCardRepository.save(creditCard);
    }

    @Override
    public void deleteCardById(Long id) {
    }

    @Override
    public CreditCard findCardById(Long id) {
        return null;
    }

    @Override
    public CreditCard findByUser(User user) {
        return null;
    }
}
