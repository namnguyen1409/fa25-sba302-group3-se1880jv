import React from "react";

export const ServiceOrderPrintTable = React.forwardRef<
  HTMLDivElement,
  { order: any | null }
>(({ order }, ref) => {
  if (!order) return null;

  const items = Array.from(order.items ?? []);

  return (
    <div ref={ref} className="p-6 w-[400px] text-sm">
      <div className="text-center font-semibold text-base">
        PHIẾU CHỈ ĐỊNH DỊCH VỤ
      </div>

      <div className="mt-2 border-t pt-3 space-y-1">
        <div><b>Mã order:</b> {order.orderCode}</div>
        <div><b>Phòng thực hiện:</b> {order.room?.name}</div>
        <div><b>Tầng:</b> {order.room?.floorNumber}</div>
        <div><b>KTV/Bác sĩ:</b> {order.assignedStaff?.fullName}</div>
        <div><b>Bệnh nhân:</b> {order.examinationPatient?.fullName}</div>
        <div><b>Mã BN:</b> {order.examinationPatient?.patientCode}</div>

        <div className="mt-3 font-semibold">Danh mục dịch vụ</div>

        <table className="w-full text-xs mt-1 border border-collapse">
          <thead >
            <tr>
              <th className="border  p-1 w-8">#</th>
              <th className="border  p-1">Dịch vụ</th>
              <th className="border  p-1 w-16">Giá</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item: any, i: number) => (
              <tr key={item.id}>
                <td className="border  p-1 text-center">
                  {i + 1}
                </td>
                <td className="border  p-1">
                  {item.service?.name}
                </td>
                <td className="border  p-1 text-right">
                  {item.price?.toLocaleString()}đ
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        <div className="text-xs text-muted-foreground mt-3 italic">
          Vui lòng mang phiếu này đến đúng phòng để thực hiện dịch vụ.
        </div>
      </div>
    </div>
  );
});

ServiceOrderPrintTable.displayName = "ServiceOrderPrintTable";
