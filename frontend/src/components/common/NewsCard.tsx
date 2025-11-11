import { Card, CardContent } from "@/components/ui/card"
import { Link } from "react-router-dom"
import type { ContentResponse } from "@/api/models"

export function NewsCard({ item }: { item: ContentResponse }) {
  return (
    <Card className="h-full overflow-hidden rounded-xl border bg-white shadow-sm hover:shadow-md transition-all">
      
      {item.coverImageUrl && (
        <Link to={`/news/${item.slug || item.id}`}>
          <img
            src={item.coverImageUrl}
            alt={item.title}
            className="h-48 w-full object-cover transition-transform duration-300 hover:scale-[1.03]"
          />
        </Link>
      )}

      <CardContent className="p-5 flex flex-col h-full">

        <Link to={`/news/${item.slug || item.id}`}>
          <h2 className="font-semibold text-lg text-gray-900 line-clamp-2 hover:text-blue-600 transition-colors">
            {item.title}
          </h2>
        </Link>

        {item.excerpt && (
          <p className="text-sm text-gray-600 line-clamp-2 mt-1">
            {item.excerpt}
          </p>
        )}

        <div className="flex justify-between items-center mt-4 text-xs text-gray-500">
          <Link
            to={`/staffs/${item.author?.id}`}
            className="hover:underline hover:text-blue-600"
          >
            By <span className="font-medium">{item.author?.fullName ?? "Unknown"}</span>
          </Link>

          <span>
            {item.publishedAt
              ? new Date(item.publishedAt).toLocaleDateString("vi-VN")
              : ""}
          </span>
        </div>

        {item.tags?.length > 0 && (
          <div className="mt-3 flex flex-wrap gap-2">
            {item?.tags?.map((t: string, index) => (
              <Link
                key={index}
                to={`/tag/${encodeURIComponent(t)}`}
                className="text-xs px-2 py-1 rounded bg-gray-100 text-gray-700 hover:bg-gray-200"
              >
                #{t}
              </Link>
            ))}
          </div>
        )}

        <div className="mt-auto pt-4">
          <Link
            to={`/news/${item.slug || item.id}`}
            className="text-sm text-blue-600 font-medium hover:underline"
          >
            Đọc thêm →
          </Link>
        </div>

      </CardContent>
    </Card>
  )
}
