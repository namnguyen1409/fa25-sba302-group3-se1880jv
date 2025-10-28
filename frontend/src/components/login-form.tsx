"use client";

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
import { Github, Lock, FacebookIcon, KeyRound, Mail, Phone, RefreshCcw } from "lucide-react";
import { startAuthentication } from "@simplewebauthn/browser";

interface LoginFormValues {
  username: string;
  password: string;
}

const schema = yup.object({
  username: yup.string().required("Username is required"),
  password: yup.string().required("Password is required"),
});

type MfaType = "EMAIL" | "SMS" | "TOTP" | "PASSKEY";

export function LoginForm({ className }: { className?: string }) {
  const navigate = useNavigate();
  const { loginSuccess } = useAuth();
  const [loadingType, setLoadingType] = useState<
    "login" | "oauth" | "passkey" | "mfa" | "switch" | "resend" | null
  >(null);

  // --- MFA states ---
  const [mfaChallenge, setMfaChallenge] = useState<any>(null);
  const [selectedMfaType, setSelectedMfaType] = useState<MfaType | undefined>(undefined);
  const [mfaCode, setMfaCode] = useState("");
  const [trustDevice, setTrustDevice] = useState(true); // mặc định trust cho UX tốt

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
        setSelectedMfaType(res.defaultMfaType as MfaType);
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

  /** ----------- MFA: SWITCH METHOD ----------- **/
  const handleSwitchMfa = async (type: MfaType) => {
    if (!mfaChallenge?.challengeId) return;
    try {
      setLoadingType("switch");
      const res = await authApi.switchMfa({
        mfaType: type,
        challengeId: mfaChallenge.challengeId,
      });
      setMfaChallenge(res);
      setSelectedMfaType(res.defaultMfaType as MfaType);
      setMfaCode("");
      toast.success(`Đã chuyển sang ${type}`);
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Không thể chuyển phương thức");
    } finally {
      setLoadingType(null);
    }
  };

  /** ----------- MFA: RESEND OTP ----------- **/
  const handleResendOtp = async () => {
    if (!mfaChallenge?.challengeId) return;
    try {
      setLoadingType("resend");
      await authApi.resendMfa({ challengeId: mfaChallenge.challengeId });
      toast.success("Đã gửi lại mã");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Không thể gửi lại mã");
    } finally {
      setLoadingType(null);
    }
  };

  /** ----------- MFA: VERIFY (EMAIL/SMS/TOTP) ----------- **/
  const handleVerifyMfa = async () => {
    if (!mfaChallenge?.challengeId || !selectedMfaType) return;

    // PASSKEY không nhập code → dùng flow riêng
    if (selectedMfaType === "PASSKEY") {
      return handlePasskeyInMfa();
    }

    // validate 6 digits cho EMAIL/SMS/TOTP
    if (!/^\d{6}$/.test(mfaCode)) {
      toast.error("Mã phải gồm 6 chữ số");
      return;
    }

    try {
      setLoadingType("mfa");
      const res = await authApi.verifyMfa(
        mfaChallenge.challengeId,
        mfaCode,
        ensureDeviceId(),
        trustDevice // ghi nhớ thiết bị nếu true
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

  /** ----------- MFA: PASSKEY FLOW ----------- **/
  const handlePasskeyInMfa = async () => {
    try {
      setLoadingType("mfa");
      const start = await authApi.loginPasskeyStart();
      const options = start.requestOptions;
      options.extensions = {};

      const assertion = await startAuthentication({ optionsJSON: options });
      const res = await authApi.loginPasskeyFinish({
        requestId: start.requestId,
        responseJson: JSON.stringify(assertion),
        deviceId: ensureDeviceId(),
        rememberMe: true
      });

      localStorage.setItem("accessToken", res.accessToken || "");
      await loginSuccess();
      toast.success("Login successful!");
      navigate("/");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Passkey thất bại");
    } finally {
      setLoadingType(null);
    }
  };

  /** ----------- OAUTH ----------- **/
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
        setSelectedMfaType(res.defaultMfaType as MfaType);
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

  /** ----------- PASSKEY (đăng nhập trực tiếp) ----------- **/
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

      // nếu backend có policy: có thể vẫn trả requires2FA (ví dụ passkey = 1 yếu tố)
      if (finishRes.requires2FA) {
        setMfaChallenge(finishRes);
        setSelectedMfaType(finishRes.defaultMfaType as MfaType);
        return;
      }

      localStorage.setItem("accessToken", finishRes.accessToken || "");
      await loginSuccess();
      toast.success("Login successful!");
      navigate("/");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Passkey not supported or failed");
    } finally {
      setLoadingType(null);
    }
  };

  /** ----------- RENDER: MFA STEP ----------- **/
  if (mfaChallenge) {
    const methods: MfaType[] = (mfaChallenge.mfaTypes || []) as MfaType[];
    const isCodeBased = selectedMfaType === "EMAIL" || selectedMfaType === "SMS" || selectedMfaType === "TOTP";
    const methodIcon = (t: MfaType) =>
      t === "EMAIL" ? <Mail className="w-4 h-4 mr-1" /> :
      t === "SMS" ? <Phone className="w-4 h-4 mr-1" /> :
      t === "TOTP" ? <KeyRound className="w-4 h-4 mr-1" /> :
      <Lock className="w-4 h-4 mr-1" />;

    return (
      <div className="w-full max-w-md mx-auto space-y-4">
        <h2 className="text-xl font-bold text-center">Xác minh bảo mật</h2>
        <p className="text-sm text-muted-foreground text-center">
          Chọn phương thức và hoàn tất xác thực.
        </p>

        {/* switch buttons */}
        <div className="flex flex-wrap justify-center gap-2">
          {methods.map((t) => (
            <Button
              key={t}
              size="sm"
              variant={selectedMfaType === t ? "default" : "outline"}
              onClick={() => handleSwitchMfa(t)}
              disabled={loadingType === "switch"}
            >
              {methodIcon(t)} {t}
            </Button>
          ))}
        </div>

        {/* code input (EMAIL/SMS/TOTP) */}
        {isCodeBased && (
          <>
            <Input
              placeholder="Nhập mã 6 chữ số"
              value={mfaCode}
              onChange={(e) => setMfaCode(e.target.value.replace(/\D/g, "").slice(0, 6))}
              maxLength={6}
              className="text-center tracking-widest text-lg font-semibold"
            />
            <div className="flex items-center justify-between text-sm">
              <label className="flex items-center gap-2 select-none cursor-pointer">
                <input
                  type="checkbox"
                  checked={trustDevice}
                  onChange={(e) => setTrustDevice(e.target.checked)}
                />
                Trust this device
              </label>

              <Button
                variant="ghost"
                size="sm"
                onClick={handleResendOtp}
                disabled={loadingType === "resend"}
              >
                <RefreshCcw className="w-4 h-4 mr-1" />
                Gửi lại mã
              </Button>
            </div>

            <Button className="w-full" onClick={handleVerifyMfa} disabled={loadingType === "mfa"}>
              {loadingType === "mfa" ? "Đang xác minh..." : "Xác nhận"}
            </Button>
          </>
        )}

        {/* passkey action */}
        {selectedMfaType === "PASSKEY" && (
          <>
            <label className="flex items-center gap-2 text-sm select-none">
              <input
                type="checkbox"
                checked={trustDevice}
                onChange={(e) => setTrustDevice(e.target.checked)}
              />
              Trust this device
            </label>
            <Button className="w-full" onClick={handlePasskeyInMfa} disabled={loadingType === "mfa"}>
              {loadingType === "mfa" ? "Đang xác minh..." : "Xác thực bằng Passkey"}
            </Button>
          </>
        )}
      </div>
    );
  }

  /** ----------- RENDER: LOGIN STEP ----------- **/
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
              autoComplete="username"
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
              autoComplete="current-password"
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
