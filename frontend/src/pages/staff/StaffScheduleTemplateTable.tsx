import React from "react";
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import { startOfWeek, addDays, format, parse, getDay } from "date-fns";
import { vi } from "date-fns/locale/vi";

import { StaffScheduleTemplateApi } from "@/api/staff/StaffScheduleTemplateApi";
import { RoomApi } from "@/api/room/RoomApi";

import { toast } from "sonner";
import { FormModal } from "@/components/common/FormModal";
import { SearchSelect } from "@/components/common/SearchSelect";

import * as yup from "yup";
import "react-big-calendar/lib/css/react-big-calendar.css";
import type { StaffScheduleResponse } from "@/api";

interface FormatFn {
  (date: Date, formatStr?: string, options?: any): string;
}
interface ParseFn {
  (value: string, formatStr: string): Date;
}
interface StartOfWeekFn {
  (): Date;
}
interface GetDayFn {
  (date: Date): number;
}
interface DateFnsLocalizerOptions {
  format: FormatFn;
  parse: ParseFn;
  startOfWeek: StartOfWeekFn;
  getDay: GetDayFn;
  locales: Record<string, any>;
}

const localizer = dateFnsLocalizer(
  {
    format: (date: Date, formatStr?: string, _?: any) =>
      format(date, formatStr || "EEEE", { locale: vi }),

    parse: (value: string, formatStr: string) =>
      parse(value, formatStr, new Date(), { locale: vi }),

    startOfWeek: () => startOfWeek(new Date(), { weekStartsOn: 1 }),

    getDay: (date: Date) => getDay(date),
    locales: { vi },
  } as DateFnsLocalizerOptions
);


const DOW_INDEX: Record<string, number> = {
  MONDAY: 1,
  TUESDAY: 2,
  WEDNESDAY: 3,
  THURSDAY: 4,
  FRIDAY: 5,
  SATURDAY: 6,
  SUNDAY: 0,
};

const JS_TO_ENUM = [
  "SUNDAY",
  "MONDAY",
  "TUESDAY",
  "WEDNESDAY",
  "THURSDAY",
  "FRIDAY",
  "SATURDAY",
];

export default function StaffScheduleTemplateCalendar({
  staffId,
}: {
  staffId: string;
}) {
  const [events, setEvents] = React.useState<any[]>([]);
  const [open, setOpen] = React.useState(false);

  const [formDefaults, setFormDefaults] = React.useState<any>({});
  const [editing, setEditing] = React.useState<StaffScheduleResponse | null>(null);

  const FIXED_WEEK_START = startOfWeek(new Date("2024-01-01"), {
    weekStartsOn: 1,
  });

  const fetch = async () => {
    try {
      const res = await StaffScheduleTemplateApi.filter(
        staffId,
        0,
        100,
        null,
        []
      );

      const mapped = res.content.map((t) => {
        const jsIndex = DOW_INDEX[t.dayOfWeek || "MONDAY"] || 1;
        const day = addDays(FIXED_WEEK_START, jsIndex === 0 ? 6 : jsIndex );
        const dateStr = day.toISOString().split("T")[0];

        return {
          id: t.id,
          title: `${t.startTime} - ${t.endTime} (${t.room?.name ?? "No room"})`,
          start: new Date(`${dateStr}T${t.startTime}`),
          end: new Date(`${dateStr}T${t.endTime}`),
          raw: t,
        };
      });

      setEvents(mapped);
    } catch {
      toast.error("Không tải được lịch mẫu");
    }
  };

  React.useEffect(() => {
    fetch();
  }, [staffId]);

  const handleSelectSlot = (slot: any) => {
    const dow = JS_TO_ENUM[slot.start.getDay()];
    setEditing(null);

    setFormDefaults({
      dayOfWeek: dow,
      startTime: "",
      endTime: "",
      roomId: "",
      active: "true",
    });

    setOpen(true);
  };

  const handleSelectEvent = (event: any) => {
    const raw = event.raw;

    setEditing(raw);

    setFormDefaults({
      dayOfWeek: raw.dayOfWeek,
      startTime: raw.startTime,
      endTime: raw.endTime,
      roomId: raw.room?.id ?? "",
      active: raw.active ? "true" : "false",
    });

    setOpen(true);
  };

  const eventStyleGetter = (event: any) => {
    let bg = event.raw.active ? "#3b82f6" : "#9ca3af";
    return {
      style: {
        backgroundColor: bg,
        color: "white",
        borderRadius: "6px",
        border: "none",
        padding: "4px 6px",
        fontSize: "0.85rem",
      },
    };
  };

  return (
    <>
      <div className="bg-white p-4 rounded shadow">
        <Calendar
          localizer={localizer}
          events={events}
          defaultView="week"
          views={["week"]}
          selectable
          defaultDate={FIXED_WEEK_START}
          onRangeChange={() => {}}
          toolbar={false}
          popup={false}
          onSelectSlot={handleSelectSlot}
          onSelectEvent={handleSelectEvent}
          eventPropGetter={eventStyleGetter}
          style={{ height: 650 }}
        />
      </div>

      <FormModal
        open={open}
        onClose={() => setOpen(false)}
        title={editing ? "Sửa lịch mẫu" : "Tạo lịch mẫu"}
        defaultValues={formDefaults}
        schema={yup.object({
          dayOfWeek: yup.string().required(),
          startTime: yup.string().required(),
          endTime: yup.string().required(),
          roomId: yup.string().required(),
        })}
        formFields={[
          {
            name: "dayOfWeek",
            label: "Thứ",
            type: "select",
            required: true,
            options: [
              { label: "Thứ 2", value: "MONDAY" },
              { label: "Thứ 3", value: "TUESDAY" },
              { label: "Thứ 4", value: "WEDNESDAY" },
              { label: "Thứ 5", value: "THURSDAY" },
              { label: "Thứ 6", value: "FRIDAY" },
              { label: "Thứ 7", value: "SATURDAY" },
              { label: "Chủ nhật", value: "SUNDAY" },
            ],
          },
          {
            name: "startTime",
            label: "Giờ bắt đầu",
            type: "time",
            required: true,
          },
          {
            name: "endTime",
            label: "Giờ kết thúc",
            type: "time",
            required: true,
          },
          {
            name: "roomId",
            label: "Phòng",
            type: "search-select",
            required: true,
            renderFormItem: ({ value, onChange }) => (
              <SearchSelect
                value={value}
                onChange={onChange}
                placeholder="Chọn phòng"
                fetchOptions={async (keyword: string) => {
                  const res = await RoomApi.search(keyword);
                  return res.content
                    .filter((p) => p.id != null)
                    .map((p) => ({
                      value: p.id as string,
                      label: p.name ?? "",
                    }));
                }}
                initialOption={
                  editing?.room?.id
                    ? { value: editing.room.id, label: editing.room.name! }
                    : null
                }
              />
            ),
          },
          {
            name: "active",
            label: "Kích hoạt",
            type: "select",
            required: true,
            options: [
              { label: "Có", value: "true" },
              { label: "Không", value: "false" },
            ],
          },
        ]}
        onSubmit={async (data) => {
          try {
            if (editing) {
              await StaffScheduleTemplateApi.update(editing.id!, data);
            } else {
              await StaffScheduleTemplateApi.create(staffId, data);
            }
            setOpen(false);
            fetch();
            toast.success("Lưu thành công");
          } catch {
            toast.error("Không thể lưu");
          }
        }}
      />
    </>
  );
}
