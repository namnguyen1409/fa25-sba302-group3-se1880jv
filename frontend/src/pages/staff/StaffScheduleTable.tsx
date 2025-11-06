import React from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { Pencil, Plus } from "lucide-react";
import { toast } from "sonner";
import * as yup from "yup";

import { type StaffScheduleResponse, type StaffScheduleRequest } from "@/api";

import type { FormFieldConfig } from "@/components/common/FormModal";
import { StaffScheduleApi } from "@/api/staff/StaffScheduleApi";
import { SearchSelect } from "@/components/common/SearchSelect";
import { RoomApi } from "@/api/room/RoomApi";

export default function StaffScheduleTable({ staffId }: { staffId: string }) {
  const tableRef = React.useRef<any>(null);
  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");
  const [selected, setSelected] = React.useState<StaffScheduleResponse | null>(
    null
  );

  const fetchData = async (
    page: number,
    size: number,
    filter?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<StaffScheduleResponse> }> => {
    const res = await StaffScheduleApi.filter(
      staffId,
      page,
      size,
      filter,
      sorts
    );
    return { data: res };
  };

  const columns = [
    { title: "Ngày", dataIndex: "date", sortable: true },
    { title: "Bắt đầu", dataIndex: "startTime", sortable: true },
    { title: "Kết thúc", dataIndex: "endTime", sortable: true },
    { title: "Phòng", dataIndex: "roomName", sortable: true },
  ];

  const formFields: FormFieldConfig[] = [
    { name: "date", label: "Ngày", type: "date", required: true },
    { name: "startTime", label: "Giờ bắt đầu", type: "time", required: true },
    { name: "endTime", label: "Giờ kết thúc", type: "time", required: true },
    {
      name: "roomId",
      label: "Phòng",
      type: "search-select",
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Chọn phòng"
          fetchOptions={async (keyword) => {
            const res = await RoomApi.search(keyword);
            return res.content.map((p) => ({
              value: String(p.id),
              label: p.name!,
            }));
          }}
          initialOption={
            selected?.room
              ? {
                  label: selected.room.name!,
                  value: selected.room.id!,
                }
              : null
          }
        />
      ),
    },
  ];

  const schema = yup.object({
    date: yup.string().required(),
    startTime: yup.string().required(),
    endTime: yup.string().required(),
    roomId: yup.string().required(),
  });

  return (
    <EntityTableWrapper<StaffScheduleResponse>
      title="Lịch làm việc thực tế"
      ref={tableRef}
      fetchData={fetchData}
      columns={columns}
      pageSize={10}
      renderRowActions={(row) => (
        <Button
          variant="outline"
          onClick={() => {
            setSelected(row);
            setFormType("update");
          }}
        >
          <Pencil className="w-4 h-4" />
        </Button>
      )}
      formModalProps={{
        open: formType !== "hidden",
        onClose: () => setFormType("hidden"),
        title: formType === "create" ? "Thêm lịch thực tế" : "Sửa lịch thực tế",
        formFields,
        defaultValues: selected || {},
        schema,
        onSubmit: async (data: StaffScheduleRequest) => {
          try {
            if (formType === "create") {
              await StaffScheduleApi.create( staffId, { ...data });
            } else {
              await StaffScheduleApi.update(selected!.id!, data);
            }
            tableRef.current?.reload();
            setFormType("hidden");
            toast.success("Lưu thành công");
          } catch {
            toast.error("Không thể lưu lịch làm việc");
          }
        },
      }}
      headerExtra={
        <Button
          onClick={() => {
            setSelected(null);
            setFormType("create");
          }}
        >
          <Plus className="w-4 h-4 mr-2" /> Thêm lịch
        </Button>
      }
    />
  );
}
