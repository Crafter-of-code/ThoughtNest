/* Response types */
export type uploadBlogResponseType = {
  status: boolean;
  message: string;
};
export type universalResponseDataType<T> = {
  status: boolean;
  message: string;
  data: T | null;
};
export type shortBlogResponseType = {
  blogTitle: string;
  blogContent: string;
  userName: string;
  publicId: string;
  userEmail: string;
  userImageUrl: string;
  createdAt: Date;
  blogId: string;
}[];
export type singleBlogResponseType = {
  blogId: string;
  blogTitle: string;
  blogSummary: string;
  blogContent: string;
  coverImage: string;

  // Author
  userId: string;
  userName: string;
  userImageUrl: string;
  // Statistics
  blogViews: number;
  blogLikes: number;
  blogComments: number;
  blogLiked: boolean;
  // Publish Date
  createdAt: Date;
} | null;

export type getFollowingTopThreeBlogResponseData = {
  userName: string;
  userPublicId: string;
  userPublicImageUrl: string | null;
  shortBlogList: shortBlogResponseType;
}[];
