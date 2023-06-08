import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpUtilService } from 'src/app/util/service/http-util.service';
import { Contact } from '../../contacts/models/contact';
import { Opportunity } from '../models/opportunity';
import { OpportunityStageEnum } from '../models/opportunityStageEnum';

const opportunityUrl = 'api/contact-management/opportunity';

@Injectable({
    providedIn: 'root',
})
export class OpportunityService {
    constructor(private httpUtil: HttpUtilService) {}

    getAllOpportunities(): Observable<Opportunity[]> {
        return this.httpUtil.get(opportunityUrl + '/all');
    }

    updateOpportunities(
        firstContactList: Opportunity[],
        meetingScheduledList: Opportunity[],
        proposalList: Opportunity[],
        closedList: Opportunity[]
    ): Observable<Opportunity[]> {
        const opportunityList: Opportunity[] = [];
        firstContactList.forEach((opportunity) => {
            opportunity.stage = OpportunityStageEnum.FIRST_CONTACT;
            opportunityList.push(opportunity);
        });
        meetingScheduledList.forEach((opportunity) => {
            opportunity.stage = OpportunityStageEnum.MEETING_SCHEDULED;
            opportunityList.push(opportunity);
        });
        proposalList.forEach((opportunity) => {
            console.log(proposalList);
            opportunity.stage = OpportunityStageEnum.PROPOSAL;
            opportunityList.push(opportunity);
        });
        closedList.forEach((opportunity) => {
            console.log(closedList);
            opportunity.stage = OpportunityStageEnum.CLOSED;
            opportunityList.push(opportunity);
        });
        return this.httpUtil.put(opportunityUrl + '/all', opportunityList);
    }

    saveNewOpportunity(opportunityName:string, selectedContacts:Contact[]):Observable<Opportunity> {
        const opportunity: Opportunity = {};
        opportunity.name = opportunityName;
        const contactList: Contact[] = [];
        if(selectedContacts){
            selectedContacts.forEach((contact) => {
                const newContact: Contact = new Contact(contact.id);
                contactList.push(newContact);
            });
            opportunity.contacts = contactList;
        }
        return this.httpUtil.post(opportunityUrl,opportunity);
    }

    deleteOpportunity(id:number):Observable<any>{
        return this.httpUtil.delete(`${opportunityUrl}/${id}`);
    }
}
