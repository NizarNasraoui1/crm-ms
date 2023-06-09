package opportunity_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactsByIdRequest {
    List<Long>contactIds;
}
