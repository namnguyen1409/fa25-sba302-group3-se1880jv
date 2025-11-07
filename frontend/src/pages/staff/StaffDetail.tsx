// StaffDetailPage.tsx
import React from "react";
import { useParams } from "react-router-dom";
import { Tabs, TabsList, TabsTrigger, TabsContent } from "@/components/ui/tabs";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { toast } from "sonner";

import { StaffApi } from "@/api/staff/StaffApi";
import StaffScheduleTemplateTable from "./StaffScheduleTemplateTable";
import StaffScheduleCalendar from "./StaffScheduleCalendar";

export default function StaffDetailPage() {
  const { id } = useParams();
  const [staff, setStaff] = React.useState<any>(null);

  React.useEffect(() => {
    StaffApi.getStaffById(id!)
      .then(setStaff)
      .catch(() => toast.error("Không tải được thông tin nhân viên"));
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
        </CardContent>
      </Card>

      {/* TABS */}
      <Tabs defaultValue="template" className="space-y-4">
        <TabsList>
          <TabsTrigger value="template">Lịch mẫu (Weekly)</TabsTrigger>
          <TabsTrigger value="schedule">Lịch thực tế (Daily)</TabsTrigger>
        </TabsList>

        <TabsContent value="template">
          <StaffScheduleTemplateTable staffId={staff.id!} />
        </TabsContent>

        <TabsContent value="schedule">
          <div className="space-y-4">
            
            <StaffScheduleCalendar staffId={staff.id!} staff={staff} />
          </div>
        </TabsContent>
      </Tabs>
    </div>
  );
}
