export type shortBlogDataType = {
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
export type profileSettingDetailType = {
  userName: string;
  userLocation: string;
  userBio: string;
  userImageUrl: string;
  userTopic: string[];
};
