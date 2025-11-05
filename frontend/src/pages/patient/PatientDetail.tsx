"use client";

import { useParams } from "react-router-dom";
import { useEffect, useState, useRef, useCallback } from "react";
import { toast } from "sonner";
import { Button } from "@/components/ui/button";
import { FormModal } from "@/components/common/FormModal";
import {
  EntityTableWrapper,
  type FilterGroup,
  type SortRequest
} from "@/components/common/EntityTableWrapper";
import { PatientApi, type PatientResponse } from "@/api/patient/PatientApi";
import type { EmergencyContactResponse, AllergyResponse } from "@/api/models";
import * as yup from "yup";
import DynamicInfo from "@/components/common/DynamicInfo";

export default function PatientDetailPage() {
  const { id } = useParams();
  const [patient, setPatient] = useState<PatientResponse | null>(null);

  const [modalType, setModalType] = useState<null | "addEC" | "editEC" | "addAL" | "editAL">(null);
  const [editData, setEditData] = useState<any>(null);

  const ecTableRef = useRef<any>(null);
  const allergyTableRef = useRef<any>(null);

  useEffect(() => {
    PatientApi.getPatientById(id!)
      .then((res) => setPatient(res))
      .catch(() => toast.error("Failed to load patient info"));
  }, [id]);

  const fetchEC = useCallback(
    (
      page: number,
      size: number,
      filterGroup?: FilterGroup | null,
      sorts?: SortRequest[]
    ) =>
      PatientApi.getEmergencyContactByPatientId(
        id!,
        page,
        size,
        (filterGroup ?? ({} as FilterGroup)),
        (sorts ?? [])
      ).then((res) => ({ data: res })),
    [id]
  );

  const fetchAllergies = useCallback(
    (
      page: number,
      size: number,
      filterGroup?: FilterGroup | null,
      sorts?: SortRequest[]
    ) =>
      PatientApi.getAllergiesByPatientId(
        id!,
        page,
        size,
        (filterGroup ?? ({} as FilterGroup)),
        (sorts ?? [])
      ).then((res) => ({ data: res })),
    [id]
  );


  // Form fields
  const emergencyFields = [
    { name : "fullName", label: "Full Name", required: true },
    { name : "relationship", label: "Relationship", required: true },
    { name : "phone", label: "Phone", required: true },
  ];

  const allergyFields = [
    { name: "substance", label: "Substance", required: true },
    { name: "reaction", label: "Reaction", required: true },
    { name: "severity", label: "Severity", required: true },
  ];

  const currentFields =
    modalType?.includes("EC") ? emergencyFields : allergyFields;

  const handleSubmit = async (data: any) => {
    try {
      if (modalType === "addEC") {
        await PatientApi.createEmergencyContact(id!, data);
        ecTableRef.current.reload();
      }
      if (modalType === "editEC") {
        await PatientApi.updateEmergencyContact(id!, editData.id, data);
        ecTableRef.current.reload();
      }
      if (modalType === "addAL") {
        await PatientApi.createAllergy(id!, data);
        allergyTableRef.current.reload();
      }
      if (modalType === "editAL") {
        await PatientApi.updateAllergy(editData.id, data);
        allergyTableRef.current.reload();
      }

      toast.success("Saved successfully");
      setModalType(null);
      setEditData(null);
    } catch {
      toast.error("Action failed");
    }
  };

  if (!patient) return <div className="p-4">Loading...</div>;

  return (
    <div className="p-6 space-y-8">
      
      <div>
        <h2 className="text-xl font-bold mb-3">Patient Details</h2>
        <DynamicInfo
          data={patient}
          config={[
            { label: "Patient Code", name: "patientCode" },
            { label: "Full Name", name: "fullName" },
            { label: "Date of Birth", name: "dateOfBirth" },
            { label: "Gender", name: "gender" },
            { label: "Blood Type", name: "bloodType" },
            { label: "Phone", name: "phone" },
            { label: "Email", name: "email" },
            { label: "Address", name: "address" },
            { label: "Insurance Number", name: "insuranceNumber" },
          ]}
          columns={3}
        />
      </div>

      <div>
        <div className="flex justify-between mb-2">
          <h3 className="text-lg font-semibold">Emergency Contacts</h3>
          <Button onClick={() => setModalType("addEC")}>Add Contact</Button>
        </div>

        <EntityTableWrapper<EmergencyContactResponse>
          ref={ecTableRef}
          fetchData={fetchEC}
          title=""
          columns={[
            { title : "Full Name", dataIndex: "fullName" },
            { title : "Relationship", dataIndex: "relationship" },
            { title : "Phone", dataIndex: "phone" },
          ]}
          renderRowActions={(row) => (
            <>
              <Button
                variant="outline"
                size="sm"
                className="mr-2"
                onClick={() => {
                  setEditData(row);
                  setModalType("editEC");
                }}
              >
                Edit
              </Button>
              <Button
                variant="destructive"
                size="sm"
                onClick={async () => {
                  await PatientApi.deleteEmergencyContact(id!, row.id!);
                  ecTableRef.current.reload();
                }}
              >
                Delete
              </Button>
            </>
          )}
        />
      </div>

      <div>
        <div className="flex justify-between mb-2">
          <h3 className="text-lg font-semibold">Allergies</h3>
          <Button onClick={() => setModalType("addAL")}>Add Allergy</Button>
        </div>
        <EntityTableWrapper<AllergyResponse>
          ref={allergyTableRef}
          fetchData={fetchAllergies}
          title=""
          columns={[
            { title : "Substance", dataIndex: "substance" },
            { title : "Reaction", dataIndex: "reaction" },
            { title : "Severity", dataIndex: "severity" },
          ]}
          renderRowActions={(row) => (
            <>
              <Button
                variant="outline"
                size="sm"
                className="mr-2"
                onClick={() => {
                  setEditData(row);
                  setModalType("editAL");
                }}
              >
                Edit
              </Button>
              <Button
                variant="destructive"
                size="sm"
                onClick={async () => {
                  await PatientApi.deleteAllergy(row.id ? row.id : "");
                  allergyTableRef.current.reload();
                }}
              >
                Delete
              </Button>
            </>
          )}
        />
      </div>
      
      <FormModal
        open={!!modalType}
        onClose={() => {
          setModalType(null);
          setEditData(null);
        }}
        title="Edit"
        formFields={currentFields}
        defaultValues={editData ?? {}}
        onSubmit={handleSubmit}
        schema={yup.object(
          Object.fromEntries(currentFields.map(f => [f.name, yup.string().required()]))
        )}
      />
    </div>
  );
}
