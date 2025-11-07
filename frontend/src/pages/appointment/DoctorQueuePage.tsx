import { useEffect, useState, useRef } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
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

export default function DoctorQueuePage() {
  const [queue, setQueue] = useState<QueueTicketResponse[]>([]);
  const [loading, setLoading] = useState(true);

  const { user } = useAuth();
  const navigate = useNavigate();

  const stompClient = useRef<Client | null>(null);

  // ✅ Load queue lần đầu
  useEffect(() => {
    async function init() {
      try {
        const res = await QueueApi.getQueuesForDoctorToday();
        setQueue(res);
        connectWebSocket(user?.staff?.id || "");
      } catch (e) {
        toast.error("Không tìm thấy phòng trực hoặc không có lịch làm việc.");
      }
      setLoading(false);
    }
    init();
  }, []);

  // ✅ WebSocket
  const connectWebSocket = (staffId: string) => {
    const socket = new SockJS("/ws");
    const client = new Client({
      webSocketFactory: () => socket as any,
      reconnectDelay: 5000,
      debug: () => {},
      onConnect: () => {
        client.subscribe(`/topic/doctor/${staffId}/queue`, (message) => {
          const updated = JSON.parse(message.body);
          setQueue(updated);
        });
      },
    });
    stompClient.current = client;
    client.activate();
  };

  const goToExamination = (ticket: QueueTicketResponse) => {
    if (ticket.examinationId) {
      navigate(`/staff/examinations/${ticket.examinationId}`);
    } else {
      toast.error("Không tìm thấy examination. Backend chưa generate?");
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
    } catch (e) {
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
          <span>Phòng khám: {user?.room?.name}</span>
          <Badge variant="secondary">
            Tầng {user?.room?.floorNumber}
          </Badge>
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
                item.status === "CALLED" && " border-blue-300",
                item.status === "IN_SERVICE" && " border-green-300"
              )}
            >
              <div>
                <div className="font-bold text-lg">{item.queueNumber}</div>
                <div>{item.appointmentPatient?.fullName}</div>
                <div>Ngày sinh: {item.appointmentPatient?.dateOfBirth}</div>
                <div>Giới tính: {item.appointmentPatient?.gender}</div>
                <Badge className="mt-1">{item.status}</Badge>
              </div>

              <div className="flex gap-2">
                {item.status === "WAITING" && (
                  <>
                    <Button onClick={() => handleAction(item, "call")}>
                      Gọi
                    </Button>
                    <Button
                      variant="destructive"
                      onClick={() => handleAction(item, "skip")}
                    >
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
                  <Button
                    variant="secondary"
                    onClick={() => goToExamination(item)}
                  >
                    Tiếp tục khám
                  </Button>
                )}

                {item.status === "IN_SERVICE" && (
                  <Button
                    onClick={() => handleAction(item, "done")}
                    variant="outline"
                  >
                    Hoàn tất
                  </Button>
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
