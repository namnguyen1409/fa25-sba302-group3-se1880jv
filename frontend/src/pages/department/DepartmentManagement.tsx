import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import type { Column } from "@/components/common/ReuseAbleTable";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import { Building2, Pencil } from "lucide-react";
import type { DepartmentResponse } from "@/api";
import { DepartmentApi } from "@/api/department/DepartmentApi";
import { useClinic } from "@/context/ClinicContext";

const fetchDepartments = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<DepartmentResponse> }> => {
  try {
    const res = await DepartmentApi.getDepartments(
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );
    return { data: res };
  } catch (err) {
    toast.error("Failed to load departments");
    throw err;
  }
};

export default function DepartmentManagementPage() {
  const [selectForForm, setSelectForForm] = React.useState<DepartmentResponse | null>(null);
  const [formType, setFormType] = React.useState<"create" | "update" | "hidden">("hidden");
  const tableRef = React.useRef<any>(null);

  const {clinicInfo} = useClinic();

  const handleUpdate = (row: DepartmentResponse) => {
    setSelectForForm(row);
    setFormType("update");
  };

  const fetchData = useCallback(fetchDepartments, []);

  // Table Columns
  const columns: Column<DepartmentResponse>[] = [
    { title: "Name", dataIndex: "name", sortable: true },
    { title: "Description", dataIndex: "description", sortable: false }
  ];

  // Form fields
  const formFieldConfigs: FormFieldConfig[] = [
    { name: "name", label: "Department Name", type: "text", required: true },
    { name: "description", label: "Description", type: "textarea", required: false }
  ];

  // Modal Config
  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Create Department" : "Update Department",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    big: false,
    schema: yup.object(
      Object.fromEntries(
        formFieldConfigs.map((f) => [
          f.name,
          f.required
            ? yup.string().required(`${f.label} is required`)
            : yup.mixed(),
        ])
      )
    ),

    onSubmit: async (data: any) => {
      try {
        if (formType === "create") {
          await DepartmentApi.createDepartment({
            ...data,
            clinicId: clinicInfo?.id || "",
          });
          toast.success("Department created successfully");
        } else if (formType === "update" && selectForForm) {
          await DepartmentApi.updateDepartment(selectForForm.id!, {
            ...data,
            clinicId: clinicInfo?.id || "",
          });
          toast.success("Department updated successfully");
        }
        setFormType("hidden");
        tableRef.current?.reload();
      } catch (err) {
        toast.error("Failed to save department");
      }
    },
  };

  return (
    <EntityTableWrapper<DepartmentResponse>
      ref={tableRef}
      title="Department Management"
      fetchData={fetchData}
      columns={columns}
      smartSearchFields={["name", "code"]}
      pageSize={10}
      renderRowActions={(row) => (
        <Button variant="outline" onClick={() => handleUpdate(row)}>
          <Pencil className="w-4 h-4" />
        </Button>
      )}
      formModalProps={formModalProps}
      headerExtra={
        <Button
          onClick={() => {
            setSelectForForm(null);
            setFormType("create");
          }}
        >
          <Building2 className="w-4 h-4 mr-2" /> Add Department
        </Button>
      }
      footerExtra={(ctx) => (
        <div>
          <strong>Total Departments:</strong> {ctx.totalElements}
        </div>
      )}
    />
  );
}
