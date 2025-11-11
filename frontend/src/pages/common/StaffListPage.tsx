import { StaffApi } from "@/api/staff/StaffApi";
import { StaffCard } from "@/components/common/StaffCard";
import { GenericListPage } from "@/components/common/GenericListPage";
import type { StaffResponse } from "@/api/models";

export default function StaffListPage() {

  return (
    <GenericListPage<StaffResponse>
      title="Danh sách nhân sự"
      pageSize={12}
      fetchApi={(page, size) => StaffApi.filter(page, size)}
      renderItem={(item) => <StaffCard key={item.id} item={item} />}
      filter={{ active: true }} // optional
    />
  );
}
