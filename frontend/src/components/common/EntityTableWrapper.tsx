import { useEffect, useImperativeHandle, useState } from "react";
import { toast } from "sonner";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Filter } from "lucide-react";
import * as yup from "yup";
import { ReusableTable } from "./ReuseAbleTable";
import { AdvancedFilterModal } from "./AdvancedFilterModal";
import { FormModal, type FormModalProps } from "./FormModal";

/* ----------------------- Types ----------------------- */

export interface Filter {
  field?: string;
  operator?: string;
  value?: any;
}

export interface FilterGroup {
  operator: "AND" | "OR";
  filters?: Filter[];
}

export interface SortRequest {
  field: string;
  direction: "ASC" | "DESC";
}

export interface PageResponse<T> {
  content: T[];
  page: {
    size: number;
    number: number;
    totalElements: number;
    totalPages: number;
  };
}

export interface FieldOption {
  name: string;
  label: string;
  type?: "text" | "number" | "boolean" | "date" | "select";
  options?: { value: any; label: string }[];
}

export interface FormField extends FieldOption {
  required?: boolean;
}

interface EntityTableWrapperProps<T> {
  title: string;
  fetchData: (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ) => Promise<{ data: PageResponse<T> }>;
  columns: any[];
  smartSearchFields?: string[];
  fieldOptions?: FieldOption[];
  formConfig?: {
    fields: FormField[];
    schema: yup.AnyObjectSchema;
    onSubmit: (values: any, editing?: T | null) => Promise<void>;
  };
  onDelete?: (id: any) => Promise<void>;
  pageSize?: number;
  getRowId?: (row: T) => string | number;
  renderRowActions?: (row: T) => React.ReactNode;
  formModalProps?: FormModalProps;
  ref?: React.Ref<any>;
  headerExtra?: React.ReactNode;
  footerExtra?:
    | React.ReactNode
    | ((ctx: {
        data: T[];
        page: number;
        totalPages: number;
        totalElements: number;
      }) => React.ReactNode);
}

export function EntityTableWrapper<T extends Record<string, any>>({
  title,
  fetchData,
  columns,
  smartSearchFields = [],
  fieldOptions = [],
  onDelete,
  pageSize = 10,
  getRowId = (r) => r.id || r.uuid || r.key,
  renderRowActions,
  formModalProps,
  ref,
  headerExtra,
  footerExtra,
}: EntityTableWrapperProps<T>) {
  const [data, setData] = useState<T[]>([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [filterGroup, setFilterGroup] = useState<FilterGroup | null>(null);
  const [sorts, setSorts] = useState<SortRequest[]>([]);
  const [searchText, setSearchText] = useState("");
  const [totalPages, setTotalPages] = useState(0);
  const [searchDebounceTimer, setSearchDebounceTimer] = useState<
    number | undefined
  >(undefined);

  // Advanced filter modal
  const [showAdvanced, setShowAdvanced] = useState(false);

  const loadData = async () => {
    setLoading(true);
    try {
      const res = await fetchData(page, pageSize, filterGroup, sorts);
      console.log("Fetched data:", res);
      setData(res?.data?.content || []);
      setTotalElements(res?.data?.page?.totalElements || 0);
      setTotalPages(res?.data?.page?.totalPages || 0);
    } catch (err) {
      console.error(err);
      toast.error("Failed to load data");
    } finally {
      setLoading(false);
    }
  };

  useImperativeHandle(ref, () => ({
    reload: () => loadData(),
    resetFilters: () => {
      setFilterGroup(null);
      setSorts([]);
      setPage(0);
      loadData();
    },
  }));

  useEffect(() => {
    loadData();
  }, [page, filterGroup, sorts]);

  const handleSmartSearch = (text: string) => {
    setSearchText(text);

    if (searchDebounceTimer) {
      clearTimeout(searchDebounceTimer);
    }

    const timer = window.setTimeout(() => {
      if (!text.trim()) {
        setFilterGroup(null);
        return;
      }

      const filters: Filter[] = smartSearchFields.map((f) => ({
        field: f,
        operator: "containsIgnoreCase",
        value: text.trim(),
      }));

      setFilterGroup({ operator: "OR", filters });
    }, 250);

    setSearchDebounceTimer(timer);
  };

  const handleDelete = async (id: any) => {
    if (!onDelete) return;
    if (!confirm("Are you sure to delete this item?")) return;
    await onDelete(id);
    await loadData();
  };

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex justify-between gap-2 items-center">
        <h2 className="text-lg font-semibold">{title}</h2>
        <div className="flex gap-2 items-center">
          {smartSearchFields.length > 0 && (
            <Input
              placeholder="Search..."
              value={searchText}
              onChange={(e) => handleSmartSearch(e.target.value)}
              className="w-[220px]"
            />
          )}

          {fieldOptions.length > 0 && (
            <Button
              variant="outline"
              size="sm"
              onClick={() => setShowAdvanced(true)}
            >
              <Filter className="h-4 w-4 mr-1" /> Advanced
            </Button>
          )}

          {headerExtra && <>{headerExtra}</>}
        </div>
      </div>

      <div className="w-full overflow-x-auto">
        {/* Table */}
        <ReusableTable
          title=""
          columns={columns}
          data={data}
          loading={loading}
          getRowId={getRowId}
          onDelete={onDelete ? (id) => handleDelete(id) : undefined}
          pagination={{
            page,
            size: pageSize,
            total: totalPages * pageSize,
            onPageChange: (p) => setPage(p),
          }}
          activeSorts={sorts}
          onSortChange={setSorts}
          renderRowActions={renderRowActions}
          footerExtra={
            typeof footerExtra === "function"
              ? footerExtra({
                  data,
                  page,
                  totalPages,
                  totalElements: totalElements,
                })
              : footerExtra
          }
        />
      </div>

      {/* CRUD Form Modal */}
      {formModalProps && <FormModal {...formModalProps} />}

      {/* Advanced Filter */}
      {showAdvanced && (
        <AdvancedFilterModal
          open={showAdvanced}
          onClose={() => setShowAdvanced(false)}
          fieldOptions={fieldOptions}
          initialFilter={filterGroup}
          onApply={(group) => {
            setFilterGroup(group);
            setShowAdvanced(false);
            toast.success("Filter applied!");
          }}
        />
      )}
    </div>
  );
}
