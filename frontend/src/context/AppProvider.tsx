import React from "react";
import { AuthProvider } from "./AuthContext";
import { ClinicProvider } from "./ClinicContext";
import { LoadingProvider } from "./LoadingContext";
import { ThemeProvider } from "@/components/theme-provider";
import { WebSocketProvider } from "./WebSocketContext";

export const AppProvider: React.FC<React.PropsWithChildren> = ({
  children,
}) => {
  return (
    <AuthProvider>
      <WebSocketProvider>
        <ThemeProvider>
          <ClinicProvider>
            <LoadingProvider>{children}</LoadingProvider>
          </ClinicProvider>
        </ThemeProvider>
      </WebSocketProvider>
    </AuthProvider>
  );
};
