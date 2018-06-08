package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Operation;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.repository.OperationRepository;
import pl.coderslab.letsbetnow.service.OperationService;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void saveOperation(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> findAllOperationsByUser(User user) {
        return operationRepository.findAllByUser(user);
    }
}
