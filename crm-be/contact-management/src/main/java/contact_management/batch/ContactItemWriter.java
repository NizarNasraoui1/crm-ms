//package contact_management.batch;
//
//import contact_management.entity.Contact;
//import contact_management.repository.ContactRepository;
//import org.springframework.batch.item.data.RepositoryItemWriter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ContactItemWriter extends RepositoryItemWriter<Contact> {
//    private final ContactRepository contactRepository;
//
//    public ContactItemWriter(ContactRepository contactRepository){
//        super();
//        this.contactRepository=contactRepository;
//        this.setRepository(contactRepository);
//        this.setMethodName("save");
//    }
//
//}
