import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponseType, loginDataType, signinDataType } from './type';
import ServerUrl from '../DefaultUrl';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}
  signIn(signinData: signinDataType): Observable<AuthResponseType> {
    return this.http.post<AuthResponseType>(`${ServerUrl}signin`, signinData);
  }
  login(loginData: loginDataType): Observable<AuthResponseType> {
    return this.http.post<AuthResponseType>(`${ServerUrl}login`, loginData);
  }
}
