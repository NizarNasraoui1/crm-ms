package contact_management.mapper;

import contact_management.dto.ContactDto;
import contact_management.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends GenericMapper<Contact, ContactDto>{
}
