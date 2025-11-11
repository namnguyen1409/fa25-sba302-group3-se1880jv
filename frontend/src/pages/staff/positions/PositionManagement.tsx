// src/pages/staff/positions/PositionManagementPage.tsx
import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import { Pencil, Plus } from "lucide-react";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import type { Column } from "@/components/common/ReuseAbleTable";

// SDK types + API
import {
  type PositionRequest,
  type PositionResponse,
} from "@/api";
import { PositionApi } from "@/api/staff/PositionApi";

export default function PositionManagementPage() {
  const [selectForForm, setSelectForForm] =
    React.useState<PositionResponse | null>(null);
  const [formType, setFormType] =
    React.useState<"create" | "update" | "hidden">("hidden");
  const tableRef = React.useRef<any>(null);

  /* ------------------------------- FETCH ------------------------------- */
  const fetchPositions = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<PositionResponse> }> => {
    try {
      const res = await PositionApi.filter(
        page,
        size,
        filterGroup ?? undefined,
        sorts
      );
      return { data: res };
    } catch {
      toast.error("Không tải được danh sách chức danh");
      throw new Error();
    }
  };

  const fetchData = useCallback(fetchPositions, []);

  /* ------------------------------ COLUMNS ------------------------------ */
  const columns: Column<PositionResponse>[] = [
    { title: "Mã chức danh", dataIndex: "positionCode", sortable: true },
    { title: "Tiêu đề", dataIndex: "title", sortable: true },
    { title: "Mô tả", dataIndex: "description", sortable: false },
  ];

  /* ------------------------ DEFAULTS (no undefined) ------------------------ */
  const normalizeDefaults = (data?: Partial<PositionResponse> | null) => ({
    // positionCode do backend tự xử lý -> không đưa vào form
    title: data?.title ?? "",
    description: data?.description ?? "",
  });

  /* ------------------------------- FORM ------------------------------- */
  const formFields: FormFieldConfig[] = [
    { name: "title", label: "Tiêu đề", type: "text", required: true },
    { name: "description", label: "Mô tả", type: "textarea" },
  ];

  const schema = yup.object({
    title: yup.string().required("Tiêu đề bắt buộc"),
    description: yup.string().nullable(),
  });

  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Thêm chức danh" : "Sửa chức danh",
    formFields,
    defaultValues: normalizeDefaults(selectForForm),
    schema,
    onSubmit: async (data: PositionRequest) => {
      try {
        if (formType === "create") {
          // positionCode để BE tự generate
          await PositionApi.createPosition(data);
          toast.success("Tạo chức danh thành công");
        } else if (formType === "update" && selectForForm?.id) {
          await PositionApi.updatePosition(selectForForm.id, data);
          toast.success("Cập nhật chức danh thành công");
        }
        setFormType("hidden");
        tableRef.current?.reload();
      } catch {
        toast.error("Không thể lưu chức danh");
      }
    },
  };

  return (
    <EntityTableWrapper<PositionResponse>
      ref={tableRef}
      title="Quản lý Chức danh (Position)"
      fetchData={fetchData}
      columns={columns}
      smartSearchFields={["positionCode", "title"]}
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
          <Plus className="w-4 h-4 mr-2" /> Thêm chức danh
        </Button>
      }
      footerExtra={(ctx) => (
        <div>
          <strong>Tổng chức danh:</strong> {ctx.totalElements}
        </div>
      )}
    />
  );
}
