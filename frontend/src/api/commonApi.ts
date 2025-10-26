import { apiClient } from "./client";


export interface AdministrativeUnit {
  code: string;
  name: string;
}

export const commonApi = {
  getProvinces: () =>
    apiClient.get<AdministrativeUnit[]>(
      "/common/administrative-units?level=PROVINCE"
    ),
  getWards: (provinceCode: string) =>
    apiClient.get<AdministrativeUnit[]>(
      `/common/administrative-units?level=WARDS&parentCode=${provinceCode}`
    ),
};
