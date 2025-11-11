import { useState, useEffect, useCallback } from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

import { Loader2, X } from "lucide-react";
import { cn } from "@/lib/utils";
import { LabTestApi } from "@/api/laboratory/LabTestApi";

interface LabTestItem {
  id: string;
  name: string;
  code: string;
  unit?: string;
  referenceRange?: string;
  category?: string;
}

interface LabTestSearchModalProps {
  open: boolean;
  onClose: () => void;
  onConfirm: (tests: LabTestItem[]) => void;
}

export function LabTestSearchModal({
  open,
  onClose,
  onConfirm,
}: LabTestSearchModalProps) {
  const [keyword, setKeyword] = useState("");
  const [results, setResults] = useState<LabTestItem[]>([]);
  const [selected, setSelected] = useState<LabTestItem[]>([]);
  const [loading, setLoading] = useState(false);

  const [page, setPage] = useState(0);
  const [_, setTotalPages] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [isLoadingMore, setIsLoadingMore] = useState(false);

  const mapItem = (t: any): LabTestItem => ({
    id: t.id,
    name: t.name,
    code: t.code,
    unit: t.unit,
    referenceRange: t.referenceRange,
    category: t.category,
  });

  const debounceSearch = useCallback(() => {
    const handler = setTimeout(async () => {
      setLoading(true);

      try {
        const res = await LabTestApi.search(keyword, 0, 20);

        setResults(res.content.map(mapItem));
        setPage(1);
        setHasMore(1 < res.page.totalPages);
      } finally {
        setLoading(false);
      }
    }, 300);

    return () => clearTimeout(handler);
  }, [keyword]);

  useEffect(() => {
    if (open) debounceSearch();
  }, [keyword, open]);

  // ✅ Load trang kế tiếp khi scroll
  const loadMore = async () => {
    if (!hasMore || isLoadingMore) return;

    setIsLoadingMore(true);
    try {
      const res = await LabTestApi.search(keyword, page, 20);

      setResults((prev) => [...prev, ...res.content.map(mapItem)]);

      const nextPage = page + 1;
      setPage(nextPage);
      setTotalPages(res.page.totalPages);

      // ✅ kiểm tra xem còn trang nữa không
      setHasMore(nextPage < res.page.totalPages);
    } finally {
      setIsLoadingMore(false);
    }
  };

  const toggleSelect = (item: LabTestItem) => {
    const exists = selected.some((s) => s.id === item.id);
    if (exists) {
      setSelected(selected.filter((s) => s.id !== item.id));
    } else {
      setSelected([...selected, item]);
    }
  };

  const handleConfirm = () => {
    onConfirm(selected);
    onClose();
  };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="min-w-5xl h-[85vh] overflow-hidden flex flex-col">
        <DialogHeader>
          <DialogTitle>Chọn xét nghiệm</DialogTitle>
        </DialogHeader>

        <div className="flex gap-4 flex-1 overflow-hidden">
          {/* LEFT */}
          <div className="flex-1 border rounded p-3 bg-muted/20 flex flex-col overflow-hidden">
            <Input
              placeholder="Tìm tên hoặc mã xét nghiệm..."
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
              className="mb-3"
            />

            <div
              className="flex-1 overflow-auto space-y-2"
              onScroll={(e) => {
                const el = e.currentTarget;
                if (el.scrollTop + el.clientHeight >= el.scrollHeight - 40) {
                  loadMore();
                }
              }}
            >
              {loading && (
                <div className="flex justify-center items-center py-8 text-muted-foreground">
                  <Loader2 className="w-5 h-5 animate-spin" />
                  <span className="ml-2">Đang tìm...</span>
                </div>
              )}

              {!loading &&
                results.map((item) => {
                  const isSelected = selected.some((s) => s.id === item.id);

                  return (
                    <div
                      key={item.id}
                      onClick={() => toggleSelect(item)}
                      className={cn(
                        "p-3 border rounded hover:bg-muted transition cursor-pointer",
                        isSelected && "border-blue-400"
                      )}
                    >
                      <p className="font-medium">{item.name}</p>
                      <p className="text-sm text-muted-foreground">
                        Mã: {item.code} — {item.category ?? "Không phân loại"}
                      </p>
                    </div>
                  );
                })}

              {isLoadingMore && (
                <div className="flex justify-center py-3 text-muted-foreground">
                  <Loader2 className="w-4 h-4 animate-spin" />
                </div>
              )}

              {!loading && results.length === 0 && (
                <p className="text-center text-muted-foreground py-6">
                  Không có kết quả
                </p>
              )}
            </div>
          </div>

          {/* RIGHT */}
          <div className="w-72 border rounded p-3 flex flex-col overflow-auto">
            <p className="font-medium mb-2">Đã chọn ({selected.length})</p>

            <div className="space-y-2">
              {selected.map((item) => (
                <div
                  key={item.id}
                  className="border rounded p-2 bg-muted/30 flex justify-between items-center"
                >
                  <div className="text-sm">
                    <p className="font-medium">{item.name}</p>
                    <p className="text-muted-foreground">{item.code}</p>
                  </div>
                  <X
                    className="w-4 h-4 cursor-pointer text-red-500"
                    onClick={() =>
                      setSelected(selected.filter((s) => s.id !== item.id))
                    }
                  />
                </div>
              ))}

              {selected.length === 0 && (
                <p className="text-sm text-muted-foreground text-center py-4">
                  Chưa chọn xét nghiệm nào
                </p>
              )}
            </div>
          </div>
        </div>

        <DialogFooter>
          <Button variant="outline" onClick={onClose}>
            Hủy
          </Button>
          <Button disabled={selected.length === 0} onClick={handleConfirm}>
            Xác nhận ({selected.length})
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
