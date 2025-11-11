

import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { toast } from "sonner";
import {
  Card, CardHeader, CardTitle, CardContent,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Skeleton } from "@/components/ui/skeleton";
import { Smartphone, Mail, KeyRound, Lock, X, Plus, Loader2, ShieldCheck } from "lucide-react";
import {
  Dialog, DialogContent, DialogHeader, DialogTitle,
} from "@/components/ui/dialog";
import QRCode from "react-qr-code";
import { startRegistration } from "@simplewebauthn/browser";
import { AccountApi } from "@/api/user/accountApi";
import type { MfaConfigResponse } from "@/types/account";
import { useAuth } from "@/context/AuthContext";

const passwordSchema = yup.object({
  currentPassword: yup.string().required("Vui lòng nhập mật khẩu hiện tại"),
  newPassword: yup
    .string()
    .required("Vui lòng nhập mật khẩu mới")
    .min(6, "Mật khẩu phải có ít nhất 6 ký tự")
    .notOneOf([yup.ref("currentPassword")], "Mật khẩu mới không được trùng với mật khẩu cũ"),
});

const emailSchema = yup.object({
  email: yup.string().required("Vui lòng nhập email").email("Email không hợp lệ"),
  otp: yup.string().nullable().max(6, "OTP không hợp lệ"),
});

