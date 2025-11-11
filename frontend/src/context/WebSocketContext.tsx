import React, {
  createContext,
  useContext,
  useEffect,
  useRef,
  useState,
} from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { useAuth } from "@/context/AuthContext";
import { toast } from "sonner";

interface WsContextValue {
  status: "connecting" | "connected" | "error" | "disconnected";
}

const WebSocketContext = createContext<WsContextValue>({
  status: "connecting",
});
export const useWebSocket = () => useContext(WebSocketContext);

export const WebSocketProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const { user } = useAuth();

  const [status, setStatus] = useState<WsContextValue["status"]>("connecting");
  const stompClient = useRef<Client | null>(null);

  // Local seen cache
  const seenDoctorQueueRef = useRef<Record<string, boolean>>({});
  const seenTechOrdersRef = useRef<Record<string, boolean>>({});
  const seenLabOrdersRef = useRef<Record<string, boolean>>({});
  const seenInvoiceOrdersRef = useRef<Record<string, boolean>>({});
  const seenDispenseOrdersRef = useRef<Record<string, boolean>>({});

  useEffect(() => {
    if (!user?.staff?.id) return;

    connect();

    return () => {
      void stompClient.current?.deactivate();
    };
  }, [user?.staff?.id]);

  const connect = () => {
    const socket = new SockJS(import.meta.env.VITE_API_URL + "/ws");

    const client = new Client({
      webSocketFactory: () => socket,
      reconnectDelay: 4000,

      debug: (msg) => console.log("%c[STOMP]", "color:#6fa8dc", msg),

      onConnect: () => {
        setStatus("connected");

        // Reset local seen cache khi kết nối lại
        seenDoctorQueueRef.current = {};
        seenTechOrdersRef.current = {};
        seenLabOrdersRef.current = {};
        seenInvoiceOrdersRef.current = {};
        seenDispenseOrdersRef.current = {};
        

        subscribeDoctorQueue(client);
        subscribeTechnicianOrders(client);
        subscribeLabOrders(client);
        subcribeDispenseOrders(client);
        subscribeInvoiceOrders(client);
      },

      onWebSocketClose: () => {
        console.warn("[WS] ❌ Closed");
        setStatus("disconnected");
      },

      onWebSocketError: () => {
        console.error("[WS] ❗ Error");
        setStatus("error");
      },

      onStompError: (frame) => {
        console.error("[STOMP ERROR]", frame);
        setStatus("error");
      },
    });

    stompClient.current = client;
    client.activate();
  };

  /* ===========================================================
      DOCTOR — /topic/doctor/{id}/queue
  ============================================================ */
  const subscribeDoctorQueue = (client: Client) => {
    if (![...user?.roles!].some((r) => r.name === "ROLE_DOCTOR")) return;
    const staffId = user?.staff?.id;
    const destination = `/topic/doctor/${staffId}/queue`;

    console.log("[WS] Doctor subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const data = JSON.parse(msg.body);
      const id = data.id;

      if (!seenDoctorQueueRef.current[id]) {
        seenDoctorQueueRef.current[id] = true;
        toast.success("Có bệnh nhân mới trong hàng chờ!");
      }

      window.dispatchEvent(new CustomEvent("doctor-queue", { detail: data }));
      console.log("[WS][Doctor] event:", data);
    });
  };

  /* ===========================================================
      TECHNICIAN — /topic/technician/{id}/orders
  ============================================================ */
  const subscribeTechnicianOrders = (client: Client) => {
    if (![...user?.roles!].some((r) => r.name === "ROLE_TECHNICIAN")) return;

    const staffId = user?.staff?.id;
    const destination = `/topic/technician/${staffId}/orders`;

    console.log("[WS] Technician subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const data = JSON.parse(msg.body);
      const id = data.id;

      if (!seenTechOrdersRef.current[id]) {
        seenTechOrdersRef.current[id] = true;
        toast(`Có order mới: ${data.orderCode}`);
      }

      window.dispatchEvent(
        new CustomEvent("technician-order", { detail: data })
      );
      console.log("[WS][Tech] event:", data);
    });
  };

  /* ===========================================================
      LAB — /topic/lab/{id}/orders
  ============================================================ */
  const subscribeLabOrders = (client: Client) => {
    if (![...user?.roles!].some((r) => r.name === "ROLE_LAB_TECHNICIAN"))
      return;

    const staffId = user?.staff?.id;
    const destination = `/topic/lab/${staffId}/orders`;

    console.log("[WS] Lab subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const data = JSON.parse(msg.body);
      const id = data.id;

      if (!seenLabOrdersRef.current[id]) {
        seenLabOrdersRef.current[id] = true;
        toast.info("Có order xét nghiệm mới!");
      }

      window.dispatchEvent(new CustomEvent("lab-order", { detail: data }));
      console.log("[WS][Lab] event:", data);
    });
  };


    /* ===========================================================
      CASHIER — /topic/invoice/{id}/orders
  ============================================================ */
  const subscribeInvoiceOrders = (client: Client) => {
    if (![...user?.roles!].some((r) => r.name === "ROLE_CASHIER"))
      return;

    const staffId = user?.staff?.id;
    const destination = `/topic/invoice/${staffId}/orders`;

    console.log("[WS] Invoice subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const data = JSON.parse(msg.body);
      const id = data.id;

      if (!seenInvoiceOrdersRef.current[id]) {
        seenInvoiceOrdersRef.current[id] = true;
        toast.info("Có order hóa đơn mới!");
      }

      window.dispatchEvent(new CustomEvent("cashier-invoice", { detail: data }));
      console.log("[WS][Invoice] event:", data);
    });
  };

  const subcribeDispenseOrders = (client: Client) => {
    if (![...user?.roles!].some((r) => r.name === "ROLE_PHARMACIST"))
      return;

    const staffId = user?.staff?.id;
    const destination = `/topic/dispense/${staffId}/orders`;

    console.log("[WS] Dispense subscribe →", destination);

    client.subscribe(destination, (msg) => {
      const data = JSON.parse(msg.body);
      const id = data.id;

      if (!seenDispenseOrdersRef.current[id]) {
        seenDispenseOrdersRef.current[id] = true;
        toast.info("Có order phát thuốc mới!");
      }

      window.dispatchEvent(new CustomEvent("pharmacist-dispense", { detail: data }));
      console.log("[WS][Dispense] event:", data);
    });
  };



  return (
    <WebSocketContext.Provider value={{ status }}>
      {children}
    </WebSocketContext.Provider>
  );
};
