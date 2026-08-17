import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import ServerUrl from './../DefaultUrl';
import { Observable } from 'rxjs';
import {
  shortBlogResponseType,
  universalResponseDataType,
  singleBlogResponseType,
  getFollowingTopThreeBlogResponseData,
} from '../../../types/BlogTypes';
import { SolidButtonComponent } from '../../../shared/components/solid-button/solid-button.component';
@Injectable({
  providedIn: 'root',
})
export class BlogService {
  constructor(private http: HttpClient) {}

  postBlog(blogData: FormData): Observable<universalResponseDataType<null>> {
    return this.http.post<universalResponseDataType<null>>(
      `${ServerUrl}blog`,
      blogData
    );
  }
  getLatestBlogs(): Observable<
    universalResponseDataType<shortBlogResponseType>
  > {
    return this.http.get<universalResponseDataType<shortBlogResponseType>>(
      `${ServerUrl}blogs/latest`
    );
  }
  getFollowingBlogs(): Observable<
    universalResponseDataType<getFollowingTopThreeBlogResponseData>
  > {
    return this.http.get<
      universalResponseDataType<getFollowingTopThreeBlogResponseData>
    >(`${ServerUrl}blogs/following`);
  }
  getTrendingBlogs(): Observable<
    universalResponseDataType<shortBlogResponseType>
  > {
    return this.http.get<universalResponseDataType<shortBlogResponseType>>(
      `${ServerUrl}blogs/trending`
    );
  }
  getSingleBlog(
    blogId: string
  ): Observable<universalResponseDataType<singleBlogResponseType>> {
    // const params = new HttpParams().set('blogId', blogId);
    return this.http.get<universalResponseDataType<singleBlogResponseType>>(
      `${ServerUrl}blog/${blogId}`,
      {
        // params: params,
      }
    );
  }
  get3Blog(): Observable<universalResponseDataType<shortBlogResponseType>> {
    const publicId: string = localStorage.getItem('publicId') ?? '';
    const params = new HttpParams().set('publicId', publicId);
    return this.http.get<universalResponseDataType<shortBlogResponseType>>(
      `${ServerUrl}blogs`,
      {
        params: params,
      }
    );
  }
  deleteBlog(id: string | number): Observable<universalResponseDataType<null>> {
    return this.http.delete<universalResponseDataType<null>>(
      `${ServerUrl}blog/${id}`
    );
  }
  likeBlog(blogId: string): Observable<universalResponseDataType<null>> {
    const publicId = localStorage.getItem('publicId');
    const postData = {
      publicId: publicId,
      blogId: blogId,
    };
    return this.http.post<universalResponseDataType<null>>(
      `${ServerUrl}blog/like`,
      postData
    );
  }
}
