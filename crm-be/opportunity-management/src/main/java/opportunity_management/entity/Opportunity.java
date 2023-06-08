package opportunity_management.entity;

import lombok.NoArgsConstructor;
import opportunity_management.enumeration.OpportunityStageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(name = "opportunity_contacts", joinColumns = @JoinColumn(name = "opportunity_id"))
    @Column(name = "contact_id")
    private List<Long> contactIds = new ArrayList<>();
}
