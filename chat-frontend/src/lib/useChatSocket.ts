import { useEffect, useRef } from "react";
import { buildMetadata, createRSocketClient, requestResponse } from "./rsocketClient";
import type { RSocketClient } from "rsocket-core";

/**
 * ✅ Decode JWT không cần lib ngoài
 * (lấy payload giữa hai dấu chấm, decode base64)
 */
function decodeJwtPayload(token: string): Record<string, any> | null {
  try {
    const base64Payload = token.split(".")[1];
    const decoded = atob(base64Payload.replace(/-/g, "+").replace(/_/g, "/"));
    return JSON.parse(decoded);
  } catch (e) {
    console.warn("⚠️ Invalid JWT token:", e);
    return null;
  }
}

export function useChatSocket(accessToken?: string | null) {
  const sockRef = useRef<RSocketClient | null>(null);
  const userIdRef = useRef<string | null>(null);

  useEffect(() => {
    if (!accessToken) return;

    // ✅ Decode sub từ JWT
    const decoded = decodeJwtPayload(accessToken);
    userIdRef.current = decoded?.sub ?? null;
    console.log("🔑 decoded userId:", userIdRef.current);

  // ✅ Tạo client RSocket, đưa Bearer token vào setup.metadataUtf8
  const client = createRSocketClient(accessToken);

    const sub = client.connect().subscribe({
      onComplete: (socket) => {
        console.log("✅ RSocket connected");
        sockRef.current = socket;
      },
      onError: (err) => console.error("❌ RSocket connect failed", err),
      onSubscribe: () => console.log("🌀 connecting..."),
    });

    return () => {
      try {
        sockRef.current?.close?.();
      } catch {}
      (sub as any)?.cancel?.();
      sockRef.current = null;
    };
  }, [accessToken]);

  // ================================
  // 🔄 JOIN STREAM
  // ================================
  const joinStream = (roomId: string, onMessage: (msg: any) => void) => {
    if (!sockRef.current) {
      console.warn("⚠️ Socket not ready");
      return;
    }

    const payload = {
      roomId,
      userId: userIdRef.current ?? "anonymous",
    };

    sockRef.current
      .requestStream({
        data: JSON.stringify(payload),
        metadata: buildMetadata("chat.stream"),
      })
      .subscribe({
        onNext: (p: any) => {
          try {
            const msg =
              typeof p.data === "string"
                ? JSON.parse(p.data)
                : JSON.parse(new TextDecoder().decode(p.data));
            onMessage(msg);
          } catch {
            onMessage(p.data);
          }
        },
        onError: (e: any) => console.error("stream error", e),
        onSubscribe: (s: any) => s.request(0x7fffffff),
      });
  };

  // ================================
  // 🚀 FIRE & FORGET (JOIN / LEAVE)
  // ================================
  const fire = (route: string, data: any) => {
    if (!sockRef.current) return console.warn("⚠️ Socket not ready");
    const enriched = { ...data, userId: userIdRef.current ?? "anonymous" };
    sockRef.current.fireAndForget({
      data: JSON.stringify(enriched),
      metadata: buildMetadata(route),
    });
  };

  const join = (roomId: string) => fire("chat.join", { roomId });
  const leave = (roomId: string) => fire("chat.leave", { roomId });

  // ================================
  // 💬 SEND MESSAGE (REQUEST-RESPONSE)
  // ================================
  const sendMessage = async (payload: any) => {
    if (!sockRef.current) {
      console.warn("⚠️ Socket not ready");
      return;
    }

    const enrichedPayload = {
      ...payload,
      userId: userIdRef.current ?? "anonymous",
    };

    console.log("➡️ Sending message via RSocket", enrichedPayload);

    try {
      const resp = await requestResponse<any>(
        sockRef.current,
        "chat.send",
        enrichedPayload
      );
      console.log("✅ send ok", resp);
    } catch (e) {
      console.error("❌ send error", e);
    }
  };

  return { joinStream, join, leave, sendMessage };
}
