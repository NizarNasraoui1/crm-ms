package contact_management.service;

import contact_management.entity.Authority;
import contact_management.entity.Role;
import contact_management.entity.User;
import contact_management.exception.AuthorityAlreadyExists;
import contact_management.exception.RoleAlreadyExistsException;

import java.util.List;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
public interface UserService {
    User saveUser(User user) throws Exception;
    Role saveRole(Role role) throws RoleAlreadyExistsException;
    Authority saveAuthority(Authority authority) throws AuthorityAlreadyExists;
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    Role addAuthorityToRole(String roleName, String authority);
    List<User>getUsers();
}
