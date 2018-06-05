package pl.coderslab.letsbetnow.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Operation;
import pl.coderslab.letsbetnow.repository.OperationRepository;
import pl.coderslab.letsbetnow.service.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void saveOperation(Operation operation) {
        operationRepository.save(operation);
    }
}
