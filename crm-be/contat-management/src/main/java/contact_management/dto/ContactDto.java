package contact_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class ContactDto extends CrmBaseEntityDto{
    private String firstName;
    private String lastName;
    private String address;
    private String email;


    public ContactDto(Long id, Date createDate, Date modifyDate, List<NoteDto> noteList, String firstName, String lastName, String address, String email) {
        super(id, createDate, modifyDate, noteList);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;

    }
}
