package opportunity_management.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import opportunity_management.dto.ContactDto;
import opportunity_management.dto.NoteDto;
import opportunity_management.entity.Contact;
import opportunity_management.entity.Note;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-09T17:35:13+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public List<ContactDto> toDtos(List<Contact> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ContactDto> list = new ArrayList<ContactDto>( dtos.size() );
        for ( Contact contact : dtos ) {
            list.add( toDto( contact ) );
        }

        return list;
    }

    @Override
    public List<Contact> toBos(List<ContactDto> bos) {
        if ( bos == null ) {
            return null;
        }

        List<Contact> list = new ArrayList<Contact>( bos.size() );
        for ( ContactDto contactDto : bos ) {
            list.add( toBo( contactDto ) );
        }

        return list;
    }

    @Override
    public ContactDto toDto(Contact bo) {
        if ( bo == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setId( bo.getId() );
        contactDto.setCreateDate( bo.getCreateDate() );
        contactDto.setModifyDate( bo.getModifyDate() );
        contactDto.setNoteList( noteListToNoteDtoList( bo.getNoteList() ) );
        contactDto.setFirstName( bo.getFirstName() );
        contactDto.setLastName( bo.getLastName() );
        contactDto.setAddress( bo.getAddress() );
        contactDto.setEmail( bo.getEmail() );

        return contactDto;
    }

    @Override
    public Contact toBo(ContactDto dto) {
        if ( dto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setId( dto.getId() );
        contact.setCreateDate( dto.getCreateDate() );
        contact.setModifyDate( dto.getModifyDate() );
        contact.setNoteList( noteDtoListToNoteList( dto.getNoteList() ) );
        contact.setFirstName( dto.getFirstName() );
        contact.setLastName( dto.getLastName() );
        contact.setAddress( dto.getAddress() );
        contact.setEmail( dto.getEmail() );

        return contact;
    }

    @Override
    public Contact fillBo(ContactDto dto, Contact bo) {
        if ( dto == null ) {
            return null;
        }

        bo.setId( dto.getId() );
        bo.setCreateDate( dto.getCreateDate() );
        bo.setModifyDate( dto.getModifyDate() );
        if ( bo.getNoteList() != null ) {
            List<Note> list = noteDtoListToNoteList( dto.getNoteList() );
            if ( list != null ) {
                bo.getNoteList().clear();
                bo.getNoteList().addAll( list );
            }
            else {
                bo.setNoteList( null );
            }
        }
        else {
            List<Note> list = noteDtoListToNoteList( dto.getNoteList() );
            if ( list != null ) {
                bo.setNoteList( list );
            }
        }
        bo.setFirstName( dto.getFirstName() );
        bo.setLastName( dto.getLastName() );
        bo.setAddress( dto.getAddress() );
        bo.setEmail( dto.getEmail() );

        return bo;
    }

    protected NoteDto noteToNoteDto(Note note) {
        if ( note == null ) {
            return null;
        }

        NoteDto noteDto = new NoteDto();

        noteDto.setId( note.getId() );
        noteDto.setTitle( note.getTitle() );
        noteDto.setContent( note.getContent() );

        return noteDto;
    }

    protected List<NoteDto> noteListToNoteDtoList(List<Note> list) {
        if ( list == null ) {
            return null;
        }

        List<NoteDto> list1 = new ArrayList<NoteDto>( list.size() );
        for ( Note note : list ) {
            list1.add( noteToNoteDto( note ) );
        }

        return list1;
    }

    protected Note noteDtoToNote(NoteDto noteDto) {
        if ( noteDto == null ) {
            return null;
        }

        Note note = new Note();

        note.setId( noteDto.getId() );
        note.setTitle( noteDto.getTitle() );
        note.setContent( noteDto.getContent() );

        return note;
    }

    protected List<Note> noteDtoListToNoteList(List<NoteDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Note> list1 = new ArrayList<Note>( list.size() );
        for ( NoteDto noteDto : list ) {
            list1.add( noteDtoToNote( noteDto ) );
        }

        return list1;
    }
}
