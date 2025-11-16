import React from "react";
import { ExternalLink } from "lucide-react";
import type { EmbedCard, LinkPreview } from "@/types/chat";

interface Props {
  linkPreviews?: LinkPreview[];
  embedCards?: EmbedCard[];
  isOwn?: boolean;
}

// Render ảnh an toàn (không phụ thuộc Next.js)
const SafeImage: React.FC<{
  src: string;
  alt?: string;
  width?: number;
  height?: number;
  className?: string;
}> = ({ src, alt = "", width, height, className }) => {
  try {
    const hostname = new URL(src).hostname;
    const allowedDomains = ["localhost", "yourdomain.com"]; // TODO: đổi thành domain thực tế
    if (allowedDomains.includes(hostname)) {
      return (
        <img
          src={src}
          alt={alt}
          width={width}
          height={height}
          className={`object-cover ${className || ""}`}
          loading="lazy"
        />
      );
    }
  } catch {
    // fallback nếu URL invalid
  }
  return (
    <img
      src={src}
      alt={alt}
      width={width}
      height={height}
      className={`object-cover ${className || ""}`}
      loading="lazy"
    />
  );
};

function areEqual(prev: Props, next: Props) {
  const shallowArrEqual = (a?: any[], b?: any[]) => {
    if (a === b) return true;
    if (!a || !b) return false;
    if (a.length !== b.length) return false;
    for (let i = 0; i < a.length; i++) {
      if (a[i] !== b[i]) return false;
    }
    return true;
  };
  return (
    shallowArrEqual(prev.linkPreviews, next.linkPreviews) &&
    shallowArrEqual(prev.embedCards, next.embedCards) &&
    prev.isOwn === next.isOwn
  );
}

export const MessageEmbedCard = React.memo(function MessageEmbedCard({
  linkPreviews = [],
  embedCards = [],
  isOwn,
}: Props) {
  return (
    <div className="flex flex-col gap-3 mt-2">
      {/* ========== EMBED CARDS (YouTube, Twitter, etc.) ========== */}
      {embedCards.map((embed, idx) => (
        <div
          key={`embed-${idx}`}
          className="rounded-lg border border-gray-200 overflow-hidden  shadow-sm"
        >
          {embed.embedHtml ? (
            <div
              className="w-full aspect-video"
              dangerouslySetInnerHTML={{ __html: embed.embedHtml }}
            />
          ) : embed.embedUrl ? (
            <iframe
              src={embed.embedUrl}
              width={embed.width || "100%"}
              height={embed.height || 200}
              className="w-full border-0"
              allowFullScreen
              loading="lazy"
            />
          ) : embed.thumbnailUrl ? (
            <SafeImage
              src={embed.thumbnailUrl}
              alt={embed.title || ""}
              width={embed.width || 480}
              height={embed.height || 270}
              className="w-full"
            />
          ) : null}

          <div className="p-3">
            <div className="font-semibold text-sm">{embed.title}</div>
            {embed.authorName && (
              <div className="text-xs text-gray-500">{embed.authorName}</div>
            )}
            {embed.originalUrl && (
              <a
                href={embed.originalUrl}
                target="_blank"
                rel="noopener noreferrer"
                className="flex items-center gap-1 text-xs text-blue-600 mt-1 hover:underline"
              >
                Mở <ExternalLink className="w-3 h-3" />
              </a>
            )}
          </div>
        </div>
      ))}

      {/* ========== LINK PREVIEWS (OpenGraph, website) ========== */}
      {linkPreviews.map((link, idx) => (
        <a
          key={`preview-${idx}`}
          href={link.finalUrl || link.url}
          target="_blank"
          rel="noopener noreferrer"
          className="rounded-lg border border-gray-200 overflow-hidden  shadow-sm flex hover:bg-gray-50 transition"
        >
          {link.image && (
            <div className="w-32 h-32 shrink-0 overflow-hidden">
              <SafeImage
                src={link.image}
                alt={link.title || link.siteName || ""}
                width={128}
                height={128}
                className="w-full h-full"
              />
            </div>
          )}
          <div className="p-3 flex flex-col justify-between flex-1">
            <div>
              <div className="font-semibold text-sm truncate">
                {link.title}
              </div>
              <div className="text-xs line-clamp-2">
                {link.description}
              </div>
            </div>
            <div className="flex items-center gap-2 mt-2 text-xs text-gray-400">
              {link.icon && (
                <SafeImage
                  src={link.icon}
                  alt="icon"
                  width={16}
                  height={16}
                  className="rounded-sm"
                />
              )}
              <span>{link.siteName || link.url}</span>
            </div>
          </div>
        </a>
      ))}
    </div>
  );
}, areEqual);
