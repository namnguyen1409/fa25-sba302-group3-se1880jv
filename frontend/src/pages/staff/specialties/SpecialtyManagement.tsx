import React, { useState } from "react";
import { Button } from "@/components/ui/button";
import { toast } from "sonner";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";

import { DepartmentApi } from "@/api/department/DepartmentApi";

import type { Column } from "@/components/common/ReuseAbleTable";
import type { FormFieldConfig } from "@/components/common/FormModal";
import * as yup from "yup";
import type { SpecialtyRequest, SpecialtyResponse } from "@/api";
import { SpecialtyApi } from "@/api/specialty/SpecialtyApi";
import { SearchSelect } from "@/components/common/SearchSelect";

export default function SpecialtyManagementPage() {
  const tableRef = React.useRef<any>(null);

  const [selectForForm, setSelectForForm] = useState<SpecialtyResponse | null>(
    null
  );
  const [formType, setFormType] = useState<"create" | "update" | "hidden">(
    "hidden"
  );

  const fetchData = async (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[]
  ): Promise<{ data: PageResponse<SpecialtyResponse> }> => {
    try {
      const res = await SpecialtyApi.getSpecialties(
        page,
        size,
        filterGroup ?? undefined,
        sorts
      );

      return { data: res };
    } catch (err) {
      toast.error("Failed to load specialties");
      throw err;
    }
  };

  const columns: Column<SpecialtyResponse>[] = [
    { title: "Name", dataIndex: "name", sortable: true },
    { title: "Department", dataIndex: "departmentName", sortable: true, render: (_value, record) => record.department?.name || "" },
    { title: "Description", dataIndex: "description" },
  ];

  const formFields: FormFieldConfig[] = [
    {
      name: "departmentId",
      label: "Department",
      type: "select",
      required: true,
      renderFormItem: ({ value, onChange }) => (
        <SearchSelect
          value={value}
          onChange={onChange}
          placeholder="Select Department"
          fetchOptions={async (searchText: string) => {
            try {
              const res = await DepartmentApi.search(searchText);
              return res.content.map<{
                label: string;
                value: string;
              }>((dept) => ({
                label: String(dept.name),
                value: dept.id!,
              }));
            } catch {
              toast.error("Failed to load departments");
              return [];
            }
          }}
        />
      ),
    },
    { name: "name", label: "Specialty Name", type: "text", required: true },
    { name: "description", label: "Description", type: "textarea" },
  ];

  const formModalProps = {
    open: formType !== "hidden",
    title: formType === "create" ? "Create Specialty" : "Update Specialty",
    formFields,
    defaultValues: selectForForm || {},
    onClose: () => setFormType("hidden"),
    schema: yup.object({
      departmentId: yup.string().required("Required"),
      name: yup.string().required("Required"),
      description: yup.string().nullable(),
    }),
    onSubmit: async (data: SpecialtyRequest) => {
      try {
        if (formType === "create") {
          await SpecialtyApi.create(data);
          toast.success("Specialty created");
        } else if (selectForForm) {
          await SpecialtyApi.update(selectForForm.id!, data);
          toast.success("Specialty updated");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch (err) {
        toast.error("Save failed");
      }
    },
  };

  return (
    <EntityTableWrapper<SpecialtyResponse>
      ref={tableRef}
      title="Specialty Management"
      fetchData={fetchData}
      columns={columns}
      smartSearchFields={["name", "departmentName"]}
      pageSize={10}
      renderRowActions={(row) => (
        <>
          <Button
            variant="outline"
            size="sm"
            onClick={() => {
              setSelectForForm(row);
              setFormType("update");
            }}
          >
            Edit
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
          Add Specialty
        </Button>
      }
    />
  );
}
