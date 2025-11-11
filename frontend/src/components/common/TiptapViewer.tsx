"use client";

import { EditorContent, useEditor } from "@tiptap/react";
import StarterKit from "@tiptap/starter-kit";
import Highlight from "@tiptap/extension-highlight";
import Typography from "@tiptap/extension-typography";
import TextAlign from "@tiptap/extension-text-align";
import Image from "@tiptap/extension-image";
import Link from "@tiptap/extension-link";

export function TiptapViewer({ content }: { content: any }) {
  const editor = useEditor({
    editable: false,
    content: JSON.parse(content),
    extensions: [
      StarterKit,
      Highlight,
      Typography,
      Image,
      Link.configure({ openOnClick: true }),
      TextAlign.configure({ types: ["heading", "paragraph"] }),
    ],
  });

  if (!editor) return null;

  return (
    <div className="prose max-w-none dark:prose-invert prose-img:rounded-xl prose-img:shadow 
                    prose-headings:font-semibold prose-h2:mt-8 prose-p:leading-relaxed">
      <EditorContent editor={editor} />
    </div>
  );
}
