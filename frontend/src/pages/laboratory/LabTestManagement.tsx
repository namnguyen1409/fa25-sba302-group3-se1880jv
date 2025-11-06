import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type PageResponse,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import { Eye, Pencil, Plus } from "lucide-react";
import * as yup from "yup";

import type { FormFieldConfig } from "@/components/common/FormModal";
import type { Column } from "@/components/common/ReuseAbleTable";

import { type LabTestRequest, type LabTestResponse } from "@/api";
import { useEnumTranslation } from "@/hooks/translations/useEnumTranslation";
import { LabTestApi } from "@/api/laboratory/LabTestApi";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";
import { formatCurrency } from "@/utils/format";

export default function LabTestManagementPage() {
  const { translate, toOptions } = useEnumTranslation();
  const [selectRow, setSelectRow] = React.useState<LabTestResponse | null>(
    null
  );
  const [showViewModal, setShowViewModal] = React.useState(false);
  const [selectForForm, setSelectForForm] =
    React.useState<LabTestResponse | null>(null);

  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");

  const tableRef = React.useRef<any>(null);

  const handleViewDetails = (row: LabTestResponse) => {
    setSelectRow(row);
    setShowViewModal(true);
  };
  /* ---------------------------- FETCH DATA ---------------------------- */
  const fetchLabs = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<LabTestResponse> }> => {
    try {
      const res = await LabTestApi.filter(
        page,
        size,
        filterGroup ?? undefined,
        sorts
      );
      return { data: res };
    } catch {
      toast.error("Không tải được danh mục xét nghiệm");
      throw new Error();
    }
  };

  const fetchData = useCallback(fetchLabs, []);

  /* --------------------------- COLUMNS --------------------------- */
  const columns: Column<LabTestResponse>[] = [
    { title: "Mã xét nghiệm", dataIndex: "code", sortable: true },
    { title: "Tên xét nghiệm", dataIndex: "name", sortable: true ,
        render: (v: string) => <div className="max-w-56 truncate">{v}</div>,
    },
    { title: "Danh mục", dataIndex: "category", sortable: true ,
        render: (v: string) => <div className="max-w-36 truncate">{v}</div>,
    },
    { title: "Đơn giá", dataIndex: "price", sortable: true , render: (value: any) => formatCurrency(value) },
    { title: "Đơn vị", dataIndex: "unit", sortable: true },
    {
      title: "Khoảng tham chiếu",
      dataIndex: "referenceRange",
      sortable: false,
        render: (v: string) => <div className="max-w-56 truncate">{v}</div>,
    },
    {
      title: "Phòng thực hiện",
      dataIndex: "roomType",
      sortable: true,
      render: (_, row) => translate("roomType", row.roomType),
    },
  ];

  /* --------------------------- FORM DEFAULTS --------------------------- */
  const normalizeDefaults = (data: any) => ({
    name: data?.name ?? "",
    category: data?.category ?? "",
    price: data?.price ?? 0,
    unit: data?.unit ?? "",
    referenceRange: data?.referenceRange ?? "",
    description: data?.description ?? "",
    roomType: data?.roomType ?? null,
  });

  /* --------------------------- FORM FIELDS --------------------------- */
  const formFieldConfigs: FormFieldConfig[] = [
    { name: "name", label: "Tên xét nghiệm", type: "text", required: true },
    { name: "category", label: "Danh mục", type: "text" },
    { name: "price", label: "Giá", type: "number", required: true },
    { name: "unit", label: "Đơn vị", type: "text" },
    { name: "referenceRange", label: "Khoảng tham chiếu", type: "text" },
    { name: "description", label: "Mô tả", type: "textarea" },
    {
      name: "roomType",
      label: "Phòng thực hiện",
      type: "select",
      options: toOptions("roomType"),
    },
  ];

  /* --------------------------- VALIDATION --------------------------- */
  const schema = yup.object({
    name: yup.string().required("Tên xét nghiệm bắt buộc"),
    price: yup.number().positive("Giá phải > 0").required("Giá bắt buộc"),
    category: yup.string().nullable(),
    unit: yup.string().nullable(),
    referenceRange: yup.string().nullable(),
    description: yup.string().nullable(),
    roomType: yup.mixed().nullable(),
  });

  /* --------------------------- FORM MODAL --------------------------- */
  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Thêm xét nghiệm" : "Sửa xét nghiệm",
    formFields: formFieldConfigs,
    defaultValues: normalizeDefaults(selectForForm),
    schema,
    onSubmit: async (data: LabTestRequest) => {
      try {
        if (formType === "create") {
          await LabTestApi.create(data);
          toast.success("Tạo xét nghiệm thành công");
        } else if (formType === "update" && selectForForm) {
          await LabTestApi.update(selectForForm.id!, data);
          toast.success("Cập nhật xét nghiệm thành công");
        }
        setFormType("hidden");
        tableRef.current?.reload();
      } catch {
        toast.error("Không thể lưu dữ liệu xét nghiệm");
      }
    },
  };

  return (
    <>
      <EntityTableWrapper<LabTestResponse>
        ref={tableRef}
        title="Quản lý danh mục xét nghiệm"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["name", "code", "category", "description"]}
        pageSize={10}
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              className="mr-2"
              onClick={() => handleViewDetails(row)}
            >
              <Eye className="w-4 h-4" />
            </Button>
            <Button
              variant="outline"
              onClick={() => {
                setSelectForForm(row);
                setFormType("update");
              }}
            >
              <Pencil className="w-4 h-4" />
            </Button>
          </>
        )}
        formModalProps={formModalProps}
        headerExtra={
          <Button
            onClick={() => {
              setSelectForForm(null);
              setFormType("create");
            }}
          >
            <Plus className="w-4 h-4 mr-2" /> Thêm xét nghiệm
          </Button>
        }
        footerExtra={(ctx) => (
          <div>
            <strong>Tổng xét nghiệm:</strong> {ctx.totalElements}
          </div>
        )}
      />

      <ViewDetailDialog
        open={showViewModal}
        onClose={setShowViewModal}
        title="Lab Test Details"
        data={selectRow}
        config={[
          { label: "Tên xét nghiệm", name: "name", type: "text" },
          { label: "Danh mục", name: "category", type: "text" },
          { label: "Giá", name: "price", type: "number",
            render: (value: any) => formatCurrency(value),
           },
          { label: "Đơn vị", name: "unit", type: "text" },
          { label: "Khoảng tham chiếu", name: "referenceRange", type: "text" },
          {
            label: "Phòng thực hiện",
            name: "roomType",
            type: "text",
            render: (value: any) => translate("roomType", value),
          },
          { label: "Mô tả", name: "description", type: "text" },
        ]}
        columns={1}
      />
    </>
  );
}
