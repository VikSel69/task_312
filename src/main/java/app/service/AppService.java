package app.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import app.model.Role;
import app.model.User;

import java.util.List;

public interface AppService extends UserDetailsService {

    List<User> findAllUsers();

    User findUser(Long userId) throws IllegalArgumentException;

    void saveUser(User user);

    void deleteUser(Long userId);

    Iterable<Role> findAllRoles();
}
