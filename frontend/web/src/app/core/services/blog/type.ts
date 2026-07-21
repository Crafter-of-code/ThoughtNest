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
type latestBlogDataType = {
  blogTitle: string;
  blogContent: string;
  userName: string;
  userId: number;
  userEmail: string;
  createdAt: Date;
  blogId: string;
};
export type latestBlogResponseDataType = {
  status: boolean;
  message: string;
  data: latestBlogDataType[];
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
