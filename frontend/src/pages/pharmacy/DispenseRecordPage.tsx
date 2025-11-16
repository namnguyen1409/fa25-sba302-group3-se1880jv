"use client";

import { useEffect, useState } from "react";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Loader2, Eye } from "lucide-react";
import { toast } from "sonner";
import { cn } from "@/lib/utils";

import { useAuth } from "@/context/AuthContext";
import { useWebSocket } from "@/context/WebSocketContext";

import type { DispenseRecordResponse } from "@/api/models/DispenseRecordResponse";
import { PharmacyApi } from "@/api/pharmacy/PharmacyApi";
import { useNavigate } from "react-router-dom";

export default function DispenseRecordPage() {
  const { user } = useAuth();
  const { status: wsStatus } = useWebSocket();

  const [records, setRecords] = useState<DispenseRecordResponse[]>([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    async function load() {
      try {
        const res = await PharmacyApi.getDispenseRecordsToday();
        setRecords(res);
      } catch {
        toast.error("Không thể tải danh sách phát thuốc.");
      }
      setLoading(false);
    }
    load();
  }, []);

  const markDispensed = async (record: DispenseRecordResponse) => {
    try {
      await PharmacyApi.markAsDispensed(record.id!);
      toast.success("Đã đánh dấu phát thuốc xong.");
    } catch {
      toast.error("Không thể cập nhật trạng thái phát thuốc.");
    }
  };

  useEffect(() => {
    const handler = (e: any) => {
      const updated = e.detail as DispenseRecordResponse;

      setRecords((prev) => {
        const exists = prev.some((x) => x.id === updated.id);
        if (updated.status === "DISPENSED") {
          return prev.filter((x) => x.id !== updated.id);
        }

        return exists
          ? prev.map((x) => (x.id === updated.id ? updated : x))
          : [...prev, updated];
      });
    };

    window.addEventListener("pharmacy-dispense", handler);
    return () => window.removeEventListener("pharmacy-dispense", handler);
  }, []);

  if (loading)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin w-10 h-10" />
      </div>
    );

  return (
    <Card className="p-4 max-h-[85vh] overflow-y-auto shadow-lg rounded-lg">

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
            {user?.room?.name || "Quầy phát thuốc"}
          </div>
          <Badge variant="secondary">Tầng {user?.room?.floorNumber}</Badge>
          <div className="font-medium mt-1">
            Nhân viên: {user?.staff?.fullName}
          </div>
        </div>
      </div>

      {records.length === 0 && (
        <div className="text-center py-8 text-muted-foreground">
          Không có phiếu phát thuốc nào đang chờ.
        </div>
      )}

      <div className="space-y-3">
        {records.map((rec) => (
          <div
            key={rec.id}
            className={cn(
              "p-4 border rounded-md flex justify-between items-center transition-all cursor-pointer",
              rec.status === "DISPENSED" && "opacity-60",
              rec.status !== "DISPENSED" && "border-blue-400"
            )}
          >
            <div>
              <div className="font-bold text-lg">
                {rec.patient?.patientCode}
              </div>
              <div className="text-sm text-muted-foreground">
                {rec.patient?.fullName}
              </div>

              <div className="text-sm font-medium">
                Tổng tiền: {rec.totalCost?.toLocaleString()} đ
              </div>

              <Badge className="mt-1" variant="secondary">
                {rec.status}
              </Badge>
            </div>
            <div className="flex gap-2">
              <Button
                size="sm"
                variant="outline"
                onClick={() => navigate(`/staff/dispense/${rec.id}`)}
              >
                <Eye className="w-4 h-4" /> Xem
              </Button>
              {rec.status !== "DISPENSED" && (
                <Button
                  size="sm"
                  variant="default"
                  onClick={() => markDispensed(rec)}
                >
                  Đã phát xong
                </Button>
              )}
            </div>
          </div>
        ))}
      </div>
    </Card>
  );
}
