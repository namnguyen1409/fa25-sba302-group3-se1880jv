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
import type { ServiceOrderResponse } from "@/api/models/ServiceOrderResponse";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";
import { ServiceOrderApi } from "@/api/service/ServiceOrderApi";

export default function ServiceOrderHistoryPage() {
  const tableRef = React.useRef<any>(null);

  const [selectRow, setSelectRow] = React.useState<ServiceOrderResponse | null>(
    null
  );
  const [showView, setShowView] = React.useState(false);

  // ✅ Backend bạn tự implement API lịch sử
  const fetchServiceOrders = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<ServiceOrderResponse> }> => {
    try {
      const res = await ServiceOrderApi.filter(page, size, filterGroup, sorts);
      return { data: res };
    } catch (err) {
      console.error(err);
      toast.error("Không tải được lịch sử dịch vụ");
      throw err;
    }
  };

  const handleView = (row: ServiceOrderResponse) => {
    setSelectRow(row);
    setShowView(true);
  };

  const columns: Column<ServiceOrderResponse>[] = [
    {
      title: "Mã phiếu",
      dataIndex: "orderCode",
      sortable: true,
    },
    {
      title: "Bệnh nhân",
      dataIndex: "examination.patient.fullName",
      render: (_v: any, row) => (
        <div className="max-w-40 truncate">
          {row.examinationPatient?.fullName ??
            row.examinationPatient?.patientCode}
        </div>
      ),
    },
    {
      title: "Nhân viên thực hiện",
      dataIndex: "assignedStaff.fullName",
      render: (_v: any, row) => (
        <div className="max-w-40 truncate">
          {row.assignedStaff?.fullName ?? "-"}
        </div>
      ),
    },
    {
      title: "Phòng",
      dataIndex: "room.name",
        render: (_v: any, row) => (
            <div className="max-w-40 truncate">{row.room?.name ?? "-"}</div>    
        ),
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
          CANCELLED: "Đã hủy",
        };
        return map[v] ?? v;
      },
    },
    {
      title: "Số dịch vụ",
      dataIndex: "items",
      render: (items) => (items ? Array.from(items).length : 0),
    },
    {
      title: "Hành động",
      dataIndex: "actions",
      sortable: false,
      render: (_: any, row: ServiceOrderResponse) => (
        <div className="flex items-center">
          <Button
            variant="outline"
            size="sm"
            onClick={() => handleView(row)}
          >
            <Eye className="w-4 h-4" />
          </Button>
        </div>
      ),
    },
  ];

  return (
    <>
      <EntityTableWrapper<ServiceOrderResponse>
        ref={tableRef}
        title="Lịch sử dịch vụ"
        fetchData={fetchServiceOrders}
        columns={columns}
        smartSearchFields={[
          "orderCode",
          "examination.patient.fullName",
          "examination.patient.patientCode",
          "assignedStaff.fullName",
          "status",
        ]}
        pageSize={10}
      />

      <ViewDetailDialog
        open={showView}
        onClose={setShowView}
        title="Chi tiết dịch vụ"
        data={selectRow}
        width="max-w-2xl"
        columns={2}
        config={[
          // ---- THÔNG TIN CHUNG ----
          {
            groupTitle: "Thông tin chung",
            fields: [
              { label: "Mã phiếu", name: "orderCode", type: "text" },
              {
                label: "Bệnh nhân",
                name: "examinationPatient.fullName",
                type: "text",
              },
              {
                label: "Nhân viên thực hiện",
                name: "assignedStaff.fullName",
                type: "text",
              },
              { label: "Phòng", name: "room.name", type: "text" },
              {
                label: "Trạng thái",
                name: "status",
                type: "enum",
                map: {
                  PENDING: "Chờ xử lý",
                  IN_PROGRESS: "Đang thực hiện",
                  COMPLETED: "Hoàn thành",
                  CANCELLED: "Đã hủy",
                },
              },
            ],
          },

          // ---- DANH SÁCH ITEM ----
          {
            groupTitle: "Danh sách dịch vụ",
            fields: [
              {
                name: "items",
                type: "text",
                render: (items: any) => {
                  if (!items) return "-";
                  const list = Array.from(items);
                  return (
                    <ul className="list-disc ml-4 space-y-1">
                      {list.map((it: any) => (
                        <li key={it.id}>
                          <span className="font-semibold">
                            {it.serviceCatalog?.name}
                          </span>
                          {" — "}
                          {it.serviceCatalog?.category}
                          <br />
                          <span className="text-sm text-muted-foreground">
                            Giá:{" "}
                            {it.serviceCatalog?.price?.toLocaleString("vi-VN")}đ
                          </span>
                          {it.note && (
                            <>
                              <br />
                              <span className="text-sm italic opacity-80">
                                Ghi chú: {it.note}
                              </span>
                            </>
                          )}
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
