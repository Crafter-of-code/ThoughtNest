export type OwnerDetailType = {
  userId: number | null;
  publicId: string | null;
  userEmail: string | null;
  userName: string | null;
  noOfFollower: number | null;
  noOfFollowing: number | null;
  createdAt: Date;
  userProfile: {
    userLocation: string | null;
    userBio: string | null;
    userPublished: number | null;
    userProfileView: number | null;
    userTotalLikes: number | null;
    userImageUrl: string | null;
  } | null;
};
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
  userProfileUrl: string;
  userName: string;
  userLocation: string;
  userBio: string;
  userTopic: string[];
};
