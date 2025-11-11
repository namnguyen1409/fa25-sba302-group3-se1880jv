"use client";

import { useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Card } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import {
  Loader2,
  Stethoscope,
  FileText,
  FlaskConical,
  Receipt,
} from "lucide-react";
import { toast } from "sonner";

import { ExaminationApi } from "@/api/examination/ExaminationApi";
import type { ExaminationResponse } from "@/api";
import type { PageResponse } from "@/components/common/EntityTableWrapper";
import { useAuth } from "@/context/AuthContext";

export default function PatientHistoryPage() {
  const { user } = useAuth();
  const navigate = useNavigate();

  const [items, setItems] = useState<ExaminationResponse[]>([]);
  const [_, setPage] = useState(0);
  const [loading, setLoading] = useState(true);
  const [loadingMore, setLoadingMore] = useState(false);
  const [hasMore, setHasMore] = useState(true);

  const load = async (pageIndex: number) => {
    try {
      const res: PageResponse<ExaminationResponse> =
        await ExaminationApi.getExaminations(
          pageIndex,
          20,
          {
            filters: [
              { field: "patient.id", operator: "eq", value: user?.patient?.id },
            ],
          },
          [{ field: "examinationDate", direction: "DESC" }]
        );

      // merge items
      if (pageIndex === 0) {
        setItems(res.content);
      } else {
        setItems((prev) => [...prev, ...res.content]);
      }
      const isLast = res.page.number + 1 >= res.page.totalPages;
      setHasMore(!isLast);

    } catch {
      toast.error("Không thể tải lịch sử khám.");
    } finally {
      setLoading(false);
      setLoadingMore(false);
    }
  };

  useEffect(() => {
    load(0);
  }, []);

  const bottomRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    if (!bottomRef.current) return;

    const observer = new IntersectionObserver(
      (entries) => {
        const first = entries[0];
        if (first.isIntersecting && hasMore && !loadingMore) {
          setLoadingMore(true);
          setPage((p) => {
            const next = p + 1;
            load(next);
            return next;
          });
        }
      },
      { threshold: 1 }
    );

    observer.observe(bottomRef.current);
    return () => observer.disconnect();
  }, [hasMore, loadingMore]);

  if (loading) {
    return (
      <div className="flex justify-center py-20">
        <Loader2 className="animate-spin h-10 w-10" />
      </div>
    );
  }

  if (!items.length) {
    return (
      <div className="p-6 text-center text-muted-foreground">
        Bạn chưa có lịch sử khám bệnh nào.
      </div>
    );
  }

  return (
    <div className="p-6 max-w-3xl mx-auto space-y-4">
      <h1 className="text-2xl font-bold mb-4">Lịch sử khám chữa bệnh</h1>

      {items.map((exam) => (
        <Card
          key={exam.id}
          className="p-4 hover:shadow-md transition cursor-pointer"
          onClick={() => navigate(`/patient/history/${exam.id}`)}
        >
          <div className="flex justify-between items-start mb-2">
            <div className="flex items-center gap-2">
              <Stethoscope className="h-5 w-5 text-blue-600" />
              <h2 className="font-semibold text-lg">
                {exam.type?.replace("_", " ")}
              </h2>
            </div>

            <Badge
              variant={exam.status === "COMPLETED" ? "default" : "secondary"}
            >
              {exam.status}
            </Badge>
          </div>

          <div className="text-sm text-muted-foreground mb-2">
            Ngày khám: <b>{exam.examinationDate?.toLocaleString()}</b>
          </div>

          <div className="text-sm mb-1">
            <b>Bác sĩ:</b> {exam.staff?.fullName}
          </div>

          {exam.diagnosisSummary && (
            <div className="text-sm mb-1">
              <b>Chẩn đoán:</b> {exam.diagnosisSummary}
            </div>
          )}

          {exam.symptom && (
            <div className="text-sm mb-2">
              <b>Triệu chứng:</b> {exam.symptom}
            </div>
          )}

          {/* Tags */}
          <div className="flex flex-wrap gap-2 text-xs mt-3">
            {exam.prescription && (
              <span className="flex items-center gap-1 bg-blue-100 text-blue-700 px-2 py-1 rounded">
                <FileText className="h-3 w-3" />
                Có đơn thuốc
              </span>
            )}

            {!!exam.labOrders?.length && (
              <span className="flex items-center gap-1 bg-purple-100 text-purple-700 px-2 py-1 rounded">
                <FlaskConical className="h-3 w-3" />
                Có xét nghiệm
              </span>
            )}

            {exam.invoiceId && (
              <span className="flex items-center gap-1 bg-green-100 text-green-700 px-2 py-1 rounded">
                <Receipt className="h-3 w-3" />
                Có hóa đơn
              </span>
            )}
          </div>
        </Card>
      ))}

      <div ref={bottomRef} className="h-10 flex justify-center items-center">
        {loadingMore && (
          <Loader2 className="animate-spin h-6 w-6 text-muted-foreground" />
        )}
      </div>
    </div>
  );
}
