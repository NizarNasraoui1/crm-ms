package contact_management.mapper;

import contact_management.dto.ContactDto;
import contact_management.dto.NoteDto;
import contact_management.dto.OpportunityDto;
import contact_management.entity.Contact;
import contact_management.entity.Note;
import contact_management.entity.Opportunity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-11T18:44:41+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.4.1 (Oracle Corporation)"
)
@Component
public class OpportunityMapperImpl implements OpportunityMapper {

    @Override
    public List<OpportunityDto> toDtos(List<Opportunity> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<OpportunityDto> list = new ArrayList<OpportunityDto>( dtos.size() );
        for ( Opportunity opportunity : dtos ) {
            list.add( toDto( opportunity ) );
        }

        return list;
    }

    @Override
    public List<Opportunity> toBos(List<OpportunityDto> bos) {
        if ( bos == null ) {
            return null;
        }

        List<Opportunity> list = new ArrayList<Opportunity>( bos.size() );
        for ( OpportunityDto opportunityDto : bos ) {
            list.add( toBo( opportunityDto ) );
        }

        return list;
    }

    @Override
    public OpportunityDto toDto(Opportunity bo) {
        if ( bo == null ) {
            return null;
        }

        OpportunityDto opportunityDto = new OpportunityDto();

        opportunityDto.setId( bo.getId() );
        opportunityDto.setCreateDate( bo.getCreateDate() );
        opportunityDto.setModifyDate( bo.getModifyDate() );
        opportunityDto.setNoteList( noteListToNoteDtoList( bo.getNoteList() ) );
        opportunityDto.setName( bo.getName() );
        opportunityDto.setStage( bo.getStage() );
        opportunityDto.setCloseDate( bo.getCloseDate() );
        opportunityDto.setContacts( contactListToContactDtoList( bo.getContacts() ) );

        return opportunityDto;
    }

    @Override
    public Opportunity toBo(OpportunityDto dto) {
        if ( dto == null ) {
            return null;
        }

        Opportunity opportunity = new Opportunity();

        opportunity.setId( dto.getId() );
        opportunity.setCreateDate( dto.getCreateDate() );
        opportunity.setModifyDate( dto.getModifyDate() );
        opportunity.setNoteList( noteDtoListToNoteList( dto.getNoteList() ) );
        opportunity.setName( dto.getName() );
        opportunity.setStage( dto.getStage() );
        opportunity.setCloseDate( dto.getCloseDate() );
        opportunity.setContacts( contactDtoListToContactList( dto.getContacts() ) );

        return opportunity;
    }

    @Override
    public Opportunity fillBo(OpportunityDto dto, Opportunity bo) {
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
        bo.setName( dto.getName() );
        bo.setStage( dto.getStage() );
        bo.setCloseDate( dto.getCloseDate() );
        if ( bo.getContacts() != null ) {
            List<Contact> list1 = contactDtoListToContactList( dto.getContacts() );
            if ( list1 != null ) {
                bo.getContacts().clear();
                bo.getContacts().addAll( list1 );
            }
            else {
                bo.setContacts( null );
            }
        }
        else {
            List<Contact> list1 = contactDtoListToContactList( dto.getContacts() );
            if ( list1 != null ) {
                bo.setContacts( list1 );
            }
        }

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

    protected ContactDto contactToContactDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        contactDto.setId( contact.getId() );
        contactDto.setCreateDate( contact.getCreateDate() );
        contactDto.setModifyDate( contact.getModifyDate() );
        contactDto.setNoteList( noteListToNoteDtoList( contact.getNoteList() ) );
        contactDto.setFirstName( contact.getFirstName() );
        contactDto.setLastName( contact.getLastName() );
        contactDto.setAddress( contact.getAddress() );
        contactDto.setEmail( contact.getEmail() );

        return contactDto;
    }

    protected List<ContactDto> contactListToContactDtoList(List<Contact> list) {
        if ( list == null ) {
            return null;
        }

        List<ContactDto> list1 = new ArrayList<ContactDto>( list.size() );
        for ( Contact contact : list ) {
            list1.add( contactToContactDto( contact ) );
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

    protected Contact contactDtoToContact(ContactDto contactDto) {
        if ( contactDto == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setId( contactDto.getId() );
        contact.setCreateDate( contactDto.getCreateDate() );
        contact.setModifyDate( contactDto.getModifyDate() );
        contact.setNoteList( noteDtoListToNoteList( contactDto.getNoteList() ) );
        contact.setFirstName( contactDto.getFirstName() );
        contact.setLastName( contactDto.getLastName() );
        contact.setAddress( contactDto.getAddress() );
        contact.setEmail( contactDto.getEmail() );

        return contact;
    }

    protected List<Contact> contactDtoListToContactList(List<ContactDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Contact> list1 = new ArrayList<Contact>( list.size() );
        for ( ContactDto contactDto : list ) {
            list1.add( contactDtoToContact( contactDto ) );
        }

        return list1;
    }
}
