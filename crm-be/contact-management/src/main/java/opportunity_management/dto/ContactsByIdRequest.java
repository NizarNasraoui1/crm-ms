package opportunity_management.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ContactsByIdRequest {
    List<Long>contactIds;
}
