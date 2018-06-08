package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Operation;
import pl.coderslab.letsbetnow.model.User;

import java.util.List;

public interface OperationService {

    void saveOperation(Operation operation);

    List<Operation> findAllOperationsByUser(User user);

}
