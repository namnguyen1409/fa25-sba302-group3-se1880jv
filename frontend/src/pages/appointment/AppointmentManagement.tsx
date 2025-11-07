// src/pages/staff/AppointmentManagementPage.tsx
import React, { useCallback, useMemo, useRef, useState } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import type { Column } from "@/components/common/ReuseAbleTable";
import { Button } from "@/components/ui/button";
import { Eye, FileDown, Plus } from "lucide-react";
import * as yup from "yup";
import { toast } from "sonner";
import { useReactToPrint } from "react-to-print";

import { PatientApi } from "@/api/patient/PatientApi";

import type { FormFieldConfig } from "@/components/common/FormModal";
import { FormModal } from "@/components/common/FormModal";
import { SearchSelect } from "@/components/common/SearchSelect";
import { Card } from "@/components/ui/card";
import type { AppointmentRequest, AppointmentResponse } from "@/api";
import { AppointmentApi } from "@/api/appointment/AppointmentApi";
import { SpecialtyApi } from "@/api/specialty/SpecialtyApi";

const TicketPrint = React.forwardRef<
  HTMLDivElement,
  { data: AppointmentResponse | null }
>(({ data }, ref) => {
  if (!data) return null;
  const q = data.queueTicket;
  return (
    <div ref={ref} className="p-6 w-[320px] text-sm">
      <div className="text-center font-semibold text-base">PHIẾU KHÁM</div>
      <div className="mt-2 border-t pt-3 space-y-1">
        <div>
          <b>Số thứ tự:</b> {q?.queueNumber ?? "-"}
        </div>
        <div>
          <b>Phòng:</b> {q?.assignedRoom?.name ?? "-"}
        </div>
        <div>
          <b>Tầng:</b> {q?.assignedRoom?.floorNumber ?? "-"}
        </div>
        <div>
          <b>Bác sĩ:</b> {q?.assignedDoctor?.fullName ?? "-"}
        </div>
        <div>
          <b>Bệnh nhân:</b> {data.patient?.fullName ?? "-"}
        </div>
        <div>
          <b>Mã BN:</b> {data.patient?.patientCode ?? "-"}
        </div>
        <div>
          <b>Chuyên khoa:</b> {data.specialty?.name ?? "-"}
        </div>
        <div>
          <b>Mã phiếu:</b> {q?.queueNumber ?? "-"}
        </div>
        <div className="text-xs text-muted-foreground mt-2">
          Vui lòng ngồi chờ đến khi được gọi. Xin cảm ơn!
        </div>
      </div>
    </div>
  );
});
TicketPrint.displayName = "TicketPrint";

// ====== fetcher for table ======
const fetchAppointments = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<AppointmentResponse> }> => {
  try {
    const res = await AppointmentApi.getAppointments(
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );
    return { data: res };
  } catch (e) {
    toast.error("Không tải được danh sách lịch hẹn");
    throw e;
  }
};

