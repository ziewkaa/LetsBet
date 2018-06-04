package pl.coderslab.letsbetnow.serviceimpl;


import org.springframework.stereotype.Service;
import pl.coderslab.letsbetnow.model.Role;
import pl.coderslab.letsbetnow.repository.RoleRepository;
import pl.coderslab.letsbetnow.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String name) {

        Role role = roleRepository.findByName(name);

        if(role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }

        return roleRepository.findByName(role.getName());
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
