export type AttachmentStatus = 'PENDING' | 'UPLOADING' | 'COMPLETED' | 'FAILED';
export type MessageType = 'TEXT' | 'FILE' | 'IMAGE' | 'VIDEO' | 'AUDIO' | 'SYSTEM';

export interface Attachment {
  id: string;
  fileName: string;
  originalFileName: string;
  url: string;
  fileSize?: number;
  mimeType?: string;
  status: AttachmentStatus;
  uploadedAt?: string;
}

export interface EmbedCard {
  provider: string;
  type?: string;
  originalUrl: string;
  embedHtml?: string;
  embedUrl?: string;
  width?: number;
  height?: number;
  title?: string;
  authorName?: string;
  thumbnailUrl?: string;
  fetchedAt: string;
}

export interface LinkPreview {
  url: string;
  finalUrl?: string;
  siteName?: string;
  title?: string;
  description?: string;
  image?: string;
  icon?: string;
  contentType?: string;
  fetchedAt: string;
}

export interface Reaction {
  userId: string;
  profileId: string;
  emoji: string;
  count: number;
  addedAt: string;
}

export interface ProfileInfo {
  id: string;
  displayName: string;
  avatar?: string;
}

export interface Message {
  id: string;
  roomId: string;
  senderId: string;
  senderProfileId: string;
  senderProfile: ProfileInfo;
  content?: string;
  messageType: MessageType;
  attachments: Attachment[];
  embedCards: EmbedCard[];
  linkPreviews: LinkPreview[];
  replyToMessageId?: string;
  replyToMessage?: Message;
  reactions: Reaction[];
  isDeleted: boolean;
  createdAt: string;
  _optimistic?: boolean;
}

export interface RoomSummary {
  id: string;
  name: string;
  description?: string;
  type?: string;
  lastMessage?: Message;
  unread?: number;
  unreadCount?: number;
}

export interface Room {
  id: string;
  name: string;
  description?: string;
  type: string;
  ownerId: string;
  memberCount: number;
  isActive: boolean;
  hasPassword: boolean;
  canJoin: boolean;
  createdAt: string;
}

export interface RoomUpdateRequest {
  name: string;
  description?: string;
  type?: string;
  password?: string;
  maxMembers?: number;
}
