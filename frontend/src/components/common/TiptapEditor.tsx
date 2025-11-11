import React, { useCallback, useEffect } from "react";
import { EditorContent, useEditor } from "@tiptap/react";
import StarterKit from "@tiptap/starter-kit";
import Underline from "@tiptap/extension-underline";
import Placeholder from "@tiptap/extension-placeholder";
import Image from "@tiptap/extension-image";
import Link from "@tiptap/extension-link";
// import CodeBlockLowlight from "@tiptap/extension-code-block-lowlight";
// import { lowlight } from 'lowlight/lib/core'
import { createLowlight } from 'lowlight'
import { Button } from "@/components/ui/button";
import {
  Paperclip,
  ImageIcon,
  Bold,
  Italic,
  List,
  ListOrdered,
  Quote,
  Code,
} from "lucide-react";

export interface TiptapCmsEditorProps {
  value: any;
  onChange: (json: any) => void;

  /** callback upload ảnh → trả về URL */
  onUploadImage: (file: File, contentId: string) => Promise<string>;

  /** callback upload file → trả về URL */
  onUploadFile?: (file: File, contentId: string) => Promise<string>;

  contentId: string;
}

export function TiptapCmsEditor({
  value,
  onChange,
  onUploadImage,
  onUploadFile,
  contentId,
}: TiptapCmsEditorProps) {
//   const lowlight = createLowlight();
  const editor = useEditor({
    content: value,
    extensions: [
      StarterKit,
      Underline,
      Placeholder.configure({
        placeholder: "Viết nội dung bài viết…",
      }),
      Image.configure({
        HTMLAttributes: { class: "rounded-md shadow border mb-2" },
      }),
      Link.configure({
        openOnClick: false,
      }),
    //   CodeBlockLowlight.configure({
    //     lowlight,
    //     HTMLAttributes: {
    //       class: "bg-neutral-100 dark:bg-neutral-900 p-3 rounded-md text-sm",
    //     },
    //   }),
    ],
    onUpdate: ({ editor }) => {
      const json = editor.getJSON();
      onChange(json);
    },
  });

  /** Upload ảnh */
  const handleImageUpload = async (e: any) => {
    const file = e.target.files?.[0];
    if (!file || !editor) return;

    const url = await onUploadImage(file, contentId);

    editor.chain().focus().setImage({ src: url }).run();
  };

  /** Upload file generic */
  const handleFileUpload = async (e: any) => {
    if (!onUploadFile) return;

    const file = e.target.files?.[0];
    if (!file || !editor) return;

    const url = await onUploadFile(file, contentId);

    editor.chain().focus().setLink({ href: url }).run();
  };

  if (!editor) return null;

  return (
    <div className="w-full">
      {/* Toolbar */}
      <div className="flex flex-wrap gap-2 mb-3">
        <Button
          type="button"
          variant="outline"
          size="sm"
          onClick={() => editor.chain().focus().toggleBold().run()}
          className={
            editor.isActive("bold") ? "bg-neutral-200 dark:bg-neutral-700" : ""
          }
        >
          <Bold className="w-4 h-4" />
        </Button>

        <Button
          type="button"
          variant="outline"
          size="sm"
          onClick={() => editor.chain().focus().toggleItalic().run()}
          className={
            editor.isActive("italic")
              ? "bg-neutral-200 dark:bg-neutral-700"
              : ""
          }
        >
          <Italic className="w-4 h-4" />
        </Button>

        <Button
          type="button"
          variant="outline"
          size="sm"
          onClick={() => editor.chain().focus().toggleBulletList().run()}
          className={
            editor.isActive("bulletList")
              ? "bg-neutral-200 dark:bg-neutral-700"
              : ""
          }
        >
          <List className="w-4 h-4" />
        </Button>

        <Button
          type="button"
          variant="outline"
          size="sm"
          onClick={() => editor.chain().focus().toggleOrderedList().run()}
          className={
            editor.isActive("orderedList")
              ? "bg-neutral-200 dark:bg-neutral-700"
              : ""
          }
        >
          <ListOrdered className="w-4 h-4" />
        </Button>

        <Button
          type="button"
          variant="outline"
          size="sm"
          onClick={() => editor.chain().focus().toggleBlockquote().run()}
          className={
            editor.isActive("blockquote")
              ? "bg-neutral-200 dark:bg-neutral-700"
              : ""
          }
        >
          <Quote className="w-4 h-4" />
        </Button>

        <Button
          type="button"
          variant="outline"
          size="sm"
          onClick={() => editor.chain().focus().toggleCodeBlock().run()}
          className={
            editor.isActive("codeBlock")
              ? "bg-neutral-200 dark:bg-neutral-700"
              : ""
          }
        >
          <Code className="w-4 h-4" />
        </Button>

        {/* Upload Image */}
        <Button variant="outline" size="sm" type="button">
          <label className="cursor-pointer flex items-center gap-2">
            <ImageIcon className="w-4 h-4" />
            <span>Ảnh</span>
            <input
              type="file"
              className="hidden"
              accept="image/*"
              onChange={handleImageUpload}
            />
          </label>
        </Button>

        {/* Upload File / PDF */}
        {onUploadFile && (
          <Button variant="outline" size="sm" type="button">
            <label className="cursor-pointer flex items-center gap-2">
              <Paperclip className="w-4 h-4" />
              <span>File</span>
              <input
                type="file"
                className="hidden"
                onChange={handleFileUpload}
              />
            </label>
          </Button>
        )}
      </div>

      {/* Editor */}
      <div className="border rounded-lg p-4 min-h-[320px] prose max-w-none dark:prose-invert">
        <EditorContent editor={editor} />
      </div>
    </div>
  );
}

export default TiptapCmsEditor;