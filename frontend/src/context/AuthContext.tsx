import React, { createContext, useContext, useEffect, useState } from "react";
import type {  LoginRequest } from "@/types/auth";
import { authApi } from "@/api/authApi";
import { toast } from "sonner";
import type { MeResponse } from "@/api";

interface AuthContextType {
  user: MeResponse | null;
  setUser: React.Dispatch<React.SetStateAction<MeResponse | null>>;
  accessToken: string | null;
  isAuthenticated: boolean;
  loading: boolean;
  login: (data: LoginRequest) => Promise<void>;
  loginSuccess: () => Promise<void>;
  logout: () => void;
  setAccessToken: (token: string | null) => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<React.PropsWithChildren> = ({
  children,
}) => {
  const [user, setUser] = useState<MeResponse | null>(null);
  const [accessToken, setAccessToken] = useState<string | null>(
    localStorage.getItem("accessToken")
  );
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const init = async () => {
      if (accessToken) {
        try {
          const res = await authApi.me();
          setUser(res);
        } catch {
          setUser(null);
        }
      }
      setLoading(false);
    };
    init();
  }, [accessToken]);

  const login = async (data: LoginRequest) => {
    try {
      const res = await authApi.login(data);
      const { accessToken } = res;
      localStorage.setItem("accessToken", accessToken);
      setAccessToken(accessToken);
      const me = await authApi.me();
      setUser(me);
      toast.success("Login successful!");
    } catch (err) {
      toast.error("Invalid credentials");
      throw err;
    }
  };

  const loginSuccess = async () => {
    try {
      const accessToken = localStorage.getItem("accessToken");
      if (accessToken) {
        setAccessToken(accessToken);
        const me = await authApi.me();
        setUser(me);
        if (me.roles && Array.from(me.roles).some(role => role.name !== "ROLE_PATIENT")) {
          window.location.href = "/staff";
        } else {
          window.location.href = "/";
        }
      }
    } catch (err) {
      console.error("Error during login success:", err);
    }
  };

  const logout = () => {
    try {
      authApi.logout();
      localStorage.removeItem("accessToken");
      setAccessToken(null);
      setUser(null);
      window.location.href = "/";
    } catch (err) {
      console.error("Logout error:", err);
      toast.error("Error during logout");
      return;
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        setUser,
        accessToken,
        isAuthenticated: !!user,
        loading,
        login,
        loginSuccess,
        logout,
        setAccessToken,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = (): AuthContextType => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("useAuth must be used within AuthProvider");
  return ctx;
};
