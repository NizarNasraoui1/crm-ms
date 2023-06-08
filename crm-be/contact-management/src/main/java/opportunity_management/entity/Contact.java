package opportunity_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends CrmBaseEntity {
    private String firstName;
    private String lastName;
    private String address;
    private String email;



    @ManyToMany(mappedBy = "contacts")
    List<Opportunity> opportunities=new ArrayList<>();





}
