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

export default function DoctorQueuePage() {
  const [queue, setQueue] = useState<QueueTicketResponse[]>([]);
  const [loading, setLoading] = useState(true);

  const {user} = useAuth();
 
  const stompClient = useRef<Client | null>(null);



  useEffect(() => {
    async function init() {
      try {
        const queueRes = await QueueApi.getQueuesForDoctorToday();
        setQueue(queueRes);
        connectWebSocket(user?.staff?.id || "");
      } catch (e) {
        toast.error("Không tìm thấy phòng trực");
      }
      setLoading(false);
    }
    init();
  }, []);


  const connectWebSocket = (staffId: string) => {
    const socket = new SockJS("/ws");
    const client = new Client({
      webSocketFactory: () => socket as any,
      reconnectDelay: 5000,
      debug: () => {},
      onConnect: () => {
        client.subscribe(`/topic/doctor/${staffId}/queue`, (message) => {
          const body = JSON.parse(message.body);
          setQueue(body);
        });
      }
    });
    stompClient.current = client;
    client.activate();
  };

  const updateStatus = (ticketId: string, status: string) => {
    stompClient.current?.publish({
      destination: "/app/queue/update-status",
      body: JSON.stringify({
        ticketId,
        newStatus: status
      })
    });
  };

  if (loading) return (
    <div className="flex justify-center py-20">
      <Loader2 className="animate-spin h-10 w-10" />
    </div>
  );

  return (
    <Card className="shadow-lg">
      <CardHeader>
        <CardTitle className="flex justify-between items-center">
          <span>Phòng khám: {user?.room?.name}</span>
          <Badge variant="secondary">Floor {user?.room?.floorNumber}</Badge>
        </CardTitle>
      </CardHeader>

      <CardContent>
        <div className="grid gap-3">
          {queue.map((item: QueueTicketResponse) => (
            <div
              key={item.id}
              className={cn(
                "p-4 border rounded-md flex justify-between items-center",
                item.status === "CALLED" && "bg-blue-50 border-blue-300",
                item.status === "IN_SERVICE" && "bg-green-50 border-green-300",
              )}
            >
              <div>
                <div className="font-bold text-lg">{item.queueNumber}</div>
                <div>{item.appointmentResponse?.patient?.fullName}</div>
                <Badge className="mt-1">{item.status}</Badge>
              </div>

              <div className="flex gap-2">
                {item.status === "WAITING" && (
                  <Button onClick={() => updateStatus(item.id!, "CALLED")}>
                    Gọi
                  </Button>
                )}

                {item.status === "CALLED" && (
                  <Button onClick={() => updateStatus(item.id!, "IN_SERVICE")}>
                    Bắt đầu khám
                  </Button>
                )}

                {item.status === "IN_SERVICE" && (
                  <Button
                    variant="secondary"
                    onClick={() => updateStatus(item.id, "DONE")}
                  >
                    Hoàn tất
                  </Button>
                )}

                {item.status === "WAITING" && (
                  <Button
                    variant="destructive"
                    onClick={() => updateStatus(item.id, "SKIPPED")}
                  >
                    Bỏ qua
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
