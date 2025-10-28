// src/lib/webauthn.ts
export const b64urlToUint8 = (b64url: string) =>
  Uint8Array.from(atob(b64url.replace(/-/g, "+").replace(/_/g, "/")), c => c.charCodeAt(0));

export const uint8ToB64url = (buf: ArrayBuffer) =>
  btoa(String.fromCharCode(...new Uint8Array(buf)))
    .replace(/\+/g, "-").replace(/\//g, "_").replace(/=+$/g, "");
