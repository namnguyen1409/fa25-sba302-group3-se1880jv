import { useState, useRef } from "react";
import type { ExaminationResponse, ServiceOrderResponse } from "@/api";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";

import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";
import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { Column } from "@/components/common/ReuseAbleTable";
import { ServiceSearchModal } from "./ServiceSearchModal";

export default function ServiceOrdersTab({
  exam,
}: {
  exam: ExaminationResponse;
}) {
  const tableRef = useRef<any>(null);
  const [viewOrder, setViewOrder] = useState<any>(null);
  const [openServiceModal, setOpenServiceModal] = useState(false);

  const fetchData = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ) => {
    const data = await ExaminationApi.filterServiceOrders(
      exam.id!,
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );
    return { data };
  };

  const columns: Column<ServiceOrderResponse>[] = [
    {
      title: "Mã Order",
      dataIndex: "orderCode",
      sortable: true,
    },
    {
      title: "Phòng",
      dataIndex: "room.name",
      render: (_, row) => row.room?.name ?? "-",
    },
    {
      title: "Nhân viên",
      dataIndex: "assignedStaff.fullName",
      render: (_, row) => row.assignedStaff?.fullName ?? "-",
    },
    {
      title: "Trạng thái",
      dataIndex: "status",
      render: (v) => (
        <span className="px-2 py-1 text-xs rounded bg-blue-50 text-blue-700">
          {v}
        </span>
      ),
    },
  ];

  const detailConfig: any = [
    {
      fields: [
        { label: "Mã Order", name: "orderCode", type: "text" },
        { label: "Phòng thực hiện", name: "room.name" },
        { label: "Nhân viên nhận", name: "assignedStaff.fullName" },
        { label: "Trạng thái", name: "status" },
      ],
    },
    {
      groupTitle: "Danh sách dịch vụ",
      fields: [
        {
          label: "",
          name: "",
          render: (_: any, row: any) => (
            <ul className="space-y-1">
              {row.items?.map((it: any) => (
                <li
                  key={it.id}
                  className="flex justify-between border-b pb-1 text-sm"
                >
                  <span>{it.serviceName}</span>
                  <span className="text-gray-500">{it.status}</span>
                </li>
              ))}
            </ul>
          ),
        },
      ],
    },
  ];

  return (
    <div className="p-4">
      {/* TABLE */}
      <EntityTableWrapper
        ref={tableRef}
        title="Chỉ định cận lâm sàng"
        fetchData={fetchData}
        columns={columns}
        renderRowActions={(row) => (
          <Button size="sm" variant="outline" onClick={() => setViewOrder(row)}>
            Xem
          </Button>
        )}
        headerExtra={
          <Button onClick={() => setOpenServiceModal(true)}>Thêm dịch vụ</Button>
        }
      />

      {/* VIEW DETAIL */}
      <ViewDetailDialog
        open={!!viewOrder}
        onClose={() => setViewOrder(null)}
        title={`Chi tiết Order ${viewOrder?.orderCode ?? ""}`}
        data={viewOrder}
        config={detailConfig}
        columns={2}
      />
      <ServiceSearchModal
        open={openServiceModal}
        onClose={() => setOpenServiceModal(false)}
        onConfirm={async (services) => {
          await ExaminationApi.createServiceOrder(exam.id!, {
            serviceIds: services.map((s) => s.id),
          });

          toast.success("Tạo order thành công");
          tableRef.current?.reload();
        }}
      />
    </div>
  );
}
