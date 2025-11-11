import { useCallback, useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { toast } from "sonner";
import { Pencil, Plus, Archive, UploadCloud, Trash2 } from "lucide-react";
import slugify from "slugify";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import type { Column } from "@/components/common/ReuseAbleTable";
import { ContentApi } from "@/api/cms/ContentApi";
import type { ContentResponse } from "@/api/models";
import { Input } from "@/components/ui/input";
import { type FormFieldConfig } from "@/components/common/FormModal";
import * as yup from "yup";

const fetchContents = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<ContentResponse> }> => {
  try {
    const res = await ContentApi.filter(
      page,
      size,
      filterGroup ?? undefined,
      sorts as any
    );
    return { data: res };
  } catch (err) {
    toast.error("Load content fail");
    throw err;
  }
};

export default function ContentManagementPage() {
  const tableRef = useRef<any>(null);
  const navigate = useNavigate();

  const fetchData = useCallback(fetchContents, []);

  const statusBadge = (s: ContentResponse["status"]) => {
    const variant =
      s === "PUBLISHED"
        ? "default"
        : s === "ARCHIVED"
        ? "secondary"
        : "outline";
    return <Badge variant={variant}>{s}</Badge>;
  };

  const [openCreate, setOpenCreate] = useState(false);

  const createFields: FormFieldConfig[] = [
    {
      name: "title",
      label: "Title",
      type: "text",
      required: true,
      placeholder: "Enter title...",
    },
    {
      name: "slug",
      label: "Slug",
      type: "text",
      required: true,
      placeholder: "auto-generated-from-title",
      renderFormItem: ({ value, onChange, watch, setValue }) => {
        const title = watch("title");
        const slug = value;

        useEffect(() => {
          // user chưa đụng vào slug
          if (title) {
            const s = slugify(title, { lower: true, strict: true, locale: 'vi' });
            setValue?.("slug", s);
            console.log("auto-slug:", s);
          }
        }, [title]); // ✅ chỉ phụ thuộc title

        return <Input value={slug || ""} onChange={onChange} />;
      },
    },
  ];

  const handleQuickCreate = async (data: any) => {
    try {
      const res = await ContentApi.create({
        title: data.title,
        slug: data.slug,
        excerpt: "",
        coverImageUrl: "",
        tags: undefined,
        body: "{}",
        status: "DRAFT",
      });

      toast.success("Draft created");
      setOpenCreate(false);

      navigate(`/staff/contents/${res.id}`);
    } catch {
      toast.error("Create fail");
    }
  };

  const columns: Column<ContentResponse>[] = [
    { title: "Title", dataIndex: "title", sortable: true },
    { title: "Slug", dataIndex: "slug", sortable: true },
    {
      title: "Status",
      dataIndex: "status",
      sortable: true,
      render: (v) => statusBadge(v),
    },
    {
      title: "Author",
      dataIndex: "authorFullName",
      sortable: false,
      render: (_, row) => row.author?.fullName || "-",
    },
    {
      title: "Created",
      dataIndex: "createdDate",
      sortable: true,
      render: (v) => (v ? new Date(v).toLocaleString() : "-"),
    },
    {
      title: "Updated",
      dataIndex: "lastModifiedDate",
      sortable: true,
      render: (v) => (v ? new Date(v).toLocaleString() : "-"),
    },
  ];

  const handlePublish = async (row: ContentResponse) => {
    try {
      await ContentApi.publish(row.id!);
      toast.success("Published");
      tableRef.current?.reload();
    } catch {
      toast.error("Publish fail");
    }
  };

  const handleArchive = async (row: ContentResponse) => {
    try {
      await ContentApi.archive(row.id!);
      toast.success("Archived");
      tableRef.current?.reload();
    } catch {
      toast.error("Archive fail");
    }
  };

  const handleDelete = async (row: ContentResponse) => {
    try {
      await ContentApi.delete(row.id!);
      toast.success("Deleted");
      tableRef.current?.reload();
    } catch {
      toast.error("Delete fail");
    }
  };

  const formModalProps = {
    open: openCreate,
    onClose: () => setOpenCreate(false),
    title: "Create New Article",
    formFields: createFields,
    defaultValues: {
      title: "",
      slug: "",
    },
    big: false,
    onSubmit: handleQuickCreate,
    schema: yup.object({
      title: yup.string().required("Title is required"),
      slug: yup.string().required("Slug is required"),
    }),
  };

  return (
    <>
      <EntityTableWrapper<ContentResponse>
        ref={tableRef}
        title="CMS — Content Management"
        fetchData={fetchData}
        columns={columns}
        pageSize={10}
        smartSearchFields={["title", "slug", "summary", "category", "tags"]}
        renderRowActions={(row) => (
          <div className="flex gap-2">
            <Button
              variant="outline"
              size="sm"
              onClick={() => navigate(`/staff/contents/${row.id}`)}
            >
              <Pencil className="w-4 h-4" />
            </Button>

            {row.status !== "PUBLISHED" && (
              <Button
                variant="outline"
                size="sm"
                onClick={() => handlePublish(row)}
              >
                <UploadCloud className="w-4 h-4" />
              </Button>
            )}

            {row.status !== "ARCHIVED" && (
              <Button
                variant="outline"
                size="sm"
                onClick={() => handleArchive(row)}
              >
                <Archive className="w-4 h-4" />
              </Button>
            )}

            <Button
              variant="destructive"
              size="sm"
              onClick={() => handleDelete(row)}
            >
              <Trash2 className="w-4 h-4" />
            </Button>
          </div>
        )}
        headerExtra={
          <Button onClick={() => setOpenCreate(true)}>
            <Plus className="w-4 h-4 mr-2" /> New Article
          </Button>
        }
        footerExtra={(ctx) => (
          <div>
            <b>Total:</b> {ctx.totalElements}
          </div>
        )}
        formModalProps={formModalProps}
      />
    </>
  );
}
