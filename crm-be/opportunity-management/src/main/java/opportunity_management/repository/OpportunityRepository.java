package opportunity_management.repository;

import opportunity_management.entity.Opportunity;
import opportunity_management.enumeration.OpportunityStageEnum;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {
    List<Opportunity> findAllByStage(OpportunityStageEnum stage);

    List<Opportunity> findAllByIdIn(List<Long>idsList);

    @Query("select count(*) from Opportunity")
    @CachePut(value = "countOpportunities")
    int countOpportunities();
    @Modifying
    @Query(value = "update opportunity_contacts SET contact_id=null WHERE contact_id=:id", nativeQuery = true)
    void deleteContactFromOpportunities(@Param("id") Long id);

}