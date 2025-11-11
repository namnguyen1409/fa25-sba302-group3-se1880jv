import { useState, useRef } from "react";
import { Button } from "@/components/ui/button";
import { FileDown, Plus } from "lucide-react";

import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import type { Column } from "@/components/common/ReuseAbleTable";

import { useReactToPrint } from "react-to-print";
import { Card } from "@/components/ui/card";

import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type {
  ServiceOrderResponse,
} from "@/api";

import { ServiceOrderPrintTable } from "./ServiceOrderPrintTable";
import { useNavigate } from "react-router-dom";
import { ServiceSearchModal } from "./ServiceSearchModal";
import { toast } from "sonner";
import type { ExaminationResponse } from "@/api/models";

export default function ServiceOrdersTab({
  exam,
}: {
  exam: ExaminationResponse;
}) {
  const tableRef = useRef<any>(null);
  const navigate = useNavigate();

  const [orderToPrint, setOrderToPrint] =
    useState<ServiceOrderResponse | null>(null);

  const [openServiceModal, setOpenServiceModal] = useState(false);

  const printRef = useRef<HTMLDivElement>(null);
  const handlePrint = useReactToPrint({
    contentRef: printRef,
  });

  const fetchData = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ) => {
    const res = await ExaminationApi.filterServiceOrders(
      exam.id!,
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );
    return { data: res };
  };

  const columns: Column<ServiceOrderResponse>[] = [
    {
      title: "Mã Order",
      dataIndex: "orderCode",
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

  return (
    <div className="p-4 space-y-4">
      <EntityTableWrapper<ServiceOrderResponse>
        ref={tableRef}
        title="Chỉ định cận lâm sàng"
        fetchData={fetchData}
        columns={columns}
        headerExtra={
          <Button onClick={() => setOpenServiceModal(true)}>
            <Plus className="mr-2 h-4 w-4" /> Thêm dịch vụ
          </Button>
        }
        renderRowActions={(row) => (
          <>
            <Button
              size="sm"
              variant="outline"
              className="mr-2"
              onClick={() => navigate(`/staff/service/orders/${row.id}`)}
            >
              Xem
            </Button>

            <Button
              size="sm"
              variant="secondary"
              onClick={() => setOrderToPrint(row)}
            >
              In phiếu
            </Button>
          </>
        )}
      />

      {/* ✅ Modal chọn dịch vụ */}
      <ServiceSearchModal
        open={openServiceModal}
        onClose={() => setOpenServiceModal(false)}
        onConfirm={async (services) => {
          try {
            await ExaminationApi.createServiceOrder(exam.id!, {
              serviceIds: services.map((s) => s.id),
            });
            toast.success("Tạo order thành công");

            setOpenServiceModal(false);
            tableRef.current?.reload();
          } catch {
            toast.error("Không thể tạo order");
          }
        }}
      />

      {/* ✅ Popup In phiếu */}
      {orderToPrint && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/30">
          <Card className="p-4 w-[500px] bg-background shadow-lg">
            <div className="font-semibold mb-2">
              Phiếu chỉ định dịch vụ
            </div>

            <div className="border rounded-md p-3">
              <ServiceOrderPrintTable ref={printRef} order={orderToPrint} />
            </div>

            <div className="flex justify-end gap-2 mt-4">
              <Button variant="outline" onClick={() => setOrderToPrint(null)}>
                Đóng
              </Button>
              <Button onClick={handlePrint}>
                <FileDown className="h-4 w-4 mr-2" /> In phiếu
              </Button>
            </div>
          </Card>
        </div>
      )}
    </div>
  );
}
