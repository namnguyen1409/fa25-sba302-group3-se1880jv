"use client";

import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { InvoiceApi } from "@/api/billing/InvoiceApi";
import type { InvoiceResponse } from "@/api";
import { Loader2 } from "lucide-react";
import { useClinic } from "@/context/ClinicContext";

export default function BillingDetailPage() {
  const { id } = useParams();
  const [invoice, setInvoice] = useState<InvoiceResponse | null>(null);

  const {clinicInfo} = useClinic();

  const load = async () => {
    try {
      const res = await InvoiceApi.getById(id!);
      setInvoice(res);
    } catch {
      console.error("Cannot load invoice");
    }
  };

  useEffect(() => {
    load();
  }, [id]);

  if (!invoice)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );

  const { patient, items } = invoice;

  return (
    <div className="p-6">
      <Card className="p-6 shadow">
        {/* Header */}
        <div className="text-center mb-6">
          <h1 className="text-xl font-bold">HÓA ĐƠN GIÁ TRỊ GIA TĂNG</h1>
          <p className="italic text-muted-foreground">
            (Bản thể hiện của hóa đơn điện tử)
          </p>
        </div>

        {/* Seller Info */}
        <div className="border p-4 rounded mb-6 text-sm">
          <div><b>Đơn vị bán hàng:</b> {clinicInfo?.name}</div>
          <div><b>Mã số thuế:</b> {clinicInfo?.taxCode}</div>
          <div><b>Địa chỉ:</b> {clinicInfo?.address?.street} - {clinicInfo?.address?.districtName} - {clinicInfo?.address?.city}</div>
          <div><b>SĐT:</b> {clinicInfo?.phone} &nbsp; <b>Website:</b> {clinicInfo?.website}</div>
          <div>
            <b>Số tài khoản:</b> {clinicInfo?.accountNumber} — <b>Ngân hàng:</b> {clinicInfo?.bankName}
          </div>
        </div>

        {/* Buyer Info */}
        <div className="border p-4 rounded mb-6 text-sm">
          <div><b>Tên khách hàng:</b> {patient?.fullName}</div>
          <div><b>Mã bệnh nhân:</b> {patient?.patientCode}</div>
          <div><b>Địa chỉ:</b> {patient?.address}</div>
          <div><b>Hình thức thanh toán:</b> TM</div>
        </div>

        {/* Table */}
        <table className="w-full border-collapse text-sm">
          <thead>
            <tr className="border">
              <th className="border p-2 w-12 text-center">STT</th>
              <th className="border p-2">Tên hàng hóa, dịch vụ</th>
              <th className="border p-2 w-24 text-center">ĐVT</th>
              <th className="border p-2 w-20 text-center">SL</th>
              <th className="border p-2 w-32 text-right">Đơn giá</th>
              <th className="border p-2 w-32 text-right">Thành tiền</th>
            </tr>
          </thead>

          <tbody>
            {items?.map((item, idx) => (
              <tr key={item.id} className="border">
                <td className="border p-2 text-center">{idx + 1}</td>
                <td className="border p-2">{item.description}</td>
                <td className="border p-2 text-center">Lần</td>
                <td className="border p-2 text-center">{item.quantity}</td>
                <td className="border p-2 text-right">
                  {item.unitPrice.toLocaleString()}
                </td>
                <td className="border p-2 text-right">
                  {item.totalPrice.toLocaleString()}
                </td>
              </tr>
            ))}
          </tbody>

          <tfoot>
            <tr className="border font-semibold">
              <td colSpan={5} className="border p-2 text-right">
                Tổng cộng:
              </td>
              <td className="border p-2 text-right">
                {invoice?.totalAmount?.toLocaleString()}
              </td>
            </tr>
          </tfoot>
        </table>

        <div className="mt-6 flex justify-end">
          <Button variant="outline" onClick={() => window.print()}>
            In hóa đơn
          </Button>
        </div>
      </Card>
    </div>
  );
}
