import { Button } from "@/components/ui/button";

export default function LoginPage() {
  const handleLogin = () => {
    const baseUrl = import.meta.env.VITE_CLINIC_BASE_URL || "https://frontend.sba301.io.vn";
    const redirectUri = encodeURIComponent(window.location.origin + "/sso/callback");
    window.location.href = `${baseUrl}/login?redirect=${redirectUri}`;
  };

  return (
    <div className="min-h-screen min-w-screen flex flex-col items-center justify-center gap-4 bg-linear-to-br from-blue-50 to-blue-100">
      <h1 className="text-2xl font-bold">Clinic Chat</h1>
      <p className="text-slate-600">Sign in via Clinic SSO</p>
      <Button onClick={handleLogin} className="bg-blue-600 hover:bg-blue-700">
        Login via Clinic
      </Button>
    </div>
  );
}
