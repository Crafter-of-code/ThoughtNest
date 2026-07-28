import { Data } from '@angular/router';

export type postBlogDataType = {
  blogTitle: string | null;
  blogContent: string | null;
  coverImage: File | null;
};
export type blogUploadResponseFromServer = {
  status: boolean;
  message: string;
};
type shortBlogDataType = {
  blogTitle: string;
  blogContent: string;
  userName: string;
  publicId: string;
  userEmail: string;
  createdAt: Date;
  blogId: string;
};
export type shortBlogResponseDataType = {
  status: boolean;
  message: string;
  data: shortBlogDataType[];
};
export type singleBlogResponseType = {
  status: boolean;
  message: string;
  data: {
    blogId: string;
    blogTitle: string;
    blogSummary: string;
    blogContent: string;
    coverImage: string;

    // Author
    userId: string;
    userName: string;

    // Statistics
    blogViews: number;
    blogLikes: number;
    blogComments: number;

    // Publish Date
    createdAt: Date;
  };
};
export type ResponseDataType = {
  status: boolean;
  message: string;
};
