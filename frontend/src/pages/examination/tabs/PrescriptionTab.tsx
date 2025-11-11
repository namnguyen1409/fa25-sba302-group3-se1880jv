import { useCallback, useRef, useState } from "react";
import type { PrescriptionItemResponse } from "@/api";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";

import { ExaminationApi } from "@/api/examination/ExaminationApi";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import type { Column } from "@/components/common/ReuseAbleTable";
import type { FormFieldConfig } from "@/components/common/FormModal";
import { FormModal } from "@/components/common/FormModal";
import { SearchSelect } from "@/components/common/SearchSelect";
import { MedicineApi } from "@/api/medicine/MedicineApi";
import * as yup from "yup";
import { useReactToPrint } from "react-to-print";
import type { ExaminationResponse } from "@/api/models";

export default function PrescriptionTab({
  exam,
}: {
  exam: ExaminationResponse;
}) {
  const [prescription, setPrescription] = useState<any>(
    exam.prescription ?? null
  );

  const [selectForForm, setSelectForForm] =
    useState<PrescriptionItemResponse | null>(null);

  const [formType, setFormType] = useState<"create" | "update" | "hidden">(
    "hidden"
  );

  const tableRef = useRef<any>(null);

  const printRef = useRef<HTMLDivElement>(null);
  const handlePrint = useReactToPrint({ contentRef: printRef });

  const handleAddPrescription = async () => {
    try {
      const res = await ExaminationApi.createPrescription(exam.id!, {
        note: "",
      });
      toast.success("Đã tạo đơn thuốc");
      setPrescription(res);
    } catch {
      toast.error("Không thể tạo đơn thuốc");
    }
  };

  const fetchPrescriptionItems = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ) => {
    if (!prescription) {
      return {
        data: {
          content: [],
          totalElements: 0,
          totalPages: 0,
          number: page,
          size,
        } as any,
      };
    }

    try {
      const res = await ExaminationApi.getPrescriptionItems(prescription.id!, {
        page,
        size,
        filterGroup,
        sorts,
      });
      return { data: res };
    } catch {
      toast.error("Không thể tải danh sách thuốc");
      return {
        data: {
          content: [],
          totalElements: 0,
          totalPages: 0,
          number: page,
          size,
        } as any,
      };
    }
  };

  const fetchData = useCallback(fetchPrescriptionItems, [prescription]);

  const columns: Column<PrescriptionItemResponse>[] = [
    {
      title: "Tên thuốc",
      dataIndex: "medicine.name",
      render: (_, row) => row.medicine?.name ?? "-",
    },
    {
      title: "Liều lượng",
      dataIndex: "dosage",
    },
    {
      title: "Tần suất",
      dataIndex: "frequency",
    },
    {
      title: "Đơn vị",
      dataIndex: "unit",
      render: (_, row) => row.medicine?.unit ?? "-",
    },
    {
      title: "Số lượng",
      dataIndex: "quantity",
    },
    {
      title: "Ghi chú",
      dataIndex: "instruction",
    },
  ];
  const formFieldConfigs: FormFieldConfig[] = [
    {
      name: "medicineId",
      label: "Thuốc",
      type: "text",
      required: true,
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Tìm thuốc"
          fetchOptions={async (keyword) => {
            const res = await MedicineApi.search(keyword);
            return {
              content: res.content.map((m) => ({
                value: m.id!,
                label: `${m.unit}-${m.name}`,
              })),
              page: {
                number: res.page.number,
                totalPages: res.page.totalPages,
              },
            };
          }}
          initialOption={
            selectForForm?.medicine
              ? {
                  label: `${selectForForm.medicine.unit} ${selectForForm
                    .medicine.name!}`,
                  value: selectForForm.medicine.id!,
                }
              : null
          }
        />
      ),
    },
    { name: "dosage", label: "Liều lượng", type: "text", required: true },
    { name: "frequency", label: "Tần suất", type: "text", required: true },
    { name: "quantity", label: "Số lượng", type: "number", required: true },
    { name: "instruction", label: "Ghi chú", type: "textarea" },
  ];

  const schema = yup.object({
    medicineId: yup.string().required(),
    dosage: yup.string().required(),
    frequency: yup.string().required(),
    quantity: yup.number().typeError("Số lượng phải là số").required().min(1),
    instruction: yup.string(),
  });

  const handleSubmit = async (data: any) => {
    if (!prescription) return toast.error("Chưa có đơn thuốc");

    try {
      if (formType === "create") {
        await ExaminationApi.createPrescriptionItem(prescription.id!, {
          ...data,
        });

        toast.success("Đã thêm thuốc");
      }

      if (formType === "update" && selectForForm) {
        await ExaminationApi.updatePrescriptionItem(selectForForm.id!, data);

        toast.success("Đã cập nhật thuốc");
      }

      setFormType("hidden");
      setSelectForForm(null);
      tableRef.current?.reload();
    } catch {
      toast.error("Không thể lưu thuốc");
    }
  };

  return (
    <div className="p-4 space-y-4">
      <h3 className="font-bold">Đơn thuốc</h3>
      {!prescription && (
        <Button onClick={handleAddPrescription}>Tạo đơn thuốc</Button>
      )}
      {prescription && (
        <>
          <EntityTableWrapper<PrescriptionItemResponse>
            ref={tableRef}
            title="Danh sách thuốc"
            fetchData={fetchData}
            columns={columns}
            renderRowActions={(row) => (
              <>
                <Button
                  size="sm"
                  variant="outline"
                  onClick={() => {
                    setSelectForForm(row);
                    setFormType("update");
                  }}
                >
                  Sửa
                </Button>
              </>
            )}
            headerExtra={
              <>
                <Button
                  size="sm"
                  onClick={() => {
                    setSelectForForm(null);
                    setFormType("create");
                  }}
                >
                  Thêm thuốc
                </Button>

                <Button size="sm" onClick={handlePrint}>
                  In đơn thuốc
                </Button>
              </>
            }
          />
          <FormModal
            open={formType !== "hidden"}
            onClose={() => {
              setFormType("hidden");
              setSelectForForm(null);
            }}
            title={formType === "create" ? "Thêm thuốc" : "Sửa thuốc"}
            formFields={formFieldConfigs}
            defaultValues={selectForForm ?? {}}
            onSubmit={handleSubmit}
            schema={schema}
          />
        </>
      )}
    </div>
  );
}