export default function AccountSecurityPage() {
  const { user } = useAuth();

  const [loading, setLoading] = useState(true);
  const [mfaEnabled, setMfaEnabled] = useState(false);
  const [methods, setMethods] = useState<MfaConfigResponse[]>([]);

  const [openAddModal, setOpenAddModal] = useState(false);
  const [openEmailModal, setOpenEmailModal] = useState(false);
  const [openTotpModal, setOpenTotpModal] = useState(false);

  const [totpSecret, setTotpSecret] = useState("");
  const [totpQr, setTotpQr] = useState("");
  const [otpCode, setOtpCode] = useState("");
  const [verifyingTotp, setVerifyingTotp] = useState(false);
  const [addingPasskey, setAddingPasskey] = useState(false);
  const [emailChallengeId, setEmailChallengeId] = useState<string | null>(null);

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    resolver: yupResolver(passwordSchema),
  });


  const {
    register: registerEmail,
    handleSubmit: handleEmailSubmit,
    formState: { errors: emailErrors },
    setValue,
  } = useForm({
    resolver: yupResolver(emailSchema),
  });


  useEffect(() => {
    bootstrap();
  }, []);

  const bootstrap = async () => {
    setLoading(true);
    try {
      const list = await AccountApi.getMfaMethods();
      setMethods(list || []);
      setMfaEnabled(user?.mfaEnabled || false);
    } catch {
      toast.error("Không thể tải cài đặt bảo mật");
    } finally {
      setLoading(false);
    }
  };

  const refresh = async () => {
    try {
      const list = await AccountApi.getMfaMethods();
      setMethods(list || []);
      setMfaEnabled(user?.mfaEnabled || false);
    } catch {}
  };

  const handleToggleMfa = async () => {
    try {
      if (mfaEnabled) {
        const code = prompt("Nhập mật khẩu (hoặc mã MFA/backup) để tắt MFA:");
        if (!code) return;
        await AccountApi.disableMFA("PASSWORD", code);
        toast.success("Đã tắt MFA");
      } else {
        await AccountApi.enableMFA();
        toast.success("Đã bật MFA");
      }
      await refresh();
    } catch (err: any) {
      toast.error(err?.response?.data?.message || "Không thể thay đổi trạng thái MFA");
    }
  };

  const handleEnableTOTP = async () => {
    try {
      const res = await AccountApi.requestTOTP();
      setTotpSecret(res.secret);
      setTotpQr(res.qrUri);
      setOpenAddModal(false);
      setOpenTotpModal(true);
    } catch {
      toast.error("Không thể khởi tạo TOTP");
    }
  };

  const handleConfirmTOTP = async () => {
    if (!otpCode.trim()) return toast.error("Nhập mã 6 số từ ứng dụng Authenticator");
    try {
      setVerifyingTotp(true);
      await AccountApi.confirmTOTP(otpCode.trim(), totpSecret);
      toast.success("Đã bật TOTP");
      setOpenTotpModal(false);
      setOtpCode("");
      await refresh();
    } catch (e: any) {
      toast.error(e?.response?.data?.message || "Mã OTP không hợp lệ");
    } finally {
      setVerifyingTotp(false);
    }
  };

  const handleInitEmailMfa = async (data: any) => {
    try {
      const res = await AccountApi.initEmailMfa(data.email.trim());
      setEmailChallengeId(res.challengeId);
      toast.info("Đã gửi mã OTP đến email");
    } catch (e: any) {
      toast.error(e?.response?.data?.message || "Không thể gửi mã xác minh");
    }
  };

  const handleConfirmEmailMfa = async (data: any) => {
    if (!emailChallengeId) return toast.error("Thiếu challenge ID");
    try {
      await AccountApi.confirmEmailMfa(emailChallengeId, data.otp.trim());
      toast.success("Đã bật xác thực Email");
      setOpenEmailModal(false);
      setEmailChallengeId(null);
      setValue("email", "");
      setValue("otp", "");
      await refresh();
    } catch (e: any) {
      toast.error(e?.response?.data?.message || "Mã OTP không hợp lệ");
    }
  };

  // ===== Passkey
  const handleAddPasskey = async () => {
    try {
      setAddingPasskey(true);
      const options = await AccountApi.startRegistration();
      options.extensions = {};
      options.authenticatorSelection = { residentKey: "required", userVerification: "preferred" };
      const att = await startRegistration(options);
      await AccountApi.finishRegistration(JSON.stringify(att));
      toast.success("Đã thêm Passkey");
      setOpenAddModal(false);
      await refresh();
    } catch (e: any) {
      toast.error(e?.message || "Không thể thêm Passkey");
    } finally {
      setAddingPasskey(false);
    }
  };

  // ===== Delete MFA method
  const handleDeleteMethod = async (m: MfaConfigResponse) => {
    const code = prompt(`Nhập mật khẩu (hoặc mã xác minh) để xoá ${m.mfaType}:`);
    if (!code) return;
    try {
      await AccountApi.deleteMfaConfig(m.id, "PASSWORD", code);
      toast.success(`Đã xoá ${m.mfaType}`);
      await refresh();
    } catch (e: any) {
      toast.error(e?.response?.data?.message || "Không thể xoá phương thức");
    }
  };

  // ===== Backup codes
  const handleGenerateBackupCodes = async () => {
    try {
      const codes: string[] = await AccountApi.generateBackupCodes();
      if (!codes?.length) return toast.info("Không có mã dự phòng");
      const blob = new Blob([codes.join("\n")], { type: "text/plain;charset=utf-8" });
      const url = URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = "backup-codes.txt";
      a.click();
      URL.revokeObjectURL(url);
      toast.success("Đã tạo backup codes (đã tải file)");
    } catch {
      toast.error("Không thể tạo backup codes");
    }
  };


  if (loading)
    return (
      <div className="p-6 max-w-3xl mx-auto space-y-4">
        <Skeleton className="h-10 w-1/2" />
        <Skeleton className="h-[180px]" />
        <Skeleton className="h-[380px]" />
      </div>
    );

  return (
    <div className="p-6 max-w-3xl mx-auto space-y-6">
      {/* GLOBAL MFA STATUS */}
      <Card>
        <CardHeader className="flex items-center justify-between">
          <div className="space-y-1">
            <CardTitle className="flex items-center gap-2">
              <ShieldCheck className="w-5 h-5" /> Bảo mật tài khoản
            </CardTitle>
            <p className="text-sm text-muted-foreground">
              Trạng thái MFA:{" "}
              <span className={mfaEnabled ? "text-green-600 font-medium" : "text-red-500 font-medium"}>
                {mfaEnabled ? "Đang bật" : "Đang tắt"}
              </span>
            </p>
          </div>
          <Button
            variant={mfaEnabled ? "destructive" : "default"}
            onClick={handleToggleMfa}
          >
            {mfaEnabled ? "Tắt MFA" : "Bật MFA"}
          </Button>
        </CardHeader>
      </Card>

      {/* MFA METHODS */}
      <Card>
        <CardHeader className="flex justify-between items-center">
          <CardTitle>Phương thức xác thực</CardTitle>
          <div className="flex gap-2">
            <Button variant="outline" onClick={handleGenerateBackupCodes}>
              Tạo Backup Codes
            </Button>
            <Button variant="outline" onClick={() => setOpenAddModal(true)}>
              <Plus className="w-4 h-4 mr-1" /> Thêm phương thức
            </Button>
          </div>
        </CardHeader>
        <CardContent className="space-y-3">
          {methods.length === 0 ? (
            <p className="text-sm text-muted-foreground">Chưa có phương thức nào.</p>
          ) : (
            methods.map((m) => (
              <div key={m.id} className="flex items-center justify-between border p-3 rounded-lg hover:bg-muted/40 transition">
                <div className="flex items-center gap-3">
                  {m.mfaType === "TOTP" && <KeyRound className="text-primary" />}
                  {m.mfaType === "EMAIL" && <Mail className="text-primary" />}
                  {m.mfaType === "PASSKEY" && <Lock className="text-primary" />}
                  {m.mfaType === "SMS" && <Smartphone className="text-primary" />}
                  <div>
                    <p className="font-medium">{m.mfaType}</p>
                    <p className="text-xs text-muted-foreground">
                      {m.contact || "—"} {m.primary ? "— chính" : m.revoked ? "(đã vô hiệu)" : ""}
                    </p>
                  </div>
                </div>
                <Button variant="destructive" size="sm" onClick={() => handleDeleteMethod(m)}>
                  <X className="w-4 h-4 mr-1" /> Xoá
                </Button>
              </div>
            ))
          )}
        </CardContent>
      </Card>

      {/* PASSWORD MANAGEMENT */}
      <Card>
        <CardHeader>
          <CardTitle>Quản lý mật khẩu</CardTitle>
        </CardHeader>
        <CardContent>
          <form
            onSubmit={handleSubmit(async (values) => {
              try {
                await AccountApi.changePassword(values.currentPassword, values.newPassword);
                toast.success("Đổi mật khẩu thành công");
                reset();
              } catch (e: any) {
                toast.error(e?.response?.data?.message || "Đổi mật khẩu thất bại");
              }
            })}
            className="space-y-3"
          >
            <div>
              <Input type="password" placeholder="Mật khẩu hiện tại" {...register("currentPassword")} />
              {errors.currentPassword && <p className="text-red-500 text-sm">{errors.currentPassword.message}</p>}
            </div>
            <div>
              <Input type="password" placeholder="Mật khẩu mới" {...register("newPassword")} />
              {errors.newPassword && <p className="text-red-500 text-sm">{errors.newPassword.message}</p>}
            </div>
            <div className="flex gap-2">
              <Button className="w-full" type="submit">
                <Loader2 className="w-4 h-4 animate-spin hidden" /> Đổi mật khẩu
              </Button>
            </div>
          </form>
        </CardContent>
      </Card>

      {/* EMAIL MFA MODAL */}
      <Dialog open={openEmailModal} onOpenChange={setOpenEmailModal}>
        <DialogContent className="max-w-md">
          <DialogHeader>
            <DialogTitle>Đăng ký xác thực Email</DialogTitle>
          </DialogHeader>
          <form onSubmit={handleEmailSubmit(emailChallengeId ? handleConfirmEmailMfa : handleInitEmailMfa)} className="space-y-3">
            <Input placeholder="example@gmail.com" {...registerEmail("email")} />
            {emailErrors.email && <p className="text-red-500 text-sm">{emailErrors.email.message}</p>}
            {!emailChallengeId ? (
              <Button className="w-full mt-2" type="submit">
                Gửi mã OTP
              </Button>
            ) : (
              <>
                <Input
                  placeholder="Nhập mã 6 chữ số"
                  {...registerEmail("otp")}
                  className="text-center text-lg mt-2"
                  maxLength={6}
                />
                {emailErrors.otp && <p className="text-red-500 text-sm">{emailErrors.otp.message}</p>}
                <Button className="w-full mt-2" type="submit">
                  Xác nhận & bật Email MFA
                </Button>
              </>
            )}
          </form>
        </DialogContent>
      </Dialog>

      {/* TOTP MODAL */}
      <Dialog open={openTotpModal} onOpenChange={setOpenTotpModal}>
        <DialogContent className="max-w-md">
          <DialogHeader>
            <DialogTitle>Kết nối ứng dụng Authenticator</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 text-center">
            <p className="text-sm text-muted-foreground">Quét mã QR bằng ứng dụng Authenticator và nhập mã 6 chữ số.</p>
            <div className="flex justify-center">
              <QRCode value={totpQr} size={160} className="border rounded-md" />
            </div>
            <p className="font-mono text-xs bg-muted p-2 rounded-md select-all">Secret: {totpSecret}</p>
            <Input
              placeholder="Nhập mã 6 chữ số"
              value={otpCode}
              onChange={(e) => setOtpCode(e.target.value)}
              maxLength={6}
              className="text-center tracking-widest text-lg"
            />
            <Button className="w-full" onClick={handleConfirmTOTP} disabled={verifyingTotp}>
              {verifyingTotp ? "Đang xác minh…" : "Xác nhận & bật TOTP"}
            </Button>
          </div>
        </DialogContent>
      </Dialog>

      {/* ADD MFA MODAL */}
      <Dialog open={openAddModal} onOpenChange={setOpenAddModal}>
        <DialogContent className="max-w-sm">
          <DialogHeader>
            <DialogTitle>Thêm phương thức xác thực</DialogTitle>
          </DialogHeader>
          <div className="grid gap-2">
            <Button onClick={handleEnableTOTP} variant="outline">
              <KeyRound className="w-4 h-4 mr-2" /> Ứng dụng Authenticator (TOTP)
            </Button>
            <Button onClick={() => setOpenEmailModal(true)} variant="outline">
              <Mail className="w-4 h-4 mr-2" /> Xác thực qua Email
            </Button>
            <Button onClick={handleAddPasskey} variant="outline" disabled={addingPasskey}>
              <Lock className="w-4 h-4 mr-2" /> {addingPasskey ? "Đang thêm…" : "Passkey (WebAuthn)"}
            </Button>
            <Button variant="outline" onClick={() => toast.error("Chưa hỗ trợ SMS")}>
              <Smartphone className="w-4 h-4 mr-2" /> Xác thực qua SMS
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
}