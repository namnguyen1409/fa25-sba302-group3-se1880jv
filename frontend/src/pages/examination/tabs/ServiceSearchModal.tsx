import { useEffect, useState, useCallback } from "react";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Check, Loader2, X } from "lucide-react";
import { cn } from "@/lib/utils";
import { ServiceCatalogApi } from "@/api/service-catalog/ServiceCatalogApi";
import { useEnumTranslation } from "@/hooks/translations/useEnumTranslation";

interface ServiceItem {
  id: string;
  name: string;
  code: string;
  roomType: string;
  category: string;
  price: number;
}

interface ServiceSearchModalProps {
  open: boolean;
  onClose: () => void;
  onConfirm: (services: ServiceItem[]) => void;
}

export function ServiceSearchModal({
  open,
  onClose,
  onConfirm,
}: ServiceSearchModalProps) {
  const [keyword, setKeyword] = useState("");
  const [results, setResults] = useState<ServiceItem[]>([]);
  const [selected, setSelected] = useState<ServiceItem[]>([]);
  const [loading, setLoading] = useState(false);

  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [isLoadingMore, setIsLoadingMore] = useState(false);

  const { translate } = useEnumTranslation();

  const mapItem = (s: any): ServiceItem => ({
    id: s.id,
    name: s.name,
    code: s.code,
    roomType: s.roomType,
    category: s.category,
    price: s.price,
  });

  // ✅ Debounce search (300ms) + reset paging
  const debounceSearch = useCallback(() => {
    const handler = setTimeout(async () => {
      setLoading(true);

      try {
        const res = await ServiceCatalogApi.search(keyword, 0, 20);
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

  // ✅ Load thêm trang khi scroll
  const loadMore = async () => {
    if (!hasMore || isLoadingMore) return;

    setIsLoadingMore(true);
    try {
      const res = await ServiceCatalogApi.search(keyword, page, 20);

      setResults((prev) => [...prev, ...res.content.map(mapItem)]);

      const nextPage = page + 1;
      setPage(nextPage);
      setHasMore(nextPage < res.page.totalPages);
    } finally {
      setIsLoadingMore(false);
    }
  };

  // ✅ Select / unselect
  const toggleSelect = (item: ServiceItem) => {
    const exists = selected.some((s) => s.id === item.id);
    if (exists) {
      setSelected(selected.filter((s) => s.id !== item.id));
    } else {
      setSelected([...selected, item]);
    }
  };

  const handleRemoveSelected = (id: string) => {
    setSelected(selected.filter((s) => s.id !== id));
  };

  const handleConfirm = () => {
    onConfirm(selected);
    onClose();
  };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="min-w-5xl h-[85vh] overflow-hidden flex flex-col">
        <DialogHeader>
          <DialogTitle>Chọn dịch vụ</DialogTitle>
        </DialogHeader>

        <div className="flex gap-4 flex-1 overflow-hidden">
          {/* LEFT: SEARCH + LIST */}
          <div className="flex-1 flex flex-col border rounded p-3 bg-muted/20 overflow-hidden">
            <Input
              placeholder="Tìm tên, mã, loại dịch vụ..."
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
                        "p-3 rounded border cursor-pointer hover:bg-muted transition flex justify-between items-center",
                        isSelected && " border-blue-400"
                      )}
                    >
                      <div>
                        <p className="font-medium">{item.name}</p>
                        <p className="text-sm text-muted-foreground">
                          Mã: {item.code} — {translate("roomType", item.roomType)}
                        </p>
                        <p className="text-sm text-muted-foreground">
                          Loại: {item.category}
                        </p>
                        <p className="text-sm text-muted-foreground">
                          Giá: {item.price.toLocaleString()}₫
                        </p>
                      </div>
                      {isSelected && <Check className="text-blue-600 w-5 h-5" />}
                    </div>
                  );
                })}

              {isLoadingMore && (
                <div className="flex justify-center py-3 text-muted-foreground">
                  <Loader2 className="w-4 h-4 animate-spin" />
                </div>
              )}

              {!loading && results.length === 0 && (
                <p className="text-center py-8 text-sm text-muted-foreground">
                  Không tìm thấy dịch vụ
                </p>
              )}
            </div>
          </div>

          {/* RIGHT: SELECTED */}
          <div className="w-72 border rounded p-3 overflow-auto flex flex-col">
            <p className="font-medium mb-2">
              Đã chọn ({selected.length})
            </p>

            <div className="space-y-2">
              {selected.map((item) => (
                <div
                  key={item.id}
                  className="border rounded p-2 flex justify-between items-center bg-muted/30"
                >
                  <div className="text-sm">
                    <p className="font-medium">{item.name}</p>
                    <p className="text-muted-foreground">
                      {translate("roomType", item.roomType)}
                    </p>
                    <p className="text-muted-foreground">
                      {item.price.toLocaleString()}₫
                    </p>
                  </div>

                  <X
                    className="w-4 h-4 cursor-pointer text-red-500"
                    onClick={() => handleRemoveSelected(item.id)}
                  />
                </div>
              ))}

              {selected.length === 0 && (
                <p className="text-sm text-muted-foreground text-center py-4">
                  Chưa chọn dịch vụ nào
                </p>
              )}
            </div>
          </div>
        </div>

        <Separator className="my-2" />

        <DialogFooter>
          <Button variant="outline" onClick={onClose}>
            Hủy
          </Button>
          <Button onClick={handleConfirm} disabled={selected.length === 0}>
            Xác nhận ({selected.length})
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
