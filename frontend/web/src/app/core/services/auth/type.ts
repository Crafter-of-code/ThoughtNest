export type AuthResponseType = {
  status: boolean;
  message: string;
};
export type signinDataType = {
  userFirstName: string | null;
  userMiddleName: string | null;
  userLastName: string | null;
  userEmail: string;
  userPassword: string | null;
};
export type loginDataType = {
  userEmail: string | null;
  userPassword: string | null;
};
