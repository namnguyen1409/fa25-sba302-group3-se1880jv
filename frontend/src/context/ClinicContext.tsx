import React, { createContext, useContext, useEffect, useState } from "react";
import type { ClinicInfo, Department, Specialty } from "@/types/clinic";
import { clinicApi } from "@/api/clinicApi";


interface ClinicContextType {
  clinicInfo: ClinicInfo | null;
  departments: Department[];
  specialties: Specialty[];
}

const ClinicContext = createContext<ClinicContextType | undefined>(undefined);

export const ClinicProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  const [clinicInfo, setClinicInfo] = useState<ClinicInfo | null>(null);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [specialties, setSpecialties] = useState<Specialty[]>([]);

  useEffect(() => {
    (async () => {
      try {
        const [info, dept, spec] = await Promise.all([
          clinicApi.getClinicInfo(),
          clinicApi.getDepartments(),
          clinicApi.getSpecialties(),
        ]);
        setClinicInfo(info);
        setDepartments(dept);
        setSpecialties(spec);
      } catch (err) {
        console.error("Failed to load clinic data:", err);
      }
    })();
  }, []);

  return (
    <ClinicContext.Provider value={{ clinicInfo, departments, specialties }}>
      {children}
    </ClinicContext.Provider>
  );
};

export const useClinic = (): ClinicContextType => {
  const ctx = useContext(ClinicContext);
  if (!ctx) throw new Error("useClinic must be used within ClinicProvider");
  return ctx;
};
