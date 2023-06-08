package opportunity_management.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class NotificationDto {

    private Long id;

    private String title;

    private String message;

    private Date createDate;

    private Boolean seen;
}
