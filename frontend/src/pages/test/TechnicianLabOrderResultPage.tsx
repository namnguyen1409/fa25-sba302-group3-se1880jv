import { useEffect, useState, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";

import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { Button } from "@/components/ui/button";
import { Loader2 } from "lucide-react";
import { toast } from "sonner";

import type { LabOrderResponse, LabTestResultResponse } from "@/api";
import { LabOrderApi } from "@/api/lab/LabOrderApi";
import { LabResultApi } from "@/api/lab/LabResultApi";

import { EntityTableWrapper } from "@/components/common/EntityTableWrapper";
import type { Column } from "@/components/common/ReuseAbleTable";

import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";

import { useAuth } from "@/context/AuthContext";

export default function TechnicianLabOrderResultPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const { user } = useAuth();
  const readonly = user?.staff?.staffType !== "LAB_TECHNICIAN";

  const [order, setOrder] = useState<LabOrderResponse | null>(null);
  const [loading, setLoading] = useState(true);

  const [openModal, setOpenModal] = useState(false);
  const [selectedTest, setSelectedTest] =
    useState<LabTestResultResponse | null>(null);
  const [savedLocal, setSavedLocal] = useState(false);

  const [formData, setFormData] = useState({
    resultValue: "",
    unit: "",
    referenceRange: "",
    remark: "",
  });

  const tableRef = useRef<any>(null);

  useEffect(() => {
    if (!id) return;

    LabOrderApi.getById(id)
      .then((res) => setOrder(res))
      .catch(() => toast.error("Không tải được order"))
      .finally(() => setLoading(false));
  }, [id]);

  const fetchData = async (page: any, size: any, filterGroup: any, sorts: any) => {
    if (!id) {
      return {
        data: {
          content: [],
          page: { size: 0, number: 0, totalElements: 0, totalPages: 0 },
        },
      };
    }

    const res = await LabResultApi.filterByLabOrder(
      id,
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );

    return { data: res };
  };

  const columns: Column<LabTestResultResponse>[] = [
    {
      title: "Xét nghiệm",
      dataIndex: "labTest.name",
      render: (_, row) => row.labTest?.name ?? "-",
    },
    {
      title: "Kết quả",
      dataIndex: "resultValue",
      render: (v) =>
        v || <span className="text-muted-foreground">Chưa có</span>,
    },
    {
      title: "Đơn vị",
      dataIndex: "unit",
      render: (_, row) => row.unit ?? row.labTest?.unit ?? "-",
    },
    {
      title: "Khoảng tham chiếu",
      dataIndex: "referenceRange",
      render: (v) =>
        v || <span className="text-muted-foreground">Chưa có</span>,
    },
    {
      title: "Ghi chú",
      dataIndex: "remark",
      render: (v) =>
        v || <span className="text-muted-foreground">Chưa có</span>,
    },
    {
      title: "Trạng thái",
      dataIndex: "status",
      render: (v) => <Badge>{v}</Badge>,
    },
  ];

  const openResultModal = (row: LabTestResultResponse) => {
    setSelectedTest(row);
    setFormData({
      resultValue: row.resultValue || "",
      unit: row.unit || "",
      referenceRange: row.referenceRange || row.labTest?.referenceRange || "",
      remark: row.remark || "",
    });
    setSavedLocal(false);
    setOpenModal(true);
  };

  const saveResult = async () => {
    if (!selectedTest) return;
    if (readonly) return;

    try {
      await LabResultApi.update(selectedTest.id!, {
        resultValue: formData.resultValue,
        unit: formData.unit,
        referenceRange: formData.referenceRange,
        remark: formData.remark,
      });

      toast.success("Đã lưu kết quả xét nghiệm");

      const updated = await LabOrderApi.getById(id!);
      setOrder(updated);

      tableRef.current?.reload();

      const refreshed =
        updated.results?.find((r: any) => r.id === selectedTest.id) ?? null;

      setSelectedTest(refreshed);
      setSavedLocal(false);
    } catch {
      toast.error("Không thể lưu kết quả");
    }
  };

  // ============================
  // Verify result
  // ============================
  const verifyResult = async () => {
    if (!selectedTest) return;
    if (readonly) return;

    try {
      await LabResultApi.verify(selectedTest.id!);
      toast.success("Kết quả đã được xác nhận");

      const updated = await LabOrderApi.getById(id!);
      setOrder(updated);

      tableRef.current?.reload();

      const refreshed =
        updated.results?.find((r: any) => r.id === selectedTest.id) ?? null;

      setSelectedTest(refreshed);
      setSavedLocal(false);
    } catch {
      toast.error("Không thể xác nhận kết quả");
    }
  };

  if (loading)
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );

  if (!order) return <div className="p-6">Order không tồn tại</div>;

  return (
    <div className="p-6 space-y-6">
      <Button variant="outline" onClick={() => navigate(-1)}>
        ← Quay lại
      </Button>

      <Card>
        <CardHeader>
          <CardTitle className="flex justify-between">
            <span>Kết quả xét nghiệm — {order.orderCode}</span>
            <Badge>{order.status}</Badge>
          </CardTitle>
        </CardHeader>

        <CardContent>
          <EntityTableWrapper
            ref={tableRef}
            title="Danh sách xét nghiệm"
            columns={columns}
            fetchData={fetchData}
            renderRowActions={(row) =>
              !readonly && row.status !== "VERIFIED" ? (
                <Button
                  size="sm"
                  variant="outline"
                  onClick={() => openResultModal(row)}
                >
                  Nhập kết quả
                </Button>
              ) : null
            }
          />
        </CardContent>
      </Card>

      <Dialog open={openModal} onOpenChange={setOpenModal}>
        <DialogContent className="max-w-xl">
          <DialogHeader>
            <DialogTitle>
              Nhập kết quả — {selectedTest?.labTest?.name}
            </DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-2">
            <div>
              <label className="font-medium">Kết quả</label>
              <Input
                value={formData.resultValue}
                disabled={readonly || selectedTest?.status === "VERIFIED"}
                onChange={(e) =>
                  setFormData({ ...formData, resultValue: e.target.value })
                }
              />
            </div>

            <div>
              <label className="font-medium">Đơn vị</label>
              <Input value={formData.unit} disabled />
            </div>

            <div>
              <label className="font-medium">Khoảng tham chiếu</label>
              <Input value={formData.referenceRange} disabled />
            </div>

            <div>
              <label className="font-medium">Ghi chú</label>
              <Textarea
                value={formData.remark}
                disabled={readonly || selectedTest?.status === "VERIFIED"}
                onChange={(e) =>
                  setFormData({ ...formData, remark: e.target.value })
                }
              />
            </div>
          </div>

          <DialogFooter className="space-x-2">
            <Button variant="outline" onClick={() => setOpenModal(false)}>
              Hủy
            </Button>

            {!readonly && selectedTest?.status !== "VERIFIED" && (
              <>
                <Button onClick={saveResult}>Lưu kết quả</Button>
                <Button variant="ghost" onClick={verifyResult} className="ml-2">
                  Xác nhận
                </Button>
              </>
            )}
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
