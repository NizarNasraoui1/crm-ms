package opportunity_management;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableFeignClients
@ComponentScan(basePackages = "opportunity_management")
	public class OpportunityManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(OpportunityManagementApplication.class, args);
	}
}
