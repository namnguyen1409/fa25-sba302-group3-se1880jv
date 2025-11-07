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

  const { translate } = useEnumTranslation();

  // ✅ Debounce search (300ms)
  const debounceSearch = useCallback(() => {
    const handler = setTimeout(async () => {
      setLoading(true);

      try {
        const res = await ServiceCatalogApi.search(keyword);
        const mapped = res.content.map((s: any) => ({
          id: s.id,
          name: s.name,
          code: s.code,
          roomType: s.roomType,
          category: s.category,
          price: s.price,
        }));

        setResults(mapped);
      } finally {
        setLoading(false);
      }
    }, 300);

    return () => clearTimeout(handler);
  }, [keyword]);

  useEffect(() => {
    if (open) debounceSearch();
  }, [keyword, open]);

  // ✅ Add/remove selected
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

        {/* TWO-PANEL LAYOUT */}
        <div className="flex gap-4 flex-1 overflow-hidden">

          {/* LEFT PANEL — SEARCH & RESULTS */}
          <div className="flex-1 flex flex-col border rounded p-3 bg-muted/20 overflow-hidden">

            {/* Search */}
            <Input
              placeholder="Tìm kiếm tên, mã, loại dịch vụ..."
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
              className="mb-3"
            />

            {/* Results */}
            <div className="flex-1 overflow-auto space-y-2">
              {loading && (
                <div className="flex justify-center items-center py-8 text-muted-foreground">
                  <Loader2 className="w-5 h-5 animate-spin" />
                  <span className="ml-2">Đang tìm...</span>
                </div>
              )}

              {!loading && results.length === 0 && (
                <p className="text-center py-8 text-sm text-muted-foreground">
                  Không tìm thấy dịch vụ
                </p>
              )}

              {results.map((item) => {
                const isSelected = selected.some((s) => s.id === item.id);
                return (
                  <div
                    key={item.id}
                    className={cn(
                      "p-3 rounded border cursor-pointer hover:bg-muted transition flex justify-between items-center",
                      isSelected && "bg-blue-50 border-blue-400"
                    )}
                    onClick={() => toggleSelect(item)}
                  >
                    <div>
                      <p className="font-medium">{item.name}</p>
                      <p className="text-sm text-muted-foreground">
                        Mã: {item.code} — {translate("roomType", item.roomType)}
                      </p>
                      <p className="text-sm text-muted-foreground">
                        Loại: {item.category}
                      </p>
                    </div>
                    {isSelected && <Check className="text-blue-600 w-5 h-5" />}
                  </div>
                );
              })}
            </div>
          </div>

          {/* RIGHT PANEL — SELECTED */}
          <div className="w-72 border rounded p-3 bg-white overflow-auto flex flex-col">

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
