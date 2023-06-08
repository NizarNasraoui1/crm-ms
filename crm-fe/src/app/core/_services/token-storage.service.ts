import { Injectable } from '@angular/core';

const ACCESS_TOKEN_KEY = 'auth-access-token';
const USER_KEY = 'auth-user';
const REFRESH_TOKEN_KEY= 'auth-refresh-token';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveAccessToken(token: string): void {
    window.sessionStorage.removeItem(ACCESS_TOKEN_KEY);
    window.sessionStorage.setItem(ACCESS_TOKEN_KEY, token);
  }

  public saveRefreshToken(accessToken:string):void{
    window.sessionStorage.removeItem(REFRESH_TOKEN_KEY);
    window.sessionStorage.setItem(REFRESH_TOKEN_KEY,accessToken);
  }

  public getToken(): string {
    let token=sessionStorage.getItem(ACCESS_TOKEN_KEY);
    return token?token:"";
  }

  public saveUser(user:any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    let user=sessionStorage.getItem(USER_KEY);
    return user?JSON.parse(user):null;

  }
}
