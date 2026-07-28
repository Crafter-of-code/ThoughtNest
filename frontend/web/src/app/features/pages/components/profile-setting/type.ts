export type profileSettingDetailType = {
  userProfileUrl: string;
  userName: string;
  userLocation: string;
  userBio: string;
  userTopic: string[];
};
export type updatedUserDetailType = {
  userName: string;
  userBio: string;
  userLocation: string;
  userProfileData: File | null;
  userTopic: string[];
};
