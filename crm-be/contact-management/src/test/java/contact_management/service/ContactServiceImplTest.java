package contact_management.service;

import contact_management.Util.KafkaUtil;
import contact_management.dto.ContactDto;
import contact_management.entity.Contact;
import contact_management.entity.Opportunity;
import contact_management.mapper.ContactMapper;
import contact_management.repository.ContactRepository;
import contact_management.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTest {
    @InjectMocks
    ContactServiceImpl contactService;

    @Mock
    KafkaUtil kafkaUtil;
    @Mock
    ContactRepository contactRepository;

    @Mock
    ContactMapper contactMapper;

    @Test
    public void deleteContact() {
        Long contactId = 1L;
        Contact contact = new Contact();
        contact.setId(contactId);
        Opportunity opportunity = new Opportunity();
        Long opportunityId = 1L;
        opportunity.setId(opportunityId);
        contact.getOpportunities().add(opportunity);
        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
        contactService.deleteContact(contactId);
        verify(contactRepository).deleteById(contactId);
        verify(contactRepository).countContacts();
        verify(kafkaUtil).sendMessage("contact-deleted", String.valueOf(contactId));

    }

    @Test
    public void testUpdateContact() {
        Long contactId = 1L;
        ContactDto contactDto = new ContactDto();
        contactDto.setEmail("newtest@gmail.com");
        contactDto.setFirstName("newname");
        contactDto.setLastName("new last name");
        contactDto.setAddress("new address");

        Contact contact = new Contact();
        contact.setEmail("test@gmail.com");
        contact.setFirstName("name");
        contact.setLastName("last name");
        contact.setAddress("address");

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        Contact contactToTest = contactService.updateContact(contactId, contactDto);

        assertEquals(contactDto.getFirstName(), contactToTest.getFirstName());
        assertEquals(contactDto.getLastName(), contactToTest.getLastName());
        assertEquals(contactDto.getEmail(), contactToTest.getEmail());
        assertEquals(contactDto.getAddress(), contactToTest.getAddress());

    }

    @Test
    public void testUpdateContactDetails() {
        Long contactId = 1L;
        ContactDto contactDto = new ContactDto();
        contactDto.setEmail("newtest@gmail.com");
        contactDto.setFirstName("newname");
        contactDto.setLastName("new last name");
        contactDto.setAddress("new address");

        Contact contact = new Contact();
        contact.setEmail("test@gmail.com");
        contact.setFirstName("name");
        contact.setLastName("last name");
        contact.setAddress("address");

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
        when(contactMapper.toDto(contact)).thenReturn(contactDto);

        ContactDto contactToTest = contactService.updateContactDetails(contactId, contactDto);

        assertEquals(contactDto.getFirstName(), contactToTest.getFirstName());
        assertEquals(contactDto.getLastName(), contactToTest.getLastName());
        assertEquals(contactDto.getEmail(), contactToTest.getEmail());
        assertEquals(contactDto.getAddress(), contactToTest.getAddress());
    }

}
