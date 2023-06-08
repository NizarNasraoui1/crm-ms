package opportunity_management.service.impl;

import opportunity_management.dto.NoteDto;
import opportunity_management.entity.CrmBaseEntity;
import opportunity_management.entity.Note;
import opportunity_management.mapper.NoteMapper;
import opportunity_management.repository.CrmBaseEntityRepository;
import opportunity_management.repository.NoteRepository;
import opportunity_management.service.NoteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private CrmBaseEntityRepository crmBaseEntityRepository;
    @Override
    public List<NoteDto> getNoteListByAccountId(Long id) {
        return noteMapper.toDtos(noteRepository.findAllByCrmBaseEntityId(id));
    }

    @Override
    public NoteDto saveNote(Long id, NoteDto noteDto) {
        CrmBaseEntity crmBaseEntity=crmBaseEntityRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        Note note=noteMapper.toBo(noteDto);
        note.setCrmBaseEntity(crmBaseEntity);
        Note savedNote=noteRepository.save(note);
        noteRepository.countNotes();
        return noteMapper.toDto(savedNote);
    }

    @Override
    public NoteDto updateNote(Long id,NoteDto noteDto) {
        Note note=noteRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        note.setContent(noteDto.getContent());
        note.setTitle(noteDto.getTitle());
        return noteMapper.toDto(noteRepository.save(note));
    }

    @Override
    public void deleteNote(Long id) {
        this.noteRepository.deleteById(id);
        noteRepository.countNotes();
    }

    @Override
    @Cacheable(value="noteCount")
    public int countNotes() {
        return noteRepository.countNotes();
    }
}
