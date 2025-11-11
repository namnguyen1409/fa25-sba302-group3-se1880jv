// StaffScheduleCalendar.tsx
import React from "react";
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import { format, parse, startOfWeek, getDay, addMonths } from "date-fns";
import { vi } from "date-fns/locale/vi";

import { StaffScheduleApi } from "@/api/staff/StaffScheduleApi";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { CalendarPlus } from "lucide-react";

import "react-big-calendar/lib/css/react-big-calendar.css";
import { FormModal } from "@/components/common/FormModal";
import * as yup from "yup";
import { SearchSelect } from "@/components/common/SearchSelect";
import { RoomApi } from "@/api/room/RoomApi";
import type { StaffResponse } from "@/api";
// import { type StaffResponse } from "../@/api/models/StaffResponse";

const locales = { vi };
const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek: () => startOfWeek(new Date(), { locale: vi }),
  getDay,
  locales,
});

export default function StaffScheduleCalendar({
  staffId,
  staff,
}: {
  staffId: string;
  staff: StaffResponse;
}) {
  const [events, setEvents] = React.useState<any[]>([]);
  const [editModalOpen, setEditModalOpen] = React.useState(false);
  const [selectedEvent, setSelectedEvent] = React.useState<any>(null);
  const [selectedDate, setSelectedDate] = React.useState<Date | null>(null);

  const fmt = (d: Date) => format(d, "yyyy-MM-dd");

  const fetch = async () => {
    try {
      const joined = staff.joinedDate ? new Date(staff.joinedDate) : new Date();
      const from = fmt(joined);
      const to = fmt(addMonths(new Date(), 2));
      const res = await StaffScheduleApi.getRange(staffId, from, to);

      setEvents(
        res.map((s) => {
          const start = new Date(`${s.date}T${s.startTime}`);
          const end = new Date(`${s.date}T${s.endTime}`);

          return {
            id: s.id,
            title: `${s.startTime} - ${s.endTime} (${
              s.room?.name || "No Room"
            }) | ${s.note}
              
            `,
            start,
            end,
            status: s.status,
            raw: s,
          };
        })
      );
    } catch {
      toast.error("Không tải được lịch");
    }
  };

  const handleGenerate = async () => {
    try {
      await StaffScheduleApi.generate(staffId, { daysAhead: 30 });
      toast.success("Đã generate lịch 30 ngày tới");
      fetch();
    } catch {
      toast.error("Không thể generate lịch");
    }
  };

  React.useEffect(() => {
    fetch();
  }, [staffId]);

  const eventStyleGetter = (event: any) => {
    let backgroundColor = "#10b981"; // green
    if (event.status === "OFF") backgroundColor = "#ef4444"; // red
    else if (event.status === "CANCELLED")
      backgroundColor = "#fbbf24"; // yellow
    else if (event.status === "CHANGED") backgroundColor = "#3b82f6"; // blue

    return {
      style: {
        backgroundColor,
        borderRadius: "6px",
        opacity: 0.9,
        color: "white",
        border: "0px",
        padding: "4px 6px",
        fontSize: "0.85rem",
      },
    };
  };

  const defaultValues = React.useMemo(() => {
    // UPDATE
    if (selectedEvent) {
      return {
        date: selectedEvent.date,
        startTime: selectedEvent.startTime,
        endTime: selectedEvent.endTime,
        roomId: selectedEvent.room?.id || null,
        status: selectedEvent.status || "CHANGED",
        note: selectedEvent.note || "",
      };
    }

    // CREATE
    if (selectedDate) {
      const d = new Date(selectedDate);
      d.setMinutes(d.getMinutes() - d.getTimezoneOffset()); // fix timezone
      return {
        date: d.toISOString().split("T")[0],
      };
    }

    return {};
  }, [selectedEvent, selectedDate]);

  return (
    <div className="space-y-4">
      {/* ACTIONS */}
      <div className="flex gap-3">
        <Button onClick={handleGenerate}>
          <CalendarPlus className="w-4 h-4 mr-2" />
          Generate 30 ngày
        </Button>
      </div>

      {/* CALENDAR */}
      <div className="bg-white rounded-md p-4 shadow">
        <Calendar
          localizer={localizer}
          events={events}
          startAccessor="start"
          endAccessor="end"
          selectable
          onSelectSlot={(slotInfo: any) => {
            setSelectedDate(slotInfo.start);
            setSelectedEvent(null);
            setEditModalOpen(true);
          }}
          onSelectEvent={(event: any) => {
            setSelectedEvent(event.raw);
            setSelectedDate(null);
            setEditModalOpen(true);
          }}
          eventPropGetter={eventStyleGetter}
          views={["month", "week", "day"]}
          defaultView="week"
          popup
          style={{ height: 800 }}
        />
      </div>

      <FormModal
        open={editModalOpen}
        onClose={() => setEditModalOpen(false)}
        title={selectedEvent ? "Chỉnh sửa lịch" : "Tạo lịch mới"}
        formFields={[
          { name: "date", label: "Ngày", type: "date", required: true },
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
                fetchOptions={async (keyword) => {
                  const res = await RoomApi.search(keyword);
                  return {
                    content: res.content.map((r) => ({
                      value: r.id!,
                      label: r.name!,
                    })),
                    page: res.page,
                  };
                }}
                initialOption={
                  selectedEvent?.room
                    ? {
                        value: selectedEvent.room.id!,
                        label: selectedEvent.room.name!,
                      }
                    : null
                }
              />
            ),
          },
          {
            name: "status",
            label: "Trạng thái",
            type: "select",
            options: [
              { label: "Thay đổi (CHANGED)", value: "CHANGED" },
              { label: "Nghỉ (OFF)", value: "OFF" },
              { label: "Hủy (CANCELLED)", value: "CANCELLED" },
            ],
            required: true,
            defaultValue:
              selectedEvent?.status === "AVAILABLE"
                ? "CHANGED"
                : selectedEvent?.status || "CHANGED",
          },

          { name: "note", label: "Ghi chú", type: "textarea" },
        ]}
        schema={yup.object({
          date: yup.string().required(),
          startTime: yup.string().required(),
          endTime: yup.string().required(),
          roomId: yup.string().required("Chọn phòng là bắt buộc"),
          note: yup.string().when("status", {
            is: (val: string) => val === "OFF" || val === "CANCELLED",
            then: (s) => s.required("Phải ghi lý do khi nghỉ hoặc hủy"),
            otherwise: (s) => s.notRequired(),
          }),
        })}
        defaultValues={defaultValues}
        onSubmit={async (data) => {
          try {
            if (selectedEvent) {
              await StaffScheduleApi.update(selectedEvent.id, data);
              toast.success("Cập nhật lịch thành công");
            } else {
              await StaffScheduleApi.create(staffId, data);
              toast.success("Tạo lịch thành công");
            }
            setEditModalOpen(false);
            fetch();
          } catch {
            toast.error("Không thể lưu");
          }
        }}
      />
    </div>
  );
}
