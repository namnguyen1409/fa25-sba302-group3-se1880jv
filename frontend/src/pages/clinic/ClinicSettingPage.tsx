"use client";

import type { ClinicResponse } from "@/api/models/ClinicResponse";
import { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardHeader,
  CardTitle,
  CardContent,
} from "@/components/ui/card";
import { Separator } from "@/components/ui/separator";
import { Skeleton } from "@/components/ui/skeleton";
import { toast } from "sonner";

import * as yup from "yup";
import { Pencil, MapPin, Building2, Phone, Globe, CreditCard } from "lucide-react";

import { FormModal } from "@/components/common/FormModal";
import { useClinic } from "@/context/ClinicContext";
import { clinicApi } from "@/api/clinicApi";

export default function ClinicProfilePage() {
  const [clinic, setClinic] = useState<ClinicResponse | null>(null);
  const [loading, setLoading] = useState(true);
  const [openModal, setOpenModal] = useState(false);

  const { setClinicInfo } = useClinic();

  // Load dữ liệu
  useEffect(() => {
    (async () => {
      try {
        setLoading(true);
        const data = await clinicApi.getClinicInfo();
        setClinic(data);
        setClinicInfo(data);
      } catch {
        toast.error("Không thể tải thông tin phòng khám");
      } finally {
        setLoading(false);
      }
    })();
  }, []);

  // Loading UI
  if (loading) {
    return (
      <div className="p-4 max-w-3xl mx-auto">
        <Card>
          <CardHeader>
            <Skeleton className="h-6 w-40" />
          </CardHeader>
          <CardContent className="space-y-4">
            <Skeleton className="h-14 w-full" />
            <Skeleton className="h-10 w-1/2" />
            <Skeleton className="h-10 w-1/2" />
          </CardContent>
        </Card>
      </div>
    );
  }

  if (!clinic) return null;

  // Form fields
  const formFields = [
    { name: "name", label: "Tên phòng khám", type: "text" as const, required: true },
    { name: "description", label: "Mô tả", type: "textarea" as const },
    { name: "phone", label: "Số điện thoại", type: "text" as const, required: true },
    { name: "email", label: "Email", type: "text" as const },
    { name: "taxCode", label: "Mã số thuế", type: "text" as const },
    { name: "website", label: "Website", type: "text" as const },
    { name: "accountNumber", label: "Số tài khoản", type: "text" as const },
    { name: "bankName", label: "Ngân hàng", type: "text" as const },
    { name: "street", label: "Địa chỉ", type: "text" as const },
    { name: "districtName", label: "Quận/Huyện", type: "text" as const },
    { name: "city", label: "Tỉnh/Thành phố", type: "text" as const },
  ];

  const schema = yup.object({
    name: yup.string().required("Tên phòng khám là bắt buộc"),
    phone: yup.string().required("Số điện thoại là bắt buộc"),
    email: yup.string().nullable(),
    taxCode: yup.string().nullable(),
    website: yup.string().nullable(),
    accountNumber: yup.string().nullable(),
    bankName: yup.string().nullable(),
    street: yup.string().nullable(),
    districtName: yup.string().nullable(),
    city: yup.string().nullable(),
  });

  const handleSubmit = async (data: any) => {
    try {
      const payload = {
        name: data.name,
        description: data.description,
        phone: data.phone,
        email: data.email,
        taxCode: data.taxCode,
        website: data.website,
        accountNumber: data.accountNumber,
        bankName: data.bankName,
        address: {
          street: data.street,
          districtName: data.districtName,
          city: data.city,
        },
      };

      const updated = await clinicApi.updateClinic(clinic.id!, payload);
      toast.success("Cập nhật phòng khám thành công!");

      setClinic(updated);
      setClinicInfo(updated);
      setOpenModal(false);
    } catch (e: any) {
      toast.error("Lỗi khi cập nhật thông tin");
    }
  };

  return (
    <div className="p-4 max-w-3xl mx-auto">
      <Card>
        <CardHeader className="flex justify-between items-center">
          <CardTitle>Thông tin phòng khám</CardTitle>
          <Button size="sm" onClick={() => setOpenModal(true)}>
            <Pencil className="w-4 h-4 mr-2" /> Chỉnh sửa
          </Button>
        </CardHeader>

        <CardContent className="space-y-4 bg-muted/30 rounded-lg p-6">
          <div className="space-y-3">
            <div className="text-lg font-bold flex items-center gap-2">
              <Building2 className="w-5 h-5" /> {clinic.name}
            </div>

            {clinic.description && (
              <p className="text-muted-foreground">{clinic.description}</p>
            )}

            <Separator />

            <div className="text-sm space-y-2">
              <p className="flex items-center gap-2">
                <Phone className="w-4 h-4" /> {clinic.phone}
              </p>

              {clinic.email && (
                <p className="flex items-center gap-2">
                  ✉️ {clinic.email}
                </p>
              )}

              {clinic.website && (
                <p className="flex items-center gap-2">
                  <Globe className="w-4 h-4" /> {clinic.website}
                </p>
              )}

              <p className="flex items-center gap-2">
                <MapPin className="w-4 h-4" />
                {[
                  clinic.address?.street,
                  clinic.address?.districtName,
                  clinic.address?.city,
                ]
                  .filter(Boolean)
                  .join(", ") || "Chưa cập nhật"}
              </p>

              <p className="flex items-center gap-2">
                <CreditCard className="w-4 h-4" /> STK: {clinic.accountNumber} — {clinic.bankName}
              </p>

              {clinic.taxCode && <p>MST: {clinic.taxCode}</p>}
            </div>
          </div>
        </CardContent>
      </Card>

      {/* FORM UPDATE */}
      <FormModal
        open={openModal}
        onClose={() => setOpenModal(false)}
        title="Cập nhật thông tin phòng khám"
        defaultValues={{
          ...clinic,
          street: clinic.address?.street,
          districtName: clinic.address?.districtName,
          city: clinic.address?.city,
        }}
        formFields={formFields}
        big
        schema={schema}
        onSubmit={handleSubmit}
      />
    </div>
  );
}
