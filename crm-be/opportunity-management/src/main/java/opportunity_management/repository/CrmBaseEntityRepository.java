package opportunity_management.repository;

import opportunity_management.entity.CrmBaseEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface CrmBaseEntityRepository extends JpaRepository<CrmBaseEntity,Long>,JpaSpecificationExecutor<CrmBaseEntity> {

}
