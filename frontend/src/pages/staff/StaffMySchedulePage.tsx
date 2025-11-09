// StaffMySchedulePage.tsx (UPDATED)
import React from "react";
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import { format, parse, startOfWeek, getDay, addMonths } from "date-fns";
import { vi } from "date-fns/locale/vi";

import { StaffScheduleApi } from "@/api/staff/StaffScheduleApi";
import { toast } from "sonner";

import "react-big-calendar/lib/css/react-big-calendar.css";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";

const locales = { vi };
const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek: () => startOfWeek(new Date(), { locale: vi }),
  getDay,
  locales,
});

export default function StaffMySchedulePage() {
  const [events, setEvents] = React.useState<any[]>([]);
  const [openDetail, setOpenDetail] = React.useState(false);
  const [detailData, setDetailData] = React.useState<any>(null);

  const fmt = (d: Date) => format(d, "yyyy-MM-dd");

  const fetch = async () => {
    try {
      const from = fmt(new Date());
      const to = fmt(addMonths(new Date(), 2));

      // ✅ Backend tự lấy từ JWT
      const res = await StaffScheduleApi.getMyRange(from, to);

      setEvents(
        res.map((s: any) => {
          return {
            id: s.id,
            title: `${s.startTime} - ${s.endTime} (${
              s.room?.name || "No Room"
            })${s.note ? " | " + s.note : ""}`,
            start: new Date(`${s.date}T${s.startTime}`),
            end: new Date(`${s.date}T${s.endTime}`),
            raw: s,
          };
        })
      );
    } catch {
      toast.error("Không tải được lịch");
    }
  };

  React.useEffect(() => {
    fetch();
  }, []);

  const eventStyleGetter = (event: any) => {
    let backgroundColor = "#10b981";
    if (event.raw.status === "OFF") backgroundColor = "#ef4444";
    else if (event.raw.status === "CANCELLED") backgroundColor = "#fbbf24";
    else if (event.raw.status === "CHANGED") backgroundColor = "#3b82f6";

    return {
      style: {
        backgroundColor,
        borderRadius: "6px",
        color: "white",
        border: "none",
        padding: "4px 6px",
        fontSize: "0.85rem",
      },
    };
  };

  const scheduleDetailConfig = [
    {
      groupTitle: "Thông tin lịch",
      fields: [
        { label: "Ngày", name: "date", type: "date" },
        { label: "Giờ bắt đầu", name: "startTime", type: "text" },
        { label: "Giờ kết thúc", name: "endTime", type: "text" },
        {
          label: "Phòng",
          name: "room.name",
          type: "text",
        },
        {
          label: "Trạng thái",
          name: "status",
          type: "enum",
          map: {
            AVAILABLE: "Có mặt",
            CHANGED: "Thay đổi",
            OFF: "Nghỉ",
            CANCELLED: "Đã hủy",
          },
        },
        { label: "Ghi chú", name: "note", type: "text" },
      ],
    },
  ];

  return (
    <div className="space-y-4">
      <h2 className="text-xl font-semibold">Lịch làm việc của tôi</h2>

      <div className=" rounded-md p-4 shadow">
        <Calendar
          localizer={localizer}
          events={events}
          startAccessor="start"
          endAccessor="end"
          views={["month", "week", "day"]}
          defaultView="week"
          popup
          style={{ height: 800 }}
          selectable={false}
          onSelectSlot={() => {}}
          onSelectEvent={(event) => {
            setDetailData(event.raw);
            setOpenDetail(true);
          }}
          eventPropGetter={eventStyleGetter}
        />
      </div>

      <ViewDetailDialog
        open={openDetail}
        onClose={setOpenDetail}
        title="Chi tiết lịch làm việc"
        data={detailData}
        config={scheduleDetailConfig}
        columns={2}
        width="max-w-xl"
      />
    </div>
  );
}
