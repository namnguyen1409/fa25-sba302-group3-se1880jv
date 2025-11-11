"use client";

import * as React from "react";
import { addDays } from "date-fns";
import type { DateRange } from "react-day-picker";
import { Calendar } from "@/components/ui/calendar";
import { Popover, PopoverTrigger, PopoverContent } from "@/components/ui/popover";
import { Button } from "@/components/ui/button";
import { cn } from "@/lib/utils";
import { CalendarIcon } from "lucide-react";

interface Props {
  value?: { from: string; to: string };
  onChange: (v: { from: string; to: string }) => void;
  className?: string;
}

export function DateRangePicker({ value, onChange, className }: Props) {
  const initialRange: DateRange | undefined = value
    ? {
        from: new Date(value.from),
        to: new Date(value.to),
      }
    : {
        from: new Date(),
        to: addDays(new Date(), 6),
      };

  const [date, setDate] = React.useState<DateRange | undefined>(initialRange);

  const handleSelect = (range: DateRange | undefined) => {
    setDate(range);
    if (range?.from && range.to) {
      onChange({
        from: range.from.toISOString().slice(0, 10),
        to: range.to.toISOString().slice(0, 10),
      });
    }
  };

  return (
    <Popover>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          className={cn(
            "justify-start text-left font-normal w-full",
            !date && "text-muted-foreground",
            className
          )}
        >
          <CalendarIcon className="mr-2 h-4 w-4" />
          {date?.from ? (
            date.to ? (
              <>
                {date.from.toLocaleDateString()} – {date.to.toLocaleDateString()}
              </>
            ) : (
              date.from.toLocaleDateString()
            )
          ) : (
            <span>Chọn khoảng thời gian</span>
          )}
        </Button>
      </PopoverTrigger>

      <PopoverContent className="w-auto p-0" align="start">
        <Calendar
          mode="range"
          defaultMonth={date?.from}
          selected={date}
          onSelect={handleSelect}
          numberOfMonths={2}
        />
      </PopoverContent>
    </Popover>
  );
}
