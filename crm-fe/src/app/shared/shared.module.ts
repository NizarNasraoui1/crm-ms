import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastComponent } from './components/toast/toast.component';
import {ButtonModule} from 'primeng/button';
import {ToastModule} from 'primeng/toast';
import {RippleModule} from 'primeng/ripple'
import { MessageService } from 'primeng/api';
import {PanelModule} from 'primeng/panel';
import { EditorComponent } from './components/editor/editor.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MultiSelect, MultiSelectModule } from 'primeng/multiselect';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { AngularEditorModule } from '@kolkov/angular-editor';

@NgModule({
  declarations: [ToastComponent, EditorComponent,SearchBarComponent],
  imports: [
    ButtonModule,
    RippleModule,
    CommonModule,
    RippleModule,
    ToastModule,
    PanelModule,
    FormsModule,
    ReactiveFormsModule,
    MultiSelectModule,
    DropdownModule,
    InputTextModule,
    AngularEditorModule
    

  ],
  exports:[ToastComponent,EditorComponent,SearchBarComponent],
  providers:[]
})
export class SharedModule { }
