package opportunity_management.service;

import opportunity_management.entity.Authority;
import opportunity_management.entity.Role;
import opportunity_management.entity.User;
import opportunity_management.exception.AuthorityAlreadyExists;
import opportunity_management.exception.RoleAlreadyExistsException;

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
