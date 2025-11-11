import { useEffect, useState, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";

import { ContentApi } from "@/api/cms/ContentApi";
import { SimpleEditor } from "@/components/tiptap-templates/simple/simple-editor";
import { FileApi } from "@/api/file/FileApi";
import { AiPromptModal } from "@/components/common/AIPromptModal";

export default function ContentEditPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [autoSaved, setAutoSaved] = useState(false);

  const autoSaveTimer = useRef<ReturnType<typeof setTimeout> | null>(null);

  const [showAIPrompt, setShowAIPrompt] = useState(false);

  const handleAIClick = () => setShowAIPrompt(true);

  const editorRef = useRef<any>(null);

  const [form, setForm] = useState<any>({
    title: "",
    slug: "",
    excerpt: "",
    coverImageUrl: "",
    tags: [],
    status: "DRAFT",
    body: {},
  });

  useEffect(() => {
    if (!id) return;
    ContentApi.getById(id)
      .then((res) => {
        setForm({
          title: res.title,
          slug: res.slug,
          excerpt: res.excerpt,
          coverImageUrl: res.coverImageUrl,
          tags: res.tags || [],
          status: res.status,
          body: res.body,
        });
      })
      .catch(() => toast.error("Không tải được nội dung"))
      .finally(() => setLoading(false));
  }, [id]);

  const generateSlug = (text: string) =>
    text
      .toLowerCase()
      .trim()
      .replace(/[^\w\s-]/g, "")
      .replace(/\s+/g, "-");

  const scheduleAutoSave = () => {
    if (autoSaveTimer.current) clearTimeout(autoSaveTimer.current);

    autoSaveTimer.current = setTimeout(() => {
      handleSave(true);
    }, 15000);
  };

  const handleChange = (field: string, value: any) => {
    setForm((prev: any) => ({ ...prev, [field]: value }));
    scheduleAutoSave();
  };

  const handleUploadImage = async (file: File) => {
    try {
      const url = await FileApi.upload({
        file,
        entityType: "CMS_CONTENT_INLINE_IMAGE",
        entityId: id!,
      });
      return url;
    } catch (err) {
      toast.error("Lỗi khi tải ảnh lên");
      throw err;
    }
  };

  const uploadCoverImage = async (file: File) => {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("entityType", "CMS_CONTENT_COVER");
    formData.append("entityId", id!);

    const res = await FileApi.upload({
      file,
      entityType: "CMS_CONTENT_COVER",
      entityId: id!,
    });

    handleChange("coverImageUrl", res);
  };

  const handleSave = async (auto = false) => {
    try {
      setSaving(!auto);

      await ContentApi.update(id!, form);

      if (auto) {
        setAutoSaved(true);
        setTimeout(() => setAutoSaved(false), 2000);
      } else {
        toast.success("Đã lưu");
      }
    } catch {
      toast.error("Lỗi khi lưu nội dung");
    } finally {
      setSaving(false);
    }
  };

  if (loading)
    return <div className="p-6 animate-pulse">Đang tải nội dung...</div>;

  return (
    <div className="grid grid-cols-4 gap-6 p-6">
      <div className="col-span-3 space-y-4">
        <Input
          className="text-3xl font-bold"
          placeholder="Tiêu đề bài viết"
          value={form.title}
          onChange={(e) => {
            handleChange("title", e.target.value);
            if (!form.slug) handleChange("slug", generateSlug(e.target.value));
          }}
        />

        <Input
          placeholder="slug-bai-viet"
          value={form.slug}
          onChange={(e) => handleChange("slug", e.target.value)}
        />

        <Textarea
          placeholder="Tóm tắt ngắn..."
          value={form.excerpt}
          onChange={(e) => handleChange("excerpt", e.target.value)}
        />

        <SimpleEditor
          value={JSON.parse(form.body || "{}")}
          onChange={(content) => handleChange("body", JSON.stringify(content))}
          onUploadImage={handleUploadImage}
          className="border-2 p-3 rounded-md"
          onAIClick={(editor) => {
            editorRef.current = editor;
            handleAIClick();
          }}
        />
      </div>

      <div className="col-span-1 space-y-6">
        {/* STATUS */}
        <div className="border rounded-md p-4">
          <div className="font-semibold mb-2">Trạng thái</div>
          <Badge>{form.status}</Badge>

          <div className="mt-3 flex gap-2">
            <Button
              onClick={() =>
                ContentApi.publish(id!).then(() =>
                  handleChange("status", "PUBLISHED")
                )
              }
            >
              Publish
            </Button>

            <Button
              variant="outline"
              onClick={() =>
                ContentApi.archive(id!).then(() =>
                  handleChange("status", "ARCHIVED")
                )
              }
            >
              Archive
            </Button>
          </div>
        </div>

        {/* COVER IMAGE */}
        <div className="border rounded-md p-4 space-y-2">
          <div className="font-semibold">Ảnh bìa</div>

          {form.coverImageUrl && (
            <img
              src={form.coverImageUrl}
              className="w-full rounded-md border"
            />
          )}

          <Input
            type="file"
            accept="image/*"
            onChange={(e) => uploadCoverImage(e.target.files![0])}
          />
        </div>

        {/* TAGS */}
        <div className="border rounded-md p-4 space-y-2">
          <div className="font-semibold">Tags</div>

          <Input
            placeholder="Nhập tag cách nhau bởi dấu phẩy"
            value={form.tags.join(", ")}
            onChange={(e) =>
              handleChange(
                "tags",
                e.target.value.split(",").map((t) => t.trim())
              )
            }
          />
        </div>

        <div className="text-sm text-muted-foreground">
          {saving && "Đang lưu..."}
          {autoSaved && "Đã tự động lưu"}
        </div>
        <Button onClick={() => handleSave(false)} className="mr-2">
          Lưu
        </Button>

        <Button
          variant="destructive"
          onClick={async () => {
            await ContentApi.delete(id!);
            toast.success("Đã xóa");
            navigate("/admin/cms");
          }}
        >
          Xóa bài viết
        </Button>
      </div>

      <AiPromptModal
        open={showAIPrompt}
        onClose={() => setShowAIPrompt(false)}
        onInsert={(content) => {
          const editor = editorRef.current;
          if (!editor) return;

          if (typeof content === "string") {
            editor.commands.insertContent(content);
          } else {
            editor.commands.insertContent(content);
          }
        }}
      />
    </div>
  );
}
