import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddOpportunityModalComponent } from './add-opportunity-modal.component';

describe('AddOpportunityModalComponent', () => {
  let component: AddOpportunityModalComponent;
  let fixture: ComponentFixture<AddOpportunityModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddOpportunityModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddOpportunityModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
