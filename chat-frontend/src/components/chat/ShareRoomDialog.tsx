"use client";
import { useState, useEffect } from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import type { RoomSummary } from "@/types/chat";

interface Props {
  open: boolean;
  onOpenChange: (o: boolean) => void;
  room?: RoomSummary;
}

export function ShareRoomDialog({ open, onOpenChange, room }: Props) {
  const [copied, setCopied] = useState(false);

  // 🔥 Dùng dynamic base URL — không hardcode domain (để chạy được local và deploy)
  const baseUrl =
    import.meta.env.VITE_CHAT_BASE_URL ||
    window.location.origin ||
    "https://chat.sba301.io.vn";

  const inviteLink = room
    ? `${baseUrl}/join/${room.id}?name=${encodeURIComponent(
        room.name
      )}&isPublic=${room.type === "PUBLIC"}`
    : "";

  useEffect(() => {
    if (!open) setCopied(false);
  }, [open]);

  const copy = async () => {
    if (!inviteLink) return;
    try {
      await navigator.clipboard.writeText(inviteLink);
      setCopied(true);
      setTimeout(() => setCopied(false), 2000);
    } catch (err) {
      console.error("❌ Copy failed:", err);
      alert("Không thể copy link. Hãy thử copy thủ công.");
    }
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-md">
        <DialogHeader>
          <DialogTitle>Chia sẻ phòng</DialogTitle>
        </DialogHeader>

        {room ? (
          <div className="space-y-3">
            <div>
              <label className="text-xs font-medium">Link mời</label>
              <Input readOnly value={inviteLink} />
            </div>

            <Button onClick={copy} variant={copied ? "secondary" : "default"} className="bg-white">
              {copied ? "✅ Đã copy" : "📋 Copy link"}
            </Button>
          </div>
        ) : (
          <div className="text-sm text-muted-foreground">
            ⚠️ Chưa chọn phòng để chia sẻ.
          </div>
        )}
      </DialogContent>
    </Dialog>
  );
}
