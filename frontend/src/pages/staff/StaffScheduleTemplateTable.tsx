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

// ---- Localizer (date-fns) ----
const localizer = dateFnsLocalizer({
  format: (date: Date, formatStr?: string, _?: any) =>
    format(date, formatStr || "EEEE", { locale: vi }),
  parse: (value: string, formatStr: string) =>
    parse(value, formatStr, new Date(), { locale: vi }),
  startOfWeek: () => startOfWeek(new Date(), { weekStartsOn: 1 }), // Monday
  getDay: (date: Date) => getDay(date),
  locales: { vi },
});

// ---- DOW mapping (Monday-first) ----
// Thứ 2 = 0, Thứ 3 = 1, ..., Chủ nhật = 6
const DOW_MONDAY_FIRST: Record<
  "MONDAY" | "TUESDAY" | "WEDNESDAY" | "THURSDAY" | "FRIDAY" | "SATURDAY" | "SUNDAY",
  number
> = {
  MONDAY: 0,
  TUESDAY: 1,
  WEDNESDAY: 2,
  THURSDAY: 3,
  FRIDAY: 4,
  SATURDAY: 5,
  SUNDAY: 6,
};

// Ngược lại: index Monday-first -> enum
const ENUM_FROM_MONDAY_FIRST = [
  "MONDAY",
  "TUESDAY",
  "WEDNESDAY",
  "THURSDAY",
  "FRIDAY",
  "SATURDAY",
  "SUNDAY",
] as const;

// JS getDay(): 0=Sun..6=Sat. Đổi sang Monday-first (0=Mon..6=Sun)
const jsGetDayToMondayFirst = (js: number) => (js + 6) % 7;

// Helper: build Date theo local time để tránh timezone đẩy sang ngày khác
function buildDateFrom(
  base: Date, // FIXED_WEEK_START (Monday)
  offsetDays: number, // 0..6
  timeHHmm: string // "HH:mm"
) {
  const [hh, mm] = timeHHmm.split(":").map((v) => parseInt(v, 10));
  const d = new Date(
    base.getFullYear(),
    base.getMonth(),
    base.getDate() + offsetDays,
    isNaN(hh) ? 0 : hh,
    isNaN(mm) ? 0 : mm,
    0,
    0
  );
  return d;
}

export default function StaffScheduleTemplateCalendar({
  staffId,
}: {
  staffId: string;
}) {
  const [events, setEvents] = React.useState<any[]>([]);
  const [open, setOpen] = React.useState(false);

  const [formDefaults, setFormDefaults] = React.useState<any>({});
  const [editing, setEditing] = React.useState<StaffScheduleResponse | null>(null);

  // Chọn 1 Monday làm mốc hiển thị
  const FIXED_WEEK_START = startOfWeek(new Date("2024-01-01"), {
    weekStartsOn: 1,
  });

  const fetch = async () => {
    try {
      const res = await StaffScheduleTemplateApi.filter(staffId, 0, 100, null, []);
      const mapped = res.content.map((t: any) => {
        const idx = DOW_MONDAY_FIRST[(t.dayOfWeek || "MONDAY") as keyof typeof DOW_MONDAY_FIRST] ?? 0;

        return {
          id: t.id,
          title: `${t.startTime} - ${t.endTime} (${t.room?.name ?? "No room"})`,
          start: buildDateFrom(FIXED_WEEK_START, idx, t.startTime),
          end: buildDateFrom(FIXED_WEEK_START, idx, t.endTime),
          raw: t,
        };
      });

      setEvents(mapped);
    } catch (e) {
      toast.error("Không tải được lịch mẫu");
    }
  };

  React.useEffect(() => {
    fetch();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [staffId]);

  const handleSelectSlot = (slot: any) => {
    // slot.start.getDay(): 0=Sun..6=Sat
    // -> chuyển Monday-first index, rồi sang enum
    const mondayFirstIdx = jsGetDayToMondayFirst(slot.start.getDay());
    const dow = ENUM_FROM_MONDAY_FIRST[mondayFirstIdx];

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
    const bg = event.raw.active ? "#3b82f6" : "#9ca3af";
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
                  return {
                    content: res.content.map((r: any) => ({
                      value: r.id!,
                      label: r.name!,
                    })),
                    page: res.page,
                  };
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
