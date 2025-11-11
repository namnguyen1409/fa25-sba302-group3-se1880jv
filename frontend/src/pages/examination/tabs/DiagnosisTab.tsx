import { useCallback, useRef, useState } from "react";
import { Button } from "@/components/ui/button";
import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type {
  DiagnosisRequest,
  DiagnosisResponse,
  // ExaminationResponse,
  VitalSignResponse,
} from "@/api";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { FormModal, type FormFieldConfig } from "@/components/common/FormModal";
import { toast } from "sonner";
import * as yup from "yup";
import { SearchSelect } from "@/components/common/SearchSelect";
import { IcdApi } from "@/api/icd/IcdApi";
import type { ExaminationResponse } from "@/api/models";

export default function DiagnosisTab({ exam }: { exam: ExaminationResponse }) {
  const tableRef = useRef<any>(null);
  const [editData, setEditData] = useState<VitalSignResponse | null>(null);
  const [modalType, setModalType] = useState<"edit" | "add" | null>(null);

  const fetchDiagnoses = useCallback(
    (
      page: number,
      size: number,
      filterGroup?: FilterGroup | null,
      sorts?: SortRequest[]
    ) => {
      return ExaminationApi.filterDiagnoses(
        exam.id!,
        page,
        size,
        filterGroup ?? ({} as FilterGroup),
        sorts ?? []
      ).then((res) => ({ data: res }));
    },
    [exam.id]
  );

  const columns = [
    {
      title: "ICD Code",
      dataIndex: "icdCode",
      render: (_value: string, record: DiagnosisResponse) => record.icdCode?.code,
    },
    {
      title: "Disease Name",
      dataIndex: "diseaseName",
      render: (_value: string, record: DiagnosisResponse) => record.icdCode?.name,
    },
    {
      title: "Note",
      dataIndex: "note",
      key: "note",
    },
  ];

  const currentFields: FormFieldConfig[] = [
    {
      name: "icdCodeId",
      label: "ICD Code",
      type: "text",
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Tìm mã ICD"
          fetchOptions={async (keyword) => {
            const res = await IcdApi.SearchIcd(keyword);
            return {
              content: res.content.map((icd) => ({
                value: String(icd.id),
                label: `${icd.code} - ${icd.name}`,
              })),
              page: res.page,
            }
          }}
        />
      ),
    },
    { name: "note", label: "Ghi chú", type: "textarea" },
  ];

  const handleSubmit = async (data: DiagnosisRequest) => {
    try {
      if (modalType === "edit" && editData) {
        await ExaminationApi.updateDiagnosis(exam.id!, editData.id!, data);
        toast.success("Cập nhật chẩn đoán thành công");
      } else if (modalType === "add") {
        await ExaminationApi.createDiagnosis(exam.id!, {
          ...data,
          examinationId: exam.id!,
        });
        toast.success("Thêm chẩn đoán thành công");
      }
      tableRef.current.reload();
      setModalType(null);
      setEditData(null);
    } catch (error) {
      toast.error("Đã có lỗi xảy ra. Vui lòng thử lại.");
    }
  };

  return (
    <>
      <EntityTableWrapper<DiagnosisResponse>
        title="Chẩn đoán"
        ref={tableRef}
        fetchData={fetchDiagnoses}
        columns={columns}
        headerExtra={
          <Button
            onClick={() => {
              setModalType("add");
              setEditData(null);
            }}
          >
            Thêm chẩn đoán
          </Button>
        }
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              size="sm"
              className="mr-2"
              onClick={() => {
                setEditData(row);
                setModalType("edit");
              }}
            >
              Edit
            </Button>
          </>
        )}
      />

      <FormModal
        open={!!modalType}
        onClose={() => {
          setModalType(null);
          setEditData(null);
        }}
        title="Edit"
        formFields={currentFields}
        defaultValues={editData ?? {}}
        onSubmit={handleSubmit}
        schema={yup.object({
            icdCodeId: yup.string().required("Chọn mã ICD"),
            note: yup.string().optional(),
        })}
      />
    </>
  );
}
