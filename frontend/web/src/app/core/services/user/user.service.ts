import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import ServerUrl from '../DefaultUrl';
import { genreDataType } from './type';
type userNameType = {
  status: boolean;
  message: string;
  data: {
    userProfileImage: string;
    publicId: string;
    userName: string;
  }[];
};
type getCompleteUserDetailType = {
  message: string;
  status: boolean;
  data: {
    publicId: string;
    userName: string;
    noOfFollowing: number;
    noOfFollower: number;
    noOfBlog: number;
    createAt: Date;
    userProfile: {
      userBio: string;
      userLocation: string;
      userImageUrl: string;
      userTotalLike: number;
      userProfileView: number;
      userPublished: number;
    };
  };
};
type getOwnerDetailType = {
  message: string;
  status: boolean;
  data: {
    userId: number;
    publicId: string;
    userEmail: string;
    userName: string;
    noOfFollower: number;
    noOfFollowing: number;
    createdAt: Date;
    userProfile: {
      userLocation: string;
      userBio: string;
      userPublished: number;
      userProfileView: number;
      userTotalLikes: number;
      userImageUrl: string;
    };
  };
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
  getCompleteUserDetail(id: string): Observable<getCompleteUserDetailType> {
    return this.http.get<getCompleteUserDetailType>(`${ServerUrl}user/${id}`);
  }
  getOwnerCompleteDetail(): Observable<getOwnerDetailType> {
    return this.http.get<getOwnerDetailType>(`${ServerUrl}user/me`);
  }
}
