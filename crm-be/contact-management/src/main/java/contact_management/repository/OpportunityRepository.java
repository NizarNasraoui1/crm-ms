package contact_management.repository;

import contact_management.entity.Opportunity;
import contact_management.enumeration.OpportunityStageEnum;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {
    public List<Opportunity> findAllByStage(OpportunityStageEnum stage);

    @Query("select count(*) from Opportunity")
    @CachePut(value = "countOpportunities")
    int countOpportunities();

}
