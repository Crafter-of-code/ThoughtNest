import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  blogUploadResponseFromServer,
  shortBlogResponseDataType,
  postBlogDataType,
  singleBlogResponseType,
  ResponseDataType,
} from './type';
import ServerUrl from './../DefaultUrl';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root',
})
export class BlogService {
  constructor(private http: HttpClient) {}
  postBlog(blogData: FormData): Observable<blogUploadResponseFromServer> {
    return this.http.post<blogUploadResponseFromServer>(
      `${ServerUrl}blog`,
      blogData
    );
  }
  getLatestBlog(): Observable<shortBlogResponseDataType> {
    return this.http.get<shortBlogResponseDataType>(`${ServerUrl}blog/latest`);
  }
  getSingleBlog(blogId: string): Observable<singleBlogResponseType> {
    const params = new HttpParams().set('blogId', blogId);
    return this.http.get<singleBlogResponseType>(`${ServerUrl}blog`, {
      params: params,
    });
  }
  getOwner3Blog(): Observable<shortBlogResponseDataType> {
    return this.http.get<shortBlogResponseDataType>(`${ServerUrl}blog/me`);
  }
  deleteBlog(id: string | number): Observable<ResponseDataType> {
    return this.http.delete<ResponseDataType>(`${ServerUrl}blog/${id}`);
  }
}
