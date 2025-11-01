

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
  email: yup.string().email("Email không hợp lệ").required("Email không được để trống"),
});

export default function ForgotPasswordPage() {
  const [loading, setLoading] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({ resolver: yupResolver(schema) });

  const onSubmit = async (data: any) => {
    try {
      setLoading(true);
      await authApi.resetPassword(data.email);
      toast.success("Đã gửi email đặt lại mật khẩu. Kiểm tra hộp thư của bạn!");
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Không thể gửi email reset.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto mt-12 space-y-4">
      <h1 className="text-2xl font-bold text-center">Quên mật khẩu</h1>
      <p className="text-sm text-center text-muted-foreground">
        Nhập địa chỉ email để nhận liên kết đặt lại mật khẩu
      </p>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
        <Input
          placeholder="example@gmail.com"
          {...register("email")}
          disabled={loading}
        />
        {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}
        <Button type="submit" className="w-full" disabled={loading}>
          {loading ? (
            <span className="flex items-center justify-center gap-2">
              <Loader2 className="h-4 w-4 animate-spin" /> Đang gửi...
            </span>
          ) : (
            "Gửi liên kết đặt lại"
          )}
        </Button>
      </form>
    </div>
  );
}
