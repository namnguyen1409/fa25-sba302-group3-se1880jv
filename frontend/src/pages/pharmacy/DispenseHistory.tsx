"use client";

import React from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type PageResponse,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import type { Column } from "@/components/common/ReuseAbleTable";

import { Button } from "@/components/ui/button";
import { Eye } from "lucide-react";
import { toast } from "sonner";

import type { DispenseRecordResponse } from "@/api/models/DispenseRecordResponse";
import { PharmacyApi } from "@/api/pharmacy/PharmacyApi";
import { useNavigate } from "react-router-dom";

export default function DispenseRecordHistoryPage() {
  const tableRef = React.useRef<any>(null);
  const navigate = useNavigate();
  
  const fetchRecords = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<DispenseRecordResponse> }> => {
    try {
      const res = await PharmacyApi.filter(
        page,
        size,
        filterGroup,
        sorts
      );
      return { data: res };
    } catch (err) {
      toast.error("Không tải được lịch sử phát thuốc");
      throw err;
    }
  };

  const columns: Column<DispenseRecordResponse>[] = [
    {
      title: "Mã BN",
      dataIndex: "patient.patientCode",
      sortable: true,
      render: (_v, row) => (
        <div className="max-w-40 truncate">{row.patient?.patientCode ?? "-"}</div>
      ),
    },
    {
      title: "Bệnh nhân",
      dataIndex: "patient.fullName",
      sortable: true,
      render: (_v, row) => (
        <div className="max-w-40 truncate">{row.patient?.fullName ?? "-"}</div>
      ),
    },
    {
      title: "Người phát",
      dataIndex: "dispensedBy.fullName",
      render: (_v, row) =>
        row.dispensedBy ? row.dispensedBy.fullName : "-",
    },
    {
      title: "Tổng tiền",
      dataIndex: "totalCost",
      render: (v) => v?.toLocaleString() + " đ",
      sortable: true,
    },
    {
      title: "Trạng thái",
      dataIndex: "status",
      render: (v) => {
        const map: any = {
          PENDING: "Chờ phát",
          DISPENSING: "Đang phát",
          DISPENSED: "Đã phát",
        };
        return map[v] ?? v;
      },
      sortable: true,
    },
  ];

  return (
    <EntityTableWrapper<DispenseRecordResponse>
      ref={tableRef}
      title="Lịch sử phát thuốc"
      fetchData={fetchRecords}
      columns={columns}
      smartSearchFields={[
        "patient.fullName",
        "patient.patientCode",
        "dispensedBy.fullName",
        "status",
      ]}
      renderRowActions={(row) => (
        <>
          <Button
            variant="outline"
            size="sm"
            onClick={() => navigate(`/staff/dispense/${row.id}`)}
          >
            <Eye className="w-4 h-4" />
          </Button>
        </>
      )}
      pageSize={10}
    />
  );
}
