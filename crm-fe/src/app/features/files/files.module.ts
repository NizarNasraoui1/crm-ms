import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FilesRoutingModule } from './files-routing.module';
import { FileListComponent } from './components/file-list/file-list.component';
import { TableModule } from 'primeng/table';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { OpenFileModalComponent } from './components/open-file-modal/open-file-modal.component'; // <- import PdfViewerModule
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';

@NgModule({
  declarations: [
    FileListComponent,
     OpenFileModalComponent
  ],
  imports: [
    CommonModule,
    FilesRoutingModule,
    TableModule,
    PdfViewerModule,
    DialogModule,
    ButtonModule
  ],
  exports: [FileListComponent]
})
export class FilesModule { }
