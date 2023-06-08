package opportunity_management.repository;

import opportunity_management.entity.Contact;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>, CrudRepository<Contact,Long>, JpaSpecificationExecutor<Contact> {
    @Query("select c from Contact c where c.id in (:idList)")
    List<Contact> findAllById(@Param("idList") List<Long>idList);

    List<Contact> findAllByIdIn(List<Long>idList);

    @Query("select count(*) from Contact")
    @CachePut(value = "countContact")
    int countContacts();



}
