import React, { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import {
  Download,
  FileText,
  Image as ImageIcon,
  Video,
  Music,
  Eye,
  Loader2,
  AlertTriangle,
} from "lucide-react";
import {
  getAttachmentUrl,
  isDisplayableImage,
  isPlayableVideo,
  formatFileSize,
} from "@/lib/attachment-utils";
import { Dialog, DialogContent } from "@/components/ui/dialog";
import { useChat } from "@/context/ChatContext";
import { ZoomPanImage } from "../ui/ZoomPanImage";
import type { Attachment } from "@/types/chat";

interface Props {
  attachment: Attachment;
  isOwn: boolean;
  onDownload?: (att: Attachment) => void;
}

const HEAVY_THRESHOLD = 5 * 1024 * 1024; // 5MB

const getFileIcon = (mime?: string) => {
  if (!mime) return <FileText className="h-4 w-4" />;
  if (mime.startsWith("image/")) return <ImageIcon className="h-4 w-4" />;
  if (mime.startsWith("video/")) return <Video className="h-4 w-4" />;
  if (mime.startsWith("audio/")) return <Music className="h-4 w-4" />;
  return <FileText className="h-4 w-4" />;
};

const StatusBadge = ({
  status,
  progress,
}: {
  status?: string;
  progress?: number;
}) => {
  switch (status) {
    case "UPLOADING":
      return (
        <span className="text-[10px] px-1 py-0.5 rounded bg-blue-500/10 text-blue-600 flex items-center gap-1">
          <Loader2 className="h-3 w-3 animate-spin" />
          {progress ?? 0}%
        </span>
      );
    case "FAILED":
      return (
        <span className="text-[10px] px-1 py-0.5 rounded bg-red-500/10 text-red-600 flex items-center gap-1">
          <AlertTriangle className="h-3 w-3" />
          Lỗi
        </span>
      );
    case "PENDING":
      return (
        <span className="text-[10px] px-1 py-0.5 rounded bg-gray-500/10 text-gray-600">
          Chờ
        </span>
      );
    default:
      return null;
  }
};

export const MessageAttachment = React.memo(function MessageAttachment({
  attachment,
  isOwn,
  onDownload,
}: Props) {
  const [showModal, setShowModal] = useState(false);
  const [previewLoaded, setPreviewLoaded] = useState(false);
  const [objectUrl, setObjectUrl] = useState<string>();
  const { uploadingFiles } = useChat();

  // nếu file đang upload (local)
  const local = uploadingFiles?.[attachment?.id ?? ""] ?? null;

  const status = local?.status || attachment.status;
  const progress = local?.progress;
  const fileSize = attachment.fileSize || local?.file?.size || 0;

  const remoteUrl =
    getAttachmentUrl(attachment) ||
    `${import.meta.env.VITE_CHAT_BACKEND}/api/files/view/${attachment.id}`;

  const isImage = isDisplayableImage(attachment);
  const isVideo = isPlayableVideo(attachment);
  const isAudio = attachment.mimeType?.startsWith("audio/");
  const heavy = fileSize > HEAVY_THRESHOLD;

  useEffect(() => {
    if ((isImage || isVideo || isAudio) && fileSize > 0 && !heavy) {
      setPreviewLoaded(true);
    }
  }, [isImage, isVideo, isAudio, fileSize, heavy]);

  useEffect(() => {
    if (previewLoaded && !remoteUrl && local?.file) {
      const url = URL.createObjectURL(local.file);
      setObjectUrl(url);
      return () => URL.revokeObjectURL(url);
    }
  }, [previewLoaded, remoteUrl, local?.file]);

  const displayUrl = remoteUrl || objectUrl;

  const handleDownload = () => {
    if (onDownload) return onDownload(attachment);
    if (displayUrl) window.open(displayUrl, "_blank");
  };

  const PreviewOverlay = () => {
    if (status === "UPLOADING")
      return (
        <div className="absolute inset-0 bg-black/40 flex flex-col items-center justify-center text-white text-xs gap-2">
          <Loader2 className="h-5 w-5 animate-spin" />
          <div>{progress ?? 0}%</div>
        </div>
      );
    if (status === "FAILED")
      return (
        <div className="absolute inset-0 bg-red-500/20 flex items-center justify-center text-red-700 text-xs font-medium">
          Lỗi upload
        </div>
      );
    return null;
  };

  if (isImage || isVideo || isAudio) {
    return (
      <div className="space-y-2" key={attachment.id}>
        {!previewLoaded && heavy ? (
          <div
            className={`w-60 h-40 border rounded flex flex-col items-center justify-center gap-2 ${
              isOwn
                ? "border-blue-300 bg-blue-50"
                : "border-gray-300 bg-gray-50"
            }`}
          >
            <Eye className="h-6 w-6 text-gray-500" />
            <div className="text-xs text-center">
              Nhấn để tải xem trước
              <br />
              {formatFileSize(fileSize)}
            </div>
            <Button
              size="sm"
              variant="outline"
              onClick={() => setPreviewLoaded(true)}
            >
              Xem
            </Button>
          </div>
        ) : (
          <div className="relative max-w-xs group">
            {isImage && displayUrl && (
              <>
                <img
                  src={displayUrl}
                  alt={attachment.originalFileName}
                  className="rounded-lg max-w-full h-auto cursor-pointer hover:opacity-90"
                  onClick={() => setShowModal(true)}
                  loading="lazy"
                />
                <Dialog open={showModal} onOpenChange={setShowModal}>
                  <DialogContent className="max-w-3xl bg-black p-0 flex items-center justify-center">
                    <ZoomPanImage
                      src={displayUrl}
                      alt={attachment.originalFileName}
                    />
                  </DialogContent>
                </Dialog>
              </>
            )}
            {isVideo && displayUrl && (
              <video
                src={displayUrl}
                controls
                className="rounded-lg max-w-full h-auto"
                preload="metadata"
              />
            )}
            {isAudio && displayUrl && (
              <div className="relative max-w-xs p-2 border rounded  flex items-center gap-2">
                <Music className="h-5 w-5 text-gray-500" />
                <audio controls src={displayUrl} className="w-48" />
              </div>
            )}
            <PreviewOverlay />
          </div>
        )}
        <div
          className={`flex items-center gap-2 text-xs ${
            isOwn ? "text-blue-100" : "text-gray-500"
          }`}
        >
          <span className="truncate max-w-[200px]">
            {attachment.originalFileName}
          </span>
          <span>• {formatFileSize(fileSize)}</span>
          <StatusBadge status={status} progress={progress} />
          {heavy && !previewLoaded && (
            <span className="text-[10px] px-1 py-0.5 rounded bg-gray-500/10 text-gray-600">
              Lớn
            </span>
          )}
        </div>
      </div>
    );
  }

  // file khác
  return (
    <div
      key={attachment.id}
      className={`flex items-center space-x-3 p-2 border rounded-lg cursor-pointer transition-colors ${
        isOwn
          ? "bg-blue-400 hover:bg-blue-300 border-blue-300"
          : "bg-gray-50 hover:bg-gray-100 border-gray-200"
      }`}
      onClick={handleDownload}
    >
      <div className="flex-shrink-0 relative">
        {getFileIcon(attachment.mimeType)}
        {status === "UPLOADING" && (
          <div className="absolute -top-1 -right-1 bg-blue-500 text-white rounded-full h-4 w-4 flex items-center justify-center text-[8px]">
            {progress ?? 0}
          </div>
        )}
      </div>
      <div className="flex-1 min-w-0">
        <p className="text-sm font-medium truncate flex items-center gap-2">
          {attachment.originalFileName}
          <StatusBadge status={status} progress={progress} />
        </p>
        <p className={`text-xs ${isOwn ? "text-blue-100" : "text-gray-500"}`}>
          {formatFileSize(fileSize)}
          {attachment.mimeType && ` • ${attachment.mimeType}`}
        </p>
      </div>
      <div className="flex-shrink-0">
        <Download
          className={`h-4 w-4 ${isOwn ? "text-blue-100" : "text-gray-400"}`}
        />
      </div>
    </div>
  );
});
