"use client";

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
import { Switch } from "@/components/ui/switch";
import { Separator } from "@/components/ui/separator";
import { Skeleton } from "@/components/ui/skeleton";
import { Badge } from "@/components/ui/badge";
import { CheckCircle2, XCircle, Link2, Unlink } from "lucide-react";
import { Avatar, AvatarFallback, AvatarImage } from "@radix-ui/react-avatar";
import { useImageLink } from "@/hooks/useImageLink";

export default function AccountSettingsPage() {
  const [settings, setSettings] = useState<AccountSettingResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [updating, setUpdating] = useState(false);

  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        const res = await AccountApi.getAccountSettings();
        setSettings(res);
      } catch (err: any) {
        toast.error(err.message || "Không thể tải cài đặt tài khoản");
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  const handleToggleMfa = async (enabled: boolean) => {
    try {
      setUpdating(true);
      await AccountApi.updateAccountSettings({ mfaEnabled: enabled });
      setSettings((prev) => prev && { ...prev, mfaEnabled: enabled });
      toast.success(
        enabled ? "Đã bật xác thực 2 lớp" : "Đã tắt xác thực 2 lớp"
      );
    } catch (err: any) {
      toast.error(err.message || "Cập nhật thất bại");
    } finally {
      setUpdating(false);
    }
  };

  if (loading)
    return (
      <div className="p-4 space-y-4 max-w-3xl mx-auto">
        <Skeleton className="h-10 w-1/2" />
        <Skeleton className="h-[200px] w-full" />
        <Skeleton className="h-[200px] w-full" />
      </div>
    );

  if (!settings)
    return (
      <div className="p-4">
        <Card>
          <CardContent>Không tìm thấy thông tin tài khoản</CardContent>
        </Card>
      </div>
    );

  return (
    <div className="p-4 max-w-3xl mx-auto space-y-6">
      <Card>
        <CardHeader>
          <CardTitle>Cài đặt tài khoản</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div>
              <p className="text-sm text-muted-foreground">Tên đăng nhập</p>
              <p className="font-medium">{settings.username}</p>
            </div>
            <div>
              <p className="text-sm text-muted-foreground">Email</p>
              <p className="font-medium">{settings.email}</p>
            </div>
          </div>

          <Separator />

          <div className="grid grid-cols-2 gap-4">
            <div>
              <p className="text-sm text-muted-foreground">Trạng thái</p>
              {settings.active ? (
                <Badge
                  variant="outline"
                  className="bg-green-100 text-green-700"
                >
                  <CheckCircle2 className="h-4 w-4 mr-1" /> Hoạt động
                </Badge>
              ) : (
                <Badge variant="outline" className="bg-red-100 text-red-700">
                  <XCircle className="h-4 w-4 mr-1" /> Bị khóa
                </Badge>
              )}
            </div>

            <div className="flex items-center justify-between">
              <div>
                <p className="text-sm text-muted-foreground">
                  Xác thực hai lớp (MFA)
                </p>
                <p className="text-xs text-muted-foreground">
                  Tăng bảo mật khi đăng nhập
                </p>
              </div>
              <Switch
                checked={settings.mfaEnabled}
                onCheckedChange={handleToggleMfa}
                disabled={updating}
              />
            </div>
          </div>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>Tài khoản liên kết</CardTitle>
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
                <Avatar className="h-10 w-10 rounded-full">
                  <AvatarImage
                    src={
                        acc.avatarUrl || useImageLink("")
                    }
                  />
                  <AvatarFallback className="h-10 w-10">
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

              <Button
                variant={acc.isRevoke ? "outline" : "destructive"}
                size="sm"
                disabled={acc.isRevoke}
              >
                {acc.isRevoke ? (
                  <>
                    <Unlink className="w-4 h-4 mr-1" /> Đã hủy
                  </>
                ) : (
                  <>
                    <Link2 className="w-4 h-4 mr-1" /> Hủy liên kết
                  </>
                )}
              </Button>
            </div>
          ))}
        </CardContent>
        <CardFooter className="text-xs text-muted-foreground">
          Liên kết tài khoản giúp bạn đăng nhập nhanh bằng Google, Facebook hoặc
          GitHub.
        </CardFooter>
      </Card>
    </div>
  );
}
