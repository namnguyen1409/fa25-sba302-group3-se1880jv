import React from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import {
  type StaffScheduleTemplateResponse,
  type StaffScheduleTemplateRequest,
} from "@/api";
import { Button } from "@/components/ui/button";
import { Pencil, Plus } from "lucide-react";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import { toast } from "sonner";
import { SearchSelect } from "@/components/common/SearchSelect";
import { RoomApi } from "@/api/room/RoomApi";
import { StaffScheduleTemplateApi } from "@/api/staff/StaffScheduleTemplateApi";
import { useEnumTranslation } from "@/hooks/translations/useEnumTranslation";

export default function StaffScheduleTemplateTable({
  staffId,
}: {
  staffId: string;
}) {
  const { translate, toOptions } = useEnumTranslation();

  const tableRef = React.useRef<any>(null);
  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");
  const [selected, setSelected] =
    React.useState<StaffScheduleTemplateResponse | null>(null);

  /* FETCHING */
  const fetchData = async (
    page: number,
    size: number,
    filter?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<StaffScheduleTemplateResponse> }> => {
    const res = await StaffScheduleTemplateApi.filter(
      staffId,
      page,
      size,
      filter,
      sorts
    );
    return { data: res };
  };

  /* COLUMNS */
  const columns = [
    {
      title: "Thứ",
      dataIndex: "dayOfWeek",
      sortable: true,
      render: (value: string) => translate("dayOfWeek", value),
    },
    { title: "Bắt đầu", dataIndex: "startTime", sortable: true },
    { title: "Kết thúc", dataIndex: "endTime", sortable: true },
    { title: "Phòng", dataIndex: "roomName", sortable: true },
  ];

  /* FORM FIELDS */
  const formFields: FormFieldConfig[] = [
    {
      name: "dayOfWeek",
      label: "Thứ",
      type: "select",
      options: toOptions("dayOfWeek"),
      required: true,
    },
    { name: "startTime", label: "Giờ bắt đầu", type: "time", required: true },
    { name: "endTime", label: "Giờ kết thúc", type: "time", required: true },
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
    dayOfWeek: yup.string().required(),
    startTime: yup.string().required(),
    endTime: yup.string().required(),
    roomId: yup.string().required(),
  });

  return (
    <EntityTableWrapper<StaffScheduleTemplateResponse>
      title="Lịch mẫu hàng tuần"
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
        title: formType === "create" ? "Thêm lịch mẫu" : "Sửa lịch mẫu",
        formFields,
        defaultValues: selected || {},
        schema,
        onSubmit: async (data: StaffScheduleTemplateRequest) => {
          try {
            if (formType === "create") {
              await StaffScheduleTemplateApi.create(staffId, {
                ...data,
                staffId,
              });
            } else {
              await StaffScheduleTemplateApi.update(selected!.id!, data);
            }
            tableRef.current?.reload();
            setFormType("hidden");
            toast.success("Lưu thành công");
          } catch {
            toast.error("Không lưu được lịch mẫu");
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
          <Plus className="w-4 h-4 mr-2" /> Thêm lịch mẫu
        </Button>
      }
    />
  );
}
