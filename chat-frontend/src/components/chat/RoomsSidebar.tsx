import { useState, useEffect, useCallback, useMemo } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { ScrollArea } from "@/components/ui/scroll-area";
import { Badge } from "@/components/ui/badge";
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet";
import {
  Hash,
  Lock,
  Plus,
  Users,
  Search,
  MessageCircle,
  RefreshCw,
  Share2,
  Menu,
} from "lucide-react";
import { useChat } from "@/context/ChatContext";
import { CreateRoomDialog } from "./CreateRoomDialog";
import { JoinRoomDialog } from "./JoinRoomDialog";
import { ShareRoomDialog } from "./ShareRoomDialog";
import { chatApi } from "@/api/chatApi";

export function RoomsSidebar() {
  const navigate = useNavigate();
  const {
    currentRoom,
    setCurrentRoom,
  } = useChat();

  const [rooms, setRooms] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [connected] = useState(true);
  const [searchTerm, setSearchTerm] = useState("");
  const [showCreate, setShowCreate] = useState(false);
  const [showJoin, setShowJoin] = useState(false);
  const [showShare, setShowShare] = useState(false);

  // Load room list
  const loadRooms = useCallback(async () => {
    setLoading(true);
    try {
      const { data } = await chatApi.get("/api/rooms/my");
      setRooms(data);
    } catch (err) {
      console.error("❌ Load rooms failed:", err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    loadRooms();
  }, [loadRooms]);

  const filteredRooms = useMemo(
    () =>
      rooms.filter((r) =>
        r.name.toLowerCase().includes(searchTerm.toLowerCase())
      ),
    [rooms, searchTerm]
  );

  const handleRoomClick = useCallback(
    (room: any) => {
      if (currentRoom?.id !== room.id) {
        setCurrentRoom(room);
        navigate(`/room/${room.id}`);
      }
    },
    [currentRoom, setCurrentRoom, navigate]
  );

  const DesktopContent = (
    <div className="flex flex-col h-full bg-background border-r">
      <div className="p-4 border-b space-y-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-2">
            <MessageCircle className="h-5 w-5 text-primary" />
            <h2 className="font-semibold text-lg">Itoma Chat</h2>
          </div>
          <div className="flex items-center gap-1">
            <Button
              
              size="sm"
              disabled={loading}
              onClick={loadRooms}
              className="h-7 w-7 p-0 bg-white"
            >
              <RefreshCw
                className={`h-4 w-4 ${loading ? "animate-spin" : ""}`}
              />
            </Button>
            <Badge
              variant={connected ? "default" : "destructive"}
              className="text-[10px]"
            >
              {connected ? "Online" : "Offline"}
            </Badge>
          </div>
        </div>

        {/* Search */}
        <div className="relative">
          <Search className="h-4 w-4 absolute left-3 top-1/2 -translate-y-1/2 text-muted-foreground" />
          <Input
            placeholder="Tìm kiếm phòng..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="pl-9"
          />
        </div>

        <div className="grid grid-cols-2 gap-2 bg-white">
          <Button variant="outline" size="sm" onClick={() => setShowCreate(true)} className="bg-white">
            <Plus className="h-4 w-4 mr-1 bg-white" /> Tạo
          </Button>
          <Button variant="outline" size="sm" onClick={() => setShowJoin(true)} className="bg-white">
            <Users className="h-4 w-4 mr-1 bg-white" /> Join
          </Button>
          <Button
            variant="outline"
            size="sm"
            disabled={!currentRoom}
            onClick={() => setShowShare(true)}
            className="col-span-2 bg-white"
          >
            <Share2 className="h-4 w-4 mr-1 bg-white" /> Chia sẻ phòng
          </Button>
        </div>
      </div>

      {/* Room list */}
      <ScrollArea className="flex-1">
        <div className="p-2 space-y-1">
          {loading ? (
            <div className="text-center py-8 text-muted-foreground text-sm flex flex-col items-center gap-2">
              <RefreshCw className="h-6 w-6 animate-spin" />
              <span>Đang tải phòng...</span>
            </div>
          ) : filteredRooms.length === 0 ? (
            <div className="text-center py-8 text-muted-foreground text-sm flex flex-col items-center gap-2">
              <MessageCircle className="h-10 w-10 opacity-40" />
              <span>Không có phòng</span>
              <span className="text-xs">Tạo hoặc tham gia phòng mới</span>
            </div>
          ) : (
            filteredRooms.map((r) => (
              <div
                key={r.id}
                onClick={() => handleRoomClick(r)}
                className={`flex items-center gap-3 p-3 rounded-lg cursor-pointer transition-colors border ${
                  currentRoom?.id === r.id
                    ? "bg-primary/10 border-primary"
                    : "hover:bg-muted border-transparent"
                }`}
              >
                <div className="shrink-0">
                  {r.type === "PUBLIC" ? (
                    <Hash className="h-4 w-4 text-muted-foreground" />
                  ) : (
                    <Lock className="h-4 w-4 text-muted-foreground" />
                  )}
                </div>
                <div className="flex-1 min-w-0">
                  <p className="text-sm font-medium truncate max-w-[200px]">
                    {r.name}
                  </p>
                  {r.lastMessage && (
                    <p className="text-xs text-muted-foreground truncate">
                      {r.lastMessage.content ||
                        (r.lastMessage.attachments?.length
                          ? "Tệp đính kèm"
                          : "")}
                    </p>
                  )}
                </div>
                {r.unread > 0 && (
                  <Badge
                    variant="destructive"
                    className="ml-auto text-[10px] h-5"
                  >
                    {r.unread}
                  </Badge>
                )}
              </div>
            ))
          )}
        </div>
      </ScrollArea>
    </div>
  );

  return (
    <>
      <div className="hidden md:flex h-full">{DesktopContent}</div>

      {/* Mobile sheet */}
      <Sheet>
        <SheetTrigger asChild>
          <Button
            
            size="icon"
            className="md:hidden absolute top-2 right-2 z-30 bg-white"
          >
            <Menu className="h-5 w-5" />
          </Button>
        </SheetTrigger>
        <SheetContent side="left" className="w-80 p-0">
          {DesktopContent}
        </SheetContent>
      </Sheet>

      {/* Dialogs */}
      <CreateRoomDialog open={showCreate} onOpenChange={setShowCreate} />
      <JoinRoomDialog open={showJoin} onOpenChange={setShowJoin} />
      <ShareRoomDialog
        open={showShare}
        onOpenChange={setShowShare}
        room={currentRoom ?? undefined}
      />
    </>
  );
}
