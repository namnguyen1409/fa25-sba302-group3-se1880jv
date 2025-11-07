import type { ExaminationResponse } from "@/api";
import { Button } from "@/components/ui/button";

export default function PrescriptionTab({ exam, reload }: { exam: ExaminationResponse; reload: () => Promise<void> }) {
  const prescription = exam.prescription;

  return (
    <div className="p-4">
      <h3 className="font-bold mb-3">Đơn thuốc</h3>

      {prescription?.items?.map((i) => (
        <div key={i.id} className="border p-3 mb-2 rounded">
          {i.medicine.name} — {i.dosage} / {i.frequency}
        </div>
      ))}

      <Button className="mt-4">Thêm thuốc</Button>
    </div>
  );
}
