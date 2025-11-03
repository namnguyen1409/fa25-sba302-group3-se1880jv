
import React, { useCallback } from "react";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest,
  type PageResponse,
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
import { useConfirm } from "@/hooks/useConfirm";
import { PatientApi, type PatientResponse } from "@/api/patient/PatientApi";
import { Eye } from "lucide-react";

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
  const [selectRow, setSelectRow] = React.useState<PatientResponse | null>(null);
  const [showViewModal, setShowViewModal] = React.useState(false);
  const tableRef = React.useRef<any>(null);

  const { confirm, ConfirmDialog } = useConfirm();

  const handleViewDetails = (row: PatientResponse) => {
    setSelectRow(row);
    setShowViewModal(true);
  };

  const fetchData = useCallback(fetchPatient, []);
  
  /**
   * "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "patientCode": "string",
        "fullName": "string",
        "dateOfBirth": "2025-11-01",
        "gender": "MALE",
        "bloodType": "A_POSITIVE",
        "status": "ACTIVE",
        "phone": "string",
        "email": "string",
        "address": "string",
        "insuranceNumber": "string",
        "initPassword": "string"
   */

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


  return (
    <>
      <EntityTableWrapper<PatientResponse>
        ref = {tableRef}
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
          </>
        )}
    
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


      <ConfirmDialog />
    </>
  );
}
