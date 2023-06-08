package contact_management.repository;

import contact_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
