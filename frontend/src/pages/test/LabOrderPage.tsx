import { useEffect, useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";
import { Loader2 } from "lucide-react";
import { cn } from "@/lib/utils";

import { useAuth } from "@/context/AuthContext";
import { useWebSocket } from "@/context/WebSocketContext";
import { useNavigate } from "react-router-dom";

import type { LabOrderResponse } from "@/api";
import { LabOrderApi } from "@/api/lab/LabOrderApi";

export default function TechnicianLabOrderPage() {
  const { user } = useAuth();
  const { status: wsStatus } = useWebSocket();

  const [orders, setOrders] = useState<LabOrderResponse[]>([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    async function init() {
      try {
        const res = await LabOrderApi.getOrdersForStaffToday();
        setOrders(res);
      } catch {
        toast.error("Không tìm thấy xét nghiệm cho phòng này.");
      }
      setLoading(false);
    }

    init();
  }, []);

  // ✅ Nhận event WebSocket cập nhật order realtime
  useEffect(() => {
    const handler = (order: LabOrderResponse) => {
      setOrders((prev) => {
        if (order.status === "COMPLETED" || order.status === "VERIFIED") {
          return prev.filter((o) => o.id !== order.id);
        }

        const exists = prev.some((o) => o.id === order.id);
        return exists
          ? prev.map((o) => (o.id === order.id ? order : o))
          : [...prev, order];
      });
    };

    window.addEventListener("lab-order", (e: any) => handler(e.detail));

    return () =>
      window.removeEventListener("lab-order", (e: any) =>
        handler(e.detail)
      );
  }, []);

  const updateStatus = async (order: LabOrderResponse, status: any) => {
    try {
      await LabOrderApi.update(order.id!, { status });

      setOrders((prev) =>
        prev.map((o) =>
          o.id === order.id ? { ...o, status } : o
        )
      );

      toast.success("Cập nhật trạng thái thành công");
    } catch {
      toast.error("Không thể cập nhật order");
    }
  };

  if (loading)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );

  return (
    <Card className="shadow-lg">
      <CardHeader>
        <CardTitle className="flex justify-between items-center">
          <Badge
            className={
              wsStatus === "connected"
                ? "bg-green-500"
                : wsStatus === "connecting"
                ? "bg-yellow-500"
                : "bg-red-500"
            }
          >
            {wsStatus === "connecting" && "Đang kết nối..."}
            {wsStatus === "connected" && "Đã kết nối"}
            {wsStatus === "error" && "Lỗi"}
            {wsStatus === "disconnected" && "Mất kết nối"}
          </Badge>

          <span>Phòng xét nghiệm: {user?.room?.name}</span>
          <Badge variant="secondary">Tầng {user?.room?.floorNumber}</Badge>
          <span>KTV: {user?.staff?.fullName}</span>
        </CardTitle>
      </CardHeader>

      <CardContent>
        <div className="grid gap-3">
          {orders.map((order) => (
            <div
              key={order.id}
              className={cn(
                "p-4 border rounded flex justify-between items-center",
                order.status === "PENDING" && "border-gray-300",
                order.status === "IN_PROGRESS" && "border-green-300",
                order.status === "COMPLETED" && "border-gray-200 opacity-70",
                order.status === "VERIFIED" && "border-purple-300"
              )}
            >
              <div className="space-y-1">
                <div className="font-bold text-lg">{order.orderCode}</div>
                <div className="text-sm text-muted-foreground">
                  {order.results?.length ?? 0} xét nghiệm
                </div>
                <Badge className="mt-1">{order.status}</Badge>
              </div>

              <div className="flex gap-2">
                {/* ✅ Nhận ca */}
                {order.status === "PENDING" && (
                  <Button onClick={() => updateStatus(order, "IN_PROGRESS")}>
                    Nhận ca
                  </Button>
                )}

                {/* ✅ Bắt đầu làm xét nghiệm */}
                {order.status === "IN_PROGRESS" && (
                  <>
                    <Button
                      variant="outline"
                      onClick={() =>
                        navigate(`/staff/lab/orders/${order.id}`)
                      }
                    >
                      Nhập kết quả
                    </Button>

                    <Button
                      variant="secondary"
                      onClick={() => updateStatus(order, "COMPLETED")}
                    >
                      Hoàn tất
                    </Button>
                  </>
                )}

                {/* ✅ Sau khi bác sĩ kiểm duyệt */}
                {order.status === "VERIFIED" && (
                  <Badge className="bg-purple-600 text-white">
                    Đã duyệt
                  </Badge>
                )}
              </div>
            </div>
          ))}

          {orders.length === 0 && (
            <div className="text-center py-6 text-muted-foreground">
              Không có xét nghiệm nào trong phòng này.
            </div>
          )}
        </div>
      </CardContent>
    </Card>
  );
}
