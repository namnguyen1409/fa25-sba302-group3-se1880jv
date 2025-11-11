import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
  type FieldOption,
} from "@/components/common/EntityTableWrapper";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { Pencil, Eye, Plus } from "lucide-react";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import type { Column } from "@/components/common/ReuseAbleTable";
import type { IcdCodeResponse } from "@/api";
import { IcdApi } from "@/api/icd/IcdApi";
import { ViewDetailDialog } from "@/components/common/ViewDetailDialog";

const fetchIcd = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<IcdCodeResponse> }> => {
  try {
    const res = await IcdApi.getIcd(
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );

    return { data: res };
  } catch (err) {
    toast.error("Failed to load ICD codes");
    throw err;
  }
};

export default function IcdManagementPage() {
  const [selectForForm, setSelectForForm] =
    React.useState<IcdCodeResponse | null>(null);
  const [formType, setFormType] = React.useState<
    "update" | "hidden" | "create"
  >("hidden");
  const tableRef = React.useRef<any>(null);
  const [showViewModal, setShowViewModal] = React.useState(false);
  const [selectRow, setSelectRow] = React.useState<IcdCodeResponse | null>(
    null
  );

  const handleUpdate = (row: IcdCodeResponse) => {
    setSelectForForm(row);
    setFormType("update");
  };

  const fetchData = useCallback(fetchIcd, []);

  const columns: Column<IcdCodeResponse>[] = [
    { title: "Code", dataIndex: "code", sortable: true },
    { title: "Name", dataIndex: "name", sortable: true },
    {
      title: "Description",
      dataIndex: "description",
      sortable: true,

      render: (v: string) => <div className="max-w-sm truncate">{v}</div>,
    },
    { title: "Chapter", dataIndex: "chapter", sortable: true },
    { title: "Version", dataIndex: "icdVersion", sortable: true },
  ];

  const formFieldConfigs: FormFieldConfig[] = [
    {
      name: "description",
      label: "Description",
      type: "textarea",
      required: false,
    },
  ];

  const addFormFieldConfigs: FormFieldConfig[] = [
    {
      name: "code",
      label: "Code",
      type: "text",
      required: true,
    },
    {
      name: "name",
      label: "Name",
      type: "text",
      required: true,
    },
    {
      name: "chapter",
      label: "Chapter",
      type: "text",
      required: true,
    },
    {
      name: "icdVersion",
      label: "ICD Version",
      type: "text",
      required: true,
    },
    ...formFieldConfigs,
  ];

  const formModalProps = {
    title: formType === "create" ? "Create ICD Code" : "Update ICD Code",
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    formFields: formType === "create" ? addFormFieldConfigs : formFieldConfigs,
    defaultValues: selectForForm || {},
    schema: yup.object({
      description: yup.string().nullable(),
      code:
        formType === "create"
          ? yup.string().required("Code is required")
          : yup.string(),
      name:
        formType === "create"
          ? yup.string().required("Name is required")
          : yup.string(),
      chapter:
        formType === "create"
          ? yup.string().required("Chapter is required")
          : yup.string(),
      icdVersion:
        formType === "create"
          ? yup.string().required("ICD Version is required")
          : yup.string(),
    }),
    onSubmit: async (data: any) => {
        try {
            if (formType === "create") {
                await IcdApi.createIcd(data);
                toast.success("ICD Code created successfully");
            } else if (formType === "update" && selectForForm) {
                await IcdApi.updateIcd(selectForForm.id!, data);
                toast.success("ICD Code updated successfully");
            }
            tableRef.current.reload();
            setFormType("hidden");
        } catch (err) {
            toast.error("Failed to submit ICD Code form");
            throw err;
        }
    },
  };

  const fieldOptions: FieldOption[] = [
    { name: "code", label: "Code", type: "text" },
    { name: "name", label: "Name", type: "text" },
    { name: "chapter", label: "Chapter", type: "text" },
    { name: "icdVersion", label: "ICD Version", type: "text" },
  ]

  return (
    <>
      <EntityTableWrapper<IcdCodeResponse>
        ref={tableRef}
        title="ICD Management"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["code", "name", "chapter"]}
        pageSize={20}
        fieldOptions={fieldOptions}
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              className="mr-2"
              onClick={() => {
                setSelectRow(row);
                setShowViewModal(true);
              }}
            >
              <Eye className="w-4 h-4" />
            </Button>
            <Button variant="outline" onClick={() => handleUpdate(row)}>
              <Pencil className="w-4 h-4" />
            </Button>
          </>
        )}
        headerExtra={
          <Button
            onClick={() => {
              setSelectForForm(null);
              setFormType("create");
            }}
          >
            <Plus className="w-4 h-4 mr-2" />
            Add ICD Code
          </Button>
        }
        formModalProps={formModalProps}
        footerExtra={(ctx) => (
          <div>
            <strong>Total Icd Code: </strong> {ctx.totalElements}
          </div>
        )}
      />

      <ViewDetailDialog
        open={showViewModal}
        onClose={setShowViewModal}
        title="ICD Code Details"
        data={selectRow}
        config={[
          { label: "Code", name: "code", type: "text" },
          { label: "Name", name: "name", type: "text" },
          { label: "Description", name: "description", type: "text" },
          { label: "Chapter", name: "chapter", type: "text" },
          { label: "Version", name: "icdVersion", type: "text" },
        ]}
        columns={1}
      />
    </>
  );
}
