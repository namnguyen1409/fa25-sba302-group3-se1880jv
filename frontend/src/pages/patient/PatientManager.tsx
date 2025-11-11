import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
  type FormField,
} from "@/components/common/EntityTableWrapper";
import { toast } from "sonner";
import DynamicInfo from "@/components/common/DynamicInfo";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { PatientApi, type PatientResponse } from "@/api/patient/PatientApi";
import { Eye } from "lucide-react";
import * as yup from "yup";

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
  const [selectRow, setSelectRow] = React.useState<PatientResponse | null>(
    null
  );
  const [selectForForm, setSelectForForm] =
    React.useState<PatientResponse | null>(null);
  const [formType, setFormType] = React.useState<
    "create" | "update" | "hidden"
  >("hidden");
  const [showViewModal, setShowViewModal] = React.useState(false);
  const tableRef = React.useRef<any>(null);

  const handleViewDetails = (row: PatientResponse) => {
    setSelectRow(row);
    setShowViewModal(true);
  };

  const handleUpdate = (row: PatientResponse) => {
    setSelectForForm(row);
    setFormType("update");
  };

  const fetchData = useCallback(fetchPatient, []);

  const columns = [
    { title: "Patient Code", dataIndex: "patientCode", sortable: true },
    { title: "Full Name", dataIndex: "fullName", sortable: true },
    { title: "Date of Birth", dataIndex: "dateOfBirth", sortable: true },
    { title: "Gender", dataIndex: "gender", sortable: true },
    { title: "Blood Type", dataIndex: "bloodType", sortable: true },
    { title: "Status", dataIndex: "status", sortable: true },
    { title: "Phone", dataIndex: "phone", sortable: true },
    { title: "Email", dataIndex: "email", sortable: true },
    { title: "Address", dataIndex: "address", sortable: true },
    { title: "Insurance Number", dataIndex: "insuranceNumber", sortable: true },
  ];

  const formFieldConfigs: FormField[] = [
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
      options: [
        { value: "MALE", label: "Male" },
        { value: "FEMALE", label: "Female" },
      ],
    },
    {
      name: "bloodType",
      label: "Blood Type",
      type: "select",
      required: false,
      options: [
        { value: "A_POSITIVE", label: "A+" },
        { value: "A_NEGATIVE", label: "A-" },
        { value: "B_POSITIVE", label: "B+" },
        { value: "B_NEGATIVE", label: "B-" },
        { value: "AB_POSITIVE", label: "AB+" },
        { value: "AB_NEGATIVE", label: "AB-" },
        { value: "O_POSITIVE", label: "O+" },
        { value: "O_NEGATIVE", label: "O-" },
      ],
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
              onClick={() => handleViewDetails(row)}
            >
              <Eye className="w-4 h-4" />
            </Button>
            <Button variant="outline" onClick={() => handleUpdate(row)}>
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
            Add Patient
          </Button>
        }
        footerExtra={(ctx) => (
          <div>
            <strong>Total Patients: </strong> {ctx.totalElements}
          </div>
        )}
      />

      <Dialog open={showViewModal} onOpenChange={setShowViewModal}>
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>Patient Details</DialogTitle>
          </DialogHeader>

          {selectRow && (
            <DynamicInfo
              data={selectRow}
              config={[
                { label: "Patient Code", name: "patientCode" },
                { label: "Email", name: "email" },
                { label: "Full Name", name: "fullName" },
                { label: "Date of Birth", name: "dateOfBirth" },
                { label: "Gender", name: "gender" },
                { label: "Blood Type", name: "bloodType" },
                { label: "Status", name: "status" },
                { label: "Phone", name: "phone" },
                { label: "Address", name: "address" },
                { label: "Insurance Number", name: "insuranceNumber" },
              ]}
              columns={2}
            />
          )}
        </DialogContent>
      </Dialog>
    </>
  );
}
