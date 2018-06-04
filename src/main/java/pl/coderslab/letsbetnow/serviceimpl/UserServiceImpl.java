package pl.coderslab.letsbetnow.serviceimpl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Role;
import pl.coderslab.letsbetnow.model.User;
import pl.coderslab.letsbetnow.repository.UserRepository;
import pl.coderslab.letsbetnow.service.RoleService;
import pl.coderslab.letsbetnow.service.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOneById(id);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void saveUser(User user) {

        Role role = roleService.findRoleByName("USER");
        user.setRoles( new HashSet<>(Arrays.asList(role)));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);

        userRepository.save(user);
    }



}
