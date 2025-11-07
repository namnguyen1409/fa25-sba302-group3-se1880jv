import type { ExaminationResponse } from "@/api";
import { Button } from "@/components/ui/button";

export default function ServiceOrdersTab({ exam, reload }: { exam: ExaminationResponse; reload: () => Promise<void> }) {
  return (
    <div className="p-4">
      <h3 className="font-bold mb-3">Danh sách dịch vụ</h3>

      {exam.serviceOrders?.map((o) => (
        <div key={o.id} className="border p-3 mb-3 rounded">
          <div className="font-bold">{o.orderCode}</div>

          {o.items.map((i) => (
            <div key={i.id} className="ml-4 p-2 border-b">
              ✅ {i.service.name} – {i.price}đ
            </div>
          ))}
        </div>
      ))}

      <Button className="mt-4">Thêm dịch vụ</Button>
    </div>
  );
}
