package opportunity_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrmBaseEntityDto{
    private Long id;
    private Date createDate;
    private Date modifyDate;
    private List<NoteDto> noteList;
}
