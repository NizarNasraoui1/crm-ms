import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OpportunitiesRoutingModule } from './opportunities-routing.module';
import { OpportunityListComponent } from './components/opportunity-list/opportunity-list.component';

import { MatCardModule } from '@angular/material/card';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ButtonModule } from 'primeng/button';
import { AddOpportunityModalComponent } from './components/add-opportunity-modal/add-opportunity-modal.component';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { AutoCompleteModule} from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    OpportunityListComponent,
    AddOpportunityModalComponent
  ],
  imports: [
    CommonModule,
    OpportunitiesRoutingModule,
    MatCardModule,
    DragDropModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    AutoCompleteModule,
    FormsModule

  ]
})
export class OpportunitiesModule { }
