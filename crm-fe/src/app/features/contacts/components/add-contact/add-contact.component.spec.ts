import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { AddContactComponent } from './add-contact.component';
import { ContactService } from '../../service/contact.service';
import { MessageService } from 'primeng/api';
import { RouterTestingModule } from '@angular/router/testing';
import { ADD_CONTACT } from '../../models/add-contact';
import { ToastService } from 'src/app/shared/services/toast.service';
import { CONTACT_MOCK } from 'src/app/shared/mocks/contact-mocks';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

describe('AddContactComponent', () => {
  let component: AddContactComponent;
  let fixture: ComponentFixture<AddContactComponent>;
  let contactService: ContactService;
  let toastService: ToastService;
  let addContactFields = ADD_CONTACT;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddContactComponent ],
      imports: [HttpClientTestingModule,RouterTestingModule],
      providers: [ContactService,MessageService,FormBuilder]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddContactComponent);
    component = fixture.componentInstance;
    contactService= TestBed.inject(ContactService);
    toastService= TestBed.inject(ToastService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should contain title',()=>{
    const title = fixture.debugElement.nativeElement.querySelector('h2').textContent;
    expect(title).toContain(addContactFields.title);
  });

  it('should validate email',()=>{
    component.contactForm.get('email')?.setValue('invalid email');
    expect(component.contactForm.invalid).toBeTruthy();
  });

  it('should toaster be displayed after successeful submit',()=>{
    spyOn(toastService,'addSuccessMessage');
    spyOn(contactService,'addContact').and.returnValue(of(CONTACT_MOCK));
    component.saveUser(CONTACT_MOCK);
    fixture.detectChanges();
    expect(toastService.addSuccessMessage).toHaveBeenCalled();
  })





});
