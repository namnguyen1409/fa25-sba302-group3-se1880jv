import React, { createContext, useContext, useEffect, useState } from "react";
import { chatApi } from "@/api/chatApi";
import { toast } from "sonner";
import { ThemeProvider } from "./theme-provider";

export interface ProfileResponse {
  id: string;
  userId: string;
  displayName: string;
  avatar?: string;
  bio?: string;
  status?: string;
  isDefault: boolean;
  createdAt: string;
}

interface AuthContextType {
  user: ProfileResponse | null;
  setUser: React.Dispatch<React.SetStateAction<ProfileResponse | null>>;
  isAuthenticated: boolean;
  loading: boolean;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<React.PropsWithChildren> = ({ children }) => {
  const [user, setUser] = useState<ProfileResponse | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const init = async () => {
      const token = localStorage.getItem("accessToken");
      if (!token) {
        setLoading(false);
        return;
      }

      try {
        const res = await chatApi.get<ProfileResponse>("/api/sso/login", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setUser(res.data);
      } catch (err) {
        console.error("Failed to fetch user profile", err);
        // localStorage.removeItem("accessToken");
        toast.error("Session expired, please log in again");
      } finally {
        setLoading(false);
      }
    };
    init();
  }, []);

  const logout = () => {
    localStorage.removeItem("accessToken");
    setUser(null);
    toast.info("Logged out");
    window.location.href = "/login";
  };

  return (
    <ThemeProvider>
      <AuthContext.Provider
        value={{
          user,
          setUser,
          isAuthenticated: !!user,
          loading,
          logout,
        }}
      >
        {children}
      </AuthContext.Provider>
    </ThemeProvider>
  );
};

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth must be used within AuthProvider");
  return ctx;
};
