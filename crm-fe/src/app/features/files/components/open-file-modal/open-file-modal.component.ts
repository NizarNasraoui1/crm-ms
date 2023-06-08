import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-open-file-modal',
  templateUrl: './open-file-modal.component.html',
  styleUrls: ['./open-file-modal.component.scss']
})
export class OpenFileModalComponent implements OnInit {
    isPdf=true;
    path='';
    displaySaveModal=true;

    constructor() { }

    ngOnInit(): void {
    }

}
