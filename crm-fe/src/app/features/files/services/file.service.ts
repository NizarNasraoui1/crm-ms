import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpUtilService } from 'src/app/util/service/http-util.service';



const FileApiUrl="/api/file";

@Injectable({
    providedIn: 'root'
  })
export class FileService {

  constructor(private httpUtil:HttpUtilService,private http:HttpClient) { }

  getFileList(crmBaseEntityId:number):Observable<any>{
    return this.httpUtil.get(`${FileApiUrl}/all/crm-base-entity/${crmBaseEntityId}`);
  }

  uploadFile(file:any,crmBaseEntityId:number):Observable<any>{
    const url = `/api/file/upload/crm-base-entity/${crmBaseEntityId}`;
    const formData = new FormData();
    formData.append('file', file);
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');
    return this.http.post(url, formData, { headers });
  }
}
