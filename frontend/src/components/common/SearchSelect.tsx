import * as React from "react"
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from "@/components/ui/command"
import { Popover, PopoverContent, PopoverTrigger } from "@/components/ui/popover"
import { Button } from "@/components/ui/button"
import { Loader2, Check } from "lucide-react"
import { cn } from "@/lib/utils"

interface Option {
  value: string
  label: string
}

interface SearchSelectProps {
  value?: string | null
  onChange: (value: string) => void
  fetchOptions: (keyword: string, page?: number) => Promise<{
    content: Option[]
    page: {
      number: number
      totalPages: number
    }
  }>
  placeholder?: string
  emptyText?: string
  initialOption?: Option | null
}

export function SearchSelect({
  value,
  onChange,
  fetchOptions,
  placeholder = "Chọn...",
  emptyText = "Không tìm thấy kết quả",
  initialOption = null,
}: SearchSelectProps) {

  const [open, setOpen] = React.useState(false)
  const [keyword, setKeyword] = React.useState("")
  const [options, setOptions] = React.useState<Option[]>([])
  const [loading, setLoading] = React.useState(false)

  const [page, setPage] = React.useState(0)
  const [totalPages, setTotalPages] = React.useState(0)
  const [isLoadingMore, setIsLoadingMore] = React.useState(false)

  const listRef = React.useRef<HTMLDivElement>(null)

  const loadFirstPage = React.useCallback(async (kw: string) => {
    setLoading(true)
    try {
      const res = await fetchOptions(kw, 0)
      setOptions(res.content)
      setPage(1)
      setTotalPages(res.page.totalPages)
    } finally {
      setLoading(false)
    }
  }, [fetchOptions])

  const loadMore = async () => {
    if (page >= totalPages || isLoadingMore) return

    setIsLoadingMore(true)
    try {
      const res = await fetchOptions(keyword, page)
      setOptions(prev => [...prev, ...res.content])
      setPage(page + 1)
      setTotalPages(res.page.totalPages)
    } finally {
      setIsLoadingMore(false)
    }
  }

  // ✅ Debounce tìm kiếm
  React.useEffect(() => {
    if (!open) return

    const timer = setTimeout(() => {
      setPage(0)
      loadFirstPage(keyword)
    }, 250)

    return () => clearTimeout(timer)
  }, [keyword, open, loadFirstPage])

  // ✅ Support initial selected option
  React.useEffect(() => {
    if (value && initialOption && initialOption.value === value) {
      setOptions(prev => {
        const exists = prev.some(o => o.value === initialOption.value)
        return exists ? prev : [initialOption, ...prev]
      })
    }
  }, [value, initialOption])

  const selectedLabel =
    options.find(o => o.value === value)?.label ||
    initialOption?.label ||
    placeholder

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button variant="outline" className="w-full justify-between">
          {selectedLabel}
        </Button>
      </PopoverTrigger>

      <PopoverContent className="w-[300px] p-0">
        <Command shouldFilter={false}>
          <CommandInput placeholder="Tìm kiếm..." onValueChange={setKeyword} />

          <CommandList
            ref={listRef}
            onScroll={(e) => {
              const el = e.currentTarget
              if (el.scrollTop + el.clientHeight >= el.scrollHeight - 30) {
                loadMore()
              }
            }}
          >
            {loading && (
              <div className="p-3 flex items-center gap-2 text-sm text-muted-foreground">
                <Loader2 className="w-4 h-4 animate-spin" />
                Đang tải...
              </div>
            )}

            {!loading && options.length === 0 && (
              <CommandEmpty>{emptyText}</CommandEmpty>
            )}

            <CommandGroup>
              {options.map(opt => (
                <CommandItem
                  key={opt.value}
                  value={opt.value}
                  onSelect={() => {
                    onChange(opt.value)
                    setOpen(false)
                  }}
                >
                  <Check
                    className={cn(
                      "mr-2 h-4 w-4",
                      opt.value === value ? "opacity-100" : "opacity-0"
                    )}
                  />
                  {opt.label}
                </CommandItem>
              ))}
            </CommandGroup>

            {isLoadingMore && (
              <div className="p-2 flex items-center justify-center text-muted-foreground text-xs">
                <Loader2 className="w-4 h-4 animate-spin" />
              </div>
            )}
          </CommandList>
        </Command>
      </PopoverContent>
    </Popover>
  )
}
