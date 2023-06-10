package contact_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import contact_management.enumeration.OpportunityStageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
public class Opportunity extends CrmBaseEntity{
    private String name;
    @Enumerated(EnumType.STRING)
    private OpportunityStageEnum stage;
    private LocalDateTime closeDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="opportunity_contact",joinColumns = @JoinColumn(name = "opportunity_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="contact_id",referencedColumnName = "id"))
    @JsonIgnore
    List<Contact> contacts;

    public Opportunity() {
        this.contacts=new ArrayList<>();
    }




    //    public Opportunity(Long id, Date createDate, Date modifyDate, List<Note> noteList, String name, OpportunityStageEnum stage, LocalDateTime closeDate) {
//        super(id, createDate, modifyDate, noteList);
//        this.name = name;
//        this.stage = stage;
//        this.closeDate = closeDate;
//    }
}
