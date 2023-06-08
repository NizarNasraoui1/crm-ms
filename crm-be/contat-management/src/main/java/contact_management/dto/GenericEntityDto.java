package contact_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GenericEntityDto {

    private Long id;
    private Date createDate;
    private Date modifyDate;


}