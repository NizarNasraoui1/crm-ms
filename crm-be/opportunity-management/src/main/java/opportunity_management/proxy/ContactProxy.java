package opportunity_management.proxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import opportunity_management.dto.ContactDto;
import opportunity_management.dto.ContactsByIdRequest;
import opportunity_management.dto.OpportunityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="contact-management")
@Retry(name = "contact-management-service",fallbackMethod = "fallbackAfterRetry")
public interface ContactProxy {
    @GetMapping("/api/contact-management/contact/all-in")
    public List<ContactDto> getAllContactsIn(@RequestBody ContactsByIdRequest contactsByIdRequest);

    public default List<ContactDto> fallbackAfterRetry(Exception e){
        return new ArrayList<ContactDto>();
    }
}
