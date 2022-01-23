package app.repository;

import org.springframework.data.repository.CrudRepository;
import app.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
