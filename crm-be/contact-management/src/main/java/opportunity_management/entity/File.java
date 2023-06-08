package opportunity_management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String path;
    @ManyToOne
    @JoinColumn(name="crm_base_entity_id",referencedColumnName = "id")
    CrmBaseEntity crmBaseEntity;
}
