"use client";

import React from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type PageResponse,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { Eye, Pencil } from "lucide-react";
import { toast } from "sonner";

import type { Column } from "@/components/common/ReuseAbleTable";
import type { ExaminationResponse } from "@/api/models";
import { ExaminationApi } from "@/api/examination/ExaminationApi";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";
import { useNavigate } from "react-router-dom";

export default function ExaminationManagementPage() {
  const tableRef = React.useRef<any>(null);

  const navigate = useNavigate();

  const [selectRow, setSelectRow] = React.useState<ExaminationResponse | null>(
    null
  );
  const [showView, setShowView] = React.useState(false);

  const fetchExaminations = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<ExaminationResponse> }> => {
    try {
      const res = await ExaminationApi.getExaminations(
        page,
        size,
        filterGroup ?? undefined,
        sorts ?? undefined
      );
      return { data: res };
    } catch (err) {
      console.error(err);
      toast.error("Không tải được danh sách khám");
      throw err;
    }
  };

  const handleView = (row: ExaminationResponse) => {
    setSelectRow(row);
    setShowView(true);
  };


  const columns: Column<ExaminationResponse>[] = [
    {
      title: "Bệnh nhân",
      dataIndex: "patient",
      sortable: true,
      render: (v: any) => (
        <div className="max-w-40 truncate">{v?.fullName ?? v?.patientCode}</div>
      ),
    },
    { title: "Bác sĩ", dataIndex: "staff", render: (v: any) => v?.fullName },
    { title: "Loại", dataIndex: "type", sortable: true },
    { title: "Trạng thái", dataIndex: "status", sortable: true },
    {
      title: "Ngày khám",
      dataIndex: "examinationDate",
      sortable: true,
      render: (v: string) => new Date(v).toLocaleString(),
    },
    {
      title: "Tóm tắt",
      dataIndex: "diagnosisSummary",
      render: (v: string) => <div className="max-w-56 truncate">{v}</div>,
    },
    {
      title: "Hành động",
      dataIndex: "actions",
      sortable: false,
      render: (_: any, row: ExaminationResponse) => (
        <div className="flex items-center">
          <Button
            variant="outline"
            size="sm"
            className="mr-2"
            onClick={() => handleView(row)}
          >
            <Eye className="w-4 h-4" />
          </Button>
          <Button
            variant="outline"
            size="sm"
            className="mr-2"
            onClick={() => {
                navigate(`/staff/examinations/${row.id}`);
            }}
          >
            <Pencil className="w-4 h-4" />
          </Button>
        </div>
      ),
    },
  ];

  return (
    <>
      <EntityTableWrapper<ExaminationResponse>
        ref={tableRef}
        title="Quản lý khám"
        fetchData={fetchExaminations}
        columns={columns}
        smartSearchFields={["patient.fullName", "patient.patientCode", "symptom", "diagnosisSummary"]}
        pageSize={10}
      />

      <ViewDetailDialog
        open={showView}
        onClose={setShowView}
        title="Chi tiết khám"
        data={selectRow}
        config={[
          { label: "Bệnh nhân", name: "patient", type: "text", render: (v: any) => v?.fullName },
          { label: "Bác sĩ", name: "staff", type: "text", render: (v: any) => v?.fullName },
          { label: "Loại", name: "type", type: "text" },
          { label: "Trạng thái", name: "status", type: "text" },
          { label: "Ngày khám", name: "examinationDate", type: "text" },
          { label: "Triệu chứng", name: "symptom", type: "text" },
          { label: "Tóm tắt chẩn đoán", name: "diagnosisSummary", type: "text" },
        ]}
      />
    </>
  );
}
