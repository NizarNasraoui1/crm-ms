import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ContactService } from '../../service/contact.service';
import { MessageService } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.scss']
})
export class AddContactComponent implements OnInit {
  contactForm:FormGroup;


  constructor(private contactService:ContactService,private messageService: MessageService,
    private router:Router) {
    this.contactForm=new FormGroup({
      firstName:new FormControl(null,[Validators.required,Validators.minLength(1)]),
      lastName:new FormControl(null),
      address:new FormControl(null),
      email:new FormControl(null,[Validators.required,Validators.email])
    });
   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.contactService.addContact(this.contactForm.value).subscribe({
      next: ()=>{
        this.messageService.add({severity:'info', summary: 'Info', detail: 'Contact Added Succefully'});
        this.router.navigate(['/contact']);
      },
      error: ()=>{
        this.messageService.add({severity:'error', summary: 'Warn', detail: 'Cannot Save Contact'});
      }
    })
  }

}
