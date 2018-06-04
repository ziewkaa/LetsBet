package pl.coderslab.letsbetnow.serviceimpl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class CurrentUser extends User {

    private final pl.coderslab.letsbetnow.model.User user;

    public CurrentUser(String username, String password, Collection<? extends GrantedAuthority> authorities, pl.coderslab.letsbetnow.model.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.coderslab.letsbetnow.model.User getUser() {
        return user;
    }
}
