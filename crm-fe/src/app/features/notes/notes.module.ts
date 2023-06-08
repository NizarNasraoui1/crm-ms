import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotesComponent } from './components/notes/notes.component';
import { NoteComponent } from './components/note/note.component';
import {PanelModule} from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { FormsModule } from '@angular/forms';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { SharedModule } from 'src/app/shared/shared.module';
import { SaveNoteModalComponent } from './components/save-note-modal/save-note-modal.component';
import { DialogModule } from 'primeng/dialog';
import { SaveNoteDirective } from './directives/save-note.directive';
import {InputTextModule} from 'primeng/inputtext';
import { NoteService } from './services/note.service';


@NgModule({
  declarations: [
    NotesComponent,
    NoteComponent,
    SaveNoteModalComponent,
    SaveNoteDirective
  ],
  imports: [
    CommonModule,
    PanelModule,
    ButtonModule,
    FormsModule,
    AngularEditorModule,
    SharedModule,
    DialogModule,
    InputTextModule
  ],
  exports: [NotesComponent,NoteComponent],
  providers: [NoteService]
})
export class NotesModule { }
