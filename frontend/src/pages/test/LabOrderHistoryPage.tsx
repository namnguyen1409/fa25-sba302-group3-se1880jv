"use client";

import React from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type PageResponse,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { Eye } from "lucide-react";
import { toast } from "sonner";

import type { Column } from "@/components/common/ReuseAbleTable";
import type { LabOrderResponse } from "@/api/models/LabOrderResponse";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";
import { LabOrderApi } from "@/api/lab/LabOrderApi";

export default function LabOrderHistoryPage() {
  const tableRef = React.useRef<any>(null);

  const [selectRow, setSelectRow] = React.useState<LabOrderResponse | null>(
    null
  );
  const [showView, setShowView] = React.useState(false);

  // ✅ API HISTORY bạn tự implement
  const fetchLabOrders = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<LabOrderResponse> }> => {
    try {
      // ví dụ:
      const res = await LabOrderApi.filter(page, size, filterGroup, sorts);
      return { data: res };
    } catch (err) {
      console.error(err);
      toast.error("Không tải được lịch sử xét nghiệm");
      throw err;
    }
  };

  const handleView = (row: LabOrderResponse) => {
    setSelectRow(row);
    setShowView(true);
  };

  const columns: Column<LabOrderResponse>[] = [
    {
      title: "Mã phiếu",
      dataIndex: "orderCode",
      sortable: true,
    },
    {
      title: "Bệnh nhân",
      dataIndex: "patient.fullName",
      sortable: true,
      render: (_v: any, row) => (
        <div className="max-w-40 truncate">
          {row.patient?.fullName ?? row.patient?.patientCode}
        </div>
      ),
    },
    {
      title: "Người chỉ định",
      dataIndex: "requestedBy",
      render: (v: any) => v?.fullName,
    },
    {
      title: "Trạng thái",
      dataIndex: "status",
      sortable: true,
      render: (v) => {
        const map: any = {
          PENDING: "Chờ xử lý",
          IN_PROGRESS: "Đang thực hiện",
          COMPLETED: "Hoàn thành",
          VERIFIED: "Đã duyệt",
          CANCELLED: "Đã hủy",
        };
        return map[v] ?? v;
      },
    },
    {
      title: "Số lượng xét nghiệm",
      dataIndex: "results",
      render: (res) => (res ? Array.from(res).length : 0),
    },
    {
      title: "Phòng",
      dataIndex: "room.name",
      sortable: true,
    },
    {
      title: "Hành động",
      dataIndex: "actions",
      sortable: false,
      render: (_: any, row: LabOrderResponse) => (
        <div className="flex items-center">
          <Button variant="outline" size="sm" onClick={() => handleView(row)}>
            <Eye className="w-4 h-4" />
          </Button>
        </div>
      ),
    },
  ];

  return (
    <>
      <EntityTableWrapper<LabOrderResponse>
        ref={tableRef}
        title="Lịch sử xét nghiệm"
        fetchData={fetchLabOrders}
        columns={columns}
        smartSearchFields={[
          "patient.fullName",
          "patient.patientCode",
          "orderCode",
          "status",
          "room.name",
          "requestedBy.fullName",
        ]}
        pageSize={10}
      />

      <ViewDetailDialog
        open={showView}
        onClose={setShowView}
        title="Chi tiết xét nghiệm"
        data={selectRow}
        width="max-w-2xl"
        columns={2}
        config={[
          // Group 1
          {
            groupTitle: "Thông tin chung",
            fields: [
              { label: "Mã phiếu", name: "orderCode", type: "text" },
              {
                label: "Bệnh nhân",
                name: "patient.fullName",
                type: "text",
              },
              {
                label: "Người chỉ định",
                name: "requestedBy.fullName",
                type: "text",
              },
              {
                label: "Trạng thái",
                name: "status",
                type: "enum",
                map: {
                  PENDING: "Chờ xử lý",
                  IN_PROGRESS: "Đang thực hiện",
                  COMPLETED: "Hoàn thành",
                  VERIFIED: "Đã duyệt",
                  CANCELLED: "Đã hủy",
                },
              },
              {
                label: "Phòng thực hiện",
                name: "room.name",
                type: "text",
              },
              {
                label: "Nhân viên thực hiện",
                name: "assignedStaff.fullName",
                type: "text",
              },
            ],
          },

          // Group 2 - Kết quả
          {
            groupTitle: "Kết quả xét nghiệm",
            fields: [
              {
                label: "Danh sách xét nghiệm",
                name: "results",
                type: "text",
                render: (results: any) => {
                  if (!results) return "-";
                  const list = Array.from(results);
                  return (
                    <ul className="list-disc ml-4 space-y-1">
                      {list.map((r: any) => (
                        <li key={r.id}>
                          <span className="font-semibold">{r.labTest?.name}</span>{" "}
                          | Giá trị: {r.resultValue ?? "-"} {r.unit ?? ""} <br />
                          <span className="text-sm text-muted-foreground">
                            Reference: {r.referenceRange ?? "N/A"}
                          </span>
                        </li>
                      ))}
                    </ul>
                  );
                },
              },
            ],
          },
        ]}
      />
    </>
  );
}
