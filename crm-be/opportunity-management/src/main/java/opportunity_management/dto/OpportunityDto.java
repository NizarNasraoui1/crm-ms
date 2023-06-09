package opportunity_management.dto;

import opportunity_management.enumeration.OpportunityStageEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class OpportunityDto{
    private Long id;
    private Date createDate;
    private Date modifyDate;
    private String name;
    private OpportunityStageEnum stage;
    private LocalDateTime closeDate;
    private List<ContactDto> contacts =new ArrayList<>();
}
