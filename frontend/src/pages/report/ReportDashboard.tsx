"use client";

import { useState, useEffect } from "react";
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";
import { DateRangePicker } from "@/components/common/DateRangePicker";
import { ReportApi } from "@/api/report/ReportApi";

import {
  BarChartBox,
  LineChartBox,
  PieChartBox,
  StatsCard,
} from "./ReportWidgets";

import { Loader2 } from "lucide-react";
import dayjs from "dayjs";
import { toast } from "sonner";

export default function ReportDashboard() {
  const [loading, setLoading] = useState(true);

  const [todaySummary, setTodaySummary] = useState<any>(null);
  const [patientByHour, setPatientByHour] = useState([]);
  const [queueStatus, setQueueStatus] = useState({});
  const [specialty, setSpecialty] = useState([]);

  const [dateRange, setDateRange] = useState({
    from: dayjs().startOf("month").format("YYYY-MM-DD"),
    to: dayjs().format("YYYY-MM-DD"),
  });

  const [patientByDay, setPatientByDay] = useState([]);
  const [revenueDaily, setRevenueDaily] = useState([]);
  const [staffWorkload, setStaffWorkload] = useState([]);
  const [serviceUsage, setServiceUsage] = useState([]);

  const load = async () => {
    try {
      setLoading(true);

      const [
        summ,
        byHour,
        qStatus,
        spec,
        byDay,
        rev,
        workload,
        usage,
      ] = await Promise.all([
        ReportApi.getTodaySummary(),
        ReportApi.getPatientByHour(),
        ReportApi.getQueueStatusToday(),
        ReportApi.getSpecialtyDistribution(),
        ReportApi.getPatientByDay(dateRange.from, dateRange.to),
        ReportApi.getRevenueDaily(dateRange.from, dateRange.to),
        ReportApi.getStaffWorkload(dateRange.from, dateRange.to),
        ReportApi.getServiceUsage(dateRange.from, dateRange.to),
      ]);

      console.log("Report data loaded", {
        summ,
        byHour,
        qStatus,
        spec,
        byDay,
        rev,
        workload,
        usage,
      });

      setTodaySummary(summ);
      setPatientByHour(byHour);
      setQueueStatus(qStatus);
      setSpecialty(spec);
      setPatientByDay(byDay);
      setRevenueDaily(rev);
      setStaffWorkload(workload);
      setServiceUsage(usage);
    } catch (e) {
      toast.error("Không thể tải dữ liệu thống kê");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, [dateRange]);

  if (loading)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );

    /**
     * 
     * {
    "code": 200,
    "data": {
        "totalPatients": 7,
        "completedExams": 7,
        "waitingPatients": 0,
        "inService": 0,
        "invoices": 7,
        "totalRevenue": 34243208.00,
        "labOrders": 4,
        "imagingOrders": 0,
        "avgWaitingMinutes": 0
    },
    "timestamp": "2025-11-09T05:46:35.124523494Z",
    "path": "/api/admin/report/today"
}
     */

  return (
    <div className="p-6 space-y-6">
      {/* ====== Today Summary Cards ====== */}
      <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
        <StatsCard
          title="Tổng bệnh nhân"
          value={todaySummary?.totalPatients}
        />
        <StatsCard title="Đang chờ" value={todaySummary?.waitingPatients} />
        <StatsCard title="Đang khám" value={todaySummary?.inService} />
        <StatsCard
          title="Khám xong"
          value={todaySummary?.completedExams}
        />

        <StatsCard title="Số hóa đơn" value={todaySummary?.invoices} />
        <StatsCard
          title="Doanh thu"
          value={todaySummary?.totalRevenue.toLocaleString() + " đ"}
        />
        <StatsCard title="Xét nghiệm" value={todaySummary?.labOrders} />
        <StatsCard title="Chẩn đoán hình ảnh" value={todaySummary?.imagingOrders} />
      </div>

      {/* ====== Chart: Patient per hour ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Lượt bệnh nhân theo giờ</CardTitle>
        </CardHeader>
        <CardContent>
          <BarChartBox
            data={patientByHour.map((x: any) => ({
              hour: `${x.hour}h`,
              count: x.count,
            }))}
            dataKey="count"
            xKey="hour"
          />
        </CardContent>
      </Card>

      {/* ====== Queue Status Today (Pie) ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Trạng thái hàng chờ hôm nay</CardTitle>
        </CardHeader>
        <CardContent>
          <PieChartBox
            data={Object.entries(queueStatus).map(([key, value]) => ({
              name: key,
              value: value,
            }))}
          />
        </CardContent>
      </Card>

      {/* ====== Date Range Picker ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Khoảng thời gian</CardTitle>
        </CardHeader>
        <CardContent>
          <DateRangePicker
            value={dateRange}
            onChange={(v) => setDateRange(v)}
          />
        </CardContent>
      </Card>

      {/* ====== Patient By Day ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Bệnh nhân theo ngày</CardTitle>
        </CardHeader>
        <CardContent>
          <LineChartBox
            data={patientByDay.map((d: any) => ({
              date: `${d.day}/${d.month}`,
              count: d.count,
            }))}
            xKey="date"
            dataKey="count"
          />
        </CardContent>
      </Card>

      {/* ====== Revenue Daily ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Doanh thu theo ngày</CardTitle>
        </CardHeader>
        <CardContent>
          <LineChartBox
            data={revenueDaily.map((d: any) => ({
              date: `${d.day}/${d.month}`,
              revenue: Number(d.revenue),
            }))}
            xKey="date"
            dataKey="revenue"
          />
        </CardContent>
      </Card>

      {/* ====== Specialty Distribution ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Phân bố chuyên khoa</CardTitle>
        </CardHeader>
        <CardContent>
          <BarChartBox
            data={specialty}
            xKey="specialty"
            dataKey="count"
          />
        </CardContent>
      </Card>

      {/* ====== Staff Workload ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Khối lượng công việc nhân viên</CardTitle>
        </CardHeader>
        <CardContent>
          <BarChartBox
            data={staffWorkload.map((x: any) => ({
              name: x.fullName,
              workload: x.workload,
            }))}
            xKey="name"
            dataKey="workload"
          />
        </CardContent>
      </Card>

      {/* ====== Service Usage ====== */}
      <Card>
        <CardHeader>
          <CardTitle>Tần suất sử dụng dịch vụ</CardTitle>
        </CardHeader>
        <CardContent>
          <BarChartBox
            data={serviceUsage.map((x: any) => ({
              service: x.serviceName,
              used: x.usedCount,
            }))}
            xKey="service"
            dataKey="used"
          />
        </CardContent>
      </Card>
    </div>
  );
}
