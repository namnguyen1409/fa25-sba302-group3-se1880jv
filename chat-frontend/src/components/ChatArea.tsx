import { useEffect, useRef } from "react";
import { useChat } from "@/context/ChatContext";
import { MessageInput } from "@/components/chat/MessageInput";
import { MessageItem } from "@/components/chat/MessageItem";
import { Hash } from "lucide-react";

export default function ChatArea() {
  const { currentRoom, messages } = useChat();
  const bottomRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  if (!currentRoom)
    return (
      <div className="flex-1 flex items-center justify-center text-gray-500">
        Chọn phòng để bắt đầu trò chuyện 💬
      </div>
    );

  return (
    <div className="flex flex-col flex-1 min-h-0">
      {/* Header */}
      <div className="border-b px-4 py-2 flex items-center gap-2 ">
        <Hash className="h-4 w-4 text-blue-600" />
        <span className="font-semibold">{currentRoom.name}</span>
      </div>

      {/* Messages */}
      <div className="flex-1 overflow-y-auto p-4 bg-gray-50 space-y-3">
        {messages.length === 0 ? (
          <div className="flex items-center justify-center text-gray-400 text-sm">
            Chưa có tin nhắn
          </div>
        ) : (
          messages.map((msg: any) => (
            <MessageItem key={msg.id} messageId={msg.id} />
          ))
        )}
        <div ref={bottomRef} />
      </div>

      {/* Input */}
      <div className="border-t ">
        <MessageInput
          roomId={currentRoom.id}
          onCancelReply={undefined}
        />
      </div>
    </div>
  );
}
