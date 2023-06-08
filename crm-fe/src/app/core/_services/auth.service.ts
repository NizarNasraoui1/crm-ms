import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginForm } from 'src/app/shared/models/loginForm';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:8080';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,private tokenStorageService:TokenStorageService) { }

  login(loginForm:LoginForm): Observable<any> {

    let body = new URLSearchParams();
    body.set('username', loginForm.username);
    body.set('password', loginForm.password);

    return this.http
        .post('auth/token', loginForm,{responseType: 'text'});
  }

  logOut(){
    this.tokenStorageService.signOut();
  }

  register(user:any): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }

  isAuthenticated():boolean{
    const token=this.tokenStorageService.getToken();
    if(!token) return false;
    return true;
  }
}
