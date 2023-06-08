package opportunity_management.entity;

import lombok.NoArgsConstructor;
import opportunity_management.enumeration.OpportunityStageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Opportunity{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;
    private String name;
    @Enumerated(EnumType.STRING)
    private OpportunityStageEnum stage;
    private LocalDateTime closeDate;

    @Embedded
    private ContactInfo contactInfo;




    //    public Opportunity(Long id, Date createDate, Date modifyDate, List<Note> noteList, String name, OpportunityStageEnum stage, LocalDateTime closeDate) {
//        super(id, createDate, modifyDate, noteList);
//        this.name = name;
//        this.stage = stage;
//        this.closeDate = closeDate;
//    }
}
