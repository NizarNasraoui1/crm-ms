package opportunity_management.service.impl;

import opportunity_management.dto.StatisticsDto;
import opportunity_management.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private ContactService contactService;
//    @Autowired
//    private FileUploadService fileUploadService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private OpportunityService opportunityService;
    @Override
    public StatisticsDto getStatistics() {
        int contactsCount= contactService.countContacts();
//        int fileCount= fileUploadService.countFiles();
        int opportunityCount=opportunityService.countOpportunities();
        int noteCount= noteService.countNotes();
        return new StatisticsDto(contactsCount,opportunityCount,noteCount,0);
    }
}
