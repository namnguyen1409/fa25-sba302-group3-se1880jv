import { useEffect, useState } from "react";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Loader2 } from "lucide-react";
import { toast } from "sonner";
import { cn } from "@/lib/utils";

import { useAuth } from "@/context/AuthContext";
import { useWebSocket } from "@/context/WebSocketContext";
import { useNavigate, useParams } from "react-router-dom";


import type { InvoiceResponse } from "@/api";
import { InvoiceApi } from "@/api/billing/InvoiceApi";

export default function BillOrderPage() {
  const { user } = useAuth();
  const { status: wsStatus } = useWebSocket();
  const navigate = useNavigate();
  const { id: currentInvoiceId } = useParams();

  const [bills, setBills] = useState<InvoiceResponse[]>([]);
  const [loading, setLoading] = useState(true);

  // Initial load
  useEffect(() => {
    async function load() {
      try {
        const res = await InvoiceApi.getInvoicesForStaffToday();
        setBills(res);
      } catch {
        toast.error("Không thể tải danh sách hóa đơn.");
      }
      setLoading(false);
    }
    load();
  }, []);

  // ✅ Listen realtime bills (window event dispatched from WebSocketProvider)
  useEffect(() => {
    const handler = (e: any) => {
      const updated = e.detail as InvoiceResponse;

      setBills((prev) => {
        const exists = prev.some((b) => b.id === updated.id);

        // Nếu đã thanh toán thì remove khỏi danh sách chờ
        if (updated.paid) {
          return prev.filter((b) => b.id !== updated.id);
        }

        return exists
          ? prev.map((b) => (b.id === updated.id ? updated : b))
          : [...prev, updated];
      });
    };

    window.addEventListener("cashier-invoice", handler);
    return () => window.removeEventListener("cashier-invoice", handler);
  }, []);

  // ✅ Update invoice status
  const markPaid = async (invoice: InvoiceResponse) => {
    try {
      await InvoiceApi.update(invoice.id!, { paid: true });
      toast.success("Thanh toán thành công");

      setBills((prev) => prev.filter((b) => b.id !== invoice.id));
    } catch {
      toast.error("Lỗi khi thanh toán hóa đơn.");
    }
  };

  if (loading)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin w-10 h-10" />
      </div>
    );

  return (
    <Card className="p-4 max-h-[85vh] overflow-y-auto shadow-lg rounded-lg">
      {/* Header */}
      <div className="flex justify-between items-center mb-4">
        <Badge
          className={
            wsStatus === "connected"
              ? "bg-green-500"
              : wsStatus === "connecting"
              ? "bg-yellow-500"
              : "bg-red-500"
          }
        >
          {wsStatus === "connected" && "Realtime OK"}
          {wsStatus === "connecting" && "Đang kết nối..."}
          {wsStatus === "error" && "Lỗi realtime"}
          {wsStatus === "disconnected" && "Mất kết nối"}
        </Badge>

        <div className="text-right">
          <div className="font-semibold">
            {user?.room?.name || "Quầy thu ngân"}
          </div>
          <Badge variant="secondary">Tầng {user?.room?.floorNumber}</Badge>
          <div className="font-medium mt-1">
            Thu ngân: {user?.staff?.fullName}
          </div>
        </div>
      </div>

      {/* Empty State */}
      {bills.length === 0 && (
        <div className="text-center py-8 text-muted-foreground">
          Không có hóa đơn cần thanh toán.
        </div>
      )}

      {/* Bill list */}
      <div className="space-y-3">
        {bills.map((bill) => (
          <div
            key={bill.id}
            className={cn(
              "p-4 border rounded-md flex justify-between items-center transition-all cursor-pointer",

              // ✅ highlight if current opened bill
              bill.id === currentInvoiceId &&
                "border-blue-600 bg-blue-50 shadow-sm scale-[1.02]",

              // ✅ states
              bill.paid && "opacity-60 border-gray-300",
              !bill.paid && "border-amber-400"
            )}
          >
            {/* Info */}
            <div>
              <div className="font-bold text-lg">{bill.invoiceNumber}</div>
              <div className="text-sm text-muted-foreground">
                {bill.patient?.fullName}
              </div>
              <div className="text-sm font-medium">
                Tổng tiền: {bill.totalAmount?.toLocaleString()} đ
              </div>

              <Badge className="mt-1" variant={bill.paid ? "secondary" : "default"}>
                {bill.paid ? "Đã thanh toán" : "Chờ thanh toán"}
              </Badge>
            </div>

            {/* Actions */}
            <div className="flex gap-2">
              <Button
                size="sm"
                variant="outline"
                onClick={() => navigate(`/staff/billing/${bill.id}`)}
              >
                Xem chi tiết
              </Button>

              {!bill.paid && (
                <Button
                  size="sm"
                  variant="secondary"
                  onClick={() => markPaid(bill)}
                >
                  Thanh toán
                </Button>
              )}
            </div>
          </div>
        ))}
      </div>
    </Card>
  );
}
