import React, { useCallback } from "react";
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
import { Eye, Pencil, Plus } from "lucide-react";

import {
  type ServiceCatalogRequest,
  type ServiceCatalogResponse,
  ServiceCatalogResponseRoomTypeEnum,
} from "@/api";
import { ServiceCatalogApi } from "@/api/service-catalog/ServiceCatalogApi";
import { useEnumTranslation } from "@/hooks/translations/useEnumTranslation";
import { formatCurrency } from "@/utils/format";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";

export default function ServiceCatalogManagementPage() {
  const [selectForForm, setSelectForForm] =
    React.useState<ServiceCatalogResponse | null>(null);

  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");

  const tableRef = React.useRef<any>(null);

  const [selectRow, setSelectRow] =
    React.useState<ServiceCatalogResponse | null>(null);
  const [showViewModal, setShowViewModal] = React.useState(false);

  const handleViewDetails = (row: ServiceCatalogResponse) => {
    setSelectRow(row);
    setShowViewModal(true);
  };

  const { translate, toOptions } = useEnumTranslation();

  /* ----------------------------------- FETCH ----------------------------------- */
  const fetchServiceCatalogs = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<ServiceCatalogResponse> }> => {
    try {
      const res = await ServiceCatalogApi.filter(
        page,
        size,
        filterGroup ?? undefined,
        sorts
      );
      return { data: res };
    } catch {
      toast.error("Không tải được danh sách dịch vụ");
      throw new Error();
    }
  };

  const fetchData = useCallback(fetchServiceCatalogs, []);

  /* ---------------------------------- COLUMNS ---------------------------------- */
  const columns: Column<ServiceCatalogResponse>[] = [
    { title: "Mã dịch vụ", dataIndex: "code", sortable: true },
    {
      title: "Tên dịch vụ",
      dataIndex: "name",
      sortable: true,
      render: (v: string) => <div className="max-w-96 truncate">{v}</div>,
    },
    {
      title: "Danh mục",
      dataIndex: "category",
      sortable: true,
      render: (v: string) => <div className="max-w-36 truncate">{v}</div>,
    },
    {
      title: "Giá",
      dataIndex: "price",
      sortable: true,
      render: (v: number) => formatCurrency(v),
    },
    {
      title: "Loại phòng thực hiện",
      dataIndex: "roomType",
      sortable: true,
      render: (_, row) => translate("roomType", row.roomType),
    },
  ];

  /* ------------------------------- FORM FIELDS ------------------------------- */
  const formFieldConfigs: FormFieldConfig[] = [
    { name: "name", label: "Tên dịch vụ", type: "text", required: true },
    { name: "category", label: "Danh mục", type: "text" },
    { name: "price", label: "Giá", type: "number", required: true },
    { name: "description", label: "Mô tả", type: "textarea" },
    {
      name: "roomType",
      label: "Loại phòng thực hiện",
      type: "select",
      options: toOptions("roomType"),
    },
  ];

  /* ----------------------------------- SCHEMA ---------------------------------- */
  const schema = yup.object({
    name: yup.string().required("Tên dịch vụ bắt buộc"),
    price: yup.number().positive("Giá phải > 0").required("Giá bắt buộc"),
    description: yup.string().nullable(),
    category: yup.string().nullable(),
    roomType: yup
      .mixed<ServiceCatalogResponseRoomTypeEnum>()
      .required("Loại phòng thực hiện bắt buộc")
      .oneOf(
        Object.values(ServiceCatalogResponseRoomTypeEnum),
        "Loại phòng không hợp lệ"
      ),
  });

  /* -------------------------------- FORM MODAL -------------------------------- */
  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Thêm dịch vụ" : "Sửa dịch vụ",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    schema,
    onSubmit: async (data: ServiceCatalogRequest) => {
      try {
        if (formType === "create") {
          await ServiceCatalogApi.create(data);
          toast.success("Tạo dịch vụ thành công");
        } else if (formType === "update" && selectForForm) {
          await ServiceCatalogApi.update(selectForForm.id!, data);
          toast.success("Cập nhật dịch vụ thành công");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch {
        toast.error("Không thể lưu dịch vụ");
      }
    },
  };

  return (
    <>
      <EntityTableWrapper<ServiceCatalogResponse>
        ref={tableRef}
        title="Quản lý Service Catalog"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["name", "code", "category"]}
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
            <Plus className="w-4 h-4 mr-2" /> Thêm dịch vụ
          </Button>
        }
        footerExtra={(ctx) => (
          <div>
            <strong>Tổng dịch vụ:</strong> {ctx.totalElements}
          </div>
        )}
      />

      <ViewDetailDialog
        open={showViewModal}
        onClose={setShowViewModal}
        title="Chi tiết dịch vụ"
        data={selectRow}
        config={[
          { label: "Mã dịch vụ", name: "code", type: "text" },
          { label: "Tên dịch vụ", name: "name", type: "text" },
          { label: "Danh mục", name: "category", type: "text" },
          {
            label: "Giá",
            name: "price",
            type: "text",
            render: (v: number) => formatCurrency(v),
          },
          { label: "Mô tả", name: "description", type: "text" },
          {
            label: "Phòng thực hiện",
            name: "roomType",
            type: "text",
            render: (value: any) => translate("roomType", value),
          },
        ]}
        columns={1}
      />
    </>
  );
}
