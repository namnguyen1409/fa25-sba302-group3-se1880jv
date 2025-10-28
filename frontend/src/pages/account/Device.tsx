"use client";

import { use, useEffect, useState } from "react";
import { toast } from "sonner";
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Skeleton } from "@/components/ui/skeleton";
import { Badge } from "@/components/ui/badge";
import { Laptop, Smartphone, ShieldCheck, Trash } from "lucide-react";
import { AccountApi } from "@/api/user/accountApi";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/context/AuthContext";
import type { DeviceSessionResponse } from "@/types/account";


export default function AccountDevicesPage() {
  const [devices, setDevices] = useState<DeviceSessionResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const [revoking, setRevoking] = useState<string | null>(null);
  const navigator = useNavigate();
  const {logout} = useAuth();

  const fetchDevices = async () => {
    try {
      const res = await AccountApi.getDevices();
      setDevices(res || []);
    } catch {
      toast.error("Không thể tải danh sách thiết bị");
    } finally {
      setLoading(false);
    }
  };

  const handleRevoke = async (deviceId: string) => {
    try {
      setRevoking(deviceId);
      await AccountApi.revokeDevice(deviceId);
      toast.success("Đã đăng xuất thiết bị này");
      fetchDevices();
    } catch {
      toast.error("Không thể thu hồi phiên đăng nhập");
    } finally {
      setRevoking(null);
    }
  };

  const handleRevokeAll = async () => {
    try {
      await AccountApi.revokeAllDevices();
      toast.success("Đã đăng xuất khỏi tất cả thiết bị");
      logout();
    } catch {
      toast.error("Không thể thu hồi tất cả thiết bị");
    }
  };

  useEffect(() => {
    fetchDevices();
  }, []);

  if (loading)
    return (
      <div className="p-6 space-y-4 max-w-4xl mx-auto">
        <Skeleton className="h-10 w-1/3" />
        <Skeleton className="h-[250px] w-full" />
      </div>
    );

  return (
    <div className="p-6 max-w-4xl mx-auto space-y-6">
      <Card>
        <CardHeader className="flex justify-between items-center">
          <CardTitle>Thiết bị đã đăng nhập</CardTitle>
          <Button variant="destructive" onClick={handleRevokeAll}>
            Đăng xuất tất cả
          </Button>
        </CardHeader>

        <CardContent className="space-y-4">
          {devices.length === 0 ? (
            <p className="text-sm text-muted-foreground">
              Không có thiết bị nào đang hoạt động.
            </p>
          ) : (
            devices.map((device) => (
              <div
                key={device.id}
                className="flex justify-between items-start border p-4 rounded-lg bg-muted/30 hover:bg-muted/50 transition"
              >
                <div className="flex items-start gap-3">
                  {device.userAgent?.toLowerCase().includes("mobile") ? (
                    <Smartphone className="text-primary w-5 h-5 mt-1" />
                  ) : (
                    <Laptop className="text-primary w-5 h-5 mt-1" />
                  )}

                  <div>
                    <p className="font-medium">{device.deviceName || "Thiết bị không xác định"}</p>
                    <p className="text-xs text-muted-foreground">
                      IP: {device.ipAddress || "—"} <br />
                      Agent: {device.userAgent?.slice(0, 50)}...
                    </p>

                    <div className="flex flex-wrap gap-2 mt-2">
                      {device.trusted && (
                        <Badge variant="outline">
                          <ShieldCheck className="w-3 h-3 mr-1" /> Tin cậy
                        </Badge>
                      )}
                      {device.revoked && (
                        <Badge variant="destructive">Đã thu hồi</Badge>
                      )}
                      {device.rememberMe && (
                        <Badge variant="secondary">Nhớ đăng nhập</Badge>
                      )}
                    </div>

                    <p className="text-xs text-muted-foreground mt-2">
                      Lần đăng nhập gần nhất:{" "}
                      {new Date(device.lastLoginAt).toLocaleString()} <br />
                      Hết hạn: {new Date(device.expiresIn).toLocaleString()}
                    </p>
                  </div>
                </div>

                <div>
                  <Button
                    variant="destructive"
                    size="sm"
                    disabled={revoking === device.id}
                    onClick={() => handleRevoke(device.id)}
                  >
                    <Trash className="w-4 h-4 mr-1" />{" "}
                    {revoking === device.id ? "Đang xử lý..." : "Đăng xuất"}
                  </Button>
                </div>
              </div>
            ))
          )}
        </CardContent>
      </Card>
    </div>
  );
}
