import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  blogUploadResponseFromServer,
  latestBlogResponseDataType,
  postBlogDataType,
  singleBlogResponseType,
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
  getLatestBlog(): Observable<latestBlogResponseDataType> {
    return this.http.get<latestBlogResponseDataType>(`${ServerUrl}blog/latest`);
  }
  getSingleBlog(blogId: string): Observable<singleBlogResponseType> {
    const params = new HttpParams().set('blogId', blogId);
    return this.http.get<singleBlogResponseType>(`${ServerUrl}blog`, {
      params: params,
    });
  }
}
