"use client";
import { useEffect, useRef, useState } from "react";
import { Button } from "@/components/ui/button";
import { Volume2, Music } from "lucide-react";
import { chatApi } from "@/api/chatApi";

export interface AudioNotificationSelectorProps {
  value?: string;
  volume?: number;
  onChange?: (fileName: string) => void;
  onVolumeChange?: (volume: number) => void;
}

export function AudioNotificationSelector({
  value,
  volume = 1,
  onChange,
  onVolumeChange,
}: AudioNotificationSelectorProps) {
  const [audioList, setAudioList] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);
  const [uploading, setUploading] = useState(false);
  const fileInputRef = useRef<HTMLInputElement | null>(null);

  // Load danh sách file âm thanh từ backend
  useEffect(() => {
    const loadAudioList = async () => {
      setLoading(true);
      try {
        const { data } = await chatApi.get("/api/profiles/audio/list");
        setAudioList(data || []);
      } catch (err) {
        console.error("❌ Lỗi khi tải danh sách âm thanh:", err);
      } finally {
        setLoading(false);
      }
    };
    loadAudioList();
  }, []);

  const handlePlay = async () => {
    if (!value) return;
    const audioUrl = await chatApi.get(`/api/profiles/${value}`).then((res) => res.data.url);
    const audio = new Audio(audioUrl);
    audio.volume = volume;
    audio.play().catch((err) => console.error("Không thể phát âm thanh:", err));
  };

  const handleUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    setUploading(true);
    try {
      const formData = new FormData();
      formData.append("file", file);
      const res = await chatApi.post("/api/profiles/audio", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      });

      const fileName = res.data?.fileName || file.name;
      setAudioList((prev) => [...prev, fileName]);
      onChange?.(fileName);
    } catch (err) {
      console.error("❌ Upload failed:", err);
      alert("Không thể tải lên âm thanh.");
    } finally {
      setUploading(false);
      if (fileInputRef.current) fileInputRef.current.value = "";
    }
  };

  return (
    <div className="flex items-center gap-2">
      {/* Select list */}
      <select
        className="border rounded px-2 py-1 max-w-[200px]"
        value={value || ""}
        onChange={(e) => onChange?.(e.target.value)}
        disabled={loading}
      >
        <option value="">Chọn âm thanh</option>
        {audioList.map((f) => (
          <option key={f} value={f}>
            {f}
          </option>
        ))}
      </select>

      {/* Nghe thử */}
      <Button
      
        type="button"
        size="icon"
        onClick={handlePlay}
        disabled={!value}
        title="Nghe thử"
      >
        <Music className="w-4 h-4" />
      </Button>

      {/* Upload */}
      <input
        ref={fileInputRef}
        type="file"
        accept="audio/*"
        className="hidden"
        onChange={handleUpload}
        disabled={uploading}
      />
      <Button
        type="button"
        size="icon"
        onClick={() => fileInputRef.current?.click()}
        disabled={uploading}
        title="Tải lên âm thanh mới"
      >
        <span className="text-base leading-none">+</span>
      </Button>

      {/* Volume control */}
      <input
        type="range"
        min={0}
        max={1}
        step={0.01}
        value={volume}
        onChange={(e) => onVolumeChange?.(parseFloat(e.target.value))}
        className="w-24"
        title="Âm lượng"
      />
      <Volume2 className="w-4 h-4 text-muted-foreground" />
    </div>
  );
}
