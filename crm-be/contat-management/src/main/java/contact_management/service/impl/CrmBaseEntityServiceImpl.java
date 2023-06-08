package contact_management.service.impl;

import contact_management.dto.CrmBaseEntityDto;
import contact_management.dto.NoteDto;
import contact_management.dto.commons.SearchConfiguration;
import contact_management.entity.Contact;
import contact_management.entity.CrmBaseEntity;
import contact_management.entity.Note;
import contact_management.mapper.ContactMapper;
import contact_management.mapper.CrmBaseEntityMapper;
import contact_management.mapper.NoteMapper;
import contact_management.repository.CrmBaseEntityRepository;
import contact_management.repository.NoteRepository;
import contact_management.service.CrmBaseEntityService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("crmBaseEntityService")
@Primary
@Transactional
public class CrmBaseEntityServiceImpl implements CrmBaseEntityService {

    @Autowired
    protected CrmBaseEntityMapper crmBaseEntityMapper;
    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CrmBaseEntityRepository crmBaseEntityRepository;

    @Autowired
    private NoteMapper noteMapper;


    @Override
    public CrmBaseEntityDto getCrmBaseEntityById(Long id) {
        CrmBaseEntity crmBaseEntity= crmBaseEntityRepository.findById(id).orElseThrow(()->new EntityNotFoundException("contact not found"));
        if (crmBaseEntity instanceof Contact){
            return contactMapper.toDto((Contact) crmBaseEntity);
        }
        return crmBaseEntityMapper.toDto(crmBaseEntity);
    }

    @Override
    public void deleteCrmBaseEntityById(Long id) {
        crmBaseEntityRepository.deleteById(id);
    }

    @Override
    public SearchConfiguration getSearchParams() {
        return null;
    }

    @Override
    public NoteDto getCrmBaseEntityNoteById(Long id) {
        return null;
    }

    @Override
    public CrmBaseEntityDto addNoteToCrmBaseEntity(Long id,NoteDto noteDto) {
        Note note=noteMapper.toBo(noteDto);
        CrmBaseEntity crmBaseEntity= crmBaseEntityRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        crmBaseEntity.getNoteList().add(note);
        note.setCrmBaseEntity(crmBaseEntity);
        return crmBaseEntityMapper.toDto(crmBaseEntityRepository.save(crmBaseEntity));
    }

    @Override
    public List<NoteDto> getCrmBaseEntityNotes(Long id) {
        return noteMapper.toDtos(new ArrayList<>());
    }


}
