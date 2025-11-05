import React, { useCallback} from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import type { Column } from "@/components/common/ReuseAbleTable";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import { DepartmentApi } from "@/api/department/DepartmentApi";
import { Pencil, Building2 } from "lucide-react";
import { SearchSelect } from "@/components/common/SearchSelect";
import type { RoomResponse } from "@/api";
import { RoomApi } from "@/api/room/RoomApi";

export default function RoomManagementPage() {
  const [selectForForm, setSelectForForm] = React.useState<RoomResponse | null>(
    null
  );
  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");
  const tableRef = React.useRef<any>(null);


  const fetchRooms = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<RoomResponse> }> => {
    try {
      const res = await RoomApi.getRooms(
        page,
        size,
        filterGroup ?? undefined,
        sorts
      );
      return { data: res };
    } catch {
      toast.error("Không tải được danh sách phòng");
      throw new Error();
    }
  };

  const fetchData = useCallback(fetchRooms, []);

  const columns: Column<RoomResponse>[] = [
    { title: "Tên phòng", dataIndex: "name", sortable: true },
    { title: "Loại phòng", dataIndex: "roomType", sortable: true },
    { title: "Tầng", dataIndex: "floorNumber", sortable: true },
    { title: "Sức chứa", dataIndex: "capacity", sortable: true },
    { title: "Khoa", dataIndex: "departmentName", sortable: true, render: (_value, record) => record.department?.name || "" },
    { title: "Mô tả", dataIndex: "description", sortable: false },
  ];

  const formFieldConfigs: FormFieldConfig[] = [
    { name: "name", label: "Tên phòng", type: "text", required: true },
    {
      name: "roomType",
      label: "Loại phòng",
      type: "select",
      required: false,
      options: [
        { value: "CLINIC", label: "Phòng khám" },
        { value: "LAB", label: "Xét nghiệm" },
        { value: "IMAGING", label: "Chẩn đoán hình ảnh" },
        { value: "WARD", label: "Buồng bệnh" },
        { value: "OTHER", label: "Khác" },
      ],
    },
    { name: "floorNumber", label: "Tầng", type: "number" },
    { name: "capacity", label: "Sức chứa", type: "number" },
    { name: "description", label: "Mô tả", type: "textarea" },
    {
      name: "departmentId",
      label: "Khoa",
      type: "select",
      required: true,
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Chọn khoa"
          fetchOptions={async (keyword) => {
            const res = await DepartmentApi.search(keyword);
            return res.content
              .filter((d) => d.id != null && d.name != null)
              .map((d) => ({
                value: String(d.id),
                label: String(d.name),
              }));
          }}
        />
      ),
    },
  ];

  const schema = yup.object({
    name: yup.string().max(100).required("Tên phòng bắt buộc"),
    roomType: yup.string().max(50).nullable(),
    floorNumber: yup.number().min(1).max(200).nullable(),
    capacity: yup.number().positive().max(1000).nullable(),
    description: yup.string().nullable(),
    departmentId: yup.string().required("Chọn khoa"),
  });

  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Thêm phòng" : "Sửa phòng",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    schema,
    onSubmit: async (data: any) => {
      try {
        if (formType === "create") {
          await RoomApi.createRoom(data);
          toast.success("Tạo phòng thành công");
        } else if (formType === "update" && selectForForm) {
          await RoomApi.updateRoom(selectForForm.id!, data);
          toast.success("Cập nhật phòng thành công");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch {
        toast.error("Không thể lưu phòng");
      }
    },
  };

  return (
    <EntityTableWrapper<RoomResponse>
      ref={tableRef}
      title="Quản lý phòng"
      fetchData={fetchData}
      columns={columns}
      smartSearchFields={["name", "roomType"]}
      pageSize={10}
      renderRowActions={(row) => (
        <Button
          variant="outline"
          onClick={() => {
            setSelectForForm(row);
            setFormType("update");
          }}
        >
          <Pencil className="w-4 h-4" />
        </Button>
      )}
      formModalProps={formModalProps}
      headerExtra={
        <Button
          onClick={() => {
            setSelectForForm(null);
            setFormType("create");
          }}
        >
          <Building2 className="w-4 h-4 mr-2" /> Thêm phòng
        </Button>
      }
      footerExtra={(ctx) => (
        <div>
          <strong>Tổng phòng:</strong> {ctx.totalElements}
        </div>
      )}
    />
  );
}
