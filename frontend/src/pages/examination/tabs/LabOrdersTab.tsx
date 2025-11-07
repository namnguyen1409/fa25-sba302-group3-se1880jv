import type { ExaminationResponse } from "@/api";
import { Button } from "@/components/ui/button";

export default function LabOrdersTab({ exam, reload }: { exam: ExaminationResponse; reload: () => Promise<void> }) {
  return (
    <div className="p-4">
      <h3 className="font-bold mb-3">Xét nghiệm</h3>

      {exam.labOrders?.map((l) => (
        <div key={l.id} className="border p-3 mb-2 rounded">
          Mã: {l.orderCode}
        </div>
      ))}

      <Button className="mt-4">Chỉ định xét nghiệm</Button>
    </div>
  );
}
