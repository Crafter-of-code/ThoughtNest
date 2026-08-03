export type universalResponseType<T> = {
  status: boolean;
  message: string;
  data: T | null;
};
