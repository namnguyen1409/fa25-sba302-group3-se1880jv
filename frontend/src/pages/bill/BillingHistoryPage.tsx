"use client";

import React from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type PageResponse,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";

import type { Column } from "@/components/common/ReuseAbleTable";
import type { InvoiceResponse } from "@/api/models/InvoiceResponse";

import { Button } from "@/components/ui/button";
import { Eye } from "lucide-react";
import { toast } from "sonner";
import { InvoiceApi } from "@/api/billing/InvoiceApi";
import { useNavigate } from "react-router-dom";

export default function BillingHistoryPage() {
  const tableRef = React.useRef<any>(null);
  const navigate = useNavigate();

  // ✅ Fetch lịch sử thu phí
  const fetchInvoices = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<InvoiceResponse> }> => {
    try {
      const res = await InvoiceApi.filter(page, size, filterGroup, sorts);
      return { data: res };
    } catch (err) {
      toast.error("Không thể tải lịch sử thu phí");
      throw err;
    }
  };

  // ✅ Các cột để hiển thị trong table
  const columns: Column<InvoiceResponse>[] = [
    {
      title: "Số hóa đơn",
      dataIndex: "invoiceNumber",
      sortable: true,
    },
    {
      title: "Bệnh nhân",
      dataIndex: "patient.fullName",
      sortable: true,
      render: (_v, row) => (
        <div className="max-w-40 truncate">
          {row.patient?.fullName ?? "-"}
        </div>
      ),
    },
    {
      title: "Mã BN",
      dataIndex: "patient.patientCode",
      sortable: true,
    },
    {
      title: "Ngày lập",
      dataIndex: "issueDate",
      sortable: true,
      render: (v) => (v ? new Date(v).toLocaleString() : "-"),
    },
    {
      title: "Tổng tiền",
      dataIndex: "totalAmount",
      sortable: true,
      render: (v) => (v ? v.toLocaleString() + " đ" : "-"),
    },
    {
      title: "Trạng thái",
      dataIndex: "paid",
      sortable: true,
      render: (v: boolean) => (
        <span className={v ? "text-green-600" : "text-red-600"}>
          {v ? "Đã thanh toán" : "Chưa thanh toán"}
        </span>
      ),
    },
    {
      title: "Nhân viên thu",
      dataIndex: "assignedStaff.fullName",
      sortable: true,
      render: (v) => v ?? "-",
    },

    // ✅ Nút xem chi tiết
    {
      title: "Hành động",
      dataIndex: "actions",
      render: (_v, row) => (
        <Button
          variant="outline"
          size="sm"
          onClick={() => navigate(`/staff/billing/${row.id}`)}
        >
          <Eye className="w-4 h-4" />
        </Button>
      ),
    },
  ];

  return (
    <>
      <EntityTableWrapper<InvoiceResponse>
        ref={tableRef}
        title="Lịch sử thu phí"
        fetchData={fetchInvoices}
        columns={columns}
        pageSize={10}
        smartSearchFields={[
          "invoiceNumber",
          "patient.fullName",
          "patient.patientCode",
          "assignedStaff.fullName",
        ]}
      />
    </>
  );
}
