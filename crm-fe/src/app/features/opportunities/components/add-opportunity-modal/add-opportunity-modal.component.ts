import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ContactService } from 'src/app/features/contacts/service/contact.service';
import { Opportunity } from '../../models/opportunity';
import { OpportunityService } from '../../services/opportunity.service';
import {Contact} from '../../../contacts/models/contact';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-add-opportunity-modal',
    templateUrl: './add-opportunity-modal.component.html',
    styleUrls: ['./add-opportunity-modal.component.scss'],
})
export class AddOpportunityModalComponent implements OnInit,OnDestroy {
    @Input() displaySaveModal = false;
    @Output() saveAndClose=new EventEmitter<any>();
    filteredContacts: any[];
    selectedContacts: any[];
    searchWord:string;
    opportunityName:string;
    saveOpportunitySubscription$:Subscription;

    constructor(private contactService:ContactService,private opportunityService:OpportunityService) {}

    ngOnInit(): void {}

    filterOpportunities(event:any){
        this.contactService.searchContactDynamically(event.query).subscribe((res)=>{
            this.filteredContacts=[];
            this.filteredContacts=[...res];
        })
    }

    saveOpportunity(){
        this.saveOpportunitySubscription$=this.opportunityService.saveNewOpportunity(this.opportunityName,this.selectedContacts).subscribe((opportunity)=>{
            this.saveAndClose.emit();
        })
    }

    

    ngOnDestroy(): void {
        if(this.saveOpportunitySubscription$){
            this.saveOpportunitySubscription$.unsubscribe();
        }
    }
}
