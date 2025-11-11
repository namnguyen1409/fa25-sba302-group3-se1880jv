"use client";

import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "sonner";

import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse } from "@/api/models";

import { Card } from "@/components/ui/card";
import { Tabs, TabsList, TabsTrigger, TabsContent } from "@/components/ui/tabs";

import ClinicalNoteTab from "./tabs/ClinicalNoteTab";
import VitalSignsTab from "./tabs/VitalSignsTab";
import ServiceOrdersTab from "./tabs/ServiceOrdersTab";
import PrescriptionTab from "./tabs/PrescriptionTab";
import LabOrdersTab from "./tabs/LabOrdersTab";
import DiagnosisTab from "./tabs/DiagnosisTab";
import DynamicInfo from "@/components/common/DynamicInfo";
import { QueuePanel } from "./QueuePanel";

export default function ExaminationPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [exam, setExam] = useState<ExaminationResponse | null>(null);
  const [activeTab, setActiveTab] = useState("clinical");

  useEffect(() => {
    const saved = localStorage.getItem("exam-tab");
    if (saved) setActiveTab(saved);
  }, []);

  const load = async () => {
    try {
      const res = await ExaminationApi.getById(id!);
      setExam(res);
    } catch {
      toast.error("Không thể load dữ liệu khám");
    }
  };

  useEffect(() => {
    load();
  }, [id]);

  if (!exam) return <div className="p-6 text-center">Đang tải...</div>;

  return (
    <div className="grid grid-cols-12 gap-6 p-4 md:p-6">
      {/* Left: Patient Info */}
      <div className="col-span-12 md:col-span-4 xl:col-span-3 order-1">
        <Card className="p-4">
          <h2 className="text-lg font-semibold mb-3">Thông tin bệnh nhân</h2>

          <DynamicInfo
            data={exam.patient}
            columns={1}
            config={[
              { label: "Mã bệnh nhân", name: "patientCode" },
              { label: "Tên", name: "fullName" },
              { label: "Giới tính", name: "gender" },
              { label: "Ngày sinh", name: "dateOfBirth" },
              { label: "SĐT", name: "phone" },
              { label: "Địa chỉ", name: "address" },
              { label: "BHYT", name: "insuranceNumber" },
            ]}
          />
        </Card>
      </div>

      {/* Middle: Examination Content */}
      <div className="col-span-12 md:col-span-8 xl:col-span-6 order-3 xl:order-2">
        
        {/* Header + Action Buttons */}
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold">Khám bệnh</h2>

          {exam.status === "COMPLETED" && (
            <div className="flex gap-2">
              {exam.prescription?.dispenseRecordId && (
                <button
                  onClick={() =>
                    navigate(`/staff/dispense/${exam.prescription?.dispenseRecordId}`)
                  }
                  className="px-3 py-2 bg-blue-600 text-white rounded hover:bg-blue-700"
                >
                  In đơn thuốc
                </button>
              )}

              {exam.invoiceId && (
                <button
                  onClick={() => navigate(`/staff/billing/${exam.invoiceId}`)}
                  className="px-3 py-2 bg-green-600 text-white rounded hover:bg-green-700"
                >
                  In hóa đơn
                </button>
              )}
            </div>
          )}
        </div>

        {/* Tabs */}
        <Tabs
          value={activeTab}
          onValueChange={(v) => {
            setActiveTab(v);
            localStorage.setItem("exam-tab", v);
          }}
          className="w-full"
        >
          <TabsList
            className="
              grid grid-cols-3 sm:grid-cols-4 md:grid-cols-6 
              gap-1 mb-4 
              overflow-x-auto scrollbar-thin
              bg-muted p-1 rounded
            "
          >
            <TabsTrigger value="clinical">Lâm sàng</TabsTrigger>
            <TabsTrigger value="icd">Chẩn đoán ICD</TabsTrigger>
            <TabsTrigger value="vitals">Sinh hiệu</TabsTrigger>
            <TabsTrigger value="services">Dịch vụ</TabsTrigger>
            <TabsTrigger value="prescription">Đơn thuốc</TabsTrigger>
            <TabsTrigger value="lab">Xét nghiệm</TabsTrigger>
          </TabsList>

          <TabsContent value="clinical">
            <ClinicalNoteTab key={exam.id} exam={exam} reload={load} />
          </TabsContent>

          <TabsContent value="icd">
            <DiagnosisTab key={exam.id} exam={exam} />
          </TabsContent>

          <TabsContent value="vitals">
            <VitalSignsTab key={exam.id} exam={exam} />
          </TabsContent>

          <TabsContent value="services">
            <ServiceOrdersTab key={exam.id} exam={exam} />
          </TabsContent>

          <TabsContent value="prescription">
            <PrescriptionTab key={exam.id} exam={exam} />
          </TabsContent>

          <TabsContent value="lab">
            <LabOrdersTab key={exam.id} exam={exam} />
          </TabsContent>
        </Tabs>
      </div>

      {/* Right: Queue Panel */}
      <div className="col-span-12 md:col-span-12 xl:col-span-3 order-2 xl:order-3">
        <QueuePanel currentExamId={exam.id} />
      </div>
    </div>
  );
}
