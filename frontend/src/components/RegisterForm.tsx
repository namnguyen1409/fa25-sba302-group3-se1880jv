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

interface RegisterValues {
  username: string;
  fullName: string;
  email: string;
  password: string;
  confirmPassword: string;
  phone: string;
  dob: string;
  gender: "MALE" | "FEMALE" | "OTHER";
  street: string;
  wardName: string;
  districtName: string;
  city: string;
}

const schema = yup.object({
  username: yup.string().required("Vui lòng nhập username"),
  fullName: yup.string().required("Vui lòng nhập họ tên"),
  email: yup.string().email("Email không hợp lệ").required("Vui lòng nhập email"),
  password: yup.string().min(6, "Mật khẩu tối thiểu 6 ký tự").required("Vui lòng nhập mật khẩu"),

  confirmPassword: yup
    .string()
    .oneOf([yup.ref("password")], "Mật khẩu không khớp")
    .required("Vui lòng nhập lại mật khẩu"),

  phone: yup.string().required("Vui lòng nhập số điện thoại"),
  dob: yup.string().required("Vui lòng chọn ngày sinh"),
  gender: yup.string().oneOf(["MALE", "FEMALE", "OTHER"]).required("Chọn giới tính"),
  street: yup.string().required("Nhập số nhà/đường"),
  wardName: yup.string().required("Nhập phường/xã"),
  districtName: yup.string().required("Nhập quận/huyện"),
  city: yup.string().required("Nhập tỉnh/thành phố"),
});

export function RegisterForm({ className }: { className?: string }) {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<RegisterValues>({
    resolver: yupResolver(schema),
  });

  const onSubmit = async (v: RegisterValues) => {
    try {
      setLoading(true);

      await authApi.register({
        username: v.username,
        fullName: v.fullName,
        email: v.email,
        password: v.password,
        phone: v.phone,
        dob: v.dob,
        gender: v.gender,
        address: {
          street: v.street,
          wardName: v.wardName,
          districtName: v.districtName,
          city: v.city,
        },
      });

      toast.success("Đăng ký thành công! Vui lòng kiểm tra email để kích hoạt tài khoản.");
      navigate("/login");

    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Đăng ký thất bại");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-full max-w-xl mx-auto">
      <form onSubmit={handleSubmit(onSubmit)} className={cn("flex flex-col gap-6", className)}>
        <FieldGroup>
          <div className="flex flex-col items-center text-center gap-1">
            <h1 className="text-2xl font-bold">Tạo tài khoản</h1>
            <p className="text-sm text-muted-foreground">Nhập đầy đủ thông tin cá nhân</p>
          </div>

          {/* ========== GRID 2 CỘT: THÔNG TIN ========== */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

            <Field>
              <FieldLabel>Username</FieldLabel>
              <Input {...register("username")} disabled={loading} />
              {errors.username && <p className="text-sm text-red-500">{errors.username.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Họ tên</FieldLabel>
              <Input {...register("fullName")} disabled={loading} />
              {errors.fullName && <p className="text-sm text-red-500">{errors.fullName.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Email</FieldLabel>
              <Input {...register("email")} disabled={loading} />
              {errors.email && <p className="text-sm text-red-500">{errors.email.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Mật khẩu</FieldLabel>
              <Input type="password" {...register("password")} disabled={loading} />
              {errors.password && <p className="text-sm text-red-500">{errors.password.message}</p>}
            </Field>

            {/* ✅ Confirm Password */}
            <Field>
              <FieldLabel>Nhập lại mật khẩu</FieldLabel>
              <Input type="password" {...register("confirmPassword")} disabled={loading} />
              {errors.confirmPassword && (
                <p className="text-sm text-red-500">{errors.confirmPassword.message}</p>
              )}
            </Field>

            <Field>
              <FieldLabel>Số điện thoại</FieldLabel>
              <Input {...register("phone")} disabled={loading} />
              {errors.phone && <p className="text-sm text-red-500">{errors.phone.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Ngày sinh</FieldLabel>
              <Input type="date" {...register("dob")} disabled={loading} />
              {errors.dob && <p className="text-sm text-red-500">{errors.dob.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Giới tính</FieldLabel>
              <select
                {...register("gender")}
                disabled={loading}
                className="border rounded-md px-3 py-2 text-sm w-full"
              >
                <option value="">-- Chọn --</option>
                <option value="MALE">Nam</option>
                <option value="FEMALE">Nữ</option>
                <option value="OTHER">Khác</option>
              </select>
              {errors.gender && <p className="text-sm text-red-500">{errors.gender.message}</p>}
            </Field>

          </div>

          {/* ========== ĐỊA CHỈ ========== */}
          <FieldSeparator>Địa chỉ</FieldSeparator>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <Field>
              <FieldLabel>Số nhà / Đường</FieldLabel>
              <Input {...register("street")} disabled={loading} />
              {errors.street && <p className="text-sm text-red-500">{errors.street.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Phường / Xã</FieldLabel>
              <Input {...register("wardName")} disabled={loading} />
              {errors.wardName && <p className="text-sm text-red-500">{errors.wardName.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Quận / Huyện</FieldLabel>
              <Input {...register("districtName")} disabled={loading} />
              {errors.districtName && <p className="text-sm text-red-500">{errors.districtName.message}</p>}
            </Field>

            <Field>
              <FieldLabel>Tỉnh / Thành phố</FieldLabel>
              <Input {...register("city")} disabled={loading} />
              {errors.city && <p className="text-sm text-red-500">{errors.city.message}</p>}
            </Field>
          </div>

          <Button type="submit" disabled={loading} className="mt-2">
            {loading ? "Đang xử lý..." : "Đăng ký"}
          </Button>

          <FieldDescription className="text-center mt-2">
            Đã có tài khoản?{" "}
            <Link to="/login" className="underline underline-offset-4">
              Đăng nhập
            </Link>
          </FieldDescription>
        </FieldGroup>
      </form>
    </div>
  );
}
