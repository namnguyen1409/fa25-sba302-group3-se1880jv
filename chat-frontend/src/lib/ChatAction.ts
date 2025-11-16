// lib/chat-actions.ts
import { createContext, useContext } from "react";

export type ChatActions = {
  sendMessage: (args: {
    roomId: string;
    content?: string;
    attachments?: any[];
    replyToId?: string;
  }) => void;
  deleteMessage: (messageId: string) => void;
  reactToMessage: (args: { messageId: string; emoji: string }) => void;
  setReply: (messageId?: string) => void;
};

export const ChatActionsContext = createContext<ChatActions | null>(null);

export function useChatActions() {
  const ctx = useContext(ChatActionsContext);
  if (!ctx)
    throw new Error("useChatActions must be used within ChatActionsContext.Provider");
  return ctx;
}
