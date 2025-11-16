import { useState, useMemo } from "react";
import { format } from "date-fns";
import { vi } from "date-fns/locale";
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { MessageAttachment } from "@/components/chat/MessageAttachment";
import { useChat } from "@/context/ChatContext";
import { useAuth } from "@/context/AuthContext";
import { useChatSocket } from "@/lib/useChatSocket";
import { MessageEmbedCard } from "./MessageEmbedCard";
import { ReactionPicker } from "./ReactionPicker";

interface MessageItemProps {
  messageId: string;
}

export function MessageItem({ messageId }: MessageItemProps) {
  const { messages, setReplyTo } = useChat();
  const { user } = useAuth();
  const { sendMessage } = useChatSocket(localStorage.getItem("accessToken") ?? "");

  const message = messages.find((m: any) => m.id === messageId);
  const [showReactionPicker, setShowReactionPicker] = useState(false);
  const [confirmDelete, setConfirmDelete] = useState(false);

  if (!message) return null;

  const isOwn = user && message.senderProfileId === user.id;

  // Only show reply preview when there is meaningful original content
  const reply = message.replyToMessage as any | undefined;
  const hasReply = !!reply && (
    Boolean(reply.id) ||
    (typeof reply.content === "string" && reply.content.trim().length > 0) ||
    (Array.isArray(reply.attachments) && reply.attachments.length > 0) ||
    Boolean(reply.senderProfile?.displayName)
  );

  const formatTime = (ts: string) => {
    try {
      return format(new Date(ts), "HH:mm", { locale: vi });
    } catch {
      return "";
    }
  };

  const getInitials = (name?: string) => name?.charAt(0).toUpperCase() || "A";

  const groupedReactions = useMemo(() => {
    const groups: Record<
      string,
      { emoji: string; count: number; reactedByMe: boolean }
    > = {};
    message.reactions?.forEach((r:any) => {
      if (!groups[r.emoji]) {
        groups[r.emoji] = { emoji: r.emoji, count: 0, reactedByMe: false };
      }
      groups[r.emoji].count += 1;
      if (r.profileId === user?.id) {
        groups[r.emoji].reactedByMe = true;
      }
    });
    return Object.values(groups);
  }, [message.reactions, user?.id]);

  const handleDelete = () => {
    sendMessage({ type: "delete_message", messageId });
    setConfirmDelete(false);
  };

  const handleReact = (emoji: string) => {
    sendMessage({
      type: "add_reaction",
      messageId: message.id,
      emoji,
      profileId: user?.id,
    });
  };

  const handleReply = () => setReplyTo(message);

  return (
    <>
      <div
        className={`flex items-start p-3 relative group ${
          isOwn ? "flex-row-reverse" : ""
        }`}
      >
        {/* Avatar */}
        <Avatar className="h-8 w-8 shrink-0 ml-2 mr-2">
          {message.senderProfile?.avatar ? (
            <AvatarImage
              src={`${import.meta.env.VITE_CLINIC_BASE_URL}${
                message.senderProfile.avatar
              }`}
              alt={message.senderProfile.displayName}
            />
          ) : (
            <AvatarFallback
              className={`text-xs ${
                isOwn
                  ? "bg-blue-100 text-blue-700"
                  : "bg-gray-100 text-gray-700"
              }`}
            >
              {getInitials(message.senderProfile?.displayName)}
            </AvatarFallback>
          )}
        </Avatar>

        {/* Nội dung */}
        <div
          className={`flex flex-col max-w-[75%] min-w-0 ${
            isOwn ? "items-end" : ""
          }`}
        >
          {/* Header */}
          <div
            className={`flex items-center gap-2 mb-1 ${
              isOwn ? "flex-row-reverse" : ""
            }`}
          >
            <span className="text-sm font-medium text-gray-900">
              {message.senderProfile?.displayName || "Anonymous"}
            </span>
            <span className="text-xs text-gray-500">
              {formatTime(message.createdAt)}
            </span>
          </div>

          {/* Reply preview */}
          {hasReply && (
            <div
              className={`mb-2 p-2 text-xs text-gray-700 bg-gray-50 border-l-2 border-gray-300 rounded ${
                isOwn ? "border-r-2 border-l-0 text-right" : ""
              }`}
            >
              {reply?.senderProfile?.displayName && (
                <div className="mb-1">
                  Trả lời {reply.senderProfile.displayName}
                </div>
              )}
              <div className="truncate">
                {reply?.content || (Array.isArray(reply?.attachments) && reply.attachments.length > 0 ? "File đính kèm" : "")}
              </div>
            </div>
          )}

          {/* Bubble */}
          <div
            className={`p-3 rounded-lg whitespace-pre-wrap wrap-break-word ${
              isOwn
                ? "bg-blue-500 text-white"
                : " border border-gray-200"
            }`}
          >
            {message.content && (
              <div className="text-sm max-w-[50vw]">{message.content}</div>
            )}
            {message.attachments?.length > 0 && (
              <div className="space-y-2 mt-2">
                {message.attachments.map((att: any) => (
                  <MessageAttachment
                    key={att.id}
                    attachment={att}
                    isOwn={isOwn || false}
                  />
                ))}
              </div>
            )}
            {(message.embedCards?.length > 0 ||
              message.linkPreviews?.length > 0) && (
              <MessageEmbedCard
                embedCards={message.embedCards}
                linkPreviews={message.linkPreviews}
              />
            )}
          </div>

          {/* Reactions */}
          {groupedReactions.length > 0 && (
            <div
              className={`flex flex-wrap gap-1 mt-1 ${
                isOwn ? "justify-end" : ""
              }`}
            >
              {groupedReactions.map((r) => (
                <button
                  key={r.emoji}
                  onClick={() => handleReact(r.emoji)}
                  className={`px-2 py-0.5 rounded-full text-xs border ${
                    r.reactedByMe
                      ? "bg-blue-100 border-blue-400"
                      : "bg-gray-100 border-gray-300"
                  }`}
                >
                  {r.emoji} {r.count}
                </button>
              ))}
            </div>
          )}

          {/* Actions */}
          <div
            className={`flex items-center gap-2 mt-2 opacity-0 group-hover:opacity-100 transition-opacity ${
              isOwn ? "justify-end" : ""
            }`}
          >
            {!message.isDeleted && (
              <>
                <Button
                  
                  size="sm"
                  onClick={handleReply}
                  className="h-7 px-2 text-xs bg-white"
                >
                  Trả lời
                </Button>
                <Button
                  
                  size="sm"
                  className="h-7 px-2 text-xs bg-white"
                  onClick={() => setShowReactionPicker((v) => !v)}
                >
                  Thêm reaction
                </Button>
              </>
            )}
            {isOwn && !message.isDeleted && (
              <Button
                
                size="sm"
                className="h-7 px-2 text-xs text-red-600 bg-white"
                onClick={() => setConfirmDelete(true)}
              >
                Xóa
              </Button>
            )}
          </div>
        </div>

        {/* Reaction Picker */}
        {showReactionPicker && (
          <ReactionPicker
            messageId={message.id}
            onClose={() => setShowReactionPicker(false)}
          />
        )}
      </div>

      {/* Confirm Delete */}
      {confirmDelete && (
        <div className="fixed inset-0 flex items-center justify-center bg-black/50 z-50">
          <div className=" p-4 rounded-lg shadow-lg">
            <h3 className="text-lg font-semibold mb-2">Xác nhận xóa</h3>
            <p className="text-sm text-gray-600 mb-4">
              Bạn có chắc muốn xóa tin nhắn này?
            </p>
            <div className="flex justify-end gap-2">
              <Button
                variant="outline"
                size="sm"
                onClick={() => setConfirmDelete(false)}
                className="bg-white"
              >
                Hủy
              </Button>
              <Button variant="destructive" size="sm" onClick={handleDelete} className="bg-white">
                Xóa
              </Button>
            </div>
          </div>
        </div>
      )}
    </>
  );
}
