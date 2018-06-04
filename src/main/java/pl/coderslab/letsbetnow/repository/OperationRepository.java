package pl.coderslab.letsbetnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.letsbetnow.model.Operation;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
