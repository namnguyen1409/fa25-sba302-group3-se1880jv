import { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { toast } from "sonner";
import { chatApi } from "@/api/chatApi";
import { useAuth } from "@/context/AuthContext";

export default function SsoCallbackPage() {
  const nav = useNavigate();
  const [searchParams, _] = useSearchParams();
  const { setUser } = useAuth();

  useEffect(() => {
    const run = async () => {
      const token = searchParams.get("token");
      console.log("SSO Callback token:", token);
      if (!token) {
        toast.error("Missing token");
        nav("/login", { replace: true });
        return;
      }
      localStorage.setItem("accessToken", token);

      // Immediately fetch user profile and populate AuthContext to avoid race
      // where AuthProvider already ran its init before token was set.
      try {
        // decode token payload for quick debug (will not verify signature)
        try {
          const parts = token.split(".");
          if (parts.length === 3) {
            const payload = JSON.parse(atob(parts[1].replace(/-/g, "+").replace(/_/g, "/")));
            console.log("SSO token claims:", payload);
          }
        } catch (e) {
          console.warn("Failed to decode token payload", e);
        }

        const res = await chatApi.get("/api/sso/login", {
          headers: { Authorization: `Bearer ${token}` },
        });
        setUser(res.data);
        toast.success("Logged in!");
        nav("/", { replace: true });
      } catch (err: any) {
        // improve error logging for debugging 401
        console.error("SSO login fetch failed", err);
        if (err?.response) {
          console.error("SSO response status:", err.response.status);
          console.error("SSO response data:", err.response.data);
        }
        toast.error("Authentication failed");
        // clear token to be safe
        localStorage.removeItem("accessToken");
        nav("/login", { replace: true });
      }
    };
    run();
  }, [searchParams]);

  return (
    <div className="min-h-screen flex items-center justify-center text-slate-500">
      Authenticating…
    </div>
  );
}
