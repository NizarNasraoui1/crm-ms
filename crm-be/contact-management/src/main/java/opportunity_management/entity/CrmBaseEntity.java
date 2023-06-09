package opportunity_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class  CrmBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyDate;

    @OneToMany(mappedBy = "crmBaseEntity",cascade = CascadeType.ALL)
    private List<File> fileList;
    @OneToMany(mappedBy = "crmBaseEntity",cascade = CascadeType.ALL)
    private List<Note>noteList;
}
