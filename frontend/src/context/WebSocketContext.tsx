
import React, { createContext, useContext, useEffect, useRef, useState } from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { useAuth } from "@/context/AuthContext";
import { toast } from "sonner";

interface WsContextValue {
  status: "connecting" | "connected" | "error" | "disconnected";
}

const WebSocketContext = createContext<WsContextValue>({ status: "connecting" });

export const useWebSocket = () => useContext(WebSocketContext);

export const WebSocketProvider = ({ children }: { children: React.ReactNode }) => {
  const { user } = useAuth();

  const [status, setStatus] = useState<WsContextValue["status"]>("connecting");
  const stompClient = useRef<Client | null>(null);

  // Local stores để detect "new vs update"
  const doctorQueueRef = useRef<Record<string, boolean>>({});
  const technicianOrdersRef = useRef<Record<string, boolean>>({});
  const labOrdersRef = useRef<Record<string, boolean>>({});

  useEffect(() => {
    if (!user?.staff?.id) return;
    connect();
    return () => {
      stompClient.current?.deactivate();
    };
  }, [user?.staff?.id]);

  const connect = () => {
    const socket = new SockJS(import.meta.env.VITE_API_URL + "/ws");

    const client = new Client({
      webSocketFactory: () => socket as any,
      reconnectDelay: 5000,

      debug: (msg) => console.log("%c[STOMP DEBUG]", "color:#6fa8dc", msg),

      onConnect: () => {
        setStatus("connected");
        console.log("[WS] Connected ✅");

        subscribeDoctorQueue(client);
        subscribeTechnicianOrders(client);
        subscribeLabOrders(client);
      },

      onWebSocketClose: (e) => {
        setStatus("disconnected");
        console.warn("[WS] Closed ❌", e);
      },

      onWebSocketError: (e) => {
        setStatus("error");
        console.error("[WS] ERROR ❗", e);
      },

      onStompError: (frame) => {
        setStatus("error");
        console.error("[STOMP ERROR]", frame);
      },
    });

    stompClient.current = client;
    client.activate();
  };

  /* ===========================================================
      1. DOCTOR LISTENS — /topic/doctor/{id}/queue
  ============================================================ */
  const subscribeDoctorQueue = (client: Client) => {
    const doctorId = user?.staff?.id;
    if (!doctorId) return;

    const destination = `/topic/doctor/${doctorId}/queue`;
    console.log("[WS] Doctor subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const queueItem = JSON.parse(msg.body);

      const id = queueItem.id;
      const seen = doctorQueueRef.current[id];

      // Toast ONLY for new queue item
      if (!seen) {
        doctorQueueRef.current[id] = true;
        toast.success(`Có bệnh nhân mới trong hàng chờ!`);
      }

      console.log("[WS] Doctor queue update:", queueItem);
    });
  };

  /* ===========================================================
      2. TECHNICIAN LISTENS — /topic/technician/{staffId}/orders
  ============================================================ */
  const subscribeTechnicianOrders = (client: Client) => {
    const staffId = user?.staff?.id;
    if (!staffId) return;

    const destination = `/topic/technician/${staffId}/orders`;
    console.log("[WS] Technician subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const order = JSON.parse(msg.body);

      const id = order.id;
      const seen = technicianOrdersRef.current[id];

      if (!seen) {
        technicianOrdersRef.current[id] = true;
        toast(`Có order mới: ${order.orderCode}`);
      }

      console.log("[WS] Technician order update:", order);
    });
  };

  /* ===========================================================
      3. LAB STAFF LISTENS — /topic/lab/{staffId}/orders
  ============================================================ */
  const subscribeLabOrders = (client: Client) => {
    const staffId = user?.staff?.id;
    if (!staffId) return;

    const destination = `/topic/lab/${staffId}/orders`;
    console.log("[WS] Lab subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const order = JSON.parse(msg.body);

      const id = order.id;
      const seen = labOrdersRef.current[id];

      if (!seen) {
        labOrdersRef.current[id] = true;
        toast.info(`Có order xét nghiệm mới!`);
      }

      console.log("[WS] Lab order update:", order);
    });
  };

  return (
    <WebSocketContext.Provider value={{ status }}>
      {children}
    </WebSocketContext.Provider>
  );
};