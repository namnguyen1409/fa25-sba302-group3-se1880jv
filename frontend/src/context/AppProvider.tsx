import React from "react";
import { AuthProvider } from "./AuthContext";
import { ClinicProvider } from "./ClinicContext";
import { LoadingProvider } from "./LoadingContext";
import { ThemeProvider } from "@/components/theme-provider";

export const AppProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  return (
    <AuthProvider>
      <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
        <ClinicProvider>
          <LoadingProvider>{children}</LoadingProvider>
        </ClinicProvider>
      </ThemeProvider>
    </AuthProvider>
  );
};
