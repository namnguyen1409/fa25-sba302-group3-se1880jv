import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
} from "@/components/common/EntityTableWrapper";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { PatientApi, type PatientResponse } from "@/api/patient/PatientApi";
import { Eye, Pencil } from "lucide-react";
import * as yup from "yup";
import type { FormFieldConfig } from "@/components/common/FormModal";
import type { Column } from "@/components/common/ReuseAbleTable";
import { useNavigate } from "react-router-dom";
import { useEnumTranslation } from "@/hooks/translations/useEnumTranslation";

const fetchPatient = async (
  page: number,
  size: number,
  filterGroup?: FilterGroup | null,
  sorts?: SortRequest[]
): Promise<{ data: PageResponse<PatientResponse> }> => {
  try {
    const res = await PatientApi.getPatient(
      page,
      size,
      filterGroup ?? undefined,
      sorts
    );

    return { data: res };
  } catch (err) {
    toast.error("Failed to load patients");
    throw err;
  }
};

export default function PatientManagementPage() {
  const navigate = useNavigate();

  const [selectForForm, setSelectForForm] =
    React.useState<PatientResponse | null>(null);
  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");
  const tableRef = React.useRef<any>(null);
  const handleUpdate = (row: PatientResponse) => {
    setSelectForForm(row);
    setFormType("update");
  };

  const fetchData = useCallback(fetchPatient, []);

  const { translate, toOptions } = useEnumTranslation();

  const columns: Column<PatientResponse>[] = [
    { title: "Patient Code", dataIndex: "patientCode", sortable: true },
    { title: "Full Name", dataIndex: "fullName", sortable: true },
    { title: "Date of Birth", dataIndex: "dateOfBirth", sortable: true },
    { title: "Gender", dataIndex: "gender", sortable: true },
    {
      title: "Blood Type",
      dataIndex: "bloodType",
      sortable: true,
      render: (value) => {
        return value ? translate("bloodType", value) : "";
      },
    },
    { title: "Status", dataIndex: "status", sortable: true },
    { title: "Phone", dataIndex: "phone", sortable: true },
    { title: "Email", dataIndex: "email", sortable: true },
    { title: "Address", dataIndex: "address", sortable: true },
    { title: "Insurance Number", dataIndex: "insuranceNumber", sortable: true },
  ];

  const formFieldConfigs: FormFieldConfig[] = [
    { name: "fullName", label: "Full Name", type: "text", required: true },
    {
      name: "dateOfBirth",
      label: "Date of Birth",
      type: "date",
      required: true,
    },
    {
      name: "gender",
      label: "Gender",
      type: "select",
      required: true,
      options: toOptions("gender"),
    },
    {
      name: "bloodType",
      label: "Blood Type",
      type: "select",
      required: false,
      options: toOptions("bloodType"),
    },
    {
      name: "status",
      label: "Status",
      type: "select",
      required: true,
      options: [
        { value: "ACTIVE", label: "Active" },
        { value: "INACTIVE", label: "Inactive" },
        { value: "DECEASED", label: "Deceased" },
      ],
    },
    { name: "phone", label: "Phone", type: "text" },
    { name: "email", label: "Email", type: "text", required: true },
    { name: "address", label: "Address", type: "text" },
    { name: "insuranceNumber", label: "Insurance Number", type: "text" },
  ];

  const formModalProps = {
    open: formType !== "hidden",
    onClose: () => setFormType("hidden"),
    title: formType === "create" ? "Create Patient" : "Update Patient",
    formFields: formFieldConfigs,
    defaultValues: selectForForm || {},
    big: true,
    schema: yup.object(
      Object.fromEntries(
        formFieldConfigs.map((f) => [
          f.name,
          f.required
            ? f.type === "number"
              ? yup
                  .number()
                  .typeError(`${f.label} must be a number`)
                  .required(`${f.label} is required`)
              : f.type === "date"
              ? yup
                  .date()
                  .typeError(`${f.label} must be a valid date`)
                  .required(`${f.label} is required`)
              : yup.string().required(`${f.label} is required`)
            : yup.mixed(),
        ])
      )
    ),
    onSubmit: async (data: any) => {
      try {
        const payload = {
          ...data,
          dateOfBirth: data.dateOfBirth
            ? new Date(data.dateOfBirth).toISOString().split("T")[0]
            : null,
        };

        if (formType === "create") {
          await PatientApi.createPatient(payload);
          toast.success("Patient created successfully");
        } else if (formType === "update" && selectForForm) {
          await PatientApi.updatePatient(selectForForm.id, payload);
          toast.success("Patient updated successfully");
        }

        setFormType("hidden");
        tableRef.current?.reload();
      } catch (err) {
        console.error(err);
        toast.error("Failed to submit patient data");
      }
    },
  };

  return (
    <>
      <EntityTableWrapper<PatientResponse>
        ref={tableRef}
        title="Patient Management"
        fetchData={fetchData}
        columns={columns}
        smartSearchFields={["fullName", "patientCode", "email", "phone"]}
        pageSize={10}
        renderRowActions={(row) => (
          <>
            <Button
              variant="outline"
              className="mr-2"
              onClick={() => navigate(`/staff/patients/${row.id}`)}
            >
              <Eye className="w-4 h-4" />
            </Button>
            <Button variant="outline" onClick={() => handleUpdate(row)}>
              <Pencil className="w-4 h-4" />
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
            Add Patient
          </Button>
        }
        footerExtra={(ctx) => (
          <div>
            <strong>Total Patients: </strong> {ctx.totalElements}
          </div>
        )}
      />
    </>
  );
}
