import { OnDestroy } from '@angular/core';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild, ViewContainerRef } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { MessageService } from 'primeng/api';
import { Subscription } from 'rxjs';
import { Note } from 'src/app/shared/models/Note';
import { BroadcastService } from 'src/app/shared/services/broadcast.service';
import { NoteService } from '../../services/note.service';
import { SaveNoteModalComponent } from '../save-note-modal/save-note-modal.component';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit,OnDestroy {
  @Input() noteList:Note[];
  @Input() contactId:number;
  @ViewChild('saveNoteModal',{read:ViewContainerRef}) saveNoteModal!:ViewContainerRef;
  @Output() noteListUpdatedEvent=new EventEmitter<any>;
  saveSubscription:Subscription;
  updateSubscription:Subscription;



  constructor(private broadcastService:BroadcastService,private noteService:NoteService,private messageService: MessageService) { }


  ngOnInit(): void {
    console.log(this.contactId)
    this.subscribeToSaveNoteEvents();
  }

  subscribeToSaveNoteEvents(){
    this.saveSubscription=this.broadcastService.subscribe("saveNote",(note)=>this.saveNote(note));
    this.updateSubscription=this.broadcastService.subscribe("updateNote",(note)=>this.updateNote(note));

  }

  onAddNote(){
    this.saveNoteModal.clear();
    const saveNoteModalComponent=this.saveNoteModal.createComponent(SaveNoteModalComponent);
  }

  saveNote(note:Note){
    this.noteService.saveNote(this.contactId,note).subscribe((res)=>{
      this.messageService.add({severity:'info', summary: 'Info', detail: 'Note Saved Succefully'});
      this.noteListUpdatedEvent.emit();
      this.onNoteListUpdated();
    })
  }

  updateNote(note:Note){
    this.noteService.updateNote(note).subscribe((res)=>{
      this.messageService.add({severity:'info', summary: 'Info', detail: 'Note Updated Succefully'});
      this.onNoteListUpdated();
    })
  }

  onNoteListUpdated(){
    this.noteListUpdatedEvent.emit();
  }



  openUpdateNote(note:Note){
    this.saveNoteModal.clear();
    const saveNoteModalComponent=this.saveNoteModal.createComponent(SaveNoteModalComponent);
    saveNoteModalComponent.instance.updateNoteComponent=true;
    saveNoteModalComponent.instance.note=note;

  }

  onDeletedNote(){
    this.onNoteListUpdated();
  }

  ngOnDestroy(): void {
    this.saveSubscription.unsubscribe();
    this.updateSubscription.unsubscribe();
}

}
