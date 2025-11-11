"use client";

import { useEffect, useRef, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Card } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Loader2, FileDown, Check } from "lucide-react";
import { toast } from "sonner";

import { useReactToPrint } from "react-to-print";
import { useClinic } from "@/context/ClinicContext";
import { PharmacyApi } from "@/api/pharmacy/PharmacyApi";
import type { DispenseRecordResponse } from "@/api/models/DispenseRecordResponse";

export default function DispenseRecordDetailPage() {
  const { id } = useParams();
  const [record, setRecord] = useState<DispenseRecordResponse | null>(null);

  const { clinicInfo } = useClinic();
  const printRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();

  const handlePrint = useReactToPrint({
    contentRef: printRef,
  });

  // Load data
  useEffect(() => {
    async function load() {
      try {
        const res = await PharmacyApi.getDispenseRecord(id!);
        setRecord(res);
      } catch {
        toast.error("Không thể tải đơn thuốc.");
      }
    }
    load();
  }, [id]);

  const markDone = async () => {
    try {
      await PharmacyApi.markAsDispensed(record!.id!);
      toast.success("Đã phát thuốc xong.");
    } catch {
      toast.error("Không thể cập nhật trạng thái.");
    }
  };

  if (!record)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );

  const { patient, prescription, dispensedBy, room } = record;

  return (
    <div className="p-6 flex justify-center">
      <Button
        variant="link"
        className="mb-4 p-0 underline"
        onClick={() => navigate(-1)}
      > 
        &larr; Quay lại
      </Button>
      <Card className="p-6 w-[700px] shadow">
        <div className="flex justify-between items-center mb-4">
          <h1 className="text-xl font-bold">ĐƠN THUỐC</h1>
          <Badge variant="secondary">{record.status}</Badge>
        </div>

        <div ref={printRef}>
          {/* ----------- CLINIC INFO ----------- */}
          <div className="text-center mb-4">
            <div className="font-bold text-lg">{clinicInfo?.name}</div>
            <div className="text-muted-foreground text-sm">
              {clinicInfo?.address?.street}, {clinicInfo?.address?.districtName}
              , {clinicInfo?.address?.city}
            </div>
            <div>Điện thoại: {clinicInfo?.phone}</div>
          </div>

          {/* ----------- PATIENT INFO ----------- */}
          <div className="border p-4 rounded mb-6 text-sm">
            <div>
              <b>Bệnh nhân:</b> {patient?.fullName}
            </div>
            <div>
              <b>Mã BN:</b> {patient?.patientCode}
            </div>
            <div>
              <b>Địa chỉ:</b> {patient?.address}
            </div>
          </div>
          {/* ----------- Thong tin nguoi pp ----------- */}
          <div className="mb-6 text-sm">
            <div>
              <b>Người phát:</b> {dispensedBy?.fullName}
            </div>
            <div>
              <b>Phòng:</b> {room?.name}
            </div>
            <div>
              <b>Ngày phát:</b> {new Date(record.dispensedAt!).toLocaleString()}
            </div>
          </div>

          {/* ----------- PRESCRIPTION LIST ----------- */}
          <div className="mb-3 font-semibold">Danh sách thuốc</div>

          <table className="w-full border-collapse text-sm">
            <thead>
              <tr className="border">
                <th className="border p-2 w-12 text-center">STT</th>
                <th className="border p-2">Tên thuốc</th>
                <th className="border p-2 w-32">Liều dùng</th>
                <th className="border p-2 w-40">Hướng dẫn</th>
                <th className="border p-2 w-16 text-center">SL</th>
              </tr>
            </thead>

            <tbody>
              {prescription?.items?.map((it, idx) => (
                <tr key={it.id} className="border">
                  <td className="border p-2 text-center">{idx + 1}</td>
                  <td className="border p-2">{it.medicine?.name}</td>
                  <td className="border p-2">{it.dosage}</td>
                  <td className="border p-2">{it.instruction}</td>
                  <td className="border p-2 text-center">{it.quantity}</td>
                </tr>
              ))}
            </tbody>
          </table>

          {/* ----------- FOOTER NOTE ----------- */}
          <div className="mt-4 text-xs italic text-muted-foreground">
            Vui lòng kiểm tra thuốc trước khi rời quầy.
          </div>
        </div>

        {/* ----------- ACTION BUTTONS ----------- */}
        <div className="flex justify-end gap-3 mt-6">
          <Button onClick={handlePrint}>
            <FileDown className="h-4 w-4 mr-2" />
            In đơn
          </Button>

          {record.status !== "DISPENSED" && (
            <Button variant="default" onClick={markDone}>
              <Check className="h-4 w-4 mr-2" />
              Đã phát xong
            </Button>
          )}
        </div>
      </Card>
    </div>
  );
}
