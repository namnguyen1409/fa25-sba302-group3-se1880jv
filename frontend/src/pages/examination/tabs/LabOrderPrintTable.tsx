// src/pages/staff/examination/LabOrderPrintTable.tsx
import React from "react";
import type { LabOrderResponse } from "@/api";

export const LabOrderPrintTable = React.forwardRef<
  HTMLDivElement,
  { order: LabOrderResponse | null }
>(({ order }, ref) => {
  if (!order) return null;

  return (
    <div ref={ref} className="p-6 w-[480px] text-sm">
      <div className="text-center font-bold text-lg">PHIẾU CHỈ ĐỊNH XÉT NGHIỆM</div>

      <div className="mt-4 border-t pt-3 space-y-1">
        <div>
          <b>Mã Order:</b> {order.orderCode}
        </div>
        <div>
          <b>Bệnh nhân:</b> {order.patient?.fullName ?? "-"}
        </div>
        <div>
          <b>Mã BN:</b> {order.patient?.patientCode ?? "-"}
        </div>
        <div>
          <b>Phòng xét nghiệm:</b> {order.room?.name ?? "-"}
        </div>
        <div>
          <b>Nhân viên thực hiện:</b> {order.assignedStaff?.fullName ?? "-"}
        </div>
        <div>
          <b>Trạng thái:</b> {order.status}
        </div>
      </div>

      <div className="mt-4">
        <b>Danh sách xét nghiệm:</b>
        <table className="w-full text-xs mt-2 border-collapse">
          <thead>
            <tr className="border-b">
              <th className="pb-1 text-left">Tên xét nghiệm</th>
              <th className="pb-1 text-left">Loại</th>
            </tr>
          </thead>
          <tbody>
            {order.results?.map((r) => (
              <tr key={r.id} className="border-b">
                <td className="py-1">{r.labTest?.name}</td>
                <td className="py-1">{r.labTest?.category}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className="text-xs text-muted-foreground mt-4 text-center">
        Vui lòng đến phòng xét nghiệm theo hướng dẫn. Xin cảm ơn!
      </div>
    </div>
  );
});

LabOrderPrintTable.displayName = "LabOrderPrintTable";
