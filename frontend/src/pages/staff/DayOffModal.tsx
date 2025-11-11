// modals/DayOffModal.tsx
import React, { useCallback } from "react";
import { StaffScheduleApi } from "@/api/staff/StaffScheduleApi";
import { toast } from "sonner";
import * as yup from "yup";
import { FormModal } from "@/components/common/FormModal";
import type { FormFieldConfig } from "@/components/common/FormModal";
import { SearchSelect } from "@/components/common/SearchSelect";
import { RoomApi } from "@/api/room/RoomApi";

const schema = yup.object({
  date: yup.string().required(),
  startTime: yup.string().required(),
  endTime: yup.string().required(),
  roomId: yup.string().required(),
  reason: yup.string().nullable(),
});

export default function DayOffModal({ open, onClose, staffId }: any) {
  const renderRoomSelect = useCallback(
    ({ value, onChange } : { value: string; onChange: (value: string) => void }) => (
      <SearchSelect
        value={value}
        onChange={onChange}
        fetchOptions={async (keyword) => {
          const res = await RoomApi.search(keyword);
          return res.content.map((r: any) => ({
            value: r.id,
            label: r.name,
          }));
        }}
      />
    ),
    []
  );

  const formFields: FormFieldConfig[] = [
    { name: "date", label: "Ngày nghỉ", type: "date", required: true },
    { name: "startTime", label: "Từ", type: "time", required: true },
    { name: "endTime", label: "Đến", type: "time", required: true },
    {
      name: "roomId",
      label: "Phòng",
      type: "search-select",
      required: true,
      renderFormItem: renderRoomSelect,
    },
    { name: "reason", label: "Lý do", type: "textarea" },
  ];

  return (
    <FormModal
      open={open}
      onClose={onClose}
      title="Tạo Day-Off"
      formFields={formFields}
      schema={schema}
      onSubmit={async (data) => {
        try {
          await StaffScheduleApi.dayOff({ ...data, staffId });
          toast.success("Đã tạo Day-Off");
          onClose();
        } catch {
          toast.error("Không thể tạo Day-Off");
        }
      }}
    />
  );
}
