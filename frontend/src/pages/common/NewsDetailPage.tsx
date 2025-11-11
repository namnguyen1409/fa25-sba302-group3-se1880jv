import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { ContentApi } from "@/api/cms/ContentApi";
import { Badge } from "@/components/ui/badge";
import { TiptapViewer } from "@/components/common/TiptapViewer";

export default function NewsDetailPage() {
  const { slugOrId } = useParams();
  const [item, setItem] = useState<any>(null);
  const [loading, setLoading] = useState(true);

  const load = async () => {
    try {
      const res = await ContentApi.getBySlug(slugOrId!);
      setItem(res);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, [slugOrId]);

  if (loading)
    return (
      <div className="flex justify-center items-center py-20 text-gray-500">
        Đang tải bài viết...
      </div>
    );

  if (!item)
    return (
      <div className="flex flex-col items-center py-20 text-gray-500">
        <p className="text-lg">Bài viết không tồn tại</p>
        <Link className="mt-4 text-blue-600" to="/news">
          Quay lại trang tin tức →
        </Link>
      </div>
    );

  return (
    <div className="max-w-4xl mx-auto px-4 py-10">

      {/* Cover image */}
      {item.coverImageUrl && (
        <img
          src={item.coverImageUrl}
          alt={item.title}
          className="w-full max-h-[420px] object-cover rounded-xl shadow mb-6"
        />
      )}

      {/* Title */}
      <h1 className="text-3xl md:text-4xl font-bold text-gray-900 mb-3">
        {item.title}
      </h1>

      {/* Excerpt */}
      {item.excerpt && (
        <p className="text-gray-600 text-lg mb-4">{item.excerpt}</p>
      )}

      {/* Meta info */}
      <div className="flex items-center justify-between text-sm text-gray-500 mb-8">
        <div>
          Tác giả:{" "}
          <Link
            to={`/staffs/${item.author?.id}`}
            className="font-medium text-blue-600 hover:underline"
          >
            {item.author?.fullName ?? "Unknown"}
          </Link>
        </div>
        <div>
          {item.publishedAt
            ? new Date(item.publishedAt).toLocaleDateString("vi-VN")
            : ""}
        </div>
      </div>

      {/* Body Content (TipTap Viewer) */}
      <TiptapViewer content={item.body} />

      {/* Tags */}
      {item.tags?.length > 0 && (
        <div className="mt-10 flex flex-wrap gap-2">
          {item.tags.map((tag: string, idx: number) => (
            <Link key={idx} to={`/tag/${tag}`}>
              <Badge variant="secondary" className="cursor-pointer">
                #{tag}
              </Badge>
            </Link>
          ))}
        </div>
      )}

      {/* Back button */}
      <div className="mt-10">
        <Link to="/news" className="text-blue-600 hover:underline">
          ← Quay lại danh sách
        </Link>
      </div>
    </div>
  );
}
