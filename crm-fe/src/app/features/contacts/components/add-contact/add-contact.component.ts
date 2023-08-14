import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ContactService } from '../../service/contact.service';
import { Router } from '@angular/router';
import { ADD_CONTACT } from '../../models/add-contact';
import { ToastService } from 'src/app/shared/services/toast.service';
import { Contact } from '../../models/contact';
@Component({
    selector: 'app-add-contact',
    templateUrl: './add-contact.component.html',
    styleUrls: ['./add-contact.component.scss']
})
export class AddContactComponent implements OnInit {
    contactForm: FormGroup;
    isFormValid: boolean = true;
    addConctactFields = ADD_CONTACT;


    constructor(private contactService: ContactService, private toastMessage: ToastService,
        private router: Router,private fb:FormBuilder) {

    }

    ngOnInit(): void {
        this.initContactForm();
    }

    onSubmit() {
        this.isFormValid = this.contactForm.valid;
        if (this.isFormValid){
            this.saveUser(this.contactForm.value);
        }
    }

    saveUser(contact:Contact){
        if (this.isFormValid) {
            this.contactService.addContact(contact).subscribe({
                next: () => {
                    this.toastMessage.addSuccessMessage('Contact Added Succefully');
                    this.router.navigate(['/contact']);
                },
                error: () => {
                    this.toastMessage.addWarnMessage('Cannot Save Contact');
                }
            })
        }
    }

    initContactForm(){
        this.contactForm = this.fb.group({
            firstName: ['', [Validators.required, Validators.minLength(1)]],
            lastName: [''],
            address: [''],
            email: ['', [Validators.required, Validators.email]]
        });
        // this.contactForm = new FormGroup({
        //     firstName: new FormControl(null, [Validators.required, Validators.minLength(1)]),
        //     lastName: new FormControl(null),
        //     address: new FormControl(null),
        //     email: new FormControl(null, [Validators.required, Validators.email])
        // });
    }

}
