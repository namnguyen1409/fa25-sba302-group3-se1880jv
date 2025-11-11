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
import type { DispenseRecordResponse } from "@/api";
import { useAuth } from "@/context/AuthContext";

export default function DispenseRecordDetailPage() {
  const { id } = useParams();
  const { user } = useAuth();

  const [record, setRecord] = useState<DispenseRecordResponse | null>(null);

  const { clinicInfo } = useClinic();
  const printRef = useRef<HTMLDivElement>(null);
  const navigate = useNavigate();

  const handlePrint = useReactToPrint({
    contentRef: printRef,
    documentTitle: "Don_Thuoc",
  });

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

      <Card className="p-6 w-[760px] shadow border">
        {/* Header */}
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold tracking-wide">ĐƠN THUỐC</h1>
          <Badge variant={record.status === "DISPENSED" ? "default" : "secondary"}>
            {record.status}
          </Badge>
          <Button
            variant="link"
            className="mb-4 p-0 underline"
            onClick={() => navigate(-1)}
          >
            &larr; Quay lại
          </Button>
        </div>

        <div ref={printRef} className="print:p-0">
          {/* Clinic Info */}
          <div className="text-center mb-6">
            <div className="font-bold text-xl">{clinicInfo?.name}</div>
            <div className="text-muted-foreground text-sm">
              {clinicInfo?.address?.street}, {clinicInfo?.address?.districtName},{" "}
              {clinicInfo?.address?.city}
            </div>
            <div className="text-sm">Điện thoại: {clinicInfo?.phone}</div>
          </div>

          {/* Patient Info */}
          <div className="border p-4 rounded mb-6 text-sm bg-white">
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-2">
              <div>
                <b>Bệnh nhân:</b> {patient?.fullName}
              </div>
              <div>
                <b>Mã BN:</b> {patient?.patientCode}
              </div>
              <div className="sm:col-span-2">
                <b>Địa chỉ:</b> {patient?.address}
              </div>
            </div>
          </div>

          {/* Dispense Info */}
          <div className="border p-4 rounded mb-6 text-sm bg-white">
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-2">
              <div>
                <b>Người phát:</b> {dispensedBy?.fullName}
              </div>
              <div>
                <b>Phòng:</b> {room?.name}
              </div>
              <div className="sm:col-span-2">
                <b>Ngày phát:</b>{" "}
                {new Date(record.dispensedAt!).toLocaleString()}
              </div>
            </div>
          </div>

          {/* Medicine List */}
          <div className="mb-3 font-semibold text-base">Danh sách thuốc</div>

          <table className="w-full border-collapse text-sm print:text-[12px]">
            <thead>
              <tr className="border bg-gray-100">
                <th className="border p-2 text-center w-12">#</th>
                <th className="border p-2">Tên thuốc</th>
                <th className="border p-2 w-32">Liều dùng</th>
                <th className="border p-2 w-40">Hướng dẫn</th>
                <th className="border p-2 text-center w-16">SL</th>
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

          <div className="mt-4 text-xs italic text-muted-foreground text-center">
            Vui lòng kiểm tra thuốc trước khi rời quầy.
          </div>
        </div>

        {/* Action Buttons */}
        {user?.staff && (
          <div className="flex justify-end gap-3 mt-6">
            <Button onClick={handlePrint}>
              <FileDown className="h-4 w-4 mr-2" />
              In đơn
            </Button>

            {user.staff.id === record.dispensedBy?.id &&
              record.status !== "DISPENSED" && (
                <Button variant="default" onClick={markDone}>
                  <Check className="h-4 w-4 mr-2" />
                  Đã phát xong
                </Button>
              )}
          </div>
        )}
      </Card>
    </div>
  );
}
