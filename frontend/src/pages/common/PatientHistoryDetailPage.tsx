"use client";

import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import {
  Loader2,
  Stethoscope,
  FileText,
  FlaskConical,
  HeartPulse,
  Receipt,
  ArrowLeft,
} from "lucide-react";
import { toast } from "sonner";

import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse } from "@/api";

export default function PatientHistoryDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [exam, setExam] = useState<ExaminationResponse | null>(null);
  const [loading, setLoading] = useState(true);

  const load = async () => {
    try {
      const res = await ExaminationApi.getById(id!);
      setExam(res);
    } catch {
      toast.error("Không thể tải chi tiết khám.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, [id]);

  if (loading) {
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );
  }

  if (!exam) {
    return (
      <div className="p-6 text-center text-muted-foreground">
        Không tìm thấy thông tin khám.
      </div>
    );
  }

  return (
    <div className="p-6 max-w-3xl mx-auto space-y-6">
      {/* BACK BUTTON */}
      <button
        onClick={() => navigate(-1)}
        className="flex items-center gap-1 text-sm text-blue-600 hover:underline"
      >
        <ArrowLeft className="h-4 w-4" /> Quay lại
      </button>

      {/* HEADER */}
      <div className="flex items-center justify-between">
        <h1 className="text-2xl font-bold">Chi tiết khám chữa bệnh</h1>
        <Badge variant={exam.status === "COMPLETED" ? "default" : "secondary"}>
          {exam.status}
        </Badge>
      </div>

      <div className="text-sm text-muted-foreground">
        Ngày khám: <b>{exam.examinationDate?.toLocaleString()}</b>
      </div>

      {/* SECTION: Patient info */}
      <Card className="p-4">
        <h2 className="font-semibold text-lg mb-2 flex items-center gap-2">
          <Stethoscope className="h-5 w-5 text-blue-600" />
          Thông tin khám
        </h2>

        <div className="space-y-1 text-sm">
          <div>
            <b>Bác sĩ khám:</b> {exam.staff?.fullName}
          </div>
          <div>
            <b>Loại khám:</b> {exam.type?.replace("_", " ")}
          </div>
          {exam.symptom && (
            <div>
              <b>Triệu chứng:</b> {exam.symptom}
            </div>
          )}
          {exam.diagnosisSummary && (
            <div>
              <b>Chẩn đoán:</b> {exam.diagnosisSummary}
            </div>
          )}
        </div>
      </Card>

      {exam.diagnoses && exam.diagnoses.length > 0 && (
        <Card className="p-4">
          <h2 className="font-semibold text-lg mb-2">Mã ICD chẩn đoán</h2>
          <ul className="list-disc ml-5 text-sm space-y-1">
            {exam.diagnoses.map((d) => (
              <li key={d.id}>
                <b>{d.icdCode?.code}</b> — {d.note}
              </li>
            ))}
          </ul>
        </Card>
      )}

      {exam.vitalSigns && exam.vitalSigns.length > 0 && (
        <Card className="p-4">
          <h2 className="font-semibold text-lg mb-2 flex items-center gap-2">
            <HeartPulse className="h-5 w-5 text-red-500" />
            Sinh hiệu
          </h2>
          <div className="grid grid-cols-2 gap-3 text-sm">
            {exam.vitalSigns.map((v) => (
              <div key={v.id} className="space-y-1">
                <div>
                  Huyết áp: <b>{v.bloodPressure}</b>
                </div>
                <div>
                  Nhiệt độ: <b>{v.temperature} °C</b>
                </div>
                <div>
                  Mạch: <b>{v.pulse} lần/phút</b>
                </div>
                <div>
                  Nhịp thở: <b>{v.respirationRate} lần/phút</b>
                </div>
                <div>
                  Chiều cao: <b>{v.height} cm</b>
                </div>
                <div>
                  Cân nặng: <b>{v.weight} kg</b>
                </div>
                <div>
                  Thời gian ghi nhận:{" "}
                  <b>{new Date(v.createdDate!).toLocaleString()}</b>
                </div>
              </div>
            ))}
          </div>
        </Card>
      )}

      {exam.serviceOrders && exam.serviceOrders.length > 0 && (
        <Card className="p-4">
          <h2 className="font-semibold text-lg mb-2">Dịch vụ đã thực hiện</h2>
          <ul className="list-disc ml-5 text-sm space-y-1">
            {exam.serviceOrders.map((s) => (
              <li key={s.id}>
                {s.orderCode}
                {" — "}
                {s.items?.map((it) => it.service?.name).join(", ")}
              </li>
            ))}
          </ul>
        </Card>
      )}

      {exam.labOrders && exam.labOrders.length > 0 && (
        <Card className="p-4">
          <h2 className="font-semibold text-lg mb-2 flex items-center gap-2">
            <FlaskConical className="h-5 w-5 text-purple-600" />
            Xét nghiệm
          </h2>

          <ul className="list-disc ml-5 text-sm space-y-1">
            {exam.labOrders.map((lab) => (
              <li key={lab.id}>
                {lab.orderCode} —{" "}
                {lab.results?.map((it) => it.labTest?.name).join(", ")}
              </li>
            ))}
          </ul>
        </Card>
      )}

      {exam.prescription && (
        <Card className="p-4">
          <h2 className="font-semibold text-lg mb-3 flex items-center gap-2">
            <FileText className="h-5 w-5 text-blue-700" />
            Đơn thuốc
          </h2>

          <ul className="list-disc ml-5 text-sm space-y-1">
            {exam.prescription.items?.map((it) => (
              <li key={it.id}>
                {it.medicine?.name} — {it.dosage} — SL: {it.quantity}
              </li>
            ))}
          </ul>

          <button
            className="mt-3 text-sm text-blue-600 underline"
            onClick={() =>
              navigate(`/patient/prescription/${exam.prescription?.dispenseRecordId}`)
            }
          >
            Xem chi tiết đơn thuốc
          </button>
        </Card>
      )}

      {exam.invoiceId && (
        <Card className="p-4">
          <h2 className="font-semibold text-lg mb-2 flex items-center gap-2">
            <Receipt className="h-5 w-5 text-green-600" />
            Hóa đơn
          </h2>

          <button
            onClick={() => navigate(`/patient/invoice/${exam.invoiceId}`)}
            className="text-blue-600 text-sm underline"
          >
            Xem hóa đơn
          </button>
        </Card>
      )}
    </div>
  );
}