export default function AppointmentManagementPage() {
  const tableRef = useRef<any>(null);

  const [formOpen, setFormOpen] = useState(false);
  const [createdApp, setCreatedApp] = useState<AppointmentResponse | null>(
    null
  );

  // for printing
  const printRef = useRef<HTMLDivElement>(null);
  const handlePrint = useReactToPrint({ contentRef: printRef });

  const fetchData = useCallback(fetchAppointments, []);

  const columns: Column<AppointmentResponse>[] = useMemo(
    () => [
      {
        title: "Bệnh nhân",
        dataIndex: "patient",
        sortable: true,
        render: (v: AppointmentResponse["patient"]) => v?.fullName ?? "-",
      },
      {
        title: "Chuyên khoa",
        dataIndex: "specialty",
        sortable: true,
        render: (v: AppointmentResponse["specialty"]) => v?.name ?? "-",
      },
      {
        title: "Bác sĩ",
        dataIndex: "queueTicket",
        render: (q) => q?.assignedDoctor?.fullName ?? "-",
      },
      {
        title: "Phòng",
        dataIndex: "queueTicket",
        render: (q) => q?.assignedRoom?.name ?? "-",
      },
      {
        title: "Số thứ tự",
        dataIndex: "queueTicket",
        render: (q) => q?.queueNumber ?? "-",
      },
      { title: "Trạng thái", dataIndex: "status", sortable: true },
      { title: "Nguồn", dataIndex: "source", sortable: true },
      { title: "Ghi chú", dataIndex: "note" },
    ],
    []
  );

  const formFields: FormFieldConfig[] = [
    {
      name: "patientId",
      label: "Bệnh nhân",
      type: "select",
      required: true,
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Tìm kiếm bệnh nhân"
          fetchOptions={async (keyword) => {
            const res = await PatientApi.search(keyword);
            return res.content.map((d) => ({
              value: String(d.id),
              label: d.fullName!,
            }));
          }}
        />
      ),
    },
    {
      name: "specialtyId",
      label: "Chuyên khoa",
      type: "select",
      required: true,
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Chọn chuyên khoa"
          fetchOptions={async (keyword) => {
            const res = await SpecialtyApi.search(keyword);
            return res.content.map((s) => ({
              value: String(s.id),
              label: s.name!,
            }));
          }}
        />
      ),
    },
    {
      name: "type",
      label: "Loại khám",
      type: "select",
      required: true,
      options: [
        { value: "CONSULTATION", label: "Khám mới" },
        { value: "FOLLOW_UP", label: "Tái khám" },
        { value: "EMERGENCY", label: "Cấp cứu" },
      ],
      defaultValue: "CONSULTATION",
    },
    {
      name: "status",
      label: "Trạng thái",
      type: "select",
      required: true,
      options: [
        { value: "SCHEDULED", label: "Đã đặt" },
        { value: "CHECKED_IN", label: "Đã check-in" },
      ],
      defaultValue: "CHECKED_IN",
    },
    {
      name: "source",
      label: "Nguồn",
      type: "select",
      required: true,
      options: [
        { value: "WALK_IN", label: "Tới trực tiếp" },
        { value: "PHONE", label: "Gọi điện" },
        { value: "ONLINE", label: "Đặt online" },
      ],
      defaultValue: "WALK_IN",
    },
    { name: "note", label: "Ghi chú", type: "textarea" },
  ];

  const schema = yup.object({
    patientId: yup.string().required("Vui lòng chọn bệnh nhân"),
    specialtyId: yup.string().required("Vui lòng chọn chuyên khoa"),
    type: yup.string().required(),
    status: yup.string().required(),
    source: yup.string().required(),
    note: yup.string().nullable(),
  });

  const handleCreate = async (data: any) => {
    const payload: AppointmentRequest = {
      patientId: data.patientId,
      specialtyId: data.specialtyId,
      type: data.type,
      status: data.status,
      source: data.source,
      note: data.note || undefined,
    };
    try {
      const res = await AppointmentApi.createAppointment(payload);
      setCreatedApp(res);
      toast.success("Tạo lịch hẹn + phát số hàng đợi thành công");
      setFormOpen(false);
      tableRef.current?.reload();
    } catch (e: any) {
      toast.error(e?.response?.data?.message || "Tạo lịch hẹn thất bại");
    }
  };

  return (
    <>
      <EntityTableWrapper<AppointmentResponse>
        ref={tableRef}
        title="Quản lý lịch hẹn"
        fetchData={fetchData}
        columns={columns}
        pageSize={10}
        smartSearchFields={[
          "appointmentCode",
          "patient.fullName",
          "patient.phone",
          "specialty.name",
          "queueTicket.assignedDoctor.fullName",
        ]}
        headerExtra={
          <Button onClick={() => setFormOpen(true)}>
            <Plus className="h-4 w-4 mr-2" /> Tạo lịch hẹn
          </Button>
        }
        renderRowActions={(row) => (
          <Button
            variant="outline"
            size="sm"
            onClick={() => setCreatedApp(row)}
          >
            <Eye className="h-4 w-4 mr-1" /> Xem phiếu
          </Button>
        )}
        footerExtra={(ctx) => (
          <div>
            <b>Tổng lịch hẹn:</b> {ctx.totalElements}
          </div>
        )}
      />

      {/* Create modal */}
      <FormModal
        open={formOpen}
        onClose={() => setFormOpen(false)}
        title="Tạo lịch hẹn & Check-in"
        formFields={formFields}
        defaultValues={{
          type: "EXAMINATION",
          status: "CHECKED_IN",
          source: "WALK_IN",
        }}
        schema={schema}
        big
        onSubmit={handleCreate}
      />

      {/* Ticket preview + print */}
      {createdApp && (
        <div className="fixed inset-0 z-50 bg-black/30 flex items-center justify-center">
          <Card className="p-4 w-[380px] bg-background">
            <div className="font-semibold mb-2">Phiếu khám vừa tạo</div>
            <div className="border rounded-md p-3">
              <TicketPrint ref={printRef} data={createdApp} />
            </div>
            <div className="flex justify-end gap-2 mt-4">
              <Button variant="outline" onClick={() => setCreatedApp(null)}>
                Đóng
              </Button>
              <Button onClick={handlePrint}>
                <FileDown className="h-4 w-4 mr-2" /> In phiếu
              </Button>
            </div>
          </Card>
        </div>
      )}
    </>
  );
}
