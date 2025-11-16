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
import { ScrollArea } from "@/components/ui/scroll-area";
import { useChat } from "@/context/ChatContext";
import type { RoomSummary } from "@/types/chat";
import { chatApi } from "@/api/chatApi";

interface Props {
  open: boolean;
  onOpenChange: (o: boolean) => void;
}

export function JoinRoomDialog({ open, onOpenChange }: Props) {
  const [rooms, setRooms] = useState<RoomSummary[]>([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(false);
  const { setCurrentRoom, setMessages } = useChat();

  // Load public rooms when open
  useEffect(() => {
    if (open) {
      setLoading(true);
      chatApi
        .get("/api/rooms/public")
        .then((res: any) => setRooms(res.data))
        .catch((err: any) => {
          console.error("❌ Load public rooms failed:", err);
        })
        .finally(() => setLoading(false));
    }
  }, [open]);

  const filtered = rooms.filter((r) =>
    r.name.toLowerCase().includes(search.toLowerCase())
  );

  const join = async (id: string) => {
    try {
      setLoading(true);
      await chatApi.post(`/api/rooms/${id}/join`, {});
      const { data } = await chatApi.get(`/api/rooms/${id}`);
      setCurrentRoom(data);
      setMessages([]);
      onOpenChange(false);
    } catch (err) {
      console.error("❌ Join room failed:", err);
      alert("Không thể tham gia phòng.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-md">
        <DialogHeader>
          <DialogTitle>Tham gia phòng công khai</DialogTitle>
        </DialogHeader>

        <div className="space-y-3">
          <Input
            placeholder="Tìm kiếm..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
          {loading ? (
            <div className="text-sm text-gray-500">Đang tải...</div>
          ) : (
            <ScrollArea className="h-72 pr-2">
              <div className="space-y-1">
                {filtered.map((r) => (
                  <div
                    key={r.id}
                    className="flex items-center justify-between rounded border p-2 text-sm"
                  >
                    <div className="font-medium truncate">{r.name}</div>
                    <Button
                      size="sm"
                      variant="outline"
                      onClick={() => join(r.id)}
                      disabled={loading}
                      className="bg-white"
                    >
                      Tham gia
                    </Button>
                  </div>
                ))}
                {filtered.length === 0 && (
                  <div className="text-xs text-muted-foreground">
                    Không có phòng phù hợp
                  </div>
                )}
              </div>
            </ScrollArea>
          )}
        </div>
      </DialogContent>
    </Dialog>
  );
}
