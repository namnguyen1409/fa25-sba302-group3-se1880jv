"use client";

import { useEffect, useImperativeHandle, useState } from "react";
import { toast } from "sonner";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Separator } from "@/components/ui/separator";
import { Loader2, Filter } from "lucide-react";
import * as yup from "yup";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { ReusableTable } from "./ReuseAbleTable";
import { AdvancedFilterModal } from "./AdvancedFilterModal";

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
  pageable: {
    pageNumber: number;
    pageSize: number;
    sort: {
      sorted: boolean;
      unsorted: boolean;
      empty: boolean;
    };
    offset: number;
    paged: boolean;
    unpaged: boolean;
  };
  totalElements: number;
  totalPages: number;
  last: boolean;
  first: boolean;
  size: number;
  number: number;
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
  ref?: React.Ref<any>;
}

export function EntityTableWrapper<T extends Record<string, any>>({
  title,
  fetchData,
  columns,
  smartSearchFields = [],
  fieldOptions = [],
  formConfig,
  onDelete,
  pageSize = 10,
  getRowId = (r) => r.id || r.uuid || r.key,
  renderRowActions,
  ref
}: EntityTableWrapperProps<T>) {
  const [data, setData] = useState<T[]>([]);
  const [loading, setLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [_, setTotal] = useState(0);
  const [filterGroup, setFilterGroup] = useState<FilterGroup | null>(null);
  const [sorts, setSorts] = useState<SortRequest[]>([]);
  const [searchText, setSearchText] = useState("");
  const [totalPages, setTotalPages] = useState(0);

  // CRUD modal
  const [showModal, setShowModal] = useState(false);
  const [editingRow, setEditingRow] = useState<T | null>(null);

  // Advanced filter modal
  const [showAdvanced, setShowAdvanced] = useState(false);

  const loadData = async () => {
    setLoading(true);
    try {
      const res = await fetchData(page, pageSize, filterGroup, sorts);
      setData(res?.data?.content || []);
      setTotal(res?.data?.totalElements || 0);
      setTotalPages(res?.data?.totalPages || 0);
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
    }
  }));

  useEffect(() => {
    loadData();
  }, [page, filterGroup, sorts]);

  const handleSmartSearch = (text: string) => {
    setSearchText(text);
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

          {formConfig && (
            <Button size="sm" onClick={() => setShowModal(true)}>
              Add
            </Button>
          )}
        </div>
      </div>

      {/* Table */}
      <ReusableTable
        title=""
        columns={columns}
        data={data}
        loading={loading}
        getRowId={getRowId}
        onEdit={
          formConfig
            ? (row) => {
                setEditingRow(row);
                setShowModal(true);
              }
            : undefined
        }
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
      />

      {/* CRUD Form Modal */}
      {formConfig && (
        <Dialog open={showModal} onOpenChange={setShowModal}>
          <DialogContent className="max-w-lg">
            <DialogHeader>
              <DialogTitle>
                {editingRow ? "Edit Record" : "Add Record"}
              </DialogTitle>
            </DialogHeader>
            <FormModalContent
              formConfig={formConfig}
              editingRow={editingRow}
              onClose={() => {
                setShowModal(false);
                setEditingRow(null);
              }}
              reload={loadData}
            />
          </DialogContent>
        </Dialog>
      )}

      {/* Advanced Filter */}
      {showAdvanced && (
        <AdvancedFilterModal
          open={showAdvanced}
          onClose={() => setShowAdvanced(false)}
          fieldOptions={fieldOptions}
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

/* ----------------------- Form Modal ----------------------- */

interface FormModalContentProps {
  formConfig: {
    fields: FormField[];
    schema: yup.AnyObjectSchema;
    onSubmit: (values: any, editing?: any) => Promise<void>;
  };
  editingRow: Record<string, any> | null;
  onClose: () => void;
  reload: () => Promise<void>;
}

function FormModalContent({
  formConfig,
  editingRow,
  onClose,
  reload,
}: FormModalContentProps) {
  const { fields, schema, onSubmit } = formConfig;

  const {
    register,
    handleSubmit,
    reset,
    formState: { errors, isSubmitting },
  } = useForm({
    resolver: yupResolver(schema),
    defaultValues: editingRow || {},
  });

  const submit = async (values: any) => {
    try {
      await onSubmit(values, editingRow);
      toast.success("Saved successfully!");
      onClose();
      await reload();
    } catch (err) {
      console.error(err);
      toast.error("Failed to save record");
    }
  };

  useEffect(() => {
    reset(editingRow || {});
  }, [editingRow, reset]);

  return (
    <form onSubmit={handleSubmit(submit)} className="space-y-3">
      {fields.map((f) => (
        <div key={f.name}>
          <label className="text-sm font-medium mb-1 block">{f.label}</label>
          {f.type === "select" && f.options ? (
            <Select {...register(f.name)}>
              <SelectTrigger>
                <SelectValue placeholder={`Select ${f.label}`} />
              </SelectTrigger>
              <SelectContent>
                {f.options.map((opt) => (
                  <SelectItem key={opt.value} value={opt.value.toString()}>
                    {opt.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          ) : (
            <Input
              type={f.type || "text"}
              placeholder={f.label}
              {...register(f.name)}
            />
          )}
          {errors[f.name] && (
            <p className="text-xs text-red-500 mt-1">
              {(errors[f.name]?.message as string) || ""}
            </p>
          )}
        </div>
      ))}
      <Separator className="my-3" />
      <div className="flex justify-end gap-2">
        <Button
          variant="outline"
          type="button"
          onClick={onClose}
          disabled={isSubmitting}
        >
          Cancel
        </Button>
        <Button type="submit" disabled={isSubmitting}>
          {isSubmitting && <Loader2 className="h-4 w-4 mr-1 animate-spin" />}
          Save
        </Button>
      </div>
    </form>
  );
}
