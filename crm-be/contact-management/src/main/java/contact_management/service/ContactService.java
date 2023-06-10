package contact_management.service;

import contact_management.dto.ContactDto;
import contact_management.dto.ContactsByIdRequest;
import contact_management.dto.DynamicSearchDto;
import contact_management.dto.commons.FilteredPageWrapper;
import contact_management.dto.commons.SearchConfiguration;
import contact_management.dto.commons.SearchFields;

import java.util.List;

public interface ContactService extends CrmBaseEntityService{

    ContactDto findContactById(Long id);

    ContactDto saveContact(ContactDto contactDto);

    ContactDto updateContactDetails(Long id,ContactDto contactDto);

    void deleteContact(Long id);

    SearchConfiguration getSearchParams();

    FilteredPageWrapper<ContactDto> getContactFilteredPage(String searchWord, SearchFields searchFields, int page, int pageSize, String sortField, String sortDirection);

    Boolean hasOpportunity(Long id);

    List<DynamicSearchDto> findContactDynamically(String searchWordParam);

    int countContacts();

    List<ContactDto> findContactsByIds(ContactsByIdRequest contactsByIdRequest);

    // FilteredPageWrapper<ContactDto> getFilteredPage(String searchWord, SearchFields searchFields, int page, int pageSize, String sortField, String sortDirection);

}
