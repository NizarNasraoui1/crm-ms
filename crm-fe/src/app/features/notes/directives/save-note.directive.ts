import { Directive, Input } from '@angular/core';

@Directive({
  selector: '[appSaveNote]'
})
export class SaveNoteDirective {
  @Input() test:string;
  constructor() {
    console.log(this.test);
   }

}
