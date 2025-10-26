"use client";

import { useState } from "react";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { useNavigate } from "react-router-dom";
import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import {
  Field,
  FieldDescription,
  FieldGroup,
  FieldLabel,
  FieldSeparator,
} from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import { authApi } from "@/api/authApi";
import { ensureDeviceId } from "@/utils/device";
import { toast } from "sonner";
import { GoogleOAuthProvider, GoogleLogin } from "@react-oauth/google";
import FacebookLogin from "@greatsumini/react-facebook-login";

interface LoginFormValues {
  username: string;
  password: string;
}

const schema = yup.object({
  username: yup.string().required(),
  password: yup.string().required("Password is required"),
});

export function LoginForm({
  className,
  ...props
}: React.ComponentProps<"form">) {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginFormValues>({
    resolver: yupResolver(schema),
  });

  const onSubmit = async (values: LoginFormValues) => {
    try {
      setLoading(true);
      const deviceId = ensureDeviceId();
      const res = await authApi.login({
        username: values.username,
        password: values.password,
        deviceId,
        rememberMe: true,
      });

      console.log("Login response:", res);

      localStorage.setItem("accessToken", res.accessToken || "");
      toast.success("Login successful!");
      navigate("/");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  const handleOAuthLogin = async (
    provider: "GOOGLE" | "FACEBOOK" | "GITHUB",
    accessToken: string
  ) => {
    try {
      const deviceId = ensureDeviceId();
      const res = await authApi.oauthLogin({
        provider,
        accessToken,
        deviceId,
        rememberMe: true,
      });

      console.log("OAuth login response:", res);
      localStorage.setItem("accessToken", res.accessToken || "");
      toast.success(`Logged in with ${provider}`);
      navigate("/");
    } catch (err: any) {
      console.error(err);
      toast.error(err?.response?.data?.message || "OAuth login failed");
      console.log("OAuth login failed", err);
    }
  };

  const handleGitHubLogin = () => {
    const clientId = import.meta.env.VITE_GITHUB_CLIENT_ID;
    const redirectUri = import.meta.env.VITE_GITHUB_REDIRECT_URI;
    const githubUrl = `https://github.com/login/oauth/authorize?client_id=${clientId}&scope=user:email&redirect_uri=${encodeURIComponent(
      redirectUri
    )}`;
    window.location.href = githubUrl;
  };

  return (
    <GoogleOAuthProvider clientId={import.meta.env.VITE_GOOGLE_CLIENT_ID}>
      <form
        onSubmit={handleSubmit(onSubmit)}
        className={cn("flex flex-col gap-6", className)}
        {...props}
      >
        <FieldGroup>
          <div className="flex flex-col items-center gap-1 text-center">
            <h1 className="text-2xl font-bold">Login to your account</h1>
            <p className="text-muted-foreground text-sm text-balance">
              Enter your email below to login to your account
            </p>
          </div>

          {/* Username */}
          <Field>
            <FieldLabel htmlFor="username">Username</FieldLabel>
            <Input
              placeholder="username"
              {...register("username")}
              disabled={loading}
            />
            {errors.username && (
              <p className="text-sm text-red-500 mt-1">
                {errors.username.message}
              </p>
            )}
          </Field>

          {/* Password */}
          <Field>
            <div className="flex items-center">
              <FieldLabel htmlFor="password">Password</FieldLabel>
              <a
                href="#"
                className="ml-auto text-sm underline-offset-4 hover:underline"
              >
                Forgot your password?
              </a>
            </div>
            <Input
              id="password"
              type="password"
              {...register("password")}
              disabled={loading}
            />
            {errors.password && (
              <p className="text-sm text-red-500 mt-1">
                {errors.password.message}
              </p>
            )}
          </Field>

          {/* Submit */}
          <Field>
            <Button type="submit" disabled={loading}>
              {loading ? "Logging in..." : "Login"}
            </Button>
          </Field>

          <FieldSeparator>Or continue with</FieldSeparator>

          {/* OAuth Buttons */}
          <div className="flex flex-col gap-2">
            {/* GOOGLE */}
            <GoogleLogin
              onSuccess={(credentialResponse) =>
                handleOAuthLogin("GOOGLE", credentialResponse.credential!)
              }
              onError={() => toast.error("Google login failed")}
            />

            {/* FACEBOOK */}
            <FacebookLogin
              appId={import.meta.env.VITE_FACEBOOK_APP_ID}
              onSuccess={(response) => {
                handleOAuthLogin("FACEBOOK", response.accessToken);
              }}
              onFail={(error) => {
                console.error("Facebook login failed:", error);
              }}
              render={({ onClick }) => (
                <Button variant="outline" type="button" onClick={onClick}>
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 24 24"
                    fill="currentColor"
                    className="mr-2 h-4 w-4"
                  >
                    <path d="M22.675 0H1.325C.593 0 0 .593 0 1.325v21.351C0 23.406.593 24 1.325 24H12v-9.294H9.294V11.41H12V8.863c0-2.675 1.633-4.131 4.017-4.131 1.144 0 2.126.085 2.414.123v2.8h-1.659c-1.3 0-1.55.617-1.55 1.518v1.985h3.1l-.404 3.296H15.22V24h7.455c.732 0 1.325-.594 1.325-1.324V1.325C24 .593 23.406 0 22.675 0z" />
                  </svg>
                  Login with Facebook
                </Button>
              )}
            />

            {/* GITHUB */}
            <Button variant="outline" type="button" onClick={handleGitHubLogin}>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 24 24"
                className="mr-2 h-4 w-4"
                fill="currentColor"
              >
                <path d="M12 .297C5.373.297 0 5.67 0 12.297c0 5.303 3.438 9.8 8.205 11.385.6.113.82-.258.82-.577v-2.04c-3.338.724-4.042-1.61-4.042-1.61C4.422 18.07 3.633 17.7 3.633 17.7c-1.087-.744.084-.729.084-.729 1.205.084 1.838 1.236 1.838 1.236 1.07 1.835 2.809 1.305 3.495.998.108-.776.417-1.305.76-1.605-2.665-.3-5.466-1.332-5.466-5.93 0-1.31.465-2.38 1.235-3.22-.135-.303-.54-1.523.105-3.176 0 0 1.005-.322 3.3 1.23.96-.267 1.98-.399 3-.405 1.02.006 2.04.138 3 .405 2.28-1.552 3.285-1.23 3.285-1.23.645 1.653.24 2.873.12 3.176.765.84 1.23 1.91 1.23 3.22 0 4.61-2.805 5.625-5.475 5.92.42.36.81 1.096.81 2.22v3.286c0 .315.21.69.825.57C20.565 22.092 24 17.592 24 12.297 24 5.67 18.627.297 12 .297z" />
              </svg>
              Login with GitHub
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
    </GoogleOAuthProvider>
  );
}
