import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { Pencil, Upload, Eye } from "lucide-react";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import type { Column } from "@/components/common/ReuseAbleTable";
import type { IcdCodeResponse } from "@/api";
import { IcdApi } from "@/api/icd/IcdApi";

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
  const [formType, setFormType] = React.useState<"update" | "hidden" | "create">("hidden");
  const tableRef = React.useRef<any>(null);

  const handleUpdate = (row: IcdCodeResponse) => {
    setSelectForForm(row);
    setFormType("update");
  };

  const fetchData = useCallback(fetchIcd, []);

  const columns: Column<IcdCodeResponse>[] = [
    { title: "Code", dataIndex: "code", sortable: true },
    { title: "Name", dataIndex: "name", sortable: true },
    { title: "Description", dataIndex: "description", sortable: true },
    { title: "Chapter", dataIndex: "chapter", sortable: true },
    { title: "Version", dataIndex: "icdVersion", sortable: true },
    {
      title: "Active",
      dataIndex: "active",
      sortable: true,
      render: (v: boolean) => (v ? "Yes" : "No"),
    },
  ];

  const formFieldConfigs: FormFieldConfig[] = [
    {
      name: "description",
      label: "Description",
      type: "textarea",
      required: false,
    },
    {
      name: "active",
      label: "Active",
      type: "select",
      required: true,
      options: [
        { value: "true", label: "Active" },
        { value: "false", label: "Inactive" },
      ],
    },
  ];

  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: "Update ICD Code",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    schema: yup.object({
      description: yup.string().nullable(),
      active: yup.boolean().required(),
    }),
    onSubmit: async (data: any) => {
      try {
        await IcdApi.updateIcd(selectForForm!.id!, data);
        toast.success("ICD updated successfully");
        setFormType("hidden");
        tableRef.current?.reload();
      } catch (err) {
        console.error(err);
        toast.error("Failed to update ICD");
      }
    },
  };

  return (
    <>
      <EntityTableWrapper<IcdCodeResponse>
        ref={tableRef}
        title="ICD Management"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["code", "name", "chapter"]}
        pageSize={20}
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              className="mr-2"
              onClick={() => setSelectForForm(row)}
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
            Add Patient
          </Button>
        }
        formModalProps={formModalProps}
      />
    </>
  );
}
