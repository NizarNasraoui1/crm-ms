//package contact_management.service.impl;
//
//import contact_management.entity.Authority;
//import contact_management.entity.Role;
//import contact_management.entity.User;
//import contact_management.exception.AuthorityAlreadyExists;
//import contact_management.exception.RoleAlreadyExistsException;
//import contact_management.repository.AuthorityRepository;
//import contact_management.repository.RoleRepository;
//import contact_management.repository.UserRepository;
//import contact_management.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Service @RequiredArgsConstructor @Transactional @Slf4j
//public class UserServiceImpl implements UserService, UserDetailsService {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final AuthorityRepository authorityRepository;
//    private final PasswordEncoder passwordEncoder;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if(user == null) {
//            log.error("User not found in the database");
//            throw new UsernameNotFoundException("User not found in the database");
//        } else {
//            log.info("User found in the database: {}", username);
//            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//            user.getRoles().forEach(role -> {
//                role.getAuthorities().forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
//            });
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//        }
//    }
//
//    @Override
//    public User saveUser(User user) throws Exception {
//        if(userRepository.findByUsername(user.getUsername())==null){
//            log.info("Saving new user {} to the database", user.getName());
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            return userRepository.save(user);
//        }
//        throw new Exception("user already exists!");
//
//    }
//
//    @Override
//    public Role saveRole(Role role) throws RoleAlreadyExistsException {
//        if(roleRepository.findByName(role.getName())==null){
//            log.info("Saving new role {} to the database", role.getName());
//            return roleRepository.save(role);
//        }
//        throw new RoleAlreadyExistsException();
//
//    }
//
//    @Override
//    public Authority saveAuthority(Authority authority) throws AuthorityAlreadyExists {
//        if(authorityRepository.findByName(authority.getName())==null){
//            return authorityRepository.save(authority);
//        }
//        throw new AuthorityAlreadyExists();
//
//
//    }
//
//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        log.info("Adding role {} to user {}", roleName, username);
//        User user = userRepository.findByUsername(username);
//        Role role = roleRepository.findByName(roleName);
//        user.getRoles().add(role);
//    }
//
//    @Override
//    public User getUser(String username) {
//        log.info("Fetching user {}", username);
//        return userRepository.findByUsername(username);
//    }
//
//    @Override
//    public Role addAuthorityToRole(String roleName, String authorityName) {
//        Role role=this.roleRepository.findByName(roleName);
//        Authority authority=authorityRepository.findByName(authorityName);
//        role.getAuthorities().add(authority);
//        return roleRepository.save(role);
//
//
//    }
//
//    @Override
//    public List<User> getUsers() {
//        log.info("Fetching all users");
//        return userRepository.findAll();
//    }
//}
