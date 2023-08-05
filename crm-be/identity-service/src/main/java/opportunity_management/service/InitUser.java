package opportunity_management.service;

import opportunity_management.entity.UserCredential;
import opportunity_management.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitUser implements CommandLineRunner {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean adminExists(){
        UserCredential admin= userCredentialRepository.findByName("admin").orElse(null);
        return admin!=null;
    }

    public void initAdmin(){
        UserCredential newUserCredential= new UserCredential();
        newUserCredential.setEmail("admin");
        newUserCredential.setName("admin");
        newUserCredential.setPassword(passwordEncoder.encode("admin"));
        userCredentialRepository.save(newUserCredential);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!adminExists()){
            this.initAdmin();
        }
    }
}
