package pl.coderslab.letsbetnow.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Operation;
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
}
