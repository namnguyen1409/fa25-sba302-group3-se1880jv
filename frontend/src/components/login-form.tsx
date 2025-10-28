import { useState } from "react";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "sonner";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  Field,
  FieldGroup,
  FieldLabel,
  FieldSeparator,
  FieldDescription,
} from "@/components/ui/field";
import { authApi } from "@/api/authApi";
import { ensureDeviceId } from "@/utils/device";
import { useAuth } from "@/context/AuthContext";
import { useGoogleLogin } from "@react-oauth/google";
import FacebookLogin from "@greatsumini/react-facebook-login";
import { Github, Lock, FacebookIcon, KeyRound, Mail } from "lucide-react";
import { startAuthentication } from "@simplewebauthn/browser";

interface LoginFormValues {
  username: string;
  password: string;
}

const schema = yup.object({
  username: yup.string().required("Username is required"),
  password: yup.string().required("Password is required"),
});

export function LoginForm({ className }: { className?: string }) {
  const navigate = useNavigate();
  const { loginSuccess } = useAuth();
  const [loadingType, setLoadingType] = useState<
    "login" | "oauth" | "passkey" | "mfa" | null
  >(null);

  const [mfaChallenge, setMfaChallenge] = useState<any>(null);
  const [mfaCode, setMfaCode] = useState("");
  const [selectedMfaType, setSelectedMfaType] = useState<string | undefined>(undefined);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginFormValues>({
    resolver: yupResolver(schema),
  });

  /** ----------- LOGIN STEP ----------- **/
  const handleLogin = async (values: LoginFormValues) => {
    try {
      setLoadingType("login");
      const res = await authApi.login({
        ...values,
        deviceId: ensureDeviceId(),
        rememberMe: true,
      });

      if (res.requires2FA) {
        setMfaChallenge(res);
        setSelectedMfaType(res.defaultMfaType);
        toast.info(`Xác minh ${res.defaultMfaType} MFA`);
        return;
      }

      localStorage.setItem("accessToken", res.accessToken || "");
      await loginSuccess();
      toast.success("Login successful!");
      navigate("/");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Login failed");
    } finally {
      setLoadingType(null);
    }
  };

  /** ----------- MFA VERIFY STEP ----------- **/
  const handleVerifyMfa = async () => {
    if (!mfaCode.trim()) {
      toast.error("Vui lòng nhập mã xác thực");
      return;
    }

    try {
      setLoadingType("mfa");
      const res = await authApi.verifyMfa(
        mfaChallenge.challengeId,
        selectedMfaType || "",
        mfaCode,
        false
      );

      localStorage.setItem("accessToken", res.accessToken || "");
      await loginSuccess();
      toast.success("Xác thực thành công!");
      navigate("/");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Mã không đúng");
    } finally {
      setLoadingType(null);
    }
  };

  const handleSwitchMfa = (type: string) => {
    setSelectedMfaType(type);
    toast.info(`Chuyển sang xác minh bằng ${type}`);
    // Backend có thể tự động gửi mã OTP mới tương ứng loại này
  };

  /** ----------- OAUTH & PASSKEY ----------- **/
  const handleOAuthLogin = async (
    provider: "GOOGLE" | "FACEBOOK" | "GITHUB",
    accessToken: string
  ) => {
    try {
      setLoadingType("oauth");
      const res = await authApi.oauthLogin({
        provider,
        accessToken,
        deviceId: ensureDeviceId(),
        rememberMe: true,
      });

      if (res.requires2FA) {
        setMfaChallenge(res);
        setSelectedMfaType(res.defaultMfaType);
        return;
      }

      localStorage.setItem("accessToken", res.accessToken || "");
      await loginSuccess();
      toast.success(`Logged in with ${provider}`);
      navigate("/");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "OAuth login failed");
    } finally {
      setLoadingType(null);
    }
  };

  const googleLogin = useGoogleLogin({
    onSuccess: (token) => handleOAuthLogin("GOOGLE", token.access_token),
    onError: () => toast.error("Google login failed"),
  });

  const handleGitHubLogin = () => {
    const clientId = import.meta.env.VITE_GITHUB_CLIENT_ID;
    const redirectUri = import.meta.env.VITE_GITHUB_REDIRECT_URI;
    window.location.href = `https://github.com/login/oauth/authorize?client_id=${clientId}&scope=user:email&redirect_uri=${encodeURIComponent(
      redirectUri
    )}`;
  };

  const handlePasskeyLogin = async () => {
    try {
      setLoadingType("passkey");
      const deviceId = ensureDeviceId();

      const startRes = await authApi.loginPasskeyStart();

      const options = startRes.requestOptions;
      options.extensions = {};

      const assertion = await startAuthentication({ optionsJSON: options });
      const finishRes = await authApi.loginPasskeyFinish({
        responseJson: JSON.stringify(assertion),
        deviceId,
        rememberMe: true,
        requestId: startRes.requestId,
      });

      localStorage.setItem("accessToken", finishRes.accessToken || "");
      await loginSuccess();
      toast.success("Login successful!");
      navigate("/");
    } catch (err: any) {
      toast.error("Passkey not supported or failed");
    } finally {
      setLoadingType(null);
    }
  };

  /** ----------- RENDER ----------- **/
  if (mfaChallenge) {
    return (
      <div className="w-full max-w-md mx-auto space-y-4">
        <h2 className="text-xl font-bold text-center">Xác minh bảo mật</h2>
        <p className="text-sm text-muted-foreground text-center">
          Vui lòng nhập mã MFA được gửi qua{" "}
          <span className="font-medium">{selectedMfaType}</span>.
        </p>

        <div className="flex justify-center gap-2">
          {mfaChallenge.mfaTypes?.map((t: string) => (
            <Button
              key={t}
              size="sm"
              variant={selectedMfaType === t ? "default" : "outline"}
              onClick={() => handleSwitchMfa(t)}
            >
              {t === "EMAIL" && <Mail className="w-4 h-4 mr-1" />}
              {t === "TOTP" && <KeyRound className="w-4 h-4 mr-1" />}
              {t === "PASSKEY" && <Lock className="w-4 h-4 mr-1" />}
              {t}
            </Button>
          ))}
        </div>

        <Input
          placeholder="Nhập mã 6 chữ số"
          value={mfaCode}
          onChange={(e) => setMfaCode(e.target.value)}
          maxLength={6}
          className="text-center tracking-widest text-lg font-semibold"
        />

        <Button className="w-full" onClick={handleVerifyMfa} disabled={!!loadingType}>
          {loadingType === "mfa" ? "Đang xác minh..." : "Xác nhận"}
        </Button>
      </div>
    );
  }

  return (
    <div className="w-full max-w-md mx-auto">
      <form
        onSubmit={handleSubmit(handleLogin)}
        className={cn("flex flex-col gap-6", className)}
      >
        <FieldGroup>
          <div className="flex flex-col items-center text-center gap-1">
            <h1 className="text-2xl font-bold">Login to your account</h1>
            <p className="text-sm text-muted-foreground">
              Enter your credentials below
            </p>
          </div>

          <Field>
            <FieldLabel>Username</FieldLabel>
            <Input
              placeholder="Enter your username"
              {...register("username")}
              disabled={!!loadingType}
            />
            {errors.username && (
              <p className="text-sm text-red-500">{errors.username.message}</p>
            )}
          </Field>

          <Field>
            <div className="flex items-center justify-between">
              <FieldLabel>Password</FieldLabel>
              <Link
                to="/forgot-password"
                className="text-sm underline-offset-4 hover:underline"
              >
                Forgot password?
              </Link>
            </div>
            <Input
              type="password"
              {...register("password")}
              disabled={!!loadingType}
            />
            {errors.password && (
              <p className="text-sm text-red-500">{errors.password.message}</p>
            )}
          </Field>

          <Button type="submit" disabled={!!loadingType}>
            {loadingType === "login" ? "Logging in..." : "Login"}
          </Button>

          <FieldSeparator>Or continue with</FieldSeparator>

          <div className="flex flex-col gap-2">
            <Button
              variant="outline"
              onClick={() => googleLogin()}
              disabled={!!loadingType}
            >
              <img
                src="https://www.svgrepo.com/show/475656/google-color.svg"
                alt="Google"
                className="h-4 w-4 mr-2"
              />
              Login with Google
            </Button>

            <FacebookLogin
              appId={import.meta.env.VITE_FACEBOOK_APP_ID}
              onSuccess={(res) => handleOAuthLogin("FACEBOOK", res.accessToken)}
              onFail={(err) => console.error(err)}
              render={({ onClick }) => (
                <Button
                  variant="outline"
                  onClick={onClick}
                  disabled={!!loadingType}
                >
                  <FacebookIcon className="mr-2 h-4 w-4" />
                  Login with Facebook
                </Button>
              )}
            />

            <Button
              variant="outline"
              onClick={handleGitHubLogin}
              disabled={!!loadingType}
            >
              <Github className="mr-2 h-4 w-4" />
              Login with GitHub
            </Button>

            <Button
              variant="outline"
              onClick={handlePasskeyLogin}
              disabled={!!loadingType}
            >
              <Lock className="mr-2 h-4 w-4" />
              Login with Passkey
            </Button>
          </div>

          <FieldDescription className="text-center mt-3">
            Don&apos;t have an account?{" "}
            <a href="#" className="underline underline-offset-4">
              Sign up
            </a>
          </FieldDescription>
        </FieldGroup>
      </form>
    </div>
  );
}
