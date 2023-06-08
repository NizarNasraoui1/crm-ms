import { Component, EventEmitter, Input, OnInit,Output } from '@angular/core';
import { Note } from 'src/app/shared/models/Note';
import { BroadcastService } from 'src/app/shared/services/broadcast.service';

@Component({
  selector: 'app-save-note-modal',
  templateUrl: './save-note-modal.component.html',
  styleUrls: ['./save-note-modal.component.scss']
})
export class SaveNoteModalComponent implements OnInit {
  displaySaveModal: boolean=true;
  updateNoteComponent=false;
  title:string="";
  note:Note;
  constructor(private broadcastService:BroadcastService) { }

  ngOnInit(): void {
    
    if(!this.updateNoteComponent){
      this.note=new Note();
    }
    else{
      this.title=this.note.title;
    }
  }

  hundleNoteContentChange(noteContent:string){
    this.note.content=noteContent;
  }

  hundleNoteTitleChange(noteTitle:string){
    this.note.title=noteTitle;
  }

  onSave(){
    if(this.updateNoteComponent){
      this.saveNote("updateNote");
    }
    else{
      this.saveNote("saveNote");
    }
    
  }

  saveNote(type:string){
    this.note.title=this.title;
    this.broadcastService.boradcast(type,this.note);
    this.displaySaveModal=false;
  }



}
