import { Injectable } from '@angular/core';
import { Note } from 'src/app/shared/models/Note';
import { HttpUtilService } from 'src/app/util/service/http-util.service';

const noteUrl="api/note";
const crmBaseEntityUrl="api/note/crm-base-entity";

@Injectable()
export class NoteService {

  constructor(private httpUtilService: HttpUtilService) { }

  saveNote(contactId:number,note:Note){
    return this.httpUtilService.post(`${crmBaseEntityUrl}/${contactId}`,note);
  }

  updateNote(note:Note){
    return this.httpUtilService.put(`${noteUrl}/${note.id}`,note);
  }

  deleteNoteByid(id:number){
    return this.httpUtilService.delete(noteUrl,id);
  }




}
