import * as React from "react";
import {
  Command,
  CommandEmpty,
  CommandInput,
  CommandItem,
  CommandGroup,
  CommandList,
} from "@/components/ui/command";
import {
  Popover,
  PopoverTrigger,
  PopoverContent,
} from "@/components/ui/popover";
import { Button } from "@/components/ui/button";
import { Loader2, Check, X } from "lucide-react";
import { cn } from "@/lib/utils";

interface Option {
  value: string;
  label: string;
}

interface MultiSearchSelectProps {
  value?: Option[];
  onChange: (value: Option[]) => void;
  fetchOptions: (keyword: string) => Promise<Option[]>;
  placeholder?: string;
  emptyText?: string;
}

export function MultiSearchSelect({
  value = [],
  onChange,
  fetchOptions,
  placeholder = "Chọn...",
  emptyText = "Không tìm thấy kết quả",
}: MultiSearchSelectProps) {
  const [open, setOpen] = React.useState(false);
  const [options, setOptions] = React.useState<Option[]>([]);
  const [loading, setLoading] = React.useState(false);

  const handleSearch = async (keyword: string) => {
    setLoading(true);
    const data = await fetchOptions(keyword);
    setOptions(data);
    setLoading(false);
  };

  const selectedLabels = value.map((v) => v.label).join(", ") || placeholder;

  return (
    <div className="space-y-2">
      {/* Button */}
      <Popover open={open} onOpenChange={setOpen}>
        <PopoverTrigger asChild>
          <Button variant="outline" className="w-full justify-between">
            {selectedLabels}
          </Button>
        </PopoverTrigger>

        <PopoverContent className="w-[350px] p-0">
          <Command shouldFilter={false}>
            <CommandInput
              placeholder="Tìm kiếm..."
              onValueChange={handleSearch}
            />

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
                {options.map((opt) => {
                  const isSelected = value.some(
                    (item) => item.value === opt.value
                  );

                  return (
                    <CommandItem
                      key={opt.value}
                      onSelect={() => {
                        if (isSelected) {
                          onChange(value.filter((v) => v.value !== opt.value));
                        } else {
                          onChange([...value, opt]);
                        }
                      }}
                    >
                      <Check
                        className={cn(
                          "mr-2 h-4 w-4",
                          isSelected ? "opacity-100" : "opacity-0"
                        )}
                      />
                      {opt.label}
                    </CommandItem>
                  );
                })}
              </CommandGroup>
            </CommandList>
          </Command>
        </PopoverContent>
      </Popover>

      {/* Selected Tags */}
      <div className="flex flex-wrap gap-2">
        {value.map((item) => (
          <span
            key={item.value}
            className="px-2 py-1 bg-muted text-sm rounded flex items-center gap-1 border"
          >
            {item.label}
            <X
              className="w-3 h-3 cursor-pointer"
              onClick={() =>
                onChange(value.filter((v) => v.value !== item.value))
              }
            />
          </span>
        ))}

        {value.length > 0 && (
          <Button size="sm" variant="outline" onClick={() => onChange([])}>
            Clear
          </Button>
        )}
      </div>
    </div>
  );
}
