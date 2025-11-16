// src/context/ChatContext.tsx
"use client";
import { chatApi } from "@/api/chatApi";
import React, { createContext, useContext, useEffect, useState, useRef } from "react";
import { useAuth } from "./AuthContext";
import { useChatSocket } from "@/lib/useChatSocket";

export interface ChatRoom {
  id: string;
  name: string;
  type: "PUBLIC" | "PRIVATE";
  description?: string;
}

export type Message = any; // backend MessageResponse shape (use any to accept full DTO)

interface UploadingFileState {
  status?: "UPLOADING" | "FAILED" | "PENDING" | "DONE";
  progress?: number;
  file?: File;
}

interface ChatContextType {
  rooms: ChatRoom[];
  currentRoom: ChatRoom | null;
  messages: Message[];
  uploadingFiles: Record<string, UploadingFileState>;
  loadRooms: () => Promise<void>;
  setCurrentRoom: (room: ChatRoom | null) => void;
  sendMessage: (roomId: string, content: string) => Promise<void>;
  replyTo: Message | null;
  setReplyTo: (msg: Message | null) => void;
  audioProfile: { fileName: string; volume: number };
  setAudioProfile: (profile: { fileName: string; volume: number }) => void;
  setUploadingFiles: React.Dispatch<React.SetStateAction<Record<string, UploadingFileState>>>;
  startUpload: (tempId: string, file: File) => void;
  updateUploadProgress: (tempId: string, pct: number) => void;
  markUploadFailed: (tempId: string) => void;
  finalizeUpload: (tempId: string, serverId: string) => void;
}

const ChatContext = createContext<ChatContextType | null>(null);

export const ChatProvider = ({ children }: { children: React.ReactNode }) => {
  const [rooms, setRooms] = useState<ChatRoom[]>([]);
  const [currentRoom, setCurrentRoom] = useState<ChatRoom | null>(null);
  const [messages, setMessages] = useState<Message[]>([]);
  const [replyTo, setReplyTo] = useState<Message | null>(null);
  const [audioProfile, setAudioProfile] = useState({
    fileName: "default.mp3",
    volume: 0.7,
  });
  const { user } = useAuth();
  const token = typeof window !== "undefined" ? localStorage.getItem("accessToken") : null;
  const prevRoomRef = useRef<string | null>(null);

  // RSocket hook for realtime
  const { joinStream, join, leave, sendMessage: sendSocketMessage } = useChatSocket(token);

  const loadRooms = async () => {
    const { data } = await chatApi.get("/api/rooms/my");
    setRooms(data);
  };
  

  const loadMessages = async (roomId: string) => {
    const { data } = await chatApi.get(`/api/chat/messages/${roomId}`);
    setMessages(data);
  };

  const sendMessage = async (roomId: string, content: string) => {
    const req = {
      type: "message",
      roomId,
      profileId: user?.id,
      content,
      attachments: [],
      replyToMessageId: null,
    } as any;

    // send via RSocket
    sendSocketMessage(req);

    // optimistic append (backend will also emit NEW_MESSAGE via stream)
    setMessages((prev) => [
      ...prev,
      {
        id: `local-${Date.now()}`,
        content,
        createdAt: new Date().toISOString(),
        senderProfile: { displayName: user?.displayName ?? "Me" },
      },
    ]);
  };

  // load messages khi đổi phòng
  // when current room changes, load history and subscribe to live stream
  useEffect(() => {
    let unsub = () => {};
    if (currentRoom) {
      const roomId = currentRoom.id;
      loadMessages(roomId).catch((e) => console.error(e));

      // leave previous room if any
      if (prevRoomRef.current && prevRoomRef.current !== roomId) {
        leave(prevRoomRef.current);
      }

      // join room (presence) and start stream
      join(roomId);
      joinStream(roomId, (evt: any) => {
        // backend emits WebSocketMessage objects
        if (!evt) return;
        const type = evt.type;
        const data = evt.data ?? evt;
        if (type === "NEW_MESSAGE") {
          setMessages((prev) => [...prev, data]);
        }
        // handle other event types as needed
      });

      prevRoomRef.current = roomId;
      unsub = () => leave(roomId);
    }

    return () => unsub();
  }, [currentRoom]);

  const [uploadingFiles, setUploadingFiles] = useState<Record<string, UploadingFileState>>({});

  // Upload helpers
  const startUpload = (tempId: string, file: File) => {
    setUploadingFiles((prev) => ({ ...prev, [tempId]: { status: "UPLOADING", progress: 0, file } }));
  };
  const updateUploadProgress = (tempId: string, pct: number) => {
    setUploadingFiles((prev) => {
      const cur = prev[tempId];
      if (!cur) return prev;
      return { ...prev, [tempId]: { ...cur, progress: pct } };
    });
  };
  const markUploadFailed = (tempId: string) => {
    setUploadingFiles((prev) => {
      const cur = prev[tempId];
      if (!cur) return prev;
      return { ...prev, [tempId]: { ...cur, status: "FAILED" } };
    });
  };
  const finalizeUpload = (tempId: string, serverId: string) => {
    setUploadingFiles((prev) => {
      const cur = prev[tempId];
      if (!cur) return prev;
      return { ...prev, [tempId]: { ...cur, status: "DONE" } };
    });
  };

  return (
    <ChatContext.Provider
      value={{
        rooms,
        currentRoom,
        messages,
        uploadingFiles,
        loadRooms,
        setCurrentRoom,
        sendMessage,
        replyTo,
        setReplyTo,
        audioProfile,
        setAudioProfile,
        setUploadingFiles,
        startUpload,
        updateUploadProgress,
        markUploadFailed,
        finalizeUpload,
      }}
    >
      {children}
    </ChatContext.Provider>
  );
};

export const useChat = () => {
  const ctx = useContext(ChatContext);
  if (!ctx) throw new Error("useChat must be used inside ChatProvider");
  return ctx;
};
