import { useCallback, useRef, useState } from "react";
import { Button } from "@/components/ui/button";
import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse, VitalSignResponse } from "@/api";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { FormModal } from "@/components/common/FormModal";
import { toast } from "sonner";
import * as yup from "yup";

export default function VitalSignsTab({ exam }: { exam: ExaminationResponse }) {
  const tableRef = useRef<any>(null);
  const [editData, setEditData] = useState<VitalSignResponse | null>(null);
  const [modalType, setModalType] = useState<"edit" | "add" | null>(null);

  const fetchVitalSigns = useCallback(
    (
      page: number,
      size: number,
      filterGroup?: FilterGroup | null,
      sorts?: SortRequest[]
    ) => {
      return ExaminationApi.filterVitals(
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
      title: "Nhiệt độ (°C)",
      dataIndex: "temperature",
      key: "temperature",
    },
    {
      title: "Huyết áp (mmHg)",
      dataIndex: "bloodPressure",
      key: "bloodPressure",
    },
    {
      title: "Mạch (lần/phút)",
      dataIndex: "pulse",
      key: "pulse",
    },
    {
      title: "Nhịp thở (lần/phút)",
      dataIndex: "respirationRate",
      key: "respirationRate",
    },
    {
      title: "Chiều cao (cm)",
      dataIndex: "height",
      key: "height",
    },
    {
      title: "Cân nặng (kg)",
      dataIndex: "weight",
      key: "weight",
    },
    {
        title: "Thời gian ghi nhận",
        dataIndex: "createdDate",
        key: "createdDate",
      render: (_value: string) => new Date(_value).toLocaleString(),
    }
  ];

  const currentFields = [
    { name: "temperature", label: "Nhiệt độ (°C)", type: "text" },
    { name: "bloodPressure", label: "Huyết áp (mmHg)", type: "text" },
    { name: "pulse", label: "Mạch (lần/phút)", type: "text" },
    { name: "respirationRate", label: "Nhịp thở (lần/phút)", type: "text" },
    { name: "height", label: "Chiều cao (cm)", type: "text" },
    { name: "weight", label: "Cân nặng (kg)", type: "text" },
  ];

  const handleSubmit = async (data: any) => {
    if (modalType === "edit" && editData) {
      await ExaminationApi.updateVital(exam.id!, editData.id!, data);
      toast.success("Cập nhật dấu hiệu sinh tồn thành công");
    } else if (modalType === "add") {
      await ExaminationApi.addVital(exam.id!, {
        ...data,
        examinationId: exam.id!,
      });
      toast.success("Thêm dấu hiệu sinh tồn thành công");
    }
    tableRef.current.reload();
    setModalType(null);
    setEditData(null);
  };

  return (
    <>
      <EntityTableWrapper<VitalSignResponse>
        title="Dấu hiệu sinh tồn"
        ref={tableRef}
        fetchData={fetchVitalSigns}
        columns={columns}
        headerExtra={
          <Button
            onClick={() => {
              setModalType("add");
              setEditData(null);
            }}
          >
            Thêm dấu hiệu sinh tồn
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
          temperature: yup.string().required(),
          bloodPressure: yup.string().required(),
          pulse: yup.string().required(),
          respirationRate: yup.string().required(),
          height: yup.string().required(),
          weight: yup.string().required(),
        })}
      />
    </>
  );
}
