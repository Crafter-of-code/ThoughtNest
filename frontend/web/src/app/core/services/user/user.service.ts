import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import ServerUrl from '../DefaultUrl';
import { genreDataType } from './type';
import { universalResponseType } from '../../../types/UniversalTypes';
import {
  getOwnerDetailResponseType,
  getUserDetailResponseType,
} from '../../../types/UserTypes';
type normalResponseType = {
  status: boolean;
  message: string;
};
type userNameType = {
  status: boolean;
  message: string;
  data: {
    userProfileImage: string;
    publicId: string;
    userName: string;
  }[];
};
export type updatedUserDetailType = {
  userName: string;
  userBio: string;
  userLocation: string;
  userProfileData: File | null;
  userTopic: string[];
};
@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getUserName(userName: string): Observable<userNameType> {
    const userNameParams = new HttpParams().set('userName', userName);
    return this.http.get<userNameType>(`${ServerUrl}user`, {
      params: userNameParams,
    });
  }
  getCompleteUserDetail(
    id: string
  ): Observable<universalResponseType<getUserDetailResponseType>> {
    return this.http.get<universalResponseType<getUserDetailResponseType>>(
      `${ServerUrl}user/${id}`
    );
  }
  getOwnerCompleteDetail(): Observable<
    universalResponseType<getOwnerDetailResponseType>
  > {
    return this.http.get<universalResponseType<getOwnerDetailResponseType>>(
      `${ServerUrl}user/me`
    );
  }
  patchUpdatedDetail(
    data: updatedUserDetailType
  ): Observable<normalResponseType> {
    return this.http.patch<normalResponseType>(`${ServerUrl}user`, data);
  }
  deleteAccount() {
    return this.http.delete(`${ServerUrl}user`);
  }
  followUser(id: string): Observable<universalResponseType<null>> {
    return this.http.post<universalResponseType<null>>(
      `${ServerUrl}user/follow/${id}`,
      null
    );
  }
  unfollowUser(id: string): Observable<universalResponseType<null>> {
    return this.http.delete<universalResponseType<null>>(
      `${ServerUrl}user/unfollow/${id}`
    );
  }
}
