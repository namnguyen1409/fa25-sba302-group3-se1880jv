"use client";

import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { toast } from "sonner";

import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse } from "@/api/models";

import { Card } from "@/components/ui/card";
import { Tabs, TabsList, TabsTrigger, TabsContent } from "@/components/ui/tabs";

import DynamicInfo from "@/components/common/DynamicInfo";


import ClinicalNoteTab from "./tabs/ClinicalNoteTab";
import VitalSignsTab from "./tabs/VitalSignsTab";
import ServiceOrdersTab from "./tabs/ServiceOrdersTab";
import PrescriptionTab from "./tabs/PrescriptionTab";
import LabOrdersTab from "./tabs/LabOrdersTab";
import DiagnosisTab from "./tabs/DiagnosisTab";

export default function ExaminationPage() {
  const { id } = useParams();
  const [exam, setExam] = useState<ExaminationResponse | null>(null);

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

  if (!exam) return <div className="p-6">Đang tải...</div>;

  return (
    <div className="grid grid-cols-12 gap-6 p-6">
      {/* -------- LEFT PANEL -------- */}
      <div className="col-span-4">
        <Card className="p-4">
          <h2 className="text-lg font-bold">Thông tin bệnh nhân</h2>
          <DynamicInfo
            data={exam.patient}
            columns={2}
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

      {/* -------- RIGHT PANEL -------- */}
      <div className="col-span-8">
        <Tabs defaultValue="clinical" className="w-full">
          <TabsList className="grid grid-cols-6 mb-4">
            <TabsTrigger value="clinical">Lâm sàng</TabsTrigger>
            <TabsTrigger value="icd">Chẩn đoán ICD</TabsTrigger>
            <TabsTrigger value="vitals">Sinh hiệu</TabsTrigger>
            <TabsTrigger value="services">Dịch vụ</TabsTrigger>
            <TabsTrigger value="prescription">Đơn thuốc</TabsTrigger>
            <TabsTrigger value="lab">Xét nghiệm</TabsTrigger>
          </TabsList>

          {/* CLINICAL NOTE */}
          <TabsContent value="clinical">
            <ClinicalNoteTab exam={exam} reload={load} />
          </TabsContent>

          {/* ICD */}
          <TabsContent value="icd">
            <DiagnosisTab exam={exam} />
          </TabsContent>

          {/* VITAL SIGNS */}
          <TabsContent value="vitals">
            <VitalSignsTab exam={exam} />
          </TabsContent>

          {/* SERVICES / SERVICE ORDERS */}
          <TabsContent value="services">
            <ServiceOrdersTab exam={exam} reload={load} />
          </TabsContent>

          {/* PRESCRIPTION */}
          <TabsContent value="prescription">
            <PrescriptionTab exam={exam} reload={load} />
          </TabsContent>

          {/* LAB ORDERS */}
          <TabsContent value="lab">
            <LabOrdersTab exam={exam} reload={load} />
          </TabsContent>
        </Tabs>
      </div>
    </div>
  );
}
