"use client";
import { useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { useChat } from "@/context/ChatContext";
import { chatApi } from "@/api/chatApi";

interface Props {
  open: boolean;
  onOpenChange: (o: boolean) => void;
}

export function CreateRoomDialog({ open, onOpenChange }: Props) {
  const [form, setForm] = useState({
    name: "",
    description: "",
    type: "PUBLIC",
    password: "",
    maxMembers: "",
  });
  const [loading, setLoading] = useState(false);

  const { setCurrentRoom, setMessages } = useChat();

  const submit = async () => {
    if (!form.name.trim()) return;
    setLoading(true);
    try {
      const payload: any = {
        name: form.name.trim(),
        description: form.description || null,
        type: form.type,
      };
      if (form.type === "PRIVATE" && form.password)
        payload.password = form.password;
      if (form.maxMembers)
        payload.maxMembers = Number(form.maxMembers);

      const { data } = await chatApi.post("/api/rooms", payload);

      // set room hiện tại & clear tin nhắn cũ
      setCurrentRoom(data);
      setMessages([]);

      // đóng dialog và reset form
      onOpenChange(false);
      setForm({
        name: "",
        description: "",
        type: "PUBLIC",
        password: "",
        maxMembers: "",
      });
    } catch (err) {
      console.error("❌ Create room failed:", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-md">
        <DialogHeader>
          <DialogTitle>Tạo phòng chat</DialogTitle>
        </DialogHeader>

        <div className="space-y-3 text-sm">
          <div>
            <label className="font-medium text-xs">Tên phòng</label>
            <Input
              value={form.name}
              onChange={(e) =>
                setForm((f) => ({ ...f, name: e.target.value }))
              }
              required
            />
          </div>

          <div>
            <label className="font-medium text-xs">Mô tả</label>
            <Textarea
              value={form.description}
              onChange={(e) =>
                setForm((f) => ({ ...f, description: e.target.value }))
              }
            />
          </div>

          <div className="flex gap-2 items-center text-xs">
            <label>Loại:</label>
            <select
              className="border rounded px-2 py-1 bg-background"
              value={form.type}
              onChange={(e) =>
                setForm((f) => ({ ...f, type: e.target.value }))
              }
            >
              <option value="PUBLIC">Công khai</option>
              <option value="PRIVATE">Riêng tư</option>
            </select>
          </div>

          {form.type === "PRIVATE" && (
            <div>
              <label className="font-medium text-xs">Mật khẩu</label>
              <Input
                type="password"
                value={form.password}
                onChange={(e) =>
                  setForm((f) => ({ ...f, password: e.target.value }))
                }
              />
            </div>
          )}

          <div>
            <label className="font-medium text-xs">
              Số thành viên tối đa (tùy chọn)
            </label>
            <Input
              type="number"
              value={form.maxMembers}
              onChange={(e) =>
                setForm((f) => ({ ...f, maxMembers: e.target.value }))
              }
            />
          </div>
        </div>

        <div className="flex justify-end pt-2">
          <Button
            disabled={loading || !form.name.trim()}
            onClick={submit}
            className="bg-white"
          >
            {loading ? "Đang tạo..." : "Tạo phòng"}
          </Button>
        </div>
      </DialogContent>
    </Dialog>
  );
}
