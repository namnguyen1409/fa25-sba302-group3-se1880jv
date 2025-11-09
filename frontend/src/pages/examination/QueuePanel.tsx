// src/components/queue/QueuePanel.tsx
import { useEffect, useState } from "react";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Loader2 } from "lucide-react";
import { toast } from "sonner";
import { cn } from "@/lib/utils";

import { QueueApi } from "@/api/queue/queueApi";
import type { QueueTicketResponse } from "@/api";
import { useNavigate } from "react-router-dom";
import { useWebSocket } from "@/context/WebSocketContext";
import { useAuth } from "@/context/AuthContext";

export function QueuePanel({ currentExamId }: { currentExamId?: string }) {
  const [queue, setQueue] = useState<QueueTicketResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const { user } = useAuth();
  const { status: wsStatus } = useWebSocket();
  const navigate = useNavigate();

  // Initial load
  useEffect(() => {
    async function init() {
      try {
        const res = await QueueApi.getQueuesForDoctorToday();
        setQueue(res);
      } catch {
        toast.error("Không tìm thấy phòng trực hoặc không có lịch làm việc.");
      }
      setLoading(false);
    }
    init();
  }, []);

  // Realtime listener
  useEffect(() => {
    const handler = (event: any) => {
      const updated = event.detail as QueueTicketResponse;

      setQueue((prev) => {
        const exists = prev.some((q) => q.id === updated.id);

        // DONE + SKIPPED out of list
        if (updated.status === "DONE" || updated.status === "SKIPPED") {
          return prev.filter((q) => q.id !== updated.id);
        }

        return exists
          ? prev.map((q) => (q.id === updated.id ? updated : q))
          : [...prev, updated];
      });
    };
    window.addEventListener("doctor-queue", handler);
    return () => window.removeEventListener("doctor-queue", handler);
  }, []);

  // Actions
  const handleAction = async (ticket: QueueTicketResponse, action: string) => {
    try {
      if (action === "call") {
        await QueueApi.callQueueTicket(ticket.id!);
        toast.success("Đã gọi bệnh nhân");
      }

      if (action === "start") {
        const res = await QueueApi.startQueueTicket(ticket.id!);
        toast.success("Bắt đầu khám");
        navigate(`/staff/examinations/${res.examinationId}`);
      }

      if (action === "done") {
        await QueueApi.doneQueueTicket(ticket.id!);
        toast.success("Hoàn tất khám");
      }

      if (action === "skip") {
        await QueueApi.skipQueueTicket(ticket.id!);
        toast.success("Đã bỏ qua bệnh nhân");
      }

      if (action === "requeue") {
        await QueueApi.requeueQueueTicket(ticket.id!);
        toast.success("Đã đưa bệnh nhân quay lại hàng chờ");
      }
    } catch {
      toast.error("Lỗi thao tác queue");
    }
  };

  if (loading)
    return (
      <div className="flex justify-center py-10">
        <Loader2 className="animate-spin h-8 w-8" />
      </div>
    );

  return (
    <Card className="p-4 max-h-[80vh] overflow-y-auto rounded-lg shadow-sm">
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
          <div className="font-semibold">{user?.room?.name}</div>
          <Badge variant="secondary">Tầng {user?.room?.floorNumber}</Badge>
        </div>
      </div>

      {/* Empty */}
      {queue.length === 0 && (
        <div className="text-center py-6 text-muted-foreground">
          Không có bệnh nhân trong hàng đợi.
        </div>
      )}

      {/* Queue list */}
      <div className="space-y-3">
        {queue.map((item) => (
          <div
            key={item.id}
            className={cn(
              "p-3 border rounded-md cursor-pointer transition-all",

              item.examinationId === currentExamId &&
                "border-blue-600 bg-blue-500 shadow-md scale-[1.02]",

              item.status === "CALLED" && "border-blue-300",
              item.status === "IN_SERVICE" && "border-green-300"
            )}
          >
            <div className="font-bold text-lg">{item.queueNumber}</div>
            <div>{item.appointmentPatient?.fullName}</div>
            <Badge className="mt-1">{item.status}</Badge>

            <div className="flex flex-wrap gap-2 mt-2">
              {/* WAITING */}
              {item.status === "WAITING" && (
                <>
                  <Button size="sm" onClick={() => handleAction(item, "call")}>
                    Gọi
                  </Button>
                  <Button
                    size="sm"
                    variant="destructive"
                    onClick={() => handleAction(item, "skip")}
                  >
                    Bỏ qua
                  </Button>
                </>
              )}

              {/* CALLED */}
              {item.status === "CALLED" && (
                <Button size="sm" onClick={() => handleAction(item, "start")}>
                  Bắt đầu
                </Button>
              )}

              {/* IN_SERVICE */}
              {item.status === "IN_SERVICE" && (
                <>
                  {item.examinationId === currentExamId ? null : (
                    <Button
                      size="sm"
                      variant="secondary"
                      onClick={() =>
                        navigate(`/staff/examinations/${item.examinationId}`)
                      }
                    >
                      Tiếp tục
                    </Button>
                  )}

                  <Button
                    size="sm"
                    variant="outline"
                    onClick={() => handleAction(item, "done")}
                  >
                    Hoàn tất
                  </Button>
                </>
              )}

              {/* ✅ IN_SERVICE_WAITING_RESULT */}
              {item.status === "IN_SERVICE_WAITING_RESULT" && (
                <div className="text-sm text-muted-foreground">
                  Đang chờ kết quả xét nghiệm...
                </div>
              )}

              {/* ✅ WAITING_AFTER_RESULT */}
              {item.status === "WAITING_AFTER_RESULT" && (
                <Button
                  size="sm"
                  variant="secondary"
                  onClick={() => {
                    handleAction(item, "start");
                  }}
                >
                  Tiếp tục khám
                </Button>
              )}

              {/* SKIPPED */}
              {item.status === "SKIPPED" && (
                <Button
                  size="sm"
                  variant="secondary"
                  onClick={() => handleAction(item, "requeue")}
                >
                  Quay lại hàng chờ
                </Button>
              )}
            </div>
          </div>
        ))}
      </div>
    </Card>
  );
}
