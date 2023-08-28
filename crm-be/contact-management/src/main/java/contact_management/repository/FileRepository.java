package contact_management.repository;

import contact_management.entity.File;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {
    Optional<File>findFileByPath(String fileName);
    @Query("select count(*) from File")
    @CachePut(value = "countFiles")
    int countFiles();

    @Query(value = "SELECT path FROM File f WHERE crm_base_entity_id=:crmBaseEntityId",nativeQuery = true)
    List<String> findAllByCrmBaseEntity(@Param("crmBaseEntityId") Long crmBaseEntityId);

    boolean existsByPath(String path);
}
