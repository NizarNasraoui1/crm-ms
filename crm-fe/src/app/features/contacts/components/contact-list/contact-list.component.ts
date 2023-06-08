import { HttpClient } from '@angular/common/http';
import { ContactService } from './../../service/contact.service';
import { Component, OnInit } from '@angular/core';
import { Contact } from '../../models/contact';
import { SearchFields } from 'src/app/shared/models/searchFields';
import { FilteredPageWrapper } from 'src/app/shared/models/filteredPageWrapper';
import { PageRequestParams } from 'src/app/shared/models/pageRequestParams';
import { MessageService } from 'primeng/api';
import { SearchParams } from 'src/app/shared/models/searchParams';
import { SearchForm } from 'src/app/shared/models/searchForm';

@Component({
  selector: 'app-contact-list',
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.scss'],
})
export class ContactListComponent implements OnInit {

  contacts:Contact[]=[];
  searchParams:SearchParams;
  searchFields:SearchFields;
  pageSize:number=10;
  totalRecords:number=0;

  constructor(private contactService:ContactService,private http:HttpClient,private messageService: MessageService) { }

  ngOnInit(): void {
    this.contactService.getSearchParams().subscribe((res) => {
      this.searchParams = res;
      let pageRequest = new PageRequestParams(0, 10);
      this.searchFields = new SearchFields([this.searchParams.searchFields[0].name]);
      this.getContactPage(pageRequest,this.searchFields);
    });
    

  }

  getContactPage(pageRequest:PageRequestParams,searchFields:SearchFields){
    this.contactService.getContactPage(pageRequest,searchFields).subscribe((res:FilteredPageWrapper<Contact>)=>{
      this.contacts=[];
      this.totalRecords=res.count;
      this.contacts=res.results;
  })
  }


  onPageChange($event:any){
    this.pageSize=$event.rows;
    let pageRequest=new PageRequestParams($event.page,$event.rows);
    this.getContactPage(pageRequest,this.searchFields);
  }

  deleteContact(id:number){
    this.contactService.deleteCotnact(id).subscribe({
      next: ()=>{
        this.messageService.add({severity:'success', summary: 'Success', detail: 'Deleted Succefully'});
        let pageRequest = new PageRequestParams(0, 10);
        this.getContactPage(pageRequest,this.searchFields);
      },
    error: ()=>{
      this.messageService.add({severity:'error', summary: 'Warn', detail: 'Delete fails'});
    }
  });
  }

  onSearchFormChange(searchForm:SearchForm){
    let pageRequest = new PageRequestParams(0, this.pageSize,searchForm.searchWord,searchForm.sortDirection,searchForm.sortField);
    let searchFields = new SearchFields(searchForm.searchFields);
    this.getContactPage(pageRequest,searchFields);
  }

}
