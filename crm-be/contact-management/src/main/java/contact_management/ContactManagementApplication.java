package contact_management;


import contact_management.Util.KafkaUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	KafkaUtil kafkaUtil;
	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}
}
