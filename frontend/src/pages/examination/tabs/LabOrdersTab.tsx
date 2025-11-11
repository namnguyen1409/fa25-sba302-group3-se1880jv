import { useRef, useState } from "react";
import { Button } from "@/components/ui/button";
import { FileDown } from "lucide-react";

import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";

import type { Column } from "@/components/common/ReuseAbleTable";
import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse, LabOrderResponse } from "@/api";

import { LabTestSearchModal } from "./LabTestSearchModal";
import { LabOrderPrintTable } from "./LabOrderPrintTable";
import { Card } from "@/components/ui/card";
import { useReactToPrint } from "react-to-print";
import { toast } from "sonner";
import { useNavigate } from "react-router-dom";

export default function LabOrdersTab({ exam }: { exam: ExaminationResponse }) {
  const tableRef = useRef<any>(null);
  const navigate = useNavigate();

  const [openModal, setOpenModal] = useState(false);
  const [orderToPrint, setOrderToPrint] = useState<LabOrderResponse | null>(
    null
  );

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
    const res = await ExaminationApi.filterLabOrders(
      exam.id!,
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );
    return { data: res };
  };

  const columns: Column<LabOrderResponse>[] = [
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
    {
      title: "Số test",
      dataIndex: "results.length",
      render: (_, row) => row.results?.length ?? 0,
    },
  ];

  return (
    <div className="p-4 space-y-4">
      <EntityTableWrapper<LabOrderResponse>
        ref={tableRef}
        title="Xét nghiệm"
        fetchData={fetchData}
        columns={columns}
        renderRowActions={(row) => (
          <>
            <Button
              size="sm"
              variant="outline"
              className="mr-2"
              onClick={() => navigate(`/staff/lab/orders/${row.id}`)}
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
        headerExtra={<Button onClick={() => setOpenModal(true)}>Thêm chỉ định</Button>}
      />

      {/* Modal thêm test */}
      <LabTestSearchModal
        open={openModal}
        onClose={() => setOpenModal(false)}
        onConfirm={async (tests) => {
          try {
            await ExaminationApi.createLabOrder(exam.id!, {
              labTestIds: tests.map((s) => s.id),
            });

            toast.success("Tạo Lab Order thành công");
            setOpenModal(false);
            tableRef.current?.reload();
          } catch {
            toast.error("Không thể tạo lab order");
          }
        }}
      />

      {/* Popup In phiếu */}
      {orderToPrint && (
        <div className="fixed inset-0 z-50 bg-black/30 flex items-center justify-center">
          <Card className="p-4 w-[520px] bg-background">
            <div className="font-semibold mb-2">Phiếu chỉ định xét nghiệm</div>

            <div className="border rounded-md p-3">
              <LabOrderPrintTable ref={printRef} order={orderToPrint} />
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
