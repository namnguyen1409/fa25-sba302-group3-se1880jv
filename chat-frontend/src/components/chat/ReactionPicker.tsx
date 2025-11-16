// ReactionPicker.tsx
"use client";
import { Button } from '@/components/ui/button';
import { useChatActions } from '@/lib/ChatAction';

const reactions = [
  { emoji: '👍', label: 'Like' },
  { emoji: '😂', label: 'Haha' },
  { emoji: '❤️', label: 'Love' },
  { emoji: '😮', label: 'Wow' },
  { emoji: '😢', label: 'Sad' },
  { emoji: '😡', label: 'Angry' },
];

export function ReactionPicker({ messageId, onClose }: { messageId: string; onClose: () => void }) {
  const { reactToMessage } = useChatActions();

  const handleReaction = (emoji: string) => {
    reactToMessage({ messageId, emoji });
    onClose();
  };

  return (
    <div className="absolute z-10  border rounded shadow p-2 flex gap-2">
      {reactions.map(r => (
        <Button key={r.emoji} size="sm"  onClick={() => handleReaction(r.emoji)} className="bg-white">
          {r.emoji}
        </Button>
      ))}
      <Button size="sm" variant="outline" onClick={onClose} className="bg-white">Đóng</Button>
    </div>
  );
}
