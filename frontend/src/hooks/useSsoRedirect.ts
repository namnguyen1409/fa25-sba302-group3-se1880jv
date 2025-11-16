import { useEffect } from "react";
import { useAuth } from "@/context/AuthContext";

export function useSsoRedirect() {
  const { accessToken, user, loading } = useAuth();

  useEffect(() => {
    if (loading) return;

    const params = new URLSearchParams(window.location.search);
    const redirectUrl = params.get("redirect");

    if (redirectUrl && accessToken && user) {
      window.location.href = `${redirectUrl}?token=${accessToken}`;
    }
  }, [loading, accessToken, user]);
}
