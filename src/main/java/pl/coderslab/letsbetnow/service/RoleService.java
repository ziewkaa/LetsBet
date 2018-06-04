package pl.coderslab.letsbetnow.service;

import pl.coderslab.letsbetnow.model.Role;

public interface RoleService {

    Role findRoleByName(String name);

    void saveRole(Role role);
}
