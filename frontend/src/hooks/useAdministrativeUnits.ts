import { commonApi } from "@/api/commonApi";
import { useEffect, useState } from "react";

export function useAdministrativeUnits() {
  const [provinces, setProvinces] = useState<{ code: string; name: string }[]>([]);
  const [wards, setWards] = useState<{ code: string; name: string }[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        const res = await commonApi.getProvinces();
        setProvinces(res);
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const loadWards = async (provinceCode: string) => {
    const res = await commonApi.getWards(provinceCode);
    setWards(res);
  };

  return { provinces, wards, loadWards, loading };
}
