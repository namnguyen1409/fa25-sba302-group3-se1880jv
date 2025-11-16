import { RSocketClient, IdentitySerializer } from "rsocket-core";
import RSocketWebSocketClient from "rsocket-websocket-client";
import { Buffer } from "buffer";
if (!(globalThis as any).Buffer) (globalThis as any).Buffer = Buffer;

const WS_URL =
  import.meta.env.VITE_APP_RSOCKET_URL || "ws://localhost:9888/rsocket";

/**
 * 🧩 Build RSocket metadata chỉ gồm route (không JWT)
 */
// ✅ Dạng đơn giản: chỉ encode route, không composite
export function buildMetadata(route: string): string {
  // Spring hiểu "message/x.rsocket.routing.v0" dạng này
  const utf8 = new TextEncoder().encode(route);
  const routeBytes = new Uint8Array(1 + utf8.length);
  routeBytes[0] = utf8.length; // prefix length
  routeBytes.set(utf8, 1);

  // Convert to string
  let result = "";
  for (let i = 0; i < routeBytes.length; i++) {
    result += String.fromCharCode(routeBytes[i]);
  }
  return result;
}


/**
 * ✅ Tạo RSocket Client (không cần token)
 */
export function createRSocketClient(token?: string | null) {
  return new RSocketClient({
    serializers: {
      data: IdentitySerializer,
      metadata: IdentitySerializer,
    },
    setup: {
      keepAlive: 10000,
      lifetime: 20000,
      dataMimeType: "application/json",
      metadataMimeType: "message/x.rsocket.routing.v0",
      // Put Bearer token into ConnectionSetupPayload.metadataUtf8 so
      // JwtRSocketInterceptor on server can authenticate the connection.
      payload: token ? ({ metadata: `Bearer ${token}` } as any) : ({} as any),
    },
    transport: new RSocketWebSocketClient({ url: WS_URL }),
  });
}

/**
 * ✅ Gửi request/response (route + data, không cần JWT)
 */
export async function requestResponse<T>(
  socket: any,
  route: string,
  data: any
): Promise<T> {
  return new Promise((resolve, reject) => {
    socket
      .requestResponse({
        data: JSON.stringify(data),
        metadata: buildMetadata(route),
      })
      .subscribe({
        onComplete: (payload: any) => {
          try {
            resolve(JSON.parse(payload.data));
          } catch {
            resolve(payload.data);
          }
        },
        onError: reject,
      });
  });
}
