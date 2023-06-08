import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MessageService } from 'primeng/api';
import {Contact} from '../../models/contact';

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.scss']
})
export class ContactDetailsComponent implements OnInit,OnChanges {
  @Input() contact:Contact;
  @Output() updateContact=new EventEmitter<Contact>;  
  contactForm:FormGroup;

  constructor() {
    this.contactForm=new FormGroup({
      firstName:new FormControl(null,[Validators.required,Validators.minLength(1)]),
      lastName:new FormControl(null),
      address:new FormControl(null),
      email:new FormControl(null,[Validators.required,Validators.email])
    });
   }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.contact){
        this.contactForm.reset({
        firstName:this.contact.firstName,
        lastName:this.contact.lastName,
        address:this.contact.address,
        email:this.contact.email
      })
    }
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.updateContact.emit(this.contactForm.value);
  }
}
