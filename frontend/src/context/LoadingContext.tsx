import React, { createContext, useContext, useState } from "react";
import { Spinner } from "@/components/ui/spinner";
interface LoadingContextType {
  isLoading: boolean;
  setLoading: (state: boolean) => void;
}

const LoadingContext = createContext<LoadingContextType | undefined>(undefined);

export const LoadingProvider: React.FC<React.PropsWithChildren> = ({
  children,
}) => {
  const [isLoading, setLoading] = useState(false);

  return (
    <LoadingContext.Provider value={{ isLoading, setLoading }}>
      {isLoading && (
        <div className="flex items-center gap-4">
          <Spinner className="mx-auto" />
          <span className="text-gray-500">Đang xác thực, vui lòng đợi...</span>
        </div>
      )}
      {children}
    </LoadingContext.Provider>
  );
};

export const useLoading = (): LoadingContextType => {
  const ctx = useContext(LoadingContext);
  if (!ctx) throw new Error("useLoading must be used within LoadingProvider");
  return ctx;
};
