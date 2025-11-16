import { useState, useRef } from "react";
import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button";
import { Textarea } from "@/components/ui/textarea";
import { nanoid } from "nanoid";
import {
  Paperclip,
  Send,
  X,
  FileText,
  Image as ImageIcon,
  Video,
  Music,
} from "lucide-react";
import { useAuth } from "@/context/AuthContext";
import { useChatSocket } from "@/lib/useChatSocket";
import { chatApi } from "@/api/chatApi";
import type { ProfileInfo } from "@/types/chat";

interface Props {
  roomId: string;
  replyTo?: {
    id: string;
    senderProfile: ProfileInfo;
    content?: string;
  };
  onCancelReply?: () => void;
}

interface FormData {
  content: string;
}

export function MessageInput({ roomId, replyTo, onCancelReply }: Props) {
  const { user } = useAuth();
  const { sendMessage } = useChatSocket(localStorage.getItem("accessToken") ?? "");
  const form = useForm<FormData>({ defaultValues: { content: "" } });

  const fileInputRef = useRef<HTMLInputElement | null>(null);
  const [files, setFiles] = useState<File[]>([]);
  const [isLoading, setIsLoading] = useState(false);

  const content = form.watch("content");

  // --- file handlers ---
  const handleFiles = (fileList: FileList | null) => {
    if (!fileList) return;
    const arr = Array.from(fileList).filter((f) => f.size <= 100 * 1024 * 1024);
    setFiles((prev) => [...prev, ...arr]);
  };

  const handlePaste = (e: React.ClipboardEvent<HTMLTextAreaElement>) => {
    const items = e.clipboardData.items;
    for (let i = 0; i < items.length; i++) {
      const item = items[i];
      if (item.kind === "file") {
        const file = item.getAsFile();
        if (file && file.type.startsWith("image/")) {
          handleFiles({
            0: file,
            length: 1,
            item: () => file,
          } as unknown as FileList);
          e.preventDefault();
        }
      }
    }
  };

  const handleDrop = (e: React.DragEvent<HTMLDivElement>) => {
    e.preventDefault();
    if (e.dataTransfer.files && e.dataTransfer.files.length > 0) {
      handleFiles(e.dataTransfer.files);
    }
  };

  const handleDragOver = (e: React.DragEvent<HTMLDivElement>) => e.preventDefault();

  const removeFile = (index: number) => setFiles((f) => f.filter((_, i) => i !== index));

  const getFileIcon = (file: File) => {
    if (file.type.startsWith("image/")) return <ImageIcon className="h-4 w-4" />;
    if (file.type.startsWith("video/")) return <Video className="h-4 w-4" />;
    if (file.type.startsWith("audio/")) return <Music className="h-4 w-4" />;
    return <FileText className="h-4 w-4" />;
  };

  const formatSize = (bytes: number) => {
    if (!bytes) return "0 B";
    const k = 1024;
    const sizes = ["B", "KB", "MB", "GB"];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return `${(bytes / Math.pow(k, i)).toFixed(1)} ${sizes[i]}`;
  };

  // --- submit ---
  const onSubmit = async (data: FormData) => {
    if (!user) return;
    if (!data.content.trim() && files.length === 0) return;

    setIsLoading(true);
    try {
      // 1️⃣ gửi message tạm
      const placeholders = files.map((f) => ({
        id: nanoid(),
        file: f,
        name: f.name,
        type: f.type,
      }));

      sendMessage({
        type: "message",
        roomId,
        profileId: user.id,
        content: data.content.trim() || "",
        attachments: placeholders.map((p) => ({
          id: p.id,
          originalFileName: p.name,
          mimeType: p.type,
        })),
        replyToMessageId: replyTo?.id,
      });

      // 2️⃣ upload file thật
      for (const p of placeholders) {
        const formData = new FormData();
        formData.append("attachmentId", p.id);
        formData.append("file", p.file);
        await chatApi.post("/api/attachments/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
      }

      // reset
      form.reset();
      setFiles([]);
      onCancelReply?.();
    } finally {
      setIsLoading(false);
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === "Enter" && !e.shiftKey) {
      e.preventDefault();
      form.handleSubmit(onSubmit)();
    }
  };

  const canSend = !!user && (content.trim() || files.length > 0);

  return (
    <div
      className="border-t  p-4"
      onDrop={handleDrop}
      onDragOver={handleDragOver}
    >
      {replyTo && (
        <div className="mb-3 p-3 bg-gray-50 border-l-4 border-blue-500 rounded flex justify-between items-start">
          <div>
            <div className="text-xs mb-1 bg-white">
              Trả lời {replyTo.senderProfile.displayName}
            </div>
            <div className="text-sm text-gray-700 truncate max-w-xs">
              {replyTo.content || "File đính kèm"}
            </div>
          </div>
          <Button
            
            size="sm"
            className="h-6 w-6 p-0 bg-white"
            onClick={onCancelReply}
          >
            <X className="h-3 w-3" />
          </Button>
        </div>
      )}

      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-3">
        {!user && (
          <div className="text-xs text-red-500">
            Đăng nhập trước khi gửi tin nhắn.
          </div>
        )}

        <div className="flex items-start gap-2">
          <div className="flex-1">
            <Textarea
              disabled={isLoading || !user}
              {...form.register("content")}
              onKeyDown={handleKeyDown}
              onPaste={handlePaste}
              placeholder={
                user ? "Nhập tin nhắn..." : "Vui lòng đăng nhập để chat..."
              }
              className="min-h-14 max-h-40 resize-none"
            />
          </div>

          <div className="flex gap-1">
            <input
              ref={fileInputRef}
              type="file"
              multiple
              className="hidden"
              onChange={(e) => handleFiles(e.target.files)}
            />
            <Button
              type="button"
              variant="outline"
              size="icon"
              disabled={!user || isLoading}
              onClick={() => fileInputRef.current?.click()}
              className="h-12 w-12 bg-white"
            >
              <Paperclip className="h-4 w-4" />
            </Button>
            <Button
              type="submit"
              size="icon"
              disabled={!canSend || isLoading}
              className="h-12 w-12 hover:bg-blue-700 bg-white"
            >
              <Send className="h-4 w-4" />
            </Button>
          </div>
        </div>

        {files.length > 0 && (
          <div className="flex flex-wrap gap-2">
            {files.map((f, i) => (
              <div
                key={i}
                className="flex items-center space-x-2 bg-gray-100 rounded p-2 pr-1"
              >
                {getFileIcon(f)}
                <div className="min-w-0 max-w-40">
                  <div className="text-xs font-medium truncate">{f.name}</div>
                  <div className="text-[10px] text-gray-500">
                    {formatSize(f.size)}
                  </div>
                </div>
                <Button
                  
                  size="sm"
                  className="h-5 w-5 p-0 bg-white"
                  onClick={() => removeFile(i)}
                >
                  <X className="h-3 w-3" />
                </Button>
              </div>
            ))}
          </div>
        )}

        {content && (
          <div className="text-right text-[10px] text-gray-500">
            {content.length}/2000
          </div>
        )}
      </form>
    </div>
  );
}
