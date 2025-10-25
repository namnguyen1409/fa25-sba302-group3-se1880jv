export interface Department {
  id: string;
  name: string;
  description?: string;
}

export interface Specialty {
  id: string;
  name: string;
  departmentId: string;
}

export interface ClinicInfo {
  name: string;
  address: string;
  hotline: string;
  openingHours: string;
}
