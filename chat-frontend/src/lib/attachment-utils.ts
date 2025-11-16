import type { Attachment } from "@/types/chat";


export function getAttachmentUrl(att: Attachment): string | undefined {
  // In Vite/browser, process.env is undefined. Use import.meta.env variables instead.
  const base: string = (import.meta as any).env?.VITE_CHAT_BACKEND || window.location.origin;
  if (!att.url) return undefined;
  if (att.url.startsWith("http")) return att.url;
  return base.replace(/\/$/, "") + att.url;
}

export function isDisplayableImage(att: Attachment): boolean {
  return !!att.mimeType && att.mimeType.startsWith('image/');
}

export function isPlayableVideo(att: Attachment): boolean {
  return !!att.mimeType && att.mimeType.startsWith('video/');
}

export function formatFileSize(bytes: number): string {
  if (!bytes) return '0 B';
  const units = ['B','KB','MB','GB','TB'];
  const i = Math.floor(Math.log(bytes)/Math.log(1024));
  return `${(bytes/Math.pow(1024,i)).toFixed(1)} ${units[i]}`;
}
