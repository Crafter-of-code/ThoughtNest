export type getOwnerDetailResponseType = {
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
    userTopics: string[];
    userJoinedOn: Date;
  };
  userFollow: {
    userFollowing: shortUserDetailResponseType[];
    userFollower: shortUserDetailResponseType[];
  };
};
export type shortUserDetailResponseType = {
  userName: string;
  publicId: string;
  userProfileImage: string;
};
export type getUserDetailResponseType = {
  publicId: string;
  userName: string;
  noOfFollowing: number;
  noOfFollower: number;
  noOfBlog: number;
  createAt: string;
  following: boolean;
  userProfile: {
    userBio: string;
    userLocation: string;
    userImageUrl: string;
    userTotalLikes: number;
    userProfileView: number;
    userPublished: number;
    userTopics: string[];
    userJoinedOn: string;
  };
};
