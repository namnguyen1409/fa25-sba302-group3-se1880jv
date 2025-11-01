

import { useSearchParams, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { toast } from "sonner";
import { authApi } from "@/api/authApi";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { useState } from "react";
import { Loader2 } from "lucide-react";

const schema = yup.object({
  newPassword: yup
    .string()
    .min(6, "Mật khẩu phải có ít nhất 6 ký tự")
    .required("Vui lòng nhập mật khẩu mới"),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref("newPassword")], "Mật khẩu xác nhận không khớp")
    .required("Vui lòng nhập lại mật khẩu"),
});

export default function ResetPasswordPage() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const token = searchParams.get("token");
  const [loading, setLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const onSubmit = async (data: any) => {
    if (!token) {
      toast.error("Liên kết không hợp lệ hoặc đã hết hạn");
      return;
    }

    try {
      setLoading(true);
      await authApi.confirmResetPassword(token, data.newPassword);
      toast.success("Đặt lại mật khẩu thành công!");
      navigate("/login");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Không thể đặt lại mật khẩu.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-12 space-y-4">
      <h1 className="text-2xl font-bold text-center">Đặt lại mật khẩu</h1>
      <p className="text-sm text-center text-muted-foreground">
        Nhập mật khẩu mới của bạn bên dưới
      </p>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
        <Input
          type="password"
          placeholder="Mật khẩu mới"
          {...register("newPassword")}
          disabled={loading}
        />
        {errors.newPassword && <p className="text-red-500 text-sm">{errors.newPassword.message}</p>}
        <Input
          type="password"
          placeholder="Xác nhận mật khẩu"
          {...register("confirmPassword")}
          disabled={loading}
        />
        {errors.confirmPassword && (
          <p className="text-red-500 text-sm">{errors.confirmPassword.message}</p>
        )}
        <Button type="submit" className="w-full" disabled={loading}>
          {loading ? (
            <span className="flex items-center justify-center gap-2">
              <Loader2 className="h-4 w-4 animate-spin" /> Đang cập nhật...
            </span>
          ) : (
            "Cập nhật mật khẩu"
          )}
        </Button>
      </form>
    </div>
  );
}
