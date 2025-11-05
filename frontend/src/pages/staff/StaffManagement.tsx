import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type PageResponse,
  type FilterGroup,
  type SortRequest,
} from "@/components/common/EntityTableWrapper";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { Eye, Pencil, KeyRound } from "lucide-react";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import type { Column } from "@/components/common/ReuseAbleTable";
import { useNavigate } from "react-router-dom";
import type { StaffResponse } from "@/api";
import { StaffApi } from "@/api/staff/StaffApi";

const fetchStaff = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<StaffResponse> }> => {
  try {
    const res = await StaffApi.getStaff(
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );

    return { data: res };
  } catch (err) {
    toast.error("Failed to load staff");
    throw err;
  }
};

export default function StaffManagementPage() {
  const navigate = useNavigate();

  const [selectForForm, setSelectForForm] =
    React.useState<StaffResponse | null>(null);
  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");

  const tableRef = React.useRef<any>(null);

  const handleUpdate = (row: StaffResponse) => {
    setSelectForForm(row);
    setFormType("update");
  };

  const fetchData = useCallback(fetchStaff, []);

  // Table Columns
  const columns: Column<StaffResponse>[] = [
    { title: "Code", dataIndex: "staffCode", sortable: true },
    { title: "Full Name", dataIndex: "fullName", sortable: true },
    { title: "Email", dataIndex: "email", sortable: true },
    { title: "Phone", dataIndex: "phone", sortable: true },
    { title: "Role", dataIndex: "staffType", sortable: true },
    { title: "Department", dataIndex: "departmentName", sortable: true },
    {
      title: "Active",
      dataIndex: "active",
      sortable: true,
      render: (v: boolean) => (v ? "Yes" : "No"),
    },
  ];

  // Form Config
  const formFieldConfigs: FormFieldConfig[] = [
    { name: "fullName", label: "Full Name", type: "text", required: true },
    { name: "email", label: "Email", type: "text", required: true },
    { name: "phone", label: "Phone", type: "text", required: true },
    {
      name: "staffType",
      label: "Role",
      type: "select",
      required: true,
      options: [
        { value: "DOCTOR", label: "Doctor" },
        { value: "NURSE", label: "Nurse" },
        { value: "TECHNICIAN", label: "Technician" },
        { value: "PHARMACIST", label: "Pharmacist" },
        { value: "RECEPTIONIST", label: "Receptionist" },
        { value: "CASHIER", label: "Cashier" },
        { value: "MANAGER", label: "Manager" },
      ],
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

  // Form Submit Schema + actions
  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Create Staff" : "Update Staff",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    big: true,

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
          await StaffApi.createStaff(data);
          toast.success("Staff created successfully");
        } else if (formType === "update" && selectForForm) {
          await StaffApi.updateStaff(selectForForm.id!, data);
          toast.success("Staff updated successfully");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch (err) {
        toast.error("Failed to submit staff data");
      }
    },
  };

  // Reset Password
  const handleResetPassword = async (id: string) => {
    try {
      await StaffApi.resetPassword(id);
      toast.success("Password reset successfully");
    } catch {
      toast.error("Failed to reset password");
    }
  };

  return (
    <>
      <EntityTableWrapper<StaffResponse>
        ref={tableRef}
        title="Staff Management"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["fullName", "staffCode", "email", "phone"]}
        pageSize={10}
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              className="mr-2"
              onClick={() => navigate(`/staff/staffs/${row.id}`)}
            >
              <Eye className="w-4 h-4" />
            </Button>

            <Button
              variant="outline"
              className="mr-2"
              onClick={() => handleUpdate(row)}
            >
              <Pencil className="w-4 h-4" />
            </Button>

            <Button
              variant="outline"
              className="mr-2"
              onClick={() => handleResetPassword(row.id!)}
            >
              <KeyRound className="w-4 h-4" />
            </Button>
          </>
        )}
        formModalProps={formModalProps}
        headerExtra={
          <Button
            onClick={() => {
              setSelectForForm(null);
              setFormType("create");
            }}
          >
            Add Staff
          </Button>
        }
        footerExtra={(ctx) => (
          <div>
            <strong>Total Staff: </strong> {ctx.totalElements}
          </div>
        )}
      />
    </>
  );
}
