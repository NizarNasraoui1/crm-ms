package opportunity_management.proxy;

import opportunity_management.dto.ContactDto;
import opportunity_management.dto.ContactsByIdRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="contact-management")
public interface ContactProxy {
    @GetMapping("/api/contact-management/contact/all-in")
    public List<ContactDto> getAllContactsIn(@RequestBody ContactsByIdRequest contactsByIdRequest);
}
