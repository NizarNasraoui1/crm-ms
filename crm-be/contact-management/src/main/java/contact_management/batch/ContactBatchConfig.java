//package contact_management.batch;
//
//import contact_management.entity.Contact;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//import org.springframework.core.task.TaskExecutor;
//
//@Configuration
//@EnableBatchProcessing
//public class ContactBatchConfig {
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//    @Autowired
//    private ContactProcessor contactProcessor;
//    @Autowired
//    private ContactItemWriter contactItemWriter;
//    @Autowired
//    ContactItemReader contactItemReader;
//
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("csv-step").<Contact, Contact>chunk(10)
//                .reader(contactItemReader)
//                .processor(contactProcessor)
//                .writer(contactItemWriter)
//                .taskExecutor(taskExecutor())
//                .build();
//    }
//
//    @Bean
//    public Job runJob() {
//        return jobBuilderFactory.get("importContacts")
//                .flow(step1()).end().build();
//
//    }
//
//    @Bean
//    public TaskExecutor taskExecutor() {
//        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
//        asyncTaskExecutor.setConcurrencyLimit(10);
//        return asyncTaskExecutor;
//    }
//
//}
