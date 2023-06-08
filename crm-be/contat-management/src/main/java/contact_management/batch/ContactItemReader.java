//package contact_management.batch;
//
//import contact_management.entity.Contact;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.LineMapper;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ContactItemReader extends FlatFileItemReader<Contact> {
//
//    public ContactItemReader(){
//        this.setResource(new FileSystemResource("src/main/resources/batch-files/contact.csv"));
//        this.setName("csvReader");
//        this.setLinesToSkip(1);
//        this.setLineMapper(this.lineMapper());
//    }
//
//    public LineMapper<Contact> lineMapper() {
//        DefaultLineMapper<Contact> lineMapper = new DefaultLineMapper<>();
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setDelimiter(",");
//        lineTokenizer.setStrict(false);
//        lineTokenizer.setNames("firstName", "lastName","email", "address");
//
//        BeanWrapperFieldSetMapper<Contact> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        fieldSetMapper.setTargetType(Contact.class);
//
//        lineMapper.setLineTokenizer(lineTokenizer);
//        lineMapper.setFieldSetMapper(fieldSetMapper);
//        return lineMapper;
//
//    }
//}
