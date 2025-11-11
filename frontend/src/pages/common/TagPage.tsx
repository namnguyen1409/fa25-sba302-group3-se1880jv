
import { ContentApi } from "@/api/cms/ContentApi";
import { NewsCard } from "@/components/common/NewsCard";
import { GenericListPage } from "@/components/common/GenericListPage";
import type { ContentResponse } from "@/api/models";
import { useParams } from "react-router-dom";

export default function TagPage() {

    const { tag } = useParams();

  return (
    <GenericListPage<ContentResponse>
      title={"Tin tức về chủ đề  #" + tag}
      pageSize={9}
      fetchApi={(page, size) =>
        ContentApi.filter(page, size, {
          filters: [
            {
              field: "tags",
              operator: "in",
              value: [tag],
            },
          ],
        })
      }
      renderItem={(item) => <NewsCard key={item.id} item={item} />}
    />
  );
}
