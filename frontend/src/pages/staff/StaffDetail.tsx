import React from "react";
import { useParams } from "react-router-dom";
import { Tabs, TabsList, TabsTrigger, TabsContent } from "@/components/ui/tabs";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { toast } from "sonner";

import { type StaffResponse } from "@/api";
import { StaffApi } from "@/api/staff/StaffApi";
import StaffScheduleTemplateTable from "./StaffScheduleTemplateTable";
import StaffScheduleTable from "./StaffScheduleTable";


export default function StaffDetailPage() {
  const { id } = useParams();
  const [staff, setStaff] = React.useState<StaffResponse | null>(null);

  React.useEffect(() => {
    const fetchStaff = async () => {
      try {
        const res = await StaffApi.getStaffById(id!);
        setStaff(res);
      } catch {
        toast.error("Không tải được thông tin nhân viên");
      }
    };
    fetchStaff();
  }, [id]);

  if (!staff) return <div className="p-4">Đang tải...</div>;

  return (
    <div className="p-6 space-y-6">
      {/* HEADER */}
      <Card>
        <CardHeader>
          <CardTitle>Thông tin nhân viên</CardTitle>
        </CardHeader>
        <CardContent className="grid grid-cols-2 gap-4">
          <div><strong>Họ tên:</strong> {staff.fullName}</div>
          <div><strong>Email:</strong> {staff.email}</div>
          <div><strong>Điện thoại:</strong> {staff.phone}</div>
          <div><strong>Chức danh:</strong> {staff.position?.title}</div>
          <div><strong>Khoa:</strong> {staff.department?.name}</div>
        </CardContent>
      </Card>

      {/* TABS */}
      <Tabs defaultValue="template" className="mt-6">
        <TabsList>
          <TabsTrigger value="template">Lịch mẫu (Weekly Template)</TabsTrigger>
          <TabsTrigger value="schedule">Lịch làm việc (Actual)</TabsTrigger>
        </TabsList>

        {/* TAB 1 */}
        <TabsContent value="template">
          <StaffScheduleTemplateTable staffId={staff.id!} />
        </TabsContent>

        {/* TAB 2 */}
        <TabsContent value="schedule">
          <StaffScheduleTable staffId={staff.id!} />
        </TabsContent>
      </Tabs>
    </div>
  );
}
