import { useEffect, useState } from "react";
import { toast } from "sonner";
import { AccountApi } from "@/api/user/accountApi";
import type { AccountSettingResponse } from "@/types/account";
import {
  Card,
  CardHeader,
  CardTitle,
  CardContent,
  CardFooter,
} from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Skeleton } from "@/components/ui/skeleton";
import { Badge } from "@/components/ui/badge";
import { CheckCircle2, XCircle, Mail } from "lucide-react";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";

export default function AccountSettingsPage() {
  const [settings, setSettings] = useState<AccountSettingResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [updating, setUpdating] = useState(false);
  const [usernameInput, setUsernameInput] = useState("");
  const [openEmailModal, setOpenEmailModal] = useState(false);
  const [newEmail, setNewEmail] = useState("");

  useEffect(() => {
    (async () => {
      try {
        const res = await AccountApi.getAccountSettings();
        setSettings(res);
        setUsernameInput(res.username || "");
      } catch (err: any) {
        toast.error(err.message || "Không thể tải cài đặt tài khoản");
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const handleSaveUsername = async () => {
    if (!usernameInput.trim()) {
      toast.error("Vui lòng nhập tên đăng nhập hợp lệ");
      return;
    }
    try {
      setUpdating(true);
      const data = await AccountApi.updateUsername(usernameInput.trim());
      toast.success("Cập nhật tên đăng nhập thành công");
      setSettings(data);
    } catch (err: any) {
      toast.error(err.message || "Không thể cập nhật tên đăng nhập");
    } finally {
      setUpdating(false);
    }
  };

  const handleEmailChangeRequest = async () => {
    if (!newEmail.trim()) {
      toast.error("Vui lòng nhập email mới");
      return;
    }
    try {
      setUpdating(true);
      await AccountApi.requestEmailChange(newEmail.trim());
      toast.success(
        `Đã gửi email xác nhận tới ${newEmail}. Vui lòng kiểm tra hộp thư.`
      );
      setOpenEmailModal(false);
    } catch (err: any) {
      toast.error(err.message || "Không thể gửi yêu cầu xác minh email");
    } finally {
      setUpdating(false);
    }
  };

  if (loading)
    return (
      <div className="p-4 max-w-3xl mx-auto space-y-4">
        <Skeleton className="h-10 w-1/3" />
        <Skeleton className="h-[200px] w-full" />
      </div>
    );

  if (!settings)
    return (
      <div className="p-4 max-w-3xl mx-auto">
        <Card>
          <CardContent>Không tìm thấy thông tin tài khoản</CardContent>
        </Card>
      </div>
    );

  return (
    <div className="p-4 max-w-3xl mx-auto space-y-6">
      {/* Account info */}
      <Card>
        <CardHeader>
          <CardTitle className="text-lg font-semibold">
            Cài đặt tài khoản
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-5">
          {/* Username */}
          <div>
            <p className="text-sm text-muted-foreground mb-1">Tên đăng nhập</p>
            {settings.firstLogin && (
              <p className="text-xs text-muted-foreground mb-2">
                Bạn chỉ được phép thay đổi tên đăng nhập 1 lần duy nhất.
              </p>
            )}
            {settings.firstLogin || !settings.username ? (
              <div className="flex items-center gap-2">
                <input
                  type="text"
                  value={usernameInput}
                  onChange={(e) => setUsernameInput(e.target.value)}
                  placeholder="Nhập tên đăng nhập..."
                  className="border rounded-md px-2 py-1 text-sm flex-1"
                />
                <Button
                  size="sm"
                  onClick={handleSaveUsername}
                  disabled={updating || !usernameInput.trim()}
                >
                  Lưu
                </Button>
              </div>
            ) : (
              <p className="font-medium">{settings.username}</p>
            )}
          </div>

          {/* Email */}
          <div>
            <p className="text-sm text-muted-foreground mb-1">Email</p>
            <div className="flex items-center justify-between">
              <p className="font-medium">
                {settings.email || "Chưa cập nhật"}
              </p>
              <Button
                size="sm"
                variant="outline"
                onClick={() => setOpenEmailModal(true)}
              >
                <Mail className="h-4 w-4 mr-1" /> Đổi email
              </Button>
            </div>
          </div>

          <Separator />

          {/* Trạng thái */}
          <div>
            <p className="text-sm text-muted-foreground mb-1">Trạng thái</p>
            {settings.active ? (
              <Badge variant="outline" className="bg-green-100 text-green-700">
                <CheckCircle2 className="h-4 w-4 mr-1" /> Hoạt động
              </Badge>
            ) : (
              <Badge variant="outline" className="bg-red-100 text-red-700">
                <XCircle className="h-4 w-4 mr-1" /> Bị khóa
              </Badge>
            )}
          </div>
        </CardContent>
      </Card>

      {/* OAuth linked accounts */}
      <Card>
        <CardHeader>
          <CardTitle className="text-lg font-semibold">
            Tài khoản liên kết
          </CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          {settings.OAuthAccounts?.length === 0 && (
            <p className="text-sm text-muted-foreground">
              Chưa liên kết tài khoản OAuth nào
            </p>
          )}
          {settings.OAuthAccounts?.map((acc, idx) => (
            <div
              key={idx}
              className="flex items-center justify-between border p-3 rounded-lg bg-muted/30"
            >
              <div className="flex items-center gap-3">
                <Avatar className="h-9 w-9">
                  <AvatarImage src={acc.avatarUrl} />
                  <AvatarFallback>
                    {acc.provider[0].toUpperCase()}
                  </AvatarFallback>
                </Avatar>
                <div>
                  <p className="font-medium">{acc.name}</p>
                  <p className="text-xs text-muted-foreground">
                    {acc.email} — {acc.provider}
                  </p>
                </div>
              </div>
            </div>
          ))}
        </CardContent>
        <CardFooter className="text-xs text-muted-foreground">
          Liên kết tài khoản giúp bạn đăng nhập nhanh bằng Google, Facebook hoặc
          GitHub.
        </CardFooter>
      </Card>

      {/* Email change modal */}
      <Dialog open={openEmailModal} onOpenChange={setOpenEmailModal}>
        <DialogContent className="max-w-md">
          <DialogHeader>
            <DialogTitle>Đổi email</DialogTitle>
          </DialogHeader>
          <div className="space-y-3 py-2">
            <Input
              type="email"
              placeholder="Nhập email mới"
              value={newEmail}
              onChange={(e) => setNewEmail(e.target.value)}
            />
            <p className="text-xs text-muted-foreground">
              Sau khi xác nhận, hệ thống sẽ gửi liên kết xác minh đến email mới.
            </p>
          </div>
          <div className="flex justify-end gap-2 pt-3">
            <Button variant="outline" onClick={() => setOpenEmailModal(false)}>
              Hủy
            </Button>
            <Button onClick={handleEmailChangeRequest} disabled={updating}>
              Gửi xác nhận
            </Button>
          </div>
        </DialogContent>
      </Dialog>
    </div>
  );
}
