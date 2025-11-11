import { useEffect, useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";
import { Loader2 } from "lucide-react";
import { cn } from "@/lib/utils";

import { useAuth } from "@/context/AuthContext";
import type { ServiceOrderResponse } from "@/api";
import { ServiceOrderApi } from "@/api/service/ServiceOrderApi";
import { useWebSocket } from "@/context/WebSocketContext";
import { useNavigate } from "react-router-dom";

export default function TechnicianOrderPage() {
  const { user } = useAuth();
  const { status: wsStatus } = useWebSocket();

  const [orders, setOrders] = useState<ServiceOrderResponse[]>([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    async function init() {
      try {
        const res = await ServiceOrderApi.getOrdersForStaffToday();
        setOrders(res);
      } catch {
        toast.error("Không tìm thấy order cho phòng này.");
      }
      setLoading(false);
    }

    init();
  }, []);

  /** WebSocketProvider sẽ gọi hàm này mỗi khi có order mới/update */
  useEffect(() => {
    const handler = (order: ServiceOrderResponse) => {
      setOrders((prev) => {
        // Remove completed
        if (order.status === "COMPLETED") {
          return prev.filter((o) => o.id !== order.id);
        }

        const exists = prev.some((o) => o.id === order.id);
        return exists
          ? prev.map((o) => (o.id === order.id ? order : o))
          : [...prev, order];
      });
    };

    // register global listener
    window.addEventListener("technician-order", (e: any) => handler(e.detail));

    return () =>
      window.removeEventListener("technician-order", (e: any) =>
        handler(e.detail)
      );
  }, []);


  const updateStatus = async (order: ServiceOrderResponse, status: any) => {
  try {
    await ServiceOrderApi.update(order.id!, { status });

    setOrders(prev =>
      prev.map(o =>
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

          <span>Phòng: {user?.room?.name}</span>
          <Badge variant="secondary">Tầng {user?.room?.floorNumber}</Badge>
          <span>Kỹ thuật viên: {user?.staff?.fullName}</span>
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
                order.status === "READY" && "border-blue-300",
                order.status === "IN_PROGRESS" && "border-green-300",
                order.status === "COMPLETED" && "border-gray-200 opacity-70"
              )}
            >
              <div className="space-y-1">
                <div className="font-bold text-lg">{order.orderCode}</div>
                <div className="text-sm text-muted-foreground">
                  {order.items?.length ?? 0} dịch vụ
                </div>
                <Badge className="mt-1">{order.status}</Badge>
              </div>

              <div className="flex gap-2">
                {order.status === "PENDING" && (
                  <Button onClick={() => updateStatus(order, "READY")}>
                    Nhận ca
                  </Button>
                )}

                {order.status === "READY" && (
                  <Button onClick={() =>{
                     updateStatus(order, "IN_PROGRESS")
                     navigate(`/staff/service/orders/${order.id}`)
                     }}>
                    Bắt đầu
                  </Button>
                )}

                {order.status === "IN_PROGRESS" && (
                    <>
                    <Button variant="outline" onClick={() => navigate(`/staff/service/orders/${order.id}`)}>
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
              </div>
            </div>
          ))}

          {orders.length === 0 && (
            <div className="text-center py-6 text-muted-foreground">
              Không có order nào trong phòng này.
            </div>
          )}
        </div>
      </CardContent>
    </Card>
  );
}
