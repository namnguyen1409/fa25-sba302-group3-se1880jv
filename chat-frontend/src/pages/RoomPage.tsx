import { useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import ChatLayout from "@/layouts/ChatLayout";
import ChatArea from "@/components/ChatArea";
import { useChat } from "@/context/ChatContext";

export default function RoomPage() {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { rooms, loadRooms, currentRoom, setCurrentRoom } = useChat();

  useEffect(() => {
    const ensureRooms = async () => {
      try {
        if (!rooms || rooms.length === 0) {
          await loadRooms();
        }
      } catch (e) {
        console.error("Failed to load rooms", e);
      }
    };
    ensureRooms();
    return () => {};
  }, []);

  useEffect(() => {
    if (!id) return;
    if (!rooms || rooms.length === 0) return;
    const room = rooms.find((r) => r.id === id);
    if (!room) {
      // room không tồn tại -> quay về trang chính hoặc 404
      navigate("/", { replace: true });
      return;
    }
    if (!currentRoom || currentRoom.id !== room.id) {
      setCurrentRoom(room);
    }
  }, [id, rooms, currentRoom]);

  return (
    <ChatLayout>
      <ChatArea />
    </ChatLayout>
  );
}
