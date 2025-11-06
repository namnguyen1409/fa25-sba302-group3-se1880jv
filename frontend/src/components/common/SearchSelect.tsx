import * as React from "react"
import {
  Command, CommandEmpty, CommandGroup,
  CommandInput, CommandItem, CommandList,
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
  fetchOptions: (keyword: string) => Promise<Option[]>
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
  const [options, setOptions] = React.useState<Option[]>([])
  const [loading, setLoading] = React.useState(false)

  React.useEffect(() => {
    if (value && initialOption && initialOption.value === value) {
      setOptions(prev => {
        const exists = prev.some(o => o.value === initialOption.value)
        return exists ? prev : [initialOption, ...prev]
      })
    }
  }, [value, initialOption])

  const handleSearch = async (keyword: string) => {
    setLoading(true)
    const data = await fetchOptions(keyword)
    let merged = data
    if (initialOption && initialOption.value === value) {
      const exists = data.some(o => o.value === initialOption.value)
      if (!exists) merged = [initialOption, ...data]
    }

    setOptions(merged)
    setLoading(false)
  }

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
          <CommandInput placeholder="Tìm kiếm..." onValueChange={handleSearch} />

          <CommandList>
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
          </CommandList>
        </Command>
      </PopoverContent>
    </Popover>
  )
}
