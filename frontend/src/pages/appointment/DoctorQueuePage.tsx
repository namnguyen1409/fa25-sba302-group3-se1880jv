import { useEffect, useState } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";
import { Loader2 } from "lucide-react";
import { cn } from "@/lib/utils";

import { QueueApi } from "@/api/queue/queueApi";
import type { QueueTicketResponse } from "@/api";
import { useAuth } from "@/context/AuthContext";
import { useNavigate } from "react-router-dom";
import { useWebSocket } from "@/context/WebSocketContext";

export default function DoctorQueuePage() {
  const [queue, setQueue] = useState<QueueTicketResponse[]>([]);
  const [loading, setLoading] = useState(true);
  const { user } = useAuth();
  const navigate = useNavigate();
  const { status: wsStatus } = useWebSocket();

  useEffect(() => {
    async function init() {
      try {
        const res = await QueueApi.getQueuesForDoctorToday();
        setQueue(res);
      } catch (e) {
        toast.error("Không tìm thấy phòng trực hoặc không có lịch làm việc.");
      }
      setLoading(false);
    }
    init();
  }, []);

  useEffect(() => {
    const handler = (event: any) => {
      const updated = event.detail as QueueTicketResponse;

      setQueue((prev) => {
        const exists = prev.some((q) => q.id === updated.id);

        // Xóa nếu queue done
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

  const goToExamination = (ticket: QueueTicketResponse) => {
    if (ticket.examinationId) {
      navigate(`/staff/examinations/${ticket.examinationId}`);
    } else {
      toast.error("Không tìm thấy examination.");
    }
  };

  const handleAction = async (ticket: QueueTicketResponse, action: string) => {
    try {
      if (action === "call") {
        await QueueApi.callQueueTicket(ticket.id!);
        toast.success("Đã gọi bệnh nhân");
      }
      if (action === "start") {
        const res = await QueueApi.startQueueTicket(ticket.id!);
        toast.success("Bắt đầu khám");
        goToExamination(res);
      }
      if (action === "done") {
        await QueueApi.doneQueueTicket(ticket.id!);
        toast.success("Hoàn tất khám");
      }
      if (action === "skip") {
        await QueueApi.skipQueueTicket(ticket.id!);
        toast.success("Đã bỏ qua bệnh nhân");
      }
    } catch {
      toast.error("Lỗi thao tác queue");
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
            {wsStatus === "connected" && "Realtime OK"}
            {wsStatus === "connecting" && "Đang kết nối..."}
            {wsStatus === "error" && "Lỗi realtime"}
            {wsStatus === "disconnected" && "Mất kết nối"}
          </Badge>

          <span>Phòng khám: {user?.room?.name}</span>
          <Badge variant="secondary">Tầng {user?.room?.floorNumber}</Badge>
          <span>Bác sĩ: {user?.staff?.fullName}</span>
        </CardTitle>
      </CardHeader>

      <CardContent>
        <div className="grid gap-3">
          {queue.map((item) => (
            <div
              key={item.id}
              className={cn(
                "p-4 border rounded-md flex justify-between items-center",
                item.status === "CALLED" && "border-blue-300",
                item.status === "IN_SERVICE" && "border-green-300"
              )}
            >
              <div>
                <div className="font-bold text-lg">{item.queueNumber}</div>
                <div>{item.appointmentPatient?.fullName}</div>

                <Badge className="mt-1">{item.status}</Badge>
              </div>

              <div className="flex gap-2">
                {item.status === "WAITING" && (
                  <>
                    <Button onClick={() => handleAction(item, "call")}>
                      Gọi
                    </Button>
                    <Button variant="destructive" onClick={() => handleAction(item, "skip")}>
                      Bỏ qua
                    </Button>
                  </>
                )}

                {item.status === "CALLED" && (
                  <Button onClick={() => handleAction(item, "start")}>
                    Bắt đầu khám
                  </Button>
                )}

                {item.status === "IN_SERVICE" && (
                  <>
                    <Button variant="secondary" onClick={() => goToExamination(item)}>
                      Tiếp tục khám
                    </Button>
                    <Button variant="outline" onClick={() => handleAction(item, "done")}>
                      Hoàn tất
                    </Button>
                  </>
                )}
              </div>
            </div>
          ))}

          {queue.length === 0 && (
            <div className="text-center py-6 text-muted-foreground">
              Không có bệnh nhân trong hàng đợi.
            </div>
          )}
        </div>
      </CardContent>
    </Card>
  );
}
