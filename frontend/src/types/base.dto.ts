export type CustomApiResponse<T = unknown> = {
  code: number;
  message?: string;
  data?: T;
  timestamp: string;
  path?: string;
};
