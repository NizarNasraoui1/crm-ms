package contact_management;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@ComponentScan(basePackages = "contact_management")
	public class ContactManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}
}
