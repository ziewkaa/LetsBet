package pl.coderslab.letsbetnow.service;


import org.springframework.security.core.Authentication;
import pl.coderslab.letsbetnow.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> findAllUsers();

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    User getUserById(Long id);

    void deleteUser(User user);
}
