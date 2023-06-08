package opportunity_management.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo {
    private Long contactId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
}
