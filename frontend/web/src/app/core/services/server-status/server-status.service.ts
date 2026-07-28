import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { serverStatusResponseType } from './type';
import ServerUrl from '../DefaultUrl';
@Injectable({
  providedIn: 'root',
})
export class ServerStatusService {
  constructor(private http: HttpClient) {}
  getServerStatus(): Observable<serverStatusResponseType> {
    return this.http.get<serverStatusResponseType>(ServerUrl);
  }
}
