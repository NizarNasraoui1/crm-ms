import { Component, EventEmitter, OnInit, Output,Input } from '@angular/core';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { Note } from '../../models/Note';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.scss']
})
export class EditorComponent implements OnInit {
  @Input() note:Note;
  @Output() noteContentChangeEvent=new EventEmitter<string>;
  
  htmlContent = '';
  config: AngularEditorConfig = {
    editable: true,
    spellcheck: true,
    height: '15rem',
    minHeight: '5rem',
    placeholder: 'Enter text here...',
    translate: 'no',
    defaultParagraphSeparator: 'p',
    defaultFontName: 'Arial',
    toolbarHiddenButtons: [
      ['bold']
    ],
    customClasses: [
      {
        name: "quote",
        class: "quote",
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: "titleText",
        class: "titleText",
        tag: "h1",
      },
    ]
  };
  constructor() { }

  ngOnInit(): void {
    this.htmlContent=this.note?.content;
  }

  typeNoteEvent(){
    this.noteContentChangeEvent.emit(this.htmlContent);
  }

}
