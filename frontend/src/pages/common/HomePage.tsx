
import { ContentApi } from "@/api/cms/ContentApi";
import { NewsCard } from "@/components/common/NewsCard";
import { GenericListPage } from "@/components/common/GenericListPage";
import type { ContentResponse } from "@/api/models";

export default function NewsHomePage() {
  return (
    <GenericListPage<ContentResponse>
      title="Tin tức mới nhất"
      pageSize={9}
      fetchApi={(page, size) => ContentApi.filter(page, size)}
      renderItem={(item) => <NewsCard key={item.id} item={item} />}
    />
  );
}
