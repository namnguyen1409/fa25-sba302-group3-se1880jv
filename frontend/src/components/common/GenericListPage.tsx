import { useEffect, useState } from "react"
import { useSearchParams } from "react-router-dom"
import {
  Pagination,
  PaginationContent,
  PaginationItem,
  PaginationLink,
  PaginationNext,
  PaginationPrevious,
} from "@/components/ui/pagination"

interface GenericListPageProps<T> {
  title: string
  pageSize?: number
  filter?: any
  fetchApi: (page: number, size: number, filter?: any) => Promise<any>
  renderItem: (item: T) => React.ReactNode
  className?: string
}

export function GenericListPage<T>({
  title,
  pageSize = 9,
  filter = {},
  fetchApi,
  renderItem,
  className = "",
}: GenericListPageProps<T>) {
  const [params, setParams] = useSearchParams()
  const [page, setPage] = useState(0)
  const [items, setItems] = useState<T[]>([])
  const [totalPages, setTotalPages] = useState(1)

  // Sync page from URL
  useEffect(() => {
    const p = Number(params.get("page") || 1) - 1
    setPage(p)
  }, [params])

  const load = async () => {
    const res = await fetchApi(page, pageSize, filter)
    setItems(res.content ?? [])
    setTotalPages(res.page?.totalPages ?? 1)
  }

  useEffect(() => {
    load()
  }, [page, JSON.stringify(filter)])

  const goToPage = (p: number) => {
    setParams({ page: (p + 1).toString() })
  }

  return (
    <div className={`w-full max-w-6xl mx-auto px-4 py-8 ${className}`}>
      <h1 className="text-3xl font-bold mb-6">{title}</h1>

      {/* GRID */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {items.map((item) => renderItem(item))}
      </div>

      {/* Pagination */}
      {totalPages > 1 && (
        <div className="mt-10 flex justify-center">
          <Pagination>
            <PaginationContent>

              <PaginationItem>
                <PaginationPrevious
                  onClick={() => page > 0 && goToPage(page - 1)}
                  className={page === 0 ? "opacity-40 pointer-events-none" : ""}
                />
              </PaginationItem>

              {Array.from({ length: totalPages }).map((_, idx) => (
                <PaginationItem key={idx}>
                  <PaginationLink
                    onClick={() => goToPage(idx)}
                    isActive={idx === page}
                  >
                    {idx + 1}
                  </PaginationLink>
                </PaginationItem>
              ))}

              <PaginationItem>
                <PaginationNext
                  onClick={() =>
                    page < totalPages - 1 && goToPage(page + 1)
                  }
                  className={
                    page >= totalPages - 1
                      ? "opacity-40 pointer-events-none"
                      : ""
                  }
                />
              </PaginationItem>

            </PaginationContent>
          </Pagination>
        </div>
      )}
    </div>
  )
}
