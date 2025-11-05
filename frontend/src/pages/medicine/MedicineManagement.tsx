import React, { useCallback, useState } from "react";
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
import { MedicineApi,} from "@/api/medicine/MedicineApi";
import { Pencil, Pill } from "lucide-react";
import type { MedicineResponse } from "@/api";
import type _ from "lodash";

export default function MedicineManagementPage() {
  const [selectForForm, setSelectForForm] = useState<MedicineResponse | null>(null);
  const [formType, setFormType] =
    useState<"create" | "update" | "hidden">("hidden");
  const tableRef = React.useRef<any>(null);

  const fetchMedicines = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<MedicineResponse> }> => {
    try {
      const res = await MedicineApi.getMedicines(page, size, filterGroup ?? undefined, sorts);
      return { data: res };
    } catch {
      toast.error("Không tải được danh sách thuốc");
      throw new Error();
    }
  };

  const fetchData = useCallback(fetchMedicines, []);

  const columns: Column<MedicineResponse>[] = [
    { title: "Mã thuốc", dataIndex: "code", sortable: true },
    { title: "Tên thương mại", dataIndex: "name", sortable: true },
    { title: "Hoạt chất", dataIndex: "activeIngredient", sortable: true,

        render: (v: string) => (<div className="max-w-sm truncate">{v}</div>)
     },
    { title: "Dạng bào chế", dataIndex: "dosageForm", sortable: true },
    { title: "Hàm lượng", dataIndex: "strength", sortable: true },
    { title: "Đơn vị", dataIndex: "unit", sortable: true },
    { title: "Giá", dataIndex: "price", sortable: true, 
        render: (v: number) => v.toLocaleString("vi-VN", { style: "currency", currency: "VND" })
     },
  ];

  const formFieldConfigs: FormFieldConfig[] = [
    { name: "name", label: "Tên thuốc", type: "text", required: true },
    { name: "activeIngredient", label: "Hoạt chất", type: "text" },
    { name: "dosageForm", label: "Dạng bào chế", type: "text" },
    { name: "strength", label: "Hàm lượng", type: "text" },
    { name: "price", label: "Giá", type: "number", required: true },
    { name: "unit", label: "Đơn vị", type: "text" },
    { name: "description", label: "Mô tả", type: "textarea" },
  ];

  const schema = yup.object({
    name: yup.string().required("Tên thuốc bắt buộc"),
    price: yup.number().typeError("Giá phải là số").required("Giá bắt buộc"),
  });

  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Thêm thuốc" : "Sửa thuốc",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    schema,
    onSubmit: async (data: any) => {
      try {
        if (formType === "create") {
          await MedicineApi.createMedicine(data);
          toast.success("Thêm thuốc thành công");
        } else if (formType === "update" && selectForForm) {
          await MedicineApi.updateMedicine(selectForForm.id!, data);
          toast.success("Cập nhật thuốc thành công");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch {
        toast.error("Lỗi khi lưu thuốc");
      }
    },
  };

  return (
    <EntityTableWrapper<MedicineResponse>
      ref={tableRef}
      title="Quản lý thuốc"
      fetchData={fetchData}
      columns={columns}
      smartSearchFields={["name", "activeIngredient"]}
      pageSize={10}
      renderRowActions={(row) => (
        <Button variant="outline" onClick={() => { setSelectForForm(row); setFormType("update"); }}>
          <Pencil className="w-4 h-4" />
        </Button>
      )}
      formModalProps={formModalProps}
      headerExtra={
        <Button onClick={() => { setSelectForForm(null); setFormType("create"); }}>
          <Pill className="w-4 h-4 mr-2" /> Thêm thuốc
        </Button>
      }
      footerExtra={(ctx) => (
        <div><strong>Tổng thuốc:</strong> {ctx.totalElements}</div>
      )}
    />
  );
}
