export type profileSettingDetailType = {
  userName: string;
  userLocation: string;
  userBio: string;
  userTopic: string[];
  userImageUrl: string;
};
export type updatedUserDetailType = {
  userName: string;
  userBio: string;
  userLocation: string;
  userProfileData: File | null;
  userTopic: string[];
};
