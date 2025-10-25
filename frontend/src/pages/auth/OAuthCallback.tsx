import { useEffect } from "react";
import { useSearchParams, useNavigate } from "react-router-dom";
import { authApi } from "@/api/authApi";
import { toast } from "sonner";
import { ensureDeviceId } from "@/utils/device";

export default function OAuthCallback() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    const code = searchParams.get("code");
    const pathParts = window.location.pathname.split("/");
    const provider = pathParts[pathParts.length - 1].toUpperCase(); // github/google/facebook

    if (!code || !provider) {
      toast.error("Invalid OAuth callback");
      navigate("/login");
      return;
    }

    (async () => {
      try {
        const res = await authApi.oauthLogin({
          provider: provider as "GITHUB" | "GOOGLE" | "FACEBOOK",
          accessToken: code,
          deviceId: ensureDeviceId(),
          rememberMe: true,
        });
        console.log("OAuth login response:", res);
        localStorage.setItem("accessToken", res.accessToken);
        toast.success("Login successful!");
        navigate("/dashboard");
      } catch (err) {
        console.error("OAuth login failed:", err);
        toast.error("OAuth login failed!");
        navigate("/login");
      }
    })();
  }, []);

  return (
    <div className="flex items-center justify-center h-screen">
      <p className="text-lg text-muted-foreground">Authenticating...</p>
    </div>
  );
}
