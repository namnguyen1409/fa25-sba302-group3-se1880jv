import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { StaffApi } from "@/api/staff/StaffApi";
import { Card, CardContent } from "@/components/ui/card";
import { GenericListPage } from "@/components/common/GenericListPage";
import type { ContentResponse } from "@/api/models";
import { ContentApi } from "@/api/cms/ContentApi";
import { NewsCard } from "@/components/common/NewsCard";

export default function StaffDetailPublicPage() {
  const { id } = useParams();
  const [item, setItem] = useState<any>(null);
  const [loading, setLoading] = useState(true);

  const load = async () => {
    try {
      const res = await StaffApi.getStaffById(id!);
      setItem(res);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, [id]);

  if (loading)
    return <div className="text-center py-20 text-gray-500">Đang tải...</div>;

  if (!item)
    return (
      <div className="text-center py-20 text-gray-500">
        Không tìm thấy nhân sự
      </div>
    );

  return (
    <>
      <div className="max-w-4xl mx-auto px-4 py-10">
        <Card className="shadow-sm">
          <CardContent className="p-6">
            {/* Header */}
            <div className="flex items-center gap-6">
              <img
                src={`https://api.dicebear.com/8.x/initials/svg?seed=${item.fullName}`}
                className="w-32 h-32 rounded-full bg-gray-200"
              />

              <div>
                <h1 className="text-3xl font-bold text-gray-900">
                  {item.fullName}
                </h1>
                <p className="text-gray-600 mt-1">{item.position?.name}</p>

                {item.specialty && (
                  <p className="text-gray-500 text-sm mt-1">
                    {item.specialty.name}
                  </p>
                )}
              </div>
            </div>

            {/* Info */}
            <div className="mt-8 grid grid-cols-1 sm:grid-cols-2 gap-6">
              <DetailRow label="Email" value={item.email} />
              <DetailRow label="Số điện thoại" value={item.phone} />
              <DetailRow label="Phòng ban" value={item.department?.name} />
              <DetailRow label="Chức vụ" value={item.position?.name} />
              <DetailRow label="Loại nhân sự" value={item.staffType} />
              <DetailRow label="Bằng cấp" value={item.education} />
              <DetailRow
                label="Số năm kinh nghiệm"
                value={item.experienceYears + " năm"}
              />
              <DetailRow label="Ngày vào làm" value={item.joinedDate} />
            </div>

            {/* Bio */}
            {item.bio && (
              <div className="mt-10">
                <h2 className="text-xl font-semibold mb-2">Giới thiệu</h2>
                <p className="text-gray-700 leading-relaxed whitespace-pre-line">
                  {item.bio}
                </p>
              </div>
            )}
          </CardContent>
        </Card>
        <hr className="my-10" />
      </div>
      <GenericListPage<ContentResponse>
        title="Bài viết của nhân sự"
        pageSize={9}
        fetchApi={(page, size) =>
          ContentApi.filter(page, size, {
            filters: [
              {
                field: "author.id",
                operator: "eq",
                value: id,
              },
            ],
          })
        }
        renderItem={(item) => <NewsCard key={item.id} item={item} />}
      />
    </>
  );
}

function DetailRow({ label, value }: { label: string; value?: any }) {
  if (!value) return null;

  return (
    <div>
      <p className="text-xs text-gray-500">{label}</p>
      <p className="text-gray-900 font-medium">{value}</p>
    </div>
  );
}
