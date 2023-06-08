package opportunity_management.repository;

import opportunity_management.entity.Note;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    List<Note> findAllByCrmBaseEntityId(Long id);

    List<Note> findAllByCrmBaseEntityIdOrderByModifyDateDesc(Long id);

    @Query("select count(*) from Note")
    @CachePut(value="noteCount")
    int countNotes();


}
