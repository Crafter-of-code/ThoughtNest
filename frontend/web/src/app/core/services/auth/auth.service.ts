import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import ServerUrl from '../DefaultUrl';
type AuthResponseType = {
  status: boolean;
  message: string;
  token: string;
  publicId: string;
};
type signinDataType = {
  userFirstName: string | null;
  userLastName: string | null;
  userEmail: string;
  userPassword: string | null;
};
type loginDataType = {
  userEmail: string;
  userPassword: string;
};
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}
  signIn(signinData: signinDataType): Observable<AuthResponseType> {
    return this.http.post<AuthResponseType>(
      `${ServerUrl}auth/signin`,
      signinData
    );
  }
  login(loginData: loginDataType): Observable<AuthResponseType> {
    return this.http.post<AuthResponseType>(
      `${ServerUrl}auth/login`,
      loginData
    );
  }
  logout(): Observable<AuthResponseType> {
    return this.http.get<AuthResponseType>(`${ServerUrl}auth/logout`);
  }
}
