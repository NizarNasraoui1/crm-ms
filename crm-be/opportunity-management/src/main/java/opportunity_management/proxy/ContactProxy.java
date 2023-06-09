package opportunity_management.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="contact-management")
public interface ContactProxy {
//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
//    public CurrencyConversion retrieveExchangeValue(
//            @PathVariable String from,
//            @PathVariable String to);
}
