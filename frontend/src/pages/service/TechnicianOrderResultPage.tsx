import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Input } from "@/components/ui/input";
import { Textarea } from "@/components/ui/textarea";
import { toast } from "sonner";
import { Loader2 } from "lucide-react";

import type { ServiceOrderResponse, ServiceOrderItemResponse } from "@/api";
import { ServiceOrderApi } from "@/api/service/ServiceOrderApi";
import { FileApi } from "@/api/file/FileApi";

export default function TechnicianOrderResultPage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [order, setOrder] = useState<ServiceOrderResponse | null>(null);
  const [loading, setLoading] = useState(true);

  // results = note + uploaded files
  const [results, setResults] = useState<
    Record<
      string,
      {
        note: string;
        attachments: File[];
        existingFiles: {
          id: string;
          url: string;
          fileName: string;
        }[];
      }
    >
  >({});

  // ✅ Load order + attachments thủ công
  useEffect(() => {
    if (!id) return;

    async function init() {
      try {
        const orderRes = await ServiceOrderApi.getById(id);
        setOrder(orderRes);

        const mapData: any = {};

        for (const item of orderRes.items ?? []) {
          // ✅ fetch file riêng cho từng item
          const files = await FileApi.getByEntity(
            "SERVICE_ORDER_ITEM",
            item.id!
          );

          mapData[item.id!] = {
            note: item.note ?? "",
            attachments: [],
            existingFiles: files.map((f: any) => ({
              id: f.id,
              url: f.url,
              fileName: f.fileName,
            })),
          };
        }

        setResults(mapData);
      } catch {
        toast.error("Không tải được order");
      } finally {
        setLoading(false);
      }
    }

    init();
  }, [id]);

  // ✅ chọn file
  const handleFileChange = (itemId: string, files: FileList | null) => {
    if (!files) return;
    setResults((prev) => ({
      ...prev,
      [itemId]: {
        ...prev[itemId],
        attachments: Array.from(files),
      },
    }));
  };

  // ✅ Lưu từng item
  const saveItem = async (item: ServiceOrderItemResponse) => {
    const r = results[item.id!];

    try {
      const uploadedUrls: string[] = [];

      // ✅ upload từng file
      for (const file of r.attachments) {
        const url = await FileApi.upload({
          file,
          entityType: "SERVICE_ORDER_ITEM",
          entityId: item.id!,
        });
        uploadedUrls.push(url);
      }

      // ✅ update note (nếu không upload file → vẫn update note bình thường)
      await ServiceOrderApi.updateItem(item.id!, {
        note: r.note,
        attachmentUrls: uploadedUrls.length > 0 ? uploadedUrls : undefined,
      });

      toast.success("Đã lưu kết quả");

      // ✅ reload file
      const files = await FileApi.getByEntity("SERVICE_ORDER_ITEM", item.id!);

      setResults((prev) => ({
        ...prev,
        [item.id!]: {
          ...prev[item.id!],
          attachments: [],
          existingFiles: files,
        },
      }));
    } catch {
      toast.error("Không thể lưu");
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
            <span>Order: {order.orderCode}</span>
            <Badge>{order.status}</Badge>
          </CardTitle>
        </CardHeader>

        <CardContent className="space-y-6">
          {order.items?.map((item) => {
            const r = results[item.id!];

            return (
              <div key={item.id} className="border rounded-md p-4 space-y-3">
                <div className="flex justify-between items-center">
                  <div>
                    <div className="font-bold">{item.service?.name}</div>
                    <div className="text-sm text-muted-foreground">
                      {item.service?.category}
                    </div>
                  </div>
                  <Badge variant="secondary">#{item.id!.slice(0, 6)}</Badge>
                </div>

                {/* Note */}
                <div className="space-y-2">
                  <label className="font-semibold">Ghi chú</label>
                  <Textarea
                    value={r?.note || ""}
                    onChange={(e) =>
                      setResults((prev) => ({
                        ...prev,
                        [item.id!]: {
                          ...prev[item.id!],
                          note: e.target.value,
                        },
                      }))
                    }
                  />
                </div>

                {/* Upload */}
                <div className="space-y-2">
                  <label className="font-semibold">Tệp đính kèm</label>
                  <Input
                    type="file"
                    multiple
                    onChange={(e) =>
                      handleFileChange(item.id!, e.target.files)
                    }
                  />

                  {r?.attachments?.length > 0 && (
                    <div className="text-sm text-muted-foreground">
                      {r.attachments.length} file đã chọn
                    </div>
                  )}

                  {/* Existing files */}
                  {r?.existingFiles?.length > 0 && (
                    <div className="border rounded p-2 grid grid-cols-3 gap-2">
                      {r.existingFiles.map((file) => (
                        <div key={file.id} className="flex flex-col gap-1">
                          <img
                            src={file.url}
                            alt={file.fileName}
                            className="w-full h-28 object-cover rounded border"
                          />
                          <div className="text-xs truncate">
                            {file.fileName}
                          </div>
                        </div>
                      ))}
                    </div>
                  )}
                </div>

                <Button onClick={() => saveItem(item)}>Lưu kết quả</Button>
              </div>
            );
          })}
        </CardContent>
      </Card>
    </div>
  );
}
