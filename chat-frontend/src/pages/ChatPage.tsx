
import { useEffect, useState } from "react";
import ChatArea from "@/components/ChatArea";
import ChatLayout from "@/layouts/ChatLayout";
import { useChat } from "@/context/ChatContext";

export default function ChatPage() {
  const { loadRooms, rooms, currentRoom, setCurrentRoom } = useChat();
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const run = async () => {
      setLoading(true);
      try {
        await loadRooms();
      } catch (e) {
        console.error("Failed to load rooms", e);
      } finally {
        setLoading(false);
      }
    };
    run();
  }, []);

  // set default room when rooms load
  useEffect(() => {
    if (!currentRoom && rooms?.length) setCurrentRoom(rooms[0]);
  }, [rooms, currentRoom]);

  return (
    <ChatLayout>
      {loading ? (
        <div className="flex-1 flex items-center justify-center">Đang tải...</div>
      ) : (
        <ChatArea />
      )}
    </ChatLayout>
  );
}
