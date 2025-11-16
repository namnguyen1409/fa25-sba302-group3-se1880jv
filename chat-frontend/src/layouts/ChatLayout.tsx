import { type ReactNode } from "react";
import { useChat } from "@/context/ChatContext";
import { RoomsSidebar } from "@/components/chat/RoomsSidebar";
import { AudioNotificationSelector } from "@/components/chat/AudioNotificationSelector";

export default function ChatLayout({ children }: { children: ReactNode }) {
  const { currentRoom, audioProfile, setAudioProfile } = useChat();

  return (
    <div className="h-screen w-screen flex overflow-hidden">
      <RoomsSidebar />

      <div className="flex flex-col flex-1 min-h-0 min-w-0 max-w-full">
        {/* Header */}
        <div className="border-b p-3 font-semibold shrink-0 flex items-center gap-4">
          <span>{currentRoom?.name || "Chọn phòng"}</span>
          <div className="ml-auto">
            <AudioNotificationSelector
              value={audioProfile?.fileName}
              volume={audioProfile?.volume ?? 1}
              onChange={(fileName) =>
                setAudioProfile({ fileName, volume: audioProfile?.volume ?? 1 })
              }
              onVolumeChange={(volume) =>
                setAudioProfile({ fileName: audioProfile?.fileName || "", volume })
              }
            />
          </div>
        </div>

        {/* Content */}
        <div className="flex-1 min-h-0 flex flex-col">{children}</div>
      </div>
    </div>
  );
}
