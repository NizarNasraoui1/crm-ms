import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import { OpportunityService } from '../../services/opportunity.service';
import { Opportunity } from '../../models/opportunity';
import { OpportunityStageEnum } from '../../models/opportunityStageEnum';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { AddOpportunityModalComponent } from '../add-opportunity-modal/add-opportunity-modal.component';

@Component({
  selector: 'app-opportunity-list',
  templateUrl: './opportunity-list.component.html',
  styleUrls: ['./opportunity-list.component.scss']
})
export class OpportunityListComponent implements OnInit {
    opportunity:Opportunity={};
    opportunityList:Opportunity[];
    firstContactList:Opportunity[]=[];
    meetingScheduledList:Opportunity[]=[];
    proposalList:Opportunity[]=[];
    closedList:Opportunity[]=[];
    opportunityListSave$:Subscription;
    @ViewChild('addOpportunityModal',{read:ViewContainerRef})
    addOpportunityModal:ViewContainerRef;
    viewSaveModal:boolean=false;

  constructor(private opportunityService:OpportunityService,private router:Router) { }

  ngOnInit(): void {
    this.getAllOpportunities();
  }

  drop(event: CdkDragDrop<any[]>) {
    if (event.previousContainer === event.container) {

      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {

      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
    this.opportunityListSave$=this.opportunityService.updateOpportunities(this.firstContactList,this.meetingScheduledList,this.proposalList,this.closedList).subscribe((res)=>{});
  }

  getAllOpportunities(){
    this.opportunityService.getAllOpportunities().subscribe((res)=>{
        this.opportunityList=[];
        this.opportunityList=[...res];
        this.fillInStepLists();
    });
  }

  fillInStepLists(){
    this.initLists();
    this.opportunityList.forEach((opportunity)=>{
        let stage=opportunity.stage;
        if(OpportunityStageEnum.FIRST_CONTACT===stage){
            this.firstContactList.push(opportunity);
        }
        else if(OpportunityStageEnum.MEETING_SCHEDULED===stage){
            this.meetingScheduledList.push(opportunity);
        }
        else if(OpportunityStageEnum.PROPOSAL==stage){
            this.proposalList.push(opportunity);
        }
        else if(OpportunityStageEnum.CLOSED==stage){
            this.closedList.push(opportunity);
        }
    });
  }

  initLists(){
    this.firstContactList=[];
    this.meetingScheduledList=[];
    this.proposalList=[];
    this.closedList=[];

  }

  navigateToContactDetails(contactId:number){
    this.router.navigate(['/contact/details/',contactId])
  }

  openAddOpportunityModal(){
    this.addOpportunityModal.clear();
        const openFileComponentRef = this.addOpportunityModal.createComponent(AddOpportunityModalComponent);
  }

  onViewSaveModal(){
    this.viewSaveModal=true;
  }

  onSaveAndCloseModal(event:any){
    this.viewSaveModal=false;
    this.getAllOpportunities();
  }
  deleteOpportunity(opportunity:Opportunity){
    if(opportunity.id){
        this.opportunityService.deleteOpportunity(opportunity.id).subscribe(()=>{
            this.getAllOpportunities();
        })
    }
  }

}
