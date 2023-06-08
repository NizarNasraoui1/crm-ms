package opportunity_management.mapper;

import opportunity_management.dto.ContactDto;
import opportunity_management.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends GenericMapper<Contact, ContactDto>{
}
