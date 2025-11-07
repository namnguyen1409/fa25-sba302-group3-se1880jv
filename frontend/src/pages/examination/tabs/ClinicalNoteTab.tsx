import { useState } from "react";
import { Button } from "@/components/ui/button";
import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse } from "@/api";

export default function ClinicalNoteTab({ exam, reload }
: { exam: ExaminationResponse; reload: () => Promise<void> }
) {
  const [symptom, setSymptom] = useState(exam.symptom || "");
  const [diagnosisSummary, setDiagnosisSummary] = useState(exam.diagnosisSummary || "");

  const save = async () => {
    await ExaminationApi.update(exam.id!, { symptom, diagnosisSummary });
    reload();
  };

  return (
    <div className="p-4 space-y-4">
      <div>
        <label className="font-bold">Triệu chứng</label>
        <textarea
          className="w-full p-3 border rounded"
          rows={4}
          value={symptom}
          onChange={(e) => setSymptom(e.target.value)}
        />
      </div>

      <div>
        <label className="font-bold">Tóm tắt chẩn đoán</label>
        <textarea
          className="w-full p-3 border rounded"
          rows={4}
          value={diagnosisSummary}
          onChange={(e) => setDiagnosisSummary(e.target.value)}
        />
      </div>

      <Button onClick={save}>Lưu</Button>
    </div>
  );
}
