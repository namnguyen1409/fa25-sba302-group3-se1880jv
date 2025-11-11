import React, { createContext, useContext, useEffect, useState } from "react";
import type { ClinicResponse } from "@/api";
import { clinicApi } from "@/api/clinicApi";


interface ClinicContextType {
  clinicInfo: ClinicResponse | null
  setClinicInfo: React.Dispatch<React.SetStateAction<ClinicResponse | null>>;
}

const ClinicContext = createContext<ClinicContextType | undefined>(undefined);

export const ClinicProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  const [clinicInfo, setClinicInfo] = useState<ClinicResponse | null>(null);

  useEffect(() => {
    (async () => {
      try {
        const res = await clinicApi.getClinicInfo();
        setClinicInfo(res)
      } catch (err) {
        console.error("Failed to load clinic data:", err);
      }
    })();
  }, []);

  return (
    <ClinicContext.Provider value={{ clinicInfo, setClinicInfo}}>
      {children}
    </ClinicContext.Provider>
  );
};

export const useClinic = (): ClinicContextType => {
  const ctx = useContext(ClinicContext);
  if (!ctx) throw new Error("useClinic must be used within ClinicProvider");
  return ctx;
};
